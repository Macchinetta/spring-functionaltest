/*
 * Copyright(c) 2014-2017 NTT Corporation.
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

    List<OrderItemForm> orderItem;

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
