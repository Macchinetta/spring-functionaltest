/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.ssmn;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Order;
import jp.co.ntt.fw.spring.functionaltest.domain.model.OrderItem;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.ssmn.OrderRepository;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        String orderId = String.format("O%09d", orderRepository
                .getOrderSequence());
        order.setOrderId(orderId);

        orderRepository.createOrder(order);

        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrderId(orderId);
            orderRepository.createOrderItem(orderItem);
        }

        return order;
    }

}
