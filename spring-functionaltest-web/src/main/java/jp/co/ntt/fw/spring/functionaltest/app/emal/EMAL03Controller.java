/*
 * Copyright 2014-2017 NTT Corporation.
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
 *
 */
package jp.co.ntt.fw.spring.functionaltest.app.emal;

import java.io.IOException;
import java.util.Arrays;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.service.emal.SessionMailSendingService;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("emal")
public class EMAL03Controller {

    @Inject
    SessionMailSendingService sessionMailSendingService;

    @ModelAttribute
    EmailSendingForm setUpForm() {
        EmailSendingForm form = new EmailSendingForm();
        return form;
    }

    @RequestMapping(value = "0301/001", method = RequestMethod.GET)
    public String handle01001(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setTestcase("textMimeMessage");
        return "emal/sendMail";
    }

    @RequestMapping(value = "0302/001", method = RequestMethod.GET)
    public String handle02001(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setTestcase("htmlMimeMessage");
        return "emal/sendMail";
    }

    @RequestMapping(value = "0303/001", method = RequestMethod.GET)
    public String handle03001(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setTestcase("attachmentMimeMessage");
        return "emal/sendMail";
    }

    @RequestMapping(value = "0304/001", method = RequestMethod.GET)
    public String handle04001(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setTestcase("inlineMimeMessage");
        return "emal/sendMail";
    }

    @RequestMapping(value = "sendmail", method = RequestMethod.POST, params = "testcase=textMimeMessage")
    public String handleTextMimeMessage(Model model, EmailSendingForm form) {
        sessionMailSendingService.popBeforeSmtp();
        sessionMailSendingService.sendTextMimeMail(form.getTo().get(0), form
                .getText());
        return "redirect:/emal/receivemail";
    }

    @RequestMapping(value = "sendmail", method = RequestMethod.POST, params = "testcase=htmlMimeMessage")
    public String handleHtmlMimeMessage(Model model, EmailSendingForm form) {
        sessionMailSendingService.popBeforeSmtp();
        sessionMailSendingService.sendHtmlMimeMail(form.getTo().get(0), form
                .getText());
        return "redirect:/emal/receivemail";
    }

    @RequestMapping(value = "sendmail", method = RequestMethod.POST, params = "testcase=attachmentMimeMessage")
    public String handleAttachmentMimeMessage(Model model,
            EmailSendingForm form) throws IOException {
        sessionMailSendingService.popBeforeSmtp();
        sessionMailSendingService.sendAttachmentMimeMail(form.getTo().get(0),
                form.getText(), form.getFilename(), new ByteArrayResource(form
                        .getMultipartFile().getBytes()));
        return "redirect:/emal/receivemail";
    }

    @RequestMapping(value = "sendmail", method = RequestMethod.POST, params = "testcase=inlineMimeMessage")
    public String handleInlineMimeMessage(Model model,
            EmailSendingForm form) throws IOException {
        sessionMailSendingService.popBeforeSmtp();
        sessionMailSendingService.sendInlineMimeMail(form.getTo().get(0), form
                .getText(), form.getCid(), new ByteArrayResource(form
                        .getMultipartFile().getBytes()), form.getMultipartFile()
                                .getContentType());
        return "redirect:/emal/receivemail";
    }

}
