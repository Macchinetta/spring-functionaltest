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
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.ResponseInfo;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.resttemplate.ResponseRestTemplateService;

/**
 * ガイドライン：HowToExtends関連 - レスポンス取得、リクエスト設定
 */
@RequestMapping("jsp/rscl/restclient")
@Controller
public class RSCL_JSP_11Controller {

    @Inject
    private ResponseRestTemplateService responseRestTemplateService;

    /**
     * テスト項目：11/01/001 ResponseEntityからHttpステータス、ヘッダ、ボディを取得できることを確認する
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1101001")
    public String handle1101001(Model model) {

        ResponseInfo responseInfo = responseRestTemplateService.responseEntity();

        model.addAttribute("resultDescription", "ResponseEntityからHttpステータス、ヘッダ、ボディを取得できることを確認する");
        model.addAttribute("responseInfo", responseInfo);

        return "jsp/rscl/restclient/resultResponse";
    }

    /**
     * テスト項目：11/02/001 httpRequestで設定したHTTPメソッド、リクエストヘッダでサーバに送信されることを確認する
     * (送信内容はログで確認)
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1102001")
    public String handle1102001(Model model) {

        ResponseInfo responseInfo = responseRestTemplateService.requestEntity();

        model.addAttribute("resultDescription", "httpRequestで設定したHTTPメソッド、リクエストヘッダでサーバに送信されることを確認する");
        model.addAttribute("responseInfo", responseInfo);

        return "jsp/rscl/restclient/resultResponse";
    }

}
