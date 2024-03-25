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
package jp.co.ntt.fw.spring.functionaltest.app.dtop;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dtop.DateOperationService;

@RequestMapping("thymeleaf")
@Controller
public class DTOP_Thymeleaf_02Controller {

    @Inject
    DateOperationService dateOperationService;

    @ModelAttribute
    public ChangeTypeForm setUpForm() {
        return new ChangeTypeForm();
    }

    @GetMapping(value = "0201/001")
    public String handle01001(Model model) {
        return "thymeleaf/dtop/changeType";
    }

    @GetMapping(value = "0201/002")
    public String handle01002(Model model) {
        return "thymeleaf/dtop/changeType";
    }

    @GetMapping(value = "0202/001")
    public String handle02001(Model model) {
        return "thymeleaf/dtop/changeType";
    }

    @GetMapping(value = "getdatetype", params = "getDateToDateTimeObject")
    public String handleGetDateToDateTimeObject(Model model) {
        DateTime dateTime = new DateTime(dateOperationService.getNowDate());
        model.addAttribute("resultDateWithPtn", DateTimeFormat.forPattern(
                "yyyy/MM/dd HH:mm:ss.SSS ZZ").print(dateTime));
        model.addAttribute("resultDateWithStyl", DateTimeFormat.forStyle("SM")
                .print(dateTime));
        model.addAttribute("resultDate", dateTime);
        return "thymeleaf/dtop/showDateTime";
    }

    @GetMapping(value = "getdatetype", params = "getDateTimeToDateObject")
    public String handleGetDateTimeToDateObject(Model model) {
        model.addAttribute("resultDate", dateOperationService.getNowDateTime()
                .toDate());
        return "thymeleaf/dtop/showDate";
    }

    @GetMapping(value = "getdatetype", params = "getParseDate")
    public String handleGetParseDateObject(Model model, ChangeTypeForm form) {
        LocalDate localDate = DateTimeFormat.forPattern("yyyy/MM/dd")
                .parseLocalDate(form.getTargetDate());
        model.addAttribute("resultDateWithPtn", DateTimeFormat.forPattern(
                "yyyy/MM/dd").print(localDate));
        model.addAttribute("resultDateWithStyl", DateTimeFormat.forStyle("S-")
                .print(localDate));
        model.addAttribute("resultDate", localDate);
        return "thymeleaf/dtop/showLocalDate";
    }
}
