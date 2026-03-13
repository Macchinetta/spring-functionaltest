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
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.UnknownHttpStatusCodeException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.ModelAndView;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception.CustomException;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.Message;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.ResponseInfo;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.Result;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;

public class RSCL_JSP_Helper {

    private static final Logger logger = LoggerFactory.getLogger(RSCL_JSP_Helper.class);

    /**
     * CompletableFutureの結果を処理する共通メソッド。
     * (ステータスのみ確認用)
     * 
     * @param future CompletableFuture 非同期処理結果
     * @return 非同期モードのModelAndView
     */
    public static CompletableFuture<ModelAndView> handleStatusFuture(
            CompletableFuture<ResponseEntity<Message>> future) {
        return future.handle((r, t) -> {
            logger.info("Completed Request Thread: {}", Thread.currentThread().getName());
            if (t == null) {
                logger.info("Success StatusCode: {}", r.getStatusCode().value());
                ModelAndView mv = new ModelAndView("jsp/rscl/webclient/result");
                mv.addObject("resultDescription", "正常終了");
                return mv;
            } else {
                return createErrorModelAndView(t);
            }
        }).orTimeout(10, TimeUnit.SECONDS);
    }

    /**
     * CompletableFutureの結果を処理する共通メソッド。
     * (レスポンス確認用)
     * 
     * @param future CompletableFuture 非同期処理結果
     * @return 非同期モードのModelAndView
     */
    public static CompletableFuture<ModelAndView> handleResponseFuture(
            CompletableFuture<ResponseEntity<Result>> future) {
        return future.handle((r, t) -> {
            logger.info("Completed Request Thread: {}", Thread.currentThread().getName());
            if (t == null) {
                logger.info("Successfully created users. statusCode: {}", r.getStatusCode());
                ModelAndView mv = new ModelAndView("jsp/rscl/webclient/resultResponse");
                var responseInfo = new ResponseInfo(r.getStatusCode().value(), r.getHeaders().toString(),
                        r.getBody().toString());
                mv.addObject("resultDescription", "正常終了");
                mv.addObject("responseInfo", responseInfo);
                return mv;
            } else {
                return createErrorModelAndView(t);
            }
        }).orTimeout(10, TimeUnit.SECONDS);
    }

    /**
     * CompletableFutureの結果を処理する共通メソッド。
     * (ユーザ情報単一)
     * 
     * @param future CompletableFuture 非同期処理結果
     * @return 非同期モードのModelAndView
     */
    public static CompletableFuture<ModelAndView> handleUserFuture(CompletableFuture<ResponseEntity<User>> future) {
        return future.handle((r, t) -> {
            logger.info("Completed Request Thread: {}", Thread.currentThread().getName());
            if (t == null) {
                logger.info("Success StatusCode: {}", r.getStatusCode().value());
                ModelAndView mv = new ModelAndView("jsp/rscl/webclient/resultUserInf");
                mv.addObject("resultDescription", "正常終了");
                mv.addObject("user", r.getBody());
                return mv;
            } else {
                return createErrorModelAndView(t);
            }
        }).orTimeout(10, TimeUnit.SECONDS);
    }

    private static ModelAndView createErrorModelAndView(Throwable t) {
        logger.error("Error occurred while creating users", t);
        ModelAndView mv = new ModelAndView("jsp/rscl/webclient/asyncError");
        mv.addObject("errorCode", "e.sf.fw.9001");
        mv.addObject("errorMessage", t.getMessage());
        if (t instanceof WebClientResponseException ex) {
            mv.addObject("statusCode", ex.getStatusCode().value());
        } else if (t instanceof UnknownHttpStatusCodeException ex) {
            mv.addObject("statusCode", ex.getStatusCode().value());
        } else if (t instanceof CustomException ex) {
            mv.addObject("statusCode", ex.getStatusCode());
        } else {
            mv.addObject("statusCode", "No Response");
        }
        return mv;
    }

}
