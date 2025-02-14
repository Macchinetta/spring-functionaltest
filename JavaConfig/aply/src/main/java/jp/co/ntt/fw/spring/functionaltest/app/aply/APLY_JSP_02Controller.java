/*
 * Copyright(c) 2024 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.app.aply;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.inject.Inject;

@RequestMapping("jsp")
@Controller
public class APLY_JSP_02Controller {
    @Inject
    LargerValue1ThanValue2Validator largerValue1ThanValue2Validator;

    @ModelAttribute
    public FormObjectForm setUpForm() {
        FormObjectForm form = new FormObjectForm();
        form.setInitConfirmValue1("@ModelAttribute属性名無し初期設定値");
        return form;
    }

    @ModelAttribute("formObjectForm2")
    public FormObjectForm setUpForm2() {
        FormObjectForm form = new FormObjectForm();
        form.setInitConfirmValue1("@ModelAttribute属性名あり初期設定値");
        return form;
    }

    @ModelAttribute("formObjectForm3")
    public FormObjectForm setUpSampleForm(@CookieValue("JSESSIONID") String sessionId) {
        FormObjectForm form = new FormObjectForm();
        form.setInitConfirmValue1(sessionId);
        return form;
    }

    @InitBinder
    public void initWebDataBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Long.class, "value6",
                new CustomNumberEditor(Long.class, new DecimalFormat("###,###"), true));
        binder.addValidators(largerValue1ThanValue2Validator);
    }

    @InitBinder("formObjectForm2")
    public void initWebDataBinder2(WebDataBinder binder) {
        binder.registerCustomEditor(Long.class, "value7",
                new CustomNumberEditor(Long.class, NumberFormat.getCurrencyInstance(), true));
    }

    @GetMapping(value = "0201/001")
    public String handle01001(FormObjectForm form, Model model) {
        form.setValue1(1234567);
        return "jsp/aply/formObjectForm";
    }

    @PostMapping(value = "0201/001")
    public String handle01001submit(FormObjectForm form, Model model) {
        return "jsp/aply/formObjectComplete";
    }

    @GetMapping(value = "0201/002")
    public String handle01002(FormObjectForm form, Model model) {
        form.setValue2(2345678);
        return "jsp/aply/formObjectForm";
    }

    @PostMapping(value = "0201/002")
    public String handle01002submit(FormObjectForm form, Model model) {
        return "jsp/aply/formObjectComplete";
    }

    @GetMapping(value = "0201/003")
    public String handle01003(FormObjectForm form, Model model) {
        form.setValue3(3456789);
        return "jsp/aply/formObjectForm";
    }

    @PostMapping(value = "0201/003")
    public String handle01003submit(FormObjectForm form, Model model) {
        return "jsp/aply/formObjectComplete";
    }

    @GetMapping(value = "0201/004")
    public String handle01004(FormObjectForm form, Model model) {
        form.setValue4(BigDecimal.valueOf(0.44));
        return "jsp/aply/formObjectForm";
    }

    @PostMapping(value = "0201/004")
    public String handle01004submit(FormObjectForm form, Model model) {
        return "jsp/aply/formObjectComplete";
    }

    @GetMapping(value = "0201/005")
    public String handle01005(FormObjectForm form, Model model) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        form.setValue5(sdf.parse("19550505"));
        return "jsp/aply/formObjectForm";
    }

    @PostMapping(value = "0201/005")
    public String handle01005submit(FormObjectForm form, Model model) {
        return "jsp/aply/formObjectComplete";
    }

    @GetMapping(value = "0201/006")
    public String handle01006(FormObjectForm form, Model model) {
        form.setValue6(Long.valueOf(6666666666l));
        return "jsp/aply/formObjectForm";
    }

    @PostMapping(value = "0201/006")
    public String handle01006submit(FormObjectForm form, Model model) {
        return "jsp/aply/formObjectComplete";
    }

    @GetMapping(value = "0201/007")
    public String handle01007(@ModelAttribute("formObjectForm2") FormObjectForm form, Model model) {
        form.setValue7(Long.valueOf(7777777777l));
        return "jsp/aply/formObjectForm2";
    }

    @PostMapping(value = "0201/007")
    public String handle01007_1(@ModelAttribute("formObjectForm2") FormObjectForm form,
            Model model) {
        return "jsp/aply/formObjectComplete2";
    }

    @GetMapping(value = "0202/001")
    public String handle02001(Model model) {
        return "jsp/aply/formInitConfirm";
    }

    @GetMapping(value = "0202/002")
    public String handle02002(Model model) {
        return "jsp/aply/formInitConfirm";
    }

    @GetMapping(value = "0202/003")
    public String handle02003(Model model) {
        return "jsp/aply/formInitConfirm";
    }

    @GetMapping(value = "0203/001")
    public String handle03001(Model model) {
        return "jsp/aply/formObjectForm";
    }

    @PostMapping(value = "0203/001")
    public String handle03001_1(@Validated FormObjectForm form, Model model) {
        return "jsp/aply/formObjectComplete";
    }

    @GetMapping(value = "0203/002")
    public String handle03002(@ModelAttribute("formObjectForm2") FormObjectForm form, Model model) {
        return "jsp/aply/formObjectForm2";
    }

    @PostMapping(value = "0203/002")
    public String handle03002_1(@ModelAttribute("formObjectForm2") @Validated FormObjectForm form,
            Model model) {
        return "jsp/aply/formObjectComplete2";
    }

    @GetMapping(value = "0203/003")
    public String handle03003(Model model) {
        return "jsp/aply/formObjectForm";
    }

    @PostMapping(value = "0203/003")
    public String handle03003(@Validated FormObjectForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "jsp/aply/formObjectForm";
        }
        return "jsp/aply/formObjectComplete";
    }

    @GetMapping(value = "0203/004")
    public String handle03004(Model model) {
        return "jsp/aply/formObjectForm";
    }

    @PostMapping(value = "0203/004")
    public String handle03004(@Validated FormObjectForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "jsp/aply/formObjectForm";
        }
        return "jsp/aply/formObjectComplete";
    }

    @GetMapping(value = "0203/005")
    public String handle03005(Model model) {
        return "jsp/aply/formObjectForm";
    }

    @PostMapping(value = "0203/005")
    public String handle03005(@Validated FormObjectForm form, BindingResult result, Model model) {
        if (result.hasGlobalErrors()) {
            return "jsp/aply/formObjectForm";
        }
        return "jsp/aply/formObjectComplete";
    }

    @GetMapping(value = "0203/006")
    public String handle03006(Model model) {
        return "jsp/aply/formObjectForm";
    }

    @PostMapping(value = "0203/006")
    public String handle03006(@Validated FormObjectForm form, BindingResult result, Model model) {
        if (result.hasGlobalErrors()) {
            return "jsp/aply/formObjectForm";
        }
        return "jsp/aply/formObjectComplete";
    }

    @GetMapping(value = "0203/007")
    public String handle03007(Model model) {
        return "jsp/aply/formObjectForm";
    }

    @PostMapping(value = "0203/007")
    public String handle03007(@Validated FormObjectForm form, BindingResult result, Model model) {
        if (result.hasFieldErrors()) {
            return "jsp/aply/formObjectForm";
        }
        return "jsp/aply/formObjectComplete";
    }

    @GetMapping(value = "0203/008")
    public String handle03008(Model model) {
        return "jsp/aply/formObjectForm";
    }

    @PostMapping(value = "0203/008")
    public String handle03008(@Validated FormObjectForm form, BindingResult result, Model model) {
        if (result.hasFieldErrors()) {
            return "jsp/aply/formObjectForm";
        }
        return "jsp/aply/formObjectComplete";
    }

    @GetMapping(value = "0203/009")
    public String handle03009(Model model) {
        return "jsp/aply/formObjectForm";
    }

    @PostMapping(value = "0203/009")
    public String handle03009(@Validated FormObjectForm form, BindingResult result, Model model) {
        if (result.hasFieldErrors("value3")) {
            return "jsp/aply/formObjectForm";
        }
        return "jsp/aply/formObjectComplete";
    }

    @GetMapping(value = "0203/010")
    public String handle03010(Model model) {
        return "jsp/aply/formObjectForm";
    }

    @PostMapping(value = "0203/010")
    public String handle03010(@Validated FormObjectForm form, BindingResult result, Model model) {
        if (result.hasFieldErrors("value3")) {
            return "jsp/aply/formObjectForm";
        }
        return "jsp/aply/formObjectComplete";
    }
}
