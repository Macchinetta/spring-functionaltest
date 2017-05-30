/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.jmss;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("jmss")
public class ReceiveMessageController {

    @ModelAttribute
    JmsReceivingForm setJmsReceivingForm() {
        JmsReceivingForm form = new JmsReceivingForm();
        return form;
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.GET)
    public String initReceive(Model model, JmsSendingForm form,
            JmsReceivingForm jmsReceivingForm, RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsReceivingForm.setJmsTodoId(form.getJmsTodoId());
        jmsReceivingForm.setTestCase(form.getTestCase());
        model.addAttribute("jmsReceivingForm", jmsReceivingForm);
        model.addAttribute("initFlg", "1");
        return "jmss/jmsReceive";
    }

}
