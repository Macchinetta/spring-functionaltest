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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("jsp")
@Controller
public class ReceiveMessage_JSP_Controller {

    @ModelAttribute
    JmsReceivingForm setJmsReceivingForm() {
        JmsReceivingForm form = new JmsReceivingForm();
        return form;
    }

    @GetMapping(value = "receivemessage")
    public String initReceive(Model model, JmsSendingForm form, JmsReceivingForm jmsReceivingForm,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsReceivingForm.setJmsTodoId(form.getJmsTodoId());
        jmsReceivingForm.setTestCase(form.getTestCase());
        model.addAttribute("jmsReceivingForm", jmsReceivingForm);
        model.addAttribute("initFlg", "1");
        return "jsp/jmss/jmsReceive";
    }

}
