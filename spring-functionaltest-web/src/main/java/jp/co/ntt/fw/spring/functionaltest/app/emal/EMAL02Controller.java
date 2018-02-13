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

import java.util.Arrays;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.service.emal.AuthMailSendingService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.emal.NoAuthMailSendingService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.emal.SessionMailSendingService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("emal")
public class EMAL02Controller {

    @Inject
    SessionMailSendingService sessionMailSendingService;

    @Inject
    NoAuthMailSendingService noAuthMailSendingService;

    @Inject
    AuthMailSendingService authMailSendingService;

    @ModelAttribute
    EmailSendingForm setUpForm() {
        EmailSendingForm form = new EmailSendingForm();
        return form;
    }

    @RequestMapping(value = "0201/001", method = RequestMethod.GET)
    public String handle01001(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setTestcase("simpleMessage");
        return "emal/sendMail";
    }

    @RequestMapping(value = "0201/002", method = RequestMethod.GET)
    public String handle01002(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setCc(Arrays.asList(""));
        form.setBcc(Arrays.asList(""));
        form.setTestcase("allProperties");
        return "emal/sendMail";
    }

    @RequestMapping(value = "0201/003", method = RequestMethod.GET)
    public String handle01003(EmailSendingForm form) {
        form.setTo(Arrays.asList("", ""));
        form.setCc(Arrays.asList("", ""));
        form.setBcc(Arrays.asList("", ""));
        form.setTestcase("pluralRecipients");
        return "emal/sendMail";
    }

    @RequestMapping(value = "sendmail", method = RequestMethod.POST, params = "testcase=simpleMessage")
    public String handleSimpleMessage(Model model, EmailSendingForm form,
            RedirectAttributes attrs) {
        sessionMailSendingService.popBeforeSmtp();
        sessionMailSendingService.sendSimpleMessage(form.getTo().get(0), form
                .getText());
        return "redirect:/emal/receivemail";
    }

    @RequestMapping(value = "sendmail", method = RequestMethod.POST, params = "testcase=allProperties")
    public String handleAllProperties(Model model, EmailSendingForm form) {
        noAuthMailSendingService.popBeforeSmtp();
        noAuthMailSendingService.sendSimpleMessage(form.getTo().get(0), form
                .getCc().get(0), form.getBcc().get(0), form.getReplyTo(), form
                        .getText());
        return "redirect:/emal/receivemail";
    }

    @RequestMapping(value = "sendmail", method = RequestMethod.POST, params = "testcase=pluralRecipients")
    public String handlePluralRecipients(Model model, EmailSendingForm form) {
        authMailSendingService.popBeforeSmtp();
        authMailSendingService.sendSimpleMessage(form.getTo().toArray(
                new String[form.getTo().size()]), form.getCc().toArray(
                        new String[form.getCc().size()]), form.getBcc().toArray(
                                new String[form.getBcc().size()]), form
                                        .getText());
        return "redirect:/emal/receivemail";
    }

}
