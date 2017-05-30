/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dam3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dam3")
public class DAM304Controller {

    @RequestMapping(value = "0401/001")
    public String handle0401001() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0401/002")
    public String handle0402001() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0402/001")
    public String handle0403001() {
        return "redirect:/dam3/todo/list";
    }

}
