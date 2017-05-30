/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.excn;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAStock;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Stock;
import jp.co.ntt.fw.spring.functionaltest.domain.service.djpa.JPAStockService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.excn.StockService;

import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.terasoluna.gfw.common.message.ResultMessages;

@Controller
@RequestMapping("excn/0501")
public class EXCN0501Controller {

    @Inject
    Mapper beanMapper;

    @Inject
    JPAStockService jpaStockService;

    @ModelAttribute
    public StockForm setUpForm() {
        JPAStock stock = jpaStockService.findOne("EXCN0501001");
        return beanMapper.map(stock, StockForm.class);
    }

    @RequestMapping(value = "001", method = RequestMethod.GET)
    public String handle001(Model model) {
        return "excn/jpaRDBMSLockView";
    }

    @RequestMapping(value = "001", method = RequestMethod.POST, params = "buy")
    public String handle001Buy(StockForm form, Model model) {

        JPAStock stock = beanMapper.map(form, JPAStock.class);

        stock = jpaStockService.buy(stock, form.getPurchasingQuantity(), form
                .getSleepMillis());

        model.addAttribute("stock", stock);
        model.addAttribute(ResultMessages.success().add("excn.result.success"));
        return "excn/updateCompleteView";
    }

}
