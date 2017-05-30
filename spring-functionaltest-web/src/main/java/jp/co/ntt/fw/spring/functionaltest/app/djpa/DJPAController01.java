/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.djpa;

import jp.co.ntt.fw.spring.functionaltest.app.dam3.BookListForm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("djpa")
public class DJPAController01 {

    @ModelAttribute
    public BookListForm setUpForm() {
        return new BookListForm();
    }

    @RequestMapping(value = "0101/001")
    public String handle0101001() {
        return "redirect:/djpa/book/list";
    }

    @RequestMapping(value = "0102/001")
    public String handle0102001() {
        return "redirect:/djpa/book/list";
    }

    @RequestMapping(value = "0102/002")
    public String handle0102002() {
        return "redirect:/djpa/book/list";
    }

    @RequestMapping(value = "0103/001")
    public String handle0103001() {
        return "redirect:/djpa/book/list";
    }

    @RequestMapping(value = "0103/002")
    public String handle0103002() {
        return "redirect:/djpa/book/list";
    }

    @RequestMapping(value = "0103/003")
    public String handle0103003() {
        return "redirect:/djpa/book/list";
    }
}
