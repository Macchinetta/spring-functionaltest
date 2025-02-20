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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("dnta")
@Controller
public class DNTA06Controller {
    private static final LocalDateTime DATE_TIME = LocalDateTime.of(2015, 12, 25, 23, 30, 59, 345);

    private static final LocalDate DATE = LocalDate.of(2015, 12, 25);

    private static final LocalTime TIME = LocalTime.of(23, 30, 59);

    private static final Date NOW_DATE = new Date();

    private static final LocalDateTime DATE_TIME_01 = LocalDateTime.of(2014, 1, 1, 1, 1, 1, 123);

    private static final LocalDateTime DATE_TIME_02 = LocalDateTime.of(2015, 4, 8, 6, 11, 14, 234);

    private static final LocalDateTime DATE_TIME_03 =
            LocalDateTime.of(2016, 7, 15, 11, 21, 27, 345);

    private static final LocalDateTime DATE_TIME_04 =
            LocalDateTime.of(2017, 10, 22, 16, 31, 40, 456);

    private static final LocalDateTime DATE_TIME_05 =
            LocalDateTime.of(2018, 1, 29, 21, 41, 53, 567);

    private static final LocalDateTime[] ARRAY_TEMPORALS =
            {DATE_TIME_01, DATE_TIME_02, DATE_TIME_03, DATE_TIME_04, DATE_TIME_05};

    private static final List<LocalDateTime> LIST_TEMPORALS;
    static {
        LIST_TEMPORALS = new ArrayList<>();
        LIST_TEMPORALS.add(DATE_TIME_02);
        LIST_TEMPORALS.add(DATE_TIME_03);
        LIST_TEMPORALS.add(DATE_TIME_04);
        LIST_TEMPORALS.add(DATE_TIME_05);
        LIST_TEMPORALS.add(DATE_TIME_01);
    }

    private static final Set<LocalDateTime> SET_TEMPORALS;
    static {
        SET_TEMPORALS = new HashSet<>();
        SET_TEMPORALS.add(DATE_TIME_03);
        SET_TEMPORALS.add(DATE_TIME_04);
        SET_TEMPORALS.add(DATE_TIME_05);
        SET_TEMPORALS.add(DATE_TIME_01);
        SET_TEMPORALS.add(DATE_TIME_02);
    }

    @RequestMapping(value = "0601/001", method = RequestMethod.GET)
    public String handleDateTimeFormat(Model model) {
        model.addAttribute("resultDate", DATE_TIME);
        return "dnta/showFormat";
    }

    @RequestMapping(value = "0601/002", method = RequestMethod.GET)
    public String handleDateFormat(Model model) {
        model.addAttribute("resultDate", DATE);
        return "dnta/showFormat";
    }

    @RequestMapping(value = "0601/003", method = RequestMethod.GET)
    public String handleTimeFormat(Model model) {
        model.addAttribute("resultDate", TIME);
        return "dnta/showFormat";
    }

    @RequestMapping(value = "0601/004", method = RequestMethod.GET)
    public String handleFormatError(Model model) {
        model.addAttribute("resultDate", NOW_DATE);
        return "dnta/showFormat";
    }

    @RequestMapping(value = "0602/001", method = RequestMethod.GET)
    public String handleFormat(Model model) {
        model.addAttribute("resultDate", DATE_TIME);
        model.addAttribute("locale", Locale.ENGLISH);
        return "dnta/showSingleFormat";
    }

    @RequestMapping(value = "0602/002", method = RequestMethod.GET)
    public String handleFormatLocale(Model model) {
        model.addAttribute("resultDate", ARRAY_TEMPORALS);
        model.addAttribute("locale", Locale.ENGLISH);
        return "dnta/showArrayFormat";
    }

    @RequestMapping(value = "0602/003", method = RequestMethod.GET)
    public String handleArrayFormat(Model model) {
        model.addAttribute("resultDate", LIST_TEMPORALS);
        model.addAttribute("locale", Locale.ENGLISH);
        return "dnta/showListFormat";
    }

    @RequestMapping(value = "0602/004", method = RequestMethod.GET)
    public String handleArrayFormatLocale(Model model) {
        model.addAttribute("resultDate", SET_TEMPORALS);
        model.addAttribute("locale", Locale.ENGLISH);
        return "dnta/showSetFormat";
    }

    @RequestMapping(value = "0602/005", method = RequestMethod.GET)
    public String handleFormatISO(Model model) {
        model.addAttribute("resultDate", DATE_TIME);
        return "dnta/showFormatISO";
    }

    @RequestMapping(value = "0602/006", method = RequestMethod.GET)
    public String handleArrayFormatISO(Model model) {
        model.addAttribute("resultDate", ARRAY_TEMPORALS);
        return "dnta/showArrayFormatISO";
    }

    @RequestMapping(value = "0602/007", method = RequestMethod.GET)
    public String handleListFormatISO(Model model) {
        model.addAttribute("resultDate", LIST_TEMPORALS);
        return "dnta/showListFormatISO";
    }

    @RequestMapping(value = "0602/008", method = RequestMethod.GET)
    public String handleSetFormatISO(Model model) {
        model.addAttribute("resultDate", SET_TEMPORALS);
        return "dnta/showSetFormatISO";
    }

    @RequestMapping(value = "0603/001", method = RequestMethod.GET)
    public String handleDateField(Model model) {
        model.addAttribute("resultDate", DATE_TIME);
        return "dnta/showDateField";
    }

    @RequestMapping(value = "0603/002", method = RequestMethod.GET)
    public String handleDateFieldArray(Model model) {
        model.addAttribute("resultDate", ARRAY_TEMPORALS);
        return "dnta/showArrayDateField";
    }

    @RequestMapping(value = "0603/003", method = RequestMethod.GET)
    public String handleDateFieldList(Model model) {
        model.addAttribute("resultDate", LIST_TEMPORALS);
        return "dnta/showListDateField";
    }

    @RequestMapping(value = "0603/004", method = RequestMethod.GET)
    public String handleDateFieldSet(Model model) {
        model.addAttribute("resultDate", SET_TEMPORALS);
        return "dnta/showSetDateField";
    }
}
