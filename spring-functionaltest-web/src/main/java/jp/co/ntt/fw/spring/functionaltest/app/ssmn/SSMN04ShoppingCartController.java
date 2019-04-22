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

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UrlPathHelper;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenCheck;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenType;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("shopping/cart")
@TransactionTokenCheck("shopping/cart")
@Controller
public class SSMN04ShoppingCartController {

    @Inject
    Cart cart;

    @Inject
    SSMN04ShoppingCartHelper shoppingCartHelper;

    private UrlPathHelper urlPathHelper = new UrlPathHelper();

    @RequestMapping(method = RequestMethod.GET)
    @TransactionTokenCheck(value = "cart", type = TransactionTokenType.BEGIN)
    public String cart(CartItemsForm form) {

        List<CartItemForm> cartItemForms = shoppingCartHelper
                .convertToCartItemForms(cart.getCartItems());
        form.setCartItemForms(cartItemForms);

        return "ssmn/shoppingCart";
    }

    @RequestMapping(method = RequestMethod.POST, params = "order")
    @TransactionTokenCheck(value = "cart")
    public String shoppingOrderComfirm(@Validated CartItemsForm form,
            BindingResult bindingResult, Model model,
            HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(cart);
            return "ssmn/shoppingCart";
        }

        shoppingCartHelper.setQuantityItems(cart, form.getCartItemForms());

        model.addAttribute(cart);

        return "forward:" + urlPathHelper.getServletPath(request)
                + "/shopping/order?order";
    }
}
