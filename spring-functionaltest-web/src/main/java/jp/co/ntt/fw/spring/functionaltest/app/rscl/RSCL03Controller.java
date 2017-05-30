/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.rscl;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UserResource;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.RestClientService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("rscl")
@Controller
public class RSCL03Controller {

    @Inject
    RestClientService restClientService;

    @ModelAttribute(value = "authenticationForm")
    public AuthenticationForm setUpAuthenticationForm() {
        return new AuthenticationForm();
    }

    @RequestMapping(value = "0303/001", method = RequestMethod.GET)
    public String handle0303001First(Model model) {

        model.addAttribute("testDescription",
                "Authenticationヘッダを設定して、REST APIを呼び出し、Javaオブジェクトを受信");
        model.addAttribute("testId", "0303/001");

        return "rscl/setAuthenticationInf";
    }

    @RequestMapping(value = "0303/001", method = RequestMethod.POST)
    public String handle0303001(Model model, AuthenticationForm form) {

        UserResource rcvUser = this.restClientService
                .exchangeWithAuthentication(form.getUserid(), form
                        .getPassword());

        model.addAttribute("resultDescription", "Basic認証ユーザ情報");
        model.addAttribute("user", rcvUser);

        return "rscl/resultUserInf";
    }

}
