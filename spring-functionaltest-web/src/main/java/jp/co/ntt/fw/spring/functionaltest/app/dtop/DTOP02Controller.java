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

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("dtop")
@Controller
public class DTOP02Controller {

    @Inject
    DateOperationService dateOperationService;

    @ModelAttribute
    public ChangeTypeForm setUpForm() {
        return new ChangeTypeForm();
    }

    @RequestMapping(value = "0201/001", method = RequestMethod.GET)
    public String handle01001(Model model) {
        return "dtop/changeType";
    }

    @RequestMapping(value = "0201/002", method = RequestMethod.GET)
    public String handle01002(Model model) {
        return "dtop/changeType";
    }

    @RequestMapping(value = "0202/001", method = RequestMethod.GET)
    public String handle02001(Model model) {
        return "dtop/changeType";
    }

    @RequestMapping(value = "getdatetype", method = RequestMethod.GET, params = "getDateToDateTimeObject")
    public String handleGetDateToDateTimeObject(Model model) {
        model.addAttribute("resultDate", new DateTime(dateOperationService
                .getNowDate()));
        return "dtop/showDateTime";
    }

    @RequestMapping(value = "getdatetype", method = RequestMethod.GET, params = "getDateTimeToDateObject")
    public String handleGetDateTimeToDateObject(Model model) {
        model.addAttribute("resultDate", dateOperationService.getNowDateTime()
                .toDate());
        return "dtop/showDate";
    }

    @RequestMapping(value = "getdatetype", method = RequestMethod.GET, params = "getParseDate")
    public String handleGetParseDateObject(Model model, ChangeTypeForm form) {
        model.addAttribute("resultDate", DateTimeFormat.forPattern("yyyy/MM/dd")
                .parseLocalDate(form.getTargetDate()));
        return "dtop/showLocalDate";
    }
}
