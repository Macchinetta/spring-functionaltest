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
package jp.co.ntt.fw.spring.functionaltest.app.cspr;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("thymeleaf/0201/009")
@Controller
public class CSPR0201009Controller {

    @GetMapping
    public String handle(NoThActionValueForm form) {
        return "thymeleaf/cspr/userRegister0201009";
    }

    @PostMapping(params = "confirm")
    public String confirm(@Validated NoThActionValueForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "thymeleaf/cspr/userRegister0201009";
        }
        return "thymeleaf/cspr/userConfirm0201009";
    }

    @PostMapping
    public String create(@Validated NoThActionValueForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "thymeleaf/cspr/userRegister0201009";
        }
        return "redirect:/thymeleaf/0201/009?complete";
    }

    @GetMapping(params = "complete")
    public String complete() {
        return "thymeleaf/cspr/userComplete0201009";
    }

    @PostMapping(params = "returnToRegister0201009")
    public String backToRegisterUseStandardFormToPost(NoThActionValueForm form) {
        return "thymeleaf/cspr/userRegister0201009";
    }
}
