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
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import java.io.Serializable;

public class OrderSummary implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int orderId;

    private String orderMemo;

    private long orderAmount;

    private Boolean logicalDelete;

    private OrderSummaryStatus orderStatus;

    public OrderSummary(Integer id, long totalPrice, String orderMemo,
            String orderStatus, String statCode) {
        super();
        this.orderId = id;
        this.orderAmount = totalPrice;
        this.orderMemo = orderMemo;
        this.orderStatus = new OrderSummaryStatus(orderStatus, statCode);
    }

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

    public long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public OrderSummaryStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderSummaryStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /*
     * public String getOrderStatus() { return orderStatus; } public void setOrderStatus(String orderStatus) { this.orderStatus
     * = orderStatus; }
     */

}
