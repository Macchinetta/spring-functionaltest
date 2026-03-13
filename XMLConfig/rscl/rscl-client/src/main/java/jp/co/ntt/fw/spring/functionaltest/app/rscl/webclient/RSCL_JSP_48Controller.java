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
package jp.co.ntt.fw.spring.functionaltest.app.rscl.webclient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.ResponseInfo;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.Result;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.webclient.EntryWebClientService;

/**
 * ガイドライン：Appendix - 非同期通信のデータ登録
 * Bean設定も含めてのテスト
 */
@RequestMapping("jsp/rscl/webclient")
@Controller
public class RSCL_JSP_48Controller {

    private static final Logger logger = LoggerFactory.getLogger(RSCL_JSP_48Controller.class);

    @Inject
    private EntryWebClientService entryWebClientService;

    /**
     * テスト項目：48/01/001 WebClientの設定を行い、データ一括登録(非同期)を確認する
     * テスト画面表示
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "4801001")
    public String handle4801001First(Model model) {

        model.addAttribute("testDescription", "WebClientの設定を行い、データ一括登録(非同期)を確認する");
        model.addAttribute("testId", "4801001");

        return "jsp/rscl/webclient/extensionOfAsyncRest";
    }

    /**
     * テスト項目：48/01/001 WebClientの設定を行い、データ一括登録(非同期)を確認する
     * 送信処理
     * 
     * @return View name
     */
    @PostMapping(value = "4801001")
    public CompletableFuture<ModelAndView> handle4801001() {
        return RSCL_JSP_Helper.handleResponseFuture(this.entryWebClientService.createAsync());
    }

    /**
     * テスト項目：48/02/001 WebClientの設定を行い、データ一括登録(同期)を確認する
     * テスト画面表示
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "4802001")
    public String handle4802001First(Model model) {

        model.addAttribute("testDescription", "WebClientの設定を行い、データ一括登録(同期)を確認する");
        model.addAttribute("testId", "4802001");

        return "jsp/rscl/webclient/extensionOfAsyncRest";
    }

    /**
     * テスト項目：48/02/001 WebClientの設定を行い、データ一括登録(同期)を確認する
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @PostMapping(value = "4802001")
    public String handle4802001(Model model) {

        CompletableFuture<ResponseEntity<Result>> future = this.entryWebClientService.createSync();
        ResponseEntity<Result> response = null;

        try {
            response = future.handle((r, t) -> {
                logger.info("Completed Request Thread: {}", Thread.currentThread().getName());
                if (t == null) {
                    logger.info("Successfully created users. statusCode: {}", r.getStatusCode());
                } else {
                    logger.error("Error occurred while creating users", t);
                }
                return r;
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error occurred during WebClient synchronous call", e);
            throw new RuntimeException(e);
        }

        var responseInfo = new ResponseInfo(response.getStatusCode().value(), response.getHeaders().toString(),
                response.getBody().toString());

        model.addAttribute("resultDescription", "WebClientの設定を行い、データ一括登録(同期)を確認する");
        model.addAttribute("responseInfo", responseInfo);

        return "jsp/rscl/webclient/resultResponse";
    }

}
