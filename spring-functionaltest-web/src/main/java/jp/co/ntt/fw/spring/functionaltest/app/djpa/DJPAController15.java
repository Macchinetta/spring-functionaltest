/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.djpa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("djpa")
public class DJPAController15 {

    @RequestMapping(value = "1501/001")
    public String handle1501001() {
        return "redirect:/djpa/order";
    }

    @RequestMapping(value = "1502/001")
    public String handle1502001() {
        return "redirect:/djpa/order";
    }

}
