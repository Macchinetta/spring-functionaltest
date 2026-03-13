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
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.resttemplate.BaseUrlRestTemplateService;

/**
 * ガイドライン：HowToUse関連 - BaseURL
 */
@RequestMapping("jsp/rscl/restclient")
@Controller
public class RSCL_JSP_03Controller {

    @Inject
    private BaseUrlRestTemplateService baseUrlRestTemplateService;

    /**
     * テスト項目：03/01/001 BaseURLを使いURL変換が正しく行え、送信が行えるかを確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0301001")
    public String handle0301001(Model model) {

        User rcvUser = baseUrlRestTemplateService.getRelativeUrl();

        model.addAttribute("resultDescription", "BaseURLを使いURL変換が正しく行え、送信が行えるかを確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

    /**
     * テスト項目：03/02/001 BaseURLを使い絶対URLで送信が行えるかを確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0302001")
    public String handle0302001(Model model) {

        User rcvUser = baseUrlRestTemplateService.getAbsoluteUrl();

        model.addAttribute("resultDescription", "BaseURLを使い絶対URLで送信が行えるかを確認");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }

}
