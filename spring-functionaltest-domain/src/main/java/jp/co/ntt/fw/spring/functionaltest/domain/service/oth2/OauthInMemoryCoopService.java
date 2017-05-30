/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.oth2;

public interface OauthInMemoryCoopService {

    /**
     * <ul>
     * <li>Sends request with the authorization code grant(GET).<br/>
     * Accessing resource server is using InMemoryTokenStore.</li>
     * </ul>
     * @param testId
     * @return OauthResource
     */
    OauthResource getResource(String testId);

    /**
     * <ul>
     * <li>Sends request with the authorization code grant(POST).<br/>
     * Accessing resource server is using InMemoryTokenStore.</li>
     * </ul>
     * @param testId
     * @return OauthResource
     */
    OauthResource postResource(String testId);

    /**
     * <ul>
     * <li>Sends request with the authorization code grant(PUT).<br/>
     * Accessing resource server is using InMemoryTokenStore.</li>
     * </ul>
     * @param testId
     */
    void putResource(String testId);

    /**
     * <ul>
     * <li>Sends request with the authorization code grant(DELETE).<br/>
     * Accessing resource server is using InMemoryTokenStore.</li>
     * </ul>
     * @param testId
     */
    void deleteResource(String testId);

    /**
     * <ul>
     * <li>Get token value from RestOperations.</li>
     * </ul>
     * @return token value
     */
    String getTokenValue();
}
