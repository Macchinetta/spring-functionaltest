/*
 * Copyright(c) 2014 NTT Corporation.
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
import jp.co.ntt.fw.spring.functionaltest.domain.model.User;
import jp.co.ntt.fw.spring.functionaltest.domain.service.emal.AuthErrorMailSendingService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.emal.ConnectionErrorMailSendingService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.emal.SessionMailSendingService;

@Controller
@RequestMapping("emal")
public class EMAL04Controller {

    @Inject
    AuthErrorMailSendingService authErrorMailSendingService;

    @Inject
    SessionMailSendingService sessionMailSendingService;

    @Inject
    ConnectionErrorMailSendingService connectionErrorMailSendingService;

    @ModelAttribute
    EmailSendingForm setUpForm() {
        EmailSendingForm form = new EmailSendingForm();
        return form;
    }

    @GetMapping(value = "0401/001")
    public String handle01001(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setTestcase("authenticationException");
        return "emal/sendMail";
    }

    @GetMapping(value = "0401/002")
    public String handle01002(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setTestcase("parseException");
        return "emal/sendMail";
    }

    @GetMapping(value = "0401/003")
    public String handle01003(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setTestcase("preparationException");
        return "emal/sendMail";
    }

    @GetMapping(value = "0401/004")
    public String handle01004(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setTestcase("sendException");
        return "emal/sendMail";
    }

    @PostMapping(value = "sendmail", params = "testcase=authenticationException")
    public String handleAuthenticationException(Model model,
            EmailSendingForm form) {

        try (Store store = authErrorMailSendingService.popBeforeSmtp()) {
            authErrorMailSendingService.sendMessage(form.getTo().get(0), form
                    .getText(), store);
        } catch (MessagingException e) {
            // ignore
        }

        return "redirect:/emal/receivemail";
    }

    @PostMapping(value = "sendmail", params = "testcase=parseException")
    public String handleParseException(Model model, EmailSendingForm form) {

        try (Store store = sessionMailSendingService.popBeforeSmtp()) {
            sessionMailSendingService.sendSimpleMessage(form.getTo().get(0),
                    form.getText(), store);
        } catch (MessagingException e) {
            // ignore
        }

        return "redirect:/emal/receivemail";
    }

    @PostMapping(value = "sendmail", params = "testcase=preparationException")
    public String handlePreparationException(Model model,
            EmailSendingForm form) {
        User user = new User();
        user.setUsername(form.getText());

        try (Store store = sessionMailSendingService.popBeforeSmtp()) {
            sessionMailSendingService.sendTemplatedMail(form.getTo().get(0),
                    user, form.getTemplateName(), store);
        } catch (MessagingException e) {
            // ignore
        }

        return "redirect:/emal/receivemail";
    }

    @PostMapping(value = "sendmail", params = "testcase=sendException")
    public String handleSendException(Model model, EmailSendingForm form) {

        try (Store store = connectionErrorMailSendingService.popBeforeSmtp()) {
            connectionErrorMailSendingService.sendMessage(form.getTo().get(0),
                    form.getText(), store);
        } catch (MessagingException e) {
            // ignore
        }

        return "redirect:/emal/receivemail";
    }

}
