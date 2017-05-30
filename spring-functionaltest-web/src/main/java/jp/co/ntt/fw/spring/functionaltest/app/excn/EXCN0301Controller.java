/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.excn;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Stock;
import jp.co.ntt.fw.spring.functionaltest.domain.service.excn.StockService;

import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.terasoluna.gfw.common.message.ResultMessages;

@Controller
@RequestMapping("excn/0301")
public class EXCN0301Controller {

    @Inject
    Mapper beanMapper;

    @Inject
    StockService stockService;

    @ModelAttribute
    public StockForm setUpForm() {
        Stock stock = stockService.findOne("EXCN0301001");
        return beanMapper.map(stock, StockForm.class);
    }

    @RequestMapping(value = "001", method = RequestMethod.GET)
    public String handle001(Model model) {
        return "excn/RDBMSLockView";
    }

    @RequestMapping(value = "001", method = RequestMethod.POST, params = "buy")
    public String handle001Buy(StockForm form, Model model) {

        Stock stock = beanMapper.map(form, Stock.class);

        stock = stockService.buy(stock, form.getPurchasingQuantity(), form
                .getSleepMillis());

        model.addAttribute("stock", stock);
        model.addAttribute(ResultMessages.success().add("excn.result.success"));
        return "excn/updateCompleteView";
    }

}
