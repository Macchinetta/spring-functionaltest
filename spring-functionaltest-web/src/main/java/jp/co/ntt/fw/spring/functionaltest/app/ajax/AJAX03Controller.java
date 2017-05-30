/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.ajax;

import java.util.Locale;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.bean.ErrorResults;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.terasoluna.gfw.common.exception.BusinessException;

@RequestMapping("ajax")
@Controller
public class AJAX03Controller {

    @Inject
    PersonalComputerHelper personalComputerHelper;

    @ModelAttribute
    public PersonalComputerForm setUpForm() {
        return new PersonalComputerForm();
    }

    @RequestMapping(value = "0301/001/001")
    public String handle0301001001(PersonalComputerForm form, Model model) {
        personalComputerHelper.setPersonalComputer(1, form);

        model.addAttribute("path", "0301/001/001/edit");
        model.addAttribute("method", "ajax.editPersonalComputer");

        return "ajax/personalComputerEdit";
    }

    @RequestMapping(value = "0301/001/002")
    public String handle0301001002(PersonalComputerForm form, Model model) {
        personalComputerHelper.setPersonalComputer(1, form);

        model.addAttribute("path", "0301/001/002/edit");
        model.addAttribute("method", "ajax.editPersonalComputer");

        return "ajax/personalComputerEdit";
    }

    @RequestMapping(value = "0302/001")
    public String handle0302001(PersonalComputerForm form, Model model) {
        personalComputerHelper.setPersonalComputer(1, form);

        model.addAttribute("path", "0302/001/edit");
        model.addAttribute("method", "ajax.editPersonalComputer");

        return "ajax/personalComputerEdit";
    }

    @RequestMapping(value = "0301/001/001/edit", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PersonalComputerResult editFirst(
            @Validated PersonalComputerForm form, Locale locale) {

        return personalComputerHelper.updateAndBindPersonalComputerResult(form,
                locale);
    }

    @RequestMapping(value = "0301/001/002/edit", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public PersonalComputerResult editSecond(
            @Validated PersonalComputerForm form, Locale locale) {

        return personalComputerHelper.updateAndBindPersonalComputerResult(form,
                locale);
    }

    @RequestMapping(value = "0301", params = "retrunToIndex")
    public String retrunToIndex() {

        return "ajax/index";
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResults handleBindException(BindException e, Locale locale) {

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
