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
package jp.co.ntt.fw.spring.functionaltest.app.exhn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Employee;
import jp.co.ntt.fw.spring.functionaltest.domain.service.exhn.EmployeeService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.exhn.ThrowErrorService;

@Controller
@RequestMapping("jsp")
public class EXHN_JSP_03Controller {

    private static final Logger logger = LoggerFactory.getLogger(
            EXHN_JSP_03Controller.class);

    @Value("${app.base.web.path}")
    private String baseWeb;

    @Inject
    EmployeeBeanMapper beanMapper;

    @Inject
    EmployeeService employeeService;

    @Inject
    EmployeeHelper employeeHelper;

    @Inject
    ThrowErrorService throwErrorService;

    @ModelAttribute
    public EmployeeForm setUpForm() {
        EmployeeForm employeeForm = new EmployeeForm();
        return employeeForm;
    }

    @GetMapping(value = "0301/001")
    public String handle0301001(EmployeeForm form, Model model) {
        employeeHelper.convertToForm(form);
        model.addAttribute("testNumber", "0301/001");

        return "jsp/exhn/employeeEdit";
    }

    @GetMapping(value = "0302/001/001")
    public String handle0301002001(EmployeeForm form, Model model) {
        employeeHelper.convertToForm(form);
        model.addAttribute("testNumber", "0302/001/001");

        return "jsp/exhn/employeeEdit";
    }

    @GetMapping(value = "0302/001/002")
    public String handle0301002002(EmployeeForm form, Model model) {
        employeeHelper.convertToForm(form);
        model.addAttribute("testNumber", "0302/001/002");

        return "jsp/exhn/employeeEdit";
    }

    @PostMapping(value = "0301/001", params = "update")
    public String employeeUpdate(@Validated EmployeeForm form,
            BindingResult result, Model model) {
        model.addAttribute("testNumber", "0301/001");

        if (result.hasErrors()) {
            return "jsp/exhn/employeeEdit";
        }

        Employee inputEmployee = employeeHelper.convertToEntity(form);

        try {
            employeeService.updateEmployee(inputEmployee);
        } catch (BusinessException e) {
            model.addAttribute(ResultMessages.danger().add("e.sf.exhn.8000"));
        }

        model.addAttribute(ResultMessages.success().add("e.sf.exhn.8001"));

        return "jsp/exhn/employeeEdit";
    }

    @PostMapping(value = "0302/001/001", params = "update")
    public String employeeUpdateFirst(@Validated EmployeeForm form,
            BindingResult result, Model model) {
        model.addAttribute("testNumber", "0302/001/001");

        if (result.hasErrors()) {
            return "jsp/exhn/employeeEdit";
        }

        Employee inputEmployee = employeeHelper.convertToEntity(form);

        model.addAttribute(ResultMessages.danger().add("e.sf.exhn.8010"));
        employeeService.updateEmployee(inputEmployee);

        return "jsp/exhn/employeeEdit";
    }

    @PostMapping(value = "0302/001/002", params = "update")
    public String employeeUpdateSecond(@Validated EmployeeForm form,
            BindingResult result, Model model) {
        model.addAttribute("testNumber", "0302/001/002");

        if (result.hasErrors()) {
            return "jsp/exhn/employeeEdit";
        }

        Employee inputEmployee = employeeHelper.convertToEntity(form);

        model.addAttribute(ResultMessages.danger().add("e.sf.exhn.8011"));
        employeeService.updateEmployee(inputEmployee);

        return "jsp/exhn/employeeEdit";
    }

    @GetMapping(value = "0302/002")
    public String handle0302002(Model model) {

        throwErrorService.throwAssertionError();

        model.addAttribute("baseWeb", this.baseWeb);
        return "index";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        logger.warn("Exception Occured");
        return "common/error/systemError";
    }

    @ExceptionHandler(ServletException.class)
    public void handleServletException(
            ServletException e) throws ServletException {
        logger.warn("ServletException Occured");
        throw e;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ModelAndView handdleBusinessException(BusinessException e) {
        ExtendedModelMap modelMap = new ExtendedModelMap();

        Employee employee = employeeService.findEmployee(1);

        EmployeeForm form = beanMapper.map(employee);
        modelMap.addAttribute(form);

        modelMap.addAttribute(ResultMessages.danger().add("e.sf.exhn.8020"));
        return new ModelAndView("jsp/exhn/employeeEdit", modelMap);
    }

    @PostMapping(value = { "/", "0301/001", "0302/001/001",
            "0302/001/002" }, params = "backToIndex")
    public String backToIndex(Model model) {
        model.addAttribute("baseWeb", this.baseWeb);
        return "index";
    }

}
