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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.ClientAuthorizationRequiredException;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthorizationException;
import org.springframework.stereotype.Service;

@Service
public class OAuth2TokenServiceImpl implements OAuth2TokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            OAuth2TokenServiceImpl.class);

    @Inject
    private OAuth2AuthorizedClientManager authorizedClientManager;

    public OAuth2AccessToken getToken(String registrationId) {

        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest
                .withClientRegistrationId(registrationId).principal(
                        authentication).build();

        // 認可されていない場合はClientAuthorizationRequiredExceptionをスローし、OAuth2AuthorizationRequestRedirectFilterで認可処理を行う
        OAuth2AuthorizedClient authorizedClient = null;
        try {
            authorizedClient = this.authorizedClientManager.authorize(
                    authorizeRequest);
        } catch (OAuth2AuthorizationException e) {
            if ("invalid_grant".equals(e.getError().getErrorCode())) {

                LOGGER.warn("refresh token expired. registrationId = {}",
                        registrationId);

                throw new ClientAuthorizationRequiredException(registrationId);
            }
            throw e;
        }

        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();

        if (accessToken != null) {
            LOGGER.debug(
                    "access token [Issued = {}, Expires = {}, Scopes = {}, Type = {}, Value ={}",
                    accessToken.getIssuedAt(), accessToken.getExpiresAt(),
                    accessToken.getScopes(), accessToken.getTokenType()
                            .getValue(), accessToken.getTokenValue());
        }

        return accessToken;
    }

}
