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
package jp.co.ntt.fw.spring.functionaltest.app.exhn;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Employee;
import jp.co.ntt.fw.spring.functionaltest.domain.service.exhn.EmployeeService;

@Controller
@RequestMapping("exhn")
public class EXHN0201001Controller {

    @Inject
    EmployeeService employeeService;

    @Inject
    EmployeeHelper employeeHelper;

    @ModelAttribute
    public EmployeeForm setUpForm() {
        EmployeeForm employeeForm = new EmployeeForm();
        return employeeForm;
    }

    @RequestMapping(value = "0201/001", method = RequestMethod.GET)
    public String handle0201001(EmployeeForm form, Model model) {
        employeeHelper.convertToForm(form);
        model.addAttribute("testNumber", "0201/001");

        return "exhn/employeeEdit";
    }

    @RequestMapping(value = "0201/001", params = "update", method = RequestMethod.POST)
    public String employeeUpdateThrowBusinessException(
            @Validated EmployeeForm form, BindingResult result, Model model,
            RedirectAttributes redirectAttrs) {

        if (result.hasErrors()) {
            model.addAttribute("testNumber", "0201/001");
            return "exhn/employeeEdit";
        }

        Employee inputEmployee = employeeHelper.convertToEntity(form);
        employeeService.updateEmployee(inputEmployee);

        redirectAttrs.addFlashAttribute(form);

        return "redirect:/exhn/0201/001?complete";
    }

    @RequestMapping(value = "0201/001", params = "complete")
    public String employeeComplete() {
        return "exhn/employeeComplete";
    }

    @RequestMapping(value = "0201/001", params = "backToIndex", method = RequestMethod.POST)
    public String backToIndex() {
        return "exhn/index";
    }

}
