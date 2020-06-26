/*
 * Copyright(c) 2014 NTT Corporation.
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

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Article;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt.ArticleSearchCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.service.pgnt.ArticleService;

@RequestMapping("pgnt")
@Controller
public class PGNT0102002Controller {
    @Inject
    ArticleService articleService;

    @ModelAttribute
    public ArticleSearchCriteria setUpForm() {
        ArticleSearchCriteria criteria = new ArticleSearchCriteria();
        return criteria;
    }

    @RequestMapping(value = "articleSearchDescArticleId", method = RequestMethod.GET)
    public String search(ArticleSearchCriteria criteria, Pageable pageable,
            Model model) {

        Page<Article> page = articleService.getArticles(criteria, pageable);
        model.addAttribute("page", page);
        model.addAttribute("path", "articleSearchDescArticleId");
        return "pgnt/articleList";
    }
}
