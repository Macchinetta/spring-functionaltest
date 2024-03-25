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
package jp.co.ntt.fw.spring.functionaltest.app.jmss;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsCacheConSendingService;

@RequestMapping("jsp")
@Controller
public class JMSS_JSP_08SendingController {

    @Inject
    JmsCacheConSendingService jmsCacheConSendingService;

    @ModelAttribute
    JmsSendingForm setUpJmsSendingForm() {
        JmsSendingForm form = new JmsSendingForm();
        return form;
    }

    @GetMapping(value = "0802/001")
    public String handle0802001(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("other_err");
        return "jsp/jmss/jmsSend";
    }

    @PostMapping(value = "sendmessage", params = "testCase=other_err")
    public String sendMessageOtherErr(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        // send message
        jmsCacheConSendingService.sendMessageOtherErr(form.getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }

    @GetMapping(value = "0803/001")
    public String handle0803001(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("catch_business_err");
        return "jsp/jmss/jmsSend";
    }

    @PostMapping(value = "sendmessage", params = "testCase=catch_business_err")
    public String sendMessageCatchBusinessErr(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        // send message
        jmsCacheConSendingService.sendMessageCatchBusinessErr(form
                .getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }

    @GetMapping(value = "0803/002")
    public String handle0803002(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("catch_err_set_queue");
        return "jsp/jmss/jmsSend";
    }

    @PostMapping(value = "sendmessage", params = "testCase=catch_err_set_queue")
    public String sendMessageCatchBusinessErrSetQueue(Model model,
            JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        // send message
        jmsCacheConSendingService.sendMessageCatchBusinessErrSetQueue(form
                .getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }
}
