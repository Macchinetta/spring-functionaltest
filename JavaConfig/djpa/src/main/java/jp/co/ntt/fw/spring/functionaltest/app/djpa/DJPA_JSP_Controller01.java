/*
 * Copyright(c) 2024 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.app.djpa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("jsp")
public class DJPA_JSP_Controller01 {

    @ModelAttribute
    public BookListForm setUpForm() {
        return new BookListForm();
    }

    @GetMapping(value = "0101/001")
    public String handle0101001() {
        return "redirect:/jsp/book/list";
    }

    @GetMapping(value = "0102/001")
    public String handle0102001() {
        return "redirect:/jsp/book/list";
    }

    @GetMapping(value = "0102/002")
    public String handle0102002() {
        return "redirect:/jsp/book/list";
    }

    @GetMapping(value = "0103/001")
    public String handle0103001() {
        return "redirect:/jsp/book/list";
    }

    @GetMapping(value = "0103/002")
    public String handle0103002() {
        return "redirect:/jsp/book/list";
    }

    @GetMapping(value = "0103/003")
    public String handle0103003() {
        return "redirect:/jsp/book/list";
    }

    @GetMapping(value = "0103/004")
    public String handle0103004() {
        return "redirect:/jsp/book/list";
    }

    @GetMapping(value = "0103/005")
    public String handle0103005() {
        return "redirect:/jsp/book/list";
    }

    @GetMapping(value = "0105/001")
    public String handle0105001() {
        return "redirect:/jsp/book/list";
    }
}
