/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dam3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dam3")
public class DAM301Controller {

    @RequestMapping(value = "0101/001")
    public String handle0101001() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0102/001")
    public String handle0102001() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0102/002")
    public String handle0102002() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0103/001")
    public String handle0103001() {
        return "redirect:/dam3/book/list";
    }

    @RequestMapping(value = "0104/001")
    public String handle0104001() {
        return "redirect:/dam3/book/list";
    }

    @RequestMapping(value = "0105/001")
    public String handle0105001() {
        return "redirect:/dam3/book/list";
    }

    @RequestMapping(value = "0106/001")
    public String handle0106001() {
        return "redirect:/dam3/book/list";
    }

    @RequestMapping(value = "0107/001")
    public String handle0107001() {
        return "redirect:/dam3/book/list";
    }

    @RequestMapping(value = "0107/002")
    public String handle0107002() {
        return "redirect:/dam3/book/list";
    }

    @RequestMapping(value = "0108/001")
    public String handle0108001() {
        return "redirect:/dam3/book/list";
    }

    @RequestMapping(value = "0108/002")
    public String handle0108002() {
        return "redirect:/dam3/book/list";
    }

}
