/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.djpa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("djpa")
public class DJPAController12 {

    @RequestMapping(value = "1201/001")
    public String handle1201001() {
        return "redirect:/djpa/delivery/order";
    }

    @RequestMapping(value = "1202/001")
    public String handle1202001() {
        return "redirect:/djpa/book/pgList";
    }

}
