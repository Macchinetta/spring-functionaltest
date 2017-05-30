/*
 * Copyright(c) 2014-2017 NTT Corporation.
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
