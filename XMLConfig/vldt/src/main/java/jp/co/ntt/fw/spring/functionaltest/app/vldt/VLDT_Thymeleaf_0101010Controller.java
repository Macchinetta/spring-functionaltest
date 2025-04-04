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
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("thymeleaf/0101/010")
public class VLDT_Thymeleaf_0101010Controller {

    @ModelAttribute
    public DisplayErrMessageForm setUpDisplayErrMessageForm() {
        DisplayErrMessageForm form = new DisplayErrMessageForm();
        return form;
    }

    @GetMapping
    public String handle() {
        return "thymeleaf/vldt/displayErrMessageByThErrosAll";
    }

    @PostMapping
    public String handleValidate(@Validated DisplayErrMessageForm form, BindingResult result) {
        if (result.hasErrors()) {
            return handle();
        }
        return "redirect:/thymeleaf/0101/010";
    }
}
