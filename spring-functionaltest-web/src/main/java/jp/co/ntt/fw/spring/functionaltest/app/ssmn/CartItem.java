/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.ssmn;

import java.io.Serializable;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Item;

public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cartId;

    private String itemId;

    private int quantity;

    private Item item;

    public CartItem() {

    }

    public CartItem(String cartId, String itemId) {
        this.cartId = cartId;
        this.itemId = itemId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public long getSubtotalPrice() {
        return item.getPrice() * quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());
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
        CartItem other = (CartItem) obj;
        if (cartId == null) {
            if (other.cartId != null)
                return false;
        } else if (!cartId.equals(other.cartId))
            return false;
        if (itemId == null) {
            if (other.itemId != null)
                return false;
        } else if (!itemId.equals(other.itemId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CartItem [cartId=" + cartId + ", itemId=" + itemId
                + ", quantity=" + quantity + ", item=" + item
                + ", super.hashCode()=" + super.hashCode() + "]";
    }

}
