/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.model;

public class OrderSummaryStatus {

    private String statusName;

    private String orderStatusCode;

    public OrderSummaryStatus(String statusName, String orderStatusCode) {
        super();
        this.statusName = statusName;
        this.orderStatusCode = orderStatusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getOrderStatusCode() {
        return orderStatusCode;
    }

    public void setOrderStatusCode(String orderStatusCode) {
        this.orderStatusCode = orderStatusCode;
    }

}
