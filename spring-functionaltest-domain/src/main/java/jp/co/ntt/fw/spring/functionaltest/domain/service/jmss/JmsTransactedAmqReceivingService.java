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

public interface JmsTransactedAmqReceivingService {

    void receiveMessage_TxRcvOK(String id) throws IOException;

    void receiveMessage_TxRcvNG(String id);

}
