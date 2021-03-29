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

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;

@Controller
@RequestMapping("jmss")
public class JMSS01ReceivingController {

    @Inject
    ReceiveMessageHelper receiveMessageHelper;

    @ModelAttribute
    JmsReceivingForm setJmsReceivingForm() {
        JmsReceivingForm form = new JmsReceivingForm();
        return form;
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=activeMqCon")
    public String receiveMessageActiveMqCon(Model model, JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        JmsTodo jmsTodo = receiveMessageHelper.receiveMessagesForJmsTodo(form
                .getJmsTodoId());

        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", jmsTodo.getJmsTodoId());
        }

        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=jndiCon")
    public String receiveMessageJndiCon(Model model, JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        JmsTodo jmsTodo = receiveMessageHelper.receiveMessagesForJmsTodo(form
                .getJmsTodoId());

        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", jmsTodo.getJmsTodoId());
        }

        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=cacheCon")
    public String receiveMessageCacheCon(Model model, JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        JmsTodo jmsTodo = receiveMessageHelper.receiveMessagesForJmsTodo(form
                .getJmsTodoId());

        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", jmsTodo.getJmsTodoId());
        }

        return "jmss/jmsReceive";
    }

}
