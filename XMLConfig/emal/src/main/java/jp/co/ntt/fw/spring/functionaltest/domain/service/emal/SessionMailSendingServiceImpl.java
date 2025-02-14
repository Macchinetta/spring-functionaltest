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
import jakarta.inject.Inject;
import jakarta.mail.Store;
import jakarta.mail.internet.MimeMessage;
import jp.co.ntt.fw.spring.functionaltest.domain.model.User;

@Service
public class SessionMailSendingServiceImpl implements SessionMailSendingService {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(SessionMailSendingServiceImpl.class);

    @Inject
    JavaMailSender mailSenderSession;

    @Inject
    SimpleMailMessage templateMessage;

    @Inject
    Configuration freemarkerConfiguration;

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
    public void sendSimpleMessage(String to, String text, Store store) {

        SimpleMailMessage message = new SimpleMailMessage(templateMessage);
        message.setTo(to);
        message.setText(text);
        mailSenderSession.send(message);

    }

    @Override
    public void sendTextMimeMail(final String to, final String text, Store store) {

        mailSenderSession.send(new MimeMessagePreparator() {

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
    public void sendHtmlMimeMail(final String to, final String text, Store store) {

        mailSenderSession.send(new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper =
                        new MimeMessageHelper(mimeMessage, StandardCharsets.UTF_8.name());
                helper.setFrom("\"髙山\" <" + fromAddress + ">");
                helper.setTo(to);
                helper.setSubject("お知らせ①");
                helper.setText(text, true);
            }
        });
    }

    @Override
    public void sendAttachmentMimeMail(final String to, final String text, final String filename,
            final InputStreamSource attachment, Store store) {

        mailSenderSession.send(new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper =
                        new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
                helper.setFrom("\"髙山\" <" + fromAddress + ">");
                helper.setTo(to);
                helper.setSubject("お知らせ①");
                helper.setText(text);
                helper.addAttachment(filename, attachment);
            }
        });

    }

    @Override
    public void sendInlineMimeMail(final String to, final String text, final String cid,
            final InputStreamSource inline, final String contentType, Store store) {

        mailSenderSession.send(new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper =
                        new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
                helper.setFrom("\"髙山\" <" + fromAddress + ">");
                helper.setTo(to);
                helper.setSubject("お知らせ①");
                helper.setText(text, true);
                helper.addInline(cid, inline, contentType);

            }
        });
    }

    @Override
    public void sendTemplatedMail(final String to, final User user, final String templateName,
            Store store) {

        mailSenderSession.send(new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper =
                        new MimeMessageHelper(mimeMessage, StandardCharsets.UTF_8.name());
                helper.setFrom("\"髙山\" <" + fromAddress + ">");
                helper.setTo(to);
                helper.setSubject("お知らせ①");
                Template template = freemarkerConfiguration.getTemplate(templateName + ".ftl");
                String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, user);
                helper.setText(text, true);
            }
        });
    }

    @Override
    public void sendIso2022jpMail(final String to, final String text, Store store) {

        mailSenderSession.send(new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                String iso2022jpBadChars = "―－～∥￠￡￢";
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "ISO-2022-JP");
                helper.setFrom(
                        convertISO2022JPCharacters(iso2022jpBadChars + " <" + fromAddress + ">"));
                helper.setTo(convertISO2022JPCharacters(to));
                helper.setSubject(convertISO2022JPCharacters(iso2022jpBadChars));
                helper.setText(convertISO2022JPCharacters(text));
            }
        });
    }

    @Override
    public void sendExternalCharMail(final String to, final String text, Store store) {

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
    }

    @Override
    public Store popBeforeSmtp() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(pop3host + ":" + pop3port + "[" + pop3user + "," + pop3password + "]");
        }

        return mailReceivingService.connect(pop3host, pop3port, pop3user, pop3password);

    }

    public static String convertISO2022JPCharacters(String targetStr) {

        if (targetStr == null) {
            return null;
        }

        char[] ch = targetStr.toCharArray();

        for (int i = 0; i < ch.length; i++) {
            // @formatter:off
            ch[i] = switch (ch[i]) {
                case '\u2015' -> '\u2014'; // '―'（全角ハイフン） -> '—'（EM ダッシュ）
                case '\uff0d' -> '\u2212'; // '－'（ハイフンマイナス） -> '−'（全角マイナス）
                case '\uff5e' -> '\u301c'; // '～'（全角チルド） -> '〜'（波ダッシュ）
                case '\u2225' -> '\u2016'; // '∥'（平行記号） -> '‖'（双柱）
                case '\uffe0' -> '\u00A2'; // '￠'（全角セント記号） -> '¢'（セント記号）
                case '\uffe1' -> '\u00A3'; // '￡'（全角ポンド記号） -> '£'（ポンド記号）
                case '\uffe2' -> '\u00AC'; // '￢'（全角否定記号） -> '¬'（否定記号）
                default -> ch[i];
            };
            // @formatter:on
        }

        return String.valueOf(ch);
    }

}
