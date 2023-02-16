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
package jp.co.ntt.fw.spring.functionaltest.app.cdls;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.CodeList;
import jp.co.ntt.fw.spring.functionaltest.domain.service.cdls.CodeListService;

@Controller
public class CDLS0105Controller {

    @Inject
    CDLSBeanMapper beanMapper;

    @Inject
    CodeListService codeListService;

    @ModelAttribute
    public CodeListForm setUpCodeListForm() {
        return new CodeListForm();
    }

    @ModelAttribute
    public ClI18nPriceForm setUpCLI18nPriceForm() {
        return new ClI18nPriceForm();
    }

    @ModelAttribute
    public ClI18nDBPriceForm setUpCLI18nDBPriceForm() {
        return new ClI18nDBPriceForm();
    }

    @GetMapping(value = "001", params = "locale")
    public String handle05001() {
        return "cdls/simpleI18nCodeList";
    }

    @GetMapping(value = "002", params = "locale")
    public String handle05002(Model model) {
        return "cdls/simpleI18nCodeList";
    }

    @GetMapping(value = "003", params = "locale")
    public String handle05003(Model model) {
        return "cdls/simpleI18nCodeList";
    }

    @PostMapping(value = "003", params = "post")
    public String handle05003Post(RedirectAttributes redirectAttrs,
            Locale locale, @Validated ClI18nPriceForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "cdls/simpleI18nCodeList";
        }

        CodeList codeList = beanMapper.map(form);

        redirectAttrs.addFlashAttribute("i18nPriceCodeListValue",
                (codeListService.getI18nPriceCodeListValue(codeList.getId(),
                        locale)));

        redirectAttrs.addAttribute("locale", locale.getLanguage());
        return "redirect:003";
    }

    @GetMapping(value = "004", params = "locale")
    public String handle05004(Model model) {
        return "cdls/simpleI18nCodeList";
    }

    @PostMapping(value = "004", params = "post")
    public String handle05004Post(RedirectAttributes redirectAttrs,
            Locale locale, @Validated ClI18nPriceForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "cdls/simpleI18nCodeList";
        }

        CodeList codeList = beanMapper.map(form);

        redirectAttrs.addFlashAttribute("i18nPriceCodeListValue",
                (codeListService.getI18nPriceCodeListValue(codeList.getId(),
                        locale)));

        redirectAttrs.addAttribute("locale", locale.getLanguage());
        return "redirect:004";
    }

    @GetMapping(value = "005", params = "locale")
    public String handle05005() {
        return "cdls/simpleI18nCodeList";
    }

    @GetMapping(value = "006", params = "locale")
    public String handle05006() {
        return "cdls/simpleI18nCodeList";
    }

    @GetMapping(value = "007", params = "locale")
    public String handle05007(Model model) {
        return "cdls/simpleI18nCodeList";
    }

    @PostMapping(value = "007", params = "post")
    public String handle05007Post(RedirectAttributes redirectAttrs,
            Locale locale, @Validated ClI18nDBPriceForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "cdls/simpleI18nCodeList";
        }

        CodeList codeList = beanMapper.map(form);

        redirectAttrs.addFlashAttribute("i18nDBPriceCodeListValue",
                (codeListService.getI18nDBPriceCodeListValue(codeList.getId(),
                        locale)));

        redirectAttrs.addAttribute("locale", locale.getLanguage());
        return "redirect:007";
    }

    @GetMapping(value = "008", params = "locale")
    public String handle05008(Model model) {
        return "cdls/simpleI18nCodeList";
    }

    @PostMapping(value = "008", params = "post")
    public String handle05008Post(RedirectAttributes redirectAttrs,
            Locale locale, @Validated ClI18nDBPriceForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return "cdls/simpleI18nCodeList";
        }

        CodeList codeList = beanMapper.map(form);

        redirectAttrs.addFlashAttribute("i18nDBPriceCodeListValue",
                (codeListService.getI18nDBPriceCodeListValue(codeList.getId(),
                        locale)));

        redirectAttrs.addAttribute("locale", locale.getLanguage());
        return "redirect:008";
    }
}
