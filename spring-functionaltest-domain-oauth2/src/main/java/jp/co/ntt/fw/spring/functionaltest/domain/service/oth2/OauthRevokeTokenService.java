/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.oth2;

public interface OauthRevokeTokenService {

    /**
     * <ul>
     * <li>Revoke token from token store, and revoke approvals from approval store.</li>
     * </ul>
     * @param tokenValue
     * @param clientId
     * @return result
     */
    String revokeToken(String tokenValue, String clientId);

}
