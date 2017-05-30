/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.jmss;

import java.io.IOException;

public interface JmsJndiConSendingService {

    void sendMessageByJndiCon(String id) throws IOException;

}
