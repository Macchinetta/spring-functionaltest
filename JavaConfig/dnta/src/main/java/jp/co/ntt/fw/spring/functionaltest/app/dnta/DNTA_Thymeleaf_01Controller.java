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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dnta.DateAndTimeApiService;

@RequestMapping("thymeleaf")
@Controller
public class DNTA_Thymeleaf_01Controller {

    @Inject
    DateAndTimeApiService dateAndTimeApiService;

    @ModelAttribute
    public DateForm setUpDateForm() {
        return new DateForm();
    }

    @GetMapping(value = "0101/001")
    public String handle01001() {
        return "thymeleaf/dnta/getDateAndTime";
    }

    @GetMapping(value = "0101/002")
    public String handle01002() {
        return "thymeleaf/dnta/getDateAndTime";
    }

    @GetMapping(value = "0101/003")
    public String handle01003() {
        return "thymeleaf/dnta/getDateAndTime";
    }

    @GetMapping(value = "0102/001")
    public String handle02001() {
        return "thymeleaf/dnta/getDateAndTime";
    }

    @GetMapping(value = "0102/002")
    public String handle02002() {
        return "thymeleaf/dnta/getDateAndTime";
    }

    @GetMapping(value = "0102/003")
    public String handle02003() {
        return "thymeleaf/dnta/getDateAndTime";
    }

    @GetMapping(value = "0102/004")
    public String handle02004() {
        return "thymeleaf/dnta/getDateAndTime";
    }

    @GetMapping(value = "0102/005")
    public String handle02005() {
        return "thymeleaf/dnta/getDateAndTime";
    }

    @GetMapping(value = "0103/001")
    public String handle03001() {
        return "thymeleaf/dnta/getDateAndTime";
    }

    @GetMapping(value = "0103/002")
    public String handle03002() {
        return "thymeleaf/dnta/getDateAndTime";
    }

    @GetMapping(value = "0103/003")
    public String handle03003() {
        return "thymeleaf/dnta/getDateAndTime";
    }

    @GetMapping(value = "0103/004")
    public String handle03004() {
        return "thymeleaf/dnta/getDateAndTime";
    }

    @GetMapping(value = "0103/005")
    public String handle03005() {
        return "thymeleaf/dnta/getDateAndTime";
    }

    @GetMapping(value = "getdate", params = "getNowLocalDate")
    public String handleGetNowLocalDate(Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.getNowLocalDate());
        return "thymeleaf/dnta/showDateTime";
    }

    @GetMapping(value = "getdate", params = "getNowLocalTime")
    public String handleGetNowLocalTime(Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.getNowLocalTime());
        return "thymeleaf/dnta/showDateTime";
    }

    @GetMapping(value = "getdate", params = "getNowLocalDateTime")
    public String handleGetNowLocalDateTime(Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.getNowLocalDateTime());
        return "thymeleaf/dnta/showDateTime";
    }

    @GetMapping(value = "getdate", params = "getSpecifiedDate")
    public String handleGetSpecifiedDate(DateForm form, Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.getSpecifiedLocalDate(form.getYear(),
                form.getMonth(), form.getDay()));
        return "thymeleaf/dnta/showDateTime";
    }

    @GetMapping(value = "getdate", params = "getSpecifiedTime")
    public String handleGetSpecifiedTime(DateForm form, Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.getSpecifiedLocalTime(form.getHour(),
                form.getMinute(), form.getSecond()));
        return "thymeleaf/dnta/showDateTime";
    }

    @GetMapping(value = "getdate", params = "getSpecifiedDateTime")
    public String handleGetSpecifiedDateTime(DateForm form, Model model) {
        model.addAttribute("resultDate",
                dateAndTimeApiService.getSpecifiedLocalDateTime(form.getYear(), form.getMonth(),
                        form.getDay(), form.getHour(), form.getMinute(), form.getSecond()));
        return "thymeleaf/dnta/showDateTime";
    }

    @GetMapping(value = "getdate", params = "getLastDateOfSpecifiedMonth")
    public String handleGetLastDateOfSpecifiedMonth(DateForm form, Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService
                .getLastDateOfSpecifiedMonth(form.getYear(), form.getMonth(), form.getDay()));
        return "thymeleaf/dnta/showDateTime";
    }

    @GetMapping(value = "getdate", params = "getNextMondayOfSpecifiedDate")
    public String handleGetNextMondayOfSpecifiedDate(DateForm form, Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService
                .getNextMondayOfSpecifiedMonth(form.getYear(), form.getMonth(), form.getDay()));
        return "thymeleaf/dnta/showDateTime";
    }

    @GetMapping(value = "getdate", params = "getOffsetTime")
    public String handleGetOffsetTime(Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.getNowOffsetTime());
        return "thymeleaf/dnta/showDateTime";
    }

    @GetMapping(value = "getdate", params = "getOffsetDateTime")
    public String handleGetOffsetDateTime(Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.getNowOffsetDateTime());
        return "thymeleaf/dnta/showDateTime";
    }

    @GetMapping(value = "getdate", params = "getZonedDateTime")
    public String handleGetZonedDateTime(Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.getNowZonedDateTime());
        return "thymeleaf/dnta/showDateTime";
    }

    @GetMapping(value = "getdate", params = "getDateTimeSpecifiedTimezoneByArea")
    public String handleGetDateTimeSpecifiedTimezoneByArea(DateForm form, Model model) {
        model.addAttribute("resultDate",
                dateAndTimeApiService.getNowSpecifiedZonedDateTime(form.getZone()));
        return "thymeleaf/dnta/showDateTime";
    }

    @GetMapping(value = "getdate", params = "getDateTimeSpecifiedTimezoneByTimeDifference")
    public String handleGetDateTimeSpecifiedTimezoneByTimeDifference(DateForm form, Model model) {
        model.addAttribute("resultDate",
                dateAndTimeApiService.getNowSpecifiedZonedDateTime(form.getZone()));
        return "thymeleaf/dnta/showDateTime";
    }
}
