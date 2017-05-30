/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.jmss;

import java.io.IOException;

import javax.inject.Inject;
import javax.jms.JMSException;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsAmqReceivingService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("jmss")
public class JMSS08ReceivingController {

    @Inject
    ReceiveMessageHelper receivemessageHelper;

    @Inject
    JmsAmqReceivingService jmsAmqReceivingService;

    @ModelAttribute
    JmsReceivingForm setJmsReceivingForm() {
        JmsReceivingForm form = new JmsReceivingForm();
        return form;
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=other_err")
    public String receiveMessageOtherErr(Model model, JmsReceivingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        JmsTodo jmsTodo = receivemessageHelper
                .waitingReceivedMessageAndForJmsTodo(form.getJmsTodoId());
        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", jmsTodo.getJmsTodoId());
        }
        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=catch_business_err")
    public String receiveMessageCatchBusinessErr(Model model,
            JmsReceivingForm form, RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        JmsTodo jmsTodo = receivemessageHelper
                .waitingReceivedMessageAndForJmsTodo(form.getJmsTodoId());
        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", jmsTodo.getJmsTodoId());
        }
        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=catch_err_set_queue")
    public String receiveMessageCatchBusinessErrSetQueue(Model model,
            JmsReceivingForm form, RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        JmsTodo jmsTodo = receivemessageHelper
                .waitingReceivedMessageAndForJmsTodo(form.getJmsTodoId());
        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", "BusinessException! :"
                    + form.getJmsTodoId());
        }
        return "jmss/jmsReceive";
    }
}
