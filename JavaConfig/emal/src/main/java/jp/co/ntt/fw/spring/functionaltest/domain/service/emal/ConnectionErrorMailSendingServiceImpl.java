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

import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import jakarta.inject.Inject;
import jakarta.mail.Store;
import jakarta.mail.internet.MimeMessage;

@Service
public class ConnectionErrorMailSendingServiceImpl implements ConnectionErrorMailSendingService {

    @Inject
    JavaMailSender mailSenderConnError;

    @Inject
    MailReceivingSharedService mailReceivingService;

    @Value("${mail.noauth.from.address}")
    String fromAddress;

    @Value("${mail.noauth.pop3.host}")
    String pop3host;

    @Value("${mail.noauth.pop3.port}")
    int pop3port;

    @Value("${mail.from.user}")
    String pop3user;

    @Value("${mail.from.password}")
    String pop3password;

    @Override
    public void sendMessage(final String to, final String text, Store store) {

        mailSenderConnError.send(new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper =
                        new MimeMessageHelper(mimeMessage, StandardCharsets.UTF_8.name());
                helper.setFrom("\"髙山\" <" + fromAddress + ">");
                helper.setTo(to);
                helper.setSubject("お知らせ①");
                helper.setText(text);
            }
        });
    }

    @Override
    public Store popBeforeSmtp() {

        return mailReceivingService.connect(pop3host, pop3port, pop3user, pop3password);

    }

}
