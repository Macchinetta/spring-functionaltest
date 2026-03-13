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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.resttemplate.UploadRestTemplateService;

/**
 * ガイドライン：HowToUse関連 - ファイルアップロード
 */
@RequestMapping("jsp/rscl/restclient")
@Controller
public class RSCL_JSP_09Controller {

    @Inject
    private UploadRestTemplateService uploadRestTemplateService;

    /**
     * テスト項目：09/01/001 マルチパートリクエストでサーバに送信が行えることを確認する
     * テスト画面表示
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "0901001")
    public String handle0901001First(Model model) {

        model.addAttribute("testDescription", "マルチパートリクエストでサーバに送信が行えることを確認する");
        model.addAttribute("testId", "0901001");

        return "jsp/rscl/restclient/fileUpload";
    }

    /**
     * テスト項目：09/01/001 マルチパートリクエストでサーバに送信が行えることを確認する
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @PostMapping(value = "0901001")
    public String handle0901001(Model model) {

        User rcvUser = this.uploadRestTemplateService.upload();

        model.addAttribute("resultDescription", "マルチパートリクエストでサーバに送信が行えることを確認する");
        model.addAttribute("user", rcvUser);

        return "jsp/rscl/restclient/resultUserInf";
    }
}
