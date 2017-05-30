/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.ssmn;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

public class CartItemsForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Valid
    private List<CartItemForm> cartItemForms;

    public List<CartItemForm> getCartItemForms() {
        return cartItemForms;
    }

    public void setCartItemForms(List<CartItemForm> cartItemForms) {
        this.cartItemForms = cartItemForms;
    }

}
