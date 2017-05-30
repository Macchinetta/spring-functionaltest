/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import javax.validation.groups.Default;

import jp.co.ntt.fw.spring.functionaltest.app.vldt.GroupedValidationByDefaultForm.Japanese;
import jp.co.ntt.fw.spring.functionaltest.app.vldt.GroupedValidationByDefaultForm.Singaporean;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("vldt/0103/002")
public class VLDT0103002Controller {

    @ModelAttribute
    public GroupedValidationByDefaultForm setUpForm() {
        return new GroupedValidationByDefaultForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handle() {
        return "vldt/groupedValidationByDefaultView";
    }

    @RequestMapping(method = RequestMethod.POST, params = { "validate",
            "country=jp" })
    public String handleValidateForJapanese(
            @Validated({ Japanese.class }) GroupedValidationByDefaultForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "vldt/groupedValidationByDefaultView";
        }
        return "redirect:/vldt/0103/002";
    }

    @RequestMapping(method = RequestMethod.POST, params = { "validate",
            "country=sg" })
    public String handleValidateForSingaporean(
            @Validated({ Singaporean.class }) GroupedValidationByDefaultForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "vldt/groupedValidationByDefaultView";
        }
        return "redirect:/vldt/0103/002";
    }

    @RequestMapping(method = RequestMethod.POST, params = { "validate" })
    public String handleValidate(
            @Validated({ Default.class }) GroupedValidationByDefaultForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "vldt/groupedValidationByDefaultView";
        }
        return "redirect:/vldt/0103/002";
    }
}
