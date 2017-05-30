/*
 * Copyright(c) 2014-2017 NTT Corporation.
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
