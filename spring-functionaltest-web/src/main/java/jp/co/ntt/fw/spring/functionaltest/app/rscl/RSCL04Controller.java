/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.rscl;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.CustomErrorRestClientService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.RestClientService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("rscl")
@Controller
public class RSCL04Controller {

    @Inject
    RestClientService restClientService;

    @Inject
    CustomErrorRestClientService customErrorRestClientService;

    @RequestMapping(value = "0402/001", method = RequestMethod.GET)
    public String handle0402001First(Model model) {

        model.addAttribute("testDescription", "Occur 404 Exception");
        model.addAttribute("testId", "0402/001");

        return "rscl/occurException";
    }

    @RequestMapping(value = "0402/001", method = RequestMethod.POST)
    public void handle0402001(Model model) {

        this.restClientService.handleException01();

    }

    @RequestMapping(value = "0403/001", method = RequestMethod.GET)
    public String handle0403001First(Model model) {

        model.addAttribute("testDescription", "Occur 500 Server Exception");
        model.addAttribute("testId", "0403/001");

        return "rscl/occurException";
    }

    @RequestMapping(value = "0403/001", method = RequestMethod.POST)
    public void handle0403001(Model model) {

        this.restClientService.handleException02();

    }

    @RequestMapping(value = "0404/001", method = RequestMethod.GET)
    public String handle0404001First(Model model) {

        model.addAttribute("testDescription",
                "Occur return unKnown Status [901] Exception");
        model.addAttribute("testId", "0404/001");

        return "rscl/occurException";
    }

    @RequestMapping(value = "0404/001", method = RequestMethod.POST)
    public void handle0404001(Model model) {

        this.restClientService.handleException03();

    }

    @RequestMapping(value = "0405/001", method = RequestMethod.GET)
    public String handle0405001First(Model model) {

        model.addAttribute("testDescription",
                "Individual Error handling [retry]");
        model.addAttribute("testId", "0405/001");

        return "rscl/occurException";
    }

    @RequestMapping(value = "0405/001", method = RequestMethod.POST)
    public String handle0405001(Model model) {

        UserResource rcvUser = this.restClientService.handleException04();

        model.addAttribute("resultDescription",
                "[0405]処理結果およびExceptionの有無、リトライ回数をログでアサート");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

    @RequestMapping(value = "0406/001", method = RequestMethod.GET)
    public String handle0406001First(Model model) {

        model.addAttribute("testDescription",
                "DefaultResponse Error handling [retry]");
        model.addAttribute("testId", "0406/001");

        return "rscl/occurException";
    }

    @RequestMapping(value = "0406/001", method = RequestMethod.POST)
    public String handle0406001(Model model) {

        UserResource rcvUser = this.customErrorRestClientService
                .handleException05();

        model.addAttribute("resultDescription",
                "[0406]処理結果およびExceptionの有無、リトライ回数をログでアサート");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

}
