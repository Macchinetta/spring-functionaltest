/*
 * Copyright(c) 2025 NTT Corporation.
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.Store;
import jp.co.ntt.fw.spring.functionaltest.domain.service.emal.JndiMailSendingService;

@Controller
@RequestMapping("emal")
public class EMAL07Controller {

    @Inject
    private JndiMailSendingService jndiMailSendingService;

    // Test page (container management)
    @GetMapping(value = "0701/001")
    public String handle01001(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setTestcase("jndiContainerMail");
        return "emal/sendMail";
    }

    // Send email using container-managed email session via JNDI
    @PostMapping(value = "sendmail", params = "testcase=jndiContainerMail")
    public String handJndiContainerMail(Model model, EmailSendingForm form,
            RedirectAttributes attrs) {

        try (Store store = jndiMailSendingService.popBeforeSmtp()) {
            jndiMailSendingService.sendJndiContainerMailSender(form.getTo().get(
                    0), form.getText(), store);
        } catch (MessagingException e) {
            // ignore
        }
        return "redirect:/emal/receivemail";
    }

    // Test page (original email session)
    @GetMapping(value = "0701/002")
    public String handle01002(EmailSendingForm form) {
        form.setTo(Arrays.asList(""));
        form.setTestcase("jndiOriginMail");
        return "emal/sendMail";
    }

    // Send email using original email session via JNDI
    @PostMapping(value = "sendmail", params = "testcase=jndiOriginMail")
    public String handJndiOriginMail(Model model, EmailSendingForm form,
            RedirectAttributes attrs) {

        try (Store store = jndiMailSendingService.popBeforeSmtp()) {
            jndiMailSendingService.sendJndiOriginMailSender(form.getTo().get(0),
                    form.getText(), store);
        } catch (MessagingException e) {
            // ignore
        }
        return "redirect:/emal/receivemail";
    }
}
