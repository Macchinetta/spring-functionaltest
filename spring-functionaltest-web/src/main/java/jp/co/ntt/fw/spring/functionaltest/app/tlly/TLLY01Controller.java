/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.tlly;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tlly")
public class TLLY01Controller {

    @RequestMapping(value = "0101/001")
    public String handle0101001() {
        return "redirect:/tlly/staff/register";
    }

    @RequestMapping(value = "0102/001")
    public String handle0102001() {
        return "redirect:/tlly/staff/delete";
    }

}
