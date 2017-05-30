/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.emal;

public interface AuthMailSendingService {

    public void sendSimpleMessage(String[] to, String[] cc, String[] bcc,
            String text);

    public void popBeforeSmtp();
}
