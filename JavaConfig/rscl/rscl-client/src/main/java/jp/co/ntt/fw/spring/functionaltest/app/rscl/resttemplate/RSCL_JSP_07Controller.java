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
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.resttemplate.CommonAuthHeaderRestTemplateService;

/**
 * ガイドライン：HowToUse関連 - 認証
 */
@RequestMapping("jsp/rscl/restclient")
@Controller
public class RSCL_JSP_07Controller {

    @Inject
    private CommonAuthHeaderRestTemplateService commonAuthHeaderRestTemplateService;

    /**
     * テスト項目：07/01/001 認証成功（Basic）でサーバにアクセスができるかの確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0701001")
    public String handle0701001(Model model) {

        User rcvUser = commonAuthHeaderRestTemplateService.basicAuthSuccess();

        model.addAttribute("resultDescription", "認証成功（Basic）でサーバにアクセスができるかの確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：07/02/001 認証失敗（Basic）でサーバにアクセスができるかの確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0702001")
    public String handle0702001(Model model) {

        User rcvUser = commonAuthHeaderRestTemplateService.basicAuthError();

        model.addAttribute("resultDescription", "認証失敗（Basic）でサーバにアクセスができるかの確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：07/03/001 認証失敗（Basic）でサーバにアクセスができるかの確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0703001")
    public String handle0703001(Model model) {

        User rcvUser = commonAuthHeaderRestTemplateService.basicAuthNotSet();

        model.addAttribute("resultDescription", "認証未設定（Basic）でサーバにアクセスができるかの確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }
}
