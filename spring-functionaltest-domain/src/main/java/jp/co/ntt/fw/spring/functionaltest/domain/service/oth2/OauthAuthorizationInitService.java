/*
 * Copyright(c) 2014-2017 NTT Corporation.
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
