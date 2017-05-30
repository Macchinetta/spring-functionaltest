/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.bnmp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("bnmp")
@Controller
public class BNMPController {

    @RequestMapping
    public String handle() {
        return "bnmp/index";
    }
}
