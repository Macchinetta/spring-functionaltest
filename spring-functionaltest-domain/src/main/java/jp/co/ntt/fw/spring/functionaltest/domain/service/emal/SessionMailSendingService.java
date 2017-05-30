/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.emal;

import jp.co.ntt.fw.spring.functionaltest.domain.model.User;

import org.springframework.core.io.InputStreamSource;

public interface SessionMailSendingService {

    public void sendSimpleMessage(String to, String text);

    public void sendTextMimeMail(String to, String text);

    public void sendHtmlMimeMail(String to, String text);

    public void sendAttachmentMimeMail(String to, String text, String filename,
            InputStreamSource attachment);

    public void sendInlineMimeMail(String to, String text, String cid,
            InputStreamSource inline, String contentType);

    public void sendTemplatedMail(String to, User user, String templateName);

    public void sendIso2022jpMail(String to, String text);

    public void sendExternalCharMail(String to, String text);

    public void popBeforeSmtp();
}
