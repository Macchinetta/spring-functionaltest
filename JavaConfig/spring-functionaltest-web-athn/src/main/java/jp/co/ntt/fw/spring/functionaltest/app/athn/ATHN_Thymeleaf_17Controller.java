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
package jp.co.ntt.fw.spring.functionaltest.app.athn;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ATHN_Thymeleaf_17Controller {

    @ModelAttribute
    public LoginForm setupForm() {
        return new LoginForm();
    }

    @GetMapping(value = "1701/001")
    public String handle0201001() {
        return "thymeleaf/athn/loginCustomer";
    }

    @GetMapping(value = "1701/001", params = "loginSuccess")
    public String handle1701001LoginSuccess() {
        return "thymeleaf/athn/showCustomerInfo";
    }

    @GetMapping(value = "1702/001")
    public String handle1702001() {
        return "thymeleaf/athn/loginCustomerCompanyId";
    }

    @GetMapping(value = "1702/001", params = "loginSuccess")
    public String handle1702001LoginSuccess() {
        return "thymeleaf/athn/showCustomerCompanyInfo";
    }

    @GetMapping(value = "1703/001")
    public String handle0102001(Model model, LoginForm form) {
        return "thymeleaf/athn/loginForBeanValidationAuthentication";
    }

    @PostMapping(value = "1703/001/login")
    public String handle1703001login(@Validated LoginForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return "thymeleaf/athn/loginForBeanValidationAuthentication";
        }
        return "forward:authenticate";
    }

    @GetMapping(value = "1703/001", params = "loginSuccess")
    public String handle0101LoginSuccess(
            @AuthenticationPrincipal User userDetails, Model model) {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("testNo", "1703/001");
        return "thymeleaf/athn/topForDefault";
    }

}
