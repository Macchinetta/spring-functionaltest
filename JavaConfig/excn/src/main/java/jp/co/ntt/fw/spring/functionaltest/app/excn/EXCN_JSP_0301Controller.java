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
import jp.co.ntt.fw.spring.functionaltest.domain.model.Stock;
import jp.co.ntt.fw.spring.functionaltest.domain.service.excn.StockDBLockService;

@Controller
@RequestMapping("jsp/0301")
public class EXCN_JSP_0301Controller {

    @Inject
    EXCNBeanMapper beanMapper;

    @Inject
    StockDBLockService stockDBLockService;

    @ModelAttribute
    public StockForm setUpForm() {
        Stock stock = stockDBLockService.findOne("EXCN0301001");
        return beanMapper.map(stock);
    }

    @GetMapping(value = "001")
    public String handle001(Model model) {
        return "jsp/excn/RDBMSLockView";
    }

    @PostMapping(value = "001", params = "buy")
    public String handle001Buy(StockForm form, Model model) {

        Stock stock = beanMapper.map(form);

        stock = stockDBLockService.buy(stock, form.getPurchasingQuantity(), form.getSleepMillis());

        model.addAttribute("stock", stock);
        model.addAttribute(ResultMessages.success().add("excn.result.success"));
        return "jsp/excn/updateCompleteView";
    }

}
