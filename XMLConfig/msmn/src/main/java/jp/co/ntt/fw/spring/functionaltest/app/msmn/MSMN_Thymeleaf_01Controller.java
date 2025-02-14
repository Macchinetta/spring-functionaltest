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
package jp.co.ntt.fw.spring.functionaltest.app.msmn;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.inject.Inject;

@Controller
@RequestMapping("thymeleaf/01")
public class MSMN_Thymeleaf_01Controller {

    @Inject
    @Qualifier("messageSourceUTF8")
    private MessageSource messageSourceUTF8;

    @ModelAttribute
    public SampleForm setUpForm() {
        return new SampleForm();
    }

    @GetMapping(value = "02/001")
    public String msmn0102001() {
        return "thymeleaf/msmn/displayYMD";
    }

    @GetMapping(value = "02/002")
    public String msmn0102002(Model model, Locale locale) {
        model.addAttribute("year",
                this.messageSourceUTF8.getMessage("label.aa.bb.year", null, locale));
        model.addAttribute("month",
                this.messageSourceUTF8.getMessage("label.aa.bb.month", null, locale));
        model.addAttribute("day",
                this.messageSourceUTF8.getMessage("label.aa.bb.day", null, locale));
        return "thymeleaf/msmn/displayYMDUTF8";
    }

}
