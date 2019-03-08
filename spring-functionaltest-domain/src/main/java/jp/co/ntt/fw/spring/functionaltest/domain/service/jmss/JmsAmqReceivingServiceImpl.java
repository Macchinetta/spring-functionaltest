/*
 * Copyright(c) 2014 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.jmss;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;
import javax.jms.JMSException;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.jmss.JmsTodoRepository;

import org.apache.activemq.BlobMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.ExceptionLogger;
import org.terasoluna.gfw.common.message.ResultMessages;

@Service
public class JmsAmqReceivingServiceImpl implements JmsAmqReceivingService {
    private static final Logger logger = LoggerFactory.getLogger(
            JmsAmqReceivingServiceImpl.class);

    @Value("${app.jms.temporaryDirectory}")
    String temporaryDirectory;

    @Value("${app.jms.receiveWaitTime}")
    int receiveWaitTime;

    @Value("${jms.mq.priority}")
    int priority;

    @Value("${jms.mq.deliveryMode}")
    int deliveryMode;

    @Inject
    JmsTemplate jndiConCacheJmsTemplate;

    @Inject
    JmsMessagingTemplate jndiConCacheJmsMessagingTemplate;

    @Inject
    JmsTodoRepository jmsTodoRepository;

    @Inject
    TransactionTemplate transactionTemplate;

    @Inject
    TransactionTemplate chainedTransactionTemplate;

    @Inject
    JmsSharedService jmsSharedService;

    @Inject
    JmsValidationService jmsValidationService;

    @Inject
    ApplicationEventPublisher eventPublisher;

    @Inject
    ExceptionLogger exceptionLogger;

    private final CyclicBarrier barrierUntilReceivedForTopic = new CyclicBarrier(2);

    @Override
    public void receiveMessageByAmq(JmsTodo jmsTodo) throws IOException {

        logger.debug("Received Message![TestQueue0101001] {}", jmsTodo
                .toString());

        jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo
                .getJmsTodoId(), jmsTodo);
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<JmsTodo>(this, jmsTodo));

    }

    @Override
    public void receiveMessageByJndiCon(JmsTodo jmsTodo) throws IOException {

        logger.debug("Received Message![TestQueue0102001] {}", jmsTodo
                .toString());

        jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo
                .getJmsTodoId(), jmsTodo);
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<JmsTodo>(this, jmsTodo));

    }

    @Override
    public void receiveMessageByCacheCon(JmsTodo jmsTodo) throws IOException {

        logger.debug("Received Message![TestQueue0103001] {}", jmsTodo
                .toString());

        jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo
                .getJmsTodoId(), jmsTodo);
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<JmsTodo>(this, jmsTodo));

    }

    @Override
    public void receiveMessageByJmsMessage(
            javax.jms.TextMessage message) throws IOException, JMSException {

        logger.debug("Received Message![TestQueue0301001] {}", message
                .toString());

        HashMap<String, String> map = new HashMap<String, String>();
        map.put(JmsSharedService.UUID_KEY, message.getText());
        jmsSharedService.writeMapToFile(temporaryDirectory, message.getText(),
                map);
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<String>(this, message
                .getText()));
    }

    @Override
    public void receiveMessageAddKey(
            Message<JmsTodo> jmsTodoMessage) throws IOException {

        logger.debug("Received Message![TestQueue0301003] {}", jmsTodoMessage
                .toString());

        HashMap<String, String> map = new HashMap<String, String>();
        map.put(JmsSharedService.HEADER_KEY1, (String) jmsTodoMessage
                .getHeaders().get(JmsSharedService.HEADER_KEY1));
        map.put(JmsSharedService.UUID_KEY, jmsTodoMessage.getPayload()
                .getJmsTodoId());

        jmsSharedService.writeMapToFile(temporaryDirectory, jmsTodoMessage
                .getPayload().getJmsTodoId(), map);
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(
                new ReceivedEvent<String>(this, jmsTodoMessage.getPayload()
                        .getJmsTodoId()));

    }

    @Override
    public void receiveMessageForTopic1(JmsTodo jmsTodo) throws IOException {

        logger.debug("Received Message![TestTopic0301004-1] {}", jmsTodo
                .toString());

        jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo
                .getJmsTodoId() + "topic1", jmsTodo);

        awaitReceivedForTopic();
        // Topic1, Topic2両方で受信完了してから、待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<JmsTodo>(this, jmsTodo));
    }

    @Override
    public void receiveMessageForTopic2(JmsTodo jmsTodo) throws IOException {

        logger.debug("Received Message![TestTopic0301004-2] {}", jmsTodo
                .toString());

        jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo
                .getJmsTodoId() + "topic2", jmsTodo);
        awaitReceivedForTopic();
        // Topic1, Topic2両方で受信完了してから、待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<JmsTodo>(this, jmsTodo));

    }

    @Override
    public void receiveMessageStream(
            InputStream inputStream) throws IOException, JMSException {

        logger.debug("Received Message![TestQueue0901001] {}", inputStream);

        Path path = Paths.get(temporaryDirectory + "output_tmp");
        Files.copy(inputStream, path);

        List<String> list = jmsSharedService.readFileToList(temporaryDirectory
                + "output_tmp");
        jmsSharedService.writeListToFile(temporaryDirectory, list.get(0), list);
        jmsSharedService.deleteFile(temporaryDirectory + "output_tmp");
        jmsSharedService.deleteFile(temporaryDirectory + "input_" + list.get(
                0));
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<String>(this, list.get(
                0)));

    }

    @Override
    public void receiveMessageMany(
            javax.jms.TextMessage message) throws IOException, JMSException, InterruptedException {

        logger.debug("Received Message! {}", message);

        List<String> list = new ArrayList<String>();
        list.add(message.getText());

        jmsSharedService.writeListToAddFile(temporaryDirectory, message
                .getText(), list);
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<String>(this, message
                .getText()));

    }

    @Override
    public void receiveMessageByCallback(
            javax.jms.TextMessage message) throws IOException, JMSException {

        logger.debug("Received Message! {}", message);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put(JmsSharedService.UUID_KEY, message.getText());
        map.put(JmsSharedService.PRIORITY, String.valueOf(message
                .getJMSPriority()));
        map.put(JmsSharedService.DELIVERY_MODE, String.valueOf(message
                .getJMSDeliveryMode()));

        jmsSharedService.writeMapToFile(temporaryDirectory, message.getText(),
                map);
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<String>(this, message
                .getText()));
    }

    @Override
    public void receiveMessageBySelectorFalse(
            JmsTodo jmsTodo) throws IOException, JMSException {

        logger.debug("Received Message! {}", jmsTodo);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put(JmsSharedService.UUID_KEY, jmsTodo.getJmsTodoId());

        jmsSharedService.writeMapToFile(temporaryDirectory, jmsTodo
                .getJmsTodoId(), map);

    }

    @Override
    public void receiveMessageBySelectorTrue(
            JmsTodo jmsTodo) throws IOException, JMSException {

        logger.debug("Received Message! {}", jmsTodo);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put(JmsSharedService.UUID_KEY, jmsTodo.getJmsTodoId());

        jmsSharedService.writeMapToFile(temporaryDirectory, jmsTodo
                .getJmsTodoId(), map);
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<JmsTodo>(this, jmsTodo));

    }

    @Override
    public void receiveMessageFromSendTo(JmsTodo jmsTodo) throws IOException {

        logger.debug("Received Message! {}", jmsTodo.toString());

        jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo
                .getJmsTodoId(), jmsTodo);
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<JmsTodo>(this, jmsTodo));

    }

    @Override
    public void receiveMessageFromJmsResponseB(
            Message<JmsTodo> jmsTodoMessage) throws IOException {

        logger.debug("Received Message! {}", jmsTodoMessage);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put(JmsSharedService.UUID_KEY, jmsTodoMessage.getPayload()
                .getJmsTodoId());
        map.put(JmsSharedService.RECEIVE_QUEUE, "TestQueue0403001B");

        jmsSharedService.writeMapToFile(temporaryDirectory, jmsTodoMessage
                .getPayload().getJmsTodoId(), map);
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(
                new ReceivedEvent<String>(this, jmsTodoMessage.getPayload()
                        .getJmsTodoId()));

    }

    @Override
    public void receiveMessageFromJmsResponseC(
            Message<JmsTodo> jmsTodoMessage) throws IOException {

        logger.debug("Received Message! {}", jmsTodoMessage);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put(JmsSharedService.UUID_KEY, jmsTodoMessage.getPayload()
                .getJmsTodoId());
        map.put(JmsSharedService.RECEIVE_QUEUE, "TestQueue0403001C");

        jmsSharedService.writeMapToFile(temporaryDirectory, jmsTodoMessage
                .getPayload().getJmsTodoId(), map);
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(
                new ReceivedEvent<String>(this, jmsTodoMessage.getPayload()
                        .getJmsTodoId()));
    }

    @Override
    public void receiveMessageReSendAnotherMessageB(
            Message<JmsTodo> jmsTodoMessage) throws IOException {

        logger.debug("Received Message>![TestQueue0403003] {}", jmsTodoMessage
                .toString());

        HashMap<String, String> map = new HashMap<String, String>();
        map.put(JmsSharedService.HEADER_KEY1, (String) jmsTodoMessage
                .getHeaders().get(JmsSharedService.HEADER_KEY1));
        map.put(JmsSharedService.UUID_KEY, jmsTodoMessage.getPayload()
                .getJmsTodoId());
        map.put(JmsSharedService.RECEIVE_QUEUE, "TestQueue0403003B");

        String todoId = jmsTodoMessage.getPayload().getJmsTodoId();
        jmsSharedService.writeMapToFile(temporaryDirectory, todoId, map);
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<String>(this, todoId));

    }

    @Override
    public void receiveMessageConcurrentListenerSingle(
            JmsTodo jmsTodo) throws IOException {

        logger.debug("Received Message![TestQueue0404001] {}", jmsTodo
                .toString());

        jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo
                .getJmsTodoId(), jmsTodo);
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<JmsTodo>(this, jmsTodo));
    }

    @Override
    public void receiveMessageConcurrentListenerMultiple(
            JmsTodo jmsTodo) throws IOException {

        logger.debug("Received Message![TestQueue0404002] {}", jmsTodo
                .toString());

        jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo
                .getJmsTodoId(), jmsTodo);
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<JmsTodo>(this, jmsTodo));
    }

    @Override
    public void receiveMessageWithHeaderOK(JmsTodo jmsTodo, Integer priority,
            Integer deliveryMode,
            Map<String, Object> headers) throws IOException, JMSException {

        logger.debug("Received Message![TestQueue0303001] {}", jmsTodo);

        Integer priorityInHeaders = (Integer) headers.get(JmsHeaders.PRIORITY);
        Integer deliveryModeInHeaders = (Integer) headers.get(
                JmsHeaders.DELIVERY_MODE);
        if (priorityInHeaders == priority
                && deliveryModeInHeaders == deliveryMode) {
            if (jmsTodo != null) {
                if (priority == this.priority
                        && deliveryMode == this.deliveryMode) {
                    // 一時ファイルへ出力
                    jmsSharedService.writeObjectToFile(temporaryDirectory,
                            jmsTodo.getJmsTodoId(), jmsTodo);
                } else {
                    logger.debug(
                            "Received Message![TestQueue0303001] But Message Headers are not expected. priority = {}  (expected 1), deliveryMode = {} (expected 2)",
                            priority, deliveryMode);
                }
            } else {
                logger.debug(
                        "Received Message![TestQueue0303001] But Message payload is null");
            }
        }
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<JmsTodo>(this, jmsTodo));

    }

    @Override
    public void receiveMessageValidationOK(JmsTodo jmsTodo) throws IOException {

        logger.debug("Received Message![TestQueue0701001] {}", jmsTodo
                .toString());

        jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo
                .getJmsTodoId(), jmsTodo);
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<JmsTodo>(this, jmsTodo));
    }

    @Override
    public void receiveMessageValidationNG(JmsTodo jmsTodo) throws IOException {

        logger.debug("Received Message![TestQueue0701002] {}", jmsTodo
                .toString());

        jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo
                .getJmsTodoId(), jmsTodo);
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<JmsTodo>(this, jmsTodo));
    }

    @Override
    public void receiveMessageInputValidationOK(
            JmsTodo jmsTodo) throws IOException {

        logger.debug("Received Message![TestQueue0701003] {}", jmsTodo
                .toString());

        jmsValidationService.writeValidatedObjectToFile(temporaryDirectory,
                jmsTodo.getJmsTodoId(), jmsTodo);
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<JmsTodo>(this, jmsTodo));
    }

    /**
     * expects that the invocation is interrupted.
     */
    @Override
    public void doNothing(JmsTodo jmsTodo) throws IOException {
        logger.debug("do nothing.");
        jmsValidationService.validate(jmsTodo);
        // 入力チェックをパスしてしまった場合に画面操作がタイムアウトまで待機するのを防ぐためにイベント通知する
        eventPublisher.publishEvent(new ReceivedEvent<JmsTodo>(this, jmsTodo));
    }

    @Override
    public void receiveMessageInputValidationNg(
            JmsTodo jmsTodo) throws IOException {
        writeObjectToFile("0701004", jmsTodo);
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<JmsTodo>(this, jmsTodo));
    }

    @Override
    public void receiveMessageInputValidationWithViolationErrMsg(
            JmsTodo jmsTodo) throws IOException {
        writeObjectToFile("0701005", jmsTodo);
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<JmsTodo>(this, jmsTodo));
    }

    @Override
    public void receiveMessageInputValidationJmsTransaction(
            JmsTodo jmsTodo) throws IOException {
        writeObjectToFile("0701006", jmsTodo);
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<JmsTodo>(this, jmsTodo));
    }

    @Override
    public void receiveInputValidationIsolatedTransactionJmsCommitDbRollbackWriteFile(
            JmsTodo jmsTodo) throws IOException {
        writeObjectToFile("0701007", jmsTodo);
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<JmsTodo>(this, jmsTodo));
    }

    @Override
    public void receiveInputValidationIsolatedTransactionJmsAndDbCommitWriteFile(
            JmsTodo jmsTodo) throws IOException {
        writeObjectToFile("0701008", jmsTodo);
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<JmsTodo>(this, jmsTodo));
    }

    private void writeObjectToFile(String queueSuffix,
            JmsTodo jmsTodo) throws IOException {
        logger.debug("Received Message![TestQueue{}] {}", queueSuffix, jmsTodo
                .toString());

        jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo
                .getJmsTodoId(), jmsTodo);

    }

    @Override
    public void receiveMessageCatchBusinessErr(JmsTodo jmsTodo) {

        logger.debug("Received Message![TestQueue0803001] {}", jmsTodo
                .toString());

        throw new BusinessException(ResultMessages.error().add(
                "e.sf.jmss.8004"));
    }

    @Override
    public void receiveMessageCatchBusinessErrSetQueueA(JmsTodo jmsTodo) {

        logger.debug("Received Message![TestQueue0803002A]" + jmsTodo
                .toString());

        throw new BusinessException(ResultMessages.error().add(
                "e.sf.jmss.8001"));
    }

    @Override
    public void receiveMessageCatchBusinessErrSetQueueB(
            JmsTodo jmsTodo) throws IOException {

        logger.debug("Received Message![TestQueue0803002] {}", jmsTodo
                .toString());

        jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo
                .getJmsTodoId(), jmsTodo);
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<JmsTodo>(this, jmsTodo));

    }

    @Override
    public boolean receiveMessage_TxSndOK(
            String id) throws IOException, JMSException, InterruptedException {

        logger.debug("Received Message![TestQueue0601001] {}", id);

        // メッセージ受信までの間にreceiveWaitTime秒の待ち時間を発生させる
        try {
            TimeUnit.MILLISECONDS.sleep(receiveWaitTime);
        } catch (InterruptedException e) {
            logger.warn("InterruptedException Occured", e);
            throw e;
        }

        // JMSからメッセージ件数取得
        String distinationName = "TestQueue0601001";
        String messageSelector = null;
        List<javax.jms.Message> msgs = jmsSharedService.getMessagesSelected(
                jndiConCacheJmsTemplate, distinationName, messageSelector);

        if (msgs.size() == 0) {
            return false;
        }

        // メッセージ受信
        JmsTodo jmsTodo = jndiConCacheJmsMessagingTemplate.receiveAndConvert(
                "TestQueue0601001", JmsTodo.class);

        // 一時ファイルへ出力
        jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo
                .getJmsTodoId(), jmsTodo);

        return true;

    }

    @Override
    public boolean receiveMessage_TxSndNG(
            String id) throws IOException, JMSException {

        logger.debug("Confirm no-Message![TestQueue0601002] {}", id);

        // JMSからメッセージ件数取得
        String distinationName = "TestQueue0601002";
        String messageSelector = null;
        List<javax.jms.Message> msgs = jmsSharedService.getMessagesSelected(
                jndiConCacheJmsTemplate, distinationName, messageSelector);

        if (msgs.size() == 0) {
            return false;
        }

        // メッセージ受信
        JmsTodo jmsTodo = jndiConCacheJmsMessagingTemplate.receiveAndConvert(
                "TestQueue0601002", JmsTodo.class);

        // 一時ファイルへ出力
        jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo
                .getJmsTodoId(), jmsTodo);

        return true;
    }

    @Override
    public void receiveMessage_TxAsyncOK(JmsTodo jmsTodo) throws IOException {

        logger.debug("Received Message![TestQueue0603001] {}", jmsTodo
                .toString());

        if (!jmsSharedService.existsFile(temporaryDirectory + jmsTodo
                .getJmsTodoId())) {
            jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo
                    .getJmsTodoId(), jmsTodo);
            // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
            eventPublisher.publishEvent(
                    new ReceivedEvent<JmsTodo>(this, jmsTodo));
        } else {
            // ロールバック時に実行される。
            jmsSharedService.deleteFile(temporaryDirectory + jmsTodo
                    .getJmsTodoId());
        }
    }

    @Override
    public void receiveMessage_TxAsyncNG(JmsTodo jmsTodo) throws IOException {

        logger.debug("Received Message![TestQueue0603002] {}", jmsTodo
                .toString());

        if (!jmsSharedService.existsFile(temporaryDirectory + jmsTodo
                .getJmsTodoId())) {
            jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo
                    .getJmsTodoId(), jmsTodo);

            throw new BusinessException(ResultMessages.error().add(
                    "e.sf.jmss.8002"));
        } else {
            // ロールバック時に実行される。
            jmsSharedService.deleteFile(temporaryDirectory + jmsTodo
                    .getJmsTodoId());
            jmsTodo.setJmsTodoId("rollbacked_" + jmsTodo.getJmsTodoId());
            jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo
                    .getJmsTodoId(), jmsTodo);
            // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
            eventPublisher.publishEvent(
                    new ReceivedEvent<JmsTodo>(this, jmsTodo));
        }

    }

    @Override
    public void receiveMessage_sendTxBestEffort1PhaseOK(
            JmsTodo jmsTodo) throws IOException {

        logger.debug("Received Message![TestQueue0604001] {}", jmsTodo
                .toString());

        jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo
                .getJmsTodoId(), jmsTodo);
        // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
        eventPublisher.publishEvent(new ReceivedEvent<JmsTodo>(this, jmsTodo));
    }

    @Override
    public void receiveMessage_sendTxBestEffort1PhaseNG(
            JmsTodo jmsTodo) throws IOException {

        logger.debug("Received Message![TestQueue0604002] {}", jmsTodo
                .toString());

        jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo
                .getJmsTodoId(), jmsTodo);
    }

    @Override
    public void receiveMessage_receTxBestEffort1PhaseOK(
            JmsTodo jmsTodo) throws IOException {

        logger.debug("Received Message![TestQueue0604005] {}", jmsTodo
                .toString());

        if (!jmsSharedService.existsFile(temporaryDirectory + jmsTodo
                .getJmsTodoId())) {
            jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo
                    .getJmsTodoId(), jmsTodo);

            // DBへデータ登録
            jmsSharedService.insert(jmsTodo);
            // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
            eventPublisher.publishEvent(
                    new ReceivedEvent<JmsTodo>(this, jmsTodo));
        } else {
            // ロールバック時に実行される。
            jmsSharedService.deleteFile(temporaryDirectory + jmsTodo
                    .getJmsTodoId());
        }
    }

    @Override
    public void receiveMessage_receTxBestEffort1PhaseNG(
            JmsTodo jmsTodo) throws IOException {

        logger.debug("Received Message![TestQueue0604006] {}", jmsTodo
                .toString());

        if (!jmsSharedService.existsFile(temporaryDirectory + jmsTodo
                .getJmsTodoId())) {
            jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo
                    .getJmsTodoId(), jmsTodo);

            // DBへデータ登録
            jmsSharedService.insert(jmsTodo);

            throw new BusinessException(ResultMessages.error().add(
                    "e.sf.jmss.8002"));
        } else {
            // ロールバック時に実行される。
            jmsSharedService.deleteFile(temporaryDirectory + jmsTodo
                    .getJmsTodoId());
            jmsTodo.setJmsTodoId("rollbacked_" + jmsTodo.getJmsTodoId());
            jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo
                    .getJmsTodoId(), jmsTodo);
            // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
            eventPublisher.publishEvent(
                    new ReceivedEvent<JmsTodo>(this, jmsTodo));
        }

    }

    @Override
    public int getMessageCount(String destinationName,
            String messageSelector) throws JMSException {
        return jmsSharedService.getMessagesSelected(jndiConCacheJmsTemplate,
                destinationName, messageSelector).size();
    }

    /**
     * Topic1,2の両方が受信完了するまで待機する
     */
    private void awaitReceivedForTopic() {
        try {
            barrierUntilReceivedForTopic.await(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            exceptionLogger.error(e);
            Thread.currentThread().interrupt();
        } catch (BrokenBarrierException e) {
            exceptionLogger.error(e);
        } catch (TimeoutException e) {
            exceptionLogger.error(e);
        }

    }

}
