/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.djpa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("djpa")
public class DJPAController09 {

    @RequestMapping(value = "0901/001")
    public String handle0901001() {
        return "redirect:/djpa/order";
    }

    @RequestMapping(value = "0902/001")
    public String handle0902001() {
        return "redirect:/djpa/order";
    }

    @RequestMapping(value = "0903/001")
    public String handle0903001() {
        return "redirect:/djpa/order";
    }

    @RequestMapping(value = "0904/001")
    public String handle0904001() {
        return "redirect:/djpa/order";
    }

    @RequestMapping(value = "0905/001")
    public String handle0905001() {
        return "redirect:/djpa/order";
    }

}
