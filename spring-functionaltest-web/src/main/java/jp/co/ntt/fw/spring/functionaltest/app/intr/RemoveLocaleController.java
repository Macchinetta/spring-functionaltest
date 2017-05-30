/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.intr;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("intr")
public class RemoveLocaleController {

    @RequestMapping(value = "removeLocale", method = RequestMethod.GET)
    public String removeLocale(HttpSession session) {

        session.removeAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE");

        return "intr/index";
    }

}
