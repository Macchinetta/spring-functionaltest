/*
 * Copyright(c) 2014 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.app.rscl;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.terasoluna.gfw.common.message.ResultMessages;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.AsyncRestClientService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.InterceptorsAsyncRestClientService;

@RequestMapping("rscl")
@Controller
public class RSCL13Controller {

    @Inject
    AsyncRestClientService asyncRestClientService;

    @Inject
    InterceptorsAsyncRestClientService interceptorsAsyncRestClientService;

    @RequestMapping(value = "1301/001", method = RequestMethod.GET)
    public String handle1301001First(Model model) {

        model.addAttribute("testDescription",
                "AsyncRestTemplateを使用して、REST APIを呼び出し、JavaBeanを取得する");
        model.addAttribute("testId", "1301/001");

        return "rscl/setAsyncRestRequestPass";
    }

    @RequestMapping(value = "1301/001", method = RequestMethod.POST)
    public String handle1301001(Model model, @RequestParam String path) {

        UserResource rcvUser = this.asyncRestClientService.confirmAsync01(path);

        model.addAttribute("resultDescription",
                "AsyncRestTemplateを使用して、想定される結果が返却されているか確認");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

    @RequestMapping(value = "1302/001", method = RequestMethod.GET)
    public String handle1302001First(Model model) {

        model.addAttribute("testDescription",
                "AsyncRestTemplateを使用して、REST APIを複数回連続で呼び出し、スレッドプールのキューを溢れさせ、スレッド数の制限が行えていることを確認");
        model.addAttribute("testId", "1302/001");

        return "rscl/sendAsyncRestRequest";
    }

    @RequestMapping(value = "1302/001", method = RequestMethod.POST)
    public void handle1302001(Model model) {

        this.asyncRestClientService.confirmAsync02();

    }

    @RequestMapping(value = "1303/001", method = RequestMethod.GET)
    public String handle1303001First(Model model) {

        model.addAttribute("testDescription",
                "AsyncClientHttpRequestInterceptorを使用したロギングの確認（レスポンスコードが正常系(2xx)の場合）");
        model.addAttribute("testId", "1303/001");

        return "rscl/extensionOfAsyncRest";
    }

    @RequestMapping(value = "1303/001", method = RequestMethod.POST)
    public String handle1303001(Model model) {
        UserResource rcvUser = this.interceptorsAsyncRestClientService
                .getUserForStatusCode200();

        model.addAttribute("resultDescription", "取得したユーザ情報");
        if (rcvUser != null) {
            model.addAttribute("user", rcvUser);
        } else {
            ResultMessages messages = ResultMessages.error().add(
                    "e.rc.fw.8002");
            model.addAttribute(messages);
        }

        return "rscl/resultUserInf";
    }

    @RequestMapping(value = "1303/002", method = RequestMethod.GET)
    public String handle1303002First(Model model) {

        model.addAttribute("testDescription",
                "AsyncClientHttpRequestInterceptorを使用したロギングの確認（レスポンスコードが正常系(2xx)以外の場合）");
        model.addAttribute("testId", "1303/002");

        return "rscl/extensionOfAsyncRest";
    }

    @RequestMapping(value = "1303/002", method = RequestMethod.POST)
    public String handle1303002(Model model) {
        UserResource rcvUser = this.interceptorsAsyncRestClientService
                .getUserForStatusCode401();

        model.addAttribute("resultDescription", "取得したユーザ情報");
        if (rcvUser != null) {
            model.addAttribute("user", rcvUser);
        } else {
            ResultMessages messages = ResultMessages.error().add(
                    "e.rc.fw.8002");
            model.addAttribute(messages);
        }

        return "rscl/resultUserInf";
    }

    @RequestMapping(value = "1303/003", method = RequestMethod.GET)
    public String handle1303003First(Model model) {

        model.addAttribute("testDescription",
                "AsyncClientHttpRequestInterceptorを使用したロギングの確認（指定したホストに接続できない場合）");
        model.addAttribute("testId", "1303/003");

        return "rscl/extensionOfAsyncRest";
    }

    @RequestMapping(value = "1303/003", method = RequestMethod.POST)
    public String handle1303003(Model model) {

        UserResource rcvUser = this.interceptorsAsyncRestClientService
                .getUserForConnectError();

        model.addAttribute("resultDescription", "取得したユーザ情報");
        if (rcvUser != null) {
            model.addAttribute("user", rcvUser);
        } else {
            ResultMessages messages = ResultMessages.error().add(
                    "e.rc.fw.8002");
            model.addAttribute(messages);
        }
        return "rscl/resultUserInf";
    }

    @RequestMapping(value = "1303/004", method = RequestMethod.GET)
    public String handle1303004First(Model model) {

        model.addAttribute("testDescription",
                "AsyncClientHttpRequestInterceptorを使用したロギングの確認（レスポンスデータの読み込みタイムアウトが発生した場合）");
        model.addAttribute("testId", "1303/004");

        return "rscl/extensionOfAsyncRest";
    }

    @RequestMapping(value = "1303/004", method = RequestMethod.POST)
    public String handle1303004(Model model) {

        UserResource rcvUser = this.interceptorsAsyncRestClientService
                .getUserForReadTimeout();

        model.addAttribute("resultDescription", "取得したユーザ情報");
        if (rcvUser != null) {
            model.addAttribute("user", rcvUser);
        } else {
            ResultMessages messages = ResultMessages.error().add(
                    "e.rc.fw.8002");
            model.addAttribute(messages);
        }

        return "rscl/resultUserInf";
    }

}
