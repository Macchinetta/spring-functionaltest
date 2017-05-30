/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import java.io.Serializable;
import java.util.List;

public class OrderMB3 implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;

    private OrderStatusMB3 orderStatus;

    List<OrderItemMB3> orderItems;

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
