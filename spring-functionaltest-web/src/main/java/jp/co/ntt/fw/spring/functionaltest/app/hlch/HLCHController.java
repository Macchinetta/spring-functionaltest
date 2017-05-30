/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.hlch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("hlch")
@Controller
public class HLCHController {

    @RequestMapping
    public String index() {
        return "hlch/index";
    }

}
