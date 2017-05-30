/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.ssmn;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Order;

public interface OrderService {

    Order createOrder(Order order);
}
