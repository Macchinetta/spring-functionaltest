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

//@RequestMapping("/vldt/0304")
@Controller
public class VLDT0304001Controller {

    @ModelAttribute
    public DefineNotAsciiMessageByValidationMessagesForm setUpForm() {
        return new DefineNotAsciiMessageByValidationMessagesForm();
    }

    @RequestMapping(value = "001", method = RequestMethod.GET)
    public String handle() {
        return "vldt/defineNotAsciiMessageByValidationMessagesView";
    }

    @RequestMapping(value = "001", method = RequestMethod.POST, params = "validate")
    public String handleValidate(
            @Validated DefineNotAsciiMessageByValidationMessagesForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "vldt/defineNotAsciiMessageByValidationMessagesView";
        }
        return "redirect:/vldt/0304/001";
    }
}
