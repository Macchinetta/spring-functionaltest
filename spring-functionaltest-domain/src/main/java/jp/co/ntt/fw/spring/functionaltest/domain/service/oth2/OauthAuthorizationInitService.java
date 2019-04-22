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

public interface OauthAuthorizationInitService {

    /**
     * <ul>
     * <li>delete approvals and tokens from DB.
     * </ul>
     * @throws IOException
     */
    void deleteApproveAndToken();

    /**
     * <ul>
     * <li>count the number of tokens in DB.
     * </ul>
     * @return number of tokens
     * @throws IOException
     */
    long countToken();

    /**
     * <ul>
     * <li>count the number of RefreshTokens in DB.
     * </ul>
     * @return number of RefreshTokens
     * @throws IOException
     */
    long countRefreshToken();

    /**
     * <ul>
     * <li>count the number of Approvals in DB.
     * </ul>
     * @return number of Approvals
     * @throws IOException
     */
    long countApprove();

}
