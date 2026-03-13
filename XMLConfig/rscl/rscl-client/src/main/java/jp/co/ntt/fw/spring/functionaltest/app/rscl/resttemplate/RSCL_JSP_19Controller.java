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
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.resttemplate.ProxyRestTemplateService;

/**
 * ガイドライン：Appendix関連 - Proxy
 */
@RequestMapping("jsp/rscl/restclient")
@Controller
public class RSCL_JSP_19Controller {

    @Inject
    private ProxyRestTemplateService proxyRestTemplateService;

    @ModelAttribute(value = "userInfForm")
    public UserInfForm setUpForm() {
        return new UserInfForm();
    }

    /**
     * テスト項目：19/01/001
     * HttpComponentsClientHttpRequestFactoryにProxyの設定を行い、サーバに送信が行えることを確認する
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1901001")
    public String handle1901001(Model model) {

        User rcvUser = this.proxyRestTemplateService.httpComponentsClient();

        model.addAttribute("resultDescription", "HttpComponentsClientHttpRequestFactoryにProxyの設定を行い、サーバに送信が行えることを確認する");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：19/02/001
     * HttpComponentsClientHttpRequestFactoryにProxyの設定(ユーザ、パスワードあり)を行い、サーバに送信が行えることを確認する
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1902001")
    public String handle1902001(Model model) {

        User rcvUser = this.proxyRestTemplateService.httpComponentsClientCredentialsSuccess();

        model.addAttribute("resultDescription",
                "HttpComponentsClientHttpRequestFactoryにProxyの設定(ユーザ、パスワードあり)を行い、サーバに送信が行えることを確認する");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：19/03/001
     * HttpComponentsClientHttpRequestFactoryにProxyの設定(ユーザ、パスワードあり)を行い、認証に失敗することを確認する
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1903001")
    public String handle1903001(Model model) {

        User rcvUser = this.proxyRestTemplateService.httpComponentsClientCredentialsFailure();

        model.addAttribute("resultDescription",
                "HttpComponentsClientHttpRequestFactoryにProxyの設定(ユーザ、パスワードあり)を行い、認証に失敗することを確認する");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }
}
