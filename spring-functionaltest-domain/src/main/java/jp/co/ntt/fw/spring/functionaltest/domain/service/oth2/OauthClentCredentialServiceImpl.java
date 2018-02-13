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
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestOperations;

@Service
@Transactional
public class OauthClentCredentialServiceImpl implements
                                             OauthClentCredentialService {

    private static final Logger logger = LoggerFactory.getLogger(
            OauthClentCredentialServiceImpl.class);

    @Value("${oth2.databaseApplicationContextUrl}${oth2.restServletPath}/oth2/resources")
    String resourceServerDbUrl;

    @Inject
    @Named("clientCredentialRestTemplate")
    OAuth2RestOperations clientCredentialGrantOperations;

    @Inject
    @Named("clientCredentialReadOnlyRestTemplate")
    OAuth2RestOperations clientCredentialGrantReadOnlyOperations;

    @Override
    public OauthResource getClientCredentialGrantResource(String testId) {

        final String url = resourceServerDbUrl + "/default/{id}";
        logger.debug("RestOperation url={}, parameter={}", url, testId);

        OauthResource result = clientCredentialGrantOperations.getForObject(url,
                OauthResource.class, testId);
        return result;
    }

    @Override
    public OauthResource postClientCredentialGrantResource(String testId) {

        final String url = resourceServerDbUrl + "/principal/";
        logger.debug("RestOperation url={}, parameter={}", url, testId);

        OauthResource resource = new OauthResource(testId, "POST");
        OauthResource result = clientCredentialGrantOperations.postForObject(
                url, resource, OauthResource.class);
        return result;
    }

    @Override
    public void putClientCredentialGrantResource(String testId) {

        final String url = resourceServerDbUrl + "/default/{id}";
        logger.debug("RestOperation url={}, parameter={}", url, testId);

        clientCredentialGrantOperations.put(url, OauthResource.class, testId);
        return;
    }

    @Override
    public void deleteClientCredentialGrantResource(String testId) {

        final String url = resourceServerDbUrl + "/default/{id}";
        logger.debug("RestOperation url={}, parameter={}", url, testId);

        clientCredentialGrantOperations.delete(url, testId);
        return;
    }

    @Override
    public void deleteNotRegistClientCredentialGrantResource(String testId) {
        final String url = resourceServerDbUrl + "/default/{id}";
        logger.debug("RestOperation url={}, parameter={}", url, testId);

        clientCredentialGrantReadOnlyOperations.delete(url, testId);
        return;
    }

    @Override
    public String getClientCredentialTokenValue() {
        return getTokenValue(clientCredentialGrantOperations);
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
