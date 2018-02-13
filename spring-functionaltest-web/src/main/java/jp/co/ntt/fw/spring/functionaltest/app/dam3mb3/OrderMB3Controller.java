/*
 * Copyright 2014-2017 NTT Corporation.
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
 *
 */
package jp.co.ntt.fw.spring.functionaltest.app.dam3mb3;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.ntt.fw.spring.functionaltest.domain.model.CategoryMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.model.ItemMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.model.OrderMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dam3mb3.OrderMB3Service;

@Controller
@RequestMapping("dam3mb3/ordermb3")
public class OrderMB3Controller {

    @Inject
    OrderMB3Helper orderMB3Helper;

    @Inject
    OrderMB3Service orderMB3Service;

    @ModelAttribute
    public OrderMB3Form setUpForm() {
        OrderMB3Form form = new OrderMB3Form();
        return form;
    }

    @RequestMapping(value = "list")
    public String list(Model model) {
        List<OrderMB3> orderMB3List = orderMB3Service.findAll();
        model.addAttribute("orderMB3List", orderMB3List);
        return "dam3mb3/orderList";
    }

    @RequestMapping(value = "detail/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        OrderMB3 orderMB3 = orderMB3Service.findOne(id.intValue());
        OrderMB3Form orderMB3Form = orderMB3Helper.chengeOrderMB3ToForm(
                orderMB3);
        model.addAttribute("orderMB3Form", orderMB3Form);
        return "dam3mb3/orderUpdateForm";
    }

    @RequestMapping(value = "register")
    public String register(Model model) {
        return "dam3mb3/orderRegistForm";
    }

    @RequestMapping(value = "detailCondSts/{id}/{sts}")
    public String detailCondSts(@PathVariable("id") Integer id,
            @PathVariable("sts") String sts, Model model) {
        OrderMB3 orderMB3 = orderMB3Service.findOneCondSts(id.intValue(), sts);
        model.addAttribute("order", orderMB3);
        return "dam3mb3/orderCompleteForm";
    }

    @RequestMapping(value = "listPageMyBatis3")
    public String listPageMyBatis3(
            @PageableDefault(page = 0, size = 3, direction = Direction.DESC) Pageable pageable,
            Model model) {
        Page<OrderMB3> orderMB3List = orderMB3Service.findPageMyBatis3(
                pageable);
        model.addAttribute("page", orderMB3List);
        return "dam3mb3/orderListPager";
    }

    @RequestMapping(value = "listPageMyBatis3Scroll")
    public String listPageMyBatis3Scroll(
            @PageableDefault(page = 0, size = 3, direction = Direction.DESC) Pageable pageable,
            Model model) {
        Page<OrderMB3> orderMB3List = orderMB3Service.findPageMyBatis3(
                pageable);
        model.addAttribute("page", orderMB3List);
        return "dam3mb3/orderListPager";
    }

    @RequestMapping(value = "complete/{id}")
    public String complete(@PathVariable("id") Integer id, Model model) {
        OrderMB3 orderMB3 = orderMB3Service.findOne(id.intValue());
        model.addAttribute("order", orderMB3);
        return "dam3mb3/orderCompleteForm";
    }

    @RequestMapping(value = "showCatDetl", method = RequestMethod.GET, params = "catDisplay")
    public String getCategoryDetail(OrderMB3Form orderMB3Form, Model model) {

        List<CategoryMB3> categories = orderMB3Service
                .findAllCategoryByItemCode(orderMB3Form.getItemCode());

        model.addAttribute("catListFlag", true);
        model.addAttribute("catList", categories);
        return "dam3mb3/orderList";
    }

    @RequestMapping(value = "showCatDetl", method = RequestMethod.GET, params = "catDisplayLazy")
    public String getCategoryDetailLazy(OrderMB3Form orderMB3Form,
            Model model) {

        List<CategoryMB3> categories = orderMB3Service
                .findAllCategoryByItemCodeLazy(orderMB3Form.getItemCode());

        model.addAttribute("catListFlag", true);
        model.addAttribute("catList", categories);
        return "dam3mb3/orderList";
    }

    @RequestMapping(value = "showList", method = RequestMethod.GET, params = "first")
    public String getItemCode(
            @PageableDefault(page = 0, size = 2, direction = Direction.DESC) Pageable pageable,
            OrderMB3Form orderMB3Form, Model model) {

        List<ItemMB3> searchItemCode = orderMB3Service.findAllItemCode();

        model.addAttribute("searchItemCode", searchItemCode);
        return "dam3mb3/orderPageList";

    }

    @RequestMapping(value = "showList", method = RequestMethod.GET)
    public String getPageByItemCode(
            @PageableDefault(page = 0, size = 2, direction = Direction.DESC) Pageable pageable,
            OrderMB3Form orderMB3Form, Model model) {

        List<ItemMB3> searchItemCode = orderMB3Service.findAllItemCode();

        model.addAttribute("searchItemCode", searchItemCode);

        Page<OrderMB3> orderMB3List = orderMB3Service.findPageByItemcode(
                orderMB3Form.getSearchItemCode(), pageable);

        model.addAttribute("page", orderMB3List);
        return "dam3mb3/orderPageList";

    }

}
