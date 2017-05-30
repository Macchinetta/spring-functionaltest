/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.ssmn;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.message.ResultMessages;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenCheck;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenType;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Order;
import jp.co.ntt.fw.spring.functionaltest.domain.service.ssmn.OrderService;

@RequestMapping("shopping/order")
@Controller
@TransactionTokenCheck("shopping/order")
public class SSMN04ShoppingOrderController {

    @Inject
    Cart cart;

    @Inject
    SSMN04ShoppingCartHelper shoppingCartHelper;

    @Inject
    OrderService orderService;

    @RequestMapping(method = RequestMethod.POST, params = "order")
    @TransactionTokenCheck(value = "order", type = TransactionTokenType.BEGIN)
    public String shoppingOrderComfirm(Model model) {
        model.addAttribute(cart);
        return "ssmn/shoppingOrderConfirm";
    }

    @RequestMapping(method = RequestMethod.POST)
    @TransactionTokenCheck(value = "order")
    public String createOrder(RedirectAttributes redirectAttributes) {

        // 注文商品登録
        Order order = orderService.createOrder(shoppingCartHelper
                .convertCartToOrder(cart));

        // 完了画面用
        redirectAttributes.addFlashAttribute(order);

        ResultMessages messages = ResultMessages.success()
                .add("i.sf.ssmn.0003");
        redirectAttributes.addFlashAttribute(messages);

        // 複数の試験を1つのView&Controllerで実現するために相対パスを使用
        return "redirect:./order?complete";
    }

    @RequestMapping(method = RequestMethod.GET, params = "complete")
    public String createOrderComplete(SessionStatus sessionStatus) {
        cart.clearItems();
        return "ssmn/shoppingOrderComplete";
    }
}
