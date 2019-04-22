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

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("vldt/0305/001")
public class VLDT0305001Controller {

    @ModelAttribute
    public DefineMessageUsingValidatedValueByValidationMessagesForm setUpForm() {
        return new DefineMessageUsingValidatedValueByValidationMessagesForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handle() {
        return "vldt/defineMessageUsingValidatedValueByValidationMessagesView";
    }

    @RequestMapping(method = RequestMethod.POST, params = "validate")
    public String handleValidate(
            @Validated DefineMessageUsingValidatedValueByValidationMessagesForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "vldt/defineMessageUsingValidatedValueByValidationMessagesView";
        }
        return "redirect:/vldt/0305/001";
    }
}
