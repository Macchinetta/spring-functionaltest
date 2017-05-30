/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.djpa;

import java.io.Serializable;

public class OrderItemForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer itemNumber;

    private Integer orderId;

    private ItemForm orderItem;

    private Integer quantity;

    private Boolean logicalDelete;

    public Boolean getLogicalDelete() {
        return logicalDelete;
    }

    public void setLogicalDelete(Boolean logicalDelete) {
        this.logicalDelete = logicalDelete;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public ItemForm getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(ItemForm orderItem) {
        this.orderItem = orderItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

}
