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

import org.springframework.dao.PessimisticLockingFailureException;
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
import org.terasoluna.gfw.common.message.ResultMessages;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Stock;
import jp.co.ntt.fw.spring.functionaltest.domain.service.excn.StockPessimisticLockService;

@Controller
@RequestMapping("jsp/0402")
public class EXCN_JSP_0402Controller {

    @Inject
    EXCNBeanMapper beanMapper;

    @Inject
    StockPessimisticLockService stockPessimisticLockService;

    @ModelAttribute()
    public StockForm setUpForm() {
        return new StockForm();
    }

    @GetMapping(value = "001")
    public String handle001(StockForm form, Model model) {
        beanMapper.map(stockPessimisticLockService.findOne("EXCN0402001"), form);
        return "jsp/excn/pessimisticLockView";
    }

    @GetMapping(value = "002")
    public String handle002(StockForm form, Model model) {
        beanMapper.map(stockPessimisticLockService.findOne("EXCN0402002"), form);
        return "jsp/excn/pessimisticLockExceptionHandlingByRequestView";
    }

    @PostMapping(value = "001", params = "buy")
    public String handle001Buy(StockForm form, Model model, RedirectAttributes attributes) {

        Stock stock = beanMapper.map(form);

        stock = stockPessimisticLockService.buy(stock, form.getPurchasingQuantity());

        model.addAttribute("stock", stock);
        model.addAttribute(ResultMessages.success().add("excn.result.success"));
        return "jsp/excn/updateCompleteView";
    }

    @PostMapping(value = "002", params = "buy")
    public String handle002Buy(StockForm form, Model model, RedirectAttributes attributes) {

        Stock stock = beanMapper.map(form);

        try {
            stock = stockPessimisticLockService.buy(stock, form.getPurchasingQuantity());
        } catch (PessimisticLockingFailureException e) {
            model.addAttribute(ResultMessages.warning().add("excn.result.exclusivebyrequest"));
            return "jsp/common/error/exclusiveLockError";
        }

        model.addAttribute("stock", stock);
        model.addAttribute(ResultMessages.success().add("excn.result.success"));
        return "jsp/excn/updateCompleteView";
    }

    @ExceptionHandler(PessimisticLockingFailureException.class)
    public ModelAndView handlePessimisticLockingFailureException(
            PessimisticLockingFailureException e) {

        ExtendedModelMap modelMap = new ExtendedModelMap();

        modelMap.addAttribute(ResultMessages.warning().add("excn.result.exclusivebycontroller"));
        return new ModelAndView("jsp/common/error/exclusiveLockError", modelMap);
    }
}
