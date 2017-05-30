/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.ssmn;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Member;

public interface MemberRepository {

    Long getMemberSequence();

    void create(Member member);
}
