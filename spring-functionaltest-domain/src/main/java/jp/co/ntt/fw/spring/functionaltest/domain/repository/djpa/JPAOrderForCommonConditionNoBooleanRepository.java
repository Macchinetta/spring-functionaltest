/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrderForCmnConditionNoBoolean;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAOrderForCommonConditionNoBooleanRepository
                                                              extends
                                                              JpaRepository<JPAOrderForCmnConditionNoBoolean, Integer> {

}
