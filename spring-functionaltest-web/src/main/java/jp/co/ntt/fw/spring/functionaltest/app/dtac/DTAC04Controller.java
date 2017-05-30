/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dtac;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dtac")
public class DTAC04Controller {

    @RequestMapping(value = "0401/001")
    public String handle0401001() {
        return "redirect:/dtac/login";
    }

    @RequestMapping(value = "0401/002")
    public String handle0401002() {
        return "redirect:/dtac/login";
    }

}
