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
package jp.co.ntt.fw.spring.functionaltest.app.thly;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("staff")
public class THLYStaffController {

    @ModelAttribute
    public StaffForm setUpForm() {
        return new StaffForm();
    }

    @GetMapping(value = "register")
    public String create() {
        return "thly/register";
    }

    @PostMapping(value = "register", params = "register")
    public String registerRegister(StaffForm form, Model model) {
        model.addAttribute("staff", form);
        return "thly/registerComplete";
    }

    @PostMapping(value = "register", params = "cancel")
    public String registerCancel() {
        return "thly/register";
    }

    @PostMapping(value = "register", params = "back")
    public String registerBack() {
        return "thly/register";
    }

    @GetMapping(value = "delete")
    public String delete() {
        return "thly/notthly/delete";
    }

    @PostMapping(value = "delete", params = "delete")
    public String deleteDelete(StaffForm form, Model model) {
        model.addAttribute("staff", form);
        return "thly/notthly/deleteComplete";
    }

    @PostMapping(value = "delete", params = "cancel")
    public String deleteCancel() {
        return "thly/notthly/delete";
    }

    @PostMapping(value = "delete", params = "back")
    public String deleteBack() {
        return "thly/notthly/delete";
    }

    @GetMapping(value = "update")
    public String update() {
        return "thly/update";
    }

    @PostMapping(value = "update", params = "update")
    public String updateUpdate(StaffForm form, Model model) {
        model.addAttribute("staff", form);
        return "thly/updateComplete";
    }

    @PostMapping(value = "update", params = "cancel")
    public String updateCancel() {
        return "thly/update";
    }

    @PostMapping(value = "update", params = "back")
    public String updateBack() {
        return "thly/update";
    }

    @GetMapping(value = "search")
    public String search() {
        return "thly/search";
    }

    @PostMapping(value = "search", params = "search")
    public String searchUpdate(StaffForm form, Model model) {
        model.addAttribute("staff", form);
        return "thly/searchComplete";
    }

    @PostMapping(value = "search", params = "cancel")
    public String searchCancel() {
        return "thly/search";
    }

    @PostMapping(value = "search", params = "back")
    public String searchBack() {
        return "thly/search";
    }

}
