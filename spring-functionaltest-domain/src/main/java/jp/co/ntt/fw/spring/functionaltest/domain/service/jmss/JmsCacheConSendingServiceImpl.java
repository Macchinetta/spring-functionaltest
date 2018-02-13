/*
 * Copyright 2014-2017 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.jmss;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.jmss.JmsTodoRepository;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;

@Service
public class JmsCacheConSendingServiceImpl implements
                                           JmsCacheConSendingService {

    private static final Logger logger = LoggerFactory.getLogger(
            JmsCacheConSendingServiceImpl.class);

    // Caching Connection
    @Inject
    JmsMessagingTemplate jndiConCacheJmsMessagingTemplate;

    @Inject
    JmsTemplate jndiConCacheJmsTemplate;

    @Inject
    JmsMessagingTemplate topicJmsMessagingTemplate;

    @Inject
    JmsMessagingTemplate selectedMessagingJmsTemplate;

    @Value("${app.jms.temporaryDirectory}")
    String temporaryDirectory;

    @Value("${app.jms.receiveWaitTime}")
    int receiveWaitTime;

    @Value("${app.jms.addReceiveWaitTime}")
    int addReceiveWaitTime;

    @Inject
    TransactionTemplate transactionTemplate;

    @Inject
    JmsTodoRepository jmsTodoRepository;

    @Inject
    JmsSharedService jmsSharedService;

    @Inject
    Destination testQueue0403003B;

    @Override
    public void sendMessageByCacheCon(final String id) {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0103001");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);
        jndiConCacheJmsMessagingTemplate.convertAndSend("TestQueue0103001",
                jmsTodo);
    }

    public void sendMessageByJmsMessage(final String id) throws IOException {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0301001");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        jndiConCacheJmsTemplate.send("TestQueue0301001", new MessageCreator() {
            public javax.jms.Message createMessage(
                    Session session) throws JMSException {

                TextMessage message = session.createTextMessage();
                message.setText(id);
                return message;
            }
        });

    }

    @Override
    public void sendMessageAddKey(final String id) throws IOException {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0301003");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        MessageHeaderAccessor accessor = new MessageHeaderAccessor();
        accessor.setHeader(JmsSharedService.HEADER_KEY1, id);

        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);

        Message<JmsTodo> jmsTodoMessage = MessageBuilder.withPayload(jmsTodo)
                .setHeaders(accessor).build();

        jndiConCacheJmsMessagingTemplate.send("TestQueue0301003",
                jmsTodoMessage);

    }

    @Override
    public void sendMessageForTopic(final String id) throws IOException {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestTopic0301004");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);

        topicJmsMessagingTemplate.convertAndSend("TestTopic0301004", jmsTodo);

    }

    @Override
    public void sendMessageMany(final String id) throws IOException {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0302001");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        MessageCreator mc = new MessageCreator() {
            public javax.jms.Message createMessage(
                    Session session) throws JMSException {

                TextMessage message = session.createTextMessage();
                message.setText(id);
                return message;
            }
        };

        for (int i = 0; i < 3; i++) {
            jndiConCacheJmsTemplate.send("TestQueue0302001", mc);
        }
    }

    @Override
    public void sendSyncMessage(
            final String id) throws IOException, InterruptedException {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0501001");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);
        jndiConCacheJmsMessagingTemplate.convertAndSend("TestQueue0501001",
                jmsTodo);

        try {
            TimeUnit.MILLISECONDS.sleep(receiveWaitTime);
        } catch (InterruptedException e) {
            logger.warn("InterruptedException Occured", e);
            throw e;
        }

        JmsTodo retJmsTodo = null;
        retJmsTodo = jndiConCacheJmsMessagingTemplate.receiveAndConvert(
                "TestQueue0501001", JmsTodo.class);

        jmsSharedService.writeObjectToFile(temporaryDirectory, jmsTodo
                .getJmsTodoId(), retJmsTodo);

    }

    @Override
    public void sendMessageWithHeadersOK(final String id) throws IOException {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0303001");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);
        jmsTodo.setDescription(id);
        selectedMessagingJmsTemplate.convertAndSend("TestQueue0303001",
                jmsTodo);
    }

    @Override
    public void sendMessageWithHeadersNG(
            final String id) throws IOException, InterruptedException {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0303002");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);
        jmsTodo.setDescription(id);
        selectedMessagingJmsTemplate.convertAndSend("TestQueue0303002",
                jmsTodo);

        try {
            TimeUnit.MILLISECONDS.sleep((long) (receiveWaitTime
                    + addReceiveWaitTime));
        } catch (InterruptedException e) {
            logger.warn("InterruptedException Occured", e);
            throw e;
        }

        // Headerに設定したTimeToLiveの時間が経過した後（メッセージ削除後）に受信できないことを確認するため、同期受信を行う。
        JmsTodo receiveJmeTodo = selectedMessagingJmsTemplate.receiveAndConvert(
                "TestQueue0303002", JmsTodo.class);

        if (receiveJmeTodo != null) {
            jmsSharedService.writeObjectToFile(temporaryDirectory,
                    receiveJmeTodo.getJmsTodoId(), receiveJmeTodo);
        }
    }

    public void sendMessageBySelectorFalse(final String id) throws IOException {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0401002");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);

        // メッセージヘッダープロパティ設定
        java.util.Map<String, Object> propMap = new java.util.HashMap<String, Object>();
        propMap.put("TodoStatus", "deleted");

        jndiConCacheJmsMessagingTemplate.convertAndSend("TestQueue0401002",
                jmsTodo, propMap);

    }

    public void sendMessageBySelectorTrue(final String id) throws IOException {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0401003");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);
        jmsTodo.setDescription(id);

        // メッセージヘッダープロパティ設定
        java.util.Map<String, Object> propMap = new java.util.HashMap<String, Object>();
        propMap.put("TodoStatus", "deleted");

        jndiConCacheJmsMessagingTemplate.convertAndSend("TestQueue0401003",
                jmsTodo, propMap);

    }

    @Override
    public void sendMessageForSendTo(final String id) {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0402001A");
        destinationNameList.add("TestQueue0402001B");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);
        jndiConCacheJmsMessagingTemplate.convertAndSend("TestQueue0402001A",
                jmsTodo);

    }

    public void sendMessageForJmsResponseB(final String id) {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0403001A");
        destinationNameList.add("TestQueue0403001B");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);
        jmsTodo.setFinished(true);

        Message<JmsTodo> jmsTodoMessage = MessageBuilder.withPayload(jmsTodo)
                .build();

        jndiConCacheJmsMessagingTemplate.send("TestQueue0403001A",
                jmsTodoMessage);

    }

    public void sendMessageForJmsResponseC(final String id) {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0403001A");
        destinationNameList.add("TestQueue0403001C");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);

        Message<JmsTodo> jmsTodoMessage = MessageBuilder.withPayload(jmsTodo)
                .build();

        jndiConCacheJmsMessagingTemplate.send("TestQueue0403001A",
                jmsTodoMessage);

    }

    public void sendMessageReSendAnotherMessage(
            final String id) throws JMSException {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0403003A");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);

        Message<JmsTodo> jmsTodoMessage = MessageBuilder.withPayload(jmsTodo)
                .setHeader(JmsHeaders.REPLY_TO, testQueue0403003B).setHeader(
                        JmsHeaders.CORRELATION_ID, id).build();

        jndiConCacheJmsMessagingTemplate.send("TestQueue0403003A",
                jmsTodoMessage);
    }

    public void sendMessageConcurrentListenerSingle(
            final String id) throws IOException {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0404001");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);

        jndiConCacheJmsMessagingTemplate.convertAndSend("TestQueue0404001",
                jmsTodo);

    }

    public void sendMessageConcurrentListenerMultiple(
            final String id) throws IOException {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0404002");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);

        jndiConCacheJmsMessagingTemplate.convertAndSend("TestQueue0404002",
                jmsTodo);

    }

    @Override
    public void sendMessageValidationOK(final String id) {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0701001A");
        destinationNameList.add("TestQueue0701001B");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);
        jmsTodo.setDescription(null);
        jndiConCacheJmsMessagingTemplate.convertAndSend("TestQueue0701001A",
                jmsTodo);
    }

    @Override
    public void sendMessageInputValidationOk(final String id) {
        final String destination = "TestQueue0701003";
        jmsSharedService.purgeMessageFrom(Arrays.asList(destination), false);

        final JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);
        jndiConCacheJmsMessagingTemplate.convertAndSend(destination, jmsTodo);
    }

    @Override
    public void sendMessageInputValidationNg(String id) {
        final String destination = "TestQueue0701004";
        jmsSharedService.purgeMessageFrom(Arrays.asList(destination), false);
        final JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setDescription("description");
        jmsTodo.setJmsTodoId(id);
        jndiConCacheJmsMessagingTemplate.convertAndSend(destination, jmsTodo);

    }

    @Override
    public void sendMessageInputValidationNgWithErrMsg(String id) {
        final String destination = "TestQueue0701005";
        jmsSharedService.purgeMessageFrom(Arrays.asList(destination), false);
        final JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setDescription("description");
        jmsTodo.setJmsTodoId(id);
        jndiConCacheJmsMessagingTemplate.convertAndSend(destination, jmsTodo);
    }

    @Override
    public void sendMessageValidationNG(final String id) {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0701002A");
        destinationNameList.add("TestQueue0701002B");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);
        jmsTodo.setDescription("description");
        jndiConCacheJmsMessagingTemplate.convertAndSend("TestQueue0701002A",
                jmsTodo);
    }

    public void sendMessageOtherErr(String id) {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0802001");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);

        // メッセージヘッダープロパティ設定
        java.util.Map<String, Object> propMap = new java.util.HashMap<String, Object>();
        propMap.put("MyPriorityKey", 9);

        jndiConCacheJmsMessagingTemplate.convertAndSend("TestQueue0802001",
                jmsTodo, propMap);
    }

    public void sendMessageCatchBusinessErr(String id) {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0803001");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);

        jndiConCacheJmsMessagingTemplate.convertAndSend("TestQueue0803001",
                jmsTodo);
    }

    public void sendMessageCatchBusinessErrSetQueue(String id) {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0803002A");
        destinationNameList.add("TestQueue0803002B");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);

        jndiConCacheJmsMessagingTemplate.convertAndSend("TestQueue0803002A",
                jmsTodo);
    }

    @Override
    public void sendMessage_TxRcvOK(String id) {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0602001");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        // Todoオブジェクト生成
        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);

        // メッセージ送信
        jndiConCacheJmsMessagingTemplate.convertAndSend("TestQueue0602001",
                jmsTodo);

    }

    @Override
    public void sendMessage_TxRcvNG(String id) throws BusinessException {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0602002");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        // Todoオブジェクト生成
        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);

        // メッセージ送信
        jndiConCacheJmsMessagingTemplate.convertAndSend("TestQueue0602002",
                jmsTodo);

    }

    @Override
    public void sendMessage_TxAsyncOK(String id) throws IOException {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0603001");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        // Todoオブジェクト生成
        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);

        // メッセージ送信
        jndiConCacheJmsMessagingTemplate.convertAndSend("TestQueue0603001",
                jmsTodo);

    }

    @Override
    public void sendMessage_TxAsyncNG(String id) throws IOException {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0603002");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        // Todoオブジェクト生成
        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);

        // メッセージ送信
        jndiConCacheJmsMessagingTemplate.convertAndSend("TestQueue0603002",
                jmsTodo);

    }

    @Override
    public void sendMessage_receTxBestEffort1PhaseOK(
            String id) throws IOException {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0604005");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        // Todoオブジェクト生成
        JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);
        jmsTodo.setDescription(id);
        jmsTodo.setDatetime(DateTime.now());

        // メッセージ送信
        jndiConCacheJmsMessagingTemplate.convertAndSend("TestQueue0604005",
                jmsTodo);

    }

    @Override
    public void sendMessage_receTxBestEffort1PhaseNG(
            final String id) throws IOException {

        // キューのクリーン処理
        List<String> destinationNameList = new ArrayList<>();
        destinationNameList.add("TestQueue0604006");
        jmsSharedService.purgeMessageFrom(destinationNameList, false);

        // Todoオブジェクト生成
        final JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setJmsTodoId(id);
        jmsTodo.setDescription(id);
        jmsTodo.setDatetime(DateTime.now());

        // メッセージ送信
        jndiConCacheJmsMessagingTemplate.convertAndSend("TestQueue0604006",
                jmsTodo);
    }

    @Override
    public void sendMessageInputValidationJmsTransaction(String id) {
        final String destination = "TestQueue0701006";
        jmsSharedService.purgeMessageFrom(Arrays.asList(destination), false);
        final JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setDescription("description");
        jmsTodo.setJmsTodoId(id);
        jndiConCacheJmsMessagingTemplate.convertAndSend(destination, jmsTodo);
    }

    @Override
    public void sendMessageInputValidationIsolatedTransactionJmsCommitDbRollback(
            String id) {
        final String destination = "TestQueue0701007";
        jmsSharedService.purgeMessageFrom(Arrays.asList(destination), false);
        final JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setDescription("description");
        jmsTodo.setJmsTodoId(id);
        jndiConCacheJmsMessagingTemplate.convertAndSend(destination, jmsTodo);
    }

    @Override
    public void sendMessageInputValidationIsolatedTransactionJmsAndDbCommit(
            String id) {
        final String destination = "TestQueue0701008";
        jmsSharedService.purgeMessageFrom(Arrays.asList(destination), false);
        final JmsTodo jmsTodo = new JmsTodo();
        jmsTodo.setDescription("description");
        jmsTodo.setJmsTodoId(id);
        jndiConCacheJmsMessagingTemplate.convertAndSend(destination, jmsTodo);
    }
}
