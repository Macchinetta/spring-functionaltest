/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.emal;

public interface ConnectionErrorMailSendingService {

    public void sendMessage(String to, String text);

    public void popBeforeSmtp();
}
