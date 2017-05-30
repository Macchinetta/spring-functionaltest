/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.pgnt;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Article;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt.ArticleSearchCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.service.pgnt.ArticleService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PGNT0102002Controller {

    @Inject
    ArticleService articleService;

    @ModelAttribute
    public ArticleSearchCriteria setUpForm() {
        ArticleSearchCriteria criteria = new ArticleSearchCriteria();
        return criteria;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String search(ArticleSearchCriteria criteria, Pageable pageable,
            Model model) {

        Page<Article> page = articleService.getArticles(criteria, pageable);
        model.addAttribute("page", page);
        model.addAttribute("path", "articleSearchDescArticleId");
        return "pgnt/articleList";
    }
}
