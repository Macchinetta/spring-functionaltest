/*
 * Copyright(c) 2024 NTT Corporation.
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

import jakarta.jms.JMSException;

public interface JmsCacheConSendingService {

    void sendMessageByCacheCon(String id) throws IOException;

    void sendMessageByJmsMessage(String id) throws IOException;

    void sendMessageAddKey(String id) throws IOException;

    void sendMessageForTopic(String id) throws IOException;

    void sendMessageMany(String id) throws IOException;

    void sendSyncMessage(String id) throws IOException, InterruptedException;

    void sendMessageBySelectorTrue(String id) throws IOException;

    void sendMessageBySelectorFalse(String id) throws IOException;

    void sendMessageForSendTo(String id) throws IOException;

    void sendMessageForJmsResponseB(String id) throws IOException;

    void sendMessageForJmsResponseC(String id) throws IOException;

    void sendMessageReSendAnotherMessage(
            String id) throws IOException, JMSException;

    void sendMessageConcurrentListenerSingle(String id) throws IOException;

    void sendMessageConcurrentListenerMultiple(String id) throws IOException;

    void sendMessageWithHeadersOK(String id) throws IOException;

    void sendMessageWithHeadersNG(
            String id) throws IOException, InterruptedException;

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

    void sendMessageInputValidationOk(String id);

    void sendMessageInputValidationNg(String id);

    void sendMessageInputValidationNgWithErrMsg(String id);

    void sendMessageInputValidationJmsTransaction(String id);

    void sendMessageInputValidationIsolatedTransactionJmsCommitDbRollback(
            String id);

    void sendMessageInputValidationIsolatedTransactionJmsAndDbCommit(String id);

}
