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
package jp.co.ntt.fw.spring.functionaltest.domain.service.interceptor;

import java.io.IOException;
import java.time.Duration;

import org.apache.commons.lang3.ThreadUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.service.todo.OAuth2TokenService;

public class RestTemplateSendWaitInterceptor implements
                                             ClientHttpRequestInterceptor {

    private String registrationId;

    @Inject
    OAuth2TokenService oAuth2TokenService;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        OAuth2AccessToken accessToken = this.oAuth2TokenService.getToken(
                this.registrationId);
        request.getHeaders().setBearerAuth(accessToken.getTokenValue());

        try {
            // TODO プロパティで設定できるようにした方が良いが、現時点ではべた書き
            ThreadUtils.sleep(Duration.ofSeconds(300L));
        } catch (InterruptedException e) {
            // noop
        }

        return execution.execute(request, body);
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

}
