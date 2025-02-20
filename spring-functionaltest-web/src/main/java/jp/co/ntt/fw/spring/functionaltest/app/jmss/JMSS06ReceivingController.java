/*
 * Copyright(c) 2014 NTT Corporation.
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

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.jmss.JmsTodoRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsAsyncReceiveSynchronizingService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsSharedService;

@Controller
@RequestMapping("jmss")
public class JMSS06ReceivingController {

    @Inject
    ReceiveMessageHelper receivemessageHelper;

    @Inject
    JmsTodoRepository jmsTodoRepository;

    @Inject
    JmsSharedService jmsSharedService;

    @Inject
    JmsAsyncReceiveSynchronizingService jmsAsyncReceiveSynchronizingService;

    @Value("${app.jms.temporaryDirectory}")
    String temporaryDirectory;

    @ModelAttribute
    JmsReceivingForm setJmsReceivingForm() {
        JmsReceivingForm form = new JmsReceivingForm();
        return form;
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST,
            params = "testCase=sendTranSuccess")
    public String receiveMessage_SendOK(Model model, JmsReceivingForm form)
            throws InterruptedException, IOException {

        // 一時ファイルの記載内容を取得し、JmsTodoに変換
        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form.getJmsTodoId());

        // アトリビュート設定
        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", form.getJmsTodoId());
        }

        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST,
            params = "testCase=sendTranFail")
    public String receiveMessage_SendNG(Model model, JmsReceivingForm form)
            throws InterruptedException, IOException {

        // 一時ファイルの記載内容を取得し、JmsTodoに変換
        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form.getJmsTodoId());

        // アトリビュート設定
        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", form.getJmsTodoId());
        }

        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST,
            params = "testCase=reciTranSuccess")
    public String receiveMessage_ReceiveOK(Model model, JmsReceivingForm form)
            throws InterruptedException, IOException {

        // 一時ファイルの記載内容を取得し、JmsTodoに変換
        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form.getJmsTodoId());

        // アトリビュート設定
        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", form.getJmsTodoId());
        }

        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST,
            params = "testCase=reciTranFail")
    public String receiveMessage_ReceiveNG(Model model, JmsReceivingForm form)
            throws InterruptedException, IOException {

        // 一時ファイルの記載内容を取得し、JmsTodoに変換
        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form.getJmsTodoId());

        // アトリビュート設定
        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", form.getJmsTodoId());
        }

        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST,
            params = "testCase=asyncTranSuccess")
    public String receiveMessage_AsyncOK(Model model, JmsReceivingForm form)
            throws InterruptedException, IOException {

        // 一時ファイルの記載内容を取得し、JmsTodoに変換
        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form.getJmsTodoId());

        // アトリビュート設定
        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", form.getJmsTodoId());
        }

        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST,
            params = "testCase=asyncTranFail")
    public String receiveMessage_AsyncNG(Model model, JmsReceivingForm form)
            throws InterruptedException, IOException {

        // 一時ファイルの記載内容を取得し、JmsTodoに変換
        JmsTodo jmsTodo =
                receivemessageHelper.receiveMessagesForJmsTodo("rollbacked_" + form.getJmsTodoId());

        // アトリビュート設定
        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", "rollbacked_" + form.getJmsTodoId());
        }

        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST,
            params = "testCase=sendTxBestEffort1PhaseSuccess")
    public String receiveMessage_sendTxBestEffort1PhaseOK(Model model, JmsReceivingForm form)
            throws InterruptedException, IOException {

        // 一時ファイルの記載内容を取得し、JmsTodoに変換
        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form.getJmsTodoId());

        JmsTodo jmsTodoInDB = jmsTodoRepository.findById(form.getJmsTodoId());

        // アトリビュート設定
        if (jmsTodo != null && jmsTodoInDB != null) {
            model.addAttribute("uniqueIdentifier", form.getJmsTodoId());
        }

        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST,
            params = "testCase=sendTxBestEffort1PhaseFail")
    public String receiveMessage_sendTxBestEffort1PhaseNG(Model model, JmsReceivingForm form)
            throws InterruptedException, IOException {

        // 一時ファイルの記載内容を取得し、JmsTodoに変換
        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form.getJmsTodoId());

        JmsTodo jmsTodoInDB = jmsTodoRepository.findById(form.getJmsTodoId());

        // アトリビュート設定
        if (jmsTodo == null && jmsTodoInDB == null) {
            model.addAttribute("uniqueIdentifier", "rollbacked_MQandDB_" + form.getJmsTodoId());
        } else if (jmsTodo == null) {
            model.addAttribute("uniqueIdentifier", "rollbacked_MQ_" + form.getJmsTodoId());
        } else if (jmsTodoInDB == null) {
            model.addAttribute("uniqueIdentifier", "rollbacked_DB_" + form.getJmsTodoId());
        } else {
            model.addAttribute("uniqueIdentifier", "not_rollbacked_MQandDB_" + form.getJmsTodoId());
        }
        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST,
            params = "testCase=receTxBestEffort1PhaseSuccess")
    public String receiveMessage_receTxBestEffort1PhaseOK(Model model, JmsReceivingForm form)
            throws InterruptedException, IOException {

        // 一時ファイルの記載内容を取得し、JmsTodoに変換
        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form.getJmsTodoId());

        JmsTodo jmsTodoInDB = jmsTodoRepository.findById(form.getJmsTodoId());

        // アトリビュート設定
        if (jmsTodo != null && jmsTodoInDB != null) {
            model.addAttribute("uniqueIdentifier", form.getJmsTodoId());
        }

        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "receivemessage", method = RequestMethod.POST,
            params = "testCase=receTxBestEffort1PhaseFail")
    public String receiveMessage_receTxBestEffort1PhaseNG(Model model, JmsReceivingForm form)
            throws InterruptedException, IOException {

        // MQがRollbackしたことを示す一時ファイルの記載内容を取得し、JmsTodoに変換
        JmsTodo jmsTodo =
                receivemessageHelper.receiveMessagesForJmsTodo("rollbacked_" + form.getJmsTodoId());

        JmsTodo jmsTodoInDB = jmsTodoRepository.findById(form.getJmsTodoId());

        // アトリビュート設定
        if (jmsTodo != null && jmsTodoInDB != null) {
            model.addAttribute("uniqueIdentifier", "rollbacked_MQ_" + form.getJmsTodoId());
        } else if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", "rollbacked_MQandDB_" + form.getJmsTodoId());
        } else if (jmsTodoInDB != null) {
            model.addAttribute("uniqueIdentifier", "rollbacked_DB_" + form.getJmsTodoId());
        } else {
            model.addAttribute("uniqueIdentifier", "not_rollbacked_MQandDB_" + form.getJmsTodoId());
        }

        return "jmss/jmsReceive";
    }

    @RequestMapping(value = "await")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void awaitUntilReceivedMessages(@RequestParam("jmsTodoId") String jmsTodoId) {
        jmsAsyncReceiveSynchronizingService.await(jmsTodoId);
    }

    @RequestMapping(value = "clear")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void clear() throws IOException {
        jmsAsyncReceiveSynchronizingService.deleteTempFilesAndClearMap();
    }

}
