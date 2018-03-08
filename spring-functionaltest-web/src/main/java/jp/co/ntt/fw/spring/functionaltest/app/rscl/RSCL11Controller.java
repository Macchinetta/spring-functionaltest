/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.app.rscl;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.InterceptorsRestClientService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.terasoluna.gfw.common.exception.BusinessException;

@RequestMapping("rscl")
@Controller
public class RSCL11Controller {

    @Inject
    InterceptorsRestClientService interceptorsRestClientService;

    @RequestMapping(value = "1102/001", method = RequestMethod.GET)
    public String handle1102001First(Model model) {

        model.addAttribute("testDescription",
                "ClientHttpRequestInterceptorを使用したロギングおよびBasic認証の確認(認証成功)");
        model.addAttribute("testId", "1102/001");

        return "rscl/extensionOfRest";
    }

    @RequestMapping(value = "1102/001", method = RequestMethod.POST)
    public String handle1102001(Model model) {
        UserResource rcvUser = this.interceptorsRestClientService
                .confirmInterceptor0101();

        model.addAttribute("resultDescription", "取得したユーザ情報");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

    @RequestMapping(value = "1103/002", method = RequestMethod.GET)
    public String handle1102002First(Model model) {

        model.addAttribute("testDescription",
                "ClientHttpRequestInterceptorを使用したロギングおよびBasic認証の確認(認証失敗)");
        model.addAttribute("testId", "1103/002");

        return "rscl/extensionOfRest";
    }

    @RequestMapping(value = "1103/002", method = RequestMethod.POST)
    public String handle1102002(Model model) {
        UserResource rcvUser = this.interceptorsRestClientService
                .confirmInterceptor0102();

        model.addAttribute("resultDescription", "取得したユーザ情報");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

    @RequestMapping(value = "1104/001", method = RequestMethod.GET)
    public String handle1104001First(Model model) {

        model.addAttribute("testDescription",
                "ClientHttpRequestInterceptorを使用したサーバとの通信の閉塞制御の確認");
        model.addAttribute("testId", "1104/001");

        return "rscl/extensionOfRest";
    }

    @RequestMapping(value = "1104/001", method = RequestMethod.POST)
    public void handle1104001(Model model) {

        try {
            this.interceptorsRestClientService.confirmInterceptor02();
        } catch (BusinessException e) {
            throw e;
        }
    }

    @RequestMapping(value = "1105/001", method = RequestMethod.GET)
    public String handle1105001First(Model model) {

        model.addAttribute("testDescription",
                "ClientHttpRequestInterceptorを使用した通信処理のリトライの確認");
        model.addAttribute("testId", "1105/001");

        return "rscl/extensionOfRest";
    }

    @RequestMapping(value = "1105/001", method = RequestMethod.POST)
    public String handle1105001(Model model) {

        UserResource rcvUser = this.interceptorsRestClientService
                .confirmInterceptor03();

        model.addAttribute("resultDescription", "取得したユーザ情報");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

}
