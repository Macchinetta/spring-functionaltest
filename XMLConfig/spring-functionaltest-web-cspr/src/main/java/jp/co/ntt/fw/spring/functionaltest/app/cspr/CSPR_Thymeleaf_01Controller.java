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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("thymeleafDisabledcspr")
@Controller
public class CSPR_Thymeleaf_01Controller {

    @ModelAttribute
    public UserForm setUpForm() {
        return new UserForm();
    }

    @GetMapping(value = "0101/001")
    public String handle0201007() {
        return "thymeleaf/cspr/userRegisterNoCsrf";
    }

    @PostMapping(params = "confirm")
    public String confirm(@Validated UserForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "thymeleaf/cspr/userRegisterNoCsrf";
        }
        return "thymeleaf/cspr/userConfirmNoCsrf";
    }

    @PostMapping
    public String create(@Validated UserForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "thymeleaf/cspr/userRegisterNoCsrf";
        }
        return "redirect:/thymeleafDisabledcspr?complete";
    }

    @GetMapping(params = "complete")
    public String complete() {

        return "thymeleaf/cspr/userComplete";
    }

    @PostMapping(params = "returnToRegisterNoCsrf")
    public String backToRegister(UserForm form) {
        return "thymeleaf/cspr/userRegisterNoCsrf";
    }
}
