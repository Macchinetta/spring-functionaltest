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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.inject.Inject;
import jakarta.jms.JMSException;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;

@RequestMapping("jsp")
@Controller
public class JMSS_JSP_08ReceivingController {

    @Inject
    ReceiveMessageHelper receivemessageHelper;

    @ModelAttribute
    JmsReceivingForm setJmsReceivingForm() {
        JmsReceivingForm form = new JmsReceivingForm();
        return form;
    }

    @PostMapping(value = "receivemessage", params = "testCase=other_err")
    public String receiveMessageOtherErr(Model model, JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form
                .getJmsTodoId());
        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", jmsTodo.getJmsTodoId());
        }
        return "jsp/jmss/jmsReceive";
    }

    @PostMapping(value = "receivemessage", params = "testCase=catch_business_err")
    public String receiveMessageCatchBusinessErr(Model model,
            JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form
                .getJmsTodoId());
        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", jmsTodo.getJmsTodoId());
        }
        return "jsp/jmss/jmsReceive";
    }

    @PostMapping(value = "receivemessage", params = "testCase=catch_err_set_queue")
    public String receiveMessageCatchBusinessErrSetQueue(Model model,
            JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form
                .getJmsTodoId());
        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", "BusinessException! :" + form
                    .getJmsTodoId());
        }
        return "jsp/jmss/jmsReceive";
    }
}
