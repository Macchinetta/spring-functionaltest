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
package jp.co.ntt.fw.spring.functionaltest.app.handler;

import java.io.IOException;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    private static final Logger logger =
            LoggerFactory.getLogger(RestTemplateResponseErrorHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        HttpStatusCode status = clientHttpResponse.getStatusCode();
        return status.is4xxClientError() || status.is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        // 400系に関してはAPI側でBusinessExceptionとして扱う
        if (clientHttpResponse.getStatusCode().is4xxClientError()) {
            throw new BusinessException(getMessage(clientHttpResponse));
        }
    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse clientHttpResponse)
            throws IOException {
        // 400系に関してはAPI側でBusinessExceptionとして扱う
        if (clientHttpResponse.getStatusCode().is4xxClientError()) {
            throw new BusinessException(getMessage(clientHttpResponse));
        }
    }

    private ResultMessages getMessage(ClientHttpResponse clientHttpResponse) {

        ResultMessages messages = null;

        // 試験に必要なものだけハンドリングしている
        try {
            int code = clientHttpResponse.getStatusCode().value();

            String messageId = switch (code) {
                case 403 -> "e.sf.fw.2001";
                default -> "e.sf.fw.8001";
            };

            messages = ResultMessages.error().add(messageId);

            logger.warn("HttpStatusCode:{}, Headers:{}", code, clientHttpResponse.getHeaders());
        } catch (IOException e) {
            // noop
        }

        return messages;
    }

}
