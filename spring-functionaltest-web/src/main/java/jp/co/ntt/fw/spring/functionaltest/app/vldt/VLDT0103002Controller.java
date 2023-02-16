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
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.groups.Default;
import jp.co.ntt.fw.spring.functionaltest.app.vldt.GroupedValidationByDefaultForm.Japanese;
import jp.co.ntt.fw.spring.functionaltest.app.vldt.GroupedValidationByDefaultForm.Singaporean;

@Controller
@RequestMapping("vldt/0103/002")
public class VLDT0103002Controller {

    @ModelAttribute
    public GroupedValidationByDefaultForm setUpForm() {
        return new GroupedValidationByDefaultForm();
    }

    @GetMapping
    public String handle() {
        return "vldt/groupedValidationByDefaultView";
    }

    @PostMapping(params = { "validate", "country=jp" })
    public String handleValidateForJapanese(@Validated({
            Japanese.class }) GroupedValidationByDefaultForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "vldt/groupedValidationByDefaultView";
        }
        return "redirect:/vldt/0103/002";
    }

    @PostMapping(params = { "validate", "country=sg" })
    public String handleValidateForSingaporean(@Validated({
            Singaporean.class }) GroupedValidationByDefaultForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "vldt/groupedValidationByDefaultView";
        }
        return "redirect:/vldt/0103/002";
    }

    @PostMapping(params = { "validate" })
    public String handleValidate(@Validated({
            Default.class }) GroupedValidationByDefaultForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "vldt/groupedValidationByDefaultView";
        }
        return "redirect:/vldt/0103/002";
    }
}
