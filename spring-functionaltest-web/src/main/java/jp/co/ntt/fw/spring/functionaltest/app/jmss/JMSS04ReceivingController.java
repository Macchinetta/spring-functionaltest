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
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsSharedService;

@Controller
@RequestMapping("jmss")
public class JMSS04ReceivingController {

    @Inject
    ReceiveMessageHelper receivemessageHelper;

    @ModelAttribute
    JmsReceivingForm setJmsReceivingForm() {
        JmsReceivingForm form = new JmsReceivingForm();
        return form;
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=selector_false")
    public String receiveMessageSelectorFalse(Model model,
            JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        Map<String, String> map = receivemessageHelper.receivedMessageAndForMap(
                form.getJmsTodoId());

        if (map != null) {
            model.addAttribute("uniqueIdentifier", map.get(
                    JmsSharedService.UUID_KEY));
        }

        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=selector_true")
    public String receiveMessageSelectorTrue(Model model, JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        Map<String, String> map = receivemessageHelper.receivedMessageAndForMap(
                form.getJmsTodoId());

        if (map != null) {
            model.addAttribute("uniqueIdentifier", map.get(
                    JmsSharedService.UUID_KEY));
        }

        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=sendto")
    public String receiveMessageSendTo(Model model, JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form
                .getJmsTodoId());

        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", jmsTodo.getJmsTodoId());
        }

        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=jmsresponseB")
    public String receiveMessageJmsresponseB(Model model, JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        Map<String, String> map = receivemessageHelper.receivedMessageAndForMap(
                form.getJmsTodoId());

        if (map != null) {
            model.addAttribute("uniqueIdentifier", map.get(
                    JmsSharedService.UUID_KEY));
            model.addAttribute("receiveQueue", map.get(
                    JmsSharedService.RECEIVE_QUEUE));
        }

        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=jmsresponseC")
    public String receiveMessageJmsresponseC(Model model, JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        Map<String, String> map = receivemessageHelper.receivedMessageAndForMap(
                form.getJmsTodoId());

        if (map != null) {
            model.addAttribute("uniqueIdentifier", map.get(
                    JmsSharedService.UUID_KEY));
            model.addAttribute("receiveQueue", map.get(
                    JmsSharedService.RECEIVE_QUEUE));
        }

        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=resend_ano_mss")
    public String receiveMessageResendAnotherMessage(Model model,
            JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        Map<String, String> map = receivemessageHelper.receivedMessageAndForMap(
                form.getJmsTodoId());

        if (map != null) {
            model.addAttribute("uniqueIdentifier", map.get(
                    JmsSharedService.UUID_KEY));
            model.addAttribute("receiveQueue", map.get(
                    JmsSharedService.RECEIVE_QUEUE));
        }

        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=concurrent_listener_single")
    public String receiveMessageConcurrentListenerSingle(Model model,
            JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form
                .getJmsTodoId());

        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", form.getJmsTodoId());
        }

        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=concurrent_listener_multiple")
    public String receiveMessageConcurrentListenerMultiple(Model model,
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
