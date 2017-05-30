/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.converter.oth2;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

public class AuthCustomUserTokenConverter extends
                                         DefaultUserAuthenticationConverter {

    @Override
    public Map<String, ?> convertUserAuthentication(
            Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.putAll(super.convertUserAuthentication(authentication));
        response.put("user_additional_key", "user_additional_value");
        return response;
    }

}
