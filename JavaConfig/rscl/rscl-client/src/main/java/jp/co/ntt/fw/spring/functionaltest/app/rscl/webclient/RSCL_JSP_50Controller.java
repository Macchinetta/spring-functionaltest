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
package jp.co.ntt.fw.spring.functionaltest.app.rscl.webclient;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.form.UserInfForm;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.webclient.FilterFunctionWebClientService;

/**
 * ガイドライン：Appendix - リクエスト送信前の共通処理の適用
 */
@RequestMapping("jsp/rscl/webclient")
@Controller
public class RSCL_JSP_50Controller {

    @Inject
    private FilterFunctionWebClientService filterFunctionWebClientService;

    @ModelAttribute(value = "userInfForm")
    public UserInfForm setUpForm() {
        return new UserInfForm();
    }

    /**
     * テスト項目：50/01/001 ExchangeFilterFunctionの設定を行い、リクエスト送信前のログを確認する
     * テスト画面表示
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "5001001")
    public String handle5001001First(Model model) {

        model.addAttribute("testDescription", "ExchangeFilterFunctionの設定を行い、リクエスト送信前のログを確認する");
        model.addAttribute("testId", "5001001");

        return "jsp/rscl/webclient/sendUserInf";
    }

    /**
     * テスト項目：50/01/001 ExchangeFilterFunctionの設定を行い、リクエスト送信前のログを確認する
     * 送信処理
     * 
     * @param form ユーザ情報フォーム
     * @return View name
     */
    @PostMapping(value = "5001001")
    public CompletableFuture<ModelAndView> handle5001001(UserInfForm form) {

        var sendUser = new User();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        return RSCL_JSP_Helper.handleUserFuture(this.filterFunctionWebClientService.postLogging(sendUser));

    }

    /**
     * テスト項目：50/02/001 ExchangeFilterFunctionの設定を行い、リクエストヘッダを確認する
     * 送信処理
     * 
     * @return View name
     */
    @GetMapping(value = "5002001")
    public CompletableFuture<ModelAndView> handle5002001() {
        return RSCL_JSP_Helper.handleUserFuture(this.filterFunctionWebClientService.getHeader());
    }
}
