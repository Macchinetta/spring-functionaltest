/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.dtac;

import jp.co.ntt.fw.spring.functionaltest.domain.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserListRoutingService {

    Page<User> getUsers(Pageable pageable);

    User getUser(String username);

    void register(User user);
}
