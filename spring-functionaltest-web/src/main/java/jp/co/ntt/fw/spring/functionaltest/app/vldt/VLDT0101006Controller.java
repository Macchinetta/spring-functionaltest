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
@RequestMapping("vldt/0101/006")
public class VLDT0101006Controller {

    @ModelAttribute
    public VariousSimpleValidationForm setUpForm() {
        return new VariousSimpleValidationForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handle() {
        return "vldt/variousSimpleValidationView";
    }

    @RequestMapping(method = RequestMethod.POST, params = "validate")
    public String handleValidate(@Validated VariousSimpleValidationForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "vldt/variousSimpleValidationView";
        }
        return "redirect:/vldt/0101/006";
    }
}
