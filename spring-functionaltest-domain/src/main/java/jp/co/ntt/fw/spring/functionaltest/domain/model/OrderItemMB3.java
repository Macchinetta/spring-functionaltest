/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import java.io.Serializable;

public class OrderItemMB3 implements Serializable {
    private static final long serialVersionUID = 1L;

    private int orderId;

    private ItemMB3 item;

    private int quantity;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public ItemMB3 getItem() {
        return item;
    }

    public void setItem(ItemMB3 item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
