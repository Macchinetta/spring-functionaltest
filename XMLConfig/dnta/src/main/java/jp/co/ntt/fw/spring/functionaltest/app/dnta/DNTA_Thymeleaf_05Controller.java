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
package jp.co.ntt.fw.spring.functionaltest.app.dnta;

import java.time.DateTimeException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dnta.DateAndTimeApiService;

@RequestMapping("thymeleaf")
@Controller
public class DNTA_Thymeleaf_05Controller {

    @Inject
    DateAndTimeApiService dateAndTimeApiService;

    @ModelAttribute
    public DateForm setUpDateForm() {
        return new DateForm();
    }

    @ModelAttribute
    public ChangeTypeForm setUpChangeTypeForm() {
        return new ChangeTypeForm();
    }

    @GetMapping(value = "0501/001")
    public String handle01001() {
        return "thymeleaf/dnta/japaneseDate";
    }

    @GetMapping(value = "0501/002")
    public String handle01002() {
        return "thymeleaf/dnta/japaneseDate";
    }

    @GetMapping(value = "0502/001")
    public String handle02001() {
        return "thymeleaf/dnta/japaneseDate";
    }

    @GetMapping(value = "0502/002")
    public String handle02002() {
        return "thymeleaf/dnta/japaneseDate";
    }

    @GetMapping(value = "0503/001")
    public String handle03001() {
        return "thymeleaf/dnta/japaneseDate";
    }

    @GetMapping(value = "0504/001")
    public String handle04001() {
        return "thymeleaf/dnta/japaneseDate";
    }

    @GetMapping(value = "japaneseDate", params = "getNowDate")
    public String handleGetNowDate(DateForm form, Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.getNowDate());
        return "thymeleaf/dnta/showDateTime";
    }

    @GetMapping(value = "japaneseDate", params = "getSpecifiedDate")
    public String handleGetSpecifiedDate(DateForm form, Model model) {
        try {
            model.addAttribute("resultDate", dateAndTimeApiService.getSpecifiedDate(form.getYear(),
                    form.getMonth(), form.getDay()));
            return "thymeleaf/dnta/showDateTime";
        } catch (DateTimeException e) {
            model.addAttribute("getResult", e);
            return "thymeleaf/dnta/showCheckResult";
        }
    }

    @GetMapping(value = "japaneseDate", params = "getSpecifiedJapaneseDate")
    public String handleGetSpecifiedJapaneseDate(DateForm form, Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService
                .getSpecifiedJapaneseDate(form.getYear(), form.getMonth(), form.getDay()));
        return "thymeleaf/dnta/showDateTime";
    }

    @GetMapping(value = "japaneseDate", params = "format")
    public String handleFormat(DateForm form, Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService
                .createJapaneseDateString(form.getYear(), form.getMonth(), form.getDay()));
        return "thymeleaf/dnta/showDateTime";
    }

    @GetMapping(value = "japaneseDate", params = "parse")
    public String handleParse(ChangeTypeForm form, Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.parse(form.getTargetDate()));
        return "thymeleaf/dnta/showDateTime";
    }

    @GetMapping(value = "japaneseDate", params = "toJapaneseDate")
    public String handleToJapaneseDate(DateForm form, Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.toJapaneseDate(form.getYear(),
                form.getMonth(), form.getDay()));
        return "thymeleaf/dnta/showDateTime";
    }
}
