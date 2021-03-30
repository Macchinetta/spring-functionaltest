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
package jp.co.ntt.fw.spring.functionaltest.converter.oth2;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import jp.co.ntt.fw.spring.functionaltest.domain.model.OauthUser;

public class ResourceCustomUserTokenConverter extends
                                              DefaultUserAuthenticationConverter {

    private String userClaimName = USERNAME;

    @Override
    public void setUserClaimName(String claimName) {
        this.userClaimName = claimName;
    }

    @Override
    public Map<String, ?> convertUserAuthentication(
            Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put(userClaimName, authentication.getName());
        if (authentication.getAuthorities() != null && !authentication
                .getAuthorities().isEmpty()) {
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(
                    authentication.getAuthorities()));
        }
        return response;
    }

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        if (map.containsKey(userClaimName)) {
            Collection<? extends GrantedAuthority> authorities = getAuthorities(
                    map);
            OauthUser user = new OauthUser((String) map.get(
                    userClaimName), (String) map.get("company_id"), (String) map
                            .get("business_id"), (String) map.get("client_id"));
            return new UsernamePasswordAuthenticationToken(user, "N/A", authorities);
        }
        return null;
    }

}
