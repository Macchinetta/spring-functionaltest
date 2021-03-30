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

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.ntt.fw.spring.functionaltest.domain.service.dtop.DateOperationService;

@RequestMapping("dtop")
@Controller
public class DTOP01Controller {

    @Inject
    DateOperationService dateOperationService;

    @ModelAttribute
    public GetDateAndTimeForm setUpForm() {
        return new GetDateAndTimeForm();
    }

    @RequestMapping(value = "0101/001", method = RequestMethod.GET)
    public String handle01001(Model model) {
        return "dtop/getDateAndTime";
    }

    @RequestMapping(value = "0101/002", method = RequestMethod.GET)
    public String handle01002(Model model) {
        return "dtop/getDateAndTime";
    }

    @RequestMapping(value = "0101/003", method = RequestMethod.GET)
    public String handle01003(Model model) {
        return "dtop/getDateAndTime";
    }

    @RequestMapping(value = "0101/004", method = RequestMethod.GET)
    public String handle01004(Model model) {
        return "dtop/getDateAndTime";
    }

    @RequestMapping(value = "0101/005", method = RequestMethod.GET)
    public String handle01005(Model model) {
        return "dtop/getDateAndTime";
    }

    @RequestMapping(value = "0102/001", method = RequestMethod.GET)
    public String handle02001(Model model) {
        return "dtop/getDateAndTime";
    }

    @RequestMapping(value = "0103/001", method = RequestMethod.GET)
    public String handle03001(Model model) {
        return "dtop/getDateAndTime";
    }

    @RequestMapping(value = "getdate", method = RequestMethod.GET, params = "getNowDateTime")
    public String handleGetNowDateTime(Model model) {
        DateTime dateTime = dateOperationService.getNowDateTime();
        model.addAttribute("resultDate", dateTime);
        return "dtop/showDateTime";
    }

    @RequestMapping(value = "getdate", method = RequestMethod.GET, params = "getNowLocalDate")
    public String handleGetNowLocalDate(Model model) {
        DateTime dateTime = dateOperationService.getNowDateTime();
        model.addAttribute("resultDate", dateTime.toLocalDate());
        return "dtop/showLocalDate";
    }

    @RequestMapping(value = "getdate", method = RequestMethod.GET, params = "getNowLocalTime")
    public String handleGetNowLocalTime(Model model) {
        DateTime dateTime = dateOperationService.getNowDateTime();
        model.addAttribute("resultDate", dateTime.toLocalTime());
        return "dtop/showLocalTime";
    }

    @SuppressWarnings("deprecation")
    @RequestMapping(value = "getdate", method = RequestMethod.GET, params = "getNowDateMidnight")
    public String handleGetNowDateMidnight(Model model) {
        DateTime dateTime = dateOperationService.getNowDateTime();
        model.addAttribute("resultDate", dateTime.toDateMidnight());
        return "dtop/showDateMidnight";
    }

    @RequestMapping(value = "getdate", method = RequestMethod.GET, params = "getWithTimeAtStartOfDay")
    public String handleGetNowDateTimeAtStartOfDay(Model model) {
        DateTime dateTime = dateOperationService.getNowDateTime();
        model.addAttribute("resultDate", dateTime.withTimeAtStartOfDay());
        return "dtop/showDateTimeWithTimeAtStartOfDay";
    }

    @RequestMapping(value = "getdate", method = RequestMethod.GET, params = "getDateTimeSpecifiedTimezone")
    public String handleGetDateTimeSpecifiedTimezone(Model model,
            GetDateAndTimeForm form) {
        DateTime dateTime = dateOperationService.getNowDateTime();
        DateTimeZone timezone = DateTimeZone.forID(form.getTargetTimeZone());
        model.addAttribute("resultDate", dateTime.withZone(timezone));
        return "dtop/showDateTime";
    }

    @RequestMapping(value = "getdate", method = RequestMethod.GET, params = "getPartOfDateTime")
    public String handleGetPartOfDateTime(Model model) {
        DateTime dateTime = dateOperationService.getNowDateTime();
        model.addAttribute("resultDate", dateTime);
        return "dtop/showPartOfDateTime";
    }
}
