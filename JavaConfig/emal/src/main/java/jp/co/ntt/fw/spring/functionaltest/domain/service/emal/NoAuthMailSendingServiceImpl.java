/*
 * Copyright(c) 2024 NTT Corporation.
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import jakarta.inject.Inject;
import jakarta.mail.Store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NoAuthMailSendingServiceImpl implements NoAuthMailSendingService {

    @Inject
    JavaMailSender mailSenderNoAuth;

    @Inject
    SimpleMailMessage templateMessage;

    @Inject
    MailReceivingSharedService mailReceivingService;

    @Value("${mail.noauth.pop3.host}")
    String pop3host;

    @Value("${mail.noauth.pop3.port}")
    int pop3port;

    @Value("${mail.from.user}")
    String pop3user;

    @Value("${mail.from.password}")
    String pop3password;

    @Override
    public void sendSimpleMessage(String to, String cc, String bcc, String replyTo, String text,
            Store store) {

        SimpleMailMessage message = new SimpleMailMessage(templateMessage);
        message.setTo(to);
        message.setCc(cc);
        message.setBcc(bcc);
        message.setReplyTo(replyTo);
        LocalDateTime sentDateTime = LocalDateTime.now().with(LocalDate.of(2015, 7, 6));
        message.setSentDate(Date.from(sentDateTime.atZone(ZoneId.systemDefault()).toInstant()));
        message.setText(text);
        mailSenderNoAuth.send(message);

    }

    @Override
    public void sendSimpleMessages(String[] to, String[] cc, String[] bcc, String text,
            Store store) {

        SimpleMailMessage message = new SimpleMailMessage(templateMessage);
        message.setTo(to);
        message.setCc(cc);
        message.setBcc(bcc);
        message.setText(text);
        mailSenderNoAuth.send(message);

    }

    @Override
    public Store popBeforeSmtp() {

        return mailReceivingService.connect(pop3host, pop3port, pop3user, pop3password);

    }

}
