/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPADeliveryOrder;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TDeliveryOrderRepository
                                         extends
                                         JpaRepository<JPADeliveryOrder, Integer>,
                                         TDeliveryOrderRepositoryCustom {

}
