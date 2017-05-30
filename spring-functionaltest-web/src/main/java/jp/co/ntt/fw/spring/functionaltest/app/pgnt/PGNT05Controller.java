/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.pgnt;

import jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt.CelebritySearchCriteria;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("pgnt")
@Controller
public class PGNT05Controller {

    @ModelAttribute
    public CelebritySearchCriteria setUpForm() {
        CelebritySearchCriteria criteria = new CelebritySearchCriteria();
        return criteria;
    }

    @RequestMapping(value = "0502/001")
    public String handle0502001(Model model) {
        model.addAttribute("path", "celebritySearch");
        return "pgnt/celebrityList";
    }

    @RequestMapping(value = "0502/002")
    public String handle0502002(Model model) {
        model.addAttribute("path", "celebritySearch");
        return "pgnt/celebrityList";
    }

    @RequestMapping(value = "0502/003")
    public String handle0502003(Model model) {
        model.addAttribute("path", "celebritySearch");
        return "pgnt/celebrityList";
    }

}
