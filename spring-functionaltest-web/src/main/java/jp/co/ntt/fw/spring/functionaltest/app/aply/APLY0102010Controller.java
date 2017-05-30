/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.aply;

import java.util.Locale;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("aply")
@Controller
public class APLY0102010Controller {

    @Inject
    MessageSource messageSource;

    @ModelAttribute
    public HandleRequestForm setUpForm() {
        return new HandleRequestForm();
    }

    @RequestMapping(value = "0102/010")
    public String handle02010(CommonParameters commonParams, Model model) {
        String[] messageArgs = { "@RequestMapping(value = \"0102/010\")",
                commonParams.getCommonParam1(), commonParams.getCommonParam2() };
        model.addAttribute("resultMessage", messageSource.getMessage(
                "i.sf.aply.0006", messageArgs, Locale.JAPANESE));
        return "aply/showHandleRequestForm";
    }
}
