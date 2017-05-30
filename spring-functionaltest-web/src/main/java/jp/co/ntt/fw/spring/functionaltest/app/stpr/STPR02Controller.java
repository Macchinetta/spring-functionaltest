/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.stpr;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("stpr")
@Controller
public class STPR02Controller {

    @ModelAttribute
    public StringProcessing0201001Form setStringProcessing0201001Form() {
        return new StringProcessing0201001Form();
    }

    @ModelAttribute
    public StringProcessing0201002Form setStringProcessing0201002Form() {
        return new StringProcessing0201002Form();
    }

    @RequestMapping(value = "0201/001", method = RequestMethod.GET)
    public String handle0201001() {
        return "stpr/paddingInput";
    }

    @RequestMapping(value = "0201/001/padding", method = RequestMethod.POST)
    public String handle0201001Padding(Model model,
            @Validated StringProcessing0201001Form form, BindingResult result) {

        if (result.hasErrors()) {
            return handle0201001();
        }
        String paddingStr = String.format(form.getFormat(), form
                .getTargetValueInt());
        model.addAttribute("resultString", paddingStr);

        return "stpr/paddingResult";
    }

    @RequestMapping(value = "0201/002", method = RequestMethod.GET)
    public String handle0201002() {
        return "stpr/suppressInput";
    }

    @RequestMapping(value = "0201/002/suppress", method = RequestMethod.POST)
    public String handle0201002Suppress(Model model,
            StringProcessing0201002Form form) {

        String paddingStr = form.getTargetValueStr();
        String suppressStr = paddingStr.replaceFirst(form.getFormat(), "");
        model.addAttribute("resultString", suppressStr);

        return "stpr/suppressResult";
    }

}
