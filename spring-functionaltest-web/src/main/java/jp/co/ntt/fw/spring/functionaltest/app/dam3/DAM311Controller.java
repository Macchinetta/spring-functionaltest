/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dam3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dam3")
public class DAM311Controller {

    @RequestMapping(value = "1101/001")
    public String handle1101001() {
        return "redirect:/dam3/todo/list";
    }

}
