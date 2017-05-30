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
public class ATHN07Controller {

    @RequestMapping(value = { "0701/001", "0702/001" }, params = "loginSuccess")
    public String handle0701LoginSuccess(
            @AuthenticationPrincipal User userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        return "athn/topForDeafalutLogout";
    }

}
