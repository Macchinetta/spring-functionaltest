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
package jp.co.ntt.fw.spring.functionaltest.app.djpa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("djpa")
public class DJPAController03 {

    @RequestMapping(value = "0301/001")
    public String handle0301001() {
        return "redirect:/djpa/delivery/order";
    }

    @RequestMapping(value = "0301/002")
    public String handle0301002() {
        return "redirect:/djpa/delivery/order";
    }

    @RequestMapping(value = "0302/001")
    public String handle0302001() {
        return "redirect:/djpa/delivery/order";
    }

    @RequestMapping(value = "0303/001")
    public String handle0303001() {
        return "redirect:/djpa/delivery/order";
    }

    @RequestMapping(value = "0304/001")
    public String handle0304001() {
        return "redirect:/djpa/book/pgList";
    }

    @RequestMapping(value = "0304/002")
    public String handle0304002() {
        return "redirect:/djpa/book/pgList";
    }
}
