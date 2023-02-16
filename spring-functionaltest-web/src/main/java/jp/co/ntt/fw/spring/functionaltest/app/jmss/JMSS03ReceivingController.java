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
import java.util.List;
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

@Controller
@RequestMapping("jmss")
public class JMSS03ReceivingController {

    @Inject
    ReceiveMessageHelper receivemessageHelper;

    @ModelAttribute
    JmsReceivingForm setJmsReceivingForm() {
        JmsReceivingForm form = new JmsReceivingForm();
        return form;
    }

    @PostMapping(value = "receivemessage", params = "testCase=jmsMessage")
    public String receiveMessageJmsMessage(Model model, JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        Map<String, String> map = receivemessageHelper.receivedMessageAndForMap(
                form.getJmsTodoId());

        if (map != null) {
            model.addAttribute("uniqueIdentifier", map.get(JmsProperty.UUID_KEY
                    .name()));
        }

        return "jmss/jmsReceive";
    }

    @PostMapping(value = "receivemessage", params = "testCase=addKey")
    public String receiveMessageAddKey(Model model, JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        Map<String, String> map = receivemessageHelper.receivedMessageAndForMap(
                form.getJmsTodoId());

        if (map != null) {
            model.addAttribute("uniqueIdentifier", map.get(JmsProperty.UUID_KEY
                    .name()));
        }

        return "jmss/jmsReceive";
    }

    @PostMapping(value = "receivemessage", params = "testCase=topic")
    public String receiveMessageTopic(Model model, JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        JmsTodo jmsTodo1 = receivemessageHelper.receiveMessagesForJmsTodo(form
                .getJmsTodoId() + "topic1");
        JmsTodo jmsTodo2 = receivemessageHelper.receiveMessagesForJmsTodo(form
                .getJmsTodoId() + "topic2");

        if (jmsTodo1 != null && jmsTodo2 != null) {
            model.addAttribute("uniqueIdentifier",
                    "All of the subscriber has been received.");
        }

        return "jmss/jmsReceive";
    }

    @PostMapping(value = "receivemessage", params = "testCase=many")
    public String receiveMessageMany(Model model, JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        List<String> list = receivemessageHelper.receivedMessageAndForList(form
                .getJmsTodoId());

        if (list != null) {
            model.addAttribute("uniqueIdentifier", list.get(0));
            model.addAttribute("receiveCount", list.size());
        }

        return "jmss/jmsReceive";
    }

    @PostMapping(value = "receivemessage", params = "testCase=callback")
    public String receiveMessageCallback(Model model, JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        Map<String, String> map = receivemessageHelper.receivedMessageAndForMap(
                form.getJmsTodoId());

        if (map != null) {
            model.addAttribute("uniqueIdentifier", map.get(JmsProperty.UUID_KEY
                    .name()));
            model.addAttribute("priority", map.get(JmsProperty.PRIORITY
                    .name()));
            model.addAttribute("deliveryMode", map.get(JmsProperty.DELIVERY_MODE
                    .name()));
        }

        return "jmss/jmsReceive";
    }

    @PostMapping(value = "receivemessage", params = "testCase=withHeadersOK")
    public String receiveMessageWithHeadersOK(Model model,
            JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form
                .getJmsTodoId());

        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", form.getJmsTodoId());
        }

        return "jmss/jmsReceive";
    }

    @PostMapping(value = "receivemessage", params = "testCase=withHeadersNG")
    public String receiveMessageWithWithHeadersNG(Model model,
            JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form
                .getJmsTodoId());

        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", form.getJmsTodoId());
        }

        return "jmss/jmsReceive";
    }

}
