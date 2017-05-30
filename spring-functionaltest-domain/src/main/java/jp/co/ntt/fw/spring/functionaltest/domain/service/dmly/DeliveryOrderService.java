/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.dmly;

import jp.co.ntt.fw.spring.functionaltest.domain.model.DeliveryOrder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliveryOrderService {

    long count();

    DeliveryOrder getOrder(Integer deliveryNo);

    DeliveryOrder getOrderExists(Integer deliveryNo);

    Page<DeliveryOrder> getOrders(Pageable pageable);

    void register(DeliveryOrder deliveryOrder);

    void update(DeliveryOrder deliveryOrder);

    void delete(Integer deliveryNo);

}
