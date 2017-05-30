/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.oth2;

public interface OauthClentCredentialService {

    /**
     * <ul>
     * <li>Sends request with the client credentials grant(GET).</li>
     * </ul>
     * @param testId
     * @return OauthResource
     */
    OauthResource getClientCredentialGrantResource(String testId);

    /**
     * <ul>
     * <li>Sends request with the client credentials grant(POST).</li>
     * </ul>
     * @param testId
     * @return OauthResource
     */
    OauthResource postClientCredentialGrantResource(String testId);

    /**
     * <ul>
     * <li>Sends request with the client credentials grant(PUT).</li>
     * </ul>
     * @param testId
     */
    void putClientCredentialGrantResource(String testId);

    /**
     * <ul>
     * <li>Sends request with the client credentials grant(DELETE).</li>
     * </ul>
     * @param testId
     */
    void deleteClientCredentialGrantResource(String testId);

    /**
     * <ul>
     * <li>Sends request with the client credentials grant(DELETE:not registered in client).</li>
     * </ul>
     * @param testId
     */
    void deleteNotRegistClientCredentialGrantResource(String testId);

    /**
     * <ul>
     * <li>Get token value from clientCredentialGrantOperations.</li>
     * </ul>
     * @return token value
     */
    String getClientCredentialTokenValue();

}
