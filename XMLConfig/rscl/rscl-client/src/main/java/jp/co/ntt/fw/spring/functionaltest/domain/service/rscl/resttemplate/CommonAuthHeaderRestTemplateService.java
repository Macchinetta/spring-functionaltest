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
package jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.resttemplate;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;

/**
 * 認証ヘッダの確認用のServiceインターフェース。
 */
public interface CommonAuthHeaderRestTemplateService {

    /**
     * Basic認証成功を確認する
     * 
     * @return ユーザ情報
     */
    User basicAuthSuccess();

    /**
     * Basic認証失敗を確認する
     * 
     * @return ユーザ情報
     */
    User basicAuthError();

    /**
     * Basic認証未設定を確認する
     * 
     * @return ユーザ情報
     */
    User basicAuthNotSet();

}