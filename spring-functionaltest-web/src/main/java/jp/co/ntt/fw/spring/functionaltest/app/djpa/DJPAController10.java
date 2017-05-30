/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.djpa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("djpa")
public class DJPAController10 {

    @RequestMapping(value = "1001/001")
    public String handle1001001() {
        return "redirect:/djpa/delivery/order";
    }

    @RequestMapping(value = "1001/002")
    public String handle1001002() {
        return "redirect:/djpa/delivery/order";
    }

    @RequestMapping(value = "1002/001")
    public String handle1002001() {
        return "redirect:/djpa/delivery/order";
    }

}
