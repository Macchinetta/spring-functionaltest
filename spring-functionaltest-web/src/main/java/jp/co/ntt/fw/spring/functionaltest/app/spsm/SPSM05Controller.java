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
public class SPSM05Controller {

    @RequestMapping("0501/001/login")
    public String handle0501001Login(Model model) {
        model.addAttribute("testNo", "0501/001");
        return "spsm/loginForSessionConcurrencyControlAfter";
    }

    @RequestMapping(value = "0501/001", params = "loginSuccess")
    public String handle0501001LoginSuccess(
            @AuthenticationPrincipal User userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        model.addAttribute("testNo", "0501/001");
        return "spsm/topForDeafalut";
    }

    @RequestMapping("0501/002/login")
    public String handle0501002Login(Model model) {
        model.addAttribute("testNo", "0501/001");
        return "spsm/loginForSessionConcurrencyControlBefore";
    }

    @RequestMapping(value = "0501/002", params = "error")
    public String handle0501002LoginError(Model model) {
        model.addAttribute("testNo", "0501/002");
        return "spsm/loginForSessionConcurrencyControlBefore";
    }

    @RequestMapping(value = "0501/002", params = "loginSuccess")
    public String handle0501002LoginSuccess(
            @AuthenticationPrincipal User userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        model.addAttribute("testNo", "0501/002");
        return "spsm/topForDeafalut";
    }

}
