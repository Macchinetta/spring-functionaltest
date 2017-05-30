/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dam3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dam3")
public class DAM307Controller {

    @RequestMapping(value = "0701/001")
    public String handle0701001() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0702/001")
    public String handle0702001() {
        return "redirect:/dam3/todo/list";
    }

}
