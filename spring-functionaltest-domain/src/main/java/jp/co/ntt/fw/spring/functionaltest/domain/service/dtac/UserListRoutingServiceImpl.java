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
package jp.co.ntt.fw.spring.functionaltest.domain.service.dtac;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import jp.co.ntt.fw.spring.functionaltest.domain.model.User;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dtac.UserListRoutingRepository;

import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserListRoutingServiceImpl implements UserListRoutingService {

    @Inject
    PasswordEncoder passwordEncoder;

    @Inject
    UserListRoutingRepository userListRoutingRepository;

    public Page<User> getUsers(Pageable pageable) {
        List<User> users = null;
        long total = userListRoutingRepository.count();
        if (0 < total) {
            RowBounds rowBounds = new RowBounds((int) pageable
                    .getOffset(), pageable.getPageSize());
            users = userListRoutingRepository.findPage(rowBounds);
        } else {
            users = new ArrayList<User>();
        }
        return new PageImpl<User>(users, pageable, total);
    }

    public User getUser(String username) {
        return userListRoutingRepository.findOneByUsername(username);
    }

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userListRoutingRepository.insert(user);

        return;
    }

}
