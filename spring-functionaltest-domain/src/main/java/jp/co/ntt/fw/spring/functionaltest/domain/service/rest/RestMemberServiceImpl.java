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
package jp.co.ntt.fw.spring.functionaltest.domain.service.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.joda.time.DateTime;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.terasoluna.gfw.common.date.jodatime.JodaTimeDateFactory;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.github.dozermapper.core.Mapper;

import jp.co.ntt.fw.spring.functionaltest.domain.message.DomainMessageCodes;
import jp.co.ntt.fw.spring.functionaltest.domain.model.RestMember;
import jp.co.ntt.fw.spring.functionaltest.domain.model.RestMemberCredential;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.rest.RestMemberRepository;

@Transactional
@Service
public class RestMemberServiceImpl implements RestMemberService {

    @Inject
    RestMemberRepository restMemberRepository;

    @Inject
    JodaTimeDateFactory dateFactory;

    @Inject
    PasswordEncoder passwordEncoder;

    @Inject
    Mapper beanMapper;

    @Override
    @Transactional(readOnly = true)
    public List<RestMember> findAll() {
        return restMemberRepository.findAll();
    }

    /**
     * searchMembers
     * <ul>
     * <li>指定されたMemberリソースのコレクションをページ検索する</li>
     * </ul>
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RestMember> searchMembers(String name, Pageable pageable) {
        List<RestMember> restMembers = null;
        // Count Member by search criteria
        long total = restMemberRepository.countByContainsName(name);
        if (0 < total) {
            RowBounds rowBounds = new RowBounds((int) pageable
                    .getOffset(), pageable.getPageSize());
            restMembers = restMemberRepository.findPageByContainsName(name,
                    rowBounds);

        } else {
            restMembers = new ArrayList<RestMember>();
        }
        return new PageImpl<RestMember>(restMembers, pageable, total);
    }

    /**
     * getMember
     * <ul>
     * <li>指定されたMemberリソースを取得する</li>
     * </ul>
     */
    @Override
    @Transactional(readOnly = true)
    public RestMember getMember(String memberId) {
        // find member
        RestMember member = restMemberRepository.findOne(memberId);
        if (member == null) {
            // If member is not exists
            throw new ResourceNotFoundException(ResultMessages.error().add(
                    DomainMessageCodes.E_SF_MM_5001, memberId));
        }
        return member;
    }

    /**
     * createMember
     * <ul>
     * <li>指定されたMemberリソースを作成し、Memberリソースをコレクションに追加する</li>
     * </ul>
     */
    @Override
    public RestMember createMember(RestMember creatingMember) {
        RestMemberCredential creatingCredential = creatingMember
                .getCredential();

        // get processing current date time
        DateTime currentDateTime = dateFactory.newDateTime();

        creatingMember.setCreatedAt(currentDateTime);
        creatingMember.setLastModifiedAt(currentDateTime);

        // decide sign id(email-address)
        String signId = creatingCredential.getSignId();
        if (!StringUtils.hasLength(signId)) {
            signId = creatingMember.getEmailAddress();
            creatingCredential.setSignId(signId.toLowerCase());
        }

        // encrypt password
        String rawPassword = creatingCredential.getPassword();
        creatingCredential.setPassword(passwordEncoder.encode(rawPassword));
        creatingCredential.setPasswordLastChangedAt(currentDateTime);
        creatingCredential.setLastModifiedAt(currentDateTime);

        // save member & member credential
        try {

            // Registering member details
            restMemberRepository.createMember(creatingMember);
            // //Registering credential details
            restMemberRepository.createCredential(creatingMember);
            return creatingMember;
        } catch (DuplicateKeyException e) {
            // If sign id is already used
            throw new BusinessException(ResultMessages.error().add(
                    DomainMessageCodes.E_SF_MM_8001, creatingCredential
                            .getSignId()), e);
        }
    }

    /**
     * updateMember
     * <ul>
     * <li>指定されたMemberリソースを更新する</li>
     * </ul>
     */
    @Override
    public RestMember updateMember(String memberId, RestMember updatingMember) {
        // get member
        RestMember member = getMember(memberId);

        // override updating member attributes
        beanMapper.map(updatingMember, member, "member.update");

        // get processing current date time
        DateTime currentDateTime = dateFactory.newDateTime();
        member.setLastModifiedAt(currentDateTime);

        // save updating member
        boolean updated = restMemberRepository.updateMember(member);
        if (!updated) {
            throw new ObjectOptimisticLockingFailureException(RestMember.class, member
                    .getMemberId());
        }

        return member;
    }

    /**
     * deleteMember
     * <ul>
     * <li>指定されたMemberリソースを削除する</li>
     * </ul>
     */
    @Override
    public void deleteMember(String memberId) {

        // First Delete from credential (Child)
        restMemberRepository.deleteCredential(memberId);
        // Delete member
        restMemberRepository.deleteMember(memberId);

    }

}
