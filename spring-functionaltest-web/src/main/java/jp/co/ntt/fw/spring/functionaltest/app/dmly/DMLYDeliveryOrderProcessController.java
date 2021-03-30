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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSessionFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.ntt.fw.spring.functionaltest.domain.model.DeliveryOrder;
import jp.co.ntt.fw.spring.functionaltest.domain.model.DeliveryType;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dmly.DeliveryOrderInitializerService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dmly.DeliveryOrderMandatoryJTA12SharedService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dmly.DeliveryOrderMandatorySharedService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dmly.DeliveryOrderNestedSharedService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dmly.DeliveryOrderNeverJTA12SharedService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dmly.DeliveryOrderNeverSharedService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dmly.DeliveryOrderNotSupportedJTA12SharedService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dmly.DeliveryOrderNotSupportedSharedService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dmly.DeliveryOrderPropagationJTA12Service;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dmly.DeliveryOrderPropagationService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dmly.DeliveryOrderRequiredJTA12SharedService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dmly.DeliveryOrderRequiredSharedService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dmly.DeliveryOrderRequiresNewJTA12SharedService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dmly.DeliveryOrderRequiresNewSharedService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dmly.DeliveryOrderService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dmly.DeliveryOrderSupportsJTA12SharedService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dmly.DeliveryOrderSupportsSharedService;

@RequestMapping("dmly/deliveryorder")
@Controller
public class DMLYDeliveryOrderProcessController {

    private static final Logger logger = LoggerFactory.getLogger(
            DMLYDeliveryOrderProcessController.class);

    @Inject
    SqlSessionFactory sqlSessionFactory;

    @Inject
    DeliveryOrderInitializerService deliveryOrderInitializerService;

    @Inject
    DeliveryOrderPropagationService deliveryOrderPropagationService;

    @Inject
    DeliveryOrderRequiredSharedService deliveryOrderRequiredService;

    @Inject
    DeliveryOrderRequiresNewSharedService deliveryOrderRequiresNewService;

    @Inject
    DeliveryOrderSupportsSharedService deliveryOrderSupportsService;

    @Inject
    DeliveryOrderNotSupportedSharedService deliveryOrderNotSupportedService;

    @Inject
    DeliveryOrderMandatorySharedService deliveryOrderMandatoryService;

    @Inject
    DeliveryOrderNeverSharedService deliveryOrderNeverService;

    @Inject
    DeliveryOrderNestedSharedService deliveryOrderNestedService;

    @Inject
    DeliveryOrderService deliveryOrderService;

    @Inject
    DeliveryOrderPropagationJTA12Service deliveryOrderPropagationJTA12Service;

    @Inject
    DeliveryOrderRequiredJTA12SharedService deliveryOrderRequiredJTA12SharedService;

    @Inject
    DeliveryOrderRequiresNewJTA12SharedService deliveryOrderRequiresNewJTA12Service;

    @Inject
    DeliveryOrderSupportsJTA12SharedService deliveryOrderSupportsJTA12Service;

    @Inject
    DeliveryOrderNotSupportedJTA12SharedService deliveryOrderNotSupportedJTA12Service;

    @Inject
    DeliveryOrderMandatoryJTA12SharedService deliveryOrderMandatoryJTA12Service;

    @Inject
    DeliveryOrderNeverJTA12SharedService deliveryOrderNeverJTA12Service;

    @ModelAttribute
    public DeliveryOrderProcessForm setUpForm() {
        return new DeliveryOrderProcessForm();
    }

    @RequestMapping(value = "initprocess")
    public String init(Model model) {
        deliveryOrderInitializerService.init();
        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.GET)
    public String list(Model model) {
        String databaseId = sqlSessionFactory.getConfiguration()
                .getDatabaseId();
        model.addAttribute("databaseId", databaseId);
        return "dmly/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "successRequired")
    public String successRequired(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        deliveryOrderRequiredService.insert(firstOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackRequired")
    public String rollbackRequired(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付NG");
        firstOrderList.add(firstOrder);

        try {
            deliveryOrderRequiredService.insert(firstOrderList);
        } catch (DataAccessException e) {
            logger.error("ProcessController exception catch.", e);
        }

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "successRequiredRequired")
    public String successRequiredRequired(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(13);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名13");
        secondOrder.setSenderAddress("送り主住所13");
        secondOrder.setRecieverName("送り先名13");
        secondOrder.setRecieverAddress("送り先住所13");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー13");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        deliveryOrderPropagationService.propagateRequiredRequired(
                firstOrderList, secondOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackRequiredRequired")
    public String rollbackRequiredRequired(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(13);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名13");
        secondOrder.setSenderAddress("送り主住所13");
        secondOrder.setRecieverName("送り先名13");
        secondOrder.setRecieverAddress("送り先住所13");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー13");
        secondOrder.setDeliveryStatus("受付NG");
        secondOrderList.add(secondOrder);

        try {
            deliveryOrderPropagationService.propagateRequiredRequired(
                    firstOrderList, secondOrderList);
        } catch (DataAccessException e) {
            logger.error("ProcessController exception catch.", e);
        }

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "successRequiresNew")
    public String successRequiresNew(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        deliveryOrderRequiresNewService.insert(firstOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackRequiresNew")
    public String rollbackRequiresNew(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付NG");
        firstOrderList.add(firstOrder);

        try {
            deliveryOrderRequiresNewService.insert(firstOrderList);
        } catch (DataAccessException e) {
            logger.error("ProcessController exception catch.", e);
        }

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "successRequiredRequiresNew")
    public String successRequiredRequiresNew(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(13);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名13");
        secondOrder.setSenderAddress("送り主住所13");
        secondOrder.setRecieverName("送り先名13");
        secondOrder.setRecieverAddress("送り先住所13");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー13");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        deliveryOrderPropagationService.propagateRequiredRequiresNew(
                firstOrderList, secondOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackRequiredRequiresNew")
    public String rollbackRequiredRequiresNew(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(13);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名13");
        secondOrder.setSenderAddress("送り主住所13");
        secondOrder.setRecieverName("送り先名13");
        secondOrder.setRecieverAddress("送り先住所13");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー13");
        secondOrder.setDeliveryStatus("受付NG");
        secondOrderList.add(secondOrder);

        deliveryOrderPropagationService.propagateRequiredRequiresNew(
                firstOrderList, secondOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackRequiredRequiresNew2")
    public String rollbackRequiredRequiresNew2(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(13);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名13");
        firstOrder.setSenderAddress("送り主住所13");
        firstOrder.setRecieverName("送り先名13");
        firstOrder.setRecieverAddress("送り先住所13");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー13");
        firstOrder.setDeliveryStatus("受付NG");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(11);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名11");
        secondOrder.setSenderAddress("送り主住所11");
        secondOrder.setRecieverName("送り先名11");
        secondOrder.setRecieverAddress("送り先住所11");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー11");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        try {
            deliveryOrderPropagationService.propagateRequiredRequiresNew2(
                    firstOrderList, secondOrderList);
        } catch (DataAccessException e) {
            logger.error("ProcessController exception catch.", e);
        }

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "successSupports")
    public String successSupports(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        deliveryOrderSupportsService.insert(firstOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackSupports")
    public String rollbackSupports(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付NG");
        firstOrderList.add(firstOrder);

        try {
            deliveryOrderSupportsService.insert(firstOrderList);
        } catch (DataAccessException e) {
            logger.error("ProcessController exception catch.", e);
        }

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "successRequiredSupports")
    public String successRequiredSupports(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(13);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名13");
        secondOrder.setSenderAddress("送り主住所13");
        secondOrder.setRecieverName("送り先名13");
        secondOrder.setRecieverAddress("送り先住所13");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー13");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        deliveryOrderPropagationService.propagateRequiredSupports(
                firstOrderList, secondOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackRequiredSupports")
    public String rollbackRequiredSupports(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(13);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名13");
        secondOrder.setSenderAddress("送り主住所13");
        secondOrder.setRecieverName("送り先名13");
        secondOrder.setRecieverAddress("送り先住所13");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー13");
        secondOrder.setDeliveryStatus("受付NG");
        secondOrderList.add(secondOrder);

        try {
            deliveryOrderPropagationService.propagateRequiredSupports(
                    firstOrderList, secondOrderList);
        } catch (DataAccessException e) {
            logger.error("ProcessController exception catch.", e);
        }

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "successNotSupported")
    public String successNotSupported(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        deliveryOrderNotSupportedService.insert(firstOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackNotSupported")
    public String rollbackNotSupported(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付NG");
        firstOrderList.add(firstOrder);

        try {
            deliveryOrderNotSupportedService.insert(firstOrderList);
        } catch (DataAccessException e) {
            logger.error("ProcessController exception catch.", e);
        }

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "successRequiredNotSupported")
    public String successRequiredNotSupported(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(13);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名13");
        secondOrder.setSenderAddress("送り主住所13");
        secondOrder.setRecieverName("送り先名13");
        secondOrder.setRecieverAddress("送り先住所13");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー13");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        deliveryOrderPropagationService.propagateRequiredNotSupported(
                firstOrderList, secondOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackRequiredNotSupported")
    public String rollbackRequiredNotSupported(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(13);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名13");
        secondOrder.setSenderAddress("送り主住所13");
        secondOrder.setRecieverName("送り先名13");
        secondOrder.setRecieverAddress("送り先住所13");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー13");
        secondOrder.setDeliveryStatus("受付NG");
        secondOrderList.add(secondOrder);

        deliveryOrderPropagationService.propagateRequiredNotSupported(
                firstOrderList, secondOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "exceptionMandatory")
    public String exceptionMandatory(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        try {
            deliveryOrderMandatoryService.insert(firstOrderList);
        } catch (IllegalTransactionStateException e) {
            logger.error("ProcessController exception catch.", e);
        }

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "successRequiredMandatory")
    public String successRequiredMandatory(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(13);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名13");
        secondOrder.setSenderAddress("送り主住所13");
        secondOrder.setRecieverName("送り先名13");
        secondOrder.setRecieverAddress("送り先住所13");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー13");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        deliveryOrderPropagationService.propagateRequiredMandatory(
                firstOrderList, secondOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackRequiredMandatory")
    public String rollbackRequiredMandatory(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(13);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名13");
        secondOrder.setSenderAddress("送り主住所13");
        secondOrder.setRecieverName("送り先名13");
        secondOrder.setRecieverAddress("送り先住所13");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー13");
        secondOrder.setDeliveryStatus("受付NG");
        secondOrderList.add(secondOrder);

        try {
            deliveryOrderPropagationService.propagateRequiredMandatory(
                    firstOrderList, secondOrderList);
        } catch (DataAccessException e) {
            logger.error("ProcessController exception catch.", e);
        }

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "successNever")
    public String successNever(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        deliveryOrderNeverService.insert(firstOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackNever")
    public String rollbackNever(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付NG");
        firstOrderList.add(firstOrder);

        try {
            deliveryOrderNeverService.insert(firstOrderList);
        } catch (DataAccessException e) {
            logger.error("ProcessController exception catch.", e);
        }

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "exceptionRequiredNever")
    public String exceptionRequiredNever(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(13);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名13");
        secondOrder.setSenderAddress("送り主住所13");
        secondOrder.setRecieverName("送り先名13");
        secondOrder.setRecieverAddress("送り先住所13");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー13");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        deliveryOrderPropagationService.propagateRequiredNever(firstOrderList,
                secondOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "successNested")
    public String successNested(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        deliveryOrderNestedService.insert(firstOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackNested")
    public String rollbackNested(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付NG");
        firstOrderList.add(firstOrder);

        try {
            deliveryOrderNestedService.insert(firstOrderList);
        } catch (DataAccessException e) {
            logger.error("ProcessController exception catch.", e);
        }

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "successRequiredNested")
    public String successRequiredNested(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(13);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名13");
        secondOrder.setSenderAddress("送り主住所13");
        secondOrder.setRecieverName("送り先名13");
        secondOrder.setRecieverAddress("送り先住所13");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー13");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        deliveryOrderPropagationService.propagateRequiredNested(firstOrderList,
                secondOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackRequiredNested")
    public String rollbackRequiredNested(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(13);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名13");
        secondOrder.setSenderAddress("送り主住所13");
        secondOrder.setRecieverName("送り先名13");
        secondOrder.setRecieverAddress("送り先住所13");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー13");
        secondOrder.setDeliveryStatus("受付NG");
        secondOrderList.add(secondOrder);

        deliveryOrderPropagationService.propagateRequiredNested(firstOrderList,
                secondOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackRequiredNested2")
    public String rollbackRequiredNested2(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(13);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名13");
        firstOrder.setSenderAddress("送り主住所13");
        firstOrder.setRecieverName("送り先名13");
        firstOrder.setRecieverAddress("送り先住所13");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー13");
        firstOrder.setDeliveryStatus("受付NG");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(11);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名11");
        secondOrder.setSenderAddress("送り主住所11");
        secondOrder.setRecieverName("送り先名11");
        secondOrder.setRecieverAddress("送り先住所11");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー11");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        try {
            deliveryOrderPropagationService.propagateRequiredNested2(
                    firstOrderList, secondOrderList);
        } catch (DataAccessException e) {
            logger.error("ProcessController exception catch.", e);
        }

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackRequiredNested3")
    public String rollbackRequiredNested3(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(13);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名13");
        firstOrder.setSenderAddress("送り主住所13");
        firstOrder.setRecieverName("送り先名13");
        firstOrder.setRecieverAddress("送り先住所13");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー13");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(11);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名11");
        secondOrder.setSenderAddress("送り主住所11");
        secondOrder.setRecieverName("送り先名11");
        secondOrder.setRecieverAddress("送り先住所11");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー11");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付NG");
        secondOrderList.add(secondOrder);

        deliveryOrderPropagationService.propagateRequiredNested3(firstOrderList,
                secondOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "successRequiredJTA12")
    public String successRequiredJTA12(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        deliveryOrderRequiredJTA12SharedService.insert(firstOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackRequiredJTA12")
    public String rollbackRequiredJTA12(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付NG");
        firstOrderList.add(firstOrder);

        try {
            deliveryOrderRequiredJTA12SharedService.insert(firstOrderList);
        } catch (DataAccessException e) {
            logger.error("ProcessController exception catch.", e);
        }

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "successRequiredRequiredJTA12")
    public String successRequiredRequiredJTA12(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(13);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名13");
        secondOrder.setSenderAddress("送り主住所13");
        secondOrder.setRecieverName("送り先名13");
        secondOrder.setRecieverAddress("送り先住所13");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー13");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        deliveryOrderPropagationJTA12Service.propagateRequiredRequired(
                firstOrderList, secondOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackRequiredRequiredJTA12")
    public String rollbackRequiredRequiredJTA12(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(13);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名13");
        secondOrder.setSenderAddress("送り主住所13");
        secondOrder.setRecieverName("送り先名13");
        secondOrder.setRecieverAddress("送り先住所13");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー13");
        secondOrder.setDeliveryStatus("受付NG");
        secondOrderList.add(secondOrder);

        try {
            deliveryOrderPropagationJTA12Service.propagateRequiredRequired(
                    firstOrderList, secondOrderList);
        } catch (DataAccessException e) {
            logger.error("ProcessController exception catch.", e);
        }

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "successRequiresNewJTA12")
    public String successRequiresNewJTA12(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        deliveryOrderRequiresNewJTA12Service.insert(firstOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackRequiresNewJTA12")
    public String rollbackRequiresNewJTA12(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付NG");
        firstOrderList.add(firstOrder);

        try {
            deliveryOrderRequiresNewJTA12Service.insert(firstOrderList);
        } catch (DataAccessException e) {
            logger.error("ProcessController exception catch.", e);
        }

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "successRequiredRequiresNewJTA12")
    public String successRequiredRequiresNewJTA12(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(13);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名13");
        secondOrder.setSenderAddress("送り主住所13");
        secondOrder.setRecieverName("送り先名13");
        secondOrder.setRecieverAddress("送り先住所13");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー13");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        deliveryOrderPropagationJTA12Service.propagateRequiredRequiresNew(
                firstOrderList, secondOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackRequiredRequiresNewJTA12")
    public String rollbackRequiredRequiresNewJTA12(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(13);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名13");
        secondOrder.setSenderAddress("送り主住所13");
        secondOrder.setRecieverName("送り先名13");
        secondOrder.setRecieverAddress("送り先住所13");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー13");
        secondOrder.setDeliveryStatus("受付NG");
        secondOrderList.add(secondOrder);

        deliveryOrderPropagationJTA12Service.propagateRequiredRequiresNew(
                firstOrderList, secondOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackRequiredRequiresNew2JTA12")
    public String rollbackRequiredRequiresNew2JTA12(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(13);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名13");
        firstOrder.setSenderAddress("送り主住所13");
        firstOrder.setRecieverName("送り先名13");
        firstOrder.setRecieverAddress("送り先住所13");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー13");
        firstOrder.setDeliveryStatus("受付NG");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(11);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名11");
        secondOrder.setSenderAddress("送り主住所11");
        secondOrder.setRecieverName("送り先名11");
        secondOrder.setRecieverAddress("送り先住所11");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー11");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        try {
            deliveryOrderPropagationJTA12Service.propagateRequiredRequiresNew2(
                    firstOrderList, secondOrderList);
        } catch (DataAccessException e) {
            logger.error("ProcessController exception catch.", e);
        }

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "successSupportsJTA12")
    public String successSupportsJTA12(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        deliveryOrderSupportsJTA12Service.insert(firstOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackSupportsJTA12")
    public String rollbackSupportsJTA12(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付NG");
        firstOrderList.add(firstOrder);

        try {
            deliveryOrderSupportsJTA12Service.insert(firstOrderList);
        } catch (DataAccessException e) {
            logger.error("ProcessController exception catch.", e);
        }

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "successRequiredSupportsJTA12")
    public String successRequiredSupportsJTA12(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(13);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名13");
        secondOrder.setSenderAddress("送り主住所13");
        secondOrder.setRecieverName("送り先名13");
        secondOrder.setRecieverAddress("送り先住所13");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー13");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        deliveryOrderPropagationJTA12Service.propagateRequiredSupports(
                firstOrderList, secondOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackRequiredSupportsJTA12")
    public String rollbackRequiredSupportsJTA12(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(13);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名13");
        secondOrder.setSenderAddress("送り主住所13");
        secondOrder.setRecieverName("送り先名13");
        secondOrder.setRecieverAddress("送り先住所13");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー13");
        secondOrder.setDeliveryStatus("受付NG");
        secondOrderList.add(secondOrder);

        try {
            deliveryOrderPropagationJTA12Service.propagateRequiredSupports(
                    firstOrderList, secondOrderList);
        } catch (DataAccessException e) {
            logger.error("ProcessController exception catch.", e);
        }

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "successNotSupportedJTA12")
    public String successNotSupportedJTA12(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        deliveryOrderNotSupportedJTA12Service.insert(firstOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackNotSupportedJTA12")
    public String rollbackNotSupportedJTA12(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付NG");
        firstOrderList.add(firstOrder);

        try {
            deliveryOrderNotSupportedJTA12Service.insert(firstOrderList);
        } catch (DataAccessException e) {
            logger.error("ProcessController exception catch.", e);
        }

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "successRequiredNotSupportedJTA12")
    public String successRequiredNotSupportedJTA12(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(13);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名13");
        secondOrder.setSenderAddress("送り主住所13");
        secondOrder.setRecieverName("送り先名13");
        secondOrder.setRecieverAddress("送り先住所13");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー13");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        deliveryOrderPropagationJTA12Service.propagateRequiredNotSupported(
                firstOrderList, secondOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackRequiredNotSupportedJTA12")
    public String rollbackRequiredNotSupportedJTA12(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(13);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名13");
        secondOrder.setSenderAddress("送り主住所13");
        secondOrder.setRecieverName("送り先名13");
        secondOrder.setRecieverAddress("送り先住所13");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー13");
        secondOrder.setDeliveryStatus("受付NG");
        secondOrderList.add(secondOrder);

        deliveryOrderPropagationJTA12Service.propagateRequiredNotSupported(
                firstOrderList, secondOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "exceptionMandatoryJTA12")
    public String exceptionMandatoryJTA12(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        try {
            deliveryOrderMandatoryJTA12Service.insert(firstOrderList);
        } catch (IllegalTransactionStateException e) {
            logger.error("ProcessController exception catch.", e);
        }

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "successRequiredMandatoryJTA12")
    public String successRequiredMandatoryJTA12(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(13);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名13");
        secondOrder.setSenderAddress("送り主住所13");
        secondOrder.setRecieverName("送り先名13");
        secondOrder.setRecieverAddress("送り先住所13");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー13");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        deliveryOrderPropagationJTA12Service.propagateRequiredMandatory(
                firstOrderList, secondOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackRequiredMandatoryJTA12")
    public String rollbackRequiredMandatoryJTA12(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(13);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名13");
        secondOrder.setSenderAddress("送り主住所13");
        secondOrder.setRecieverName("送り先名13");
        secondOrder.setRecieverAddress("送り先住所13");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー13");
        secondOrder.setDeliveryStatus("受付NG");
        secondOrderList.add(secondOrder);

        try {
            deliveryOrderPropagationJTA12Service.propagateRequiredMandatory(
                    firstOrderList, secondOrderList);
        } catch (DataAccessException e) {
            logger.error("ProcessController exception catch.", e);
        }

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "successNeverJTA12")
    public String successNeverJTA12(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        deliveryOrderNeverJTA12Service.insert(firstOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "rollbackNeverJTA12")
    public String rollbackNeverJTA12(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付NG");
        firstOrderList.add(firstOrder);

        try {
            deliveryOrderNeverJTA12Service.insert(firstOrderList);
        } catch (DataAccessException e) {
            logger.error("ProcessController exception catch.", e);
        }

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "exceptionRequiredNeverJTA12")
    public String exceptionRequiredNeverJTA12(Model model) {
        List<DeliveryOrder> firstOrderList = new ArrayList<DeliveryOrder>();
        List<DeliveryOrder> secondOrderList = new ArrayList<DeliveryOrder>();
        DeliveryOrder firstOrder = null;
        DeliveryOrder secondOrder = null;

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(10);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名10");
        firstOrder.setSenderAddress("送り主住所10");
        firstOrder.setRecieverName("送り先名10");
        firstOrder.setRecieverAddress("送り先住所10");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/10 10:10:10",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー10");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        firstOrder = new DeliveryOrder();
        firstOrder.setDeliveryNo(11);
        firstOrder.setDeliveryType(new DeliveryType(1, "通常"));
        firstOrder.setSenderName("送り主名11");
        firstOrder.setSenderAddress("送り主住所11");
        firstOrder.setRecieverName("送り先名11");
        firstOrder.setRecieverAddress("送り先住所11");
        firstOrder.setAcceptDatetime(DateTime.parse("2014/01/11 11:11:11",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        firstOrder.setCompletionDatetime(null);
        firstOrder.setDeliveryDriver("ドライバー11");
        firstOrder.setDeliveryStatus("受付");
        firstOrderList.add(firstOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(12);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名12");
        secondOrder.setSenderAddress("送り主住所12");
        secondOrder.setRecieverName("送り先名12");
        secondOrder.setRecieverAddress("送り先住所12");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/12 12:12:12",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー12");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        secondOrder = new DeliveryOrder();
        secondOrder.setDeliveryNo(13);
        secondOrder.setDeliveryType(new DeliveryType(1, "通常"));
        secondOrder.setSenderName("送り主名13");
        secondOrder.setSenderAddress("送り主住所13");
        secondOrder.setRecieverName("送り先名13");
        secondOrder.setRecieverAddress("送り先住所13");
        secondOrder.setAcceptDatetime(DateTime.parse("2014/01/13 13:13:13",
                DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")).toDate());
        secondOrder.setCompletionDatetime(null);
        secondOrder.setDeliveryDriver("ドライバー13");
        secondOrder.setDeliveryStatus("受付");
        secondOrderList.add(secondOrder);

        deliveryOrderPropagationJTA12Service.propagateRequiredNever(
                firstOrderList, secondOrderList);

        return "redirect:/dmly/deliveryorder/process";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "display")
    public String display(@Validated DeliveryOrderProcessForm form,
            BindingResult result, RedirectAttributes redirectAttrs,
            Model model) {
        DeliveryOrder deliveryOrder = deliveryOrderService.getOrderExists(form
                .getDisplayDeliveryNo());
        if (null == deliveryOrder) {
            deliveryOrder = new DeliveryOrder();
        }

        model.addAttribute("deliveryOrder", deliveryOrder);

        return "dmly/display";
    }

    @RequestMapping(value = "process", method = RequestMethod.POST, params = "back")
    public String back(RedirectAttributes redirectAttrs, Model model) {
        return "redirect:/dmly/deliveryorder/process";
    }

}
