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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.inject.Inject;
import jakarta.mail.MessagingException;
import jakarta.mail.Store;
import jp.co.ntt.fw.spring.functionaltest.domain.model.MailMessage;
import jp.co.ntt.fw.spring.functionaltest.domain.service.emal.MailReceivingSharedService;

@Controller
@RequestMapping("jsp")
public class ReceiveMail_JSP_Controller {

    @Inject
    MailReceivingSharedService mailReceivingSharedService;

    @ModelAttribute
    EmailReceivingForm setUpForm() {
        EmailReceivingForm form = new EmailReceivingForm();
        return form;
    }

    @GetMapping(value = "receivemail")
    public String handle() {
        return "jsp/emal/receiveMail";
    }

    @PostMapping(value = "receivemail")
    public String handleReceiveMail(Model model, EmailReceivingForm form)
            throws InterruptedException {

        try (Store store = mailReceivingSharedService.connect(form.getHost(), form.getPort(),
                form.getUser(), form.getPassword())) {

            MailMessage mail = mailReceivingSharedService.receive(form.getIdentifier(), 5, store);
            model.addAttribute("mail", mail);
        } catch (MessagingException e) {
            // ignore
        }

        return "jsp/emal/receiveMail";
    }

}
