/*
 * Copyright(c) 2014-2017 NTT Corporation.
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

    private static final Logger logger = LoggerFactory
            .getLogger(OauthInitContextTokenServiceImpl.class);

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
