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
package jp.co.ntt.fw.spring.functionaltest.app.cspr;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.github.dozermapper.core.Mapper;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.bean.ErrorResults;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Committer;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.cspr.CommitterCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.service.cspr.CommitterService;

@RequestMapping("cspr")
@Controller
public class CSPR03Controller {

    @Inject
    MessageSource messageSource;

    @Inject
    CommitterService committerService;

    @Inject
    Mapper beanMapper;

    @ModelAttribute
    public CommitterCriteria setUpCriteria() {
        return new CommitterCriteria();
    }

    @ModelAttribute
    public CommitterForm setUpForm() {
        return new CommitterForm();
    }

    @RequestMapping(value = "0301/001")
    public String handle0301001() {
        return "cspr/committerEdit";
    }

    @RequestMapping(value = "0301/002")
    public String handle0301002() {
        return "cspr/committerList";
    }

    @RequestMapping(value = "0301/003")
    public String handle0301003() {
        return "cspr/committerEdit";
    }

    @RequestMapping(value = "0301/004")
    public String handle0301004() {
        return "cspr/committerList";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ProfileResult edit(@Validated CommitterForm form,
            Principal principal, Locale locale) {

        Committer committer = new Committer();
        beanMapper.map(form, committer);
        committer.setUsername(principal.getName());

        committerService.update(committer);

        ProfileResult result = new ProfileResult();

        result.setMessages(Arrays.asList(messageSource.getMessage(
                "i.sf.cspr.0001", null, locale)));

        return result;
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public CommittersResult search(CommitterCriteria criteria) {

        List<Committer> committers = committerService.getCommitters(criteria);

        CommittersResult result = new CommittersResult();
        result.setCommitters(committers);

        return result;
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResults handleBindException(BindException e, Locale locale) {
        ErrorResults result = new ErrorResults();

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            result.add(fieldError.getCode(), messageSource.getMessage(
                    fieldError, locale), fieldError.getField());
        }

        return result;
    }

    @RequestMapping(value = "0301", params = "retrunToIndex")
    public String retrunToIndex() {

        return "cspr/index";
    }
}
