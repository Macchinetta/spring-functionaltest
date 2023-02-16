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
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dtop.DateOperationService;

@RequestMapping("dtop")
@Controller
public class DTOP01Controller {

    @Inject
    DateOperationService dateOperationService;

    @ModelAttribute
    public GetDateAndTimeForm setUpForm() {
        return new GetDateAndTimeForm();
    }

    @GetMapping(value = "0101/001")
    public String handle01001(Model model) {
        return "dtop/getDateAndTime";
    }

    @GetMapping(value = "0101/002")
    public String handle01002(Model model) {
        return "dtop/getDateAndTime";
    }

    @GetMapping(value = "0101/003")
    public String handle01003(Model model) {
        return "dtop/getDateAndTime";
    }

    @GetMapping(value = "0101/004")
    public String handle01004(Model model) {
        return "dtop/getDateAndTime";
    }

    @GetMapping(value = "0101/005")
    public String handle01005(Model model) {
        return "dtop/getDateAndTime";
    }

    @GetMapping(value = "0102/001")
    public String handle02001(Model model) {
        return "dtop/getDateAndTime";
    }

    @GetMapping(value = "0103/001")
    public String handle03001(Model model) {
        return "dtop/getDateAndTime";
    }

    @GetMapping(value = "getdate", params = "getNowDateTime")
    public String handleGetNowDateTime(Model model) {
        DateTime dateTime = dateOperationService.getNowDateTime();
        model.addAttribute("resultDateWithPtn", DateTimeFormat.forPattern(
                "yyyy/MM/dd HH:mm:ss.SSS ZZ").print(dateTime));
        model.addAttribute("resultDateWithStyl", DateTimeFormat.forStyle("SM")
                .print(dateTime));
        model.addAttribute("resultDate", dateTime);
        return "dtop/showDateTime";
    }

    @GetMapping(value = "getdate", params = "getNowLocalDate")
    public String handleGetNowLocalDate(Model model) {
        DateTime dateTime = dateOperationService.getNowDateTime();
        model.addAttribute("resultDateWithPtn", DateTimeFormat.forPattern(
                "yyyy/MM/dd").print(dateTime));
        model.addAttribute("resultDateWithStyl", DateTimeFormat.forStyle("S-")
                .print(dateTime));
        model.addAttribute("resultDate", dateTime.toLocalDate());
        return "dtop/showLocalDate";
    }

    @GetMapping(value = "getdate", params = "getNowLocalTime")
    public String handleGetNowLocalTime(Model model) {
        DateTime dateTime = dateOperationService.getNowDateTime();
        model.addAttribute("resultDateWithPtn", DateTimeFormat.forPattern(
                "HH:mm:ss").print(dateTime));
        model.addAttribute("resultDateWithStyl", DateTimeFormat.forStyle("-M")
                .print(dateTime));
        model.addAttribute("resultDate", dateTime.toLocalTime());
        return "dtop/showLocalTime";
    }

    @SuppressWarnings("deprecation")
    @GetMapping(value = "getdate", params = "getNowDateMidnight")
    public String handleGetNowDateMidnight(Model model) {
        DateTime dateTime = dateOperationService.getNowDateTime();
        model.addAttribute("resultDateWithPtn", DateTimeFormat.forPattern(
                "yyyy/MM/dd HH:mm:ss.SSS ZZ").print(dateTime.toDateMidnight()));
        model.addAttribute("resultDate", dateTime.toDateMidnight());
        return "dtop/showDateMidnight";
    }

    @GetMapping(value = "getdate", params = "getWithTimeAtStartOfDay")
    public String handleGetNowDateTimeAtStartOfDay(Model model) {
        DateTime dateTime = dateOperationService.getNowDateTime();
        model.addAttribute("resultDateWithPtn", DateTimeFormat.forPattern(
                "yyyy/MM/dd HH:mm:ss.SSS ZZ").print(dateTime
                        .withTimeAtStartOfDay()));
        model.addAttribute("resultDateWithStyl", DateTimeFormat.forStyle("SM")
                .print(dateTime.withTimeAtStartOfDay()));
        model.addAttribute("resultDate", dateTime.withTimeAtStartOfDay());
        return "dtop/showDateTimeWithTimeAtStartOfDay";
    }

    @GetMapping(value = "getdate", params = "getDateTimeSpecifiedTimezone")
    public String handleGetDateTimeSpecifiedTimezone(Model model,
            GetDateAndTimeForm form) {
        DateTime dateTime = dateOperationService.getNowDateTime();
        DateTimeZone timezone = DateTimeZone.forID(form.getTargetTimeZone());
        model.addAttribute("resultDateWithPtn", DateTimeFormat.forPattern(
                "yyyy/MM/dd HH:mm:ss.SSS ZZ").print(dateTime.withZone(
                        timezone)));
        model.addAttribute("resultDateWithStyl", DateTimeFormat.forStyle("SM")
                .print(dateTime.withZone(timezone)));
        model.addAttribute("resultDate", dateTime.withZone(timezone));
        return "dtop/showDateTime";
    }

    @GetMapping(value = "getdate", params = "getPartOfDateTime")
    public String handleGetPartOfDateTime(Model model) {
        DateTime dateTime = dateOperationService.getNowDateTime();
        model.addAttribute("resultDate", dateTime);
        return "dtop/showPartOfDateTime";
    }
}
