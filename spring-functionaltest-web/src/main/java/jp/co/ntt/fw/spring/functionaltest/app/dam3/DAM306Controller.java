/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dam3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dam3")
public class DAM306Controller {

    @RequestMapping(value = "0601/002")
    public String handle0601002() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0601/003")
    public String handle0601003() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0602/001")
    public String handle0602001() {
        return "redirect:/dam3/todo/list";
    }

    @RequestMapping(value = "0603/001")
    public String handle0603001() {
        return "redirect:/dam3/todo/list";
    }

}
