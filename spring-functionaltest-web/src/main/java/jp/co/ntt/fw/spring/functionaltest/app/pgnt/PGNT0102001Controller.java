/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.pgnt;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Celebrity;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt.CelebritySearchCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.service.pgnt.CelebrityService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PGNT0102001Controller {

    @Inject
    CelebrityService celebrityService;

    @ModelAttribute
    public CelebritySearchCriteria setUpForm() {
        CelebritySearchCriteria criteria = new CelebritySearchCriteria();
        return criteria;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String search(CelebritySearchCriteria criteria, Pageable pageable,
            Model model) {

        Page<Celebrity> page = celebrityService.getNames(criteria, pageable);
        model.addAttribute("page", page);
        model.addAttribute("path", "celebritySearchDispFive");
        return "pgnt/celebrityList";
    }
}
