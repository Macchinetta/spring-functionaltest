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

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dtop.DateOperationService;

@RequestMapping("jsp")
@Controller
public class DTOP_JSP_03Controller {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat
            .forPattern("yyyy/MM/dd");

    @Inject
    public DateOperationService dateOperationService;

    @ModelAttribute
    public DateManipulationForm setUpForm() {
        return new DateManipulationForm();
    }

    @GetMapping(value = "0301/001")
    public String handle01001(Model model) {
        return "jsp/dtop/dateManipulation";
    }

    @GetMapping(value = "0301/002")
    public String handle01002(Model model) {
        return "jsp/dtop/dateManipulation";
    }

    @GetMapping(value = "0301/003")
    public String handle01003(Model model) {
        return "jsp/dtop/dateManipulation";
    }

    @GetMapping(value = "0301/004")
    public String handle01004(Model model) {
        return "jsp/dtop/dateManipulation";
    }

    @GetMapping(value = "0301/005")
    public String handle01005(Model model) {
        return "jsp/dtop/dateManipulation";
    }

    @GetMapping(value = "0301/006")
    public String handle01006(Model model) {
        return "jsp/dtop/dateManipulation";
    }

    @GetMapping(value = "0302/001")
    public String handle02001(Model model) {
        return "jsp/dtop/dateManipulation";
    }

    @GetMapping(value = "0302/002")
    public String handle02002(Model model) {
        return "jsp/dtop/dateManipulation";
    }

    @GetMapping(value = "0303/001")
    public String handle03001(Model model) {
        return "jsp/dtop/dateManipulation";
    }

    @GetMapping(value = "0303/002")
    public String handle03002(Model model) {
        return "jsp/dtop/dateManipulation";
    }

    @GetMapping(value = "calcdate", params = "calcDayDate")
    public String handleCalculateDayDate(Model model,
            DateManipulationForm form) {

        LocalDate targetIncDecDate = DATE_TIME_FORMATTER.parseLocalDate(form
                .getTargetIncDecDate());

        LocalDate localDateInc = dateOperationService.calcIncreaseNumOfDay(
                targetIncDecDate, form.getTargetIncreaseNum());
        LocalDate localDateDec = dateOperationService.calcDecreaseNumOfDay(
                targetIncDecDate, form.getTargetDecreaseNum());

        model.addAttribute("resultStartDate", localDateInc.toString(
                "yyyy/MM/dd"));
        model.addAttribute("resultEndDate", localDateDec.toString(
                "yyyy/MM/dd"));

        return "jsp/dtop/showIncDecDate";
    }

    @GetMapping(value = "calcdate", params = "calcMonthDate")
    public String handleCalculateMonthDate(Model model,
            DateManipulationForm form) {

        LocalDate targetIncDecDate = DATE_TIME_FORMATTER.parseLocalDate(form
                .getTargetIncDecDate());

        LocalDate localDateInc = dateOperationService.calcIncreaseNumOfMonth(
                targetIncDecDate, form.getTargetIncreaseNum());
        LocalDate localDateDec = dateOperationService.calcDecreaseNumOfMonth(
                targetIncDecDate, form.getTargetDecreaseNum());

        model.addAttribute("resultStartDate", localDateInc.toString(
                "yyyy/MM/dd"));
        model.addAttribute("resultEndDate", localDateDec.toString(
                "yyyy/MM/dd"));
        return "jsp/dtop/showIncDecDate";
    }

    @GetMapping(value = "calcdate", params = "calcYearDate")
    public String handleCalculateYearDate(Model model,
            DateManipulationForm form) {

        LocalDate targetIncDecDate = DATE_TIME_FORMATTER.parseLocalDate(form
                .getTargetIncDecDate());

        LocalDate localDateInc = dateOperationService.calcIncreaseNumOfYear(
                targetIncDecDate, form.getTargetIncreaseNum());
        LocalDate localDateDec = dateOperationService.calcDecreaseNumOfYear(
                targetIncDecDate, form.getTargetDecreaseNum());

        model.addAttribute("resultStartDate", localDateInc.toString(
                "yyyy/MM/dd"));
        model.addAttribute("resultEndDate", localDateDec.toString(
                "yyyy/MM/dd"));
        return "jsp/dtop/showIncDecDate";
    }

    @GetMapping(value = "calcdate", params = "calcMonthStartEndDate")
    public String handleCalcMonthStartEndDate(Model model,
            DateManipulationForm form) {

        LocalDate targetStartEndDate = DATE_TIME_FORMATTER.parseLocalDate(form
                .getTargetStartEndDate());

        LocalDate localDateMin = targetStartEndDate.dayOfMonth()
                .withMinimumValue();
        LocalDate localDateMax = targetStartEndDate.dayOfMonth()
                .withMaximumValue();

        model.addAttribute("resultStartDate", localDateMin.toString(
                "yyyy/MM/dd"));
        model.addAttribute("resultEndDate", localDateMax.toString(
                "yyyy/MM/dd"));
        return "jsp/dtop/showStartEndDate";
    }

    @GetMapping(value = "calcdate", params = "calcWeekStartEndDate")
    public String handleCalcWeekStartEndDate(Model model,
            DateManipulationForm form) {

        LocalDate targetStartEndDate = DATE_TIME_FORMATTER.parseLocalDate(form
                .getTargetStartEndDate());

        LocalDate localDateMin = targetStartEndDate.dayOfWeek()
                .withMinimumValue();
        LocalDate localDateMax = targetStartEndDate.dayOfWeek()
                .withMaximumValue();

        model.addAttribute("resultStartDate", localDateMin.toString(
                "yyyy/MM/dd"));
        model.addAttribute("resultEndDate", localDateMax.toString(
                "yyyy/MM/dd"));
        return "jsp/dtop/showStartEndDate";
    }
}
