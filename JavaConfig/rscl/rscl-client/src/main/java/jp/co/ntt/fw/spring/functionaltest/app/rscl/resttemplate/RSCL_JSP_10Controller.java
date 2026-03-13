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
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.resttemplate.DownloadRestTemplateService;

/**
 * ガイドライン：HowToUse関連 - ファイルダウンロード
 */
@RequestMapping("jsp/rscl/restclient")
@Controller
public class RSCL_JSP_10Controller {

    @Inject
    private DownloadRestTemplateService downloadRestTemplateService;

    /**
     * テスト項目：10/01/001 byteにてファイルダウンロードが行えることを確認する
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1001001")
    public String handle1001001(Model model) {

        List<User> userList = downloadRestTemplateService.byteDownload();

        model.addAttribute("resultDescription", "byteにてファイルダウンロードが行えることを確認する");
        model.addAttribute("userList", userList);

        return "jsp/rscl/restclient/resultUserList";
    }

    /**
     * テスト項目：10/02/001 Resourceにてファイルダウンロードが行えることを確認する
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1002001")
    public String handle1002001(Model model) {

        List<User> userList = downloadRestTemplateService.resourceDownload();

        model.addAttribute("resultDescription", "Resourceにてファイルダウンロードが行えることを確認する");
        model.addAttribute("userList", userList);

        return "jsp/rscl/restclient/resultUserList";
    }

    /**
     * テスト項目：10/03/001 ResponseExtractor(Inputstream)にてファイルダウンロードが行えることを確認する
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1003001")
    public String handle1003001(Model model) {

        List<User> userList = downloadRestTemplateService.inputStreamDownload();

        model.addAttribute("resultDescription", "ResponseExtractor(Inputstream)にてファイルダウンロードが行えることを確認する");
        model.addAttribute("userList", userList);

        return "jsp/rscl/restclient/resultUserList";
    }

    /**
     * テスト項目：10/04/001
     * ResponseExtractor(Inputstream)にてファイルダウンロードを行った際に、404エラーが発生した場合のエラー内容を確認する
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1004001")
    public String handle1004001(Model model) {

        List<User> userList = downloadRestTemplateService.inputStreamDownloadError();

        model.addAttribute("resultDescription",
                "ResponseExtractor(Inputstream)にてファイルダウンロードを行った際に、404エラーが発生した場合のエラー内容を確認する");
        model.addAttribute("userList", userList);

        return "jsp/rscl/restclient/resultUserList";
    }

    /**
     * テスト項目：10/05/001 ResponseEntityからHttpステータス、ヘッダ、ボディを取得できることを確認する（正常）
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1005001")
    public String handle1005001(Model model) {

        List<User> userList = downloadRestTemplateService.inputStreamResponse();

        model.addAttribute("resultDescription", "ResponseEntityからHttpステータス、ヘッダ、ボディを取得できることを確認する（正常）");
        model.addAttribute("userList", userList);

        return "jsp/rscl/restclient/resultUserList";
    }

    /**
     * テスト項目：10/06/001 ResponseEntityからHttpステータス、ヘッダ、ボディを取得できることを確認する（エラー）
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "1006001")
    public String handle1006001(Model model) {

        List<User> userList = downloadRestTemplateService.inputStreamResponseError();

        model.addAttribute("resultDescription", "ResponseEntityからHttpステータス、ヘッダ、ボディを取得できることを確認する（エラー）");
        model.addAttribute("userList", userList);

        return "jsp/rscl/restclient/resultUserList";
    }

}
