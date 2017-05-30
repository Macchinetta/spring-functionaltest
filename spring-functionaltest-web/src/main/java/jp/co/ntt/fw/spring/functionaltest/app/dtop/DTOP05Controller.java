/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dtop;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.service.dtop.DateOperationService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        model.addAttribute("resultDate", dateOperationService.getNowDateTime()
                .toLocalDateTime());
        return "dtop/showLocalDateTime";
    }

    @RequestMapping(value = "jsptag/jspTagLocalDate", method = RequestMethod.GET)
    public String handleGetNowLocalDate(Model model) {
        model.addAttribute("resultDate", dateOperationService.getNowDateTime()
                .toLocalDate());
        return "dtop/showLocalDate";
    }

    @RequestMapping(value = "jsptag/jspTagLocalTime", method = RequestMethod.GET)
    public String handleGetNowLocalTime(Model model) {
        model.addAttribute("resultDate", dateOperationService.getNowDateTime()
                .toLocalTime());
        return "dtop/showLocalTime";
    }
}
