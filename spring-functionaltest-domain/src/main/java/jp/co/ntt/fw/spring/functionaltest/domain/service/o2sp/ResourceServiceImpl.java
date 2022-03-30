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
package jp.co.ntt.fw.spring.functionaltest.domain.service.o2sp;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Inject
    private RestTemplate restOperations;

    @Inject
    private OAuth2TokenService oAuth2TokenService;

    @Value("${messages.base-uri}")
    private String messagesBaseUri;

    @Override
    public String getRequestUsingInterceptUrl(String registrationId) {
        OAuth2AccessToken accessToken = this.oAuth2TokenService.getToken(
                registrationId);

        // @formatter:off
        RequestEntity<Void> requestEntity = RequestEntity
                .get(this.messagesBaseUri + "/intercept")
                .headers(httpHeaders -> httpHeaders.setBearerAuth(accessToken.getTokenValue()))
                .build();
        // @formatter:on

        ResponseEntity<String> responseEntity = this.restOperations.exchange(
                requestEntity, String.class);
        String message = responseEntity.getBody();

        return message;
    }

    @Override
    public String putRequestUsingInterceptUrl(String registrationId) {
        OAuth2AccessToken accessToken = this.oAuth2TokenService.getToken(
                registrationId);

        // @formatter:off
        RequestEntity<Void> requestEntity = RequestEntity
                .put(this.messagesBaseUri + "/intercept")
                .headers(httpHeaders -> httpHeaders.setBearerAuth(accessToken.getTokenValue()))
                .build();
        // @formatter:on

        ResponseEntity<String> responseEntity = this.restOperations.exchange(
                requestEntity, String.class);
        String message = responseEntity.getBody();

        return message;
    }

    @Override
    public String postRequestUsingInterceptUrl(String registrationId) {
        OAuth2AccessToken accessToken = this.oAuth2TokenService.getToken(
                registrationId);

        // @formatter:off
        RequestEntity<Void> requestEntity = RequestEntity
                .post(this.messagesBaseUri + "/intercept")
                .headers(httpHeaders -> httpHeaders.setBearerAuth(accessToken.getTokenValue()))
                .build();
        // @formatter:on

        ResponseEntity<String> responseEntity = this.restOperations.exchange(
                requestEntity, String.class);
        String message = responseEntity.getBody();

        return message;
    }

    @Override
    public String deleteRequestUsingInterceptUrl(String registrationId) {
        OAuth2AccessToken accessToken = this.oAuth2TokenService.getToken(
                registrationId);

        // @formatter:off
        RequestEntity<Void> requestEntity = RequestEntity
                .delete(this.messagesBaseUri + "/intercept")
                .headers(httpHeaders -> httpHeaders.setBearerAuth(accessToken.getTokenValue()))
                .build();
        // @formatter:on

        ResponseEntity<String> responseEntity = this.restOperations.exchange(
                requestEntity, String.class);
        String message = responseEntity.getBody();

        return message;
    }

    @Override
    public String getRequestUsingPreAuthorize(String registrationId) {
        OAuth2AccessToken accessToken = this.oAuth2TokenService.getToken(
                registrationId);

        // @formatter:off
        RequestEntity<Void> requestEntity = RequestEntity
                .get(this.messagesBaseUri + "/annotation")
                .headers(httpHeaders -> httpHeaders.setBearerAuth(accessToken.getTokenValue()))
                .build();
        // @formatter:on

        ResponseEntity<String> responseEntity = this.restOperations.exchange(
                requestEntity, String.class);
        String message = responseEntity.getBody();

        return message;
    }

    @Override
    public String putRequestUsingPreAuthorize(String registrationId) {
        OAuth2AccessToken accessToken = this.oAuth2TokenService.getToken(
                registrationId);

        // @formatter:off
        RequestEntity<Void> requestEntity = RequestEntity
                .put(this.messagesBaseUri + "/annotation")
                .headers(httpHeaders -> httpHeaders.setBearerAuth(accessToken.getTokenValue()))
                .build();
        // @formatter:on

        ResponseEntity<String> responseEntity = this.restOperations.exchange(
                requestEntity, String.class);
        String message = responseEntity.getBody();

        return message;
    }

    @Override
    public String postRequestUsingPreAuthorize(String registrationId) {
        OAuth2AccessToken accessToken = this.oAuth2TokenService.getToken(
                registrationId);

        // @formatter:off
        RequestEntity<Void> requestEntity = RequestEntity
                .post(this.messagesBaseUri + "/annotation")
                .headers(httpHeaders -> httpHeaders.setBearerAuth(accessToken.getTokenValue()))
                .build();
        // @formatter:on

        ResponseEntity<String> responseEntity = this.restOperations.exchange(
                requestEntity, String.class);
        String message = responseEntity.getBody();

        return message;
    }

    @Override
    public String deleteRequestUsingPreAuthorize(String registrationId) {
        OAuth2AccessToken accessToken = this.oAuth2TokenService.getToken(
                registrationId);

        // @formatter:off
        RequestEntity<Void> requestEntity = RequestEntity
                .delete(this.messagesBaseUri + "/annotation")
                .headers(httpHeaders -> httpHeaders.setBearerAuth(accessToken.getTokenValue()))
                .build();
        // @formatter:on

        ResponseEntity<String> responseEntity = this.restOperations.exchange(
                requestEntity, String.class);
        String message = responseEntity.getBody();

        return message;
    }

}
