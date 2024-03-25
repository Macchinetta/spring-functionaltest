/*
 * Copyright(c) 2024 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.domain.service.athn;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

public class CompanyIdUsernamePasswordAuthenticationToken extends
                                                          UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final String companyId;

    public CompanyIdUsernamePasswordAuthenticationToken(Object principal,
            Object credentials, String companyId) {
        super(principal, credentials);

        this.companyId = companyId;
    }

    public CompanyIdUsernamePasswordAuthenticationToken(Object principal,
            Object credentials, String companyId,
            Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.companyId = companyId;
    }

    public String getCompanyId() {
        return companyId;
    }

}
