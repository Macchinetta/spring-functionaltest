/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.jmss;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.jms.JMSException;

import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsAmqReceivingService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsCacheConSendingService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsSharedService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("jmss")
public class JMSS07SendingController {

    private static final Logger logger = LoggerFactory
            .getLogger(JMSS07SendingController.class);

    @Inject
    JmsCacheConSendingService jmsCacheConSendingService;

    @Inject
    JmsAmqReceivingService jmsAmqReceivingService;

    @Value("${app.jms.temporaryDirectory}")
    String temporaryDirectory;

    @Value("${app.jms.receiveWaitTime}")
    int receiveWaitTime;

    @Inject
    JmsSharedService jmsSharedService;

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

    @RequestMapping(value = "0701/001", method = RequestMethod.GET)
    public String handle0701001(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("validation_ok");
        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0701/002", method = RequestMethod.GET)
    public String handle0701002(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("validation_ng");
        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0701/003", method = RequestMethod.GET)
    public String handle0701003(JmsSendingForm form) {

        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("input_validation_ok");
        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0701/004", method = RequestMethod.GET)
    public String handle0701004(JmsSendingForm form) {
        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("input_validation_ng");
        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0701/005", method = RequestMethod.GET)
    public String handle0701005(JmsSendingForm form) {
        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("input_validation_ng_with_err_msg");
        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0701/006", method = RequestMethod.GET)
    public String handle0701006(JmsSendingForm form) {
        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("input_validation_jms_transaction");
        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0701/007", method = RequestMethod.GET)
    public String handle0701007(JmsSendingForm form) {
        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("input_validation_isolated_transaction_jms_c_db_r");
        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0701/008", method = RequestMethod.GET)
    public String handle0701008(JmsSendingForm form) {
        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("input_validation_isolated_transaction_jms_db_c");
        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0701/009", method = RequestMethod.GET)
    public String handle0701009(JmsSendingForm form) {
        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("input_validation_isolated_chaind_transaction_jms_c_db_r");
        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0701/010", method = RequestMethod.GET)
    public String handle0701010(JmsSendingForm form) {
        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("input_validation_isolated_chaind_transaction_jms_db_c");
        return "jmss/jmsSend";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=validation_ok")
    public String sendMessageValidationOK(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        // send message
        jmsCacheConSendingService.sendMessageValidationOK(form.getJmsTodoId());

        for (int i = 0; i < 3; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(receiveWaitTime);
            } catch (InterruptedException e) {
                logger.warn("InterruptedException Occured", e);
            }

            // 一時ファイルの出力確認。
            if (!jmsSharedService.existsFile(temporaryDirectory
                    + form.getJmsTodoId())) {
                break;
            }
        }

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=validation_ng")
    public String sendMessageValidationNG(Model model, JmsSendingForm form,
            RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        // send message
        jmsCacheConSendingService.sendMessageValidationNG(form.getJmsTodoId());

        for (int i = 0; i < 4; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(receiveWaitTime);
            } catch (InterruptedException e) {
                logger.warn("InterruptedException Occured", e);
            }

            // 一時ファイルの存在確認
            if (!jmsSharedService.existsFile(temporaryDirectory
                    + form.getJmsTodoId())) {
                break;
            }
        }

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=input_validation_ok")
    public String sendMessageInputValidationOK(Model model,
            JmsSendingForm form, RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {
        jmsCacheConSendingService.sendMessageInputValidationOk(form
                .getJmsTodoId());

        for (int i = 0; i < 4; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(receiveWaitTime);
            } catch (InterruptedException e) {
                logger.warn("InterruptedException Occured", e);
            }

            // 一時ファイルの存在確認
            if (!jmsSharedService.existsFile(temporaryDirectory
                    + form.getJmsTodoId())) {
                break;
            }
        }

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=input_validation_ng")
    public String sendMessageInputValidationNg(Model model,
            JmsSendingForm form, RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        jmsCacheConSendingService.sendMessageInputValidationNg(form
                .getJmsTodoId());

        for (int i = 0; i < 4; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(receiveWaitTime);
            } catch (InterruptedException e) {
                logger.warn("InterruptedException Occured", e);
            }

            // 一時ファイルの存在確認
            if (!jmsSharedService.existsFile(temporaryDirectory
                    + form.getJmsTodoId())) {
                break;
            }
        }

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=input_validation_ng_with_err_msg")
    public String sendMessageInputValidationWithViolationErrMsg(Model model,
            JmsSendingForm form, RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        jmsCacheConSendingService.sendMessageInputValidationNgWithErrMsg(form
                .getJmsTodoId());

        for (int i = 0; i < 4; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(receiveWaitTime);
            } catch (InterruptedException e) {
                logger.warn("InterruptedException Occured", e);
            }

            // 一時ファイルの存在確認
            if (!jmsSharedService.existsFile(temporaryDirectory
                    + form.getJmsTodoId())) {
                break;
            }
        }

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=input_validation_jms_transaction")
    public String sendMessageInputValidationJmsTransaction(Model model,
            JmsSendingForm form, RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        jmsCacheConSendingService.sendMessageInputValidationJmsTransaction(form
                .getJmsTodoId());

        for (int i = 0; i < 4; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(receiveWaitTime);
            } catch (InterruptedException e) {
                logger.warn("InterruptedException Occured", e);
            }

            // 一時ファイルの存在確認
            if (!jmsSharedService.existsFile(temporaryDirectory
                    + form.getJmsTodoId())) {
                break;
            }
        }

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=input_validation_isolated_transaction_jms_c_db_r")
    public String sendMessageInputValidationIsolatedTransactionJmsCommitDbRollback(
            Model model, JmsSendingForm form, RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        jmsCacheConSendingService
                .sendMessageInputValidationIsolatedTransactionJmsCommitDbRollback(form
                        .getJmsTodoId());

        for (int i = 0; i < 4; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(receiveWaitTime);
            } catch (InterruptedException e) {
                logger.warn("InterruptedException Occured", e);
            }

            // 一時ファイルの存在確認
            if (!jmsSharedService.existsFile(temporaryDirectory
                    + form.getJmsTodoId())) {
                break;
            }
        }

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=input_validation_isolated_transaction_jms_db_c")
    public String sendMessageInputValidationIsolatedTransactionJmsAndDbCommit(
            Model model, JmsSendingForm form, RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        jmsCacheConSendingService
                .sendMessageInputValidationIsolatedTransactionJmsAndDbCommit(form
                        .getJmsTodoId());

        for (int i = 0; i < 4; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(receiveWaitTime);
            } catch (InterruptedException e) {
                logger.warn("InterruptedException Occured", e);
            }

            // 一時ファイルの存在確認
            if (!jmsSharedService.existsFile(temporaryDirectory
                    + form.getJmsTodoId())) {
                break;
            }
        }

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=input_validation_isolated_chaind_transaction_jms_c_db_r")
    public String sendMessageInputValidationIsolatedChaindTransactionJmsCommitDbRollback(
            Model model, JmsSendingForm form, RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        jmsCacheConSendingService
                .sendMessageInputValidationIsolatedChaindTransactionJmsCommitDbRollback(form
                        .getJmsTodoId());

        for (int i = 0; i < 4; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(receiveWaitTime);
            } catch (InterruptedException e) {
                logger.warn("InterruptedException Occured", e);
            }

            // 一時ファイルの存在確認
            if (!jmsSharedService.existsFile(temporaryDirectory
                    + form.getJmsTodoId())) {
                break;
            }
        }

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=input_validation_isolated_chaind_transaction_jms_db_c")
    public String sendMessageInputValidationIsolatedChaindTransactionJmsAndDbCommit(
            Model model, JmsSendingForm form, RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        jmsCacheConSendingService
                .sendMessageInputValidationIsolatedChaindTransactionJmsAndDbCommit(form
                        .getJmsTodoId());

        for (int i = 0; i < 4; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(receiveWaitTime);
            } catch (InterruptedException e) {
                logger.warn("InterruptedException Occured", e);
            }

            // 一時ファイルの存在確認
            if (!jmsSharedService.existsFile(temporaryDirectory
                    + form.getJmsTodoId())) {
                break;
            }
        }

        attrs.addFlashAttribute("jmsSendingForm", form);
        return "redirect:/jmss/receivemessage";
    }

}
