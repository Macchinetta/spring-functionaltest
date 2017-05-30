/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.lggn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("lggn")
@Controller
public class LGGNController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String handle001() {
        return "lggn/index";
    }
}
