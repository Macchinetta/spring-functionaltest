/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dam3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dam3")
public class DAM302Controller {

    @RequestMapping(value = "0201/001")
    public String handle0201001() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0202/001")
    public String handle0202001() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0204/001")
    public String handle0204001() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0205/001")
    public String handle0205001() {
        return "redirect:/dam3/todo/list";
    }

}
