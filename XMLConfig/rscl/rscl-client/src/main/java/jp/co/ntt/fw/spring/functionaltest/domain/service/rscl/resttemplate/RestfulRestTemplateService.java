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
 * URLテンプレートと埋め込みパラメータを使ったURL文字列の生成を確認するServiceインターフェース。
 */
public interface RestfulRestTemplateService {

    /**
     * getForObjectの確認用
     * 
     * @return 受信メッセージ
     */
    User getForObject();

    /**
     * exchangeの確認用
     * 
     * @param user 送信メッセージ
     * @return 受信メッセージ
     */
    User exchange(User user);
}