/*
 * Copyright(c) 2014 NTT Corporation.
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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Item;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Order;
import jp.co.ntt.fw.spring.functionaltest.domain.model.OrderItem;
import jp.co.ntt.fw.spring.functionaltest.domain.service.ssmn.ItemService;

import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Component;

@Component
public class SSMN04ShoppingCartHelper {

    @Inject
    Mapper beanMapper;

    @Inject
    ItemService itemService;

    public List<CartItem> convertToCartItems(List<CartItemForm> forms) {
        List<CartItem> cartItems = new ArrayList<CartItem>();
        if (forms == null) {
            return cartItems;
        }
        for (CartItemForm form : forms) {
            cartItems.add(beanMapper.map(form, CartItem.class));
        }
        return cartItems;
    }

    public List<CartItemForm> convertToCartItemForms(List<CartItem> cartItems) {
        List<CartItemForm> cartItemForms = new ArrayList<CartItemForm>();
        if (cartItems == null) {
            return cartItemForms;
        }
        for (CartItem cartItem : cartItems) {
            cartItemForms.add(beanMapper.map(cartItem, CartItemForm.class));
        }
        return cartItemForms;
    }

    public Order convertCartToOrder(Cart cart) {
        Order order = new Order();
        order.setOrderItems(new ArrayList<OrderItem>());
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = beanMapper.map(cartItem, OrderItem.class);
            order.getOrderItems().add(orderItem);
        }
        return order;
    }

    public void addItem(CartItem cartItem, Cart cart) {

        // 商品情報登録
        Item item = itemService.getItem(cartItem.getItemId());
        cartItem.setItem(item);
        cartItem.setCartId(cart.getCartId());
        cartItem.setQuantity(1);

        // すでにカートに格納されている商品は個数を加算
        CartItem storedCartItem = cart.findCartItem(cartItem.getItemId());

        if (storedCartItem != null) {
            storedCartItem.setQuantity(storedCartItem.getQuantity() + cartItem
                    .getQuantity());
        } else {
            cart.getCartItems().add(cartItem);
        }

    }

    public void setQuantityItems(Cart cart, List<CartItemForm> forms) {

        List<CartItem> cartItems = convertToCartItems(forms);

        // 入力された注文数をセッションに更新する
        for (CartItem targetInputCartItem : cartItems) {
            CartItem targetCartItem = cart.findCartItem(targetInputCartItem
                    .getItemId());
            if (targetCartItem != null) {
                targetCartItem.setQuantity(targetInputCartItem.getQuantity());
            }
        }
    }
}
