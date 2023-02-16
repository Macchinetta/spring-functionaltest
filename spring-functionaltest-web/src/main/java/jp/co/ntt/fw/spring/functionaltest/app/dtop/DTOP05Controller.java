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
package jp.co.ntt.fw.spring.functionaltest.app.dtop;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dtop.DateOperationService;

@RequestMapping("dtop")
@Controller
public class DTOP05Controller {

    @Inject
    public DateOperationService dateOperationService;

    @GetMapping(value = "0501/001")
    public String handle05001(Model model) {
        return "dtop/jspTagLibrary";
    }

    @GetMapping(value = "0501/002")
    public String handle05002(Model model) {
        return "dtop/jspTagLibrary";
    }

    @GetMapping(value = "0501/003")
    public String handle05003(Model model) {
        return "dtop/jspTagLibrary";
    }

    @GetMapping(value = "0501/004")
    public String handle05004(Model model) {
        return "dtop/jspTagLibrary";
    }

    @GetMapping(value = "jsptag/jspTagDateTime")
    public String handleGetNowDatetime(Model model) {
        DateTime dateTime = dateOperationService.getNowDateTime();
        model.addAttribute("resultDateWithPtn", DateTimeFormat.forPattern(
                "yyyy/MM/dd HH:mm:ss.SSS ZZ").print(dateTime));
        model.addAttribute("resultDateWithStyl", DateTimeFormat.forStyle("SM")
                .print(dateTime));
        model.addAttribute("resultDate", dateTime);
        return "dtop/showDateTime";
    }

    @GetMapping(value = "jsptag/jspTagLocalDateTime")
    public String handleGetNowLocalDateTime(Model model) {
        DateTime dateTime = dateOperationService.getNowDateTime();
        model.addAttribute("resultDateWithPtn", DateTimeFormat.forPattern(
                "yyyy/MM/dd HH:mm:ss.SSS").print(dateTime));
        model.addAttribute("resultDateWithStyl", DateTimeFormat.forStyle("SM")
                .print(dateTime));
        model.addAttribute("resultDate", dateTime);
        return "dtop/showLocalDateTime";
    }

    @GetMapping(value = "jsptag/jspTagLocalDate")
    public String handleGetNowLocalDate(Model model) {
        LocalDate dateDate = dateOperationService.getNowDateTime()
                .toLocalDate();
        model.addAttribute("resultDateWithPtn", DateTimeFormat.forPattern(
                "yyyy/MM/dd").print(dateDate));
        model.addAttribute("resultDateWithStyl", DateTimeFormat.forStyle("S-")
                .print(dateDate));
        model.addAttribute("resultDate", dateDate);
        return "dtop/showLocalDate";
    }

    @GetMapping(value = "jsptag/jspTagLocalTime")
    public String handleGetNowLocalTime(Model model) {
        LocalTime localTime = dateOperationService.getNowDateTime()
                .toLocalTime();
        model.addAttribute("resultDateWithPtn", DateTimeFormat.forPattern(
                "HH:mm:ss").print(localTime));
        model.addAttribute("resultDateWithStyl", DateTimeFormat.forStyle("-M")
                .print(localTime));
        model.addAttribute("resultDate", localTime);
        return "dtop/showLocalTime";
    }
}
