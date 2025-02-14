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
package jp.co.ntt.fw.spring.functionaltest.app.djpa;

import java.io.Serializable;
import java.util.List;

public class OrderForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private int orderId;

    private String orderMemo;

    private Integer orderAmount;

    private Boolean logicalDelete;

    transient List<OrderItemForm> orderItem;

    OrderStatusForm orderStatus;

    public Boolean getLogicalDelete() {
        return logicalDelete;
    }

    public void setLogicalDelete(Boolean logicalDelete) {
        this.logicalDelete = logicalDelete;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderMemo() {
        return orderMemo;
    }

    public void setOrderMemo(String orderMemo) {
        this.orderMemo = orderMemo;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public List<OrderItemForm> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<OrderItemForm> orderItem) {
        this.orderItem = orderItem;
    }

    public OrderStatusForm getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusForm orderStatus) {
        this.orderStatus = orderStatus;
    }

}
