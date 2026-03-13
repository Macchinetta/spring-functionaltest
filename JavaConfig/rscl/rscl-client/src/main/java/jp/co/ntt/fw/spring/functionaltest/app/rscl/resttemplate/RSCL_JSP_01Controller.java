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
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.resttemplate.BufferingRestTemplateService;

/**
 * ガイドライン：Overview - リクエストの生成（ClientHttpRequestFactory）
 */
@RequestMapping("jsp/rscl/restclient")
@Controller
public class RSCL_JSP_01Controller {

    @Inject
    private BufferingRestTemplateService bufferingRestTemplateService;

    @ModelAttribute(value = "userInfForm")
    public UserInfForm setUpForm() {
        return new UserInfForm();
    }

    /**
     * テスト項目：01/01/001 リクエストボディがバッファリングされなくなったことによるテスト（バッファリングなし）
     * テスト画面表示
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0101001")
    public String handle0101001First(Model model) {

        model.addAttribute("testDescription", "リクエストボディがバッファリングされなくなったことによるテスト（バッファリングなし）");
        model.addAttribute("testId", "0101001");

        return "jsp/rscl/restclient/sendUserInf";
    }

    /**
     * テスト項目：01/01/001 リクエストボディがバッファリングされなくなったことによるテスト（バッファリングなし）
     * 送信処理
     * 
     * @param model Model
     * @param form  ユーザ情報フォーム
     * @return View name
     */
    @PostMapping(value = "0101001")
    public String handle0101001(Model model, UserInfForm form) {

        var sendUser = new User();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        User rcvUser = this.bufferingRestTemplateService.postNonBuffered(sendUser);

        model.addAttribute("resultDescription", "リクエストボディがバッファリングされなくなったことによるテスト（バッファリングなし）");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：01/02/001 リクエストボディがバッファリングされなくなったことによるテスト（バッファリングあり）
     * テスト画面表示
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0102001")
    public String handle0102001First(Model model) {

        model.addAttribute("testDescription", "リクエストボディがバッファリングされなくなったことによるテスト（バッファリングあり）");
        model.addAttribute("testId", "0102001");

        return "jsp/rscl/restclient/sendUserInf";
    }

    /**
     * テスト項目：01/02/001 リクエストボディがバッファリングされなくなったことによるテスト（バッファリングあり）
     * 送信処理
     * 
     * @param model Model
     * @param form  ユーザ情報フォーム
     * @return View name
     */
    @PostMapping(value = "0102001")
    public String handle0102001(Model model, UserInfForm form) {

        var sendUser = new User();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        User rcvUser = this.bufferingRestTemplateService.postBuffered(sendUser);

        model.addAttribute("resultDescription", "リクエストボディがバッファリングされなくなったことによるテスト（バッファリングあり）");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

}
