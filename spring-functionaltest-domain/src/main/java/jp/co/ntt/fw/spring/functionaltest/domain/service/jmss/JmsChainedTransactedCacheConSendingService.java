/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.jmss;

public interface JmsChainedTransactedCacheConSendingService {

    void sendMessage_sendTxBestEffort1PhaseOK(String id);

    void sendMessage_sendTxBestEffort1PhaseNG(String id);

}
