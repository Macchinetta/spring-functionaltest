/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.model;

import java.io.Serializable;

public class ItemStock implements Serializable {

    private static final long serialVersionUID = 1L;

    private String itemId;

    public ItemStock() {

    }

    public ItemStock(String itemId) {
        this.itemId = itemId;
    }

    private int quantity;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemCode) {
        this.itemId = itemCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ItemStock other = (ItemStock) obj;
        if (itemId == null) {
            if (other.itemId != null)
                return false;
        } else if (!itemId.equals(other.itemId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ItemStock [itemCode=" + itemId + ", quantity=" + quantity
                + ", super.hashCode()=" + super.hashCode() + "]";
    }

}
