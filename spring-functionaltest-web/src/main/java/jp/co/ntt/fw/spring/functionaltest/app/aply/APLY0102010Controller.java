/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
                commonParams.getCommonParam1(), commonParams
                        .getCommonParam2() };
        model.addAttribute("resultMessage", messageSource.getMessage(
                "i.sf.aply.0006", messageArgs, Locale.JAPANESE));
        return "aply/showHandleRequestForm";
    }
}
