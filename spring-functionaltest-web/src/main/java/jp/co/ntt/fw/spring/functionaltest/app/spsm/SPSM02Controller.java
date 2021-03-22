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
package jp.co.ntt.fw.spring.functionaltest.app.spsm;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes(value = "sessionForm")
@Controller
public class SPSM02Controller {

    @ModelAttribute(value = "sessionForm")
    public SessionForm setUpForm() {
        return new SessionForm();
    }

    @RequestMapping("0201/001/login")
    public String handle0102001Login(SessionForm form) {
        return "spsm/loginForDisableUrlRewrite";
    }

    @RequestMapping(value = "0201/001", params = "loginSuccess")
    public String handle0102001LoginSuccess(
            @AuthenticationPrincipal User userDetails, Model model,
            SessionForm form) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        model.addAttribute("testNo", "0201/001");
        model.addAttribute("sessionform", form.getOeratorName());
        return "spsm/topForDeafalut";
    }

    @RequestMapping("0201/001/send")
    public String handle0102001send(@AuthenticationPrincipal User userDetails,
            Model model) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        model.addAttribute("testNo", "0201/001");

        return "spsm/topForDeafalut";
    }

    @RequestMapping("0202/001/login")
    public String handle0202001Login(Model model, SessionForm form) {
        model.addAttribute("testNo", "0202/001");
        form.setOeratorName("This is Session");
        return "spsm/loginForMigrateSession";
    }

    @RequestMapping(value = "0202/001", params = "loginSuccess")
    public String handle002001LoginSuccess(
            @AuthenticationPrincipal User userDetails, Model model,
            SessionForm form) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        model.addAttribute("testNo", "0202/001");
        model.addAttribute("sessionform", form.getOeratorName());
        return "spsm/topForDeafalut";
    }

    @RequestMapping("0201/002/send")
    public String handle0102002send(@AuthenticationPrincipal User userDetails,
            Model model) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        model.addAttribute("testNo", "0201/002");

        return "spsm/topForDeafalut";
    }

    @RequestMapping("0202/002/login")
    public String handle0202002Login(Model model, SessionForm form) {
        model.addAttribute("testNo", "0202/002");
        form.setOeratorName("This is Session");
        return "spsm/loginForMigrateSession";
    }

    @RequestMapping(value = "0202/002", params = "loginSuccess")
    public String handle002002LoginSuccess(
            @AuthenticationPrincipal User userDetails, Model model,
            SessionForm form) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        model.addAttribute("testNo", "0202/002");
        model.addAttribute("sessionform", form.getOeratorName());
        return "spsm/topForDeafalut";
    }

}
