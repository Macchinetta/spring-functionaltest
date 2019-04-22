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

    private static final Logger logger = LoggerFactory.getLogger(
            OauthIllegalParamAuthCodeServiceImpl.class);

    @Value("${oth2.databaseApplicationContextUrl}${oth2.restServletPath}/oth2/resources")
    String url;

    @Inject
    @Named("authorizationCodeIllegalSecretRestTemplate")
    RestOperations authCodeGrantIllegalSecretOperations;

    @Inject
    @Named("authorizationCodeIllegalResourceRestTemplate")
    RestOperations authCodeGrantIllegalResourceOperations;

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

}
