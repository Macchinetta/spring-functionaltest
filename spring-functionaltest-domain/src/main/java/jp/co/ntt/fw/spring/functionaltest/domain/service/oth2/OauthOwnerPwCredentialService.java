/*
 * Copyright 2014-2017 NTT Corporation.
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
 *
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
