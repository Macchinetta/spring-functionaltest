/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.djpa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("djpa")
public class DJPAController03 {

    @RequestMapping(value = "0301/001")
    public String handle0301001() {
        return "redirect:/djpa/delivery/order";
    }

    @RequestMapping(value = "0301/002")
    public String handle0301002() {
        return "redirect:/djpa/delivery/order";
    }

    @RequestMapping(value = "0302/001")
    public String handle0302001() {
        return "redirect:/djpa/delivery/order";
    }

    @RequestMapping(value = "0303/001")
    public String handle0303001() {
        return "redirect:/djpa/delivery/order";
    }

    @RequestMapping(value = "0304/001")
    public String handle0304001() {
        return "redirect:/djpa/book/pgList";
    }

    @RequestMapping(value = "0304/002")
    public String handle0304002() {
        return "redirect:/djpa/book/pgList";
    }
}
