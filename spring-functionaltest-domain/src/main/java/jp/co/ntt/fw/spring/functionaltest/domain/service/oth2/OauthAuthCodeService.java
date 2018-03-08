/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
