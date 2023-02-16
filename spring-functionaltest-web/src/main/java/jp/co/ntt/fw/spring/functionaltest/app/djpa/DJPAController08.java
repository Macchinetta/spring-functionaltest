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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("djpa")
public class DJPAController08 {

    @GetMapping(value = "0801/001")
    public String handle0801001() {
        return "redirect:/djpa/order";
    }

    @GetMapping(value = "0802/001")
    public String handle0802001() {
        return "redirect:/djpa/order";
    }

    @GetMapping(value = "0803/001")
    public String handle0803001() {
        return "redirect:/djpa/order";
    }

    @GetMapping(value = "0804/001")
    public String handle0804001() {
        return "redirect:/djpa/order";
    }

    @GetMapping(value = "0804/002")
    public String handle0804002() {
        return "redirect:/djpa/order";
    }

    @GetMapping(value = "0804/003")
    public String handle0804003() {
        return "redirect:/djpa/order";
    }

}
