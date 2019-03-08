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

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.service.dtop.DateOperationService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("dtop")
@Controller
public class DTOP03Controller {

    @Inject
    public DateOperationService dateOperationService;

    @ModelAttribute
    public DateManipulationForm setUpForm() {
        return new DateManipulationForm();
    }

    @RequestMapping(value = "0301/001", method = RequestMethod.GET)
    public String handle01001(Model model) {
        return "dtop/dateManipulation";
    }

    @RequestMapping(value = "0301/002", method = RequestMethod.GET)
    public String handle01002(Model model) {
        return "dtop/dateManipulation";
    }

    @RequestMapping(value = "0301/003", method = RequestMethod.GET)
    public String handle01003(Model model) {
        return "dtop/dateManipulation";
    }

    @RequestMapping(value = "0301/004", method = RequestMethod.GET)
    public String handle01004(Model model) {
        return "dtop/dateManipulation";
    }

    @RequestMapping(value = "0301/005", method = RequestMethod.GET)
    public String handle01005(Model model) {
        return "dtop/dateManipulation";
    }

    @RequestMapping(value = "0301/006", method = RequestMethod.GET)
    public String handle01006(Model model) {
        return "dtop/dateManipulation";
    }

    @RequestMapping(value = "0302/001", method = RequestMethod.GET)
    public String handle02001(Model model) {
        return "dtop/dateManipulation";
    }

    @RequestMapping(value = "0302/002", method = RequestMethod.GET)
    public String handle02002(Model model) {
        return "dtop/dateManipulation";
    }

    @RequestMapping(value = "0303/001", method = RequestMethod.GET)
    public String handle03001(Model model) {
        return "dtop/dateManipulation";
    }

    @RequestMapping(value = "0303/002", method = RequestMethod.GET)
    public String handle03002(Model model) {
        return "dtop/dateManipulation";
    }

    @RequestMapping(value = "calcdate", method = RequestMethod.GET, params = "calcDayDate")
    public String handleCalculateDayDate(Model model,
            DateManipulationForm form) {
        model.addAttribute("resultStartDate", dateOperationService
                .calcIncreaseNumOfDay(form.getTargetIncDecDate(), form
                        .getTargetIncreaseNum()));
        model.addAttribute("resultEndDate", dateOperationService
                .calcDecreaseNumOfDay(form.getTargetIncDecDate(), form
                        .getTargetDecreaseNum()));
        return "dtop/showIncDecDate";
    }

    @RequestMapping(value = "calcdate", method = RequestMethod.GET, params = "calcMonthDate")
    public String handleCalculateMonthDate(Model model,
            DateManipulationForm form) {
        model.addAttribute("resultStartDate", dateOperationService
                .calcIncreaseNumOfMonth(form.getTargetIncDecDate(), form
                        .getTargetIncreaseNum()));
        model.addAttribute("resultEndDate", dateOperationService
                .calcDecreaseNumOfMonth(form.getTargetIncDecDate(), form
                        .getTargetDecreaseNum()));
        return "dtop/showIncDecDate";
    }

    @RequestMapping(value = "calcdate", method = RequestMethod.GET, params = "calcYearDate")
    public String handleCalculateYearDate(Model model,
            DateManipulationForm form) {
        model.addAttribute("resultStartDate", dateOperationService
                .calcIncreaseNumOfYear(form.getTargetIncDecDate(), form
                        .getTargetIncreaseNum()));
        model.addAttribute("resultEndDate", dateOperationService
                .calcDecreaseNumOfYear(form.getTargetIncDecDate(), form
                        .getTargetDecreaseNum()));
        return "dtop/showIncDecDate";
    }

    @RequestMapping(value = "calcdate", method = RequestMethod.GET, params = "calcMonthStartEndDate")
    public String handleCalcMonthStartEndDate(Model model,
            DateManipulationForm form) {
        model.addAttribute("resultStartDate", form.getTargetStartEndDate()
                .dayOfMonth().withMinimumValue());
        model.addAttribute("resultEndDate", form.getTargetStartEndDate()
                .dayOfMonth().withMaximumValue());
        return "dtop/showStartEndDate";
    }

    @RequestMapping(value = "calcdate", method = RequestMethod.GET, params = "calcWeekStartEndDate")
    public String handleCalcWeekStartEndDate(Model model,
            DateManipulationForm form) {
        model.addAttribute("resultStartDate", form.getTargetStartEndDate()
                .dayOfWeek().withMinimumValue());
        model.addAttribute("resultEndDate", form.getTargetStartEndDate()
                .dayOfWeek().withMaximumValue());
        return "dtop/showStartEndDate";
    }
}
