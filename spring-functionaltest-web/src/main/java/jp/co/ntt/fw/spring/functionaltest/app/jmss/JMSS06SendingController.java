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
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.jms.JMSException;

import jp.co.ntt.fw.spring.functionaltest.domain.repository.jmss.JmsTodoRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsAmqReceivingService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsCacheConSendingService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsChainedTransactedCacheConSendingService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsSharedService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsTransactedAmqReceivingService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsTransactedCacheConSendingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.BusinessException;

@Controller
@RequestMapping("jmss")
public class JMSS06SendingController {

    private static final Logger logger = LoggerFactory.getLogger(
            JMSS06SendingController.class);

    @Value("${app.jms.temporaryDirectory}")
    String temporaryDirectory;

    @Value("${app.jms.receiveWaitTime}")
    int receiveWaitTime;

    @Inject
    JmsSharedService jmsSharedService;

    @Inject
    JmsCacheConSendingService jmsCacheConSendingService;

    @Inject
    JmsTransactedCacheConSendingService jmsTransactedCacheConSendingService;

    @Inject
    JmsChainedTransactedCacheConSendingService jmsChainedTransactedCacheConSendingService;

    @Inject
    JmsAmqReceivingService jmsAmqReceivingService;

    @Inject
    JmsTransactedAmqReceivingService jmsTransactedAmqReceivingService;

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

    @RequestMapping(value = "0601/001", method = RequestMethod.GET)
    public String handle0601001(JmsSendingForm form) {

        // Formオブジェクトに値設定
        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("sendTranSuccess");

        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0601/002", method = RequestMethod.GET)
    public String handle0601002(JmsSendingForm form) {

        // Formオブジェクトに値設定
        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("sendTranFail");

        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0602/001", method = RequestMethod.GET)
    public String handle0602001(JmsSendingForm form) {

        // Formオブジェクトに値設定
        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("reciTranSuccess");

        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0602/002", method = RequestMethod.GET)
    public String handle0602002(JmsSendingForm form) {

        // Formオブジェクトに値設定
        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("reciTranFail");

        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0603/001", method = RequestMethod.GET)
    public String handle0603001(JmsSendingForm form) {

        // Formオブジェクトに値設定
        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("asyncTranSuccess");

        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0603/002", method = RequestMethod.GET)
    public String handle0603002(JmsSendingForm form) {

        // Formオブジェクトに値設定
        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("asyncTranFail");

        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0604/001", method = RequestMethod.GET)
    public String handle0604001(JmsSendingForm form) {

        // Formオブジェクトに値設定
        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("sendTxBestEffort1PhaseSuccess");

        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0604/002", method = RequestMethod.GET)
    public String handle0604002(JmsSendingForm form) {

        // Formオブジェクトに値設定
        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("sendTxBestEffort1PhaseFail");

        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0604/005", method = RequestMethod.GET)
    public String handle0604005(JmsSendingForm form) {

        // Formオブジェクトに値設定
        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("receTxBestEffort1PhaseSuccess");

        return "jmss/jmsSend";
    }

    @RequestMapping(value = "0604/006", method = RequestMethod.GET)
    public String handle0604006(JmsSendingForm form) {

        // Formオブジェクトに値設定
        form.setJmsTodoId(UUID.randomUUID().toString());
        form.setTestCase("receTxBestEffort1PhaseFail");

        return "jmss/jmsSend";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=sendTranSuccess")
    public String sendMessage_SendOK(JmsSendingForm form,
            RedirectAttributes attrs) throws IOException, JMSException, InterruptedException {

        // メッセージ送信
        jmsTransactedCacheConSendingService.sendMessage_TxSndOK(form
                .getJmsTodoId());

        // メッセージ取得可否判定（true：取得可 false:取得不可）
        if (!jmsAmqReceivingService.receiveMessage_TxSndOK(form
                .getJmsTodoId())) {
            form.setJmsTodoId("Not Received!");
        } else {
            form.setJmsTodoId(form.getJmsTodoId());
        }

        // アトリビュート設定
        attrs.addFlashAttribute("jmsSendingForm", form);

        return "redirect:/jmss/receivemessage";
    }

    @Inject
    JmsTodoRepository jmsTodoRepository;

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=sendTranFail")
    public String sendMessage_SendNG(JmsSendingForm form,
            RedirectAttributes attrs) throws JMSException, IOException {

        // メッセージ送信（強制的に例外発生）
        try {
            jmsTransactedCacheConSendingService.sendMessage_TxSndNG(form
                    .getJmsTodoId());
        } catch (BusinessException me) {
            logger.info("BusinessException Occured");
        }

        // メッセージ取得可否判定（true：取得可 false:取得不可）
        if (!jmsAmqReceivingService.receiveMessage_TxSndNG(form
                .getJmsTodoId())) {
            form.setJmsTodoId("Not Received!");
        } else {
            form.setJmsTodoId(form.getJmsTodoId());
        }

        // アトリビュート設定
        attrs.addFlashAttribute("jmsSendingForm", form);

        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=reciTranSuccess")
    public String sendMessage_ReceiveOK(JmsSendingForm form,
            RedirectAttributes attrs) throws IOException, JMSException, InterruptedException {

        // メッセージ送信
        jmsCacheConSendingService.sendMessage_TxRcvOK(form.getJmsTodoId());

        // メッセージ受信
        jmsTransactedAmqReceivingService.receiveMessage_TxRcvOK(form
                .getJmsTodoId());

        // Queue内のメッセージを確認
        int msgCnt = jmsAmqReceivingService.getMessageCount("TestQueue0602001",
                "");
        if (msgCnt == 0) {
            form.setJmsTodoId(form.getJmsTodoId());
        } else {
            form.setJmsTodoId("Not Received!");
        }

        // アトリビュート設定
        attrs.addFlashAttribute("jmsSendingForm", form);

        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=reciTranFail")
    public String sendMessage_ReceiveNG(JmsSendingForm form,
            RedirectAttributes attrs) throws JMSException, InterruptedException {

        // メッセージ送信
        jmsCacheConSendingService.sendMessage_TxRcvNG(form.getJmsTodoId());

        // メッセージ受信（強制的に例外発生）
        try {
            jmsTransactedAmqReceivingService.receiveMessage_TxRcvNG(form
                    .getJmsTodoId());
        } catch (BusinessException me) {
            logger.info("BusinessException Occured");
        }

        // Queue内のメッセージを確認
        int msgCnt = jmsAmqReceivingService.getMessageCount("TestQueue0602002",
                "");
        if (msgCnt == 0) {
            form.setJmsTodoId(form.getJmsTodoId());
        } else {
            form.setJmsTodoId("Not Received!");
        }

        // アトリビュート設定
        attrs.addFlashAttribute("jmsSendingForm", form);

        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=asyncTranSuccess")
    public String sendMessage_asyncOK(JmsSendingForm form,
            RedirectAttributes attrs) throws IOException, JMSException {

        // メッセージ送信
        jmsCacheConSendingService.sendMessage_TxAsyncOK(form.getJmsTodoId());

        // アトリビュート設定
        attrs.addFlashAttribute("jmsSendingForm", form);

        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=asyncTranFail")
    public String sendMessage_asyncNG(JmsSendingForm form,
            RedirectAttributes attrs) throws IOException {

        // メッセージ送信
        jmsCacheConSendingService.sendMessage_TxAsyncNG(form.getJmsTodoId());

        // アトリビュート設定
        attrs.addFlashAttribute("jmsSendingForm", form);

        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=sendTxBestEffort1PhaseSuccess")
    public String sendMessage_sendTxBestEffort1PhaseOK(JmsSendingForm form,
            RedirectAttributes attrs) throws IOException {

        // メッセージ送信
        jmsChainedTransactedCacheConSendingService
                .sendMessage_sendTxBestEffort1PhaseOK(form.getJmsTodoId());
        // アトリビュート設定
        attrs.addFlashAttribute("jmsSendingForm", form);

        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=sendTxBestEffort1PhaseFail")
    public String sendMessage_sendTxBestEffort1PhaseNG(JmsSendingForm form,
            RedirectAttributes attrs) throws IOException {

        try {
            // メッセージ送信
            jmsChainedTransactedCacheConSendingService
                    .sendMessage_sendTxBestEffort1PhaseNG(form.getJmsTodoId());
        } catch (BusinessException ex) {
            logger.info("BusinessException Occured");
        }

        // アトリビュート設定
        attrs.addFlashAttribute("jmsSendingForm", form);

        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=receTxBestEffort1PhaseSuccess")
    public String sendMessage_receTxBestEffort1PhaseOK(JmsSendingForm form,
            RedirectAttributes attrs) throws IOException {

        // メッセージ送信
        jmsCacheConSendingService.sendMessage_receTxBestEffort1PhaseOK(form
                .getJmsTodoId());

        // アトリビュート設定
        attrs.addFlashAttribute("jmsSendingForm", form);

        return "redirect:/jmss/receivemessage";
    }

    @RequestMapping(value = "sendmessage", method = RequestMethod.POST, params = "testCase=receTxBestEffort1PhaseFail")
    public String sendMessage_receTxBestEffort1PhaseNG(JmsSendingForm form,
            RedirectAttributes attrs) throws IOException {

        // メッセージ送信
        jmsCacheConSendingService.sendMessage_receTxBestEffort1PhaseNG(form
                .getJmsTodoId());

        // アトリビュート設定
        attrs.addFlashAttribute("jmsSendingForm", form);

        return "redirect:/jmss/receivemessage";
    }

}
