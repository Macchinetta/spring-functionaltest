/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "vldt/0501/001")
public class VLDT0501001Controller {

    @ModelAttribute
    public ExistInCheckDefaultForm setUpExistInCheckDefaultForm() {
        return new ExistInCheckDefaultForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String handle(ExistInCheckDefaultForm form, Model model) {
        return "vldt/existIncheckDefaultForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String handleValidate(@Valid ExistInCheckDefaultForm form,
            BindingResult result, Model model) {
        return "vldt/existIncheckDefaultForm";
    }
}
