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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception.CustomException;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.Message;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;
import reactor.core.publisher.Mono;

@Service
public class ErrorHandleWebClientServiceImpl implements ErrorHandleWebClientService {

    private static final Logger logger = LoggerFactory.getLogger(ErrorHandleWebClientServiceImpl.class);

    @Value("${rscl.applicationContextUrl}/api/v1/async")
    private String uri;

    @Inject
    private WebClient webClient;

    @Inject
    private WebClient statusWebClient;

    @Inject
    private WebClient errorWebClient;

    @Override
    public CompletableFuture<ResponseEntity<User>> exception(int statusCode) {
        logger.info("Start Request Thread: {}", Thread.currentThread().getName());
        return webClient.get().uri(uri + "/exception/{status}", statusCode).retrieve()
                .toEntity(User.class).toFuture();
    }

    @Override
    public CompletableFuture<ResponseEntity<Message>> streamException() {
        logger.info("Start Request Thread: {}", Thread.currentThread().getName());
        return errorWebClient.get().uri(uri + "/largeData").retrieve()
                .toEntity(Message.class)
                .toFuture();
    }

    @Override
    public CompletableFuture<ResponseEntity<User>> handleException(int statusCode) {
        logger.info("Start Request Thread: {}", Thread.currentThread().getName());
        return statusWebClient.get().uri(uri + "/exception/{status}", statusCode).retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, response -> {
                    logger.error("StatusHandler StatusCode:{}", response.statusCode().value());
                    return Mono.error(new CustomException("StatusHandler StatusCode:" + response.statusCode().value(),
                            response.headers().toString(), response.statusCode().value()));
                })
                .toEntity(User.class).toFuture();

    }

}