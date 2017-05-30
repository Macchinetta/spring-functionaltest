/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.pgnt;

import jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt.ArticleSearchCriteria;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("pgnt")
@Controller
public class PGNT02Controller {

    @ModelAttribute
    public ArticleSearchCriteria setUpArticleSearchCriteria() {
        ArticleSearchCriteria criteria = new ArticleSearchCriteria();
        return criteria;
    }

    @RequestMapping(value = "0202/001")
    public String handle0202001(Model model) {
        model.addAttribute("path", "articleSearch");
        return "pgnt/articleList";
    }

    @RequestMapping(value = "0202/002")
    public String handle0202002(Model model) {
        model.addAttribute("path", "articleSearchDispTen");
        return "pgnt/articleList";
    }

    @RequestMapping(value = "0202/003")
    public String handle0202003(Model model) {
        model.addAttribute("path", "articleSearchDispEleven");
        return "pgnt/articleList";
    }

    @RequestMapping(value = "0202/004")
    public String handle0202004(Model model) {
        model.addAttribute("path", "articleSearchDispTwelve");
        return "pgnt/articleList";
    }

    @RequestMapping(value = "0202/005")
    public String handle0202005(Model model) {
        model.addAttribute("path", "articleSearchSortSpecified");
        return "pgnt/articleList";
    }
}
