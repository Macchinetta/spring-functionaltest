/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.app.rscl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.AsyncClientHttpRequestExecution;
import org.springframework.http.client.AsyncClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * <ul>
 * ロギング用インターセプタ（AsyncRestTemplate用）
 * </ul>
 */
public class AsyncLoggingInterceptor implements
                                     AsyncClientHttpRequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(
            AsyncLoggingInterceptor.class);

    @Override
    public ListenableFuture<ClientHttpResponse> intercept(HttpRequest request,
            byte[] body,
            AsyncClientHttpRequestExecution execution) throws IOException {
        logger.info("AsyncLoggingInterceptor Called!");

        if (logger.isInfoEnabled()) {
            String requestBody = new String(body, StandardCharsets.UTF_8);

            logger.info("Request Header {}", request.getHeaders());
            logger.info("Request Body {}", requestBody);
        }

        // 次の処理起動
        ListenableFuture<ClientHttpResponse> future = execution.executeAsync(
                request, body);

        if (logger.isInfoEnabled()) {
            future.addCallback(
                    new ListenableFutureCallback<ClientHttpResponse>() {

                        @Override
                        public void onSuccess(ClientHttpResponse response) {
                            logger.info("onSuccess Called!");
                            try {
                                logger.info("Response Header {}", response
                                        .getHeaders());
                                logger.info("Response Status Code {}", response
                                        .getStatusCode());
                            } catch (IOException e) {
                                logger.warn("I/O Error", e);
                            }
                        }

                        @Override
                        public void onFailure(Throwable e) {
                            logger.info("onFailure Called!");
                            logger.info("Communication Error", e);
                        }
                    });
        }

        return future;
    }
}
