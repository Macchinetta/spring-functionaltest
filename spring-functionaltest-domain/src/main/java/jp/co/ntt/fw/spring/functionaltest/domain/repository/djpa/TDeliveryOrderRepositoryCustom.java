/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPADeliveryOrder;

public interface TDeliveryOrderRepositoryCustom {
    List<JPADeliveryOrder> findAllByCriteria(DeliveryOrderCriteria criteria);
}
