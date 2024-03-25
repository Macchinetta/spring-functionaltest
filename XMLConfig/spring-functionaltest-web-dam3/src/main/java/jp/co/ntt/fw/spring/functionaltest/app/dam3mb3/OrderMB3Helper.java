/*
 * Copyright(c) 2024 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.app.dam3mb3;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jp.co.ntt.fw.spring.functionaltest.domain.model.ItemMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.model.OrderItemMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.model.OrderMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.model.OrderStatusMB3;

@Component
public class OrderMB3Helper {

    public OrderMB3 chengeFormToOrderMB3(OrderMB3Form orderMB3Form) {

        OrderStatusMB3 orderStatusMB3 = new OrderStatusMB3();
        orderStatusMB3.setCode(orderMB3Form.getOrderStatus());

        ItemMB3 itemMB3 = new ItemMB3();
        itemMB3.setCode(orderMB3Form.getItemCode());

        OrderItemMB3 orderItemMB3 = new OrderItemMB3();
        orderItemMB3.setQuantity(orderMB3Form.getQuantity());
        orderItemMB3.setItem(itemMB3);

        List<OrderItemMB3> orderItems = new ArrayList<OrderItemMB3>();
        orderItems.add(orderItemMB3);

        OrderMB3 orderMB3 = new OrderMB3();
        orderMB3.setOrderItems(orderItems);
        if (orderMB3Form.getMemo().isEmpty()) {
            orderMB3.setMemo(null);
        } else {
            orderMB3.setMemo(orderMB3Form.getMemo());
        }
        orderMB3.setOrderStatus(orderStatusMB3);

        return orderMB3;
    }

    public OrderMB3Form chengeOrderMB3ToForm(OrderMB3 orderMB3) {
        OrderMB3Form orderMB3Form = new OrderMB3Form();

        orderMB3Form.setId(orderMB3.getId());
        if (!orderMB3.getOrderItems().isEmpty()) {
            orderMB3Form.setItemCode(orderMB3.getOrderItems().get(0).getItem()
                    .getCode());
            orderMB3Form.setQuantity(orderMB3.getOrderItems().get(0)
                    .getQuantity());
        }
        orderMB3Form.setMemo(orderMB3.getMemo());
        orderMB3Form.setOrderStatus(orderMB3.getOrderStatus().getCode());

        return orderMB3Form;

    }

    public List<OrderMB3> createOrderMB3List(int maxOrderId) {
        List<OrderMB3> orderMB3List = new ArrayList<OrderMB3>();

        // 1個目
        OrderStatusMB3 orderStatusMB3 = new OrderStatusMB3();
        orderStatusMB3.setCode("accepted");

        ItemMB3 itemMB3 = new ItemMB3();
        itemMB3.setCode("ITM0000001");

        OrderItemMB3 orderItemMB3 = new OrderItemMB3();
        orderItemMB3.setQuantity(11);
        orderItemMB3.setItem(itemMB3);

        List<OrderItemMB3> orderItems = new ArrayList<OrderItemMB3>();
        orderItems.add(orderItemMB3);

        OrderMB3 orderMB3 = new OrderMB3();
        orderMB3.setId(maxOrderId);
        orderMB3.setOrderItems(orderItems);
        orderMB3.setMemo("ikkatsu1");
        orderMB3.setOrderStatus(orderStatusMB3);

        orderMB3List.add(orderMB3);

        // 2個目
        orderStatusMB3 = new OrderStatusMB3();
        orderStatusMB3.setCode("accepted");

        itemMB3 = new ItemMB3();
        itemMB3.setCode("ITM0000002");

        orderItemMB3 = new OrderItemMB3();
        orderItemMB3.setQuantity(21);
        orderItemMB3.setItem(itemMB3);

        orderItems = new ArrayList<OrderItemMB3>();
        orderItems.add(orderItemMB3);

        orderMB3 = new OrderMB3();
        orderMB3.setId(maxOrderId + 1);
        orderMB3.setOrderItems(orderItems);
        orderMB3.setMemo("ikkatsu2");
        orderMB3.setOrderStatus(orderStatusMB3);

        orderMB3List.add(orderMB3);

        // 3個目
        orderStatusMB3 = new OrderStatusMB3();
        orderStatusMB3.setCode("accepted");

        itemMB3 = new ItemMB3();
        itemMB3.setCode("ITM0000003");

        orderItemMB3 = new OrderItemMB3();
        orderItemMB3.setQuantity(31);
        orderItemMB3.setItem(itemMB3);

        orderItems = new ArrayList<OrderItemMB3>();
        orderItems.add(orderItemMB3);

        orderMB3 = new OrderMB3();
        orderMB3.setId(maxOrderId + 2);
        orderMB3.setOrderItems(orderItems);
        orderMB3.setMemo("ikkatsu3");
        orderMB3.setOrderStatus(orderStatusMB3);

        orderMB3List.add(orderMB3);

        return orderMB3List;
    }
}
