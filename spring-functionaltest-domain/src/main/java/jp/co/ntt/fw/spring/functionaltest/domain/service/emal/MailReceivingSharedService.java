/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.emal;

import jp.co.ntt.fw.spring.functionaltest.domain.model.MailMessage;

public interface MailReceivingSharedService {

    public void connect(String host, int port, String user, String password);

    public MailMessage receive(String identifier);

    public MailMessage receive(String identifier, int retryCount);

    public void close();
}
