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
package jp.co.ntt.fw.spring.functionaltest.app.jmss;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsAmqSendingService;

@Controller
@RequestMapping("jmss")
public class JMSS09SendingController {

    @Inject
    JmsAmqSendingService jmsAmqSendingService;

    @Value("${app.jms.temporaryDirectory}")
    String temporaryDirectory;

    @Value("${app.jms.receiveWaitTime}")
    int receiveWaitTime;

    int receiveCheckInterval = 200;

    @ModelAttribute
    JmsSendingForm setUpJmsSendingForm() {
        JmsSendingForm form = new JmsSendingForm();
        return form;
    }

    @ModelAttribute
    JmsReceivingForm setJmsReceivingForm() {
        JmsReceivingForm form = new JmsReceivingForm();
        return form;
    }

    @GetMapping(value = "0901/001")
    public String handle0901001(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("blob");
        return "jmss/jmsSend";
    }

    @PostMapping(value = "sendmessage", params = "testCase=blob")
    public String sendMessageJmsMsg(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        // send message
        jmsAmqSendingService.sendBlobMessage(form.getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

}
