/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.stpr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("stpr")
@Controller
public class STPRController {

    @RequestMapping
    public String handle() {
        return "stpr/index";
    }

}
