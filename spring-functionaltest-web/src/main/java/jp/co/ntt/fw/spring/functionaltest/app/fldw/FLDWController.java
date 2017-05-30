/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.fldw;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("fldw")
@Controller
public class FLDWController {

    @RequestMapping
    public String index() {
        return "fldw/index";
    }

}
