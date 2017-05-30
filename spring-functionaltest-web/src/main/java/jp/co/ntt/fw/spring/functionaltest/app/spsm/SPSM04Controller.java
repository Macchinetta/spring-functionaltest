/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.spsm;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SPSM04Controller {

    @RequestMapping("0403/001/001/login")
    public String handle0401002001Login(Model model) {
        model.addAttribute("testNo", "0403/001/001");
        return "spsm/loginForInvalidSession";
    }

    @RequestMapping(value = "0403/001/001", params = "loginSuccess")
    public String handle0401002001LoginSuccess(
            @AuthenticationPrincipal User userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        model.addAttribute("testNo", "0403/001/001");
        return "spsm/validSession";
    }

    @RequestMapping("0403/001/002/login")
    public String handle0401002002Login(Model model) {
        model.addAttribute("testNo", "0403/001/002");
        return "spsm/loginForInvalidSession";
    }

    @RequestMapping(value = "0403/001/002", params = "loginSuccess")
    public String handle0401002002LoginSuccess(
            @AuthenticationPrincipal User userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        model.addAttribute("testNo", "0403/001/002");
        return "spsm/validSession";
    }

}
