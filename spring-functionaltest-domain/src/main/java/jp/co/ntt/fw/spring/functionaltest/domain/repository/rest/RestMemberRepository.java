/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.rest;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import jp.co.ntt.fw.spring.functionaltest.domain.model.RestMember;

public interface RestMemberRepository {

    RestMember findOne(String memberId);

    List<RestMember> findAll();

    long countByContainsName(String name);

    List<RestMember> findPageByContainsName(String name, RowBounds rowBounds);

    void createMember(RestMember creatingMember);

    void createCredential(RestMember creatingMember);

    boolean updateMember(RestMember updatingMember);

    void deleteMember(String memberId);

    void deleteCredential(String memberId);
}
