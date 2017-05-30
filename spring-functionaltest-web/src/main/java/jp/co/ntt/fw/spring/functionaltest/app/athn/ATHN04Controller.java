/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.athn;

import jp.co.ntt.fw.spring.functionaltest.domain.service.athn.AccountUserDetails;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ATHN04Controller {

    @RequestMapping(value = "0401/001", params = "loginSuccess")
    public String handle0401LoginSuccess(
            @AuthenticationPrincipal AccountUserDetails userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        model.addAttribute("testNo", "0401/001");
        return "athn/topForDeafalut";
    }

}
