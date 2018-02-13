/*
 * Copyright 2014-2017 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
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
