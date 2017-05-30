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
public class ATHN01Controller {

    @RequestMapping(value = "0102", params = "loginSuccess")
    public String handle0101LoginSuccess(
            @AuthenticationPrincipal User userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        model.addAttribute("testNo", "0102/001");
        return "athn/topForDeafalut";
    }

    @RequestMapping("0102/001")
    public String handle0102001(@AuthenticationPrincipal User userDetails,
            Model model) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }

        model.addAttribute("testNo", "0102/001");
        return "athn/topForDeafalut";
    }

    @RequestMapping("0102/002")
    public String handle0102002(Model model) {
        model.addAttribute("testNo", "0102/002");
        return "athn/top0102";
    }
}
