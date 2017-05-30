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
@RequestMapping("vldt/0401/001")
public class VLDT0401001Controller {

    @ModelAttribute
    public CustomBeanValidationByExistingRulesForm setUpForm() {
        return new CustomBeanValidationByExistingRulesForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handle() {
        return "vldt/customBeanValidationByExistingRulesView";
    }

    @RequestMapping(method = RequestMethod.POST, params = "validate")
    public String handleValidate(
            @Validated CustomBeanValidationByExistingRulesForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "vldt/customBeanValidationByExistingRulesView";
        }
        return "redirect:/vldt/0401/001";
    }
}
