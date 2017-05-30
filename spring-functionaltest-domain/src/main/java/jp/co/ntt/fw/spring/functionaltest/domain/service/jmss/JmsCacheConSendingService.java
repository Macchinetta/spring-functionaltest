/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.jmss;

import java.io.IOException;

import javax.jms.JMSException;

public interface JmsCacheConSendingService {

    void sendMessageByCacheCon(String id) throws IOException;

    void sendMessageByJmsMessage(String id) throws IOException;

    void sendMessageAddKey(String id) throws IOException;

    void sendMessageForTopic(String id) throws IOException;

    void sendMessageMany(String id) throws IOException;

    void sendSyncMessage(String id) throws IOException;

    void sendMessageBySelectorTrue(String id) throws IOException;

    void sendMessageBySelectorFalse(String id) throws IOException;

    void sendMessageForSendTo(String id) throws IOException;

    void sendMessageForJmsResponseB(String id) throws IOException;

    void sendMessageForJmsResponseC(String id) throws IOException;

    void sendMessageReSendAnotherMessage(String id) throws IOException, JMSException;

    void sendMessageConcurrentListenerSingle(String id) throws IOException;

    void sendMessageConcurrentListenerMultiple(String id) throws IOException;

    void sendMessageWithHeadersOK(String id) throws IOException;

    void sendMessageWithHeadersNG(String id) throws IOException;

    void sendMessageValidationOK(String id) throws IOException;

    void sendMessageValidationNG(String id) throws IOException;

    void sendMessageOtherErr(String id) throws IOException;

    void sendMessageCatchBusinessErr(String id) throws IOException;

    void sendMessageCatchBusinessErrSetQueue(String id) throws IOException;

    void sendMessage_TxRcvOK(String id);

    void sendMessage_TxRcvNG(String id);

    void sendMessage_TxAsyncOK(String id) throws IOException;

    void sendMessage_TxAsyncNG(String id) throws IOException;

    void sendMessage_receTxBestEffort1PhaseOK(String id) throws IOException;

    void sendMessage_receTxBestEffort1PhaseNG(String id) throws IOException;

    void sendMessage_receTx1PhaseOK(String id) throws IOException;

    void sendMessage_receTx1PhaseNG(String id) throws IOException;

    void sendMessageInputValidationOk(String id);

    void sendMessageInputValidationNg(String id);

    void sendMessageInputValidationNgWithErrMsg(String id);

    void sendMessageInputValidationJmsTransaction(String id);

    void sendMessageInputValidationIsolatedTransactionJmsCommitDbRollback(
            String id);

    void sendMessageInputValidationIsolatedTransactionJmsAndDbCommit(String id);

    void sendMessageInputValidationIsolatedChaindTransactionJmsCommitDbRollback(
            String id);

    void sendMessageInputValidationIsolatedChaindTransactionJmsAndDbCommit(
            String id);

}
