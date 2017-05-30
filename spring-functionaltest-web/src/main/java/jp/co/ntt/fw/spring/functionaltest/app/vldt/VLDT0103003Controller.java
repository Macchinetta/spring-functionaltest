/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.groups.Default;

import jp.co.ntt.fw.spring.functionaltest.app.vldt.AuthorizedValidationForm.Anonymous;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("vldt/0103/003")
public class VLDT0103003Controller {

    @Inject
    @Named("validator")
    SmartValidator smartValidator;

    @ModelAttribute
    public AuthorizedValidationForm setUpForm() {
        return new AuthorizedValidationForm();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handle() {
        return "vldt/authorizedValidationView";
    }

    @RequestMapping(method = RequestMethod.POST, params = "LoginSuccess")
    public String handleLoginSuccess() {
        return "vldt/authorizedValidationView";
    }

    @RequestMapping(method = RequestMethod.POST, params = "validate")
    public String handleValidate(AuthorizedValidationForm form,
            BindingResult result, Authentication authentication) {

        smartValidator.validate(form, result,
                getUserValidationGroup(authentication));
        if (result.hasErrors()) {
            return "vldt/authorizedValidationView";
        }
        return "redirect:/vldt/0103/003";
    }

    private Class<?> getUserValidationGroup(Authentication authentication) {

        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication
                    .getPrincipal();
            if (userDetails != null) {
                return Default.class;
            }
        }
        return Anonymous.class;
    }
}
