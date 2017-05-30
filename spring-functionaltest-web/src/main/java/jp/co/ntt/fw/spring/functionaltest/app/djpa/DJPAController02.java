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
public class DJPAController02 {

    @ModelAttribute
    public BookListForm setUpForm() {
        return new BookListForm();
    }

    @RequestMapping(value = "0201/001")
    public String handle0201001() {
        return "redirect:/djpa/book/list";
    }

    @RequestMapping(value = "0201/002")
    public String handle0201002() {
        return "redirect:/djpa/book/pgList";
    }

    @RequestMapping(value = "0201/003")
    public String handle0201003() {
        return "redirect:/djpa/book/list";
    }

    @RequestMapping(value = "0201/004")
    public String handle0201004() {
        return "redirect:/djpa/book/list";
    }

    @RequestMapping(value = "0201/005")
    public String handle0201005() {
        return "redirect:/djpa/book/list";
    }

    @RequestMapping(value = "0201/006")
    public String handle0201006() {
        return "redirect:/djpa/book/list";
    }

    @RequestMapping(value = "0201/007")
    public String handle0201007() {
        return "redirect:/djpa/book/pgList";
    }

    @RequestMapping(value = "0201/008")
    public String handle0201008() {
        return "redirect:/djpa/book/list";
    }

    @RequestMapping(value = "0201/009")
    public String handle0201009() {
        return "redirect:/djpa/book/list";
    }

    @RequestMapping(value = "0201/010")
    public String handle0201010() {
        return "redirect:/djpa/book/list";
    }

    @RequestMapping(value = "0201/011")
    public String handle0201011() {
        return "redirect:/djpa/book/list";
    }

    @RequestMapping(value = "0201/012")
    public String handle0201012() {
        return "redirect:/djpa/book/list";
    }

    @RequestMapping(value = "0201/013")
    public String handle0201013() {
        return "redirect:/djpa/book/list";
    }

    @RequestMapping(value = "0202/001")
    public String handle0202001() {
        return "redirect:/djpa/book/pgList";
    }

    @RequestMapping(value = "0202/002")
    public String handle0202002() {
        return "redirect:/djpa/book/pgList";
    }
}
