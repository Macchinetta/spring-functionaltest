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
package jp.co.ntt.fw.spring.functionaltest.domain.service.interceptor;

import java.io.IOException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.service.todo.OAuth2TokenService;

public class RestTemplateAccessTokenInterceptor implements ClientHttpRequestInterceptor {

    private String registrationId;

    private boolean changed = false;

    @Inject
    OAuth2TokenService oAuth2TokenService;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        OAuth2AccessToken accessToken = this.oAuth2TokenService.getToken(this.registrationId);
        String tokenValue =
                changed ? accessToken.getTokenValue() + "dummy" : accessToken.getTokenValue();

        request.getHeaders().setBearerAuth(tokenValue);

        return execution.execute(request, body);
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

}
