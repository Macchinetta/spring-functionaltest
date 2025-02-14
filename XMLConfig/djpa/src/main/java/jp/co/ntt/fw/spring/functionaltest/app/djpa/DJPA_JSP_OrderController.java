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
package jp.co.ntt.fw.spring.functionaltest.app.djpa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.DataBaseInfo;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAItem;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrder;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrderForCmnConditionNoBoolean;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrderForCommonCondition;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAOrderItem;
import jp.co.ntt.fw.spring.functionaltest.domain.model.OrderSummary;
import jp.co.ntt.fw.spring.functionaltest.domain.service.djpa.JPAItemService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.djpa.JPAOrderItemService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.djpa.JPAOrderService;

@Controller
@RequestMapping("jsp/order")
public class DJPA_JSP_OrderController {

    @Inject
    JpaVendorAdapter jpaVendorAdapter;

    @Inject
    JPAOrderService jpaOrderService;

    @Inject
    JPAItemService jpaItemService;

    @Inject
    JPAOrderItemService jpaOrderItemService;

    @Inject
    DJPAOrderBeanMapper beanMapper;

    private static final Logger logger = LoggerFactory.getLogger(DJPA_JSP_OrderController.class);

    @ModelAttribute
    public OrderForm setUpForm() {
        return new OrderForm();
    }

    @GetMapping
    public String dispalyOrders(OrderForm form, Model model) {
        List<JPAOrder> orderList = jpaOrderService.getAll();
        List<OrderForm> orderFormList = convertEntityToForm(orderList);

        sortOrderList(orderFormList);
        model.addAttribute("orderList", orderFormList);

        return "jsp/djpa/orders";
    }

    private void sortOrderList(List<OrderForm> orderFormList) {
        Collections.sort(orderFormList, new Comparator<OrderForm>() {
            public int compare(OrderForm o1, OrderForm o2) {
                Integer orderId1 = o1.getOrderId();
                Integer orderId2 = o2.getOrderId();
                return orderId1.compareTo(orderId2);
                // it can also return 0, and 1
            }
        });
    }

    @GetMapping(params = "fetch")
    public String fetchOrderDetails(OrderForm form, Model model) {
        return "redirect:/jsp/order/" + form.getOrderId() + "/detail";
    }

    @GetMapping(params = "searchByStatus")
    public String searchOrderDetails(OrderForm form, Model model) {

        List<JPAOrder> orderList = jpaOrderService.joinFetch(form.getOrderStatus().getStatusName());
        List<OrderForm> orderFormList = convertEntityToForm(orderList);

        sortOrderList(orderFormList);
        model.addAttribute("orderList", orderFormList);
        return "jsp/djpa/orders";
    }

    @GetMapping(params = "fetchSummary")
    public String fetchStoringResultInCustomObject(OrderForm form, Model model) {
        List<OrderSummary> orderSummaries = jpaOrderService.storeFetchResultInCustomObject();
        model.addAttribute("orderList", orderSummaries);
        return "jsp/djpa/orders";
    }

    @GetMapping(params = "cmnCondFetch")
    public String fetchUsingCommonCondition(OrderForm form, Model model) {

        String dataBaseName =
                DataBaseInfo.getDataBaseID((HibernateJpaVendorAdapter) jpaVendorAdapter);
        logger.debug("Current Database Under Test ::" + dataBaseName);
        logger.info("Current Database Under Test ::" + dataBaseName);
        if (Database.H2.name().equals(dataBaseName)
                || Database.POSTGRESQL.name().equals(dataBaseName)) {

            JPAOrderForCommonCondition jpaOrder =
                    jpaOrderService.findOrderDeatilUsingCommonConditionOnEntity(form.getOrderId());
            OrderForm orderForm = beanMapper.map(jpaOrder);
            model.addAttribute("orderForm", orderForm);
        } else {
            JPAOrderForCmnConditionNoBoolean jpaOrder =
                    jpaOrderService.findOrderDeatilUsingCommonConditionOnEntityWithNoBooleanSupport(
                            form.getOrderId());
            OrderForm orderForm = beanMapper.map(jpaOrder);
            model.addAttribute("orderForm", orderForm);
        }
        return "jsp/djpa/orderDetail";
    }

    @GetMapping(value = "{orderId}/detail")
    public String dispalyOrdersDetail(@PathVariable("orderId") Integer orderId, Model model) {
        JPAOrder jpaOrder = jpaOrderService.getOrderDetail(orderId);
        OrderForm orderForm = beanMapper.map(jpaOrder);
        model.addAttribute("orderForm", orderForm);
        return "jsp/djpa/orderDetail";
    }

    @GetMapping(value = "cmnCondFetch/{orderId}/detail")
    public String dispalyOrdersDetailUsingCmnCondition(@PathVariable("orderId") Integer orderId,
            Model model) {
        JPAOrderForCommonCondition jpaOrder =
                jpaOrderService.findOrderDeatilUsingCommonConditionOnEntity(orderId);
        OrderForm orderForm = beanMapper.map(jpaOrder);
        model.addAttribute("orderForm", orderForm);
        return "jsp/djpa/orderDetail";
    }

    @DeleteMapping(value = "delete")
    public String deleteOrder(OrderForm form, Model model) {
        jpaOrderService.delete(form.getOrderId());
        return "redirect:/jsp/order";
    }

    @GetMapping(value = "{orderId}/item/{itemNum}/detail")
    public String dispalyOrderItemDetail(@PathVariable("orderId") Integer orderId,
            @PathVariable("itemNum") Integer itemNumber, Model model) {
        JPAOrderItem jpaOrderItemOrder =
                jpaOrderItemService.getOrderItemDetail(orderId, itemNumber);
        OrderItemForm orderItemForm = beanMapper.map(jpaOrderItemOrder);
        model.addAttribute("orderItemForm", orderItemForm);
        return "jsp/djpa/item";
    }

    @PostMapping(value = "item/modify")
    public String updateItemDetail(OrderItemForm orderItemForm, Model model) {
        JPAOrder jpaOrder = jpaOrderService.updateOrderItem(orderItemForm.getOrderId(),
                orderItemForm.getItemNumber(), orderItemForm.getQuantity());
        OrderForm orderForm = beanMapper.map(jpaOrder);
        model.addAttribute("orderForm", orderForm);
        return "redirect:/jsp/order";
    }

    @PostMapping(value = "item/modify", params = "dirRelatedEntityUpdate")
    public String updateDirectlyRelatedEntity(OrderItemForm orderItemForm, Model model) {

        jpaOrderItemService.updateOrderItem(orderItemForm.getItemNumber(),
                orderItemForm.getQuantity());

        return "redirect:/jsp/order";
    }

    @PostMapping(value = "item/modify", params = "dirRelatedEntityDelete")
    public String deleteDirectlyRelatedEntity(OrderItemForm orderItemForm, Model model) {

        jpaOrderItemService.directDeleteOrderItem(orderItemForm.getItemNumber(),
                orderItemForm.getOrderId());

        return "redirect:/jsp/order";
    }

    @PostMapping(value = "item/modify", params = "delete")
    public String deleteRelatedEntity(OrderItemForm orderItemForm, Model model) {

        jpaOrderService.deleteItem(orderItemForm.getOrderId(), orderItemForm.getItemNumber());
        return "redirect:/jsp/order";
    }

    @PostMapping(value = "item/modify", params = "deleteNoSuccess")
    public String deleteRelatedEntityNoSuccess(OrderItemForm orderItemForm, Model model) {

        jpaOrderItemService.deleteOrderItemNoSuccess(orderItemForm.getOrderId(),
                orderItemForm.getItemNumber());
        return "redirect:/jsp/order";
    }

    @PostMapping(value = "{orderId}/update", params = "deleteEntityByQuery")
    public String deleteEntityByQuery(@PathVariable("orderId") Integer orderId, Model model) {

        jpaOrderItemService.deleteUsingQueryMethodAndClear(orderId);

        return "redirect:/jsp/order";
    }

    @GetMapping(value = "{orderId}/addUpdt", params = "add")
    public String addItem(@PathVariable("orderId") Integer orderId, Model model) {
        constructCartForm(model);
        model.addAttribute("orderId", orderId);
        return "jsp/djpa/addToCart";
    }

    private void constructCartForm(Model model) {
        List<JPAItem> itemList = jpaItemService.getAll();
        List<ItemForm> itemFormList = convertItemEntityToItemForm(itemList);
        model.addAttribute("itemList", itemFormList);
        model.addAttribute("cartForm", new CartForm());
    }

    @PostMapping(value = "{orderId}/addToOrder")
    public String addItemToOrder(@PathVariable("orderId") Integer orderId, CartForm cartForm,
            Model model) {

        List<ItemForm> items = cartForm.getItems();
        List<JPAItem> itemList = convertItemFormToItemEntity(items);
        jpaOrderService.addtoOrder(itemList, orderId, cartForm.getMemo());

        return "redirect:/jsp/order";
    }

    @PostMapping(value = "{orderId}/update", params = "updateStatus")
    public String updateOrderStatus(@PathVariable("orderId") Integer orderId, OrderForm orderForm,
            Model model) {

        jpaOrderService.updateStatus(orderId, orderForm.getOrderStatus().getStatusName());

        return "redirect:/jsp/order";
    }

    @PostMapping(value = "{orderId}/update", params = "updateByQuery")
    public String updateOrderByQueryMethod(@PathVariable("orderId") Integer orderId,
            OrderForm orderForm, RedirectAttributes redirectAttr) {

        JPAOrderItem jpaOrderItem = jpaOrderItemService.updateUsingQueryMethod(orderId);
        OrderItemForm orderItemForm = beanMapper.map(jpaOrderItem);
        redirectAttr.addFlashAttribute("orderItemForm", orderItemForm);
        return "redirect:/jsp/order/item/detail";
    }

    @PostMapping(value = "{orderId}/update", params = "updateByQueryClear")
    public String updateOrderByQueryMethodAndClear(@PathVariable("orderId") Integer orderId,
            OrderForm orderForm, RedirectAttributes redirectAttr) {

        JPAOrderItem jpaOrderItem = jpaOrderItemService.updateUsingQueryMethodAndClear(orderId);

        OrderItemForm orderItemForm = beanMapper.map(jpaOrderItem);
        redirectAttr.addFlashAttribute("orderItemForm", orderItemForm);
        return "redirect:/jsp/order/item/detail";
    }

    @PostMapping(value = "{orderId}/update", params = "updateByQueryErr")
    public String updateOrderByQueryMethodErr(@PathVariable("orderId") Integer orderId,
            OrderForm orderForm, Model model) {

        jpaOrderItemService.updateUsingQueryMethodErr(orderId);

        return "redirect:/jsp/order";
    }

    @GetMapping(value = "item/detail")
    public String dispalyGivenOrderDetail(Model model) {
        Map<String, Object> map = model.asMap();
        OrderItemForm orderItemForm = new OrderItemForm();
        String keyName = "orderItemForm";
        if (map.containsKey(keyName)) {
            orderItemForm = (OrderItemForm) map.get(keyName);
        }
        model.addAttribute(keyName, orderItemForm);
        return "jsp/djpa/item";
    }

    @GetMapping(params = "create")
    public String createOrder(OrderForm form, Model model) {
        constructCartForm(model);
        return "jsp/djpa/cart";
    }

    @PostMapping(value = "place")
    public String placeOrder(CartForm form, Model model) {
        List<ItemForm> items = form.getItems();
        List<JPAItem> itemList = convertItemFormToItemEntity(items);
        jpaOrderService.placeOrder(itemList, form.getMemo());
        return "redirect:/jsp/order";
    }

    /**
     * @param items
     * @return
     */
    private List<JPAItem> convertItemFormToItemEntity(List<ItemForm> items) {
        List<JPAItem> itemList = new ArrayList<JPAItem>();
        for (ItemForm itemForm : items) {
            JPAItem jpaItem = beanMapper.map(itemForm);
            itemList.add(jpaItem);
        }
        return itemList;
    }

    /**
     * Related-entity object is to be added directly without linking it with the parent-entity
     * object, save it using the Repository interface of related-entity.
     * @param orderId
     * @param cartForm
     * @param model
     * @return
     */
    @PostMapping(value = "place", params = "directItem2Order")
    public String addDirectItemToOrder(CartForm cartForm, Model model) {

        List<ItemForm> items = cartForm.getItems();
        List<JPAItem> itemList = convertItemFormToItemEntity(items);
        //
        jpaOrderItemService.addItemToOrder(itemList);

        return "redirect:/jsp/order";
    }

    /**
     * @param itemList
     * @return
     */
    private List<ItemForm> convertItemEntityToItemForm(List<JPAItem> itemList) {
        List<ItemForm> itemFormList = new ArrayList<ItemForm>();
        for (JPAItem jpaItem : itemList) {
            ItemForm itemForm = beanMapper.map(jpaItem);
            itemFormList.add(itemForm);
        }
        return itemFormList;
    }

    /**
     * @param orderList
     * @return
     */
    private List<OrderForm> convertEntityToForm(List<JPAOrder> orderList) {
        List<OrderForm> orderFormList = new ArrayList<OrderForm>();
        for (JPAOrder jpaOrder : orderList) {
            OrderForm orderForm = beanMapper.map(jpaOrder);
            orderFormList.add(orderForm);
        }
        return orderFormList;
    }

}
