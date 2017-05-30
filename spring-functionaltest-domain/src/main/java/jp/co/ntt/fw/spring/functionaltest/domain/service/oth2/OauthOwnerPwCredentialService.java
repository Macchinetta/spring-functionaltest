/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.oth2;

public interface OauthOwnerPwCredentialService {

    /**
     * <ul>
     * <li>Sends request with the resource owner password credentials grant(GET).</li>
     * </ul>
     * @param testId
     * @return OauthResource
     */
    OauthResource getOwnerCredentialGrantResource(String testId);

    /**
     * <ul>
     * <li>Sends request with the resource owner password credentials grant(POST).</li>
     * </ul>
     * @param testId
     * @return OauthResource
     */
    OauthResource postOwnerCredentialGrantResource(String testId);

    /**
     * <ul>
     * <li>Sends request with the resource owner password credentials grant(PUT).</li>
     * </ul>
     * @param testId
     */
    void putOwnerCredentialGrantResource(String testId);

    /**
     * <ul>
     * <li>Sends request with the resource owner password credentials grant(DELETE).</li>
     * </ul>
     * @param testId
     */
    void deleteOwnerCredentialGrantResource(String testId);

    /**
     * <ul>
     * <li>Sends request with the resource owner password credentials grant(DELETE:not registered in client).</li>
     * </ul>
     * @param testId
     */
    void deleteNotRegistOwnerCredentialGrantResource(String testId);

    /**
     * <ul>
     * <li>Get token value from ownerCredentialGrantOperations.</li>
     * </ul>
     * @return token value
     */
    String getOwnerCredentialTokenValue();

}
