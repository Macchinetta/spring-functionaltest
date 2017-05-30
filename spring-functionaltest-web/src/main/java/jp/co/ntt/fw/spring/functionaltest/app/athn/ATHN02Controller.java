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
public class ATHN02Controller {

    @RequestMapping(value = "0201/001", params = "loginSuccess")
    public String handle0201001(@AuthenticationPrincipal User userDetails,
            Model model) {
        model.addAttribute("testNo", "0201/001");
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }

        return "athn/topForDeafalut";
    }
}
