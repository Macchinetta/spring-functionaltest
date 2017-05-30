/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.excn;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Stock;
import jp.co.ntt.fw.spring.functionaltest.domain.service.excn.StockService;

import org.dozer.Mapper;
import org.springframework.dao.OptimisticLockingFailureException;
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
@RequestMapping("excn/0302")
public class EXCN0302Controller {

    @Inject
    Mapper beanMapper;

    @Inject
    StockService stockService;

    @ModelAttribute()
    public StockForm setUpForm() {
        return new StockForm();
    }

    @RequestMapping(value = "001", method = RequestMethod.GET)
    public String handle001(StockForm form, Model model) {
        beanMapper.map(stockService.findOne("EXCN0302001"), form);
        return "excn/optimisticLockView";
    }

    @RequestMapping(value = "002", method = RequestMethod.GET)
    public String handle002(StockForm form, Model model) {
        beanMapper.map(stockService.findOne("EXCN0302002"), form);
        return "excn/optimisticLockWithHiddenVersionView";
    }

    @RequestMapping(value = "001", method = RequestMethod.POST, params = "buy")
    public String handle001Buy(StockForm form, Model model) {

        Stock stock = beanMapper.map(form, Stock.class);

        stock = stockService.buyWithOptimisticLock(stock, form
                .getPurchasingQuantity(), form.getSleepMillis());

        model.addAttribute("stock", stock);
        model.addAttribute(ResultMessages.success().add("excn.result.success"));
        return "excn/updateCompleteView";
    }

    @RequestMapping(value = "001", method = RequestMethod.POST, params = "sale")
    public String handle001Sale(StockForm form, Model model) {

        Stock stock = beanMapper.map(form, Stock.class);

        stock = stockService.saleWithOptimisticLock(stock, form
                .getPurchasingQuantity(), form.getSleepMillis());

        model.addAttribute("stock", stock);
        model.addAttribute(ResultMessages.success().add("excn.result.success"));
        return "excn/updateCompleteView";
    }

    @RequestMapping(value = "002", method = RequestMethod.POST, params = "buy")
    public String handle002Buy(StockForm form, Model model) {

        Stock stock = beanMapper.map(form, Stock.class);

        try {
            stock = stockService.buyWithOptimisticLockByHiddenVersion(stock,
                    form.getPurchasingQuantity(), form.getSleepMillis());
        } catch (OptimisticLockingFailureException e) {
            model.addAttribute(ResultMessages.warning().add(
                    "excn.result.exclusivebyrequest"));
            return "common/error/exclusiveLockError";
        }

        model.addAttribute("stock", stock);
        model.addAttribute(ResultMessages.success().add("excn.result.success"));
        return "excn/updateCompleteView";
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ModelAndView handleOptimisticLockingFailureException(
            OptimisticLockingFailureException e) {

        ExtendedModelMap modelMap = new ExtendedModelMap();

        modelMap.addAttribute(ResultMessages.warning().add(
                "excn.result.exclusivebycontroller"));
        return new ModelAndView("common/error/exclusiveLockError", modelMap);
    }

}
