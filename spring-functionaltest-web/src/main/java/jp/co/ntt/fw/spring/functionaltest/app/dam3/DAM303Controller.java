/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dam3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dam3")
public class DAM303Controller {

    @RequestMapping(value = "0301/001")
    public String handle0301001() {
        return "redirect:/dam3/todo/list";
    }

}
