/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dnta;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("dnta")
@Controller
public class DNTAController {

    @RequestMapping
    public String handle() {
        return "dnta/index";
    }

}
