/*
 * Copyright(c) 2014-2017 NTT Corporation.
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

public class AsyncChainInterceptor implements AsyncClientHttpRequestInterceptor {
    private static final Logger logger = LoggerFactory
            .getLogger(AsyncChainInterceptor.class);

    @Override
    public ListenableFuture<ClientHttpResponse> intercept(HttpRequest request,
            byte[] body, AsyncClientHttpRequestExecution execution) throws IOException {

        logger.info("AsyncChainInterceptor Called!");

        ListenableFuture<ClientHttpResponse> future = execution.executeAsync(
                request, body);

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
