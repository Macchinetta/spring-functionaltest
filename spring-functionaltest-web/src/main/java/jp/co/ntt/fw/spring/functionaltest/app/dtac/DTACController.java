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
package jp.co.ntt.fw.spring.functionaltest.app.dtac;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dtac")
public class DTACController {

    @GetMapping
    public String handleIndex() {
        return "dtac/index";
    }

    @GetMapping(value = "login")
    public String handleLognin() {
        return "dtac/login";
    }

    @GetMapping(value = "login", params = "loginSuccess")
    public String handleLogninSuccess(Principal principal) {
        return "redirect:/dtac/user/list";
    }

    @GetMapping(value = "select")
    public String handleSelect() {
        return "dtac/select";
    }

}
