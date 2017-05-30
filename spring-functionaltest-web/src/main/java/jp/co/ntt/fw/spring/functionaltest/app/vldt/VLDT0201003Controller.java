/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("vldt/0201/003")
public class VLDT0201003Controller {

    @Inject
    PasswordEqualsValidatorForMultiFieldHighlight passwordEqualsValidatorForMultiFieldHighlight;

    @ModelAttribute
    public UserForm setUpForm() {
        return new UserForm();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(passwordEqualsValidatorForMultiFieldHighlight);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handle() {
        return "vldt/correlationValidationBySpringValidatorViewForMultiFieldHighlight";
    }

    @RequestMapping(method = RequestMethod.POST, params = "validate")
    public String handleValidate(@Validated UserForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "vldt/correlationValidationBySpringValidatorViewForMultiFieldHighlight";
        }
        return "redirect:/vldt/0201/003";
    }
}
