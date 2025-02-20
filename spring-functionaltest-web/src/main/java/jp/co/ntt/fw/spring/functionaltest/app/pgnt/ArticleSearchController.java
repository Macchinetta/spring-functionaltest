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
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Article;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt.ArticleSearchCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.service.pgnt.ArticleService;

//@RequestMapping("pgnt")
@Controller
public class ArticleSearchController {

    @Inject
    ArticleService articleService;

    @ModelAttribute
    public ArticleSearchCriteria setUpForm() {
        ArticleSearchCriteria criteria = new ArticleSearchCriteria();
        return criteria;
    }

    @RequestMapping(value = "articleSearch", method = RequestMethod.GET)
    public String search(
            ArticleSearchCriteria criteria, @PageableDefault(page = 0, size = 6,
                    direction = Direction.DESC, sort = {"article_id"}) Pageable pageable,
            Model model) {

        Page<Article> page = articleService.getArticles(criteria, pageable);
        model.addAttribute("page", page);
        model.addAttribute("path", "articleSearch");
        return "pgnt/articleList";
    }

    @RequestMapping(value = "articleSearchDispTen", method = RequestMethod.GET)
    public String searchDispTen(ArticleSearchCriteria criteria, @PageableDefault Pageable pageable,
            Model model) {

        Page<Article> page = articleService.getArticles(criteria, pageable);
        model.addAttribute("page", page);
        model.addAttribute("path", "articleSearchDispTen");
        return "pgnt/articleList";
    }

    @RequestMapping(value = "articleSearchDispEleven", method = RequestMethod.GET)
    public String searchDispEleven(ArticleSearchCriteria criteria,
            @PageableDefault(11) Pageable pageable, Model model) {

        Page<Article> page = articleService.getArticles(criteria, pageable);
        model.addAttribute("page", page);
        model.addAttribute("path", "articleSearchDispEleven");
        return "pgnt/articleList";
    }

    @RequestMapping(value = "articleSearchDispTwelve", method = RequestMethod.GET)
    public String searchDispTwelve(ArticleSearchCriteria criteria,
            @PageableDefault(12)
            @SortDefault(sort = "article_id", direction = Direction.DESC) Pageable pageable,
            Model model) {

        Page<Article> page = articleService.getArticles(criteria, pageable);
        model.addAttribute("page", page);
        model.addAttribute("path", "articleSearchDispTwelve");
        return "pgnt/articleList";
    }

    @RequestMapping(value = "articleSearchDispTwenty", method = RequestMethod.GET)
    public String searchDispTwenty(ArticleSearchCriteria criteria, Pageable pageable, Model model) {

        Page<Article> page = articleService.getArticles(criteria, pageable);
        model.addAttribute("page", page);
        model.addAttribute("path", "articleSearchDispTwenty");
        return "pgnt/articleList";
    }

    @RequestMapping(value = "articleSearchSortSpecified", method = RequestMethod.GET)
    public String searchSortSpecified(ArticleSearchCriteria criteria,
            @PageableDefault(sort = "article_id") Pageable pageable, Model model) {

        Page<Article> page = articleService.getArticles(criteria, pageable);
        model.addAttribute("page", page);
        model.addAttribute("path", "articleSearchSortSpecified");
        return "pgnt/articleList";
    }
}
