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
package jp.co.ntt.fw.spring.functionaltest.domain.service.athn;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class CompanyIdUsernamePasswordAuthenticationProvider extends
                                                             DaoAuthenticationProvider {

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        super.additionalAuthenticationChecks(userDetails, authentication);

        CompanyIdUsernamePasswordAuthenticationToken companyIdUsernamePasswordAuthentication = (CompanyIdUsernamePasswordAuthenticationToken) authentication;
        String requestedCompanyId = companyIdUsernamePasswordAuthentication
                .getCompanyId();
        String companyId = ((ReservationUserDetails) userDetails).getCustomer()
                .getCompanyId();

        if (!companyId.equals(requestedCompanyId)) {
            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials",
                    "Bad credentials"));
        }
    }

    @Override
    protected Authentication createSuccessAuthentication(Object principal,
            Authentication authentication, UserDetails user) {
        String companyId = ((ReservationUserDetails) user).getCustomer()
                .getCompanyId();
        return new CompanyIdUsernamePasswordAuthenticationToken(user, authentication
                .getCredentials(), companyId, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CompanyIdUsernamePasswordAuthenticationToken.class
                .isAssignableFrom(authentication);
    }

}
