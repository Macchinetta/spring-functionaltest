/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import org.springframework.security.oauth2.provider.client.BaseClientDetails;

public class OAuthClientDetails extends BaseClientDetails {

    private static final long serialVersionUID = 1L;

    private Client client;

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

}
