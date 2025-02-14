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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JmsTodo;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.jmss.JmsTodoRepository;

@RequestMapping("jsp")
@Controller
public class JMSS_JSP_06ReceivingController {

    @Inject
    ReceiveMessageHelper receivemessageHelper;

    @Inject
    JmsTodoRepository jmsTodoRepository;

    @ModelAttribute
    JmsReceivingForm setJmsReceivingForm() {
        JmsReceivingForm form = new JmsReceivingForm();
        return form;
    }

    @PostMapping(value = "receivemessage", params = "testCase=sendTranSuccess")
    public String receiveMessage_SendOK(Model model, JmsReceivingForm form)
            throws InterruptedException, IOException {

        // 一時ファイルの記載内容を取得し、JmsTodoに変換
        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form.getJmsTodoId());

        // アトリビュート設定
        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", form.getJmsTodoId());
        }

        return "jsp/jmss/jmsReceive";
    }

    @PostMapping(value = "receivemessage", params = "testCase=sendTranFail")
    public String receiveMessage_SendNG(Model model, JmsReceivingForm form)
            throws InterruptedException, IOException {

        // 一時ファイルの記載内容を取得し、JmsTodoに変換
        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form.getJmsTodoId());

        // アトリビュート設定
        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", form.getJmsTodoId());
        }

        return "jsp/jmss/jmsReceive";
    }

    @PostMapping(value = "receivemessage", params = "testCase=reciTranSuccess")
    public String receiveMessage_ReceiveOK(Model model, JmsReceivingForm form)
            throws InterruptedException, IOException {

        // 一時ファイルの記載内容を取得し、JmsTodoに変換
        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form.getJmsTodoId());

        // アトリビュート設定
        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", form.getJmsTodoId());
        }

        return "jsp/jmss/jmsReceive";
    }

    @PostMapping(value = "receivemessage", params = "testCase=reciTranFail")
    public String receiveMessage_ReceiveNG(Model model, JmsReceivingForm form)
            throws InterruptedException, IOException {

        // 一時ファイルの記載内容を取得し、JmsTodoに変換
        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form.getJmsTodoId());

        // アトリビュート設定
        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", form.getJmsTodoId());
        }

        return "jsp/jmss/jmsReceive";
    }

    @PostMapping(value = "receivemessage", params = "testCase=asyncTranSuccess")
    public String receiveMessage_AsyncOK(Model model, JmsReceivingForm form)
            throws InterruptedException, IOException {

        // 一時ファイルの記載内容を取得し、JmsTodoに変換
        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form.getJmsTodoId());

        // アトリビュート設定
        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", form.getJmsTodoId());
        }

        return "jsp/jmss/jmsReceive";
    }

    @PostMapping(value = "receivemessage", params = "testCase=asyncTranFail")
    public String receiveMessage_AsyncNG(Model model, JmsReceivingForm form)
            throws InterruptedException, IOException {

        // 一時ファイルの記載内容を取得し、JmsTodoに変換
        JmsTodo jmsTodo =
                receivemessageHelper.receiveMessagesForJmsTodo("rollbacked_" + form.getJmsTodoId());

        // アトリビュート設定
        if (jmsTodo != null) {
            model.addAttribute("uniqueIdentifier", "rollbacked_" + form.getJmsTodoId());
        }

        return "jsp/jmss/jmsReceive";
    }

    @PostMapping(value = "receivemessage", params = "testCase=sendTxBestEffort1PhaseSuccess")
    public String receiveMessage_sendTxBestEffort1PhaseOK(Model model, JmsReceivingForm form)
            throws InterruptedException, IOException {

        // 一時ファイルの記載内容を取得し、JmsTodoに変換
        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form.getJmsTodoId());

        JmsTodo jmsTodoInDB = jmsTodoRepository.findById(form.getJmsTodoId());

        // アトリビュート設定
        if (jmsTodo != null && jmsTodoInDB != null) {
            model.addAttribute("uniqueIdentifier", form.getJmsTodoId());
        }

        return "jsp/jmss/jmsReceive";
    }

    @PostMapping(value = "receivemessage", params = "testCase=sendTxBestEffort1PhaseFail")
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
        return "jsp/jmss/jmsReceive";
    }

    @PostMapping(value = "receivemessage", params = "testCase=receTxBestEffort1PhaseSuccess")
    public String receiveMessage_receTxBestEffort1PhaseOK(Model model, JmsReceivingForm form)
            throws InterruptedException, IOException {

        // 一時ファイルの記載内容を取得し、JmsTodoに変換
        JmsTodo jmsTodo = receivemessageHelper.receiveMessagesForJmsTodo(form.getJmsTodoId());

        JmsTodo jmsTodoInDB = jmsTodoRepository.findById(form.getJmsTodoId());

        // アトリビュート設定
        if (jmsTodo != null && jmsTodoInDB != null) {
            model.addAttribute("uniqueIdentifier", form.getJmsTodoId());
        }

        return "jsp/jmss/jmsReceive";
    }

    @PostMapping(value = "receivemessage", params = "testCase=receTxBestEffort1PhaseFail")
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

        return "jsp/jmss/jmsReceive";
    }

}
