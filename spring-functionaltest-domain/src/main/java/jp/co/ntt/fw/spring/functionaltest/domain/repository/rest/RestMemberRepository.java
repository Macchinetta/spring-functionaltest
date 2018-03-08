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
