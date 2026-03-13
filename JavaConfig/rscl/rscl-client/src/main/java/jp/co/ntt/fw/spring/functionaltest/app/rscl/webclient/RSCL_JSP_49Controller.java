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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.webclient.ErrorHandleWebClientService;

/**
 * ガイドライン：Appendix - エラーハンドリング
 */
@RequestMapping("jsp/rscl/webclient")
@Controller
public class RSCL_JSP_49Controller {

    @Inject
    private ErrorHandleWebClientService errorHandleWebClientService;

    /**
     * テスト項目：49/01/001 アプリケーション全体でエラーハンドリングの確認
     * 送信処理
     * 
     * @return View name
     */
    @GetMapping(value = "4901001")
    public CompletableFuture<ModelAndView> handle4901001() {
        return RSCL_JSP_Helper.handleUserFuture(errorHandleWebClientService.handleException(404));
    }

    /**
     * テスト項目：49/02/001 リクエスト毎でエラーハンドリングの確認
     * 送信処理
     * 
     * @return View name
     */
    @GetMapping(value = "4902001")
    public CompletableFuture<ModelAndView> handle4902001() {
        return RSCL_JSP_Helper.handleUserFuture(errorHandleWebClientService.handleException(500));
    }

    /**
     * テスト項目：49/03/001 エラーハンドリング（2xx系）の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "4903001")
    public CompletableFuture<ModelAndView> handle4903001(Model model) {
        return RSCL_JSP_Helper.handleUserFuture(errorHandleWebClientService.exception(200));
    }

    /**
     * テスト項目：49/04/001 エラーハンドリング（4xx系）の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "4904001")
    public CompletableFuture<ModelAndView> handle4904001() {
        return RSCL_JSP_Helper.handleUserFuture(errorHandleWebClientService.exception(404));
    }

    /**
     * テスト項目：49/05/001 エラーハンドリング（5xx系）の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "4905001")
    public CompletableFuture<ModelAndView> handle4905001() {
        return RSCL_JSP_Helper.handleUserFuture(errorHandleWebClientService.exception(500));
    }

    /**
     * テスト項目：49/06/001 エラーハンドリング（Unknown）の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "4906001")
    public CompletableFuture<ModelAndView> handle4906001() {
        return RSCL_JSP_Helper.handleUserFuture(errorHandleWebClientService.exception(901));
    }

    /**
     * テスト項目：49/07/001 ストリーム中の例外の確認
     * 送信処理
     * 
     * @param model Model
     * @return View name
     */
    @GetMapping(value = "4907001")
    public CompletableFuture<ModelAndView> handle4907001(Model model) {
        return RSCL_JSP_Helper.handleStatusFuture(errorHandleWebClientService.streamException());
    }
}
