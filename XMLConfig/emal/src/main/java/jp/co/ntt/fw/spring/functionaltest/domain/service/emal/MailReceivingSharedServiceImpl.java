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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.terasoluna.gfw.common.exception.SystemException;
import jakarta.mail.Address;
import jakarta.mail.BodyPart;
import jakarta.mail.Flags;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Part;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeUtility;
import jp.co.ntt.fw.spring.functionaltest.domain.model.MailMessage;

@Service
public class MailReceivingSharedServiceImpl implements MailReceivingSharedService {
    private static final Logger logger =
            LoggerFactory.getLogger(MailReceivingSharedServiceImpl.class);

    @Override
    public Store connect(String host, int port, String user, String password) {

        Properties props = new Properties();
        props.put("mail.pop3.host", host);
        props.put("mail.pop3.port", port);

        if (logger.isDebugEnabled()) {
            logger.debug(host + ":" + port + "[" + user + "," + password + "]");
        }

        Session session = Session.getInstance(props);
        try {
            Store store = session.getStore("pop3");
            store.connect(user, password);

            return store;

        } catch (MessagingException e) {
            throw new SystemException("e.sf.ea.0001", "connecting via pop3 failed.", e);
        }

    }

    @Override
    public MailMessage receive(String identifier, Store store) {

        MailMessage mail = null;
        Folder folder = null;

        try {
            folder = store.getFolder("INBOX");
            folder.open(Folder.READ_WRITE);
            Message[] messages = folder.getMessages();

            for (int i = messages.length - 1; i >= 0; i--) {
                Message message = messages[i];
                MailMessage processedMessage = processMessage(message);
                if (processedMessage.getBody().contains(identifier)) {
                    // 読込済みメールを削除
                    message.setFlag(Flags.Flag.DELETED, true);
                    mail = processedMessage;
                    break;
                }
            }

        } catch (MessagingException e) {
            throw new SystemException("e.sf.ea.0002", "receiving email via pop3 failed.", e);
        } finally {

            if (folder != null) {
                try {
                    folder.close(true);
                    folder = null;
                } catch (MessagingException e) {
                    // ignore
                }
            }

        }

        return mail;

    }

    @Override
    public MailMessage receive(String identifier, int retryCount, Store store)
            throws InterruptedException {

        for (int i = 0; i < retryCount; i++) {
            MailMessage mail = receive(identifier, store);
            if (mail != null) {
                return mail;
            }

            TimeUnit.SECONDS.sleep(1);
        }

        return null;
    }

    /**
     * 受信メールからMailMessageクラスを作成する、
     * @param message
     * @return
     */
    MailMessage processMessage(Message message) {

        MailMessage mail = new MailMessage();

        try {
            if (message.getFrom() != null && message.getFrom().length > 0) {
                mail.setFrom(((InternetAddress) message.getFrom()[0]).toUnicodeString());
            }

            // 件名
            mail.setSubject(message.getSubject());

            // To
            List<String> to = new ArrayList<>();
            if (message.getRecipients(RecipientType.TO) != null) {
                for (Address address : message.getRecipients(RecipientType.TO)) {
                    to.add(((InternetAddress) address).toUnicodeString());
                }
            }
            mail.setTo(to);

            // Cc
            List<String> cc = new ArrayList<>();
            if (message.getRecipients(RecipientType.CC) != null) {
                for (Address address : message.getRecipients(RecipientType.CC)) {
                    cc.add(((InternetAddress) address).toUnicodeString());
                }
            }
            mail.setCc(cc);

            // 送信日
            if (message.getReplyTo() != null && message.getReplyTo().length > 0) {
                mail.setReplyTo(((InternetAddress) message.getReplyTo()[0]).toUnicodeString());
            }

            mail.setSentDate(message.getSentDate());

            if (message.getContent() instanceof Multipart) {
                // マルチパート時のメール関連情報設定
                processMultipart((Multipart) message.getContent(), mail);
            } else {
                // 本文、ContentType
                mail.setBody(message.getContent().toString());
                mail.setContentType(message.getContentType());
            }
        } catch (MessagingException | IOException e) {
            throw new SystemException("e.sf.ea.000", "processing email message failed.", e);
        }

        return mail;

    }

    /**
     * マルチーパート時、mailオブジェクトにメール関連情報を設定する
     * @param multipart
     * @param mail
     * @throws MessagingException
     * @throws IOException
     */
    void processMultipart(Multipart multipart, MailMessage mail)
            throws MessagingException, IOException {

        int count = multipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart part = multipart.getBodyPart(i);
            if (part.getContent() instanceof Multipart) {
                processMultipart((Multipart) part.getContent(), mail);
            } else {
                // 添付ファイル
                if (Part.ATTACHMENT.equals(part.getDisposition())) {
                    mail.setAttachment(MimeUtility.decodeText(part.getFileName()));
                    // インライン
                } else if (Part.INLINE.equals(part.getDisposition())) {
                    mail.setInline(part.getHeader("Content-ID")[0]);
                } else if (part.isMimeType("text/plain") || part.isMimeType("text/html")) {
                    mail.setBody(part.getContent().toString());
                    mail.setContentType(part.getContentType());
                }
            }
        }

    }

}
