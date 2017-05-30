/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dam3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dam3")
public class DAM305Controller {

    @RequestMapping(value = "0501/001")
    public String handle0501001() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0501/002")
    public String handle0501002() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0502/001")
    public String handle0502001() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0502/002")
    public String handle0502002() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0503/001")
    public String handle0503001() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0503/002")
    public String handle0503002() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0504/001")
    public String handle0504001() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0505/001")
    public String handle0505001() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0506/001")
    public String handle0506001() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0507/001")
    public String handle0507001() {
        return "redirect:/dam3/todo/list";
    }
}
