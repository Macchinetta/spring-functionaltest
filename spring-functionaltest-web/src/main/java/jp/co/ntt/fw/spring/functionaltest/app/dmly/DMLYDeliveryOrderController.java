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
package jp.co.ntt.fw.spring.functionaltest.app.dmly;

import java.util.List;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.DeliveryOrder;
import jp.co.ntt.fw.spring.functionaltest.domain.model.DeliveryStatus;
import jp.co.ntt.fw.spring.functionaltest.domain.model.DeliveryType;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dmly.DeliveryOrderService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dmly.DeliveryStatusService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dmly.DeliveryTypeService;

import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("dmly/deliveryorder")
@Controller
public class DMLYDeliveryOrderController {

    @Inject
    Mapper beanMapper;

    @Inject
    DeliveryTypeService deliveryTypeService;

    @Inject
    DeliveryStatusService deliveryStatusService;

    @Inject
    DeliveryOrderService deliveryOrderService;

    @ModelAttribute
    public DeliveryOrderForm setUpForm() {
        return new DeliveryOrderForm();
    }

    @RequestMapping(value = "register", method = RequestMethod.GET, params = "complete")
    public String registerComplete(@RequestParam("no") Integer no,
            Model model) {

        DeliveryOrder deliveryOrder = deliveryOrderService.getOrderExists(no);
        if (null == deliveryOrder) {
            deliveryOrder = new DeliveryOrder();
        }
        model.addAttribute("deliveryOrder", deliveryOrder);

        return "dmly/registerComplete";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST, params = "redo")
    public String registerRedo(DeliveryOrderForm form, Model model) {
        DeliveryOrder deliveryOrder = beanMapper.map(form, DeliveryOrder.class);
        List<DeliveryType> deliveryTypeList = deliveryTypeService.findAll();
        List<DeliveryStatus> deliveryStatusList = deliveryStatusService
                .findAll();
        model.addAttribute("deliveryOrder", deliveryOrder);
        model.addAttribute("deliveryTypeList", deliveryTypeList);
        model.addAttribute("deliveryStatusList", deliveryStatusList);

        return "dmly/registerForm";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST, params = "cancel")
    public String registerCancel(RedirectAttributes redirectAttrs,
            Model model) {
        return "redirect:/dmly/deliveryorder/list";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST, params = "back")
    public String registerBack(RedirectAttributes redirectAttrs, Model model) {
        return "redirect:/dmly/deliveryorder/list";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(DeliveryOrderForm form,
            RedirectAttributes redirectAttrs, Model model) {
        DeliveryOrder deliveryOrder = beanMapper.map(form, DeliveryOrder.class);
        deliveryOrderService.register(deliveryOrder);

        redirectAttrs.addAttribute("complete", "");
        redirectAttrs.addAttribute("no", form.getDeliveryNo());

        return "redirect:/dmly/deliveryorder/register";
    }

    @RequestMapping(value = "register", params = "form")
    public String registerForm(Model model) {
        List<DeliveryType> deliveryTypeList = deliveryTypeService.findAll();
        List<DeliveryStatus> deliveryStatusList = deliveryStatusService
                .findAll();
        model.addAttribute("deliveryTypeList", deliveryTypeList);
        model.addAttribute("deliveryStatusList", deliveryStatusList);

        return "dmly/registerForm";
    }

    @RequestMapping(value = "{no}/update")
    public String updateForm(@PathVariable("no") Integer no, Model model) {
        DeliveryOrder deliveryOrder = deliveryOrderService.getOrderExists(no);
        if (null == deliveryOrder) {
            deliveryOrder = new DeliveryOrder();
        }
        List<DeliveryType> deliveryTypeList = deliveryTypeService.findAll();
        List<DeliveryStatus> deliveryStatusList = deliveryStatusService
                .findAll();

        model.addAttribute("deliveryOrder", deliveryOrder);
        model.addAttribute("deliveryTypeList", deliveryTypeList);
        model.addAttribute("deliveryStatusList", deliveryStatusList);

        return "dmly/updateForm";
    }

    @RequestMapping(value = "{no}/update", method = RequestMethod.POST)
    public String update(@PathVariable("no") Integer no, DeliveryOrderForm form,
            RedirectAttributes redirectAttrs, Model model) {
        DeliveryOrder deliveryOrder = beanMapper.map(form, DeliveryOrder.class);
        deliveryOrderService.update(deliveryOrder);

        redirectAttrs.addAttribute("complete", "");
        redirectAttrs.addAttribute("no", no);

        return "redirect:/dmly/deliveryorder/{no}/update";
    }

    @RequestMapping(value = "{no}/update", method = RequestMethod.POST, params = "redo")
    public String updateRedo(DeliveryOrderForm form, Model model) {
        DeliveryOrder deliveryOrder = beanMapper.map(form, DeliveryOrder.class);
        List<DeliveryType> deliveryTypeList = deliveryTypeService.findAll();
        List<DeliveryStatus> deliveryStatusList = deliveryStatusService
                .findAll();
        model.addAttribute("deliveryOrder", deliveryOrder);
        model.addAttribute("deliveryTypeList", deliveryTypeList);
        model.addAttribute("deliveryStatusList", deliveryStatusList);

        return "dmly/updateForm";
    }

    @RequestMapping(value = "{no}/update", method = RequestMethod.POST, params = "cancel")
    public String updateCancel(@PathVariable("no") Integer no,
            RedirectAttributes redirectAttrs, Model model) {
        return "redirect:/dmly/deliveryorder/list";
    }

    @RequestMapping(value = "{no}/update", method = RequestMethod.GET, params = "complete")
    public String updateComplete(@PathVariable("no") Integer no, Model model) {

        DeliveryOrder deliveryOrder = deliveryOrderService.getOrderExists(no);
        if (null == deliveryOrder) {
            deliveryOrder = new DeliveryOrder();
        }
        model.addAttribute("deliveryOrder", deliveryOrder);

        return "dmly/updateComplete";
    }

    @RequestMapping(value = "{no}/update", method = RequestMethod.POST, params = "back")
    public String updateBack(@PathVariable("no") Integer no,
            RedirectAttributes redirectAttrs, Model model) {
        return "redirect:/dmly/deliveryorder/list";
    }

    @RequestMapping(value = "{no}/update", method = RequestMethod.POST, params = "delete")
    public String updateDelete(@PathVariable("no") Integer no,
            RedirectAttributes redirectAttrs, Model model) {
        deliveryOrderService.delete(no);

        redirectAttrs.addAttribute("complete", "");
        redirectAttrs.addAttribute("no", no);

        return "redirect:/dmly/deliveryorder/{no}/delete";
    }

    @RequestMapping(value = "{no}/delete", method = RequestMethod.GET, params = "complete")
    public String deleteComplete(@PathVariable("no") Integer no, Model model) {

        DeliveryOrder deliveryOrder = deliveryOrderService.getOrderExists(no);
        if (null == deliveryOrder) {
            deliveryOrder = new DeliveryOrder();
        }
        model.addAttribute("deliveryOrder", deliveryOrder);

        return "dmly/deleteComplete";
    }

    @RequestMapping(value = "{no}/delete", method = RequestMethod.POST, params = "back")
    public String delete(@PathVariable("no") Integer no,
            RedirectAttributes redirectAttrs, Model model) {
        return "redirect:/dmly/deliveryorder/list";
    }

}
