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
package jp.co.ntt.fw.spring.functionaltest.app.thym;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
// @GetMapping("thym/0803/004")
public class THYM0803004Controller {

    @GetMapping
    public String handleHtml(Model model) {

        model.addAttribute("pathFragments", "004");
        model.addAttribute("className", "TemplateResolver");

        return "thym/jsAndHtmlTemplate";
    }

    @GetMapping(value = "{jsTemplate}.js")
    public String handleJs(@PathVariable("jsTemplate") String jsTemplate,
            Model model) {

        Item item = new Item();
        item.setName("Grape");
        model.addAttribute(item);

        return jsTemplate;
    }
}
