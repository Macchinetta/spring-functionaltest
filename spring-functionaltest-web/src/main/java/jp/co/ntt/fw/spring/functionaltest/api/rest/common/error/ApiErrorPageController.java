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
package jp.co.ntt.fw.spring.functionaltest.api.rest.common.error;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

// エラー応答を行うためのControllerクラス
@RequestMapping("error")
@RestController
public class ApiErrorPageController {

    @Inject
    ApiErrorCreator apiErrorCreator; // エラー情報を作成するクラス

    private final Map<HttpStatus, String> errorCodeMap = new HashMap<HttpStatus, String>();

    public ApiErrorPageController() {
        errorCodeMap.put(HttpStatus.SERVICE_UNAVAILABLE, "e.sf.cmmn.9503");
    }

    // エラー応答を行う処理メソッド
    @RequestMapping
    public ResponseEntity<ApiError> handleErrorPage(WebRequest request) {
        // リクエストスコープに格納されているステータスコードを取得
        HttpStatus httpStatus = HttpStatus.valueOf((Integer) request
                .getAttribute(RequestDispatcher.ERROR_STATUS_CODE,
                        RequestAttributes.SCOPE_REQUEST));

        // ステータスコード毎のエラーコードを取得
        String errorCode = errorCodeMap.get(httpStatus);

        // リクエストパラメータで受け取ったエラーコードに対応するエラー情報を生成
        ApiError apiError = apiErrorCreator.createApiError(request, errorCode,
                httpStatus.getReasonPhrase());
        // 取得したエラー情報を応答
        return ResponseEntity.status(httpStatus).contentType(
                MediaType.APPLICATION_JSON).body(apiError);
    }

}
