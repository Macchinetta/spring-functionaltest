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
