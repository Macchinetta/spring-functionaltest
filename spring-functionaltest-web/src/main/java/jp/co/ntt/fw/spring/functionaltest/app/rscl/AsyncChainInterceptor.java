/*
 * Copyright(c) 2014 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.app.rscl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.AsyncClientHttpRequestExecution;
import org.springframework.http.client.AsyncClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@SuppressWarnings("deprecation")
public class AsyncChainInterceptor implements AsyncClientHttpRequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(AsyncChainInterceptor.class);

    @Override
    public ListenableFuture<ClientHttpResponse> intercept(HttpRequest request, byte[] body,
            AsyncClientHttpRequestExecution execution) throws IOException {

        logger.info("AsyncChainInterceptor Called!");

        ListenableFuture<ClientHttpResponse> future = execution.executeAsync(request, body);

        if (logger.isInfoEnabled()) {
            future.addCallback(new ListenableFutureCallback<ClientHttpResponse>() {

                @Override
                public void onSuccess(ClientHttpResponse response) {
                    logger.info("onSuccess Called!");
                }

                @Override
                public void onFailure(Throwable e) {
                    logger.info("onFailure Called!");
                }
            });
        }

        return future;
    }

}
