/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dtac;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dtac")
public class DTAC01Controller {

    @RequestMapping(value = "0101/001")
    public String handle0101001() {
        return "redirect:/dtac/login";
    }

    @RequestMapping(value = "0101/002")
    public String handle0101002() {
        return "redirect:/dtac/login";
    }

    @RequestMapping(value = "0102/001")
    public String handle0102001() {
        return "redirect:/dtac/login";
    }

    @RequestMapping(value = "0102/002")
    public String handle0102002() {
        return "redirect:/dtac/select";
    }

    @RequestMapping(value = "0102/select/dataSourceOpen")
    public String selectDataSourceA() {
        return "redirect:/dtac/user/listOpen";
    }

    @RequestMapping(value = "0102/select/dataSourceClose")
    public String selectDataSourceB() {
        return "redirect:/dtac/user/listClose";
    }
}
