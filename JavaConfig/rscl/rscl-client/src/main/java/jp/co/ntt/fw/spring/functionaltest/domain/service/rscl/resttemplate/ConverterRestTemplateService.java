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
 * Converterの確認用のServiceインターフェース。
 */
public interface ConverterRestTemplateService {

    /**
     * ByteArrayHttpMessageConverterが適用されることを確認する。
     * 
     * @param message 送信メッセージ
     * @return 受信メッセージリスト
     */
    List<String> exchangeWithByteArrayHttpMessageConverter(String message);

    /**
     * StringHttpMessageConverterが適用されることを確認する。
     * 
     * @param message 送信メッセージ
     * @return 受信メッセージ
     */
    String exchangeWithStringHttpMessageConverter(String message);

    /**
     * ResourceHttpMessageConverterが適用されることを確認する。
     * 
     * @param message 送信メッセージ
     * @return 受信メッセージリスト
     */
    List<String> exchangeWithResourceHttpMessageConverter(String message);

    /**
     * SourceHttpMessageConverterが適用されることを確認する。
     * 
     * @param user 送信ユーザ情報
     * @return 受信ユーザ情報
     */
    User exchangeWithSourceHttpMessageConverter(User user);

    /**
     * AllEncompassingFormHttpMessageConverterが適用されることを確認する。
     * application/x-www-form-urlencoded対応
     * 
     * @param user 送信ユーザ情報
     * @return 受信ユーザ情報
     */
    User exchangeApplicationWithAllEncompassingFormHttpMessageConverter(User user);

    /**
     * AllEncompassingFormHttpMessageConverterが適用されることを確認する。
     * multipart/form-data対応
     * 
     * @param user 送信ユーザ情報
     * @return 受信ユーザ情報
     */
    User exchangeMultipartWithAllEncompassingFormHttpMessageConverter(User user);

    /**
     * MappingJackson2HttpMessageConverterが適用されることを確認する。
     * 
     * @param user 送信ユーザ情報
     * @return 受信ユーザ情報
     */
    User exchangeWithMappingJackson2HttpMessageConverter(User user);

    /**
     * Jaxb2RootElementHttpMessageConverterが適用されることを確認する。
     * 
     * @param user 送信ユーザ情報
     * @return 受信ユーザ情報
     */
    User exchangeWithJaxb2RootElementHttpMessageConverter(User user);
}