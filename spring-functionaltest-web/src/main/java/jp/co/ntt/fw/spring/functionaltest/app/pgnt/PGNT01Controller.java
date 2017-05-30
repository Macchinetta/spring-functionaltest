/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.pgnt;

import jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt.ArticleSearchCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt.CelebritySearchCriteria;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("pgnt")
@Controller
public class PGNT01Controller {

    @ModelAttribute
    public CelebritySearchCriteria setUpForm() {
        CelebritySearchCriteria criteria = new CelebritySearchCriteria();
        return criteria;
    }

    @ModelAttribute
    public ArticleSearchCriteria setUpArticleSearchCriteria() {
        ArticleSearchCriteria criteria = new ArticleSearchCriteria();
        return criteria;
    }

    @RequestMapping(value = "0101/001")
    public String handle0101001(Model model) {
        model.addAttribute("path", "celebritySearchDispTwenty");

        return "pgnt/celebrityList";
    }

    @RequestMapping(value = "0101/002")
    public String handle0101002(Model model) {
        model.addAttribute("path", "articleSearchDispTwenty");
        return "pgnt/articleList";
    }

    @RequestMapping(value = "0101/003")
    public String handle0101003(Model model) {
        model.addAttribute("path", "articleSearchDispTwenty");
        return "pgnt/articleList";
    }

    @RequestMapping(value = "0102/001")
    public String handle0102001(Model model) {
        model.addAttribute("path", "celebritySearchDispFive");
        return "pgnt/celebrityList";
    }

    @RequestMapping(value = "0102/002")
    public String handle0102002(Model model) {
        model.addAttribute("path", "articleSearchDescArticleId");
        return "pgnt/articleList";
    }
}
