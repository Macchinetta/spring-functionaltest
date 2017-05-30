/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.oth2;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestOperations;

@Transactional
@Service
public class OauthIllegalParamAuthCodeServiceImpl implements
                                                 OauthIllegalParamAuthCodeService {

    private static final Logger logger = LoggerFactory
            .getLogger(OauthIllegalParamAuthCodeServiceImpl.class);

    @Value("${oth2.databaseApplicationContextUrl}${oth2.restServletPath}/oth2/resources")
    String url;

    @Inject
    @Named("authorizationCodeIllegalIdRestTemplate")
    RestOperations authCodeGrantIllegalIdOperations;

    @Inject
    @Named("authorizationCodeIllegalSecretRestTemplate")
    RestOperations authCodeGrantIllegalSecretOperations;

    @Inject
    @Named("authorizationCodeIllegalResourceRestTemplate")
    RestOperations authCodeGrantIllegalResourceOperations;

    @Inject
    @Named("authorizationCodeIllegalUriRestTemplate")
    RestOperations authCodeGrantIllegalUriOperations;

    @Override
    public OauthResource getResourceByIllegalId(String testId) {

        final String resourceServerUrl = url + "/principal/{id}";
        logger.debug("RestOperation url={}, parameter={}", resourceServerUrl,
                testId);

        OauthResource result = authCodeGrantIllegalIdOperations.getForObject(
                url, OauthResource.class, testId);
        return result;
    }

    @Override
    public OauthResource getResourceByIllegalSecret(String testId) {

        final String resourceServerUrl = url + "/principal/{id}";
        logger.debug("RestOperation url={}, parameter={}", resourceServerUrl,
                testId);

        OauthResource result = authCodeGrantIllegalSecretOperations
                .getForObject(url, OauthResource.class, testId);
        return result;
    }

    @Override
    public OauthResource getResourceByIllegalResourceId(String testId) {

        final String resourceServerUrl = url + "/principal/{id}";
        logger.debug("RestOperation url={}, parameter={}", resourceServerUrl,
                testId);

        OauthResource result = authCodeGrantIllegalResourceOperations
                .getForObject(url, OauthResource.class, testId);
        return result;
    }

    @Override
    public OauthResource getResourceByIllegalUri(String testId) {

        final String resourceServerUrl = url + "/principal/{id}";
        logger.debug("RestOperation url={}, parameter={}", resourceServerUrl,
                testId);

        OauthResource result = authCodeGrantIllegalUriOperations.getForObject(
                url, OauthResource.class, testId);
        return result;
    }
}
