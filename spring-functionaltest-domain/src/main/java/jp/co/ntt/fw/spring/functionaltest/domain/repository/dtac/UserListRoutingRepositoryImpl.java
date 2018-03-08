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
package jp.co.ntt.fw.spring.functionaltest.domain.repository.dtac;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.User;

import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class UserListRoutingRepositoryImpl extends SqlSessionDaoSupport
                                           implements
                                           UserListRoutingRepository {

    private MapperRegistry mapperRegistory = null;

    private void mapping() {
        if (mapperRegistory == null) {
            mapperRegistory = getSqlSession().getConfiguration()
                    .getMapperRegistry();
            mapperRegistory.addMapper(UserListRepository.class);
        }
    }

    @Override
    public long count() {
        mapping();
        return getSqlSession().getMapper(UserListRepository.class).count();
    }

    @Override
    public List<User> findPage(RowBounds rowBounds) {
        mapping();
        return getSqlSession().getMapper(UserListRepository.class).findPage(
                rowBounds);
    }

    @Override
    public User findOneByUsername(String username) {
        mapping();
        return getSqlSession().getMapper(UserListRepository.class)
                .findOneByUsername(username);
    }

    @Override
    public void insert(User user) {
        mapping();
        getSqlSession().getMapper(UserListRepository.class).insert(user);
    }

}
