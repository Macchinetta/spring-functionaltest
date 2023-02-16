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
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.groups.Default;
import jp.co.ntt.fw.spring.functionaltest.app.vldt.AuthorizedValidationForm.Anonymous;

@Controller
@RequestMapping("vldt/0103/003")
public class VLDT0103003Controller {

    @Inject
    @Named("validator")
    SmartValidator smartValidator;

    @ModelAttribute
    public AuthorizedValidationForm setUpForm() {
        return new AuthorizedValidationForm();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class,
                new StringTrimmerEditor(true));
    }

    @GetMapping
    public String handle() {
        return "vldt/authorizedValidationView";
    }

    @PostMapping(params = "LoginSuccess")
    public String handleLoginSuccess() {
        return "vldt/authorizedValidationView";
    }

    @PostMapping(params = "validate")
    public String handleValidate(AuthorizedValidationForm form,
            BindingResult result, Authentication authentication) {

        smartValidator.validate(form, result, getUserValidationGroup(
                authentication));
        if (result.hasErrors()) {
            return "vldt/authorizedValidationView";
        }
        return "redirect:/vldt/0103/003";
    }

    private Class<?> getUserValidationGroup(Authentication authentication) {

        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication
                    .getPrincipal();
            if (userDetails != null) {
                return Default.class;
            }
        }
        return Anonymous.class;
    }
}
