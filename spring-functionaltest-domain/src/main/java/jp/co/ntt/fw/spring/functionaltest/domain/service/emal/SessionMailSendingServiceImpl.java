/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.emal;

import java.nio.charset.StandardCharsets;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;

import jp.co.ntt.fw.spring.functionaltest.domain.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class SessionMailSendingServiceImpl implements
                                           SessionMailSendingService {
    private static final Logger logger = LoggerFactory.getLogger(
            SessionMailSendingServiceImpl.class);

    @Inject
    JavaMailSender mailSenderSession;

    @Inject
    SimpleMailMessage templateMessage;

    @Inject
    Configuration freemarkerConfiguration;

    @Inject
    MailReceivingSharedService mailReceivingService;

    @Value("${mail.from.address}")
    String fromAddress;

    @Value("${mail.pop3.host}")
    String pop3host;

    @Value("${mail.pop3.port}")
    int pop3port;

    @Value("${mail.from.user}")
    String pop3user;

    @Value("${mail.from.password}")
    String pop3password;

    @Override
    public void sendSimpleMessage(String to, String text) {

        try {
            SimpleMailMessage message = new SimpleMailMessage(templateMessage);
            message.setTo(to);
            message.setText(text);
            mailSenderSession.send(message);

        } finally {
            mailReceivingService.close();
        }

    }

    @Override
    public void sendTextMimeMail(final String to, final String text) {

        try {
            mailSenderSession.send(new MimeMessagePreparator() {

                @Override
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, StandardCharsets.UTF_8
                            .name());
                    helper.setFrom("\"髙山\" <" + fromAddress + ">");
                    helper.setTo(to);
                    helper.setSubject("お知らせ①");
                    helper.setText(text);
                }
            });

        } finally {
            mailReceivingService.close();
        }

    }

    @Override
    public void sendHtmlMimeMail(final String to, final String text) {

        try {
            mailSenderSession.send(new MimeMessagePreparator() {

                @Override
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, StandardCharsets.UTF_8
                            .name());
                    helper.setFrom("\"髙山\" <" + fromAddress + ">");
                    helper.setTo(to);
                    helper.setSubject("お知らせ①");
                    helper.setText(text, true);
                }
            });
        } finally {
            mailReceivingService.close();
        }
    }

    @Override
    public void sendAttachmentMimeMail(final String to, final String text,
            final String filename, final InputStreamSource attachment) {

        try {
            mailSenderSession.send(new MimeMessagePreparator() {

                @Override
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8
                            .name());
                    helper.setFrom("\"髙山\" <" + fromAddress + ">");
                    helper.setTo(to);
                    helper.setSubject("お知らせ①");
                    helper.setText(text);
                    helper.addAttachment(filename, attachment);
                }
            });

        } finally {
            mailReceivingService.close();
        }

    }

    @Override
    public void sendInlineMimeMail(final String to, final String text,
            final String cid, final InputStreamSource inline,
            final String contentType) {

        try {
            mailSenderSession.send(new MimeMessagePreparator() {

                @Override
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8
                            .name());
                    helper.setFrom("\"髙山\" <" + fromAddress + ">");
                    helper.setTo(to);
                    helper.setSubject("お知らせ①");
                    helper.setText(text, true);
                    helper.addInline(cid, inline, contentType);

                }
            });
        } finally {
            mailReceivingService.close();
        }
    }

    @Override
    public void sendTemplatedMail(final String to, final User user,
            final String templateName) {

        try {
            mailSenderSession.send(new MimeMessagePreparator() {

                @Override
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, StandardCharsets.UTF_8
                            .name());
                    helper.setFrom("\"髙山\" <" + fromAddress + ">");
                    helper.setTo(to);
                    helper.setSubject("お知らせ①");
                    Template template = freemarkerConfiguration.getTemplate(
                            templateName + ".ftl");
                    String text = FreeMarkerTemplateUtils
                            .processTemplateIntoString(template, user);
                    helper.setText(text, true);
                }
            });
        } finally {
            mailReceivingService.close();
        }
    }

    @Override
    public void sendIso2022jpMail(final String to, final String text) {

        try {

            mailSenderSession.send(new MimeMessagePreparator() {

                @Override
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    String iso2022jpBadChars = "―－～∥￠￡￢";
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "ISO-2022-JP");
                    helper.setFrom(convertISO2022JPCharacters(iso2022jpBadChars
                            + " <" + fromAddress + ">"));
                    helper.setTo(convertISO2022JPCharacters(to));
                    helper.setSubject(convertISO2022JPCharacters(
                            iso2022jpBadChars));
                    helper.setText(convertISO2022JPCharacters(text));
                }
            });
        } finally {
            mailReceivingService.close();
        }
    }

    @Override
    public void sendExternalCharMail(final String to, final String text) {

        try {
            mailSenderSession.send(new MimeMessagePreparator() {

                @Override
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    String externalChars = "㈱ｻﾝﾌﾟﾙ～①";
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "ISO-2022-JP");
                    helper.setFrom(externalChars + " <" + fromAddress + ">");
                    helper.setTo(to);
                    helper.setSubject(externalChars);
                    helper.setText(text);
                }
            });
        } finally {
            mailReceivingService.close();
        }
    }

    @Override
    public void popBeforeSmtp() {
        if (logger.isDebugEnabled()) {
            logger.debug(pop3host + ":" + pop3port + "[" + pop3user + ","
                    + pop3password + "]");
        }
        mailReceivingService.connect(pop3host, pop3port, pop3user,
                pop3password);

    }

    public static String convertISO2022JPCharacters(String targetStr) {

        if (targetStr == null) {
            return null;
        }

        char[] ch = targetStr.toCharArray();

        for (int i = 0; i < ch.length; i++) {
            switch (ch[i]) {

            // '―'（全角ハイフン）
            case '\u2015':
                ch[i] = '\u2014';
                break;
            // '－'（全角マイナス）
            case '\uff0d':
                ch[i] = '\u2212';
                break;
            // '～'（波ダッシュ）
            case '\uff5e':
                ch[i] = '\u301c';
                break;
            // '∥'（双柱）
            case '\u2225':
                ch[i] = '\u2016';
                break;
            // '￠'（セント記号)
            case '\uffe0':
                ch[i] = '\u00A2';
                break;
            // '￡'（ポンド記号）
            case '\uffe1':
                ch[i] = '\u00A3';
                break;
            // '￢'（否定記号）
            case '\uffe2':
                ch[i] = '\u00AC';
                break;
            default:
                break;
            }
        }

        return String.valueOf(ch);
    }

}
