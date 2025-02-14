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
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.bean.ErrorResults;

@RequestMapping("thymeleaf")
@Controller
public class AJAX_Thymeleaf_02Controller {

    @Inject
    PersonalComputerHelper personalComputerHelper;

    @ModelAttribute
    public PersonalComputerForm setUpForm() {
        return new PersonalComputerForm();
    }

    @GetMapping(value = "0201/001")
    public String handle0201001(PersonalComputerForm form, Model model) {

        personalComputerHelper.setPersonalComputer(1, form);

        model.addAttribute("path", "0201/001/edit");
        model.addAttribute("method", "ajax.editPersonalComputer");

        return "thymeleaf/ajax/personalComputerEdit";
    }

    @GetMapping(value = "0201/002")
    public String handle0201002(PersonalComputerForm form, Model model) {

        personalComputerHelper.setPersonalComputer(1, form);

        model.addAttribute("path", "0201/edit");
        model.addAttribute("method", "ajax.editPersonalComputerForJson");

        return "thymeleaf/ajax/personalComputerEdit";
    }

    @GetMapping(value = "0201/003")
    public String handle0201003(PersonalComputerForm form, Model model) {

        personalComputerHelper.setPersonalComputer(1, form);

        model.addAttribute("path", "0201/edit");
        model.addAttribute("method", "ajax.editPersonalComputerForJson");

        return "thymeleaf/ajax/personalComputerEdit";
    }

    @GetMapping(value = "0202/001")
    public String handle0202001(PersonalComputerForm form, Model model) {

        personalComputerHelper.setPersonalComputer(1, form);

        model.addAttribute("path", "0202/001/edit");
        model.addAttribute("method", "ajax.editPersonalComputerForJsonAndBidingResultHandle");

        return "thymeleaf/ajax/personalComputerEdit";
    }

    @GetMapping(value = "0202/002")
    public String handle0202002(PersonalComputerForm form, Model model) {

        personalComputerHelper.setPersonalComputer(1, form);

        model.addAttribute("path", "0202/002/edit");
        model.addAttribute("method", "ajax.editPersonalComputerForJsonAndBidingResultHandle");

        return "thymeleaf/ajax/personalComputerEdit";
    }

    @PostMapping(value = "0201/001/edit")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PersonalComputerResult edit(@Validated PersonalComputerForm form, Locale locale) {

        return personalComputerHelper.updateAndBindPersonalComputerResult(form, locale);
    }

    @PostMapping(value = "0201/edit")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PersonalComputerResult editForJson(@Validated @RequestBody PersonalComputerForm form,
            Locale locale) {

        return personalComputerHelper.updateAndBindPersonalComputerResult(form, locale);
    }

    @PostMapping(value = "0202/001/edit")
    @ResponseBody
    public PersonalComputerWithErrorResults editForJsonAndUseBindingResult(
            @Validated @RequestBody PersonalComputerForm form, BindingResult bresult,
            Locale locale) {

        if (bresult.hasErrors()) {
            return personalComputerHelper.setPersonalComputerErrorResults(bresult, locale);
        }

        return personalComputerHelper.updateAndBindPersonalComputerWithErrorResults(form, locale);
    }

    @PostMapping(value = "0202/002/edit")
    @ResponseBody
    public ResponseEntity<PersonalComputerWithErrorResults> editForJsonAndSetStatusCode(
            @Validated @RequestBody PersonalComputerForm form, BindingResult bresult,
            Locale locale) {

        if (bresult.hasErrors()) {
            PersonalComputerWithErrorResults personalComputerWithErrorResults =
                    personalComputerHelper.setPersonalComputerErrorResults(bresult, locale);
            return new ResponseEntity<PersonalComputerWithErrorResults>(
                    personalComputerWithErrorResults, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<PersonalComputerWithErrorResults>(
                personalComputerHelper.updateAndBindPersonalComputerWithErrorResults(form, locale),
                HttpStatus.OK);
    }

    @GetMapping(value = "0201", params = "retrunToIndex")
    public String retrunToIndex() {

        return "index";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResults handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
            Locale locale) {

        return personalComputerHelper.setErrorResults(e.getBindingResult().getFieldErrors(),
                locale);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResults handleHttpMessageNotReadableException(HttpMessageNotReadableException e,
            Locale locale) {

        return personalComputerHelper.setHttpMessageNotReadableExceptionResults(e, locale);
    }

}
