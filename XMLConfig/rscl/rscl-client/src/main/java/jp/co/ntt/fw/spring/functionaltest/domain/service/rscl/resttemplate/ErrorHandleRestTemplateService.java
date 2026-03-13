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
 * エラーハンドリングの確認用のServiceインターフェース。
 */
public interface ErrorHandleRestTemplateService {

    /**
     * エラーハンドリングの確認
     * (例外発生)
     * 
     * @param statusCode HTTPステータス
     * @return ユーザ情報
     */
    User exception(int statusCode);

    /**
     * エラーハンドリングの確認
     * （ステータスコード）
     * 
     * @param statusCode HTTPステータス
     * @return ユーザ情報
     */
    User status(int statusCode);

    /**
     * エラーハンドリング(NoOp版)の確認
     * （ステータスコード）
     * 
     * @param statusCode HTTPステータス
     * @return ユーザ情報
     */
    User statusNoOpResponse(int statusCode);

    /**
     * リトライ処理の確認
     * (例外発生)
     * 
     * @param throwSuccessRecord 成功(200)を投げてくるレコード（index）
     * @return ユーザ情報
     */
    User exceptionRetry(int throwSuccessRecord);

    /**
     * リトライ処理の確認
     * （ステータスコード）
     * 
     * @param throwSuccessRecord 成功(200)を投げてくるレコード（index）
     * @return ユーザ情報
     */
    User statusRetry(int throwSuccessRecord);
}