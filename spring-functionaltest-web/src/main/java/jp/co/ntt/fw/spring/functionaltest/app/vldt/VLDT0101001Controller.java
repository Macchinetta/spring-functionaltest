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
@RequestMapping("vldt/0101/001")
public class VLDT0101001Controller {

    @ModelAttribute
    public SimpleValidationByJSR303Form setUpForm() {
        return new SimpleValidationByJSR303Form();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handle() {
        return "vldt/simpleValidationByJSR303View";
    }

    @RequestMapping(method = RequestMethod.POST, params = "validate")
    public String handleValidate(@Validated SimpleValidationByJSR303Form form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "vldt/simpleValidationByJSR303View";
        }
        return "redirect:/vldt/0101/001";
    }
}
