/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.intr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class INTR0102Controller {

    @RequestMapping(value = "001", method = RequestMethod.GET)
    public String handle001() {
        return "intr/userDetails";
    }

    @RequestMapping(value = "002", method = RequestMethod.GET)
    public String handle002() {
        return "intr/userDetails";
    }

    @RequestMapping(value = "003", method = RequestMethod.GET)
    public String handle003() {
        return "intr/userDetails";
    }

}
