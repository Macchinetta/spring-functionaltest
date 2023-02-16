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
package jp.co.ntt.fw.spring.functionaltest.app.dmly;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dmly")
public class DMLY02Controller {

    @GetMapping(value = "0201/001")
    public String handle0201001() {
        return "redirect:/dmly/deliveryorder/initlist";
    }

    @GetMapping(value = "0201/002")
    public String handle0201002() {
        return "redirect:/dmly/deliveryorder/initlist";
    }

    @GetMapping(value = "0201/003")
    public String handle0201003() {
        return "redirect:/dmly/deliveryorder/initlist";
    }

    @GetMapping(value = "0201/004")
    public String handle0201004() {
        return "redirect:/dmly/deliveryorder/initlist";
    }

    @GetMapping(value = "0201/005")
    public String handle0201005() {
        return "redirect:/dmly/deliveryorder/initlist";
    }

    @GetMapping(value = "0201/006")
    public String handle0201006() {
        return "redirect:/dmly/deliveryorder/initlist";
    }

    @GetMapping(value = "0201/007")
    public String handle0201007() {
        return "redirect:/dmly/deliveryorder/initlist";
    }

    @GetMapping(value = "0201/008")
    public String handle0201008() {
        return "redirect:/dmly/deliveryorder/initlist";
    }

}
