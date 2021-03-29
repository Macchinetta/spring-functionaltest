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
package jp.co.ntt.fw.spring.functionaltest.app.dnta;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.ntt.fw.spring.functionaltest.domain.service.dnta.DateAndTimeApiService;

@RequestMapping("dnta")
@Controller
public class DNTA01Controller {

    @Inject
    DateAndTimeApiService dateAndTimeApiService;

    @ModelAttribute
    public DateForm setUpDateForm() {
        return new DateForm();
    }

    @RequestMapping(value = "0101/001", method = RequestMethod.GET)
    public String handle01001() {
        return "dnta/getDateAndTime";
    }

    @RequestMapping(value = "0101/002", method = RequestMethod.GET)
    public String handle01002() {
        return "dnta/getDateAndTime";
    }

    @RequestMapping(value = "0101/003", method = RequestMethod.GET)
    public String handle01003() {
        return "dnta/getDateAndTime";
    }

    @RequestMapping(value = "0102/001", method = RequestMethod.GET)
    public String handle02001() {
        return "dnta/getDateAndTime";
    }

    @RequestMapping(value = "0102/002", method = RequestMethod.GET)
    public String handle02002() {
        return "dnta/getDateAndTime";
    }

    @RequestMapping(value = "0102/003", method = RequestMethod.GET)
    public String handle02003() {
        return "dnta/getDateAndTime";
    }

    @RequestMapping(value = "0102/004", method = RequestMethod.GET)
    public String handle02004() {
        return "dnta/getDateAndTime";
    }

    @RequestMapping(value = "0102/005", method = RequestMethod.GET)
    public String handle02005() {
        return "dnta/getDateAndTime";
    }

    @RequestMapping(value = "0103/001", method = RequestMethod.GET)
    public String handle03001() {
        return "dnta/getDateAndTime";
    }

    @RequestMapping(value = "0103/002", method = RequestMethod.GET)
    public String handle03002() {
        return "dnta/getDateAndTime";
    }

    @RequestMapping(value = "0103/003", method = RequestMethod.GET)
    public String handle03003() {
        return "dnta/getDateAndTime";
    }

    @RequestMapping(value = "0103/004", method = RequestMethod.GET)
    public String handle03004() {
        return "dnta/getDateAndTime";
    }

    @RequestMapping(value = "0103/005", method = RequestMethod.GET)
    public String handle03005() {
        return "dnta/getDateAndTime";
    }

    @RequestMapping(value = "getdate", method = RequestMethod.GET, params = "getNowLocalDate")
    public String handleGetNowLocalDate(Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService
                .getNowLocalDate());
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "getdate", method = RequestMethod.GET, params = "getNowLocalTime")
    public String handleGetNowLocalTime(Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService
                .getNowLocalTime());
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "getdate", method = RequestMethod.GET, params = "getNowLocalDateTime")
    public String handleGetNowLocalDateTime(Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService
                .getNowLocalDateTime());
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "getdate", method = RequestMethod.GET, params = "getSpecifiedDate")
    public String handleGetSpecifiedDate(DateForm form, Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService
                .getSpecifiedLocalDate(form.getYear(), form.getMonth(), form
                        .getDay()));
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "getdate", method = RequestMethod.GET, params = "getSpecifiedTime")
    public String handleGetSpecifiedTime(DateForm form, Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService
                .getSpecifiedLocalTime(form.getHour(), form.getMinute(), form
                        .getSecond()));
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "getdate", method = RequestMethod.GET, params = "getSpecifiedDateTime")
    public String handleGetSpecifiedDateTime(DateForm form, Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService
                .getSpecifiedLocalDateTime(form.getYear(), form.getMonth(), form
                        .getDay(), form.getHour(), form.getMinute(), form
                                .getSecond()));
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "getdate", method = RequestMethod.GET, params = "getLastDateOfSpecifiedMonth")
    public String handleGetLastDateOfSpecifiedMonth(DateForm form,
            Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService
                .getLastDateOfSpecifiedMonth(form.getYear(), form.getMonth(),
                        form.getDay()));
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "getdate", method = RequestMethod.GET, params = "getNextMondayOfSpecifiedDate")
    public String handleGetNextMondayOfSpecifiedDate(DateForm form,
            Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService
                .getNextMondayOfSpecifiedMonth(form.getYear(), form.getMonth(),
                        form.getDay()));
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "getdate", method = RequestMethod.GET, params = "getOffsetTime")
    public String handleGetOffsetTime(Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService
                .getNowOffsetTime());
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "getdate", method = RequestMethod.GET, params = "getOffsetDateTime")
    public String handleGetOffsetDateTime(Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService
                .getNowOffsetDateTime());
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "getdate", method = RequestMethod.GET, params = "getZonedDateTime")
    public String handleGetZonedDateTime(Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService
                .getNowZonedDateTime());
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "getdate", method = RequestMethod.GET, params = "getDateTimeSpecifiedTimezoneByArea")
    public String handleGetDateTimeSpecifiedTimezoneByArea(DateForm form,
            Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService
                .getNowSpecifiedZonedDateTime(form.getZone()));
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "getdate", method = RequestMethod.GET, params = "getDateTimeSpecifiedTimezoneByTimeDifference")
    public String handleGetDateTimeSpecifiedTimezoneByTimeDifference(
            DateForm form, Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService
                .getNowSpecifiedZonedDateTime(form.getZone()));
        return "dnta/showDateTime";
    }
}
