/*
 * Copyright(c) 2014-2017 NTT Corporation.
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
    private static final Logger logger = LoggerFactory
            .getLogger(AsyncLoggingInterceptor.class);

    @Override
    public ListenableFuture<ClientHttpResponse> intercept(HttpRequest request,
            byte[] body, AsyncClientHttpRequestExecution execution) throws IOException {
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
            future.addCallback(new ListenableFutureCallback<ClientHttpResponse>() {

                @Override
                public void onSuccess(ClientHttpResponse response) {
                    logger.info("onSuccess Called!");
                    try {
                        logger.info("Response Header {}", response.getHeaders());
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
