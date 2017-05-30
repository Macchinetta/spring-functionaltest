/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.aply;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("aply")
@Controller
public class APLYController {

    @RequestMapping
    public String index() {
        return "aply/index";
    }

}
