/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.converter.oth2;

import java.util.Map;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;

public class AuthCustomAccessTokenConverter extends DefaultAccessTokenConverter {

    @Override
    public Map<String, ?> convertAccessToken(OAuth2AccessToken token,
            OAuth2Authentication authentication) {

        @SuppressWarnings("unchecked")
        Map<String, Object> response = (Map<String, Object>) super
                .convertAccessToken(token, authentication);
        response.put("client_additional_key", "client_additional_value");
        return response;

    }

}
