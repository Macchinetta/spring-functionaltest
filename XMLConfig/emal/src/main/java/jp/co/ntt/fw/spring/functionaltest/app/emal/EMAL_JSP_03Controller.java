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
package jp.co.ntt.fw.spring.functionaltest.app.emal;

import java.io.IOException;
import java.util.Arrays;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.inject.Inject;
import jakarta.mail.MessagingException;
import jakarta.mail.Store;
import jp.co.ntt.fw.spring.functionaltest.domain.service.emal.SessionMailSendingService;

@Controller
@RequestMapping("jsp")
public class EMAL_JSP_03Controller {

    @Inject
    SessionMailSendingService sessionMailSendingService;

    @ModelAttribute
    EmailSendingForm setUpForm() {
        EmailSendingForm form = new EmailSendingForm();
        return form;
    }

    @GetMapping(value = "0301/001")
    public String handle01001(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setTestcase("textMimeMessage");
        return "jsp/emal/sendMail";
    }

    @GetMapping(value = "0302/001")
    public String handle02001(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setTestcase("htmlMimeMessage");
        return "jsp/emal/sendMail";
    }

    @GetMapping(value = "0303/001")
    public String handle03001(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setTestcase("attachmentMimeMessage");
        return "jsp/emal/sendMail";
    }

    @GetMapping(value = "0304/001")
    public String handle04001(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setTestcase("inlineMimeMessage");
        return "jsp/emal/sendMail";
    }

    @PostMapping(value = "sendmail", params = "testcase=textMimeMessage")
    public String handleTextMimeMessage(Model model, EmailSendingForm form) {

        try (Store store = sessionMailSendingService.popBeforeSmtp()) {
            sessionMailSendingService.sendTextMimeMail(form.getTo().get(0), form.getText(), store);
        } catch (MessagingException e) {
            // ignore
        }

        return "redirect:/jsp/receivemail";
    }

    @PostMapping(value = "sendmail", params = "testcase=htmlMimeMessage")
    public String handleHtmlMimeMessage(Model model, EmailSendingForm form) {

        try (Store store = sessionMailSendingService.popBeforeSmtp()) {
            sessionMailSendingService.sendHtmlMimeMail(form.getTo().get(0), form.getText(), store);
        } catch (MessagingException e) {
            // ignore
        }

        return "redirect:/jsp/receivemail";
    }

    @PostMapping(value = "sendmail", params = "testcase=attachmentMimeMessage")
    public String handleAttachmentMimeMessage(Model model, EmailSendingForm form)
            throws IOException {

        try (Store store = sessionMailSendingService.popBeforeSmtp()) {
            sessionMailSendingService.sendAttachmentMimeMail(form.getTo().get(0), form.getText(),
                    form.getFilename(), new ByteArrayResource(form.getMultipartFile().getBytes()),
                    store);
        } catch (MessagingException e) {
            // ignore
        }

        return "redirect:/jsp/receivemail";
    }

    @PostMapping(value = "sendmail", params = "testcase=inlineMimeMessage")
    public String handleInlineMimeMessage(Model model, EmailSendingForm form) throws IOException {

        try (Store store = sessionMailSendingService.popBeforeSmtp()) {
            sessionMailSendingService.sendInlineMimeMail(form.getTo().get(0), form.getText(),
                    form.getCid(), new ByteArrayResource(form.getMultipartFile().getBytes()),
                    form.getMultipartFile().getContentType(), store);
        } catch (MessagingException e) {
            // ignore
        }

        return "redirect:/jsp/receivemail";
    }

}
