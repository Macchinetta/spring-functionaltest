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
import java.util.Map;

import javax.jms.JMSException;
import javax.validation.Valid;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;

import org.springframework.messaging.Message;

public interface JmsAmqReceivingService {

    void receiveMessageByAmq(JmsTodo jmsTodo) throws IOException;

    void receiveMessageByJndiCon(JmsTodo jmsTodo) throws IOException;

    void receiveMessageByCacheCon(JmsTodo jmsTodo) throws IOException;

    void receiveMessageByJmsMessage(
            javax.jms.TextMessage message) throws IOException, JMSException;

    void receiveMessageAddKey(
            Message<JmsTodo> jmsTodoMessage) throws IOException;

    void receiveMessageForTopic1(JmsTodo jmsTodo) throws IOException;

    void receiveMessageForTopic2(JmsTodo jmsTodo) throws IOException;

    void receiveMessageStream(
            InputStream inputStream) throws IOException, JMSException;

    void receiveMessageMany(
            javax.jms.TextMessage message) throws IOException, JMSException, InterruptedException;

    void receiveMessageByCallback(
            javax.jms.TextMessage message) throws IOException, JMSException;

    void receiveMessageBySelectorFalse(
            JmsTodo jmsTodo) throws IOException, JMSException;

    void receiveMessageBySelectorTrue(
            JmsTodo jmsTodo) throws IOException, JMSException;

    void receiveMessageFromSendTo(JmsTodo jmsTodo) throws IOException;

    void receiveMessageFromJmsResponseB(
            Message<JmsTodo> jmsTodoMessage) throws IOException;

    void receiveMessageFromJmsResponseC(
            Message<JmsTodo> jmsTodoMessage) throws IOException;

    void receiveMessageReSendAnotherMessageB(
            Message<JmsTodo> jmsTodoMessage) throws IOException;

    void receiveMessageConcurrentListenerSingle(
            JmsTodo jmsTodo) throws IOException;

    void receiveMessageConcurrentListenerMultiple(
            JmsTodo jmsTodo) throws IOException;

    void receiveMessageWithHeaderOK(JmsTodo jmsTodo, Integer priority,
            Integer deliveryMode,
            Map<String, Object> headers) throws IOException, JMSException;

    void receiveMessageValidationOK(@Valid JmsTodo jmsTodo) throws IOException;

    void receiveMessageValidationNG(@Valid JmsTodo jmsTodo) throws IOException;

    void receiveMessageCatchBusinessErr(JmsTodo jmsTodo);

    void receiveMessageCatchBusinessErrSetQueueA(JmsTodo jmsTodo);

    void receiveMessageCatchBusinessErrSetQueueB(
            JmsTodo jmsTodo) throws IOException;

    boolean receiveMessage_TxSndOK(
            String id) throws IOException, JMSException, InterruptedException;

    boolean receiveMessage_TxSndNG(String id) throws IOException, JMSException;

    void receiveMessage_TxAsyncOK(JmsTodo jmsTodo) throws IOException;

    void receiveMessage_TxAsyncNG(JmsTodo jmsTodo) throws IOException;

    void receiveMessage_receTxBestEffort1PhaseOK(
            JmsTodo jmsTodo) throws IOException;

    void receiveMessage_receTxBestEffort1PhaseNG(
            JmsTodo jmsTodo) throws IOException;

    int getMessageCount(String destinationName,
            String messageSelector) throws JMSException;

    void receiveMessage_sendTxBestEffort1PhaseOK(
            JmsTodo jmsTodo) throws IOException;

    void receiveMessage_sendTxBestEffort1PhaseNG(
            JmsTodo jmsTodo) throws IOException;

    void receiveMessageInputValidationOK(
            @Valid JmsTodo jmsTodo) throws IOException;

    void receiveMessageInputValidationNg(JmsTodo jmsTodo) throws IOException;

    void doNothing(@Valid JmsTodo jmsTodo) throws IOException;

    void receiveMessageInputValidationWithViolationErrMsg(
            JmsTodo jmsTodo) throws IOException;

    void receiveMessageInputValidationJmsTransaction(
            JmsTodo jmsTodo) throws IOException;

    void receiveInputValidationIsolatedTransactionJmsCommitDbRollbackWriteFile(
            JmsTodo jmsTodo) throws IOException;

    void receiveInputValidationIsolatedTransactionJmsAndDbCommitWriteFile(
            JmsTodo jmsTodo) throws IOException;

}
