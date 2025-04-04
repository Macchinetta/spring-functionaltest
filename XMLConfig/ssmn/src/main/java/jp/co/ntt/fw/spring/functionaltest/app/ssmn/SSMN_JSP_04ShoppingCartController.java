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

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UrlPathHelper;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenCheck;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenType;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("shopping/cart")
@TransactionTokenCheck("jsp/shopping/cart")
@Controller
public class SSMN_JSP_04ShoppingCartController {

    @Inject
    Cart cart;

    @Inject
    SSMN04ShoppingCartHelper shoppingCartHelper;

    private UrlPathHelper urlPathHelper = new UrlPathHelper();

    @GetMapping
    @TransactionTokenCheck(value = "cart", type = TransactionTokenType.BEGIN)
    public String cart(CartItemsForm form) {

        List<CartItemForm> cartItemForms =
                shoppingCartHelper.convertToCartItemForms(cart.getCartItems());
        form.setCartItemForms(cartItemForms);

        return "jsp/ssmn/shoppingCart";
    }

    @PostMapping(params = "order")
    @TransactionTokenCheck(value = "cart")
    public String shoppingOrderComfirm(@Validated CartItemsForm form, BindingResult bindingResult,
            Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(cart);
            return "jsp/ssmn/shoppingCart";
        }

        shoppingCartHelper.setQuantityItems(cart, form.getCartItemForms());

        model.addAttribute(cart);

        return "forward:" + urlPathHelper.getServletPath(request) + "/shopping/order?order";
    }
}
