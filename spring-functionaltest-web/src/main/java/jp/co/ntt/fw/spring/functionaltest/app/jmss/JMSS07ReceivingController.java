/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.jmss;

import java.io.IOException;

import javax.inject.Inject;
import javax.jms.JMSException;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsAmqReceivingService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsSharedService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("jmss")
public class JMSS07ReceivingController {

    @Inject
    ReceiveMessageHelper receiveMessageHelper;

    @Inject
    JmsSharedService jmsSharedService;

    @Inject
    JmsAmqReceivingService jmsAmqReceivingService;

    @Value("${app.jms.temporaryDirectory}")
    String temporaryDirectory;

    @ModelAttribute
    JmsReceivingForm setJmsReceivingForm() {
        JmsReceivingForm form = new JmsReceivingForm();
        return form;
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=validation_ok")
    public String receiveMessageValidationOK(Model model,
            JmsReceivingForm form, RedirectAttributes attrs) throws InterruptedException, IOException {

        JmsTodo jmsTodo = receiveMessageHelper
                .waitingReceivedMessageAndForJmsTodo(form.getJmsTodoId());

        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", jmsTodo.getJmsTodoId());
        }

        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=validation_ng")
    public String receiveMessageValidationNG(Model model,
            JmsReceivingForm form, RedirectAttributes attrs) throws InterruptedException, IOException, JMSException {

        JmsTodo jmsTodo = receiveMessageHelper
                .waitingReceivedMessageAndForJmsTodo(form.getJmsTodoId());
        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", "Validated!! :"
                    + form.getJmsTodoId());
        }
        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=input_validation_ok")
    public String receiveMessageInputValidationOK(Model model,
            JmsReceivingForm form, RedirectAttributes attrs) throws InterruptedException, IOException {
        return prepareForReceivingPage(model, form);
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=input_validation_ng")
    public String receiveMessageInputValidationNg(Model model,
            JmsReceivingForm form, RedirectAttributes attrs) throws InterruptedException, IOException {
        return prepareForReceivingPage(model, form);
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=input_validation_ng_with_err_msg")
    public String receiveMessageInputValidationWithViolationErrMsg(Model model,
            JmsReceivingForm form, RedirectAttributes attrs) throws InterruptedException, IOException {
        return prepareForReceivingPage(model, form);
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=input_validation_jms_transaction")
    public String receiveMessageInputValidationJmsTransaction(Model model,
            JmsReceivingForm form, RedirectAttributes attrs) throws InterruptedException, IOException {
        return prepareForReceivingPage(model, form);
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=input_validation_isolated_transaction_jms_c_db_r")
    public String receiveInputValidationIsolatedTransactionJmsCommitDbRollback(
            Model model, JmsReceivingForm form, RedirectAttributes attrs) throws InterruptedException, IOException {
        JmsTodo jmsTodo = receiveMessageHelper
                .waitingReceivedMessageAndForJmsTodo(form.getJmsTodoId());

        JmsTodo jmsTodoInDB = jmsSharedService.find(form.getJmsTodoId());

        if (jmsTodo == null && jmsTodoInDB == null) {
            model.addAttribute("uniqueIdentifier", "rollbacked_MQandDB_"
                    + form.getJmsTodoId());
        } else if (jmsTodo == null) {
            model.addAttribute("uniqueIdentifier", "rollbacked_MQ_"
                    + form.getJmsTodoId());
        } else if (jmsTodoInDB == null) {
            model.addAttribute("uniqueIdentifier", "rollbacked_DB_"
                    + form.getJmsTodoId());
        } else {
            model.addAttribute("uniqueIdentifier", "not_rollbacked_MQandDB_"
                    + form.getJmsTodoId());
        }
        if (jmsSharedService.existsFile(temporaryDirectory
                + form.getJmsTodoId() + ".invoked")) {
            jmsSharedService.deleteFile(temporaryDirectory
                    + form.getJmsTodoId() + ".invoked");
        }
        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=input_validation_isolated_transaction_jms_db_c")
    public String receiveInputValidationIsolatedTransactionJmsAndDbCommit(
            Model model, JmsReceivingForm form, RedirectAttributes attrs) throws InterruptedException, IOException {
        JmsTodo jmsTodo = receiveMessageHelper
                .waitingReceivedMessageAndForJmsTodo(form.getJmsTodoId());

        JmsTodo jmsTodoInDB = jmsSharedService.find(form.getJmsTodoId());

        if (jmsTodo == null && jmsTodoInDB == null) {
            model.addAttribute("uniqueIdentifier", "rollbacked_MQandDB_"
                    + form.getJmsTodoId());
        } else if (jmsTodo == null) {
            model.addAttribute("uniqueIdentifier", "rollbacked_MQ_"
                    + form.getJmsTodoId());
        } else if (jmsTodoInDB == null) {
            model.addAttribute("uniqueIdentifier", "rollbacked_DB_"
                    + form.getJmsTodoId());
        } else {
            model.addAttribute("uniqueIdentifier", "not_rollbacked_MQandDB_"
                    + form.getJmsTodoId());
        }
        if (jmsSharedService.existsFile(temporaryDirectory
                + form.getJmsTodoId() + ".invoked")) {
            jmsSharedService.deleteFile(temporaryDirectory
                    + form.getJmsTodoId() + ".invoked");
        }
        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=input_validation_isolated_chaind_transaction_jms_c_db_r")
    public String receiveInputValidationIsolatedChaindTransactionJmsCommitDbRollback(
            Model model, JmsReceivingForm form, RedirectAttributes attrs) throws InterruptedException, IOException {
        JmsTodo jmsTodo = receiveMessageHelper
                .waitingReceivedMessageAndForJmsTodo(form.getJmsTodoId());

        JmsTodo jmsTodoInDB = jmsSharedService.find(form.getJmsTodoId());

        if (jmsTodo == null && jmsTodoInDB == null) {
            model.addAttribute("uniqueIdentifier", "rollbacked_MQandDB_"
                    + form.getJmsTodoId());
        } else if (jmsTodo == null) {
            model.addAttribute("uniqueIdentifier", "rollbacked_MQ_"
                    + form.getJmsTodoId());
        } else if (jmsTodoInDB == null) {
            model.addAttribute("uniqueIdentifier", "rollbacked_DB_"
                    + form.getJmsTodoId());
        } else {
            model.addAttribute("uniqueIdentifier", "not_rollbacked_MQandDB_"
                    + form.getJmsTodoId());
        }
        if (jmsSharedService.existsFile(temporaryDirectory
                + form.getJmsTodoId() + ".invoked")) {
            jmsSharedService.deleteFile(temporaryDirectory
                    + form.getJmsTodoId() + ".invoked");
        }
        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST, params = "testCase=input_validation_isolated_chaind_transaction_jms_db_c")
    public String receiveInputValidationIsolatedChaindTransactionJmsAndDbCommit(
            Model model, JmsReceivingForm form, RedirectAttributes attrs) throws InterruptedException, IOException {
        JmsTodo jmsTodo = receiveMessageHelper
                .waitingReceivedMessageAndForJmsTodo(form.getJmsTodoId());

        JmsTodo jmsTodoInDB = jmsSharedService.find(form.getJmsTodoId());

        if (jmsTodo == null && jmsTodoInDB == null) {
            model.addAttribute("uniqueIdentifier", "rollbacked_MQandDB_"
                    + form.getJmsTodoId());
        } else if (jmsTodo == null) {
            model.addAttribute("uniqueIdentifier", "rollbacked_MQ_"
                    + form.getJmsTodoId());
        } else if (jmsTodoInDB == null) {
            model.addAttribute("uniqueIdentifier", "rollbacked_DB_"
                    + form.getJmsTodoId());
        } else {
            model.addAttribute("uniqueIdentifier", "not_rollbacked_MQandDB_"
                    + form.getJmsTodoId());
        }
        if (jmsSharedService.existsFile(temporaryDirectory
                + form.getJmsTodoId() + ".invoked")) {
            jmsSharedService.deleteFile(temporaryDirectory
                    + form.getJmsTodoId() + ".invoked");
        }
        return "jmss/jmsReceive";
    }

    private String prepareForReceivingPage(Model model, JmsReceivingForm form) throws InterruptedException, IOException {
        JmsTodo jmsTodo = receiveMessageHelper
                .waitingReceivedMessageAndForJmsTodo(form.getJmsTodoId());

        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", jmsTodo.getJmsTodoId());
        }
        if (jmsSharedService.existsFile(temporaryDirectory
                + form.getJmsTodoId() + ".invoked")) {
            jmsSharedService.deleteFile(temporaryDirectory
                    + form.getJmsTodoId() + ".invoked");
        }

        return "jmss/jmsReceive";
    }

}
