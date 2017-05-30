/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.ssmn;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenCheck;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenType;

@RequestMapping("shopping/cart")
@TransactionTokenCheck("shopping/cart")
@Controller
public class SSMN04ShoppingCartController {

    @Inject
    Cart cart;

    @Inject
    SSMN04ShoppingCartHelper shoppingCartHelper;

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
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(cart);
            return "ssmn/shoppingCart";
        }

        shoppingCartHelper.setQuantityItems(cart, form.getCartItemForms());

        model.addAttribute(cart);

        return "forward:./order?order";
    }
}
