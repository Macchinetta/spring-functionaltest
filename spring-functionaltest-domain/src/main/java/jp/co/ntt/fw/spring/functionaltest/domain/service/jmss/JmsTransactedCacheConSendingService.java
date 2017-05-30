/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.jmss;

public interface JmsTransactedCacheConSendingService {

    void sendMessage_TxSndOK(String id);

    void sendMessage_TxSndNG(String id);

    void sendMessage_sendTx1PhaseOK(String id);

    void sendMessage_sendTx1PhaseNG(String id);

}
