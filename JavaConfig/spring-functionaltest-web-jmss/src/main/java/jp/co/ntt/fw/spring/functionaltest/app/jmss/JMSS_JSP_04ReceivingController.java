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
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsProperty;

@RequestMapping("jsp")
@Controller
public class JMSS_JSP_04ReceivingController {

    @Inject
    ReceiveMessageHelper receivemessageHelper;

    @ModelAttribute
    JmsReceivingForm setJmsReceivingForm() {
        JmsReceivingForm form = new JmsReceivingForm();
        return form;
    }

    @PostMapping(value = "receivemessage", params = "testCase=selector_false")
    public String receiveMessageSelectorFalse(Model model,
            JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        Map<String, String> map = receivemessageHelper.receivedMessageAndForMap(
                form.getJmsTodoId());

        if (map != null) {
            model.addAttribute("uniqueIdentifier", map.get(JmsProperty.UUID_KEY
                    .name()));
        }

        return "jsp/jmss/jmsReceive";
    }

    @PostMapping(value = "receivemessage", params = "testCase=selector_true")
    public String receiveMessageSelectorTrue(Model model, JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        Map<String, String> map = receivemessageHelper.receivedMessageAndForMap(
                form.getJmsTodoId());

        if (map != null) {
            model.addAttribute("uniqueIdentifier", map.get(JmsProperty.UUID_KEY
                    .name()));
        }

        return "jsp/jmss/jmsReceive";
    }

    @PostMapping(value = "receivemessage", params = "testCase=sendto")
    public String receiveMessageSendTo(Model model, JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form
                .getJmsTodoId());

        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", jmsTodo.getJmsTodoId());
        }

        return "jsp/jmss/jmsReceive";
    }

    @PostMapping(value = "receivemessage", params = "testCase=jmsresponseB")
    public String receiveMessageJmsresponseB(Model model, JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        Map<String, String> map = receivemessageHelper.receivedMessageAndForMap(
                form.getJmsTodoId());

        if (map != null) {
            model.addAttribute("uniqueIdentifier", map.get(JmsProperty.UUID_KEY
                    .name()));
            model.addAttribute("receiveQueue", map.get(JmsProperty.RECEIVE_QUEUE
                    .name()));
        }

        return "jsp/jmss/jmsReceive";
    }

    @PostMapping(value = "receivemessage", params = "testCase=jmsresponseC")
    public String receiveMessageJmsresponseC(Model model, JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        Map<String, String> map = receivemessageHelper.receivedMessageAndForMap(
                form.getJmsTodoId());

        if (map != null) {
            model.addAttribute("uniqueIdentifier", map.get(JmsProperty.UUID_KEY
                    .name()));
            model.addAttribute("receiveQueue", map.get(JmsProperty.RECEIVE_QUEUE
                    .name()));
        }

        return "jsp/jmss/jmsReceive";
    }

    @PostMapping(value = "receivemessage", params = "testCase=resend_ano_mss")
    public String receiveMessageResendAnotherMessage(Model model,
            JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        Map<String, String> map = receivemessageHelper.receivedMessageAndForMap(
                form.getJmsTodoId());

        if (map != null) {
            model.addAttribute("uniqueIdentifier", map.get(JmsProperty.UUID_KEY
                    .name()));
            model.addAttribute("receiveQueue", map.get(JmsProperty.RECEIVE_QUEUE
                    .name()));
        }

        return "jsp/jmss/jmsReceive";
    }

    @PostMapping(value = "receivemessage", params = "testCase=concurrent_listener_single")
    public String receiveMessageConcurrentListenerSingle(Model model,
            JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form
                .getJmsTodoId());

        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", form.getJmsTodoId());
        }

        return "jsp/jmss/jmsReceive";
    }

    @PostMapping(value = "receivemessage", params = "testCase=concurrent_listener_multiple")
    public String receiveMessageConcurrentListenerMultiple(Model model,
            JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form
                .getJmsTodoId());

        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", form.getJmsTodoId());
        }

        return "jsp/jmss/jmsReceive";
    }
}
