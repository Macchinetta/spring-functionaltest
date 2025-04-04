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
import jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt.ArticleSearchCriteria;

@RequestMapping("jsp")
@Controller
public class PGNT_JSP_02Controller {

    @ModelAttribute
    public ArticleSearchCriteria setUpArticleSearchCriteria() {
        ArticleSearchCriteria criteria = new ArticleSearchCriteria();
        return criteria;
    }

    @GetMapping(value = "0202/001")
    public String handle0202001(Model model) {
        model.addAttribute("path", "articleSearch");
        return "jsp/pgnt/articleList";
    }

    @GetMapping(value = "0202/002")
    public String handle0202002(Model model) {
        model.addAttribute("path", "articleSearchDispTen");
        return "jsp/pgnt/articleList";
    }

    @GetMapping(value = "0202/003")
    public String handle0202003(Model model) {
        model.addAttribute("path", "articleSearchDispEleven");
        return "jsp/pgnt/articleList";
    }

    @GetMapping(value = "0202/004")
    public String handle0202004(Model model) {
        model.addAttribute("path", "articleSearchDispTwelve");
        return "jsp/pgnt/articleList";
    }

    @GetMapping(value = "0202/005")
    public String handle0202005(Model model) {
        model.addAttribute("path", "articleSearchSortSpecified");
        return "jsp/pgnt/articleList";
    }
}
