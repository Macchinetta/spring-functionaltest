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
@RequestMapping("vldt/0402/002")
public class VLDT0402002Controller {

    @ModelAttribute
    public CustomBeanValidationByServiceForm setUpForm() {
        return new CustomBeanValidationByServiceForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handle() {
        return "vldt/customBeanValidationByServiceView";
    }

    @RequestMapping(method = RequestMethod.POST, params = "validate")
    public String handleValidate(
            @Validated CustomBeanValidationByServiceForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "vldt/customBeanValidationByServiceView";
        }
        return "redirect:/vldt/0402/002";
    }
}
