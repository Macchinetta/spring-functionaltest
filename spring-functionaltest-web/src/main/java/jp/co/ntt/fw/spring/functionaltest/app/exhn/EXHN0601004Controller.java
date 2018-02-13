/*
 * Copyright 2014-2017 NTT Corporation.
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
 *
 */
package jp.co.ntt.fw.spring.functionaltest.app.exhn;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Employee;
import jp.co.ntt.fw.spring.functionaltest.domain.service.exhn.EmployeeService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EXHN0601004Controller {

    @Inject
    EmployeeService employeeService;

    @Inject
    EmployeeHelper employeeHelper;

    @ModelAttribute
    public EmployeeForm setUpForm() {
        EmployeeForm employeeForm = new EmployeeForm();
        return employeeForm;
    }

    @RequestMapping(params = "update")
    public String employeeUpdate(EmployeeForm form) {

        Employee inputEmployee = employeeHelper.convertToEntity(form);
        employeeService.updateEmployee(inputEmployee);

        return "exhn/employeeEdit";
    }

    @RequestMapping(params = "backToIndex")
    public String backToIndex() {
        return "exhn/index";
    }
}
