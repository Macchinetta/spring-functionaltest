/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.oth2;

import java.util.Set;

import jp.co.ntt.fw.spring.functionaltest.domain.model.OAuthClient;

public interface OAuthClientRepository {

    OAuthClient findClientByClientId(String clientId);

    Set<String> findClientScopesByClientId(String clientId);

    Set<String> findClientResourcesByClientId(String clientId);

    Set<String> findClientGrantsByClientId(String clientId);

    Set<String> findClientRedirectUrisByClientId(String clientId);

}
