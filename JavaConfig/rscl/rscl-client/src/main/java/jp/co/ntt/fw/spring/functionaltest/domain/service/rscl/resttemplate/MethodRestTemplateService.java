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

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;

/**
 * Httpメソッドの確認用のServiceインターフェース。
 */
public interface MethodRestTemplateService {

    /**
     * GETメソッドでアクセスする。
     * 
     * @return 受信メッセージ
     */
    User get();

    /**
     * 複数件取得の確認用。
     * 
     * @return 受信メッセージリスト
     */
    List<User> getList();

    /**
     * POSTメソッドでアクセスする。
     * 
     * @param user 送信メッセージ
     * @return 受信メッセージ
     */
    User post(User user);

    /**
     * PUTメソッドでアクセスする。
     * 
     * @param user 送信メッセージ
     * @return 受信メッセージ
     */
    User put(User user);

    /**
     * PATCHメソッドでアクセスする。
     * 
     * @param user 送信メッセージ
     * @return 受信メッセージ
     */
    User patch(User user);

    /**
     * DELETEメソッドでアクセスする。
     */
    void delete();

    /**
     * HEADメソッドでアクセスする。
     */
    void head();
}