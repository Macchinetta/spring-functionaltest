/*
 * Copyright(c) 2014 NTT Corporation.
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
