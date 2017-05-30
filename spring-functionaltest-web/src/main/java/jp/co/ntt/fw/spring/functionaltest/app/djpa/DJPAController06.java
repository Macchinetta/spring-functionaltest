/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.djpa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("djpa")
public class DJPAController06 {

    @RequestMapping(value = "0601/001")
    public String handle0601001() {
        return "redirect:/djpa/book/list";
    }

    @RequestMapping(value = "0602/001")
    public String handle0602001() {
        return "redirect:/djpa/book/pgList";
    }

}
