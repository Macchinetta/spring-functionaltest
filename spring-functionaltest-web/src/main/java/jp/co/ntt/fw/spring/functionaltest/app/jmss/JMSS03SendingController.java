/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.jmss;

import java.io.IOException;
import java.util.UUID;

import javax.inject.Inject;
import javax.jms.JMSException;

import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsCacheConSendingService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("jmss")
public class JMSS03SendingController {

    @Inject
    JmsCacheConSendingService jmsCacheConSendingService;

    @Value("${app.jms.temporaryDirectory}")
    String temporaryDirectory;

    @Value("${app.jms.receiveWaitTime}")
    int receiveWaitTime;

    int receiveCheckInterval = 200;

    @ModelAttribute
    JmsSendingForm setUpJmsSendingForm() {
        JmsSendingForm form = new JmsSendingForm();
        return form;
    }

    @ModelAttribute
    JmsReceivingForm setJmsReceivingForm() {
        JmsReceivingForm form = new JmsReceivingForm();
        return form;
    }

    @RequestMapping(value = "0301/001", method = RequestMethod.GET)
    public String handle0301001(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("jmsMessage");
        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0301/003", method = RequestMethod.GET)
    public String handle0301003(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("addKey");
        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0301/004", method = RequestMethod.GET)
    public String handle0301004(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("topic");
        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0302/001", method = RequestMethod.GET)
    public String handle0302001(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("many");
        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0303/001", method = RequestMethod.GET)
    public String handle0303001(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("withHeadersOK");
        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0303/002", method = RequestMethod.GET)
    public String handle0303002(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("withHeadersNG");
        return "jmss/jmsSend";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=jmsMessage")
    public String sendMessageJmsMsg(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsCacheConSendingService.sendMessageByJmsMessage(form.getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=addKey")
    public String sendMessageAddKey(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsCacheConSendingService.sendMessageAddKey(form.getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=topic")
    public String sendMessageForTopic(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsCacheConSendingService.sendMessageForTopic(form.getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=many")
    public String sendMessageForMany(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsCacheConSendingService.sendMessageMany(form.getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=withHeadersOK")
    public String sendMessageWithHeadersOK(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        // send message
        jmsCacheConSendingService.sendMessageWithHeadersOK(form.getJmsTodoId());

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=withHeadersNG")
    public String sendMessageWithHeadersNG(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        // send message
        jmsCacheConSendingService.sendMessageWithHeadersNG(form.getJmsTodoId());

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

}
