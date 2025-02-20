/*
 * Copyright(c) 2014 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
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
    public String batchRegister(@Validated CustomerBatchRegisterForm form, BindingResult result,
            Model model) throws UnsupportedEncodingException, IOException {
        if (result.hasErrors()) {
            return "cspr/customerBatchRegister";
        }

        model.addAttribute("uploadedFileName", form.getMultipartFile().getOriginalFilename());
        model.addAttribute("registerCount", 30);

        return "cspr/customerBatchComplete";
    }

    @RequestMapping(value = "registerWithTokenFromQuery", method = RequestMethod.POST)
    public String batchRegisterWithTokenFromQuery(@Validated CustomerBatchRegisterForm form,
            BindingResult result, Model model) throws UnsupportedEncodingException, IOException {
        if (result.hasErrors()) {
            return "cspr/customerBatchRegisterUseStandardForm";
        }

        model.addAttribute("uploadedFileName", form.getMultipartFile().getOriginalFilename());
        model.addAttribute("registerCount", 30);

        return "cspr/customerBatchComplete";
    }

}
