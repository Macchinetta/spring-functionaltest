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
@RequestMapping("vldt/0201/001")
public class VLDT0201001Controller {

    @Inject
    PasswordEqualsValidator passwordEqualsValidator;

    @ModelAttribute
    public UserForm setUpForm() {
        return new UserForm();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(passwordEqualsValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handle() {
        return "vldt/correlationValidationBySpringValidatorView";
    }

    @RequestMapping(method = RequestMethod.POST, params = "validate")
    public String handleValidate(@Validated UserForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "vldt/correlationValidationBySpringValidatorView";
        }
        return "redirect:/vldt/0201/001";
    }
}
