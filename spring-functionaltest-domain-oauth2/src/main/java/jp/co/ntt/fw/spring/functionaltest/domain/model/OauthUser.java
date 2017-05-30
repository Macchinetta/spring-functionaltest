/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import java.io.Serializable;

public class OauthUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String username;

    private final String userAdditionalValue;

    private final String clientAdditionalValue;

    private final String clientId;

    public OauthUser(String username, String userAdditionalValue,
            String clientAdditionalValue, String clientId) {
        this.username = username;
        this.userAdditionalValue = userAdditionalValue;
        this.clientAdditionalValue = clientAdditionalValue;
        this.clientId = clientId;
    }

    public String getUsername() {
        return username;
    }

    public String getUserAdditionalValue() {
        return userAdditionalValue;
    }

    public String getClientAdditionalValue() {
        return clientAdditionalValue;
    }

    public String getClientId() {
        return clientId;
    }

}
