/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.app.jmss;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsSharedService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=jmsMessage")
    public String receiveMessageJmsMessage(Model model, JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        Map<String, String> map = receivemessageHelper.receivedMessageAndForMap(
                form.getJmsTodoId());

        if (map != null) {
            model.addAttribute("uniqueIdentifier", map.get(
                    JmsSharedService.UUID_KEY));
        }

        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=addKey")
    public String receiveMessageAddKey(Model model, JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        Map<String, String> map = receivemessageHelper.receivedMessageAndForMap(
                form.getJmsTodoId());

        if (map != null) {
            model.addAttribute("uniqueIdentifier", map.get(
                    JmsSharedService.UUID_KEY));
        }

        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=topic")
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

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=many")
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

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=callback")
    public String receiveMessageCallback(Model model, JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        Map<String, String> map = receivemessageHelper.receivedMessageAndForMap(
                form.getJmsTodoId());

        if (map != null) {
            model.addAttribute("uniqueIdentifier", map.get(
                    JmsSharedService.UUID_KEY));
            model.addAttribute("priority", map.get(JmsSharedService.PRIORITY));
            model.addAttribute("deliveryMode", map.get(
                    JmsSharedService.DELIVERY_MODE));
        }

        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=withHeadersOK")
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

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=withHeadersNG")
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
