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

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.UrlPathHelper;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Item;
import jp.co.ntt.fw.spring.functionaltest.domain.service.ssmn.ItemService;

@RequestMapping("shopping/items")
@Controller
@SessionAttributes("scopedTarget.cart")
public class SSMN04ShoppingItemsController {

    @Inject
    ItemService itemService;

    private UrlPathHelper urlPathHelper = new UrlPathHelper();

    @RequestMapping(method = RequestMethod.GET, params = "init")
    public String init(SessionStatus sessionStatus,
            HttpServletRequest request) {
        sessionStatus.setComplete();

        return "redirect:" + urlPathHelper.getServletPath(request)
                + "/shopping/items";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String items(@PageableDefault(20) Pageable pageable, Model model) {
        Page<Item> page = itemService.getItems(pageable);
        model.addAttribute("page", page);
        return "ssmn/shoppingItems";
    }

    @RequestMapping(value = "{itemId}", method = RequestMethod.GET)
    public String item(@PathVariable("itemId") String itemId, Model model) {
        Item item = itemService.getItem(itemId);
        model.addAttribute(item);

        CartItemForm form = new CartItemForm();
        form.setItemId(itemId);

        model.addAttribute(form);

        return "ssmn/shoppingItem";
    }

}
