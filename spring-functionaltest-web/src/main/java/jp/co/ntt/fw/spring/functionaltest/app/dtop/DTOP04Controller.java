/*
 * Copyright 2014-2017 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
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
public class DTOP04Controller {

    @Inject
    public DateOperationService dateOperationService;

    @Inject
    public DateOperationHelper dateOperationHelper;

    @ModelAttribute
    public CheckTermForm setUpForm() {
        return new CheckTermForm();
    }

    @RequestMapping(value = "0401/001", method = RequestMethod.GET)
    public String handle01001(Model model) {
        return "dtop/checkTerm";
    }

    @RequestMapping(value = "0401/002", method = RequestMethod.GET)
    public String handle01002(Model model) {
        return "dtop/checkTerm";
    }

    @RequestMapping(value = "0401/003", method = RequestMethod.GET)
    public String handle01003(Model model) {
        return "dtop/checkTerm";
    }

    @RequestMapping(value = "0401/004", method = RequestMethod.GET)
    public String handle01004(Model model) {
        return "dtop/checkTerm";
    }

    @RequestMapping(value = "0401/005", method = RequestMethod.GET)
    public String handle01005(Model model) {
        return "dtop/checkTerm";
    }

    @RequestMapping(value = "0401/006", method = RequestMethod.GET)
    public String handle01006(Model model) {
        return "dtop/checkTerm";
    }

    @RequestMapping(value = "0401/007", method = RequestMethod.GET)
    public String handle01007(Model model) {
        return "dtop/checkTerm";
    }

    @RequestMapping(value = "0401/008", method = RequestMethod.GET)
    public String handle01008(Model model) {
        return "dtop/checkTerm";
    }

    @RequestMapping(value = "0402/001", method = RequestMethod.GET)
    public String handle02001(Model model) {
        return "dtop/checkTerm";
    }

    @RequestMapping(value = "0402/002", method = RequestMethod.GET)
    public String handle02002(Model model) {
        return "dtop/checkTerm";
    }

    @RequestMapping(value = "checkterm", method = RequestMethod.GET, params = "checkContainTermToDate")
    public String handleCheckContainTermToDate(Model model,
            CheckTermForm form) {
        model.addAttribute("result", dateOperationService
                .checkContainTermToDate(form.getTargetTermFrom(), form
                        .getTargetTermTo(), form.getTargetCheckDate()));
        return "dtop/showExistDate";
    }

    @RequestMapping(value = "checkterm", method = RequestMethod.GET, params = "checkContainTermToTerm")
    public String handleCheckContainTermToTerm(Model model,
            CheckTermForm form) {
        model.addAttribute("result", dateOperationService
                .checkContainTermToTerm(form.getTargetTermFrom(), form
                        .getTargetTermTo(), form.getTargetCheckTermFrom(), form
                                .getTargetCheckTermTo()));
        return "dtop/showExistDate";
    }

    @RequestMapping(value = "checkterm", method = RequestMethod.GET, params = "checkAbutsTerm")
    public String handleCheckAbutsTerm(Model model, CheckTermForm form) {
        model.addAttribute("result", dateOperationService.checkAbutsTerm(form
                .getTargetTermFrom(), form.getTargetTermTo(), form
                        .getTargetCheckTermFrom(), form
                                .getTargetCheckTermTo()));
        return "dtop/showExistDate";
    }

    @RequestMapping(value = "checkterm", method = RequestMethod.GET, params = "getGapTerm")
    public String handleGetGapTerm(Model model, CheckTermForm form) {
        dateOperationHelper.bindTermResultToModel(model, dateOperationService
                .getGapTerm(form.getTargetTermFrom(), form.getTargetTermTo(),
                        form.getTargetCheckTermFrom(), form
                                .getTargetCheckTermTo()));
        return "dtop/showExistTerm";
    }

    @RequestMapping(value = "checkterm", method = RequestMethod.GET, params = "getOverlapTerm")
    public String handleGetOverlapTerm(Model model, CheckTermForm form) {
        dateOperationHelper.bindTermResultToModel(model, dateOperationService
                .getOverlapTerm(form.getTargetTermFrom(), form
                        .getTargetTermTo(), form.getTargetCheckTermFrom(), form
                                .getTargetCheckTermTo()));
        return "dtop/showExistTerm";
    }

    @RequestMapping(value = "checkterm", method = RequestMethod.GET, params = "calcMonthDate")
    public String handleCalcMonthDate(Model model, CheckTermForm form) {
        model.addAttribute("resultStartDate", dateOperationService
                .calcIncreaseNumOfDateUsingPeriod(form.getTargetIncDecDate(),
                        dateOperationHelper.getPeriodByMonth(form
                                .getTargetIncreaseMonthNum())));
        model.addAttribute("resultEndDate", dateOperationService
                .calcDecreaseNumOfDateUsingPeriod(form.getTargetIncDecDate(),
                        dateOperationHelper.getPeriodByMonth(form
                                .getTargetDecreaseMonthNum())));
        return "dtop/showIncDecDate";
    }

    @RequestMapping(value = "checkterm", method = RequestMethod.GET, params = "calcMonthAndDayDate")
    public String handleCalcMonthAndDayDate(Model model, CheckTermForm form) {
        model.addAttribute("resultStartDate", dateOperationService
                .calcIncreaseNumOfDateUsingPeriod(form.getTargetIncDecDate(),
                        dateOperationHelper.getPeriodByMonthAndDay(form
                                .getTargetIncreaseMonthNum(), form
                                        .getTargetIncreaseDayNum())));
        model.addAttribute("resultEndDate", dateOperationService
                .calcDecreaseNumOfDateUsingPeriod(form.getTargetIncDecDate(),
                        dateOperationHelper.getPeriodByMonthAndDay(form
                                .getTargetDecreaseMonthNum(), form
                                        .getTargetDecreaseDayNum())));
        return "dtop/showIncDecDate";
    }

}
