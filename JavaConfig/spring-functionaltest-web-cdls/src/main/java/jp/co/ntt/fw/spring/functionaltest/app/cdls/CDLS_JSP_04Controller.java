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
package jp.co.ntt.fw.spring.functionaltest.app.cdls;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("jsp")
@Controller
public class CDLS_JSP_04Controller {

    @ModelAttribute
    public CodeListForm setUpCodeListForm() {
        return new CodeListForm();
    }

    @GetMapping(value = "0403/001")
    public String handle03001(Model model) {
        return "jsp/cdls/codeListVariation";
    }

    @GetMapping(value = "0403/002")
    public String handle03002(Model model) {
        return "jsp/cdls/codeListVariation";
    }

    @GetMapping(value = "0403/003")
    public String handle03003(Model model) {
        return "jsp/cdls/codeListVariation";
    }
}
