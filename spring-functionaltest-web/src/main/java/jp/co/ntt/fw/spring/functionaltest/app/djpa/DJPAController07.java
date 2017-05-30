/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.djpa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("djpa")
public class DJPAController07 {

    @RequestMapping(value = "0701/001")
    public String handle0701001() {
        return "redirect:/djpa/delivery/order";
    }

    @RequestMapping(value = "0702/001")
    public String handle0702001() {
        return "redirect:/djpa/order";
    }

    @RequestMapping(value = "0703/001")
    public String handle0703001() {
        return "redirect:/djpa/order";
    }

    @RequestMapping(value = "0704/001")
    public String handle0704001() {
        return "redirect:/djpa/order";
    }

}
