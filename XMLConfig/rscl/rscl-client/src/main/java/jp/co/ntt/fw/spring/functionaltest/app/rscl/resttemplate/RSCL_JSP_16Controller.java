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
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.resttemplate.CustomConverterRestTemplateService;

/**
 * ガイドライン：HowToExtends関連 - カスタムコンバータ
 */
@RequestMapping("jsp/rscl/restclient")
@Controller
public class RSCL_JSP_16Controller {

    @Inject
    private CustomConverterRestTemplateService customConverterRestTemplateService;

    @ModelAttribute(value = "userInfForm")
    public UserInfForm setUpForm() {
        return new UserInfForm();
    }

    /**
     * テスト項目：16/01/001 受信データがカスタムコンバータで変換(csv⇒Object)されることを確認する
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1601001")
    public String handle1601001(Model model) {

        List<User> userList = customConverterRestTemplateService.getCsvUser();

        model.addAttribute("resultDescription", "受信データがカスタムコンバータで変換(csv⇒Object)されることを確認する");
        model.addAttribute("userList", userList);

        return "jsp/rscl/restclient/resultUserList";
    }

    /**
     * テスト項目：16/02/001 application/jsonのコンバータを変更し、受信データが変換(json⇒Object)されることを確認する
     * テスト画面表示
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1602001")
    public String handle1602001First(Model model) {

        model.addAttribute("testDescription", "application/jsonのコンバータを変更し、受信データが変換(json⇒Object)されることを確認する");
        model.addAttribute("testId", "1602001");

        return "jsp/rscl/restclient/sendUserInf";
    }

    /**
     * テスト項目：16/02/001 application/jsonのコンバータを変更し、受信データが変換(json⇒Object)されることを確認する
     * 送信処理
     * 
     * @param model Model
     * @param form  ユーザ情報フォーム
     * @return View name
     */
    @PostMapping(value = "1602001")
    public String handle1602001(Model model, UserInfForm form) {

        var sendUser = new User();
        sendUser.setName(form.getName());
        sendUser.setAge(form.getAge());

        User rcvUser = this.customConverterRestTemplateService.postUser(sendUser);

        model.addAttribute("resultDescription", "application/jsonのコンバータを変更し、受信データが変換(json⇒Object)されることを確認する");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

}
