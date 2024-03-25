/*
 * Copyright(c) 2024 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.app.cspr;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.cspr.CommitterCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.service.cspr.CommitterService;

@RequestMapping("jsp")
@Controller
public class CSPR_JSP_03Controller {

    @Inject
    MessageSource messageSource;

    @Inject
    CommitterService committerService;

    @Inject
    CSPRBeanMapper beanMapper;

    @ModelAttribute
    public CommitterCriteria setUpCriteria() {
        return new CommitterCriteria();
    }

    @ModelAttribute
    public CommitterForm setUpForm() {
        return new CommitterForm();
    }

    @GetMapping(value = "0301/001")
    public String handle0301001() {
        return "jsp/cspr/committerEdit";
    }

    @GetMapping(value = "0301/002")
    public String handle0301002() {
        return "jsp/cspr/committerList";
    }

    @GetMapping(value = "0301/003")
    public String handle0301003() {
        return "jsp/cspr/committerEdit";
    }

    @GetMapping(value = "0301/004")
    public String handle0301004() {
        return "jsp/cspr/committerList";
    }

}
