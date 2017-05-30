/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import javax.validation.groups.Default;

import jp.co.ntt.fw.spring.functionaltest.app.vldt.GroupedValidationForm.Japanese;
import jp.co.ntt.fw.spring.functionaltest.app.vldt.GroupedValidationForm.Singaporean;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("vldt/0103/001")
public class VLDT0103001Controller {

    @ModelAttribute
    public GroupedValidationForm setUpForm() {
        return new GroupedValidationForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handle() {
        return "vldt/groupedValidationView";
    }

    @RequestMapping(method = RequestMethod.POST, params = { "validate",
            "country=jp" })
    public String handleValidateForJapanese(@Validated({ Japanese.class,
            Default.class }) GroupedValidationForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "vldt/groupedValidationView";
        }
        return "redirect:/vldt/0103/001";
    }

    @RequestMapping(method = RequestMethod.POST, params = { "validate",
            "country=sg" })
    public String handleValidateForSingaporean(@Validated({ Singaporean.class,
            Default.class }) GroupedValidationForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "vldt/groupedValidationView";
        }
        return "redirect:/vldt/0103/001";
    }
}
