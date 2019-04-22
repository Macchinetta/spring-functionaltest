/*
 * Copyright(c) 2014 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.oth2;

public interface OauthRemoteCoopService {

    /**
     * <ul>
     * <li>Sends request with the authorization code grant(GET).<br/>
     * Accessing resource server is using remote token service.</li>
     * </ul>
     * @param testId
     * @return OauthResource
     */
    OauthResource getResource(String testId);

    /**
     * <ul>
     * <li>Sends request with the authorization code grant(POST).<br/>
     * Accessing resource server is using remote token service.</li>
     * </ul>
     * @param testId
     * @return OauthResource
     */
    OauthResource postResource(String testId);

    /**
     * <ul>
     * <li>Sends request with the authorization code grant(PUT).<br/>
     * Accessing resource server is using remote token service.</li>
     * </ul>
     * @param testId
     */
    void putResource(String testId);

    /**
     * <ul>
     * <li>Sends request with the authorization code grant(DELETE).<br/>
     * Accessing resource server is using remote token service.</li>
     * </ul>
     * @param testId
     */
    void deleteResource(String testId);

    OauthResource getResourceDefault(String testId);

    OauthResource getResourceUnauthorized(String testId);

    /**
     * <ul>
     * <li>Get token value from RestOperations.</li>
     * </ul>
     * @return token value
     */
    String getTokenValue();
}
