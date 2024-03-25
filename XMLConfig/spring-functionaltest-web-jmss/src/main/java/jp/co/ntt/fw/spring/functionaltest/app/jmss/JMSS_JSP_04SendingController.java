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
public class JMSS_JSP_04SendingController {

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

    @GetMapping(value = "0401/002")
    public String handle0401002(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("selector_false");
        return "jsp/jmss/jmsSend";
    }

    @GetMapping(value = "0401/003")
    public String handle0401003(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("selector_true");
        return "jsp/jmss/jmsSend";
    }

    @GetMapping(value = "0402/001")
    public String handle0402001(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("sendto");
        return "jsp/jmss/jmsSend";
    }

    @GetMapping(value = "0403/001")
    public String handle0403001(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("jmsresponseB");
        return "jsp/jmss/jmsSend";
    }

    @GetMapping(value = "0403/002")
    public String handle0403002(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("jmsresponseC");
        return "jsp/jmss/jmsSend";
    }

    @GetMapping(value = "0403/003")
    public String handle0403003(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("resend_ano_mss");
        return "jsp/jmss/jmsSend";
    }

    @GetMapping(value = "0404/001")
    public String handle0404001(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("concurrent_listener_single");
        return "jsp/jmss/jmsSend";
    }

    @GetMapping(value = "0404/002")
    public String handle0404002(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("concurrent_listener_multiple");
        return "jsp/jmss/jmsSend";
    }

    @PostMapping(value = "sendmessage", params = "testCase=selector_false")
    public String sendMessageJmsMsg(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsCacheConSendingService.sendMessageBySelectorFalse(form
                .getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }

    @PostMapping(value = "sendmessage", params = "testCase=selector_true")
    public String sendMessageAddKey(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsCacheConSendingService.sendMessageBySelectorTrue(form
                .getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }

    @PostMapping(value = "sendmessage", params = "testCase=sendto")
    public String sendMessageSendTo(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsCacheConSendingService.sendMessageForSendTo(form.getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }

    @PostMapping(value = "sendmessage", params = "testCase=jmsresponseB")
    public String sendMessageJmsresponseB(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsCacheConSendingService.sendMessageForJmsResponseB(form
                .getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }

    @PostMapping(value = "sendmessage", params = "testCase=jmsresponseC")
    public String sendMessageJmsresponseC(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsCacheConSendingService.sendMessageForJmsResponseC(form
                .getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }

    @PostMapping(value = "sendmessage", params = "testCase=resend_ano_mss")
    public String sendMessageReSendAnotherMessage(Model model,
            JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        jmsCacheConSendingService.sendMessageReSendAnotherMessage(form
                .getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }

    @PostMapping(value = "sendmessage", params = "testCase=concurrent_listener_single")
    public String sendMessageJmsConcurrentListenerSingle(Model model,
            JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsCacheConSendingService.sendMessageConcurrentListenerSingle(form
                .getJmsTodoId());
        jmsCacheConSendingService.sendMessageConcurrentListenerSingle(form
                .getJmsTodoId());
        jmsCacheConSendingService.sendMessageConcurrentListenerSingle(form
                .getJmsTodoId());
        jmsCacheConSendingService.sendMessageConcurrentListenerSingle(form
                .getJmsTodoId());
        jmsCacheConSendingService.sendMessageConcurrentListenerSingle(form
                .getJmsTodoId());

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }

    @PostMapping(value = "sendmessage", params = "testCase=concurrent_listener_multiple")
    public String sendMessageJmsConcurrentListenerMultiple(Model model,
            JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsCacheConSendingService.sendMessageConcurrentListenerMultiple(form
                .getJmsTodoId());
        jmsCacheConSendingService.sendMessageConcurrentListenerMultiple(form
                .getJmsTodoId());
        jmsCacheConSendingService.sendMessageConcurrentListenerMultiple(form
                .getJmsTodoId());
        jmsCacheConSendingService.sendMessageConcurrentListenerMultiple(form
                .getJmsTodoId());
        jmsCacheConSendingService.sendMessageConcurrentListenerMultiple(form
                .getJmsTodoId());

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }
}
