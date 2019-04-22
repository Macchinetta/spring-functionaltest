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

import jp.co.ntt.fw.spring.functionaltest.domain.service.dnta.DateAndTimeApiService;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("dnta")
@Controller
public class DNTA04Controller {

    @Inject
    // JDK7向けのテスト実行時に、インスタンス生成の際のNoClassDefFoundErrorを回避するために@Lazyをつける
    @Lazy
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

    @RequestMapping(value = "0401/001", method = RequestMethod.GET)
    public String handle01001() {
        return "dnta/dateManipulation";
    }

    @RequestMapping(value = "0401/002", method = RequestMethod.GET)
    public String handle01002() {
        return "dnta/dateManipulation";
    }

    @RequestMapping(value = "0401/003", method = RequestMethod.GET)
    public String handle01003() {
        return "dnta/dateManipulation";
    }

    @RequestMapping(value = "0401/004", method = RequestMethod.GET)
    public String handle01004() {
        return "dnta/dateManipulation";
    }

    @RequestMapping(value = "0401/005", method = RequestMethod.GET)
    public String handle01005() {
        return "dnta/dateManipulation";
    }

    @RequestMapping(value = "0401/006", method = RequestMethod.GET)
    public String handle01006() {
        return "dnta/dateManipulation";
    }

    @RequestMapping(value = "0401/007", method = RequestMethod.GET)
    public String handle01007() {
        return "dnta/dateManipulation";
    }

    @RequestMapping(value = "0401/008", method = RequestMethod.GET)
    public String handle01008() {
        return "dnta/dateManipulation";
    }

    @RequestMapping(value = "0401/009", method = RequestMethod.GET)
    public String handle01009() {
        return "dnta/dateManipulation";
    }

    @RequestMapping(value = "0402/001", method = RequestMethod.GET)
    public String handle02001() {
        return "dnta/dateManipulation";
    }

    @RequestMapping(value = "0402/002", method = RequestMethod.GET)
    public String handle02002() {
        return "dnta/dateManipulation";
    }

    @RequestMapping(value = "0403/001", method = RequestMethod.GET)
    public String handle03001() {
        return "dnta/dateManipulation";
    }

    @RequestMapping(value = "0403/002", method = RequestMethod.GET)
    public String handle03002() {
        return "dnta/dateManipulation";
    }

    @RequestMapping(value = "0403/003", method = RequestMethod.GET)
    public String handle03003() {
        return "dnta/dateManipulation";
    }

    @RequestMapping(value = "0403/004", method = RequestMethod.GET)
    public String handle03004() {
        return "dnta/dateManipulation";
    }

    @RequestMapping(value = "0403/005", method = RequestMethod.GET)
    public String handle03005() {
        return "dnta/dateManipulation";
    }

    @RequestMapping(value = "0403/006", method = RequestMethod.GET)
    public String handle03006() {
        return "dnta/dateManipulation";
    }

    @RequestMapping(value = "0403/007", method = RequestMethod.GET)
    public String handle03007() {
        return "dnta/dateManipulation";
    }

    @RequestMapping(value = "0404/001", method = RequestMethod.GET)
    public String handle04001() {
        return "dnta/dateManipulation";
    }

    @RequestMapping(value = "0404/002", method = RequestMethod.GET)
    public String handle04002() {
        return "dnta/dateManipulation";
    }

    @RequestMapping(value = "dateManipulation", method = RequestMethod.GET, params = "calcYear")
    public String handleCalcYear(CalcDateForm form, Model model) {
        int[] date = dateAndTimeApiService.parseAndGetDateElement(form
                .getInputDateTime());
        model.addAttribute("getPlus", dateAndTimeApiService.plusYear(date[0],
                date[1], date[2], form.getPlus()));
        model.addAttribute("getMinus", dateAndTimeApiService.minusYear(date[0],
                date[1], date[2], form.getMinus()));
        return "dnta/showCalcResult";
    }

    @RequestMapping(value = "dateManipulation", method = RequestMethod.GET, params = "calcMonth")
    public String handleCalcMonth(CalcDateForm form, Model model) {
        int[] date = dateAndTimeApiService.parseAndGetDateElement(form
                .getInputDateTime());
        model.addAttribute("getPlus", dateAndTimeApiService.plusMonth(date[0],
                date[1], date[2], form.getPlus()));
        model.addAttribute("getMinus", dateAndTimeApiService.minusMonth(date[0],
                date[1], date[2], form.getMinus()));
        return "dnta/showCalcResult";
    }

    @RequestMapping(value = "dateManipulation", method = RequestMethod.GET, params = "calcDay")
    public String handleCalcDay(CalcDateForm form, Model model) {
        int[] date = dateAndTimeApiService.parseAndGetDateElement(form
                .getInputDateTime());
        model.addAttribute("getPlus", dateAndTimeApiService.plusDay(date[0],
                date[1], date[2], form.getPlus()));
        model.addAttribute("getMinus", dateAndTimeApiService.minusDay(date[0],
                date[1], date[2], form.getMinus()));
        return "dnta/showCalcResult";
    }

    @RequestMapping(value = "dateManipulation", method = RequestMethod.GET, params = "calcHour")
    public String handleCalcHour(CalcDateForm form, Model model) {
        int[] time = dateAndTimeApiService.parseAndGetTimeElement(form
                .getInputDateTime());
        model.addAttribute("getPlus", dateAndTimeApiService.plusHour(time[0],
                time[1], time[2], form.getPlus()));
        model.addAttribute("getMinus", dateAndTimeApiService.minusHour(time[0],
                time[1], time[2], form.getMinus()));
        return "dnta/showCalcResult";
    }

    @RequestMapping(value = "dateManipulation", method = RequestMethod.GET, params = "calcMinute")
    public String handleCalcMinute(CalcDateForm form, Model model) {
        int[] time = dateAndTimeApiService.parseAndGetTimeElement(form
                .getInputDateTime());
        model.addAttribute("getPlus", dateAndTimeApiService.plusMinute(time[0],
                time[1], time[2], form.getPlus()));
        model.addAttribute("getMinus", dateAndTimeApiService.minusMinute(
                time[0], time[1], time[2], form.getMinus()));
        return "dnta/showCalcResult";
    }

    @RequestMapping(value = "dateManipulation", method = RequestMethod.GET, params = "calcSecond")
    public String handleCalcSecond(CalcDateForm form, Model model) {
        int[] time = dateAndTimeApiService.parseAndGetTimeElement(form
                .getInputDateTime());
        model.addAttribute("getPlus", dateAndTimeApiService.plusSecond(time[0],
                time[1], time[2], form.getPlus()));
        model.addAttribute("getMinus", dateAndTimeApiService.minusSecond(
                time[0], time[1], time[2], form.getMinus()));
        return "dnta/showCalcResult";
    }

    @RequestMapping(value = "dateManipulation", method = RequestMethod.GET, params = "compareDate")
    public String handleCompareDate(CompareDateTimeForm form, Model model) {
        model.addAttribute("NowIsBeforePast", dateAndTimeApiService
                .isBeforeDate(form.getYear2(), form.getMonth2(), form.getDay2(),
                        form.getYear1(), form.getMonth1(), form.getDay1()));
        model.addAttribute("NowIsBeforeNow", dateAndTimeApiService.isBeforeDate(
                form.getYear2(), form.getMonth2(), form.getDay2(), form
                        .getYear2(), form.getMonth2(), form.getDay2()));
        model.addAttribute("NowIsBeforeFuture", dateAndTimeApiService
                .isBeforeDate(form.getYear2(), form.getMonth2(), form.getDay2(),
                        form.getYear3(), form.getMonth3(), form.getDay3()));
        model.addAttribute("NowIsAfterPast", dateAndTimeApiService.isAfterDate(
                form.getYear2(), form.getMonth2(), form.getDay2(), form
                        .getYear1(), form.getMonth1(), form.getDay1()));
        model.addAttribute("NowIsAfterNow", dateAndTimeApiService.isAfterDate(
                form.getYear2(), form.getMonth2(), form.getDay2(), form
                        .getYear2(), form.getMonth2(), form.getDay2()));
        model.addAttribute("NowIsAfterFuture", dateAndTimeApiService
                .isAfterDate(form.getYear2(), form.getMonth2(), form.getDay2(),
                        form.getYear3(), form.getMonth3(), form.getDay3()));
        model.addAttribute("NowEqualsPast", dateAndTimeApiService.equalsDate(
                form.getYear2(), form.getMonth2(), form.getDay2(), form
                        .getYear1(), form.getMonth1(), form.getDay1()));
        model.addAttribute("NowEqualsNow", dateAndTimeApiService.equalsDate(form
                .getYear2(), form.getMonth2(), form.getDay2(), form.getYear2(),
                form.getMonth2(), form.getDay2()));
        model.addAttribute("NowEqualsFuture", dateAndTimeApiService.equalsDate(
                form.getYear2(), form.getMonth2(), form.getDay2(), form
                        .getYear3(), form.getMonth3(), form.getDay3()));
        return "dnta/showCompareResult";
    }

    @RequestMapping(value = "dateManipulation", method = RequestMethod.GET, params = "compareTime")
    public String handleCompareTime(CompareDateTimeForm form, Model model) {
        model.addAttribute("NowIsBeforePast", dateAndTimeApiService
                .isBeforeTime(form.getHour2(), form.getMinute2(), form
                        .getSecond2(), form.getHour1(), form.getMinute1(), form
                                .getSecond1()));
        model.addAttribute("NowIsBeforeNow", dateAndTimeApiService.isBeforeTime(
                form.getHour2(), form.getMinute2(), form.getSecond2(), form
                        .getHour2(), form.getMinute2(), form.getSecond2()));
        model.addAttribute("NowIsBeforeFuture", dateAndTimeApiService
                .isBeforeTime(form.getHour2(), form.getMinute2(), form
                        .getSecond2(), form.getHour3(), form.getMinute3(), form
                                .getSecond3()));
        model.addAttribute("NowIsAfterPast", dateAndTimeApiService.isAfterTime(
                form.getHour2(), form.getMinute2(), form.getSecond2(), form
                        .getHour1(), form.getMinute1(), form.getSecond1()));
        model.addAttribute("NowIsAfterNow", dateAndTimeApiService.isAfterTime(
                form.getHour2(), form.getMinute2(), form.getSecond2(), form
                        .getHour2(), form.getMinute2(), form.getSecond2()));
        model.addAttribute("NowIsAfterFuture", dateAndTimeApiService
                .isAfterTime(form.getHour2(), form.getMinute2(), form
                        .getSecond2(), form.getHour3(), form.getMinute3(), form
                                .getSecond3()));
        model.addAttribute("NowEqualsPast", dateAndTimeApiService.equalsTime(
                form.getHour2(), form.getMinute2(), form.getSecond2(), form
                        .getHour1(), form.getMinute1(), form.getSecond1()));
        model.addAttribute("NowEqualsNow", dateAndTimeApiService.equalsTime(form
                .getHour2(), form.getMinute2(), form.getSecond2(), form
                        .getHour2(), form.getMinute2(), form.getSecond2()));
        model.addAttribute("NowEqualsFuture", dateAndTimeApiService.equalsTime(
                form.getHour2(), form.getMinute2(), form.getSecond2(), form
                        .getHour3(), form.getMinute3(), form.getSecond3()));
        return "dnta/showCompareResult";
    }

    @RequestMapping(value = "dateManipulation", method = RequestMethod.GET, params = "checkDateString")
    public String handleCheckDateString(ChangeTypeForm form, Model model) {
        try {
            model.addAttribute("checkResult", true);
            model.addAttribute("getResult", dateAndTimeApiService.parseToDate(
                    form.getTargetDate()));
        } catch (/* java.time.format.DateTimeParseException　JDK7向けのテスト実行時に、インスタンス生成の際のNoClassDefFoundErrorを回避するため */RuntimeException e) {
            model.addAttribute("checkResult", false);
            model.addAttribute("getResult", e);

        }
        return "dnta/showCheckResult";
    }

    @RequestMapping(value = "dateManipulation", method = RequestMethod.GET, params = "checkTimeString")
    public String handleCheckTimeString(ChangeTypeForm form, Model model) {
        try {
            model.addAttribute("checkResult", true);
            model.addAttribute("getResult", dateAndTimeApiService.parseToTime(
                    form.getTargetDate()));
        } catch (/* java.time.format.DateTimeParseException　JDK7向けのテスト実行時に、インスタンス生成の際のNoClassDefFoundErrorを回避するため */RuntimeException e) {
            model.addAttribute("checkResult", false);
            model.addAttribute("getResult", e);

        }
        return "dnta/showCheckResult";
    }

    @RequestMapping(value = "dateManipulation", method = RequestMethod.GET, params = "checkLeapYear")
    public String handleCheckLeapYear(ChangeTypeForm form, Model model) {

        int[] date = dateAndTimeApiService.parseAndGetDateElement(form
                .getTargetDate());
        model.addAttribute("checkResult", dateAndTimeApiService.isLeapYear(
                date[0], date[1], date[2]));
        return "dnta/showCheckResult";
    }

    @RequestMapping(value = "dateManipulation", method = RequestMethod.GET, params = "getEachValueOfDate")
    public String handleGetEachValueOfDate(ChangeTypeForm form, Model model) {

        int[] date = dateAndTimeApiService.parseAndGetDateElement(form
                .getTargetDate());
        model.addAttribute("year", date[0]);
        model.addAttribute("month", date[1]);
        model.addAttribute("dayOfMonth", date[2]);
        model.addAttribute("dayOfYear", date[3]);
        return "dnta/showEachValue";
    }

    @RequestMapping(value = "dateManipulation", method = RequestMethod.GET, params = "getEachValueOfTime")
    public String handleGetEachValueOfTime(ChangeTypeForm form, Model model) {
        int[] time = dateAndTimeApiService.parseAndGetTimeElement(form
                .getTargetDate());
        model.addAttribute("hour", time[0]);
        model.addAttribute("minute", time[1]);
        model.addAttribute("second", time[2]);
        return "dnta/showEachValue";
    }
}
