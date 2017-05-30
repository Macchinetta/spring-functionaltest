/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.oth2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.Approval.ApprovalStatus;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public String revokeToken(String tokenValue, String clientId) {

        OAuth2Authentication authentication = tokenStore
                .readAuthentication(tokenValue);

        if (authentication != null) {

            if (clientId
                    .equals(authentication.getOAuth2Request().getClientId())) {

                Authentication user = authentication.getUserAuthentication();
                if (user != null) {
                    Collection<Approval> approvals = new ArrayList<Approval>();
                    for (String scope : authentication.getOAuth2Request()
                            .getScope()) {
                        approvals
                                .add(new Approval(user.getName(), clientId, scope, new Date(), ApprovalStatus.APPROVED));
                    }
                    approvalStore.revokeApprovals(approvals);
                }
                consumerService.revokeToken(tokenValue);
                return "success";

            } else {
                return "invalid client";
            }
        } else {
            return "invalid token";
        }

    }
}
