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

import java.time.format.DateTimeParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dnta.DateAndTimeApiService;

@RequestMapping("jsp")
@Controller
public class DNTA_JSP_04Controller {

    @Inject
    DateAndTimeApiService dateAndTimeApiService;

    @ModelAttribute
    public CalcDateForm setUpCalcDateForm() {
        return new CalcDateForm();
    }

    @ModelAttribute
    public CompareDateTimeForm setUpCompareDateTimeForm() {
        return new CompareDateTimeForm();
    }

    @ModelAttribute
    public ChangeTypeForm setUpChangeTypeForm() {
        return new ChangeTypeForm();
    }

    @GetMapping(value = "0401/001")
    public String handle01001() {
        return "jsp/dnta/dateManipulation";
    }

    @GetMapping(value = "0401/002")
    public String handle01002() {
        return "jsp/dnta/dateManipulation";
    }

    @GetMapping(value = "0401/003")
    public String handle01003() {
        return "jsp/dnta/dateManipulation";
    }

    @GetMapping(value = "0401/004")
    public String handle01004() {
        return "jsp/dnta/dateManipulation";
    }

    @GetMapping(value = "0401/005")
    public String handle01005() {
        return "jsp/dnta/dateManipulation";
    }

    @GetMapping(value = "0401/006")
    public String handle01006() {
        return "jsp/dnta/dateManipulation";
    }

    @GetMapping(value = "0401/007")
    public String handle01007() {
        return "jsp/dnta/dateManipulation";
    }

    @GetMapping(value = "0401/008")
    public String handle01008() {
        return "jsp/dnta/dateManipulation";
    }

    @GetMapping(value = "0401/009")
    public String handle01009() {
        return "jsp/dnta/dateManipulation";
    }

    @GetMapping(value = "0402/001")
    public String handle02001() {
        return "jsp/dnta/dateManipulation";
    }

    @GetMapping(value = "0402/002")
    public String handle02002() {
        return "jsp/dnta/dateManipulation";
    }

    @GetMapping(value = "0403/001")
    public String handle03001() {
        return "jsp/dnta/dateManipulation";
    }

    @GetMapping(value = "0403/002")
    public String handle03002() {
        return "jsp/dnta/dateManipulation";
    }

    @GetMapping(value = "0403/003")
    public String handle03003() {
        return "jsp/dnta/dateManipulation";
    }

    @GetMapping(value = "0403/004")
    public String handle03004() {
        return "jsp/dnta/dateManipulation";
    }

    @GetMapping(value = "0403/005")
    public String handle03005() {
        return "jsp/dnta/dateManipulation";
    }

    @GetMapping(value = "0403/006")
    public String handle03006() {
        return "jsp/dnta/dateManipulation";
    }

    @GetMapping(value = "0403/007")
    public String handle03007() {
        return "jsp/dnta/dateManipulation";
    }

    @GetMapping(value = "0404/001")
    public String handle04001() {
        return "jsp/dnta/dateManipulation";
    }

    @GetMapping(value = "0404/002")
    public String handle04002() {
        return "jsp/dnta/dateManipulation";
    }

    @GetMapping(value = "dateManipulation", params = "calcYear")
    public String handleCalcYear(CalcDateForm form, Model model) {
        int[] date = dateAndTimeApiService.parseAndGetDateElement(form.getInputDateTime());
        model.addAttribute("getPlus",
                dateAndTimeApiService.plusYear(date[0], date[1], date[2], form.getPlus()));
        model.addAttribute("getMinus",
                dateAndTimeApiService.minusYear(date[0], date[1], date[2], form.getMinus()));
        return "jsp/dnta/showCalcResult";
    }

    @GetMapping(value = "dateManipulation", params = "calcMonth")
    public String handleCalcMonth(CalcDateForm form, Model model) {
        int[] date = dateAndTimeApiService.parseAndGetDateElement(form.getInputDateTime());
        model.addAttribute("getPlus",
                dateAndTimeApiService.plusMonth(date[0], date[1], date[2], form.getPlus()));
        model.addAttribute("getMinus",
                dateAndTimeApiService.minusMonth(date[0], date[1], date[2], form.getMinus()));
        return "jsp/dnta/showCalcResult";
    }

    @GetMapping(value = "dateManipulation", params = "calcDay")
    public String handleCalcDay(CalcDateForm form, Model model) {
        int[] date = dateAndTimeApiService.parseAndGetDateElement(form.getInputDateTime());
        model.addAttribute("getPlus",
                dateAndTimeApiService.plusDay(date[0], date[1], date[2], form.getPlus()));
        model.addAttribute("getMinus",
                dateAndTimeApiService.minusDay(date[0], date[1], date[2], form.getMinus()));
        return "jsp/dnta/showCalcResult";
    }

    @GetMapping(value = "dateManipulation", params = "calcHour")
    public String handleCalcHour(CalcDateForm form, Model model) {
        int[] time = dateAndTimeApiService.parseAndGetTimeElement(form.getInputDateTime());
        model.addAttribute("getPlus",
                dateAndTimeApiService.plusHour(time[0], time[1], time[2], form.getPlus()));
        model.addAttribute("getMinus",
                dateAndTimeApiService.minusHour(time[0], time[1], time[2], form.getMinus()));
        return "jsp/dnta/showCalcResult";
    }

    @GetMapping(value = "dateManipulation", params = "calcMinute")
    public String handleCalcMinute(CalcDateForm form, Model model) {
        int[] time = dateAndTimeApiService.parseAndGetTimeElement(form.getInputDateTime());
        model.addAttribute("getPlus",
                dateAndTimeApiService.plusMinute(time[0], time[1], time[2], form.getPlus()));
        model.addAttribute("getMinus",
                dateAndTimeApiService.minusMinute(time[0], time[1], time[2], form.getMinus()));
        return "jsp/dnta/showCalcResult";
    }

    @GetMapping(value = "dateManipulation", params = "calcSecond")
    public String handleCalcSecond(CalcDateForm form, Model model) {
        int[] time = dateAndTimeApiService.parseAndGetTimeElement(form.getInputDateTime());
        model.addAttribute("getPlus",
                dateAndTimeApiService.plusSecond(time[0], time[1], time[2], form.getPlus()));
        model.addAttribute("getMinus",
                dateAndTimeApiService.minusSecond(time[0], time[1], time[2], form.getMinus()));
        return "jsp/dnta/showCalcResult";
    }

    @GetMapping(value = "dateManipulation", params = "compareDate")
    public String handleCompareDate(CompareDateTimeForm form, Model model) {
        model.addAttribute("NowIsBeforePast",
                dateAndTimeApiService.isBeforeDate(form.getYear2(), form.getMonth2(),
                        form.getDay2(), form.getYear1(), form.getMonth1(), form.getDay1()));
        model.addAttribute("NowIsBeforeNow",
                dateAndTimeApiService.isBeforeDate(form.getYear2(), form.getMonth2(),
                        form.getDay2(), form.getYear2(), form.getMonth2(), form.getDay2()));
        model.addAttribute("NowIsBeforeFuture",
                dateAndTimeApiService.isBeforeDate(form.getYear2(), form.getMonth2(),
                        form.getDay2(), form.getYear3(), form.getMonth3(), form.getDay3()));
        model.addAttribute("NowIsAfterPast",
                dateAndTimeApiService.isAfterDate(form.getYear2(), form.getMonth2(), form.getDay2(),
                        form.getYear1(), form.getMonth1(), form.getDay1()));
        model.addAttribute("NowIsAfterNow",
                dateAndTimeApiService.isAfterDate(form.getYear2(), form.getMonth2(), form.getDay2(),
                        form.getYear2(), form.getMonth2(), form.getDay2()));
        model.addAttribute("NowIsAfterFuture",
                dateAndTimeApiService.isAfterDate(form.getYear2(), form.getMonth2(), form.getDay2(),
                        form.getYear3(), form.getMonth3(), form.getDay3()));
        model.addAttribute("NowEqualsPast",
                dateAndTimeApiService.equalsDate(form.getYear2(), form.getMonth2(), form.getDay2(),
                        form.getYear1(), form.getMonth1(), form.getDay1()));
        model.addAttribute("NowEqualsNow",
                dateAndTimeApiService.equalsDate(form.getYear2(), form.getMonth2(), form.getDay2(),
                        form.getYear2(), form.getMonth2(), form.getDay2()));
        model.addAttribute("NowEqualsFuture",
                dateAndTimeApiService.equalsDate(form.getYear2(), form.getMonth2(), form.getDay2(),
                        form.getYear3(), form.getMonth3(), form.getDay3()));
        return "jsp/dnta/showCompareResult";
    }

    @GetMapping(value = "dateManipulation", params = "compareTime")
    public String handleCompareTime(CompareDateTimeForm form, Model model) {
        model.addAttribute("NowIsBeforePast",
                dateAndTimeApiService.isBeforeTime(form.getHour2(), form.getMinute2(),
                        form.getSecond2(), form.getHour1(), form.getMinute1(), form.getSecond1()));
        model.addAttribute("NowIsBeforeNow",
                dateAndTimeApiService.isBeforeTime(form.getHour2(), form.getMinute2(),
                        form.getSecond2(), form.getHour2(), form.getMinute2(), form.getSecond2()));
        model.addAttribute("NowIsBeforeFuture",
                dateAndTimeApiService.isBeforeTime(form.getHour2(), form.getMinute2(),
                        form.getSecond2(), form.getHour3(), form.getMinute3(), form.getSecond3()));
        model.addAttribute("NowIsAfterPast",
                dateAndTimeApiService.isAfterTime(form.getHour2(), form.getMinute2(),
                        form.getSecond2(), form.getHour1(), form.getMinute1(), form.getSecond1()));
        model.addAttribute("NowIsAfterNow",
                dateAndTimeApiService.isAfterTime(form.getHour2(), form.getMinute2(),
                        form.getSecond2(), form.getHour2(), form.getMinute2(), form.getSecond2()));
        model.addAttribute("NowIsAfterFuture",
                dateAndTimeApiService.isAfterTime(form.getHour2(), form.getMinute2(),
                        form.getSecond2(), form.getHour3(), form.getMinute3(), form.getSecond3()));
        model.addAttribute("NowEqualsPast",
                dateAndTimeApiService.equalsTime(form.getHour2(), form.getMinute2(),
                        form.getSecond2(), form.getHour1(), form.getMinute1(), form.getSecond1()));
        model.addAttribute("NowEqualsNow",
                dateAndTimeApiService.equalsTime(form.getHour2(), form.getMinute2(),
                        form.getSecond2(), form.getHour2(), form.getMinute2(), form.getSecond2()));
        model.addAttribute("NowEqualsFuture",
                dateAndTimeApiService.equalsTime(form.getHour2(), form.getMinute2(),
                        form.getSecond2(), form.getHour3(), form.getMinute3(), form.getSecond3()));
        return "jsp/dnta/showCompareResult";
    }

    @GetMapping(value = "dateManipulation", params = "checkDateString")
    public String handleCheckDateString(ChangeTypeForm form, Model model) {
        try {
            model.addAttribute("checkResult", true);
            model.addAttribute("getResult",
                    dateAndTimeApiService.parseToDate(form.getTargetDate()));
        } catch (DateTimeParseException e) {
            model.addAttribute("checkResult", false);
            model.addAttribute("getResult", e);

        }
        return "jsp/dnta/showCheckResult";
    }

    @GetMapping(value = "dateManipulation", params = "checkTimeString")
    public String handleCheckTimeString(ChangeTypeForm form, Model model) {
        try {
            model.addAttribute("checkResult", true);
            model.addAttribute("getResult",
                    dateAndTimeApiService.parseToTime(form.getTargetDate()));
        } catch (DateTimeParseException e) {
            model.addAttribute("checkResult", false);
            model.addAttribute("getResult", e);

        }
        return "jsp/dnta/showCheckResult";
    }

    @GetMapping(value = "dateManipulation", params = "checkLeapYear")
    public String handleCheckLeapYear(ChangeTypeForm form, Model model) {

        int[] date = dateAndTimeApiService.parseAndGetDateElement(form.getTargetDate());
        model.addAttribute("checkResult",
                dateAndTimeApiService.isLeapYear(date[0], date[1], date[2]));
        return "jsp/dnta/showCheckResult";
    }

    @GetMapping(value = "dateManipulation", params = "getEachValueOfDate")
    public String handleGetEachValueOfDate(ChangeTypeForm form, Model model) {

        int[] date = dateAndTimeApiService.parseAndGetDateElement(form.getTargetDate());
        model.addAttribute("year", date[0]);
        model.addAttribute("month", date[1]);
        model.addAttribute("dayOfMonth", date[2]);
        model.addAttribute("dayOfYear", date[3]);
        return "jsp/dnta/showEachValue";
    }

    @GetMapping(value = "dateManipulation", params = "getEachValueOfTime")
    public String handleGetEachValueOfTime(ChangeTypeForm form, Model model) {
        int[] time = dateAndTimeApiService.parseAndGetTimeElement(form.getTargetDate());
        model.addAttribute("hour", time[0]);
        model.addAttribute("minute", time[1]);
        model.addAttribute("second", time[2]);
        return "jsp/dnta/showEachValue";
    }
}
