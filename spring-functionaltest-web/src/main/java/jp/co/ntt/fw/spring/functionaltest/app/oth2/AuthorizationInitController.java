/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.oth2;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.ntt.fw.spring.functionaltest.domain.service.oth2.OauthAuthorizationInitService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.oth2.OauthInitContextTokenService;

/**
 * <ul>
 * <li>Calls Service to delete for approval information and access token.</li>
 * <li>This method delete approvals and tokens from DB.</li>
 * </ul>
 */
@RequestMapping("oth2/client/common")
@Controller
public class AuthorizationInitController {

    @Inject
    OauthAuthorizationInitService authorizationService;

    @Inject
    OauthInitContextTokenService oauthInitContextTokenService;

    /**
     * <ul>
     * <li>Calls Service to delete approvals and token.</li>
     * <li>This method delete approvals and tokens from DB, and delete context token from RestOperations.
     * </ul>
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public String handleClear(Model model) {

        authorizationService.deleteApproveAndToken();

        oauthInitContextTokenService.initContextTokenAll();

        model.addAttribute("response", "Success");
        model.addAttribute("title", "初期化の結果");
        model.addAttribute("token", "-");
        return "oth2/view";
    }
}
