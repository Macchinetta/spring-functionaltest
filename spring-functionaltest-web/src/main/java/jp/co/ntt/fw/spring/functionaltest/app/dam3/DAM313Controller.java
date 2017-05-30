/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dam3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dam3")
public class DAM313Controller {

    @RequestMapping(value = "1301/001")
    public String handle1301001() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "1304/001")
    public String handle1304001() {
        return "redirect:/dam3/show/date";
    }

}
