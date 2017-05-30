/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.djpa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("djpa")
public class DJPAController14 {

    @RequestMapping(value = "1401/001")
    public String handle1401001() {
        return "redirect:/djpa/book/pgList";
    }

    @RequestMapping(value = "1402/001")
    public String handle1402001() {
        return "redirect:/djpa/book/pgList";
    }

}
