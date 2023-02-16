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
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsCacheConSendingService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsJndiConSendingService;

@Controller
@RequestMapping("jmss")
public class JMSS01SendingController {

    // ActiveMQ Connection
    @Inject
    JmsAmqSendingService jmsAmqSendingService;

    @Inject
    JmsJndiConSendingService jmsJndiConSendingService;

    @Inject
    JmsCacheConSendingService jmsCacheConSendingService;

    @Value("${app.jms.temporaryDirectory}")
    String temporaryDirectory;

    @Value("${app.jms.receiveWaitTime}")
    int receiveWaitTime;

    @Value("${app.jms.receiveCheckInterval}")
    int receiveCheckInterval;

    @ModelAttribute
    JmsSendingForm setUpJmsSendingForm() {
        JmsSendingForm form = new JmsSendingForm();
        return form;
    }

    @GetMapping(value = "0101/001")
    public String handle0101001(JmsSendingForm form) {

        form.setTestCase("activeMqCon");
        return "jmss/jmsSend";
    }

    @GetMapping(value = "0102/001")
    public String handle0102001(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("jndiCon");
        return "jmss/jmsSend";
    }

    @GetMapping(value = "0103/001")
    public String handle0103001(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("cacheCon");
        return "jmss/jmsSend";
    }

    @PostMapping(value = "sendmessage", params = "testCase=activeMqCon")
    public String sendMessageAmqCon(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsAmqSendingService.sendMessageForAmq(form.getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

    @PostMapping(value = "sendmessage", params = "testCase=jndiCon")
    public String sendMessageJndiCon(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsJndiConSendingService.sendMessageByJndiCon(form.getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

    @PostMapping(value = "sendmessage", params = "testCase=cacheCon")
    public String sendMessageCacheCon(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsCacheConSendingService.sendMessageByCacheCon(form.getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

}
