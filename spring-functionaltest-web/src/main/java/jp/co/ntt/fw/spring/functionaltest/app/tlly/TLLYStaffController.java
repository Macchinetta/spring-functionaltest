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
package jp.co.ntt.fw.spring.functionaltest.app.tlly;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("tlly/staff")
public class TLLYStaffController {

    @ModelAttribute
    public StaffForm setUpForm() {
        return new StaffForm();
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String create() {
        return "tlly/register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST, params = "register")
    public String registerRegister(StaffForm form, Model model) {
        model.addAttribute("staff", form);
        return "tlly/registerComplete";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST, params = "cancel")
    public String registerCancel() {
        return "tlly/register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST, params = "back")
    public String registerBack() {
        return "tlly/register";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String delete() {
        return "tlly/nottlly/delete";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST, params = "delete")
    public String deleteDelete(StaffForm form, Model model) {
        model.addAttribute("staff", form);
        return "tlly/nottlly/deleteComplete";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST, params = "cancel")
    public String deleteCancel() {
        return "tlly/nottlly/delete";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST, params = "back")
    public String deleteBack() {
        return "tlly/nottlly/delete";
    }

    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String update() {
        return "tlly/update";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST, params = "update")
    public String updateUpdate(StaffForm form, Model model) {
        model.addAttribute("staff", form);
        return "tlly/updateComplete";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST, params = "cancel")
    public String updateCancel() {
        return "tlly/update";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST, params = "back")
    public String updateBack() {
        return "tlly/update";
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search() {
        return "tlly/search";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST, params = "search")
    public String searchUpdate(StaffForm form, Model model) {
        model.addAttribute("staff", form);
        return "tlly/searchComplete";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST, params = "cancel")
    public String searchCancel() {
        return "tlly/search";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST, params = "back")
    public String searchBack() {
        return "tlly/search";
    }

}
