/*
 * Copyright(c) 2014 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.ssmn;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.inject.Inject;
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
