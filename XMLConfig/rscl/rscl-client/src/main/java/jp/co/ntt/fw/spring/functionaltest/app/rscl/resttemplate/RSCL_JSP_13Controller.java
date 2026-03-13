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
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.Order;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.resttemplate.UriRestTemplateService;

/**
 * ガイドライン：HowToExtends関連 - 埋め込みパラメータ
 */
@RequestMapping("jsp/rscl/restclient")
@Controller
public class RSCL_JSP_13Controller {

    @Inject
    private UriRestTemplateService uriRestTemplateService;

    /**
     * テスト項目：13/01/001 LocalDateの埋め込みパラメータがString(yyyyMMdd形式)に変換されて送信されることを確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1301001")
    public String handle1301001(Model model) {

        Order order = uriRestTemplateService.get();

        model.addAttribute("resultDescription", "LocalDateの埋め込みパラメータがString(yyyyMMdd形式)に変換されて送信されることを確認");
        model.addAttribute("order", order);

        return "jsp/rscl/restclient/resultOrderInf";
    }

}
