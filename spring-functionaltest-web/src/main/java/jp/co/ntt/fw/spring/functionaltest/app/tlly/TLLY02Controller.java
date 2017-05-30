/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.tlly;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tlly")
public class TLLY02Controller {

    @RequestMapping(value = "0201/001")
    public String handle0201001() {
        return "redirect:/tlly/staff/update";
    }

    @RequestMapping(value = "0201/002")
    public String handle0201002() {
        return "redirect:/tlly/staff/search";
    }

    @RequestMapping(value = "0202/001")
    public String handle0202001() {
        return "redirect:/tlly/staff/update";
    }

}
