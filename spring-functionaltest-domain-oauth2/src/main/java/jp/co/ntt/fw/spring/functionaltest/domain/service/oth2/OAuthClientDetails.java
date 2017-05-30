/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.oth2;

import jp.co.ntt.fw.spring.functionaltest.domain.model.OAuthClient;

import org.springframework.security.oauth2.provider.client.BaseClientDetails;

public class OAuthClientDetails extends BaseClientDetails {

    private static final long serialVersionUID = 1L;

    private OAuthClient oauthClient;

    public void setOauthClient(OAuthClient oauthclient) {
        this.oauthClient = oauthclient;
    }

    public OAuthClient getOauthClientlient() {
        return this.oauthClient;
    }

}
