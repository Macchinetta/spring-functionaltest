/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.app.ssmn;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UrlPathHelper;
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

    private UrlPathHelper urlPathHelper = new UrlPathHelper();

    @RequestMapping(method = RequestMethod.POST, params = "order")
    @TransactionTokenCheck(value = "order", type = TransactionTokenType.BEGIN)
    public String shoppingOrderComfirm(Model model) {
        model.addAttribute(cart);
        return "ssmn/shoppingOrderConfirm";
    }

    @RequestMapping(method = RequestMethod.POST)
    @TransactionTokenCheck(value = "order")
    public String createOrder(RedirectAttributes redirectAttributes,
            HttpServletRequest request) {

        // 注文商品登録
        Order order = orderService.createOrder(shoppingCartHelper
                .convertCartToOrder(cart));

        // 完了画面用
        redirectAttributes.addFlashAttribute(order);

        ResultMessages messages = ResultMessages.success().add(
                "i.sf.ssmn.0003");
        redirectAttributes.addFlashAttribute(messages);

        return "redirect:" + urlPathHelper.getServletPath(request)
                + "/shopping/order?complete";
    }

    @RequestMapping(method = RequestMethod.GET, params = "complete")
    public String createOrderComplete(SessionStatus sessionStatus) {
        cart.clearItems();
        return "ssmn/shoppingOrderComplete";
    }
}
