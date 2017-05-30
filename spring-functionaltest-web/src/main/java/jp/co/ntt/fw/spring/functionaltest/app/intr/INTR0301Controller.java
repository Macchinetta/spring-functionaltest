/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.intr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class INTR0301Controller {

    public String login() {
        return "intr/login";
    }

    @RequestMapping(value = "001/login")
    public String login001() {
        return login();
    }

    @RequestMapping(value = "001")
    public String handle001() {
        return "intr/userDetails";
    }

    @RequestMapping(value = "error")
    public String handleError() {
        return "intr/accessDeniedPage";
    }
}
