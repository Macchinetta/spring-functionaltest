/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dam3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dam3")
public class DAM306Controller {

    @RequestMapping(value = "0601/002")
    public String handle0601002() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0601/003")
    public String handle0601003() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0602/001")
    public String handle0602001() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0603/001")
    public String handle0603001() {
        return "redirect:/dam3/todo/list";
    }

}
