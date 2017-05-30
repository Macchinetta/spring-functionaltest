/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.oth2;

public interface TokenRepository {

    void deleteAllToken();

    long countAllToken();

    void deleteAllRefreshToken();

    long countAllRefreshToken();

}
