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
package jp.co.ntt.fw.spring.functionaltest.app.athr;

import java.security.Principal;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ATHR02Controller {

    @RequestMapping("0201/001")
    public String handle0201001loginForStaff() {
        return "athr/loginForStaff";
    }

    @RequestMapping(value = "0201/001/afterLogin")
    public String handle0201001afterLogin(Principal principal, Model model) {
        Authentication authentication = (Authentication) principal;
        UserDetails userDetails = null;
        if (authentication != null) {
            userDetails = (UserDetails) authentication.getPrincipal();
        }
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
            model.addAttribute("userEmail", userDetails.getUsername()
                    + "@example.com");
        }
        return "athr/showForStaffInfoDsp";
    }

    @RequestMapping("0201/002")
    public String handle0201002loginForStaff() {
        return "athr/loginForJSPTagRoleAdmin";
    }

    @RequestMapping(value = "0201/002/afterLogin")
    public String handle0201002afterLogin() {
        return "athr/showForJSPTagRoleAdminDsp";
    }

    @RequestMapping("0201/002/restrict")
    public String handle0201002restrict(
            @AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("username", userDetails.getUsername());
        Collection<?> authorities = userDetails.getAuthorities();
        model.addAttribute("userRoles", authorities);
        return "athr/showForStaffRoleInfoDsp";
    }

    @RequestMapping("0201/004/showForAccessSuccessfully")
    public String handle0201004showForAccessSuccessfully() {
        return "athr/showForAccessSuccessfully";
    }

}
