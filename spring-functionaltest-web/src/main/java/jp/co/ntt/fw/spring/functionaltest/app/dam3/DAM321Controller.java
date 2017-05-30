/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dam3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dam3")
public class DAM321Controller {

    @RequestMapping(value = "2101/001")
    public String handle2101001() {
        return "redirect:/dam3mb3/ordermb3/list";
    }

    @RequestMapping(value = "2102/001")
    public String handle2102001() {
        return "redirect:/dam3mb3/ordermb3/list";
    }

    @RequestMapping(value = "2102/002")
    public String handle2102002() {
        return "redirect:/dam3mb3/ordermb3/list";
    }
}
