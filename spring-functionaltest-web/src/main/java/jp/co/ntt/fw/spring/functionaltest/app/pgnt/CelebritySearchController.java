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
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("pgnt")
@Controller
public class CelebritySearchController {

    @Inject
    CelebrityService celebrityService;

    @ModelAttribute
    public CelebritySearchCriteria setUpForm() {
        CelebritySearchCriteria criteria = new CelebritySearchCriteria();
        return criteria;
    }

    @RequestMapping(value = "celebritySearch", method = RequestMethod.GET)
    public String search(CelebritySearchCriteria criteria,
            @PageableDefault Pageable pageable, Model model) {

        Page<Celebrity> page = celebrityService.getNames(criteria, pageable);
        model.addAttribute("page", page);
        model.addAttribute("path", "celebritySearch");
        return "pgnt/celebrityList";
    }

    @RequestMapping(value = "celebritySearchDispTwenty", method = RequestMethod.GET)
    public String searchDispTwenty(CelebritySearchCriteria criteria,
            Pageable pageable, Model model) {

        Page<Celebrity> page = celebrityService.getNames(criteria, pageable);
        model.addAttribute("page", page);
        model.addAttribute("path", "celebritySearchDispTwenty");
        return "pgnt/celebrityList";
    }

}
