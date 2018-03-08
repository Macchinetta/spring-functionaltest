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
