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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.ntt.fw.spring.functionaltest.domain.service.dtop.DateOperationService;

@RequestMapping("dtop")
@Controller
public class DTOP05Controller {

    @Inject
    public DateOperationService dateOperationService;

    @RequestMapping(value = "0501/001", method = RequestMethod.GET)
    public String handle05001(Model model) {
        return "dtop/jspTagLibrary";
    }

    @RequestMapping(value = "0501/002", method = RequestMethod.GET)
    public String handle05002(Model model) {
        return "dtop/jspTagLibrary";
    }

    @RequestMapping(value = "0501/003", method = RequestMethod.GET)
    public String handle05003(Model model) {
        return "dtop/jspTagLibrary";
    }

    @RequestMapping(value = "0501/004", method = RequestMethod.GET)
    public String handle05004(Model model) {
        return "dtop/jspTagLibrary";
    }

    @RequestMapping(value = "jsptag/jspTagDateTime", method = RequestMethod.GET)
    public String handleGetNowDatetime(Model model) {
        model.addAttribute("resultDate", dateOperationService.getNowDateTime());
        return "dtop/showDateTime";
    }

    @RequestMapping(value = "jsptag/jspTagLocalDateTime", method = RequestMethod.GET)
    public String handleGetNowLocalDateTime(Model model) {
        model.addAttribute("resultDate", dateOperationService.getNowDateTime().toLocalDateTime());
        return "dtop/showLocalDateTime";
    }

    @RequestMapping(value = "jsptag/jspTagLocalDate", method = RequestMethod.GET)
    public String handleGetNowLocalDate(Model model) {
        model.addAttribute("resultDate", dateOperationService.getNowDateTime().toLocalDate());
        return "dtop/showLocalDate";
    }

    @RequestMapping(value = "jsptag/jspTagLocalTime", method = RequestMethod.GET)
    public String handleGetNowLocalTime(Model model) {
        model.addAttribute("resultDate", dateOperationService.getNowDateTime().toLocalTime());
        return "dtop/showLocalTime";
    }
}
