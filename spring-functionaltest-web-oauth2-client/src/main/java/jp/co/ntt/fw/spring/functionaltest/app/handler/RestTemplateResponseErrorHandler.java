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
package jp.co.ntt.fw.spring.functionaltest.app.handler;

import java.io.IOException;
import java.net.URI;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.terasoluna.gfw.common.exception.BusinessException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.ntt.fw.spring.functionaltest.api.common.error.ApiError;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public boolean hasError(
            ClientHttpResponse clientHttpResponse) throws IOException {
        HttpStatus status = clientHttpResponse.getStatusCode();
        return status.is4xxClientError() || status.is5xxServerError();
    }

    @Override
    public void handleError(
            ClientHttpResponse clientHttpResponse) throws IOException {
        // 409に関してはAPI側でBusinessExceptionとして扱う
        if (clientHttpResponse.getStatusCode().value() == 409) {
            throw new BusinessException(getMessage(clientHttpResponse));
        }
    }

    @Override
    public void handleError(URI url, HttpMethod method,
            ClientHttpResponse clientHttpResponse) throws IOException {
        // 409に関してはAPI側でBusinessExceptionとして扱う
        if (clientHttpResponse.getStatusCode().value() == 409) {
            throw new BusinessException(getMessage(clientHttpResponse));
        }
    }

    private String getMessage(ClientHttpResponse clientHttpResponse) {
        String message = "";
        try {
            // 本来はクライアント用に変換すべきだが、本質ではないので簡易的な措置としてそのまま出力する
            ApiError error = MAPPER.readValue(clientHttpResponse.getBody(),
                    ApiError.class);
            message = error.getMessage();
        } catch (IOException e) {
            // noop
        }

        return message;
    }

}
