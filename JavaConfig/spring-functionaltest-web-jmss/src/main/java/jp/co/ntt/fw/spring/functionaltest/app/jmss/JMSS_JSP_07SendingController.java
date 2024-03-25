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
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.inject.Inject;
import jakarta.jms.JMSException;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsCacheConSendingService;

@RequestMapping("jsp")
@Controller
public class JMSS_JSP_07SendingController {

    @Inject
    JmsCacheConSendingService jmsCacheConSendingService;


    @ModelAttribute
    JmsSendingForm setUpJmsSendingForm() {
        JmsSendingForm form = new JmsSendingForm();
        return form;
    }

    @GetMapping(value = "0701/001")
    public String handle0701001(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("validation_ok");
        return "jsp/jmss/jmsSend";
    }

    @GetMapping(value = "0701/002")
    public String handle0701002(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("validation_ng");
        return "jsp/jmss/jmsSend";
    }

    @GetMapping(value = "0701/003")
    public String handle0701003(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("input_validation_ok");
        return "jsp/jmss/jmsSend";
    }

    @GetMapping(value = "0701/004")
    public String handle0701004(JmsSendingForm form) {
        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("input_validation_ng");
        return "jsp/jmss/jmsSend";
    }

    @GetMapping(value = "0701/005")
    public String handle0701005(JmsSendingForm form) {
        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("input_validation_ng_with_err_msg");
        return "jsp/jmss/jmsSend";
    }

    @GetMapping(value = "0701/006")
    public String handle0701006(JmsSendingForm form) {
        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("input_validation_jms_transaction");
        return "jsp/jmss/jmsSend";
    }

    @GetMapping(value = "0701/007")
    public String handle0701007(JmsSendingForm form) {
        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("input_validation_isolated_transaction_jms_c_db_r");
        return "jsp/jmss/jmsSend";
    }

    @GetMapping(value = "0701/008")
    public String handle0701008(JmsSendingForm form) {
        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("input_validation_isolated_transaction_jms_db_c");
        return "jsp/jmss/jmsSend";
    }

    @PostMapping(value = "sendmessage", params = "testCase=validation_ok")
    public String sendMessageValidationOK(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        // send message
        jmsCacheConSendingService.sendMessageValidationOK(form.getJmsTodoId());

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }

    @PostMapping(value = "sendmessage", params = "testCase=validation_ng")
    public String sendMessageValidationNG(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        // send message
        jmsCacheConSendingService.sendMessageValidationNG(form.getJmsTodoId());

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }

    @PostMapping(value = "sendmessage", params = "testCase=input_validation_ok")
    public String sendMessageInputValidationOK(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {
        jmsCacheConSendingService.sendMessageInputValidationOk(form
                .getJmsTodoId());

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }

    @PostMapping(value = "sendmessage", params = "testCase=input_validation_ng")
    public String sendMessageInputValidationNg(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        jmsCacheConSendingService.sendMessageInputValidationNg(form
                .getJmsTodoId());

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }

    @PostMapping(value = "sendmessage", params = "testCase=input_validation_ng_with_err_msg")
    public String sendMessageInputValidationWithViolationErrMsg(Model model,
            JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        jmsCacheConSendingService.sendMessageInputValidationNgWithErrMsg(form
                .getJmsTodoId());

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }

    @PostMapping(value = "sendmessage", params = "testCase=input_validation_jms_transaction")
    public String sendMessageInputValidationJmsTransaction(Model model,
            JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        jmsCacheConSendingService.sendMessageInputValidationJmsTransaction(form
                .getJmsTodoId());

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }

    @PostMapping(value = "sendmessage", params = "testCase=input_validation_isolated_transaction_jms_c_db_r")
    public String sendMessageInputValidationIsolatedTransactionJmsCommitDbRollback(
            Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        jmsCacheConSendingService
                .sendMessageInputValidationIsolatedTransactionJmsCommitDbRollback(
                        form.getJmsTodoId());

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }

    @PostMapping(value = "sendmessage", params = "testCase=input_validation_isolated_transaction_jms_db_c")
    public String sendMessageInputValidationIsolatedTransactionJmsAndDbCommit(
            Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        jmsCacheConSendingService
                .sendMessageInputValidationIsolatedTransactionJmsAndDbCommit(
                        form.getJmsTodoId());

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jsp/receivemessage";
    }

}
