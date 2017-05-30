/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.jmss;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("jmss")
public class JMSS09ReceivingController {

    @Inject
    ReceiveMessageHelper receivemessageHelper;

    @Value("${app.jms.temporaryDirectory}")
    String temporaryDirectory;

    @ModelAttribute
    JmsReceivingForm setJmsReceivingForm() {
        JmsReceivingForm form = new JmsReceivingForm();
        return form;
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=blob")
    public String receiveMessageValidationOK(Model model,
            JmsReceivingForm form, RedirectAttributes attrs) throws InterruptedException, IOException {

        String id = form.getJmsTodoId();
        List<String> list = receivemessageHelper
                .waitingReceivedMessageAndForList(id);
        if (list != null) {
            model.addAttribute("uniqueIdentifier", list.get(0));
        }

        return "jmss/jmsReceive";
    }
}
