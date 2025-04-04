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

import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class THYM0502Controller {

    @ModelAttribute
    public UserForm setUpTodoForm() {
        return new UserForm();
    }

    @GetMapping("001")
    public String handle0502001(Model model) {
        model.addAttribute("date", new Date(1510704000000L));
        return "thym/creationDate";
    }

    @GetMapping("002")
    public String handle0502002(Model model) {
        model.addAttribute("date", new Date());
        return "thym/cacheCheck";
    }

}
