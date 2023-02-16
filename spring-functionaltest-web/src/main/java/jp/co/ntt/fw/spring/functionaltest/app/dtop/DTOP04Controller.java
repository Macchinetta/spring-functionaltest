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
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.service.dtop.DateOperationService;

@RequestMapping("dtop")
@Controller
public class DTOP04Controller {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat
            .forPattern("yyyy/MM/dd");

    @Inject
    public DateOperationService dateOperationService;

    @Inject
    public DateOperationHelper dateOperationHelper;

    @ModelAttribute
    public CheckTermForm setUpForm() {
        return new CheckTermForm();
    }

    @GetMapping(value = "0401/001")
    public String handle01001(Model model) {
        return "dtop/checkTerm";
    }

    @GetMapping(value = "0401/002")
    public String handle01002(Model model) {
        return "dtop/checkTerm";
    }

    @GetMapping(value = "0401/003")
    public String handle01003(Model model) {
        return "dtop/checkTerm";
    }

    @GetMapping(value = "0401/004")
    public String handle01004(Model model) {
        return "dtop/checkTerm";
    }

    @GetMapping(value = "0401/005")
    public String handle01005(Model model) {
        return "dtop/checkTerm";
    }

    @GetMapping(value = "0401/006")
    public String handle01006(Model model) {
        return "dtop/checkTerm";
    }

    @GetMapping(value = "0401/007")
    public String handle01007(Model model) {
        return "dtop/checkTerm";
    }

    @GetMapping(value = "0401/008")
    public String handle01008(Model model) {
        return "dtop/checkTerm";
    }

    @GetMapping(value = "0402/001")
    public String handle02001(Model model) {
        return "dtop/checkTerm";
    }

    @GetMapping(value = "0402/002")
    public String handle02002(Model model) {
        return "dtop/checkTerm";
    }

    @GetMapping(value = "checkterm", params = "checkContainTermToDate")
    public String handleCheckContainTermToDate(Model model,
            CheckTermForm form) {

        DateTime targetTermFrom = DATE_TIME_FORMATTER.parseDateTime(form
                .getTargetTermFrom());
        DateTime tergetTermTo = DATE_TIME_FORMATTER.parseDateTime(form
                .getTargetTermTo());
        DateTime targetCheckDate = DATE_TIME_FORMATTER.parseDateTime(form
                .getTargetCheckDate());

        model.addAttribute("result", dateOperationService
                .checkContainTermToDate(targetTermFrom, tergetTermTo,
                        targetCheckDate));
        return "dtop/showExistDate";
    }

    @GetMapping(value = "checkterm", params = "checkContainTermToTerm")
    public String handleCheckContainTermToTerm(Model model,
            CheckTermForm form) {

        DateTime targetTermFrom = DATE_TIME_FORMATTER.parseDateTime(form
                .getTargetTermFrom());
        DateTime tergetTermTo = DATE_TIME_FORMATTER.parseDateTime(form
                .getTargetTermTo());
        DateTime targetCheckTermFrom = DATE_TIME_FORMATTER.parseDateTime(form
                .getTargetCheckTermFrom());
        DateTime targetCheckTermTo = DATE_TIME_FORMATTER.parseDateTime(form
                .getTargetCheckTermTo());

        model.addAttribute("result", dateOperationService
                .checkContainTermToTerm(targetTermFrom, tergetTermTo,
                        targetCheckTermFrom, targetCheckTermTo));
        return "dtop/showExistDate";
    }

    @GetMapping(value = "checkterm", params = "checkAbutsTerm")
    public String handleCheckAbutsTerm(Model model, CheckTermForm form) {

        DateTime targetTermFrom = DATE_TIME_FORMATTER.parseDateTime(form
                .getTargetTermFrom());
        DateTime tergetTermTo = DATE_TIME_FORMATTER.parseDateTime(form
                .getTargetTermTo());
        DateTime targetCheckTermFrom = DATE_TIME_FORMATTER.parseDateTime(form
                .getTargetCheckTermFrom());
        DateTime targetCheckTermTo = DATE_TIME_FORMATTER.parseDateTime(form
                .getTargetCheckTermTo());

        model.addAttribute("result", dateOperationService.checkAbutsTerm(
                targetTermFrom, tergetTermTo, targetCheckTermFrom,
                targetCheckTermTo));
        return "dtop/showExistDate";
    }

    @GetMapping(value = "checkterm", params = "getGapTerm")
    public String handleGetGapTerm(Model model, CheckTermForm form) {

        DateTime targetTermFrom = DATE_TIME_FORMATTER.parseDateTime(form
                .getTargetTermFrom());
        DateTime tergetTermTo = DATE_TIME_FORMATTER.parseDateTime(form
                .getTargetTermTo());
        DateTime targetCheckTermFrom = DATE_TIME_FORMATTER.parseDateTime(form
                .getTargetCheckTermFrom());
        DateTime targetCheckTermTo = DATE_TIME_FORMATTER.parseDateTime(form
                .getTargetCheckTermTo());

        dateOperationHelper.bindTermResultToModel(model, dateOperationService
                .getGapTerm(targetTermFrom, tergetTermTo, targetCheckTermFrom,
                        targetCheckTermTo));
        return "dtop/showExistTerm";
    }

    @GetMapping(value = "checkterm", params = "getOverlapTerm")
    public String handleGetOverlapTerm(Model model, CheckTermForm form) {

        DateTime targetTermFrom = DATE_TIME_FORMATTER.parseDateTime(form
                .getTargetTermFrom());
        DateTime tergetTermTo = DATE_TIME_FORMATTER.parseDateTime(form
                .getTargetTermTo());
        DateTime targetCheckTermFrom = DATE_TIME_FORMATTER.parseDateTime(form
                .getTargetCheckTermFrom());
        DateTime targetCheckTermTo = DATE_TIME_FORMATTER.parseDateTime(form
                .getTargetCheckTermTo());

        dateOperationHelper.bindTermResultToModel(model, dateOperationService
                .getOverlapTerm(targetTermFrom, tergetTermTo,
                        targetCheckTermFrom, targetCheckTermTo));
        return "dtop/showExistTerm";
    }

    @GetMapping(value = "checkterm", params = "calcMonthDate")
    public String handleCalcMonthDate(Model model, CheckTermForm form) {

        DateTime targetIncDecDate = DATE_TIME_FORMATTER.parseDateTime(form
                .getTargetIncDecDate());

        DateTime startDate = dateOperationService
                .calcIncreaseNumOfDateUsingPeriod(targetIncDecDate,
                        dateOperationHelper.getPeriodByMonth(form
                                .getTargetIncreaseMonthNum()));
        DateTime endDate = dateOperationService
                .calcDecreaseNumOfDateUsingPeriod(targetIncDecDate,
                        dateOperationHelper.getPeriodByMonth(form
                                .getTargetDecreaseMonthNum()));
        model.addAttribute("resultStartDate", startDate.toString("yyyy/MM/dd"));
        model.addAttribute("resultEndDate", endDate.toString("yyyy/MM/dd"));
        return "dtop/showIncDecDate";
    }

    @GetMapping(value = "checkterm", params = "calcMonthAndDayDate")
    public String handleCalcMonthAndDayDate(Model model, CheckTermForm form) {

        DateTime targetIncDecDate = DATE_TIME_FORMATTER.parseDateTime(form
                .getTargetIncDecDate());

        DateTime startDate = dateOperationService
                .calcIncreaseNumOfDateUsingPeriod(targetIncDecDate,
                        dateOperationHelper.getPeriodByMonthAndDay(form
                                .getTargetIncreaseMonthNum(), form
                                        .getTargetIncreaseDayNum()));
        DateTime endDate = dateOperationService
                .calcDecreaseNumOfDateUsingPeriod(targetIncDecDate,
                        dateOperationHelper.getPeriodByMonthAndDay(form
                                .getTargetDecreaseMonthNum(), form
                                        .getTargetDecreaseDayNum()));
        model.addAttribute("resultStartDate", startDate.toString("yyyy/MM/dd"));
        model.addAttribute("resultEndDate", endDate.toString("yyyy/MM/dd"));
        return "dtop/showIncDecDate";
    }

}
