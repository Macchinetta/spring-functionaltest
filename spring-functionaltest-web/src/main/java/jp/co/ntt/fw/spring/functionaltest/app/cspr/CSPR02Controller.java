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
package jp.co.ntt.fw.spring.functionaltest.app.cspr;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("cspr")
@Controller
public class CSPR02Controller {

    @ModelAttribute
    public UserForm setUpForm() {
        return new UserForm();
    }

    @RequestMapping(value = "0201/001")
    public String handle0201001() {
        return "cspr/userRegister";
    }

    @RequestMapping(value = "0201/002")
    public String handle0201002() {
        return "cspr/userRegisterUseStandardFormToPost";
    }

    @RequestMapping(value = "0201/003")
    public String handle0201003() {
        return "cspr/userRegisterUseStandardFormToGet";
    }

    @RequestMapping(value = "0201/004")
    public String handle0201004() {
        return "cspr/userRegisterUseSpringFormToGet";
    }

    @RequestMapping(value = "0201/006")
    public String handle0201006() {
        return "cspr/userRegister";
    }

    @RequestMapping(value = "0201/007")
    public String handle0201007() {
        return "cspr/userRegister";
    }

    @RequestMapping(value = "0201/008")
    public String handle0201008() {
        return "cspr/userRegisterNotUseDeniedHandler";
    }

    @RequestMapping(method = RequestMethod.POST, params = "confirm")
    public String confirm(@Validated UserForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "cspr/userRegister";
        }
        return "cspr/userConfirm";
    }

    @RequestMapping(method = RequestMethod.POST, params = "confirmUseStandardFormToPost")
    public String confirmUseStandardFormToPost(@Validated UserForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return "cspr/userRegisterUseStandardFormToPost";
        }
        return "cspr/userConfirm";
    }

    @RequestMapping(method = RequestMethod.GET, params = "confirmUseStandardFormToGet")
    public String confirmUseStandardFormToGet(@Validated UserForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return "cspr/userRegisterUseStandardFormToGet";
        }
        return "cspr/userConfirm";
    }

    @RequestMapping(method = RequestMethod.GET, params = "confirmUseSpringFormToGet")
    public String confirmUseSpringFormToGet(@Validated UserForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return "cspr/userRegisterUseSpringFormToGet";
        }
        return "cspr/userConfirm";
    }

    @RequestMapping(value = "notUseDeniedHandler", method = RequestMethod.POST, params = "confirmNotUseDeniedHandler")
    public String confirmNotUseDeniedHandler(@Validated UserForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return "cspr/userRegisterNotUseDeniedHandler";
        }
        return "cspr/userConfirmNotUseDeniedHandler";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Validated UserForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "cspr/userRegister";
        }
        return "redirect:/cspr?complete";
    }

    @RequestMapping(method = RequestMethod.GET, params = "complete")
    public String complete() {

        return "cspr/userComplete";
    }

    @RequestMapping(value = "notUseDeniedHandler", method = RequestMethod.POST)
    public String completeNouUseDeniedHandler() {

        return "cspr/userComplete";
    }

    @RequestMapping(params = "return")
    public String backToIndex() {
        return "cspr/index";
    }

    @RequestMapping(value = "notUseDeniedHandler", params = "return")
    public String backToIndexNouUseDeniedHandler() {

        return "cspr/index";
    }

    @RequestMapping(params = "returnToRegister")
    public String backToRegister(UserForm form) {
        return "cspr/userRegister";
    }

    @RequestMapping(value = "notUseDeniedHandler", params = "returnToRegisterNouUseDeniedHandler")
    public String backToRegisterNouUseDeniedHandler(UserForm form) {
        return "cspr/userRegisterNotUseDeniedHandler";
    }

}
