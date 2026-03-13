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
package jp.co.ntt.fw.spring.functionaltest.app.cmmn.model;

import java.io.Serializable;

/**
 * <ul>
 * <li>UserInfFormのクラス。</li> <br>
 * </ul>
 */
public class ResponseInfo implements Serializable {

    /**
     * シリアルID
     */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     */
    public ResponseInfo() {
    }

    /**
     * コンストラクタ
     */
    public ResponseInfo(int httpStatusCode, String responseHeader) {
        this.httpStatusCode = httpStatusCode;
        this.responseHeader = responseHeader;
        this.responseBody = null;
    }

    /**
     * コンストラクタ
     */
    public ResponseInfo(int httpStatusCode, String responseHeader, String responseBody) {
        this.httpStatusCode = httpStatusCode;
        this.responseHeader = responseHeader;
        this.responseBody = responseBody;
    }

    /**
     * Httpステータスコード
     */
    private int httpStatusCode;

    /**
     * レスポンスヘッダ
     */
    private String responseHeader;

    /**
     * レスポンスボディ
     */
    private String responseBody;

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(String responseHeader) {
        this.responseHeader = responseHeader;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}
