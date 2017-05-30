/*
 * Copyright(c) 2014-2017 NTT Corporation.
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
public class OauthAuthCodeServiceImpl implements OauthAuthCodeService {

    private static final Logger logger = LoggerFactory
            .getLogger(OauthAuthCodeServiceImpl.class);

    @Value("${oth2.databaseApplicationContextUrl}${oth2.restServletPath}/oth2/resources")
    String resourceServerDbUrl;

    @Inject
    @Named("authorizationCodeRestTemplate")
    OAuth2RestOperations authCodeGrantOperations;

    @Inject
    @Named("authorizationCodeScopeRestTemplate")
    OAuth2RestOperations authCodeGrantScopeOperations;

    @Inject
    @Named("authorizationCodeReadOnlyRestTemplate")
    OAuth2RestOperations authCodeGrantReadOnlyOperations;

    @Override
    public OauthResource getAuthCodeGrantResource(String testId) {

        return getResource(resourceServerDbUrl, authCodeGrantOperations, testId);
    }

    @Override
    public OauthResource postAuthCodeGrantResource(String testId) {
        return postResource(resourceServerDbUrl, authCodeGrantOperations,
                testId);
    }

    @Override
    public void putAuthCodeGrantResource(String testId) {
        putResource(resourceServerDbUrl, authCodeGrantOperations, testId);
    }

    @Override
    public void deleteAuthCodeGrantResource(String testId) {
        deleteResource(resourceServerDbUrl, authCodeGrantOperations, testId);
    }

    @Override
    public void deleteNotRegistAuthCodeGrantResource(String testId) {
        deleteResource(resourceServerDbUrl, authCodeGrantReadOnlyOperations,
                testId);
    }

    @Override
    public OauthResource getAuthCodeGrantResourceByScope(String testId) {
        return getResource(resourceServerDbUrl, authCodeGrantScopeOperations,
                testId);
    }

    @Override
    public String getAuthCodeTokenValue() {
        return getTokenValue(authCodeGrantOperations);
    }

    @Override
    public String getAuthCodeByScopeTokenValue() {
        return getTokenValue(authCodeGrantScopeOperations);
    }

    private OauthResource getResource(String url,
            RestOperations restOperations, String testId) {
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
