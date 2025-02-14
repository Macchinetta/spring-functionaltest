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
package jp.co.ntt.fw.spring.functionaltest.app.athr;

import java.security.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ATHR_Thymeleaf_06Controller {
    @GetMapping("0601/001")
    public String handle0601001() {
        return "thymeleaf/athr/loginForAccsessPolicy";
    }

    @RequestMapping(value = "0601/001/afterLogin", method = {RequestMethod.GET, RequestMethod.POST})
    public String handle0601001(Principal principal, Model model) {
        Authentication authentication = (Authentication) principal;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("userEmail", userDetails.getUsername() + "@example.com");
        return "thymeleaf/athr/showForAccessPolicy";
    }

    @GetMapping({"0601/001/accounts", "0601/001/manager", "0601/001/all"})
    public String handleAccessPolicy() {
        return "thymeleaf/athr/showAccessPolicyAccessAllowedPage";
    }

    @GetMapping("0601/002")
    public String handle0602001() {
        return "thymeleaf/athr/loginForAccsessPolicyIpAddressAllow";
    }

    @RequestMapping(value = "0601/002/afterLogin", method = {RequestMethod.GET, RequestMethod.POST})
    public String handle0601002(Principal principal, Model model) {
        Authentication authentication = (Authentication) principal;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("userEmail", userDetails.getUsername() + "@example.com");
        return "thymeleaf/athr/showForAccessPolicyIpAddressAllow";
    }

    @GetMapping({"0601/002/manager", "0601/002/configurations", "0601/002/admin",
            "athr/0601/002/all"})
    public String handleAccessPolicyIpAddressAllow() {
        return "thymeleaf/athr/showAccessPolicyAccessIpAddressAllowedPage";
    }

    @GetMapping("0601/003")
    public String handle0601003() {
        return "thymeleaf/athr/loginForAccsessPolicyIpAddressDeny";
    }

    @RequestMapping(value = "0601/003/afterLogin", method = {RequestMethod.GET, RequestMethod.POST})
    public String handle0612003(Principal principal, Model model) {
        Authentication authentication = (Authentication) principal;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("userEmail", userDetails.getUsername() + "@example.com");
        return "thymeleaf/athr/showForAccessPolicyIpAddressDeny";
    }

    @GetMapping({"0601/003/accounts", "0601/003/configurations", "0601/003/admin",
            "athr/0601/003/all"})
    public String handleAccessPolicyIpAddressDeny() {
        return "thymeleaf/athr/showAccessPolicyAccessIpAddressDeniedPage";
    }

    @GetMapping("0601/004")
    public String handle0601004() {
        return "thymeleaf/athr/loginForAccsessPolicyDenyAll";
    }

    @RequestMapping(value = "0601/004/afterLogin", method = {RequestMethod.GET, RequestMethod.POST})
    public String handle0601004(Principal principal, Model model) {
        Authentication authentication = (Authentication) principal;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("userEmail", userDetails.getUsername() + "@example.com");
        return "thymeleaf/athr/showForAccessPolicyDenyAll";
    }

    @GetMapping({"0601/004/accounts", "0601/004/admin", "0601/004/all"})
    public String handleAccessPolicyDenyAll() {
        return "thymeleaf/athr/showAccessPolicyAccessDenyAllPage";
    }

    @GetMapping({"0601/019", "0601/020", "0601/021", "0601/022"})
    public String handle0601019loginForPathVariable() {
        return "thymeleaf/athr/loginForPathVariable";
    }

    @GetMapping({"0601/{testNo}/account/{userName}", "0601/{testNo}/account/{userName}.*"})
    public String handle0601019afterLogin(@PathVariable("testNo") String testNo,
            @PathVariable("userName") String userName, Model model) {
        model.addAttribute("testNo", testNo);
        model.addAttribute("username", userName);
        return "thymeleaf/athr/showForPathVariableDsp";
    }

}
