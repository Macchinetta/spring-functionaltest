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
public class DTOP06Controller {

    @Inject
    public DateOperationService dateOperationService;

    @RequestMapping(value = "0601/001", method = RequestMethod.GET)
    public String handle05001(Model model) {
        model.addAttribute("resultDate", dateOperationService
                .getJapaneseDateStr("Gy.MM.dd"));
        return "dtop/showJapaneseDate";
    }

    @RequestMapping(value = "0601/002", method = RequestMethod.GET)
    public String handle05002(Model model) {
        model.addAttribute("resultDate", dateOperationService
                .getJapaneseDateStr("GGGGyy/MM/dd"));
        return "dtop/showJapaneseDate";
    }

    @RequestMapping(value = "0601/003", method = RequestMethod.GET)
    public String handle05003(Model model) {
        model.addAttribute("resultDate", dateOperationService
                .parseJapaneseDate("H25.12.09", "Gy.MM.dd"));
        return "dtop/showJapaneseDate";
    }

    @RequestMapping(value = "0601/004", method = RequestMethod.GET)
    public String handle05004(Model model) {
        model.addAttribute("resultDate", dateOperationService
                .parseJapaneseDate("平成25/12/09", "GGGGyy/MM/dd"));
        return "dtop/showJapaneseDate";
    }

}
