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
package jp.co.ntt.fw.spring.functionaltest.app.athn;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ATHN17Controller {

    @ModelAttribute
    public LoginForm setupForm() {
        return new LoginForm();
    }

    @RequestMapping(value = "1701/001")
    public String handle0201001() {
        return "athn/loginCustomer";
    }

    @RequestMapping(value = "1701/001", params = "loginSuccess")
    public String handle1701001LoginSuccess() {
        return "athn/showCustomerInfo";
    }

    @RequestMapping(value = "1702/001")
    public String handle1702001() {
        return "athn/loginCustomerCompanyId";
    }

    @RequestMapping(value = "1702/001", params = "loginSuccess")
    public String handle1702001LoginSuccess() {
        return "athn/showCustomerCompanyInfo";
    }

    @RequestMapping(value = "1703/001")
    public String handle0102001(Model model, LoginForm form) {
        return "athn/loginForBeanValidationAuthentication";
    }

    @RequestMapping(value = "1703/001/login")
    public String handle1703001login(@Validated LoginForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return "athn/loginForBeanValidationAuthentication";
        }
        return "forward:authenticate";
    }

    @RequestMapping(value = "1703/001", params = "loginSuccess")
    public String handle0101LoginSuccess(
            @AuthenticationPrincipal User userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        model.addAttribute("testNo", "1703/001");
        return "athn/topForDeafalut";
    }

}
