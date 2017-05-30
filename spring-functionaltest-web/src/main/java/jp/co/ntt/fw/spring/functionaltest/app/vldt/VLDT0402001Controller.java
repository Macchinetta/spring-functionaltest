/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("vldt/0402/001")
public class VLDT0402001Controller {

    @ModelAttribute
    public CustomBeanValidationByOriginalRulesForm setUpForm() {
        return new CustomBeanValidationByOriginalRulesForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handle() {
        return "vldt/customBeanValidationByOriginalRulesView";
    }

    @RequestMapping(method = RequestMethod.POST, params = "validate")
    public String handleValidate(
            @Validated CustomBeanValidationByOriginalRulesForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "vldt/customBeanValidationByOriginalRulesView";
        }
        return "redirect:/vldt/0402/001";
    }
}
