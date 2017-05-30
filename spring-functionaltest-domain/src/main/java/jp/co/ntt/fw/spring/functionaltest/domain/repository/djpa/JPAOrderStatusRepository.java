/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrderStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAOrderStatusRepository extends
                                         JpaRepository<JPAOrderStatus, String> {

    JPAOrderStatus findByStatusName(String statusName);

}
