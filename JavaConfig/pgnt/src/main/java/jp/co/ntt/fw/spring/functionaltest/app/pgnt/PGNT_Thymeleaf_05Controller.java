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
package jp.co.ntt.fw.spring.functionaltest.app.pgnt;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt.CelebritySearchCriteria;

@RequestMapping("thymeleaf")
@Controller
public class PGNT_Thymeleaf_05Controller {

    @ModelAttribute
    public CelebritySearchCriteria setUpForm() {
        CelebritySearchCriteria criteria = new CelebritySearchCriteria();
        return criteria;
    }

    @GetMapping(value = "0502/001")
    public String handle0502001(Model model) {
        model.addAttribute("path", "celebritySearch");
        return "thymeleaf/pgnt/celebrityList";
    }

    @GetMapping(value = "0502/002")
    public String handle0502002(Model model) {
        model.addAttribute("path", "celebritySearch");
        return "thymeleaf/pgnt/celebrityList";
    }

    @GetMapping(value = "0502/003")
    public String handle0502003(Model model) {
        model.addAttribute("path", "celebritySearch");
        return "thymeleaf/pgnt/celebrityList";
    }

}
