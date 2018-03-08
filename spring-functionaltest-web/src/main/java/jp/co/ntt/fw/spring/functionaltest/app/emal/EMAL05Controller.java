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
package jp.co.ntt.fw.spring.functionaltest.app.emal;

import java.util.Arrays;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.User;
import jp.co.ntt.fw.spring.functionaltest.domain.service.emal.SessionMailSendingService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("emal")
public class EMAL05Controller {

    @Inject
    SessionMailSendingService sessionMailSendingService;

    @ModelAttribute
    EmailSendingForm setUpForm() {
        EmailSendingForm form = new EmailSendingForm();
        return form;
    }

    @RequestMapping(value = "0501/001", method = RequestMethod.GET)
    public String handle01001(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setTestcase("templatedMessage");
        return "emal/sendMail";
    }

    @RequestMapping(value = "sendmail", method = RequestMethod.POST, params = "testcase=templatedMessage")
    public String handleTemplatedMessage(Model model, EmailSendingForm form) {
        sessionMailSendingService.popBeforeSmtp();
        User user = new User();
        user.setUsername(form.getText());
        sessionMailSendingService.sendTemplatedMail(form.getTo().get(0), user,
                form.getTemplateName());
        return "redirect:/emal/receivemail";
    }

}
