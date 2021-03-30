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
package jp.co.ntt.fw.spring.functionaltest.app.aply;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception.IntentionalException;

@ControllerAdvice
@Order(3)
public class APLY04CommonControllerAdvice {

    // ExceptionHandlingを実施
    @ExceptionHandler(IntentionalException.class)
    public String handleIntentionalException(IntentionalException e) {
        // 特に設定もせずエラー画面表示（デフォルトメッセージ表示）
        return "common/error/intentionalError";
    }
}
