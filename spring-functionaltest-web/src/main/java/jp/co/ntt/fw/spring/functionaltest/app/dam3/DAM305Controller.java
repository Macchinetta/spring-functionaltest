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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dam3")
public class DAM305Controller {

    @GetMapping(value = "0501/001")
    public String handle0501001() {
        return "redirect:/dam3/todo/list";
    }

    @GetMapping(value = "0501/002")
    public String handle0501002() {
        return "redirect:/dam3/todo/list";
    }

    @GetMapping(value = "0502/001")
    public String handle0502001() {
        return "redirect:/dam3/todo/list";
    }

    @GetMapping(value = "0502/002")
    public String handle0502002() {
        return "redirect:/dam3/todo/list";
    }

    @GetMapping(value = "0503/001")
    public String handle0503001() {
        return "redirect:/dam3/todo/list";
    }

    @GetMapping(value = "0503/002")
    public String handle0503002() {
        return "redirect:/dam3/todo/list";
    }

    @GetMapping(value = "0504/001")
    public String handle0504001() {
        return "redirect:/dam3/todo/list";
    }

    @GetMapping(value = "0505/001")
    public String handle0505001() {
        return "redirect:/dam3/todo/list";
    }

    @GetMapping(value = "0506/001")
    public String handle0506001() {
        return "redirect:/dam3/todo/list";
    }

    @GetMapping(value = "0507/001")
    public String handle0507001() {
        return "redirect:/dam3/todo/list";
    }
}
