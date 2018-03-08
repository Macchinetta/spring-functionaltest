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
public class JMSS04SendingController {

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

    @RequestMapping(value = "0401/002", method = RequestMethod.GET)
    public String handle0401002(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("selector_false");
        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0401/003", method = RequestMethod.GET)
    public String handle0401003(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("selector_true");
        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0402/001", method = RequestMethod.GET)
    public String handle0402001(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("sendto");
        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0403/001", method = RequestMethod.GET)
    public String handle0403001(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("jmsresponseB");
        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0403/002", method = RequestMethod.GET)
    public String handle0403002(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("jmsresponseC");
        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0403/003", method = RequestMethod.GET)
    public String handle0403003(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("resend_ano_mss");
        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0404/001", method = RequestMethod.GET)
    public String handle0404001(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("concurrent_listener_single");
        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0404/002", method = RequestMethod.GET)
    public String handle0404002(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("concurrent_listener_multiple");
        return "jmss/jmsSend";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=selector_false")
    public String sendMessageJmsMsg(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsCacheConSendingService.sendMessageBySelectorFalse(form
                .getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=selector_true")
    public String sendMessageAddKey(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsCacheConSendingService.sendMessageBySelectorTrue(form
                .getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=sendto")
    public String sendMessageSendTo(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsCacheConSendingService.sendMessageForSendTo(form.getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=jmsresponseB")
    public String sendMessageJmsresponseB(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsCacheConSendingService.sendMessageForJmsResponseB(form
                .getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=jmsresponseC")
    public String sendMessageJmsresponseC(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsCacheConSendingService.sendMessageForJmsResponseC(form
                .getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=resend_ano_mss")
    public String sendMessageReSendAnotherMessage(Model model,
            JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        jmsCacheConSendingService.sendMessageReSendAnotherMessage(form
                .getJmsTodoId());
        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=concurrent_listener_single")
    public String sendMessageJmsConcurrentListenerSingle(Model model,
            JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsCacheConSendingService.sendMessageConcurrentListenerSingle(form
                .getJmsTodoId());
        jmsCacheConSendingService.sendMessageConcurrentListenerSingle(form
                .getJmsTodoId());
        jmsCacheConSendingService.sendMessageConcurrentListenerSingle(form
                .getJmsTodoId());
        jmsCacheConSendingService.sendMessageConcurrentListenerSingle(form
                .getJmsTodoId());
        jmsCacheConSendingService.sendMessageConcurrentListenerSingle(form
                .getJmsTodoId());

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=concurrent_listener_multiple")
    public String sendMessageJmsConcurrentListenerMultiple(Model model,
            JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException {

        jmsCacheConSendingService.sendMessageConcurrentListenerMultiple(form
                .getJmsTodoId());
        jmsCacheConSendingService.sendMessageConcurrentListenerMultiple(form
                .getJmsTodoId());
        jmsCacheConSendingService.sendMessageConcurrentListenerMultiple(form
                .getJmsTodoId());
        jmsCacheConSendingService.sendMessageConcurrentListenerMultiple(form
                .getJmsTodoId());
        jmsCacheConSendingService.sendMessageConcurrentListenerMultiple(form
                .getJmsTodoId());

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }
}
