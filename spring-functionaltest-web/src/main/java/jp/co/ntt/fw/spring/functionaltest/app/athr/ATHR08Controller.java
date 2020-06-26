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
package jp.co.ntt.fw.spring.functionaltest.app.athr;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ATHR08Controller {
    @RequestMapping("0802/001")
    public String handle0801001() {
        return "athr/loginForCustomizedAuthorizeErrorForUnAuthenticated";
    }

    @RequestMapping(value = { "0802/001/afterLogin", "0802/001/api" })
    public String handle0801001afterLogin(Principal principal, Model model) {
        Authentication authentication = (Authentication) principal;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("userEmail", userDetails.getUsername()
                + "@example.com");
        return "athr/showForCustomizedAutnenticateErrorForUnAuthenticated";
    }

}
