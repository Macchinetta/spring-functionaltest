/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.ajax;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("ajax")
@Controller
public class AJAXController {

    @RequestMapping
    public String index() {
        return "ajax/index";
    }

}
