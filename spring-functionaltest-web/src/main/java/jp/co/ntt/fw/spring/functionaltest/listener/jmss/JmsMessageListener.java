/*
 * Copyright 2014-2018 NTT Corporation.
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
 */
package jp.co.ntt.fw.spring.functionaltest.listener.jmss;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsAmqReceivingService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsDbTransactedAmqReceivingService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsSharedService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsValidationService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.ReceivedEvent;

import org.apache.activemq.BlobMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.JmsResponse;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.SystemException;

@Component
public class JmsMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(
            JmsMessageListener.class);

    @Inject
    SmartValidator validator;

    @Inject
    JmsAmqReceivingService jmsAmqReceivingService;

    @Inject
    JmsDbTransactedAmqReceivingService jmsDbTransactedAmqReceivingService;

    @Inject
    JmsSharedService jmsSharedService;

    @Inject
    JmsValidationService jmsValidationService;

    @Inject
    ApplicationEventPublisher eventPublisher;

    @JmsListener(containerFactory = "conCacheDynamicNoTranContainerFactory", destination = "TestQueue0101001")
    public void receiveMessageByAmq(JmsTodo jmsTodo) throws IOException {
        jmsAmqReceivingService.receiveMessageByAmq(jmsTodo);
    }

    @JmsListener(containerFactory = "conCacheNoTranContainerFactory", destination = "TestQueue0102001")
    public void receiveMessageByJndiCon(JmsTodo jmsTodo) throws IOException {
        jmsAmqReceivingService.receiveMessageByJndiCon(jmsTodo);
    }

    @JmsListener(containerFactory = "conCacheNoTranContainerFactory", destination = "TestQueue0103001")
    public void receiveMessageByCacheCon(JmsTodo jmsTodo) throws IOException {
        jmsAmqReceivingService.receiveMessageByCacheCon(jmsTodo);
    }

    @JmsListener(containerFactory = "conCacheNoTranContainerFactory", destination = "TestQueue0301001")
    public void receiveMessageByJmsMessage(
            javax.jms.TextMessage message) throws IOException, JMSException {
        jmsAmqReceivingService.receiveMessageByJmsMessage(message);
    }

    @JmsListener(containerFactory = "conCacheNoTranContainerFactory", destination = "TestQueue0301003")
    public void receiveMessageAddKey(
            Message<JmsTodo> jmsTodoMessage) throws IOException {
        jmsAmqReceivingService.receiveMessageAddKey(jmsTodoMessage);
    }

    @JmsListener(containerFactory = "topicContainerFactory", destination = "TestTopic0301004")
    public void receiveMessageForTopic1(JmsTodo jmsTodo) throws IOException {
        jmsAmqReceivingService.receiveMessageForTopic1(jmsTodo);
    }

    @JmsListener(containerFactory = "topicContainerFactory", destination = "TestTopic0301004")
    public void receiveMessageForTopic2(JmsTodo jmsTodo) throws IOException {
        jmsAmqReceivingService.receiveMessageForTopic2(jmsTodo);
    }

    @JmsListener(containerFactory = "conCacheDynamicNoTranContainerFactory", destination = "TestQueue0901001")
    public void receiveMessageBlobMessage(
            BlobMessage message) throws IOException, JMSException {
        try (InputStream inputStream = message.getInputStream()) {
            jmsAmqReceivingService.receiveMessageStream(inputStream);
        }
    }

    @JmsListener(containerFactory = "conCacheNoTranContainerFactory", destination = "TestQueue0302001")
    public void receiveMessageMany(
            javax.jms.TextMessage message) throws IOException, JMSException, InterruptedException {
        jmsAmqReceivingService.receiveMessageMany(message);
    }

    @JmsListener(containerFactory = "conCacheNoTranContainerFactory", destination = "TestQueue0303001")
    public void receiveMessageWithPriorityA(JmsTodo jmsTodo,
            @Header("jms_priority") Integer priority,
            @Header("jms_deliveryMode") Integer deliveryMode,
            @Headers Map<String, Object> headers) throws IOException, JMSException {
        jmsAmqReceivingService.receiveMessageWithHeaderOK(jmsTodo, priority,
                deliveryMode, headers);
    }

    @JmsListener(containerFactory = "conCacheNoTranContainerFactory", destination = "TestQueue0401002", selector = "TodoStatus = 'updated'")
    public void receiveUpdatedTodo(
            JmsTodo jmsTodo) throws IOException, JMSException {
        jmsAmqReceivingService.receiveMessageBySelectorFalse(jmsTodo);
    }

    @JmsListener(containerFactory = "conCacheNoTranContainerFactory", destination = "TestQueue0401003", selector = "TodoStatus = 'deleted'")
    public void receiveDeletedTodo(
            JmsTodo jmsTodo) throws IOException, JMSException {
        jmsAmqReceivingService.receiveMessageBySelectorTrue(jmsTodo);
    }

    @JmsListener(containerFactory = "conCacheNoTranContainerFactory", destination = "TestQueue0402001A")
    @SendTo("TestQueue0402001B")
    public JmsTodo receiveMessageSendTo(JmsTodo jmsTodo) {
        logger.debug("Message : {}, ConcurrentThreadID is {}", jmsTodo
                .getJmsTodoId(), Thread.currentThread().getId());
        return jmsTodo;
    }

    @JmsListener(containerFactory = "conCacheNoTranContainerFactory", destination = "TestQueue0402001B")
    public void receiveMessageFromSendTo(JmsTodo jmsTodo) throws IOException {
        jmsAmqReceivingService.receiveMessageFromSendTo(jmsTodo);
    }

    @JmsListener(containerFactory = "conCacheNoTranContainerFactory", destination = "TestQueue0403001A")
    public JmsResponse<JmsTodo> receiveMessageJmsResponse(JmsTodo jmsTodo) {

        logger.debug("Received Message! {}", jmsTodo);

        String resQueue = null;

        if (jmsTodo.isFinished()) {
            resQueue = "TestQueue0403001B";
        } else {
            resQueue = "TestQueue0403001C";
        }

        return JmsResponse.forQueue(jmsTodo, resQueue);
    }

    @JmsListener(containerFactory = "conCacheNoTranContainerFactory", destination = "TestQueue0403001B")
    public void receiveMessageFromJmsResponseB(
            Message<JmsTodo> jmsTodoMessage) throws IOException {
        jmsAmqReceivingService.receiveMessageFromJmsResponseB(jmsTodoMessage);
    }

    @JmsListener(containerFactory = "conCacheNoTranContainerFactory", destination = "TestQueue0403001C")
    public void receiveMessageFromJmsResponseC(
            Message<JmsTodo> jmsTodoMessage) throws IOException {
        jmsAmqReceivingService.receiveMessageFromJmsResponseC(jmsTodoMessage);
    }

    @JmsListener(containerFactory = "conCacheNoTranContainerFactory", destination = "TestQueue0403003A")
    public JmsResponse<JmsTodo> receiveMessageReSendAnotherMessageA(
            JmsTodo jmsTodo,
            @Header("jms_replyTo") Destination replyDestination,
            @Header("jms_correlationId") String correlationId) throws IOException, JMSException {

        if (!jmsTodo.getJmsTodoId().equals(correlationId)) {
            return null;
        }

        return JmsResponse.forDestination(jmsTodo, replyDestination);
    }

    @JmsListener(containerFactory = "conCacheNoTranContainerFactory", destination = "TestQueue0403003B")
    public void receiveMessageReSendAnotherMessageB(
            Message<JmsTodo> jmsTodoMessage) throws IOException, JMSException {
        jmsAmqReceivingService.receiveMessageReSendAnotherMessageB(
                jmsTodoMessage);
    }

    @JmsListener(containerFactory = "conCacheConcurrentSingleContainerFactory", destination = "TestQueue0404001")
    public void receiveMessageConcurrentListenerSingle(
            JmsTodo jmsTodo) throws IOException {
        logger.debug("Message : {}, ConcurrentThreadID is {}", jmsTodo
                .getJmsTodoId(), Thread.currentThread().getId());
        jmsAmqReceivingService.receiveMessageConcurrentListenerSingle(jmsTodo);
    }

    @JmsListener(containerFactory = "conCacheConcurrentMultipleContainerFactory", destination = "TestQueue0404002")
    public void receiveMessageConcurrentListenerMultiple(
            JmsTodo jmsTodo) throws IOException {
        logger.debug("Message : {}, ConcurrentThreadID is {}", jmsTodo
                .getJmsTodoId(), Thread.currentThread().getId());
        jmsAmqReceivingService.receiveMessageConcurrentListenerMultiple(
                jmsTodo);
    }

    @JmsListener(destination = "TestQueue0701001A")
    public JmsResponse<JmsTodo> receiveMessageValidationOK(
            JmsTodo jmsTodo) throws IOException {
        BindingResult bindingResult = new BeanPropertyBindingResult(jmsTodo, "JmsTodo");
        validator.validate(jmsTodo, bindingResult);
        // 入力チェックエラーがないので、TestQueue0701001Bにメッセージが送信されない。
        if (bindingResult.hasErrors()) {
            return JmsResponse.forQueue(jmsTodo, "TestQueue0701001B");
        }
        jmsAmqReceivingService.receiveMessageValidationOK(jmsTodo);
        return null;
    }

    @JmsListener(destination = "TestQueue0701002A")
    public JmsResponse<JmsTodo> receiveMessageValidationNG(
            JmsTodo jmsTodo) throws IOException {
        BindingResult bindingResult = new BeanPropertyBindingResult(jmsTodo, "JmsTodo");
        validator.validate(jmsTodo, bindingResult);
        // 入力チェックエラーがあるので、TestQueue0701002Bにメッセージが送信される。
        if (bindingResult.hasErrors()) {
            return JmsResponse.forQueue(jmsTodo, "TestQueue0701002B");
        }
        return null;
    }

    @JmsListener(destination = "TestQueue0701001B")
    public void notReceiveInvalidMessage(JmsTodo jmsTodo) throws IOException {
        // TestQueue0701001Bにメッセージが送信されないので、受信処理は不要。
    }

    @JmsListener(destination = "TestQueue0701002B")
    public void receiveInvalidMessage(JmsTodo jmsTodo) throws IOException {
        jmsAmqReceivingService.receiveMessageValidationNG(jmsTodo);
    }

    @JmsListener(destination = "TestQueue0701003")
    public void receiveInputValidation(JmsTodo jmsTodo) throws IOException {
        try {
            jmsAmqReceivingService.receiveMessageInputValidationOK(jmsTodo);
        } catch (Exception e) {
            logger.error("Unexpected behavior");
        }
    }

    @JmsListener(destination = "TestQueue0701004")
    public void receiveInputValidationWithViolation(
            JmsTodo jmsTodo) throws IOException {
        try {
            jmsAmqReceivingService.doNothing(jmsTodo);
        } catch (ConstraintViolationException e) {
            jmsAmqReceivingService.receiveMessageInputValidationNg(jmsTodo);
        }
    }

    @JmsListener(destination = "TestQueue0701005")
    public void receiveInputValidationWithViolationMsgHandling(
            JmsTodo jmsTodo) throws IOException {
        try {
            jmsAmqReceivingService.doNothing(jmsTodo);
        } catch (ConstraintViolationException e) {
            for (ConstraintViolation<?> violation : e
                    .getConstraintViolations()) {
                if ("must be null".equals(violation.getMessage())) {
                    jmsAmqReceivingService
                            .receiveMessageInputValidationWithViolationErrMsg(
                                    jmsTodo);
                }
            }
        }
    }

    @JmsListener(destination = "TestQueue0701006")
    public void receiveInputValidationJmsTransaction(
            JmsTodo jmsTodo) throws IOException {
        final String invokedFlg = jmsTodo.getJmsTodoId() + ".invoked";

        if (!jmsSharedService.existsFile(jmsSharedService
                .getTemporaryDirectory() + invokedFlg)) {
            jmsSharedService.writeObjectToFile(jmsSharedService
                    .getTemporaryDirectory(), invokedFlg, jmsTodo);
            try {
                jmsAmqReceivingService.doNothing(jmsTodo);
            } catch (Exception e) {
                jmsAmqReceivingService
                        .receiveMessageInputValidationJmsTransaction(jmsTodo);
            }
        } else {
            logger.debug(
                    "unexpected case: this method was invoked more than once.");
            jmsSharedService.deleteFile(jmsSharedService.getTemporaryDirectory()
                    + invokedFlg);
        }
    }

    @JmsListener(destination = "TestQueue0701007")
    public void receiveInputValidationIsolatedTransactionJmsCommitDbRollback(
            JmsTodo jmsTodo) throws IOException {
        final String invokedFlg = jmsTodo.getJmsTodoId() + ".invoked";

        if (!jmsSharedService.existsFile(jmsSharedService
                .getTemporaryDirectory() + invokedFlg)) {
            jmsSharedService.writeObjectToFile(jmsSharedService
                    .getTemporaryDirectory(), invokedFlg, jmsTodo);
            try {
                jmsDbTransactedAmqReceivingService
                        .receiveInputValidationIsolatedTransactionJmsCommitDbRollback(
                                jmsTodo);
            } catch (Exception e) {
                jmsAmqReceivingService
                        .receiveInputValidationIsolatedTransactionJmsCommitDbRollbackWriteFile(
                                jmsTodo);
            }
        } else {
            logger.debug(
                    "unexpected case(jms rollback occured): this method is not expected to be invoked more than once.");
            jmsSharedService.deleteFile(jmsSharedService.getTemporaryDirectory()
                    + invokedFlg);
        }

    }

    @JmsListener(destination = "TestQueue0701008")
    public void receiveInputValidationIsolatedTransactionJmsAndDbCommit(
            JmsTodo jmsTodo) throws IOException {
        final String invokedFlg = jmsTodo.getJmsTodoId() + ".invoked";

        if (!jmsSharedService.existsFile(jmsSharedService
                .getTemporaryDirectory() + invokedFlg)) {
            jmsSharedService.writeObjectToFile(jmsSharedService
                    .getTemporaryDirectory(), invokedFlg, jmsTodo);
            try {
                jmsDbTransactedAmqReceivingService
                        .receiveInputValidationIsolatedTransactionJmsAndDbCommit(
                                jmsTodo);
                jmsValidationService.validate(jmsTodo);
            } catch (Exception e) {
                jmsAmqReceivingService
                        .receiveInputValidationIsolatedTransactionJmsAndDbCommitWriteFile(
                                jmsTodo);
            }
        } else {
            logger.debug(
                    "unexpected case(jms rollback occured): this method is not expected to be invoked more than once.");
            jmsSharedService.deleteFile(jmsSharedService.getTemporaryDirectory()
                    + invokedFlg);
        }

    }

    @JmsListener(containerFactory = "conCacheNoTranContainerFactory", destination = "TestQueue0802001")
    public void receiveMessageOtherErr(JmsTodo jmsTodo) {
        // イベントを通知するために必要なidを渡す
        throw new SystemException("e.xx.fw.9001", jmsTodo.getJmsTodoId());
    }

    @JmsListener(containerFactory = "conCacheTryCatchContainerFactory", destination = "TestQueue0803001")
    public void receiveMessageCatchBusinessErr(JmsTodo jmsTodo) {
        try {
            jmsAmqReceivingService.receiveMessageCatchBusinessErr(jmsTodo);
        } catch (BusinessException e) {
            // 受信完了を待っているスレッドの待機を解除するためにイベントを通知する
            eventPublisher.publishEvent(
                    new ReceivedEvent<JmsTodo>(this, jmsTodo));
        }
    }

    @JmsListener(containerFactory = "conCacheTryCatchContainerFactory", destination = "TestQueue0803002A")
    public JmsResponse<JmsTodo> receiveMessageCatchBusinessErrSetQueueA(
            JmsTodo jmsTodo) {
        try {
            jmsAmqReceivingService.receiveMessageCatchBusinessErrSetQueueA(
                    jmsTodo);
        } catch (BusinessException e) {
            return JmsResponse.forQueue(jmsTodo, "TestQueue0803002B");
        }
        return null;
    }

    @JmsListener(destination = "TestQueue0803002B")
    public void receiveMessageCatchBusinessErrSetQueueB(
            JmsTodo jmsTodo) throws IOException {
        jmsAmqReceivingService.receiveMessageCatchBusinessErrSetQueueB(jmsTodo);
    }

    @JmsListener(destination = "TestQueue0603001")
    public void receiveMessage_TxAsyncOK(JmsTodo jmsTodo) throws IOException {
        jmsAmqReceivingService.receiveMessage_TxAsyncOK(jmsTodo);
    }

    @JmsListener(destination = "TestQueue0603002")
    public void receiveMessage_TxAsyncNG(JmsTodo jmsTodo) throws IOException {
        jmsAmqReceivingService.receiveMessage_TxAsyncNG(jmsTodo);
    }

    @JmsListener(destination = "TestQueue0604001")
    public void receiveMessage_sendTxBestEffort1PhaseOK(
            JmsTodo jmsTodo) throws IOException {
        jmsAmqReceivingService.receiveMessage_sendTxBestEffort1PhaseOK(jmsTodo);
    }

    @JmsListener(destination = "TestQueue0604002")
    public void receiveMessage_sendTxBestEffort1PhaseNG(
            JmsTodo jmsTodo) throws IOException {
        jmsAmqReceivingService.receiveMessage_sendTxBestEffort1PhaseNG(jmsTodo);
    }

    @JmsListener(containerFactory = "conCacheChainedTranContainerFactory", destination = "TestQueue0604005")
    public void receiveMessage_receTxBestEffort1PhaseOK(
            JmsTodo jmsTodo) throws IOException {
        jmsAmqReceivingService.receiveMessage_receTxBestEffort1PhaseOK(jmsTodo);
    }

    @JmsListener(containerFactory = "conCacheChainedTranContainerFactory", destination = "TestQueue0604006")
    public void receiveMessage_receTxBestEffort1PhaseNG(
            JmsTodo jmsTodo) throws IOException {
        jmsAmqReceivingService.receiveMessage_receTxBestEffort1PhaseNG(jmsTodo);
    }
}
