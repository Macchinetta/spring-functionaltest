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
package jp.co.ntt.fw.spring.functionaltest.app.dmly;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.DeliveryOrder;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dmly.DeliveryOrderCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dmly.DeliveryOrderInitializerService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dmly.DeliveryOrderService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dmly.DeliveryOrderUpdateBLogic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("dmly/deliveryorder")
@Controller
public class DMLYDeliveryOrderListController {

    @Inject
    DeliveryOrderService deliveryOrderService;

    @Inject
    DeliveryOrderUpdateBLogic deliveryOrderUpdateBLogic;

    @Inject
    DeliveryOrderInitializerService deliveryOrderInitializerService;

    @ModelAttribute
    public DeliveryOrderListForm setUpForm() {
        return new DeliveryOrderListForm();
    }

    @RequestMapping(value = "initlist")
    public String init(Model model) {
        deliveryOrderInitializerService.init();
        return "redirect:/dmly/deliveryorder/list";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(@PageableDefault(page = 0, size = 10) Pageable pageable,
            Model model) {

        Page<DeliveryOrder> deliveryOrders = deliveryOrderService.getOrders(
                pageable);
        model.addAttribute("page", deliveryOrders);

        return "dmly/list";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST, params = "updateCriteria")
    public String update(
            @PageableDefault(page = 0, size = 10) Pageable pageable,
            DeliveryOrderListForm form, Model model) {

        DeliveryOrderCriteria criteria = new DeliveryOrderCriteria();
        criteria.setFromAcceptDatetime(form.getFromAcceptDatetime().toDate());
        criteria.setToAcceptDatetime(form.getToAcceptDatetime().toDate());
        criteria.setUpdateCompletionDatetime(form.getUpdateCompletionDatetime()
                .toDate());

        deliveryOrderUpdateBLogic.execute(criteria);

        return "redirect:/dmly/deliveryorder/list";
    }

}
