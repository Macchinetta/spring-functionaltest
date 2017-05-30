/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dam3mb3;

import java.io.Serializable;
import java.util.List;

public class OrderMB3Form implements Serializable {

    private static final long serialVersionUID = -1;

    private int id;

    private String orderStatus;

    private int quantity;

    private String itemCode;

    private String memo;

    private List<String> searchItemCode;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public List<String> getSearchItemCode() {
        return searchItemCode;
    }

    public void setSearchItemCode(List<String> searchItemCode) {
        this.searchItemCode = searchItemCode;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
