/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.ssmn;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Item;
import jp.co.ntt.fw.spring.functionaltest.domain.service.ssmn.ItemService;

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

@RequestMapping("shopping/items")
@Controller
@SessionAttributes("scopedTarget.cart")
public class SSMN04ShoppingItemsController {

    @Inject
    ItemService itemService;

    @RequestMapping(method = RequestMethod.GET, params = "init")
    public String init(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:./items";
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
