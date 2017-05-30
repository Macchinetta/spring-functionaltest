/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import java.io.Serializable;

import org.joda.time.DateTime;

public class OrderHistoryMB3 implements Serializable {

    private static final long serialVersionUID = -1L;

    private int id;

    private int orderId;

    private String memo;

    private DateTime updTime;

    public DateTime getUpdTime() {
        return updTime;
    }

    public void setUpdTime(DateTime updTime) {
        this.updTime = updTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}
