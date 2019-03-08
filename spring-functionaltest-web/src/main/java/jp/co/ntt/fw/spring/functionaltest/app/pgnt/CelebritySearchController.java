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
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Celebrity;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt.CelebritySearchCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.service.pgnt.CelebrityService;

//@RequestMapping("pgnt")
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
