/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.dtac;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.User;

import org.apache.ibatis.session.RowBounds;

public interface UserListRepository {

    long count();

    List<User> findPage(RowBounds rowBounds);

    User findOneByUsername(String username);

    void insert(User user);

}
