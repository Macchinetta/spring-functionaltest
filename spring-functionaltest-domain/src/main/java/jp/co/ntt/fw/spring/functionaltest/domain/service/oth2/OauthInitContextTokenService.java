/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.oth2;

public interface OauthInitContextTokenService {

    /**
     * <ul>
     * <li>Delete context token from all RestOperations.<br/>
     * </li>
     * </ul>
     */
    void initContextTokenAll();
}
