/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.djpa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("djpa")
public class DJPAController04 {

    @RequestMapping(value = "0401/001")
    public String handle0401001() {
        return "redirect:/djpa/delivery/order";
    }

    @RequestMapping(value = "0402/001")
    public String handle0402001() {
        return "redirect:/djpa/delivery/order";
    }
}
