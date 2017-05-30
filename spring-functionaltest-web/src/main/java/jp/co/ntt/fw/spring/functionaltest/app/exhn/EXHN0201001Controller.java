/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.exhn;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Employee;
import jp.co.ntt.fw.spring.functionaltest.domain.service.exhn.EmployeeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
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

    @RequestMapping(method = RequestMethod.GET)
    public String handle0201001(EmployeeForm form, Model model) {
        employeeHelper.convertToForm(form);
        model.addAttribute("testNumber", "0201/001");

        return "exhn/employeeEdit";
    }

    @RequestMapping(params = "update", method = RequestMethod.POST)
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

    @RequestMapping(params = "complete")
    public String employeeComplete() {
        return "exhn/employeeComplete";
    }

    @RequestMapping(params = "backToIndex", method = RequestMethod.POST)
    public String backToIndex() {
        return "exhn/index";
    }

}
