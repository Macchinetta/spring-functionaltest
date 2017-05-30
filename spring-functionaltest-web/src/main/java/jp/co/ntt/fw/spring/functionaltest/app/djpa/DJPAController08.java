/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.djpa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("djpa")
public class DJPAController08 {

    @RequestMapping(value = "0801/001")
    public String handle0801001() {
        return "redirect:/djpa/order";
    }

    @RequestMapping(value = "0802/001")
    public String handle0802001() {
        return "redirect:/djpa/order";
    }

    @RequestMapping(value = "0803/001")
    public String handle0803001() {
        return "redirect:/djpa/order";
    }

    @RequestMapping(value = "0804/001")
    public String handle0804001() {
        return "redirect:/djpa/order";
    }

    @RequestMapping(value = "0804/002")
    public String handle0804002() {
        return "redirect:/djpa/order";
    }

    @RequestMapping(value = "0804/003")
    public String handle0804003() {
        return "redirect:/djpa/order";
    }

}
