/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.oth2;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestOperations;

@Transactional
@Service
public class OauthInMemoryCoopServiceImpl implements OauthInMemoryCoopService {

    private static final Logger logger = LoggerFactory
            .getLogger(OauthInMemoryCoopServiceImpl.class);

    @Value("${oth2.memoryApplicationContextUrl}${oth2.restServletPath}/oth2/resources")
    String url;

    @Inject
    @Named("authorizationCodeMemoryRestTemplate")
    RestOperations restOperations;

    @Override
    public OauthResource getResource(String testId) {
        final String resourceServerUrl = url + "/principal/{id}";
        logger.debug("RestOperation url={}, parameter={}", resourceServerUrl,
                testId);

        OauthResource result = restOperations.getForObject(resourceServerUrl,
                OauthResource.class, testId);
        return result;
    }

    @Override
    public OauthResource postResource(String testId) {
        final String resourceServerUrl = url + "/principal/{id}";
        logger.debug("RestOperation url={}, parameter={}", resourceServerUrl,
                testId);

        OauthResource resource = new OauthResource(testId, "POST");
        OauthResource result = restOperations.postForObject(resourceServerUrl,
                resource, OauthResource.class, testId);
        return result;
    }

    @Override
    public void putResource(String testId) {
        final String resourceServerUrl = url + "/principal/{id}";
        logger.debug("RestOperation url={}, parameter={}", resourceServerUrl,
                testId);

        restOperations.put(resourceServerUrl, OauthResource.class, testId);
        return;
    }

    @Override
    public void deleteResource(String testId) {
        final String resourceServerUrl = url + "/principal/{id}";
        logger.debug("RestOperation url={}, parameter={}", resourceServerUrl,
                testId);

        restOperations.delete(resourceServerUrl, testId);
        return;
    }

    @Override
    public String getTokenValue() {
        String tokenValue = "";
        if (restOperations instanceof OAuth2RestTemplate) {
            OAuth2AccessToken token = ((OAuth2RestTemplate) restOperations)
                    .getAccessToken();
            if (token != null) {
                tokenValue = token.getValue();
            }
        } else {
            logger.debug(
                    "Get context token failed. {} is not OAuth2RestTemplate.",
                    restOperations);
        }

        return tokenValue;
    }

}
