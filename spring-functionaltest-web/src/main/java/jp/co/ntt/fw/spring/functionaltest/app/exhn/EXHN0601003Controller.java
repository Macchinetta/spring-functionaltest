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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Employee;
import jp.co.ntt.fw.spring.functionaltest.domain.service.exhn.EmployeeService;

@Controller
@RequestMapping("exhn")
public class EXHN0601003Controller {

    @Inject
    EmployeeService employeeService;

    @Inject
    EmployeeHelper employeeHelper;

    @ModelAttribute
    public EmployeeForm setUpForm() {
        EmployeeForm employeeForm = new EmployeeForm();
        return employeeForm;
    }

    @RequestMapping(value = "0601/003", method = RequestMethod.GET)
    public String handle0601003(EmployeeForm form, Model model) {
        employeeHelper.convertToForm(form);
        model.addAttribute("testNumber", "0601/003");

        return "exhn/employeeEdit";
    }

    @RequestMapping(value = "0601/003", params = "update")
    public String employeeUpdateCustomSystemExceptionResolver(EmployeeForm form) {

        Employee inputEmployee = employeeHelper.convertToEntity(form);
        employeeService.updateEmployee(inputEmployee);

        return "exhn/employeeEdit";
    }

    @RequestMapping(value = "0601/003", params = "backToIndex", method = RequestMethod.POST)
    public String backToIndex() {
        return "exhn/index";
    }
}
