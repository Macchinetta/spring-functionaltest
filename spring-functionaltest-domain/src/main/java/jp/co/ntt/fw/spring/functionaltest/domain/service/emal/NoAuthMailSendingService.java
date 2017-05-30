/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.emal;

public interface NoAuthMailSendingService {

    public void sendSimpleMessage(String to, String cc, String bcc,
            String replyTo, String text);

    public void popBeforeSmtp();
}
