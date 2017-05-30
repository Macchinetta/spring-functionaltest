/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.aply;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception.IntentionalException;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
