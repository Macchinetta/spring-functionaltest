/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dtac;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dtac")
public class DTAC02Controller {

    @RequestMapping(value = "0201/001")
    public String handle0201001() {
        return "redirect:/dtac/user/list";
    }

}
