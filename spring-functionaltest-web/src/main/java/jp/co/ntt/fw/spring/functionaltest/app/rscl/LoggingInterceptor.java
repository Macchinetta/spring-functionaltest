/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.rscl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * <ul>
 * ロギング用インターセプタ
 * </ul>
 */
public class LoggingInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger logger = LoggerFactory
            .getLogger(LoggingInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
            ClientHttpRequestExecution execution) throws IOException {
        if (logger.isInfoEnabled()) {
            String requestBody = new String(body, StandardCharsets.UTF_8);

            logger.info("Request Header {}", request.getHeaders());
            logger.info("Request Body {}", requestBody);
        }

        logger.info("LoggingInterceptor Called!");

        // 次の処理起動
        ClientHttpResponse response = execution.execute(request, body);

        if (logger.isInfoEnabled()) {
            logger.info("Response Header {}", response.getHeaders());
            logger.info("Response Status Code {}", response.getStatusCode());
        }

        return response;
    }
}
