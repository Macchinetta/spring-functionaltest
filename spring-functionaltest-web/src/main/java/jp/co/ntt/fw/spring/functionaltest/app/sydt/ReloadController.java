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
package jp.co.ntt.fw.spring.functionaltest.app.sydt;

import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jp.co.ntt.fw.spring.functionaltest.domain.service.sydt.OperationDataService;

@RequestMapping("sydt")
@Controller
public class ReloadController {

    @Inject
    private OperationDataService service;

    @ModelAttribute
    public ReloadForm setReloadForm() {
        return new ReloadForm();
    }

    @GetMapping("register")
    public String register() {
        return "sydt/register";
    }

    @PostMapping("confirm")
    public String confirm(Model model, @Validated ReloadForm form) {
        this.service.update(form.getDiffValue());
        int diff = this.service.select();
        model.addAttribute("diff", diff);
        return "sydt/confirm";
    }

    @GetMapping("select")
    public String select(Model model) {
        int diff = this.service.select();
        model.addAttribute("diff", diff);
        return "sydt/confirm";
    }
}
