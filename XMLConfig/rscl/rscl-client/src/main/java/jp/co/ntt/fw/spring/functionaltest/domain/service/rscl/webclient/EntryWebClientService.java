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

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.Result;

/**
 * WebClientの非同期登録の確認用のServiceインターフェース。
 */
public interface EntryWebClientService {

    /**
     * データ一括登録(非同期)を行う。
     * 
     * @return 登録したユーザ一覧
     */
    CompletableFuture<ResponseEntity<Result>> createAsync();

    /**
     * データ一括登録(同期)を行う。
     * 
     * @return 登録したユーザ一覧
     */
    CompletableFuture<ResponseEntity<Result>> createSync();

}
