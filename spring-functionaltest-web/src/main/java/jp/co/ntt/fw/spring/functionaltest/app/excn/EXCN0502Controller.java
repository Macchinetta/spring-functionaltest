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
package jp.co.ntt.fw.spring.functionaltest.app.excn;

import javax.inject.Inject;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.github.dozermapper.core.Mapper;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAStock;
import jp.co.ntt.fw.spring.functionaltest.domain.service.djpa.JPAStockOptimisticLockService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.djpa.JPAStockPessimisticLockService;

@Controller
@RequestMapping("excn/0502")
public class EXCN0502Controller {

    @Inject
    Mapper beanMapper;

    @Inject
    JPAStockOptimisticLockService jpaStockOptimisticLockService;

    @Inject
    JPAStockPessimisticLockService jpaStockPessimisticLockService;

    @ModelAttribute
    public StockForm setUpForm() {
        JPAStock stock = jpaStockOptimisticLockService.findOne("EXCN0502001");
        return beanMapper.map(stock, StockForm.class);
    }

    @RequestMapping(value = "001", method = RequestMethod.GET)
    public String handle001(Model model) {
        return "excn/jpaOptimisticLockView";
    }

    @RequestMapping(value = "001", method = RequestMethod.POST, params = "buy")
    public String handle001Buy(StockForm form, Model model) {

        JPAStock stock = beanMapper.map(form, JPAStock.class);
        try {
            stock = jpaStockOptimisticLockService.buy(stock, form.getPurchasingQuantity());
        } catch (OptimisticLockingFailureException excp) {
            model.addAttribute(ResultMessages.warning().add("excn.result.exclusivebycontroller"));
            return "common/error/exclusiveLockError";
        }

        model.addAttribute("stock", stock);
        model.addAttribute(ResultMessages.success().add("excn.result.success"));
        return "excn/updateCompleteView";
    }

    @RequestMapping(value = "002", method = RequestMethod.GET)
    public String handle002(Model model) {
        JPAStock stock = jpaStockPessimisticLockService.findOne("EXCN0502002");
        StockForm stockForm = beanMapper.map(stock, StockForm.class);
        model.addAttribute("stockForm", stockForm);
        return "excn/jpaPessimisticLockView";
    }

    @RequestMapping(value = "002", method = RequestMethod.POST, params = "buy")
    public String handle002Buy(StockForm form, Model model) {

        JPAStock stock = beanMapper.map(form, JPAStock.class);
        try {
            stock = jpaStockPessimisticLockService.buy(stock, form.getPurchasingQuantity(),
                    form.getSleepMillis());
        } catch (PessimisticLockingFailureException excp) {
            model.addAttribute(ResultMessages.warning().add("excn.result.exclusivebycontroller"));
            return "common/error/exclusiveLockError";
        }

        model.addAttribute("stock", stock);
        model.addAttribute(ResultMessages.success().add("excn.result.success"));
        return "excn/updateCompleteView";
    }

}
