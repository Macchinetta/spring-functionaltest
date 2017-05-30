/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dam3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dam3")
public class DAM318Controller {

    @RequestMapping(value = "1801/001")
    public String handle1801001() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "1801/002")
    public String handle1801002() {
        return "redirect:/dam3/todo/list";
    }

}
