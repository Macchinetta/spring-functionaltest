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

import jp.co.ntt.fw.spring.functionaltest.domain.service.emal.SessionMailSendingService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("emal")
public class EMAL06Controller {

    @Inject
    SessionMailSendingService sessionMailSendingService;

    @ModelAttribute
    EmailSendingForm setUpForm() {
        EmailSendingForm form = new EmailSendingForm();
        return form;
    }

    @RequestMapping(value = "0601/001", method = RequestMethod.GET)
    public String handle01001(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setTestcase("iso2022jpCharMail");
        return "emal/sendMail";
    }

    @RequestMapping(value = "0601/002", method = RequestMethod.GET)
    public String handle01002(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setTestcase("externalCharMail");
        return "emal/sendMail";
    }

    @RequestMapping(value = "sendmail", method = RequestMethod.POST, params = "testcase=iso2022jpCharMail")
    public String handleIso2022jpCharMail(Model model, EmailSendingForm form) {
        sessionMailSendingService.popBeforeSmtp();
        sessionMailSendingService.sendIso2022jpMail(form.getTo().get(0), form
                .getText());
        return "redirect:/emal/receivemail";
    }

    @RequestMapping(value = "sendmail", method = RequestMethod.POST, params = "testcase=externalCharMail")
    public String handleExternalCharMail(Model model, EmailSendingForm form) {
        sessionMailSendingService.popBeforeSmtp();
        sessionMailSendingService.sendExternalCharMail(form.getTo().get(0), form
                .getText());
        return "redirect:/emal/receivemail";
    }

}
