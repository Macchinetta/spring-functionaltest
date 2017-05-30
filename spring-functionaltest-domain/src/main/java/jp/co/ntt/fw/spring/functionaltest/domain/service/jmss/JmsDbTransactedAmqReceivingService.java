/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.jmss;

import java.io.IOException;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;

public interface JmsDbTransactedAmqReceivingService {

    void receiveInputValidationIsolatedTransactionJmsCommitDbRollback(
            JmsTodo jmsTodo) throws IOException;

    void receiveInputValidationIsolatedTransactionJmsAndDbCommit(JmsTodo jmsTodo) throws IOException;

    void receiveInputValidationIsolatedChaindTransactionJmsCommitDbRollback(
            JmsTodo jmsTodo) throws IOException;

}
