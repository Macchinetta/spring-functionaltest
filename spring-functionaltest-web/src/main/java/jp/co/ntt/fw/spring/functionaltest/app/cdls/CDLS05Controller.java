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

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

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

    @RequestMapping(value = "001", method = RequestMethod.GET)
    public String handle05001(Model model) {
        return "cdls/codeListDirectReference";
    }

    @RequestMapping(value = "002", method = RequestMethod.GET, params = "locale")
    public String handle05002(Model model) {
        return "cdls/codeListDirectReference";
    }

    @RequestMapping(value = "003", method = RequestMethod.GET, params = "locale")
    public String handle05003(Model model) {
        return "cdls/codeListDirectReference";
    }

    @RequestMapping(value = "004", method = RequestMethod.GET, params = "locale")
    public String handle05004(Model model) {
        return "cdls/codeListDirectReference";
    }
}
