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

import jp.co.ntt.fw.spring.functionaltest.domain.model.Stock;
import jp.co.ntt.fw.spring.functionaltest.domain.service.excn.StockDBLockService;

import com.github.dozermapper.core.Mapper;
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
    StockDBLockService stockDBLockService;

    @ModelAttribute
    public StockForm setUpForm() {
        Stock stock = stockDBLockService.findOne("EXCN0301001");
        return beanMapper.map(stock, StockForm.class);
    }

    @RequestMapping(value = "001", method = RequestMethod.GET)
    public String handle001(Model model) {
        return "excn/RDBMSLockView";
    }

    @RequestMapping(value = "001", method = RequestMethod.POST, params = "buy")
    public String handle001Buy(StockForm form, Model model) {

        Stock stock = beanMapper.map(form, Stock.class);

        stock = stockDBLockService.buy(stock, form.getPurchasingQuantity(), form
                .getSleepMillis());

        model.addAttribute("stock", stock);
        model.addAttribute(ResultMessages.success().add("excn.result.success"));
        return "excn/updateCompleteView";
    }

}
