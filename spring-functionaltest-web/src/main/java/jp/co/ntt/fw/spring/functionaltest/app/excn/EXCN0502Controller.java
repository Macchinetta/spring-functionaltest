/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.excn;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAStock;
import jp.co.ntt.fw.spring.functionaltest.domain.service.djpa.JPAStockService;

import org.dozer.Mapper;
import org.hibernate.PessimisticLockException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.terasoluna.gfw.common.message.ResultMessages;

@Controller
@RequestMapping("excn/0502")
public class EXCN0502Controller {

    @Inject
    Mapper beanMapper;

    @Inject
    JPAStockService jpaStockService;

    @ModelAttribute
    public StockForm setUpForm() {
        JPAStock stock = jpaStockService.findOne("EXCN0502001");
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
            stock = jpaStockService.buyWithOptimisticLock(stock, form
                    .getPurchasingQuantity(), form.getSleepMillis());
        } catch (OptimisticLockingFailureException excp) {
            model.addAttribute(ResultMessages.warning().add(
                    "excn.result.exclusivebycontroller"));
            return "common/error/exclusiveLockError";
        }

        model.addAttribute("stock", stock);
        model.addAttribute(ResultMessages.success().add("excn.result.success"));
        return "excn/updateCompleteView";
    }

    @RequestMapping(value = "002", method = RequestMethod.GET)
    public String handle002(Model model) {
        JPAStock stock = jpaStockService.findOne("EXCN0502002");
        StockForm stockForm = beanMapper.map(stock, StockForm.class);
        model.addAttribute("stockForm", stockForm);
        return "excn/jpaPessimisticLockView";
    }

    @RequestMapping(value = "002", method = RequestMethod.POST, params = "buy")
    public String handle002Buy(StockForm form, Model model) {

        JPAStock stock = beanMapper.map(form, JPAStock.class);
        try {
            stock = jpaStockService.buyWithPessimisticLock(stock, form
                    .getPurchasingQuantity(), form.getSleepMillis());
        } catch (PessimisticLockingFailureException excp) {
            model.addAttribute(ResultMessages.warning().add(
                    "excn.result.exclusivebycontroller"));
            return "common/error/exclusiveLockError";
        }

        model.addAttribute("stock", stock);
        model.addAttribute(ResultMessages.success().add("excn.result.success"));
        return "excn/updateCompleteView";
    }

}
