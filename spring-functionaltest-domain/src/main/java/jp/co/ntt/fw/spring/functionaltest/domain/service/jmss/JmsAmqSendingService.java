/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.jmss;

import java.io.IOException;

public interface JmsAmqSendingService {

    void sendMessageForAmq(String id) throws IOException;

    void sendBlobMessage(String id) throws IOException;

}
