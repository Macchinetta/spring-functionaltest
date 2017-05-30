/*
 * Copyright(c) 2014-2017 NTT Corporation.
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
