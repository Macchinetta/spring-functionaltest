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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.Result;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;

@Service
public class EntryWebClientServiceImpl implements EntryWebClientService {

    private static final Logger logger = LoggerFactory.getLogger(EntryWebClientServiceImpl.class);

    @Value("${rscl.applicationContextUrl}/api/v1/async")
    private String uri;

    @Inject
    private WebClient webClient;

    @Override
    public CompletableFuture<ResponseEntity<Result>> createAsync() {

        String url = uri + "/bulk";
        List<User> users = createUsers();

        logger.info("Start Request Thread: {}", Thread.currentThread().getName());
        CompletableFuture<ResponseEntity<Result>> future = webClient.post()
                .uri(url)
                .bodyValue(users)
                .retrieve()
                .toEntity(Result.class)
                .toFuture();

        return future;
    }

    @Override
    public CompletableFuture<ResponseEntity<Result>> createSync() {
        String url = uri + "/bulk";
        List<User> users = createUsers();

        logger.info("Start Request Thread: {}", Thread.currentThread().getName());
        CompletableFuture<ResponseEntity<Result>> future = webClient.post()
                .uri(url)
                .bodyValue(users)
                .retrieve()
                .toEntity(Result.class)
                .toFuture();

        return future;
    }

    // ユーザリスト作成
    private List<User> createUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            User user = new User();
            user.setName(String.format("test%04d", i));
            user.setAge(20 + i);
            users.add(user);
        }
        return users;
    }

}