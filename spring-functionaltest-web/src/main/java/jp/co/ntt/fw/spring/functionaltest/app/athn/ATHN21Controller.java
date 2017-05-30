/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.athn;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ATHN21Controller {

    @RequestMapping("2101/001")
    public String handle2101001() {
        return "athn/loginForRememberMe";
    }

    @RequestMapping(value = "2101", params = "loginSuccess")
    public String handle2101LoginSuccess(
            @AuthenticationPrincipal User userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
            model.addAttribute("userEmail", userDetails.getUsername()
                    + "@example.com");
        }
        return "athn/rememberMeUserInfoDsp";
    }
}
