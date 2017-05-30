/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.flup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("flup")
@Controller
public class FLUPController {

    @RequestMapping
    public String index() {
        return "flup/index";
    }

}
