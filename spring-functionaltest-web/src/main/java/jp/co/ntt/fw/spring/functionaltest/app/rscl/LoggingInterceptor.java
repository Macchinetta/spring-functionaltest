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
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * <ul>
 * ロギング用インターセプタ
 * </ul>
 */
public class LoggingInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(
            LoggingInterceptor.class);

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
