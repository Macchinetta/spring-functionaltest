/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.oth2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;

@Service
@Transactional
public class OauthRevokeTokenClientServiceImpl implements
                                              OauthRevokeTokenClientService {

    @Value("${oth2.databaseApplicationContextUrl}${oth2.restServletPath}/oth2/tokens/revoke")
    String revokeTokenDbUrl;

    @Value("${oth2.memoryApplicationContextUrl}${oth2.restServletPath}/oth2/tokens/revoke")
    String revokeTokenMemoryUrl;

    @Inject
    @Named("authorizationCodeRestTemplate")
    OAuth2RestOperations authCodeGrantTokenDbOperations;

    @Inject
    @Named("authorizationCodeMemoryRestTemplate")
    OAuth2RestOperations authCodeGrantTokenMemoryOperations;

    @Inject
    @Named("revokeRestTemplate")
    RestOperations revokeTokenRestOperations;

    private static final Logger logger = LoggerFactory
            .getLogger(OauthRevokeTokenClientServiceImpl.class);

    @Override
    public String revokeTokenFromDb() {
        return revokeToken(authCodeGrantTokenDbOperations, revokeTokenDbUrl);
    }

    @Override
    public String revokeTokenFromMemory() {
        return revokeToken(authCodeGrantTokenMemoryOperations,
                revokeTokenMemoryUrl);
    }

    public String revokeToken(OAuth2RestOperations restOperation, String url) {

        String token = getTokenValue(restOperation);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> variables = new LinkedMultiValueMap<String, String>();
        variables.add("token", token);

        String result = revokeTokenRestOperations
                .postForObject(
                        url,
                        new HttpEntity<MultiValueMap<String, String>>(variables, headers),
                        String.class);

        result = removeString(result, "\\\"");

        if ("success".equals(result)) {
            initContextToken(restOperation);
        }
        return result;
    }

    private String getTokenValue(OAuth2RestOperations restOperations) {
        String tokenValue = "";
        OAuth2AccessToken token = restOperations.getAccessToken();
        if (token != null) {
            tokenValue = token.getValue();
        }
        return tokenValue;
    }

    private void initContextToken(OAuth2RestOperations restOperations) {
        restOperations.getOAuth2ClientContext().setAccessToken(null);
    }

    private String removeString(String raw, String remove) {
        Pattern p = Pattern.compile(remove);
        Matcher m = p.matcher(raw);
        String result = m.replaceAll("");
        return result;
    }

}
