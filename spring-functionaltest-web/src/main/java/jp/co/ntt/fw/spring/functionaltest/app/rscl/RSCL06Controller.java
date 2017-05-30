/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.rscl;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.HttpsRestClientService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.RestClientService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("rscl")
@Controller
public class RSCL06Controller {

    @Inject
    RestClientService restClientService;

    @Inject
    HttpsRestClientService httpsRestClientService;

    @RequestMapping(value = "0601/001", method = RequestMethod.GET)
    public String handle0601001First(Model model) {

        model.addAttribute("testDescription", "Self-signed certificate");
        model.addAttribute("testId", "0601/001");

        return "rscl/setSelfSignedCertificate";
    }

    @RequestMapping(value = "0601/001", method = RequestMethod.POST)
    public String handle0601001(Model model) {

        UserResource rcvUser = this.httpsRestClientService.connectHttps();

        model.addAttribute("resultDescription", "自己署名証明書情報");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

}
