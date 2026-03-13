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

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.form.UserInfForm;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.resttemplate.MethodRestTemplateService;

/**
 * ガイドライン：HowToUse関連 - HTTPメソッド
 */
@RequestMapping("jsp/rscl/restclient")
@Controller
public class RSCL_JSP_04Controller {

    @Inject
    private MethodRestTemplateService methodRestTemplateService;

    @ModelAttribute(value = "userInfForm")
    public UserInfForm setUpForm() {
        return new UserInfForm();
    }

    /**
     * テスト項目：04/01/001 GET送信-単一件取得（正常）の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0401001")
    public String handle0401001(Model model) {

        User rcvUser = this.methodRestTemplateService.get();

        model.addAttribute("resultDescription", "GET送信-単一件取得（正常）の確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：04/02/001 GET送信-複数件取得（正常）の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0402001")
    public String handle0402001(Model model) {

        List<User> rcvUser = this.methodRestTemplateService.getList();

        model.addAttribute("resultDescription", "GET送信-複数件取得（正常）の確認");
        model.addAttribute("userList", rcvUser);

        return "jsp/rscl/restclient/resultUserList";
    }

    /**
     * テスト項目：04/03/001 POST送信（正常）の確認
     * テスト画面表示
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0403001")
    public String handle0403001First(Model model) {

        model.addAttribute("testDescription", "POST送信（正常）の確認");
        model.addAttribute("testId", "0403001");

        return "jsp/rscl/restclient/sendUserInf";
    }

    /**
     * テスト項目：04/03/001 POST送信（正常）の確認
     * 送信処理
     * 
     * @param model Model
     * @param form  ユーザ情報フォーム
     * @return View name
     */
    @PostMapping(value = "0403001")
    public String handle0403001(Model model, UserInfForm form) {

        User sendUser = new User();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        User rcvUser = this.methodRestTemplateService.post(sendUser);

        model.addAttribute("resultDescription", "POST送信（正常）の確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：04/04/001 PUT送信（正常）の確認
     * テスト画面表示
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0404001")
    public String handle0404001First(Model model) {

        model.addAttribute("testDescription", "PUT送信（正常）の確認");
        model.addAttribute("testId", "0404001");

        return "jsp/rscl/restclient/sendUserInf";
    }

    /**
     * テスト項目：04/04/001 PUT送信（正常）の確認
     * 送信処理
     * 
     * @param model Model
     * @param form  ユーザ情報フォーム
     * @return View name
     */
    @PostMapping(value = "0404001")
    public String handle0404001(Model model, UserInfForm form) {

        User sendUser = new User();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        User rcvUser = this.methodRestTemplateService.put(sendUser);

        model.addAttribute("resultDescription", "PUT送信（正常）の確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：04/05/001 PATCH送信（正常）の確認
     * テスト画面表示
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0405001")
    public String handle0405001First(Model model) {

        model.addAttribute("testDescription", "PATCH送信（正常）の確認");
        model.addAttribute("testId", "0405001");

        return "jsp/rscl/restclient/sendUserInf";
    }

    /**
     * テスト項目：04/05/001 PATCH送信（正常）の確認
     * 送信処理
     * 
     * @param model Model
     * @param form  ユーザ情報フォーム
     * @return View name
     */
    @PostMapping(value = "0405001")
    public String handle0405001(Model model, UserInfForm form) {

        User sendUser = new User();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        User rcvUser = this.methodRestTemplateService.patch(sendUser);

        model.addAttribute("resultDescription", "PATCH送信（正常）の確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：04/06/001 DELETE送信（正常）の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0406001")
    public String handle0406001(Model model) {

        this.methodRestTemplateService.delete();

        model.addAttribute("resultDescription", "DELETE送信（正常）の確認");

        return "jsp/rscl/restclient/result";
    }

    /**
     * テスト項目：04/07/001 HEAD送信（正常）の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0407001")
    public String handle0407001(Model model) {

        this.methodRestTemplateService.head();

        model.addAttribute("resultDescription", "HEAD送信（正常）の確認");

        return "jsp/rscl/restclient/result";
    }
}
