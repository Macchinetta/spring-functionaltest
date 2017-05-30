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
public class VLDT0502Controller {

    @ModelAttribute(value = "roleForFormatterForm")
    public RoleForFormatterForm roleForFormatterForm() {
        return new RoleForFormatterForm();
    }

    @RequestMapping(value = "001", method = RequestMethod.GET)
    public String handle001Form() {
        return form();
    }

    @RequestMapping(value = "001", method = RequestMethod.POST)
    public String handleValidate(@Valid RoleForFormatterForm form,
            BindingResult result, Model model) {
        return form();
    }

    private String form() {
        return "vldt/existInCheckForCollectionByFormatterForm";
    }
}
