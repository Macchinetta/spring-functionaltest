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
import jakarta.jms.JMSException;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsCacheConSendingService;

@RequestMapping("jsp")
@Controller
public class JMSS_JSP_03SendingController {

    @Inject
    JmsCacheConSendingService jmsCacheConSendingService;

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

    @GetMapping(value = "0301/001")
    public String handle0301001(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("jmsMessage");
        return "jsp/jmss/jmsSend";
    }

    @GetMapping(value = "0301/003")
    public String handle0301003(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("addKey");
        return "jsp/jmss/jmsSend";
    }

    @GetMapping(value = "0301/004")
    public String handle0301004(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("topic");
        return "jsp/jmss/jmsSend";
    }

    @GetMapping(value = "0302/001")
    public String handle0302001(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("many");
        return "jsp/jmss/jmsSend";
    }

    @GetMapping(value = "0303/001")
    public String handle0303001(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("withHeadersOK");
        return "jsp/jmss/jmsSend";
    }

    @GetMapping(value = "0303/002")
    public String handle0303002(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("withHeadersNG");
        return "jsp/jmss/jmsSend";
    }

    @PostMapping(value = "sendmessage", params = "testCase=jmsMessage")
    public String sendMessageJmsMsg(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsCacheConSendingService.sendMessageByJmsMessage(form.getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }

    @PostMapping(value = "sendmessage", params = "testCase=addKey")
    public String sendMessageAddKey(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsCacheConSendingService.sendMessageAddKey(form.getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }

    @PostMapping(value = "sendmessage", params = "testCase=topic")
    public String sendMessageForTopic(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsCacheConSendingService.sendMessageForTopic(form.getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }

    @PostMapping(value = "sendmessage", params = "testCase=many")
    public String sendMessageForMany(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsCacheConSendingService.sendMessageMany(form.getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }

    @PostMapping(value = "sendmessage", params = "testCase=withHeadersOK")
    public String sendMessageWithHeadersOK(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        // send message
        jmsCacheConSendingService.sendMessageWithHeadersOK(form.getJmsTodoId());

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }

    @PostMapping(value = "sendmessage", params = "testCase=withHeadersNG")
    public String sendMessageWithHeadersNG(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        // send message
        jmsCacheConSendingService.sendMessageWithHeadersNG(form.getJmsTodoId());

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }

}
