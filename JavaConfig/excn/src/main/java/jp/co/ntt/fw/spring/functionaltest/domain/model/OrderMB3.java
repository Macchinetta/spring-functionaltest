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
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import java.io.Serializable;
import java.util.List;

public class OrderMB3 implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;

    private OrderStatusMB3 orderStatus;

    private List<OrderItemMB3> orderItems;

    private String memo;

    private int historyId;

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderStatusMB3 getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusMB3 orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderItemMB3> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemMB3> orderItems) {
        this.orderItems = orderItems;
    }

}
