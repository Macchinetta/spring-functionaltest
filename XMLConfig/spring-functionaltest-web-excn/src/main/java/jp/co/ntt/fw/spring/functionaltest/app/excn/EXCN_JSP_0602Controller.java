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
package jp.co.ntt.fw.spring.functionaltest.app.excn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.DataBaseInfo;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAStock;
import jp.co.ntt.fw.spring.functionaltest.domain.service.djpa.JPAStockOptimisticLockService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.djpa.JPAStockPessimisticLockService;

@Controller
@RequestMapping("jsp/0602")
public class EXCN_JSP_0602Controller {

    private static final Logger logger = LoggerFactory.getLogger(
            EXCN_JSP_0602Controller.class);

    @Inject
    EXCNBeanMapper beanMapper;

    @Inject
    JPAStockOptimisticLockService stockOptimisticLockService;

    @Inject
    JPAStockPessimisticLockService stockPessimisticLockService;

    @Inject
    JpaVendorAdapter jpaVendorAdapter;

    @ModelAttribute()
    public StockForm setUpForm() {
        return new StockForm();
    }

    @GetMapping(value = "001")
    public String handle001(StockForm form, Model model) {
        beanMapper.mapJPA(stockOptimisticLockService.findOne("EXCN0602001"),
                form);
        return "jsp/excn/jpaOptimisticLockViewExcpHandler";
    }

    @GetMapping(value = "002")
    public String handle002(StockForm form, Model model) {
        beanMapper.mapJPA(stockPessimisticLockService.findOne("EXCN0602002"),
                form);
        return "jsp/excn/jpaPessimisticLockViewExcpHandler";
    }

    @PostMapping(value = "001", params = "buy")
    public String handle001Buy(StockForm form, Model model,
            RedirectAttributes attributes) {

        JPAStock stock = beanMapper.mapJPA(form);

        stock = stockOptimisticLockService.buy(stock, form
                .getPurchasingQuantity());

        model.addAttribute("stock", stock);
        model.addAttribute(ResultMessages.success().add("excn.result.success"));
        return "jsp/excn/updateCompleteView";
    }

    @PostMapping(value = "002", params = "buy")
    public String handle002Buy(StockForm form, Model model,
            RedirectAttributes attributes) {

        String dataBaseName = DataBaseInfo.getDataBaseID(
                (HibernateJpaVendorAdapter) jpaVendorAdapter);
        logger.debug("Current Database Under Test ::" + dataBaseName);

        JPAStock stock = beanMapper.mapJPA(form);
        model.addAttribute("databaseId", dataBaseName);
        stock = stockPessimisticLockService.buyExcp(stock, form
                .getPurchasingQuantity(), form.getSleepMillis());

        model.addAttribute("stock", stock);
        model.addAttribute(ResultMessages.success().add("excn.result.success"));
        return "jsp/excn/updateCompleteView";
    }

    @ExceptionHandler(PessimisticLockingFailureException.class)
    // (1)
    public ModelAndView handlePessimisticLockingFailureException(
            PessimisticLockingFailureException e) {
        // (2)
        ExtendedModelMap modelMap = new ExtendedModelMap();
        ResultMessages resultMessages = ResultMessages.warning();
        resultMessages.add(ResultMessage.fromText("Other user updated!!"));
        modelMap.addAttribute(setUpForm());
        modelMap.addAttribute(resultMessages);
        String viewName = "jsp/common/error/exclusiveLockError";
        return new ModelAndView(viewName, modelMap);
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    // (1)
    public ModelAndView handleOptimisticLockingFailureException(
            OptimisticLockingFailureException e) {
        ExtendedModelMap modelMap = new ExtendedModelMap();
        ResultMessages resultMessages = ResultMessages.warning();
        resultMessages.add(ResultMessage.fromText("Other user updated!!"));
        modelMap.addAttribute(setUpForm());
        modelMap.addAttribute(resultMessages);
        String viewName = "jsp/common/error/exclusiveLockError";
        return new ModelAndView(viewName, modelMap);
    }
}
