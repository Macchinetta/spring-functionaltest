/*
 * Copyright(c) 2025 NTT Corporation.
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

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.dozermapper.core.Mapper;

import jp.co.ntt.fw.spring.functionaltest.domain.model.CodeList;
import jp.co.ntt.fw.spring.functionaltest.domain.service.cdls.ReloadCodeListService;

@Controller
public class CDLS0202Controller {

    @Inject
    Mapper beanMapper;

    @Inject
    ReloadCodeListService reloadCodeListService;

    @ModelAttribute
    public CodeListForm setUpCodeListForm() {
        return new CodeListForm();
    }

    @RequestMapping(value = "001", method = RequestMethod.GET, params = "locale")
    public String handle02001(Model model, UpdateCodeListForm form) {
        return "cdls/codeListReload";
    }

    @RequestMapping(value = "001", method = RequestMethod.POST, params = "update")
    public String handle02001Update(RedirectAttributes redirectAttrs, Locale locale,
            @Validated UpdateCodeListForm updateCodeListForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cdls/codeListReload";
        }
        CodeList updateCodeList = beanMapper.map(updateCodeListForm, CodeList.class);
        reloadCodeListService.updatePriceTableValue(updateCodeList, locale);

        redirectAttrs.addAttribute("locale", locale.getLanguage());
        return "redirect:001";
    }

    @RequestMapping(value = "002", method = RequestMethod.GET, params = "locale")
    public String handle02002(Model model, UpdateCodeListForm form) {
        return "cdls/codeListReload";
    }

    @RequestMapping(value = "002", method = RequestMethod.POST, params = "update")
    public String handle02002Update(RedirectAttributes redirectAttrs, Locale locale,
            @Validated UpdateCodeListForm updateCodeListForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cdls/codeListReload";
        }
        CodeList updateCodeList = beanMapper.map(updateCodeListForm, CodeList.class);
        reloadCodeListService.updatePriceTableValue(updateCodeList, locale);

        redirectAttrs.addAttribute("locale", locale.getLanguage());
        return "redirect:002";
    }

    @RequestMapping(value = "003", method = RequestMethod.GET, params = "locale")
    public String handle02003(Model model, UpdateCodeListForm form) {
        return "cdls/codeListReload";
    }

    @RequestMapping(value = "003", method = RequestMethod.POST, params = "update")
    public String handle02003Update(RedirectAttributes redirectAttrs, Locale locale,
            @Validated UpdateCodeListForm updateCodeListForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cdls/codeListReload";
        }
        CodeList updateCodeList = beanMapper.map(updateCodeListForm, CodeList.class);
        reloadCodeListService.updatePriceTableValue(updateCodeList, locale);

        redirectAttrs.addAttribute("locale", locale.getLanguage());
        return "redirect:003";
    }

    @RequestMapping(value = "003", method = RequestMethod.GET, params = "refresh")
    public String handle02002Refresh(Model model, UpdateCodeListForm form) {
        reloadCodeListService.refresh2();
        return "cdls/codeListReload";
    }

    @RequestMapping(value = "004", method = RequestMethod.GET, params = "locale")
    public String handle02004(Model model, UpdateCodeListForm form) {
        return "cdls/codeListReload";
    }

    @RequestMapping(value = "004", method = RequestMethod.POST, params = "update")
    public String handle02004Update(RedirectAttributes redirectAttrs, Locale locale,
            @Validated UpdateCodeListForm updateCodeListForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cdls/codeListReload";
        }
        CodeList updateCodeList = beanMapper.map(updateCodeListForm, CodeList.class);
        reloadCodeListService.updatePriceTableValue(updateCodeList, locale);

        redirectAttrs.addAttribute("locale", locale.getLanguage());
        return "redirect:004";
    }

    @RequestMapping(value = "004", method = RequestMethod.GET, params = "refresh")
    public String handle02004Refresh(Model model, UpdateCodeListForm form) {
        reloadCodeListService.refresh2();
        return "cdls/codeListReload";
    }
}
