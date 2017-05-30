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
@RequestMapping("vldt/0101/003")
public class VLDT0101003Controller {

    @ModelAttribute
    public DisplayInsideMessagesForm setUpForm() {
        return new DisplayInsideMessagesForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handle() {
        return "vldt/displayInsideMessagesView";
    }

    @RequestMapping(method = RequestMethod.POST, params = "validate")
    public String handleValidate(@Validated DisplayInsideMessagesForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "vldt/displayInsideMessagesView";
        }
        return "redirect:/vldt/0101/003";
    }
}
