/*
 * Copyright(c) 2025 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.emal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.mail.Store;

@Service
public class JndiMailSendingServiceImpl implements JndiMailSendingService {

    @Inject
    private JavaMailSender mailServerContainerSender;

    @Inject
    private JavaMailSender mailServerOriginSender;

    @Inject
    private MailReceivingSharedService mailReceivingService;

    @Value("${mail.noauth.from.address}")
    private String fromAddress;

    @Value("${mail.noauth.pop3.host}")
    private String pop3host;

    @Value("${mail.noauth.pop3.port}")
    private int pop3port;

    @Value("${mail.from.user}")
    private String pop3user;

    @Value("${mail.from.password}")
    private String pop3password;

    @Override
    public void sendJndiContainerMailSender(String to, String text,
            Store store) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("JNDI Lookup(Container Prefix)");
        message.setFrom(fromAddress);
        message.setTo(to);
        message.setText(text);
        mailServerContainerSender.send(message);
    }

    @Override
    public void sendJndiOriginMailSender(String to, String text, Store store) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("JNDI Lookup(Origin)");
        message.setFrom(fromAddress);
        message.setTo(to);
        message.setText(text);
        mailServerOriginSender.send(message);
    }

    @Override
    public Store popBeforeSmtp() {
        return mailReceivingService.connect(pop3host, pop3port, pop3user,
                pop3password);
    }
}
