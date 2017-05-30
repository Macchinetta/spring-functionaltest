/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.oth2;

public interface OauthIllegalParamAuthCodeService {

    /**
     * <ul>
     * <li>Sends request with the authorization code grant(GET).<br/>
     * ClientId parameter contains illegal ClientId.</li>
     * </ul>
     * @param testId
     * @return OauthResource
     */
    OauthResource getResourceByIllegalId(String testId);

    /**
     * <ul>
     * <li>Sends request with the authorization code grant(GET).<br/>
     * ClientSecret parameter contains illegal ClientSecret.</li>
     * </ul>
     * @param testId
     * @return OauthResource
     */
    OauthResource getResourceByIllegalSecret(String testId);

    /**
     * <ul>
     * <li>Sends request with the authorization code grant(GET).<br/>
     * ClientSecret parameter contains illegal Resource Id.</li>
     * </ul>
     * @param testId
     * @return OauthResource
     */
    OauthResource getResourceByIllegalResourceId(String testId);

    /**
     * <ul>
     * <li>Sends request with the authorization code grant(GET).<br/>
     * ClientSecret parameter contains illegal Redirect Uri.</li>
     * </ul>
     * @param testId
     * @return OauthResource
     */
    OauthResource getResourceByIllegalUri(String testId);
}
