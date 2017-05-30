/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dam3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dam3")
public class DAM308Controller {

    @RequestMapping(value = "0801/001")
    public String handle0801001() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0802/001")
    public String handle0802001() {
        return "redirect:/dam3/todo/list";
    }

}
