/*
 * Copyright 2014-2017 NTT Corporation.
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
 *
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.oth2;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;

@Service
@Transactional
public class OauthRevokeTokenClientServiceImpl implements
                                               OauthRevokeTokenClientService {

    @Value("${oth2.databaseApplicationContextUrl}${oth2.restServletPath}/oth2/tokens/revoke_and_approvals")
    String revokeTokenAndApprovalsDbUrl;

    @Value("${oth2.databaseApplicationContextUrl}${oth2.restServletPath}/oth2/tokens/revoke")
    String revokeTokenDbUrl;

    @Value("${oth2.memoryApplicationContextUrl}${oth2.restServletPath}/oth2/tokens/revoke_and_approvals")
    String revokeTokenAndApprovalsMemoryUrl;

    @Inject
    @Named("authorizationCodeRestTemplate")
    OAuth2RestOperations authCodeGrantTokenDbOperations;

    @Inject
    @Named("authorizationCodeMemoryRestTemplate")
    OAuth2RestOperations authCodeGrantTokenMemoryOperations;

    @Inject
    @Named("revokeRestTemplate")
    RestOperations revokeTokenRestOperations;

    @Override
    public String revokeTokenAndApprovalsFromDb() {
        return revokeToken(authCodeGrantTokenDbOperations,
                revokeTokenAndApprovalsDbUrl);
    }

    @Override
    public String revokeTokenFromDb() {
        return revokeToken(authCodeGrantTokenDbOperations, revokeTokenDbUrl);
    }

    @Override
    public String revokeTokenAndApprovalsFromMemory() {
        return revokeToken(authCodeGrantTokenMemoryOperations,
                revokeTokenAndApprovalsMemoryUrl);
    }

    public String revokeToken(OAuth2RestOperations restOperation, String url) {

        String tokenValue = getTokenValue(restOperation);

        String result = "";

        if (StringUtils.hasLength(tokenValue)) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> variables = new LinkedMultiValueMap<>();
            variables.add("token", tokenValue);

            try {
                revokeTokenRestOperations.postForObject(url,
                        new HttpEntity<MultiValueMap<String, String>>(variables, headers),
                        Void.class);
                result = "success";
                initContextToken(restOperation);
            } catch (HttpClientErrorException e) {
                result = "invalid request";
            }
        } else {
            result = "token not exist";
        }
        return result;
    }

    private String getTokenValue(OAuth2RestOperations restOperations) {
        OAuth2AccessToken token = restOperations.getOAuth2ClientContext()
                .getAccessToken();
        if (token == null) {
            return "";
        }
        return token.getValue();
    }

    private void initContextToken(OAuth2RestOperations restOperations) {
        restOperations.getOAuth2ClientContext().setAccessToken(null);
    }

}
