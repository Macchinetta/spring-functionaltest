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

import java.util.Arrays;
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
public class EMAL_JSP_06Controller {

    @Inject
    SessionMailSendingService sessionMailSendingService;

    @ModelAttribute
    EmailSendingForm setUpForm() {
        EmailSendingForm form = new EmailSendingForm();
        return form;
    }

    @GetMapping(value = "0601/001")
    public String handle01001(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setTestcase("iso2022jpCharMail");
        return "jsp/emal/sendMail";
    }

    @GetMapping(value = "0601/002")
    public String handle01002(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setTestcase("externalCharMail");
        return "jsp/emal/sendMail";
    }

    @PostMapping(value = "sendmail", params = "testcase=iso2022jpCharMail")
    public String handleIso2022jpCharMail(Model model, EmailSendingForm form) {

        try (Store store = sessionMailSendingService.popBeforeSmtp()) {
            sessionMailSendingService.sendIso2022jpMail(form.getTo().get(0), form.getText(), store);
        } catch (MessagingException e) {
            // ignore
        }

        return "redirect:/jsp/receivemail";
    }

    @PostMapping(value = "sendmail", params = "testcase=externalCharMail")
    public String handleExternalCharMail(Model model, EmailSendingForm form) {

        try (Store store = sessionMailSendingService.popBeforeSmtp()) {
            sessionMailSendingService.sendExternalCharMail(form.getTo().get(0), form.getText(),
                    store);
        } catch (MessagingException e) {
            // ignore
        }

        return "redirect:/jsp/receivemail";
    }

}
