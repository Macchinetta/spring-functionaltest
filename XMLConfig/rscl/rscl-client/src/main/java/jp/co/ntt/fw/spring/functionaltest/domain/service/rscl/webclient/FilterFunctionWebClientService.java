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
package jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.webclient;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;

/**
 * WebClientのExchangeFilterFunctionの確認用のServiceインターフェース。
 */
public interface FilterFunctionWebClientService {

    /**
     * 通信を行い、ログが出力されることを確認
     * 
     * @return 処理結果(User)
     */
    CompletableFuture<ResponseEntity<User>> postLogging(User user);

    /**
     * 通信を行い、リクエストヘッダが付与されていることを確認
     * 
     * @return 処理結果(User)
     */
    CompletableFuture<ResponseEntity<User>> getHeader();
}
