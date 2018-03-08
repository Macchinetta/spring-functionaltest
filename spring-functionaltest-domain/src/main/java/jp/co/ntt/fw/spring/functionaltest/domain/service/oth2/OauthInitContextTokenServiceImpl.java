/*
 * Copyright 2014-2018 NTT Corporation.
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
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.oth2;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestOperations;

@Transactional
@Service
public class OauthInitContextTokenServiceImpl implements
                                              OauthInitContextTokenService {

    private static final Logger logger = LoggerFactory.getLogger(
            OauthInitContextTokenServiceImpl.class);

    @Inject
    private List<OAuth2RestTemplate> restTemplateList;

    @Override
    public void initContextTokenAll() {
        for (RestOperations restOperations : restTemplateList) {
            initContextToken(restOperations);
        }
    }

    /**
     * <ul>
     * <li>Delete context token from RestOperations.<br/>
     * </li>
     * </ul>
     * @param restOperations RestOperations
     */
    private void initContextToken(RestOperations restOperations) {

        if (restOperations instanceof OAuth2RestTemplate) {
            ((OAuth2RestTemplate) restOperations).getOAuth2ClientContext()
                    .setAccessToken(null);
        } else {
            logger.debug(
                    "Initialize context tokens failed. {} is not OAuth2RestTemplate.",
                    restOperations);
        }
    }
}
