/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.djpa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("djpa")
public class DJPAController05 {

    @RequestMapping(value = "0501/001")
    public String handle0501001() {
        return "redirect:/djpa/delivery/order";
    }

}
