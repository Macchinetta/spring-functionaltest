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

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.Store;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "0401/001", method = RequestMethod.GET)
    public String handle01001(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setTestcase("authenticationException");
        return "emal/sendMail";
    }

    @RequestMapping(value = "0401/002", method = RequestMethod.GET)
    public String handle01002(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setTestcase("parseException");
        return "emal/sendMail";
    }

    @RequestMapping(value = "0401/003", method = RequestMethod.GET)
    public String handle01003(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setTestcase("preparationException");
        return "emal/sendMail";
    }

    @RequestMapping(value = "0401/004", method = RequestMethod.GET)
    public String handle01004(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setTestcase("sendException");
        return "emal/sendMail";
    }

    @RequestMapping(value = "sendmail", method = RequestMethod.POST, params = "testcase=authenticationException")
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

    @RequestMapping(value = "sendmail", method = RequestMethod.POST, params = "testcase=parseException")
    public String handleParseException(Model model, EmailSendingForm form) {

        try (Store store = sessionMailSendingService.popBeforeSmtp()) {
            sessionMailSendingService.sendSimpleMessage(form.getTo().get(0),
                    form.getText(), store);
        } catch (MessagingException e) {
            // ignore
        }

        return "redirect:/emal/receivemail";
    }

    @RequestMapping(value = "sendmail", method = RequestMethod.POST, params = "testcase=preparationException")
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

    @RequestMapping(value = "sendmail", method = RequestMethod.POST, params = "testcase=sendException")
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
