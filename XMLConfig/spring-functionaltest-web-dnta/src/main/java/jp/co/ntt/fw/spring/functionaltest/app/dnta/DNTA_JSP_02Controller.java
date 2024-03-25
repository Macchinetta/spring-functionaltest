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

@RequestMapping("jsp")
@Controller
public class DNTA_JSP_02Controller {

    @Inject
    DateAndTimeApiService dateAndTimeApiService;

    @ModelAttribute
    public CompareDateForm setUpCompareDateForm() {
        return new CompareDateForm();
    }

    @ModelAttribute
    public CompareTimeForm setUpCompareTimeForm() {
        return new CompareTimeForm();
    }

    @ModelAttribute
    public CompareZonedDateTimeForm setUpCompareZonedDateTimeForm() {
        return new CompareZonedDateTimeForm();
    }

    @GetMapping(value = "0201/001")
    public String handle01001() {
        return "jsp/dnta/duration";
    }

    @GetMapping(value = "0201/002")
    public String handle01002() {
        return "jsp/dnta/duration";
    }

    @GetMapping(value = "0202/001")
    public String handle02001() {
        return "jsp/dnta/duration";
    }

    @GetMapping(value = "0202/002")
    public String handle02002() {
        return "jsp/duration";
    }

    @GetMapping(value = "duration", params = "compareDate")
    public String handleCompareDate(CompareDateForm form, Model model) {

        int[] result = dateAndTimeApiService.compareDate(form.getYear1(), form
                .getMonth1(), form.getDay1(), form.getYear2(), form.getMonth2(),
                form.getDay2());

        model.addAttribute("start", dateAndTimeApiService.getSpecifiedLocalDate(
                form.getYear1(), form.getMonth1(), form.getDay1()));
        model.addAttribute("end", dateAndTimeApiService.getSpecifiedLocalDate(
                form.getYear2(), form.getMonth2(), form.getDay2()));
        model.addAttribute("yearResult", result[0]);
        model.addAttribute("monthResult", result[1]);
        model.addAttribute("dayResult", result[2]);
        return "jsp/dnta/showPeriod";
    }

    @GetMapping(value = "duration", params = "compareTime")
    public String handleCompareTime(CompareTimeForm form, Model model) {

        long[] result = dateAndTimeApiService.compareTime(form.getHour1(), form
                .getMinute1(), form.getSecond1(), form.getHour2(), form
                        .getMinute2(), form.getSecond2());

        model.addAttribute("start", dateAndTimeApiService.getSpecifiedLocalTime(
                form.getHour1(), form.getMinute1(), form.getSecond1()));
        model.addAttribute("end", dateAndTimeApiService.getSpecifiedLocalTime(
                form.getHour2(), form.getMinute2(), form.getSecond2()));
        model.addAttribute("hourResult", result[0]);
        model.addAttribute("minuteResult", result[1]);
        model.addAttribute("secondResult", result[2]);
        return "jsp/dnta/showDuration";
    }

    @GetMapping(value = "duration", params = "compareTimeWithSummerTime")
    public String handleCompareTimeWithSummerTime(CompareZonedDateTimeForm form,
            Model model) {
        long[] result = dateAndTimeApiService.compareTime(form
                .getZonedDateTimeyYear1(), form.getZonedDateTimeMonth1(), form
                        .getZonedDateTimeDay1(), form.getZonedDateTimeHour1(),
                form.getZonedDateTimeMinute1(), form.getZonedDateTimeSecond1(),
                0, form.getZonedDateTimeZone1(), form.getZonedDateTimeYear2(),
                form.getZonedDateTimeMonth2(), form.getZonedDateTimeDay2(), form
                        .getZonedDateTimeHour2(), form
                                .getZonedDateTimeMinute2(), form
                                        .getZonedDateTimeSecond2(), 0, form
                                                .getZonedDateTimeZone2());

        model.addAttribute("start", dateAndTimeApiService
                .getSpecifiedZonedDateTime(form.getZonedDateTimeyYear1(), form
                        .getZonedDateTimeMonth1(), form.getZonedDateTimeDay1(),
                        form.getZonedDateTimeHour1(), form
                                .getZonedDateTimeMinute1(), form
                                        .getZonedDateTimeSecond1(), 0, form
                                                .getZonedDateTimeZone1()));
        model.addAttribute("end", dateAndTimeApiService
                .getSpecifiedZonedDateTime(form.getZonedDateTimeYear2(), form
                        .getZonedDateTimeMonth2(), form.getZonedDateTimeDay2(),
                        form.getZonedDateTimeHour2(), form
                                .getZonedDateTimeMinute2(), form
                                        .getZonedDateTimeSecond2(), 0, form
                                                .getZonedDateTimeZone2()));
        model.addAttribute("hourResult", result[0]);
        model.addAttribute("minuteResult", result[1]);
        model.addAttribute("secondResult", result[2]);
        return "jsp/dnta/showDuration";
    }
}
