/*
 * Copyright 2014-2017 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package jp.co.ntt.fw.spring.functionaltest.app.excn;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.terasoluna.gfw.common.message.ResultMessages;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAStock;
import jp.co.ntt.fw.spring.functionaltest.domain.service.djpa.JPAStockDBLockService;

@Controller
@RequestMapping("excn/0501")
public class EXCN0501Controller {

    @Inject
    Mapper beanMapper;

    @Inject
    JPAStockDBLockService jpaStockDBLockService;

    @ModelAttribute
    public StockForm setUpForm() {
        JPAStock stock = jpaStockDBLockService.findOne("EXCN0501001");
        return beanMapper.map(stock, StockForm.class);
    }

    @RequestMapping(value = "001", method = RequestMethod.GET)
    public String handle001(Model model) {
        return "excn/jpaRDBMSLockView";
    }

    @RequestMapping(value = "001", method = RequestMethod.POST, params = "buy")
    public String handle001Buy(StockForm form, Model model) {

        JPAStock stock = beanMapper.map(form, JPAStock.class);

        stock = jpaStockDBLockService.buy(stock, form.getPurchasingQuantity(),
                form.getSleepMillis());

        model.addAttribute("stock", stock);
        model.addAttribute(ResultMessages.success().add("excn.result.success"));
        return "excn/updateCompleteView";
    }

}
