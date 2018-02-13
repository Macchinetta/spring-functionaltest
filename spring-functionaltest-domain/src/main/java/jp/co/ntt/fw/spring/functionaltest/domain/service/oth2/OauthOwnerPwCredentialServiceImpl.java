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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestOperations;

@Service
@Transactional
public class OauthOwnerPwCredentialServiceImpl implements
                                               OauthOwnerPwCredentialService {

    private static final Logger logger = LoggerFactory.getLogger(
            OauthOwnerPwCredentialServiceImpl.class);

    @Value("${oth2.databaseApplicationContextUrl}${oth2.restServletPath}/oth2/resources")
    String resourceServerDbUrl;

    @Inject
    @Named("passwordRestTemplate")
    OAuth2RestOperations ownerCredentialGrantOperations;

    @Inject
    @Named("passwordReadOnlyRestTemplate")
    // passwordReadOnlyRestTemplate
    OAuth2RestOperations ownerCredentialGrantReadOnlyOperations;

    @Override
    public OauthResource getOwnerCredentialGrantResource(String testId) {
        return getResource(resourceServerDbUrl, ownerCredentialGrantOperations,
                testId);
    }

    @Override
    public OauthResource postOwnerCredentialGrantResource(String testId) {
        return postResource(resourceServerDbUrl, ownerCredentialGrantOperations,
                testId);
    }

    @Override
    public void putOwnerCredentialGrantResource(String testId) {
        putResource(resourceServerDbUrl, ownerCredentialGrantOperations,
                testId);
    }

    @Override
    public void deleteOwnerCredentialGrantResource(String testId) {
        deleteResource(resourceServerDbUrl, ownerCredentialGrantOperations,
                testId);
    }

    @Override
    public void deleteNotRegistOwnerCredentialGrantResource(String testId) {
        deleteResource(resourceServerDbUrl,
                ownerCredentialGrantReadOnlyOperations, testId);
    }

    @Override
    public String getOwnerCredentialTokenValue() {
        return getTokenValue(ownerCredentialGrantOperations);
    }

    private OauthResource getResource(String url, RestOperations restOperations,
            String testId) {
        final String resourceServerUrl = url + "/principal/{id}";
        logger.debug("RestOperation url={}, parameter={}", resourceServerUrl,
                testId);

        OauthResource result = restOperations.getForObject(resourceServerUrl,
                OauthResource.class, testId);
        return result;
    }

    private OauthResource postResource(String url,
            RestOperations restOperations, String testId) {
        final String resourceServerUrl = url + "/principal/{id}";
        logger.debug("RestOperation url={}, parameter={}", resourceServerUrl,
                testId);

        OauthResource resource = new OauthResource(testId, "POST");
        OauthResource result = restOperations.postForObject(resourceServerUrl,
                resource, OauthResource.class, testId);
        return result;
    }

    private void putResource(String url, RestOperations restOperations,
            String testId) {
        final String resourceServerUrl = url + "/principal/{id}";
        logger.debug("RestOperation url={}, parameter={}", resourceServerUrl,
                testId);

        restOperations.put(resourceServerUrl, OauthResource.class, testId);
        return;
    }

    private void deleteResource(String url, RestOperations restOperations,
            String testId) {
        final String resourceServerUrl = url + "/principal/{id}";
        logger.debug("RestOperation url={}, parameter={}", resourceServerUrl,
                testId);

        restOperations.delete(resourceServerUrl, testId);
        return;
    }

    private String getTokenValue(OAuth2RestOperations restOperations) {
        String tokenValue = "";
        OAuth2AccessToken token = restOperations.getAccessToken();
        if (token != null) {
            tokenValue = token.getValue();
        }
        return tokenValue;
    }

}
