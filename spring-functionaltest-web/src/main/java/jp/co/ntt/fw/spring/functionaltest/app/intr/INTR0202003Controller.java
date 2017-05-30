/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.intr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class INTR0202003Controller {

    @RequestMapping(method = RequestMethod.GET)
    public String handle003() {
        return "intr/userDetails";
    }

}
