/*
 * Copyright(c) 2014-2017 NTT Corporation.
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

    void receiveMessageByJmsMessage(javax.jms.TextMessage message) throws IOException, JMSException;

    void receiveMessageAddKey(Message<JmsTodo> jmsTodoMessage) throws IOException;

    void receiveMessageForTopic1(JmsTodo jmsTodo) throws IOException;

    void receiveMessageForTopic2(JmsTodo jmsTodo) throws IOException;

    void receiveMessageStream(InputStream inputStream) throws IOException, JMSException;

    void receiveMessageMany(javax.jms.TextMessage message) throws IOException, JMSException, InterruptedException;

    void receiveMessageByCallback(javax.jms.TextMessage message) throws IOException, JMSException;

    void receiveMessageBySelectorFalse(JmsTodo jmsTodo) throws IOException, JMSException;

    void receiveMessageBySelectorTrue(JmsTodo jmsTodo) throws IOException, JMSException;

    void receiveMessageFromSendTo(JmsTodo jmsTodo) throws IOException;

    void receiveMessageFromJmsResponseB(Message<JmsTodo> jmsTodoMessage) throws IOException;

    void receiveMessageFromJmsResponseC(Message<JmsTodo> jmsTodoMessage) throws IOException;

    void receiveMessageReSendAnotherMessageB(Message<JmsTodo> jmsTodoMessage) throws IOException;

    void receiveMessageConcurrentListenerSingle(JmsTodo jmsTodo) throws IOException;

    void receiveMessageConcurrentListenerMultiple(JmsTodo jmsTodo) throws IOException;

    void receiveMessageWithHeaderOK(JmsTodo jmsTodo, Integer priority,
            Integer deliveryMode, Map<String, Object> headers) throws IOException, JMSException;

    void receiveMessageValidationOK(@Valid JmsTodo jmsTodo) throws IOException;

    void receiveMessageValidationNG(@Valid JmsTodo jmsTodo) throws IOException;

    void receiveMessageCatchBusinessErr(JmsTodo jmsTodo);

    void receiveMessageCatchBusinessErrSetQueueA(JmsTodo jmsTodo);

    void receiveMessageCatchBusinessErrSetQueueB(JmsTodo jmsTodo) throws IOException;

    boolean receiveMessage_TxSndOK(String id) throws IOException, JMSException;

    boolean receiveMessage_TxSndNG(String id) throws IOException, JMSException;

    void receiveMessage_TxAsyncOK(JmsTodo jmsTodo) throws IOException;

    void receiveMessage_TxAsyncNG(JmsTodo jmsTodo) throws IOException;

    void receiveMessage_receTxBestEffort1PhaseOK(JmsTodo jmsTodo) throws IOException;

    void receiveMessage_receTxBestEffort1PhaseNG(JmsTodo jmsTodo) throws IOException;

    int getMessageCount(String destinationName, String messageSelector) throws JMSException;

    void receiveMessage_receTx1PhaseOK(JmsTodo jmsTodo) throws IOException;

    void receiveMessage_receTx1PhaseNG(JmsTodo jmsTodo) throws IOException;

    void receiveMessage_sendTxBestEffort1PhaseOK(JmsTodo jmsTodo) throws IOException;

    void receiveMessage_sendTxBestEffort1PhaseNG(JmsTodo jmsTodo) throws IOException;

    void receiveMessage_sendTx1PhaseOK(JmsTodo jmsTodo) throws IOException;

    void receiveMessage_sendTx1PhaseNG(JmsTodo jmsTodo) throws IOException;

    void receiveMessageInputValidationOK(@Valid JmsTodo jmsTodo) throws IOException;

    void receiveMessageInputValidationNg(JmsTodo jmsTodo) throws IOException;

    void doNothing(@Valid JmsTodo jmsTodo) throws IOException;

    void receiveMessageInputValidationWithViolationErrMsg(JmsTodo jmsTodo) throws IOException;

    void receiveMessageInputValidationJmsTransaction(JmsTodo jmsTodo) throws IOException;

    void receiveInputValidationIsolatedTransactionJmsCommitDbRollbackWriteFile(
            JmsTodo jmsTodo) throws IOException;

    void receiveInputValidationIsolatedTransactionJmsAndDbCommitWriteFile(
            JmsTodo jmsTodo) throws IOException;

    void receiveInputValidationIsolatedChaindTransactionJmsCommitDbRollbackWriteFile(
            JmsTodo jmsTodo) throws IOException;

    void receiveInputValidationIsolatedChaindTransactionJmsAndDbCommit(
            JmsTodo jmsTodo) throws IOException;

    void receiveInputValidationIsolatedChaindTransactionJmsAndDbCommitWriteFile(
            JmsTodo jmsTodo) throws IOException;

}
