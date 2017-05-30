/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.rscl;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.TimeoutRestClientService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("rscl")
@Controller
public class RSCL05Controller {

    @Inject
    TimeoutRestClientService timeoutRestClientService;

    @RequestMapping(value = "0501/001", method = RequestMethod.GET)
    public String handle0501001First(Model model) {

        model.addAttribute("testDescription", "Case [Conection Timeout]");
        model.addAttribute("testId", "0501/001");

        return "rscl/occurTimeout";
    }

    @RequestMapping(value = "0501/001", method = RequestMethod.POST)
    public void handle0501001(Model model) {

        this.timeoutRestClientService.confirmTimeout01();

    }

    @RequestMapping(value = "0502/001", method = RequestMethod.GET)
    public String handle0502001First(Model model) {

        model.addAttribute("testDescription", "Case [Read Timeout]");
        model.addAttribute("testId", "0502/001");

        return "rscl/occurTimeout";
    }

    @RequestMapping(value = "0502/001", method = RequestMethod.POST)
    public void handle0502001(Model model) {

        this.timeoutRestClientService.confirmTimeout02();
    }

}
