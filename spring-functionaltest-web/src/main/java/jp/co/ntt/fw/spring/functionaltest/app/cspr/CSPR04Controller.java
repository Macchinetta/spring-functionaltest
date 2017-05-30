/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.cspr;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("cspr")
@Controller
public class CSPR04Controller {

    @ModelAttribute
    public CustomerBatchRegisterForm setUpForm() {
        return new CustomerBatchRegisterForm();
    }

    @RequestMapping(value = "0401/001")
    public String handle0401001() {
        return "cspr/customerBatchRegister";
    }

    @RequestMapping(value = "0401/002")
    public String handle0401002() {
        return "cspr/customerBatchRegisterUseStandardForm";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String batchRegister(@Validated CustomerBatchRegisterForm form,
            BindingResult result, Model model) throws UnsupportedEncodingException, IOException {
        if (result.hasErrors()) {
            return "cspr/customerBatchRegister";
        }

        model.addAttribute("uploadedFileName", form.getMultipartFile()
                .getOriginalFilename());
        model.addAttribute("registerCount", 30);

        return "cspr/customerBatchComplete";
    }

    @RequestMapping(value = "registerWithTokenFromQuery", method = RequestMethod.POST)
    public String batchRegisterWithTokenFromQuery(
            @Validated CustomerBatchRegisterForm form, BindingResult result,
            Model model) throws UnsupportedEncodingException, IOException {
        if (result.hasErrors()) {
            return "cspr/customerBatchRegisterUseStandardForm";
        }

        model.addAttribute("uploadedFileName", form.getMultipartFile()
                .getOriginalFilename());
        model.addAttribute("registerCount", 30);

        return "cspr/customerBatchComplete";
    }

}
