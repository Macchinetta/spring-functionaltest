/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.oth2;

public interface OauthAuthCodeService {

    // For Authorization Code Grant

    /**
     * <ul>
     * <li>Sends request with the authorization code grant(GET).</li>
     * </ul>
     * @param testId
     * @return OauthResource
     */
    OauthResource getAuthCodeGrantResource(String testId);

    /**
     * <ul>
     * <li>Sends request with the authorization code grant(POST).</li>
     * </ul>
     * @param testId
     * @return OauthResource
     */
    OauthResource postAuthCodeGrantResource(String testId);

    /**
     * <ul>
     * <li>Sends request with the authorization code grant(PUT).</li>
     * </ul>
     * @param testId
     */
    void putAuthCodeGrantResource(String testId);

    /**
     * <ul>
     * <li>Sends request with the authorization code grant(DELETE).</li>
     * </ul>
     * @param testId
     */
    void deleteAuthCodeGrantResource(String testId);

    /**
     * <ul>
     * <li>Sends request with the authorization code grant(DELETE:not registered in client).</li>
     * </ul>
     * @param testId
     */
    void deleteNotRegistAuthCodeGrantResource(String testId);

    /**
     * <ul>
     * <li>Sends request with the authorization code grant(GET).<br/>
     * Scope parameter contains "READ" and "DELETE".</li>
     * </ul>
     * @param testId
     * @return OauthResource
     */
    OauthResource getAuthCodeGrantResourceByScope(String testId);

    /**
     * <ul>
     * <li>Get token value from authCodeGrantOperations.</li>
     * </ul>
     * @return token value
     */
    String getAuthCodeTokenValue();

    /**
     * <ul>
     * <li>Get token value from authCodeGrantScopeOperations.</li>
     * </ul>
     * @return token value
     */
    String getAuthCodeByScopeTokenValue();
}
