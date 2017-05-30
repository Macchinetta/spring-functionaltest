/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.ssmn;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Order;
import jp.co.ntt.fw.spring.functionaltest.domain.model.OrderItem;

public interface OrderRepository {

    void createOrder(Order order);

    void createOrderItem(OrderItem orderItem);

    Long getOrderSequence();
}
