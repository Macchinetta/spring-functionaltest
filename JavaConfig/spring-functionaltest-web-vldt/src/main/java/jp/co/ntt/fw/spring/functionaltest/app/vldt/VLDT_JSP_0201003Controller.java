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
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.inject.Inject;

@Controller
@RequestMapping("jsp/0201/003")
public class VLDT_JSP_0201003Controller {

    @Inject
    PasswordEqualsValidatorForMultiFieldHighlight passwordEqualsValidatorForMultiFieldHighlight;

    @ModelAttribute
    public UserForm setUpForm() {
        return new UserForm();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(passwordEqualsValidatorForMultiFieldHighlight);
    }

    @GetMapping
    public String handle() {
        return "jsp/vldt/correlationValidationBySpringValidatorViewForMultiFieldHighlight";
    }

    @PostMapping(params = "validate")
    public String handleValidate(@Validated UserForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "jsp/vldt/correlationValidationBySpringValidatorViewForMultiFieldHighlight";
        }
        return "redirect:/jsp/0201/003";
    }
}
