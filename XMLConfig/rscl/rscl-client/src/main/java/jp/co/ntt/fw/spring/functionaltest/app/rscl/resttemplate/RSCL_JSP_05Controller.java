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
package jp.co.ntt.fw.spring.functionaltest.app.rscl.resttemplate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.form.UserInfForm;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.resttemplate.ErrorHandleRestTemplateService;

/**
 * ガイドライン：HowToUse関連 - エラーハンドリング
 */
@RequestMapping("jsp/rscl/restclient")
@Controller
public class RSCL_JSP_05Controller {

    @Inject
    private ErrorHandleRestTemplateService errorHandleRestTemplateService;

    @ModelAttribute(value = "userInfForm")
    public UserInfForm setUpForm() {
        return new UserInfForm();
    }

    /**
     * テスト項目：05/01/001 エラーハンドリング（2xx系）の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0501001")
    public String handle0501001(Model model) {

        User rcvUser = errorHandleRestTemplateService.exception(200);

        model.addAttribute("resultDescription", "エラーハンドリング（2xx系）の確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：05/02/001 エラーハンドリング（4xx系）の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0502001")
    public void handle0502001(Model model) {

        errorHandleRestTemplateService.exception(404);

    }

    /**
     * テスト項目：05/03/001 エラーハンドリング（5xx系）の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0503001")
    public void handle0503001(Model model) {

        errorHandleRestTemplateService.exception(500);

    }

    /**
     * テスト項目：05/04/001 エラーハンドリング（Unknown）の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0504001")
    public String handle0504001(Model model) {

        User rcvUser = errorHandleRestTemplateService.exception(901);

        model.addAttribute("resultDescription", "エラーハンドリング（Unknown）の確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";

    }

    /**
     * テスト項目：05/05/001 エラーリトライ（成功）の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0505001")
    public String handle0505001(Model model) {

        User rcvUser = this.errorHandleRestTemplateService.exceptionRetry(2);

        model.addAttribute("resultDescription", "エラーリトライ（成功）の確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：05/06/001 エラーリトライ（失敗）の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0506001")
    public String handle0506001(Model model) {

        User rcvUser = this.errorHandleRestTemplateService.exceptionRetry(3);

        model.addAttribute("resultDescription", "エラーリトライ（失敗）の確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：05/07/001 ステータスコードハンドリング（2xx系）の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0507001")
    public String handle0507001(Model model) {

        User rcvUser = this.errorHandleRestTemplateService.status(200);

        model.addAttribute("resultDescription", "ステータスコードハンドリング（2xx系）の確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：05/08/001 ステータスコードハンドリング（4xx系）の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0508001")
    public String handle0508001(Model model) {

        User rcvUser = this.errorHandleRestTemplateService.status(404);

        model.addAttribute("resultDescription", "ステータスコードハンドリング（4xx系）の確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：05/09/001 ステータスコードハンドリング（5xx系）の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0509001")
    public String handle0509001(Model model) {

        User rcvUser = this.errorHandleRestTemplateService.status(500);

        model.addAttribute("resultDescription", "ステータスコードハンドリング（5xx系）の確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：05/10/001 ステータスコードハンドリング（Unknown）の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0510001")
    public String handle0510001(Model model) {

        User rcvUser = this.errorHandleRestTemplateService.status(901);

        model.addAttribute("resultDescription", "ステータスコードハンドリング（Unknown）の確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：05/11/001 ステータスコードハンドリング：NoOpResponseErrorHandler使用（2xx系）の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0511001")
    public String handle0511001(Model model) {

        User rcvUser = this.errorHandleRestTemplateService.statusNoOpResponse(200);

        model.addAttribute("resultDescription", "ステータスコードハンドリング：NoOpResponseErrorHandler使用（2xx系）の確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：05/12/001 ステータスコードハンドリング：NoOpResponseErrorHandler使用（4xx系）の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0512001")
    public String handle0512001(Model model) {

        User rcvUser = this.errorHandleRestTemplateService.statusNoOpResponse(404);

        model.addAttribute("resultDescription", "ステータスコードハンドリング：NoOpResponseErrorHandler使用（4xx系）の確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：05/13/001 ステータスコードハンドリング：NoOpResponseErrorHandler使用（5xx系）の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0513001")
    public String handle0513001(Model model) {

        User rcvUser = this.errorHandleRestTemplateService.statusNoOpResponse(500);

        model.addAttribute("resultDescription", "ステータスコードハンドリング：NoOpResponseErrorHandler使用（5xx系）の確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：05/14/001 ステータスコードハンドリング：NoOpResponseErrorHandler使用（Unknown）の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0514001")
    public String handle0514001(Model model) {

        User rcvUser = this.errorHandleRestTemplateService.statusNoOpResponse(901);

        model.addAttribute("resultDescription", "ステータスコードハンドリング：NoOpResponseErrorHandler使用（Unknown）の確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：05/15/001 ステータスコードリトライ（成功）の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0515001")
    public String handle0515001(Model model) {

        User rcvUser = this.errorHandleRestTemplateService.statusRetry(2);

        model.addAttribute("resultDescription", "ステータスコードリトライ（成功）の確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：05/16/001 ステータスコードリトライ（失敗）の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0516001")
    public String handle0516001(Model model) {

        User rcvUser = this.errorHandleRestTemplateService.statusRetry(3);

        model.addAttribute("resultDescription", "ステータスコードリトライ（失敗）の確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

}
