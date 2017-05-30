/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dtop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("dtop")
@Controller
public class DTOPController {

    @RequestMapping
    public String handle() {
        return "dtop/index";
    }

}
