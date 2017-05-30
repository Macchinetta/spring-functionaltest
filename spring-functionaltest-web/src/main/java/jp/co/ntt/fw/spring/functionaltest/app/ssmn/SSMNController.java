/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.ssmn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("ssmn")
@Controller
public class SSMNController {

    @RequestMapping
    public String handle() {
        return "ssmn/index";
    }
}
