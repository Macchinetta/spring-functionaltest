/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dam3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dam3")
public class DAM309Controller {

    @RequestMapping(value = "0901/001")
    public String handle0901001() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0902/001")
    public String handle0902001() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0904/001")
    public String handle0904001() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0906/001")
    public String handle0906001() {
        return "redirect:/dam3/todo/list";
    }

}
