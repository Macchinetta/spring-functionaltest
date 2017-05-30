/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPADeliveryOrder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JPADeliveryOrderRepositoryCustom {

    Page<JPADeliveryOrder> findByCriteria(DeliveryOrderCriteria criteria,
            Pageable pageable);

}
