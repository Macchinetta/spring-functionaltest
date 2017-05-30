/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dtac;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dtac")
public class DTACController {

    @RequestMapping
    public String handleIndex() {
        return "dtac/index";
    }

    @RequestMapping(value = "login")
    public String handleLognin() {
        return "dtac/login";
    }

    @RequestMapping(value = "login", params = "loginSuccess")
    public String handleLogninSuccess(Principal principal) {
        return "redirect:/dtac/user/list";
    }

    @RequestMapping(value = "select")
    public String handleSelect() {
        return "dtac/select";
    }

}
