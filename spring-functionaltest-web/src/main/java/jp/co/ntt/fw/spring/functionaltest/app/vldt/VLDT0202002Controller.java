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
@RequestMapping("vldt/0202/002")
public class VLDT0202002Controller {

    @ModelAttribute
    public ConfirmFormForMultiFieldHighlight setUpForm() {
        return new ConfirmFormForMultiFieldHighlight();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handle() {
        return "vldt/correlationValidationByBeanValiidationViewForMultiFieldHighlight";
    }

    @RequestMapping(method = RequestMethod.POST, params = "validate")
    public String handleValidate(
            @Validated ConfirmFormForMultiFieldHighlight confirmFormForMultiFieldHighlight,
            BindingResult result) {

        if (result.hasErrors()) {
            return "vldt/correlationValidationByBeanValiidationViewForMultiFieldHighlight";
        }
        return "redirect:/vldt/0202/002";
    }
}
