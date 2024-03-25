/*
 * Copyright(c) 2024 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.app.ssmn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cartId;

    private List<CartItem> cartItems;

    public Cart() {
        cartId = UUID.randomUUID().toString();
        cartItems = new ArrayList<CartItem>();
    }

    public Cart(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    /**
     * remove items in the cart
     */
    public void clearItems() {
        cartItems.clear();
    }

    public long getTotalPrice() {
        long totalPrice = 0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getSubtotalPrice();
        }
        return totalPrice;
    }

    public CartItem findCartItem(String itemCode) {
        CartItem storedCartItem = null;
        for (CartItem targetStoredCartItem : cartItems) {
            if (itemCode.equals(targetStoredCartItem.getItemId())) {
                storedCartItem = targetStoredCartItem;
                break;
            }
        }
        return storedCartItem;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());
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
        Cart other = (Cart) obj;
        if (cartId == null) {
            if (other.cartId != null)
                return false;
        } else if (!cartId.equals(other.cartId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Cart [cartId=" + cartId + ", cartItems=" + cartItems
                + ", super.hashCode()=" + super.hashCode() + "]";
    }

}
