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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.support.RequestContextUtils;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CDLS05Controller {

    @Value("${cdls.simpleI18nCodeList.fallback.locale}")
    private Locale fallBackLocale;

    @ModelAttribute
    public CodeListForm setUpCodeListForm() {
        return new CodeListForm();
    }

    @ModelAttribute
    public void setUpLocale(Model model, HttpServletRequest request) {
        model.addAttribute("requestLocale", RequestContextUtils.getLocale(
                request));
        model.addAttribute("fallBackLocale", fallBackLocale);
    }

    @GetMapping(value = "001")
    public String handle05001(Model model) {
        return "cdls/codeListDirectReference";
    }

    @GetMapping(value = "002", params = "locale")
    public String handle05002(Model model) {
        return "cdls/codeListDirectReference";
    }

    @GetMapping(value = "003", params = "locale")
    public String handle05003(Model model) {
        return "cdls/codeListDirectReference";
    }

    @GetMapping(value = "004", params = "locale")
    public String handle05004(Model model) {
        return "cdls/codeListDirectReference";
    }
}
