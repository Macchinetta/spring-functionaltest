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
public class DAM321Controller {

    @RequestMapping(value = "2101/001")
    public String handle2101001() {
        return "redirect:/dam3mb3/ordermb3/list";
    }

    @RequestMapping(value = "2102/001")
    public String handle2102001() {
        return "redirect:/dam3mb3/ordermb3/list";
    }

    @RequestMapping(value = "2102/002")
    public String handle2102002() {
        return "redirect:/dam3mb3/ordermb3/list";
    }
}
