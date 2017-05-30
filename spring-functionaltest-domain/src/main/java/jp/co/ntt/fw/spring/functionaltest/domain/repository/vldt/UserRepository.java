/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.vldt;

public interface UserRepository {

    /**
     * 存在チェック<br>
     * <p>
     * userIdからレコードの存在チェック
     * </p>
     * @param userId
     * @return
     */
    long existsUser(String userId);

}
