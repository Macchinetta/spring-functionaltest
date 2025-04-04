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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "jsp/0501/001")
public class VLDT_JSP_0501001Controller {

    @ModelAttribute
    public ExistInCheckDefaultForm setUpExistInCheckDefaultForm() {
        return new ExistInCheckDefaultForm();
    }

    @GetMapping
    public String handle(ExistInCheckDefaultForm form, Model model) {
        return "jsp/vldt/existIncheckDefaultForm";
    }

    @PostMapping
    public String handleValidate(@Valid ExistInCheckDefaultForm form, BindingResult result,
            Model model) {
        return "jsp/vldt/existIncheckDefaultForm";
    }
}
