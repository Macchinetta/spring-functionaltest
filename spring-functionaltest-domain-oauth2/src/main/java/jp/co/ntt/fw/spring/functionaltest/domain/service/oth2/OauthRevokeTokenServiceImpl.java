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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.Approval.ApprovalStatus;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.date.jodatime.JodaTimeDateFactory;

@Service
@Transactional
public class OauthRevokeTokenServiceImpl implements OauthRevokeTokenService {

    @Inject
    @Named("oauth2TokenServices")
    ConsumerTokenServices consumerService;

    @Inject
    @Named("oauth2TokenStore")
    TokenStore tokenStore;

    @Inject
    ApprovalStore approvalStore;

    @Inject
    JodaTimeDateFactory dateFactory;

    public void revokeTokenAndApprovals(String tokenValue, String clientId) {

        OAuth2Authentication authentication = tokenStore.readAuthentication(
                tokenValue);

        Date date = dateFactory.newDate();

        if (authentication == null) {
            return;
        }

        if (!clientId.equals(authentication.getOAuth2Request().getClientId())) {
            throw new InvalidRequestException(null);
        }

        Authentication user = authentication.getUserAuthentication();
        if (user != null) {
            Collection<Approval> approvals = new ArrayList<>();
            for (String scope : authentication.getOAuth2Request().getScope()) {
                approvals.add(new Approval(user
                        .getName(), clientId, scope, date, ApprovalStatus.APPROVED));
            }
            approvalStore.revokeApprovals(approvals);
        }
        consumerService.revokeToken(tokenValue);
    }

    public void revokeToken(String tokenValue, String clientId) {

        OAuth2Authentication authentication = tokenStore.readAuthentication(
                tokenValue);

        if (authentication == null) {
            return;
        }

        if (clientId.equals(authentication.getOAuth2Request().getClientId())) {
            consumerService.revokeToken(tokenValue);
        } else {
            throw new InvalidRequestException(null);
        }

    }

}
