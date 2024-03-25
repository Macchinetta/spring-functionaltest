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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.terasoluna.gfw.common.message.ResultMessages;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAStock;
import jp.co.ntt.fw.spring.functionaltest.domain.service.djpa.JPAStockDBLockService;

@Controller
@RequestMapping("jsp/0501")
public class EXCN_JSP_0501Controller {

    @Inject
    EXCNBeanMapper beanMapper;

    @Inject
    JPAStockDBLockService jpaStockDBLockService;

    @ModelAttribute
    public StockForm setUpForm() {
        JPAStock stock = jpaStockDBLockService.findOne("EXCN0501001");
        return beanMapper.mapJPA(stock);
    }

    @GetMapping(value = "001")
    public String handle001(Model model) {
        return "jsp/excn/jpaRDBMSLockView";
    }

    @PostMapping(value = "001", params = "buy")
    public String handle001Buy(StockForm form, Model model) {

        JPAStock stock = beanMapper.mapJPA(form);

        stock = jpaStockDBLockService.buy(stock, form.getPurchasingQuantity(),
                form.getSleepMillis());

        model.addAttribute("stock", stock);
        model.addAttribute(ResultMessages.success().add("excn.result.success"));
        return "jsp/excn/updateCompleteView";
    }

}
