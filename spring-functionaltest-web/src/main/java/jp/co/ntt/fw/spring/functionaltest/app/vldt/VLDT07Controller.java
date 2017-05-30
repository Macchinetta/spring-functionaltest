/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.ntt.fw.spring.functionaltest.domain.service.vldt.CmdExecService;

@RequestMapping("vldt")
@Controller
public class VLDT07Controller {

    @Inject
    CmdExecService cmdExecService;

    @ModelAttribute
    public OsCommandInjectionForm setUpOsCommandInjectionForm() {
        return new OsCommandInjectionForm();
    }

    @RequestMapping(value = "07", method = RequestMethod.GET)
    public String handle() {
        return "vldt/osCommandInjectionForm";
    }

    @RequestMapping(value = "07", method = RequestMethod.POST)
    public String handleValidate(@Validated OsCommandInjectionForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return handle();
        }

        cmdExecService.exec(form.getCmdStr(), form.getArg());

        return "redirect:/vldt/07";
    }

}
