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
package jp.co.ntt.fw.spring.functionaltest.app.ajax;

import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.terasoluna.gfw.common.exception.BusinessException;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.bean.ErrorResults;

@RequestMapping("jsp")
@Controller
public class AJAX_JSP_03Controller {

    @Inject
    PersonalComputerHelper personalComputerHelper;

    @ModelAttribute
    public PersonalComputerForm setUpForm() {
        return new PersonalComputerForm();
    }

    @GetMapping(value = "0301/001/001")
    public String handle0301001001(PersonalComputerForm form, Model model) {
        personalComputerHelper.setPersonalComputer(1, form);

        model.addAttribute("path", "0301/001/001/edit");
        model.addAttribute("method", "ajax.editPersonalComputer");

        return "jsp/ajax/personalComputerEdit";
    }

    @GetMapping(value = "0301/001/002")
    public String handle0301001002(PersonalComputerForm form, Model model) {
        personalComputerHelper.setPersonalComputer(1, form);

        model.addAttribute("path", "0301/001/002/edit");
        model.addAttribute("method", "ajax.editPersonalComputer");

        return "jsp/ajax/personalComputerEdit";
    }

    @GetMapping(value = "0302/001")
    public String handle0302001(PersonalComputerForm form, Model model) {
        personalComputerHelper.setPersonalComputer(1, form);

        model.addAttribute("path", "0302/001/edit");
        model.addAttribute("method", "ajax.editPersonalComputer");

        return "jsp/ajax/personalComputerEdit";
    }

    @PostMapping(value = "0301/001/001/edit")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PersonalComputerResult editFirst(
            @Validated PersonalComputerForm form, Locale locale) {

        return personalComputerHelper.updateAndBindPersonalComputerResult(form,
                locale);
    }

    @PostMapping(value = "0301/001/002/edit")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PersonalComputerResult editSecond(
            @Validated PersonalComputerForm form, Locale locale) {

        return personalComputerHelper.updateAndBindPersonalComputerResult(form,
                locale);
    }

    @GetMapping(value = "0301", params = "retrunToIndex")
    public String retrunToIndex() {

        return "jsp/ajax/index";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResults handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e, Locale locale) {

        return personalComputerHelper.setErrorResults(e.getBindingResult()
                .getFieldErrors(), locale);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResults handleHttpBusinessException(BusinessException e,
            Locale locale) {

        return personalComputerHelper.setBusinessExceptionErrorResults(e,
                locale);
    }

}
