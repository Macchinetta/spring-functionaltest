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
@RequestMapping("vldt/0101/007")
public class VLDT0101007Controller {

    @ModelAttribute
    public SimpleValidationByInclusiveForm setUpForm() {
        return new SimpleValidationByInclusiveForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handle() {
        return "vldt/simpleValidationByInclusiveView";
    }

    @RequestMapping(method = RequestMethod.POST, params = "validate")
    public String handleValidate(
            @Validated SimpleValidationByInclusiveForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "vldt/simpleValidationByInclusiveView";
        }
        return "redirect:/vldt/0101/007";
    }
}
