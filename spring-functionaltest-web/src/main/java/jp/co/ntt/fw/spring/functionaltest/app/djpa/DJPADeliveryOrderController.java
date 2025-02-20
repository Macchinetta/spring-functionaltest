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
package jp.co.ntt.fw.spring.functionaltest.app.djpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.dozermapper.core.Mapper;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPADeliveryOrder;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.DeliveryOrderCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.service.djpa.JPADeliveryOrderService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.djpa.JPAOrderService;

@Controller
@RequestMapping("djpa/delivery/order")
public class DJPADeliveryOrderController {

    @Inject
    JPADeliveryOrderService jpaDeliveryOrderService;

    @Inject
    JPAOrderService jpaOrderService;

    @Inject
    Mapper beaMapper;

    @ModelAttribute
    public DeliveryOrderStatusForm setUpForm() {
        return new DeliveryOrderStatusForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String deliveryOrderDetailsForm(Model model) {

        return "djpa/orderList";
    }

    @RequestMapping(method = RequestMethod.GET, params = "form")
    public String deliveryOrderDetails(DeliveryOrderStatusForm deliveryOrderStatusForm,
            Model model) {

        String orderStatus = deliveryOrderStatusForm.getDelOrderStatus();
        List<JPADeliveryOrder> deliveryOrderList =
                jpaDeliveryOrderService.searchUsingStatCode(orderStatus);
        model.addAttribute("deliveryOrders", deliveryOrderList);
        return "djpa/orderList";
    }

    @RequestMapping(method = RequestMethod.GET, params = "likeSrch")
    public String likeSearch(DeliveryOrderStatusForm deliveryOrderStatusForm, Model model) {

        String searchVal = deliveryOrderStatusForm.getDelOrderStatus();
        String criteria[] = deliveryOrderStatusForm.getMatchOption().split("_");
        List<JPADeliveryOrder> deliveryOrderList =
                jpaDeliveryOrderService.searchUsingGiveSearchType(searchVal, criteria);
        model.addAttribute("deliveryOrders", deliveryOrderList);
        return "djpa/orderList";
    }

    @RequestMapping(params = "escSrchMod", method = RequestMethod.GET)
    public String escapeModSerach(@RequestParam("escapeSrchVal") String modSearchVal,
            DeliveryOrderStatusForm deliveryOrderStatusForm,
            @PageableDefault(page = 0, size = 2) Pageable pageable, Model model) {
        Page<JPADeliveryOrder> deliveryOrderPage =
                jpaDeliveryOrderService.findByEscapeSearchMod(modSearchVal, pageable);
        model.addAttribute("page", deliveryOrderPage);
        model.addAttribute("isPageable", true);
        model.addAttribute("modSearch", true);
        model.addAttribute("deliveryOrderStatusForm", deliveryOrderStatusForm);
        return "djpa/orderList";
    }

    @RequestMapping(params = "escSrchDash", method = RequestMethod.GET)
    public String escapeDashSerach(@RequestParam("escapeSrchVal") String modSearchVal,
            DeliveryOrderStatusForm deliveryOrderStatusForm,
            @PageableDefault(page = 0, size = 2) Pageable pageable, Model model) {
        Page<JPADeliveryOrder> deliveryOrderPage =
                jpaDeliveryOrderService.findByEscapeSearchDash(modSearchVal, pageable);
        model.addAttribute("page", deliveryOrderPage);
        model.addAttribute("isPageable", true);
        model.addAttribute("dashSrch", true);
        model.addAttribute("deliveryOrderStatusForm", deliveryOrderStatusForm);
        return "djpa/orderList";
    }

    @RequestMapping(params = "srchMatchInLogic", method = RequestMethod.GET)
    public String escapeModSerachMatchInLogic(@RequestParam("escapeSrchVal") String modSearchVal,
            DeliveryOrderStatusForm deliveryOrderStatusForm,
            @PageableDefault(page = 0, size = 2) Pageable pageable, Model model) {
        Page<JPADeliveryOrder> deliveryOrderPage =
                jpaDeliveryOrderService.findByEscapeSearchMatchInLogic(modSearchVal, pageable);
        model.addAttribute("page", deliveryOrderPage);
        model.addAttribute("isPageable", true);
        model.addAttribute("matchInLogic", true);
        model.addAttribute("deliveryOrderStatusForm", deliveryOrderStatusForm);
        return "djpa/orderList";
    }

    @RequestMapping(method = RequestMethod.GET, params = "methodNameConven")
    public String fetchUsingMethodNamingConvention(
            @PageableDefault(page = 0, size = 2) Pageable pageable,
            DeliveryOrderStatusForm deliveryOrderStatusForm, Model model) {
        String statusCode = deliveryOrderStatusForm.getDelOrderStatus();
        Page<JPADeliveryOrder> deliveryOrderPage =
                jpaDeliveryOrderService.findByStatusCode(statusCode, pageable);
        model.addAttribute("page", deliveryOrderPage);
        model.addAttribute("isPageable", true);
        model.addAttribute("deliveryOrderStatusForm", deliveryOrderStatusForm);
        return "djpa/orderList";
    }

    @RequestMapping(method = RequestMethod.GET, params = "nativeQuery")
    public String fetchUsingNativeQuery(DeliveryOrderStatusForm deliveryOrderStatusForm,
            Model model) {
        String statusCode = deliveryOrderStatusForm.getDelOrderStatus();
        List<JPADeliveryOrder> deliveryOrderPage =
                jpaDeliveryOrderService.findUsingNativeQuery(statusCode);
        model.addAttribute("deliveryOrders", deliveryOrderPage);
        model.addAttribute("deliveryOrderStatusForm", deliveryOrderStatusForm);
        return "djpa/orderList";
    }

    @RequestMapping(method = RequestMethod.GET, params = "qHint")
    public String fetchUsingQueryHint(DeliveryOrderStatusForm deliveryOrderStatusForm,
            Model model) {
        String statusCode = deliveryOrderStatusForm.getDelOrderStatus();
        List<JPADeliveryOrder> deliveryOrderPage = jpaDeliveryOrderService.queryHint(statusCode, 5);
        model.addAttribute("deliveryOrders", deliveryOrderPage);
        model.addAttribute("deliveryOrderStatusForm", deliveryOrderStatusForm);
        return "djpa/orderList";
    }

    @RequestMapping(params = "matchCond", method = RequestMethod.GET)
    public String list(@RequestParam("delOrderStatus") String delOrderStatus,
            DeliveryOrderStatusForm deliveryOrderStatusForm, @PageableDefault(page = 0, value = 2,
                    sort = {"acceptDateTime"}, direction = Direction.DESC) Pageable pageable,
            Model model) {
        Page<JPADeliveryOrder> deliveryOrderPage =
                jpaDeliveryOrderService.findEntityPageMatchingCondition(delOrderStatus, pageable);
        model.addAttribute("page", deliveryOrderPage);
        model.addAttribute("isPageable", true);
        model.addAttribute("isMatchCondTC", true);
        model.addAttribute("deliveryOrderStatusForm", deliveryOrderStatusForm);
        return "djpa/orderList";
    }

    @RequestMapping(params = "addMethodToIndRepo", method = RequestMethod.GET)
    public String searchByMethodAddedtoRepository(
            DeliveryOrderStatusForm deliveryOrderStatusForm, @PageableDefault(page = 0, value = 2,
                    sort = {"delivery_no"}, direction = Direction.ASC) Pageable pageable,
            Model model) {
        DeliveryOrderCriteria criteria = new DeliveryOrderCriteria();

        List<String> statList = new ArrayList<String>();

        statList.add(deliveryOrderStatusForm.getDelOrderStatus());

        criteria.setDeliveryStatus(statList);

        Page<JPADeliveryOrder> deliveryOrderPage =
                jpaDeliveryOrderService.search(criteria, pageable);
        model.addAttribute("page", deliveryOrderPage);
        model.addAttribute("isPageable", true);
        model.addAttribute("deliveryOrderStatusForm", deliveryOrderStatusForm);
        return "djpa/orderList";
    }

    @RequestMapping(method = RequestMethod.GET, params = "dynaCond")
    public String fetchUsingDynamicParam(DeliveryOrderStatusForm deliveryOrderStatusForm,
            Model model) {
        String statusCode = deliveryOrderStatusForm.getDelOrderStatus();
        List<String> statCodeList = new ArrayList<String>();
        if (null != statusCode && statusCode.contains(",")) {
            String statArray[] = statusCode.split(",");
            statCodeList = Arrays.asList(statArray);
        } else if (!"".equals(statusCode)) {
            statCodeList.add(statusCode);
        }
        DeliveryOrderCriteria deliveryOrderCriteria = new DeliveryOrderCriteria();

        deliveryOrderCriteria.setDeliveryStatus(statCodeList);
        deliveryOrderCriteria.setDeliveryNumber(deliveryOrderStatusForm.getDeliverNumber());
        deliveryOrderCriteria.setDeliveryType(deliveryOrderStatusForm.getDeliveryType());

        List<JPADeliveryOrder> deliveryOrderList =
                jpaDeliveryOrderService.searchUsingDynamicCond(deliveryOrderCriteria);
        model.addAttribute("deliveryOrders", deliveryOrderList);
        model.addAttribute("deliveryOrderStatusForm", deliveryOrderStatusForm);
        return "djpa/orderList";
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Integer id,
            @Validated DeliveryOrderStatusForm form, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return updateRedo(form, model);
        }

        JPADeliveryOrder deliveryOrder = jpaDeliveryOrderService.findById(id);
        if (null == deliveryOrder) {
            deliveryOrder = new JPADeliveryOrder();
        }
        model.addAttribute("delOrder", deliveryOrder);

        return "djpa/jpaDeliverOrderUpdateForm";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String dispalyDeliveryOrderForm(Model model) {
        model.addAttribute("deliveryOrderStatusForm", new DeliveryOrderStatusForm());
        return "djpa/registerDeliverOrderForm";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST, params = "add")
    public String registerOrder(DeliveryOrderStatusForm form, RedirectAttributes redirectAttrs,
            Model model) {

        JPADeliveryOrder jpaDeliveryOrder = beaMapper.map(form, JPADeliveryOrder.class);
        jpaDeliveryOrder = jpaDeliveryOrderService.save(jpaDeliveryOrder);

        DeliveryOrderStatusForm deliveryOrderStatusForm =
                beaMapper.map(jpaDeliveryOrder, DeliveryOrderStatusForm.class);

        redirectAttrs.addFlashAttribute("deliveryOrderStatusForm", deliveryOrderStatusForm);
        redirectAttrs.addFlashAttribute("id", jpaDeliveryOrder.getDeliverNumber());
        System.out.println(jpaDeliveryOrder.getDeliverNumber());
        return "redirect:" + jpaDeliveryOrder.getDeliverNumber() + "/display";
    }

    @RequestMapping(value = "{id}/display", method = RequestMethod.GET)
    public String dispalyOrderDetail(@PathVariable("id") Integer id, DeliveryOrderStatusForm form,
            Model model) {
        model.addAttribute("deliveryOrderStatusForm", form);
        JPADeliveryOrder delOrder = jpaDeliveryOrderService.findById(id);
        DeliveryOrderStatusForm jpaDeliveryOrder =
                beaMapper.map(delOrder, DeliveryOrderStatusForm.class);
        model.addAttribute("delOrder", jpaDeliveryOrder);
        return "djpa/jpaDeliverOrderUpdateForm";
    }

    @RequestMapping(method = RequestMethod.GET, params = "orderDet")
    public String dispalyOrders(DeliveryOrderStatusForm form, Model model) {
        model.addAttribute("deliveryOrderStatusForm", form);
        // Need to get JpaOrder.
        jpaOrderService.getOrderDetail(1);
        return "djpa/jpaDeliverOrderUpdateForm";
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST, params = "redo")
    public String updateRedo(DeliveryOrderStatusForm form, Model model) {
        model.addAttribute("deliveryOrderStatusForm", form);

        return "djpa/jpaDeliverOrderUpdateForm";
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST, params = "cancel")
    public String orderList(Model model) {

        return "redirect:/djpa/delivery/order";
    }

}
