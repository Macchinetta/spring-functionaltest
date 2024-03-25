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

@RequestMapping("jsp")
@Controller
public class CSPR_JSP_02Controller {

    @ModelAttribute
    public UserForm setUpForm() {
        return new UserForm();
    }

    @GetMapping(value = "0201/001")
    public String handle0201001() {
        return "jsp/cspr/userRegister";
    }

    @GetMapping(value = "0201/002")
    public String handle0201002() {
        return "jsp/cspr/userRegisterUseStandardFormToPost";
    }

    @GetMapping(value = "0201/003")
    public String handle0201003() {
        return "jsp/cspr/userRegisterUseStandardFormToGet";
    }

    @GetMapping(value = "0201/004")
    public String handle0201004() {
        return "jsp/cspr/userRegisterUseSpringFormToGet";
    }

    @GetMapping(value = "0201/006")
    public String handle0201006() {
        return "jsp/cspr/userRegister";
    }

    @GetMapping(value = "0201/007")
    public String handle0201007() {
        return "jsp/cspr/userRegister";
    }

    @GetMapping(value = "0201/008")
    public String handle0201008() {
        return "jsp/cspr/userRegisterNotUseDeniedHandler";
    }

    @PostMapping(params = "confirm")
    public String confirm(@Validated UserForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "jsp/cspr/userRegister";
        }
        return "jsp/cspr/userConfirm";
    }

    @PostMapping(params = "confirmUseStandardFormToPost")
    public String confirmUseStandardFormToPost(@Validated UserForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return "jsp/cspr/userRegisterUseStandardFormToPost";
        }
        return "jsp/cspr/userConfirmUseStandardFormToPost";
    }

    @GetMapping(params = "confirmUseStandardFormToGet")
    public String confirmUseStandardFormToGet(@Validated UserForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return "jsp/cspr/userRegisterUseStandardFormToGet";
        }
        return "jsp/cspr/userConfirmUseStandardFormToGet";
    }

    @GetMapping(params = "confirmUseSpringFormToGet")
    public String confirmUseSpringFormToGet(@Validated UserForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return "jsp/cspr/userRegisterUseSpringFormToGet";
        }
        return "jsp/cspr/userConfirmUseSpringFormToGet";
    }

    @PostMapping(value = "notUseDeniedHandler", params = "confirmNotUseDeniedHandler")
    public String confirmNotUseDeniedHandler(@Validated UserForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return "jsp/cspr/userRegisterNotUseDeniedHandler";
        }
        return "jsp/cspr/userConfirmNotUseDeniedHandler";
    }

    @PostMapping
    public String create(@Validated UserForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "jsp/cspr/userRegister";
        }
        return "redirect:/jsp?complete";
    }

    @GetMapping(params = "complete")
    public String complete() {

        return "jsp/cspr/userComplete";
    }

    @PostMapping(value = "notUseDeniedHandler")
    public String completeNouUseDeniedHandler() {

        return "jsp/cspr/userComplete";
    }

    @GetMapping(params = "return")
    public String backToIndex() {
        return "index";
    }

    @PostMapping(params = "returnToRegister")
    public String backToRegister(UserForm form) {
        return "jsp/cspr/userRegister";
    }

    @PostMapping(params = "returnToRegisterUseStandardFormToPost")
    public String backToRegisterUseStandardFormToPost(UserForm form) {
        return "jsp/cspr/userRegisterUseStandardFormToPost";
    }

    @PostMapping(params = "returnToRegisterUseStandardFormToGet")
    public String backToRegisterUseStandardFormToGet(UserForm form) {
        return "jsp/cspr/userRegisterUseStandardFormToGet";
    }

    @PostMapping(params = "returnToRegisterUseSpringFormToGet")
    public String backToRegisterUseSpringFormToGet(UserForm form) {
        return "jsp/cspr/userRegisterUseSpringFormToGet";
    }

    @PostMapping(params = "returnToRegisterNouUseDeniedHandler")
    public String backToRegisterNouUseDeniedHandler(UserForm form) {
        return "jsp/cspr/userRegisterNotUseDeniedHandler";
    }

}
