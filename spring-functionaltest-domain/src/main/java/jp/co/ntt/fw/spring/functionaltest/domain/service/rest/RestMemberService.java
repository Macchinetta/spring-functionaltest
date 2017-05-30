/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.rest;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.RestMember;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RestMemberService {

    List<RestMember> findAll();

    Page<RestMember> searchMembers(String name, Pageable pageable);

    RestMember getMember(String memberId);

    RestMember createMember(RestMember creatingMember);

    RestMember updateMember(String memberId, RestMember updatingMember);

    void deleteMember(String memberId);
}
