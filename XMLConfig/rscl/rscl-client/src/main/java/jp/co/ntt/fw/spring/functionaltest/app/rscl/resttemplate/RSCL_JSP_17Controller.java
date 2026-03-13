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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.form.UserInfForm;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.resttemplate.InterceptorRestTemplateService;

/**
 * ガイドライン：HowToExtends関連 - リクエスト送信前後処理
 */
@RequestMapping("jsp/rscl/restclient")
@Controller
public class RSCL_JSP_17Controller {

    @Inject
    private InterceptorRestTemplateService interceptorRestTemplateService;

    @ModelAttribute(value = "userInfForm")
    public UserInfForm setUpForm() {
        return new UserInfForm();
    }

    /**
     * テスト項目：17/01/001 リクエスト送信前後処理に設定したログ出力を確認する
     * テスト画面表示
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1701001")
    public String handle1701001First(Model model) {

        model.addAttribute("testDescription", "リクエスト送信前後処理に設定したログ出力を確認する");
        model.addAttribute("testId", "1701001");

        return "jsp/rscl/restclient/sendUserInf";
    }

    /**
     * テスト項目：17/01/001 リクエスト送信前後処理に設定したログ出力を確認する
     * 送信処理
     * 
     * @param model Model
     * @param form  ユーザ情報フォーム
     * @return View name
     */
    @PostMapping(value = "1701001")
    public String handle1701001(Model model, UserInfForm form) {

        User sendUser = new User();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        User rcvUser = this.interceptorRestTemplateService.post(sendUser);

        model.addAttribute("resultDescription", "リクエスト送信前後処理に設定したログ出力を確認する");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：17/02/001 複数のインターセプターを実行して順序を確認する
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1702001")
    public String handle1702001(Model model) {

        User rcvUser = interceptorRestTemplateService.multiInterceptor();

        model.addAttribute("resultDescription", "複数のインターセプターを実行して順序を確認する");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }
}
