/*
 * Copyright 2014-2018 NTT Corporation.
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
 */
package jp.co.ntt.fw.spring.functionaltest.app.bnmp;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.DateMappingDto;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.ExcludeNullEmptyDto;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.ExcludeSpecifiedFieldDto;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.MappingFailedDto;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.ScopeMappingDto;

import org.dozer.Mapper;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("bnmp")
@Controller
public class BNMP03Controller {

    @Inject
    Mapper beanMapper;

    @RequestMapping(value = "0301/001", method = RequestMethod.GET)
    public String handle01001(Model model, ExcludeSpecifiedFieldForm form) {
        return "bnmp/excludeSpecifiedField";
    }

    @RequestMapping(value = "0302/001", method = RequestMethod.GET)
    public String handle02001(Model model, ScopeMappingForm form) {
        return "bnmp/setBeanScopeMapping";
    }

    @RequestMapping(value = "0303/001", method = RequestMethod.GET)
    public String handle03001(Model model, ExcludeNullEmptyForm form) {
        return "bnmp/excludeNullEmptyMapping";
    }

    @RequestMapping(value = "0304/001", method = RequestMethod.GET)
    public String handle04001(Model model, DateMappingForm form) {
        return "bnmp/stringToDateMapping";
    }

    @RequestMapping(value = "0305/001", method = RequestMethod.GET)
    public String handle05001(Model model, MappingFailedForm form) {
        return "bnmp/mappingFailed";
    }

    @RequestMapping(value = "beanMappingExtention", method = RequestMethod.POST, params = "fieldExclusions")
    public String handleFieldExclusions(Model model,
            ExcludeSpecifiedFieldForm form) {

        ExcludeSpecifiedFieldDto destinationBean = new ExcludeSpecifiedFieldDto();
        destinationBean.setFirstName("dummy");
        destinationBean.setLastName("dummy");
        destinationBean.setAge(99);
        destinationBean.setBirthDate(DateTimeFormat.forPattern("yyyyMMdd")
                .parseDateTime("99991231"));

        beanMapper.map(form, destinationBean);

        model.addAttribute("resultBean", destinationBean);

        return "bnmp/showBeanMappingExcludeResult";
    }

    @RequestMapping(value = "setBeanScopeMapping", method = RequestMethod.POST, params = "scopeSetting")
    public String handleSetBeanScopeMapping(Model model,
            ScopeMappingForm form) {
        // 範囲指定なし
        ScopeMappingDto destinationBean = new ScopeMappingDto();
        destinationBean.setFirstName("dummy");
        destinationBean.setLastName("dummy");
        destinationBean.setAge(99);
        destinationBean.setBirthDate(DateTimeFormat.forPattern("yyyyMMdd")
                .parseDateTime("99991231"));

        beanMapper.map(form, destinationBean);

        model.addAttribute("resultBean", destinationBean);

        // 範囲指定あり
        ScopeMappingDto destinationScopedBean = new ScopeMappingDto();
        destinationScopedBean.setFirstName("dummy");
        destinationScopedBean.setLastName("dummy");
        destinationScopedBean.setAge(99);
        destinationScopedBean.setBirthDate(DateTimeFormat.forPattern("yyyyMMdd")
                .parseDateTime("99991231"));

        beanMapper.map(form, destinationScopedBean, "mapIdExclude");

        model.addAttribute("resultScopedBean", destinationScopedBean);

        return "bnmp/showBeanMappingScopeResult";
    }

    @RequestMapping(value = "excludeNullEmptyMapping", method = RequestMethod.POST, params = "excludeNullEmpty")
    public String handleExcludeNullEmptyMapping(Model model,
            ExcludeNullEmptyForm form) {

        ExcludeNullEmptyDto destinationBean = new ExcludeNullEmptyDto();
        destinationBean.setFirstName("dummy");
        destinationBean.setLastName("dummy");
        destinationBean.setAge(99);
        destinationBean.setBirthDate(DateTimeFormat.forPattern("yyyyMMdd")
                .parseDateTime("99991231"));

        beanMapper.map(form, destinationBean);

        model.addAttribute("resultBean", destinationBean);

        return "bnmp/showBeanMappingExcludeResult";
    }

    @RequestMapping(value = "stringToDateMapping", method = RequestMethod.POST, params = "stringToDate")
    public String handleStringToDateMapping(Model model, DateMappingForm form) {

        DateMappingDto destinationBean = beanMapper.map(form,
                DateMappingDto.class);

        model.addAttribute("resultBean", destinationBean);

        return "bnmp/showBeanMappingStringToDateResult";
    }

    @RequestMapping(value = "mappingFailed", method = RequestMethod.POST, params = "mappingFailed")
    public String handleMappingFailed(Model model, MappingFailedForm form) {

        MappingFailedDto destinationBean = beanMapper.map(form,
                MappingFailedDto.class);

        model.addAttribute("resultBean", destinationBean);

        // マッピングじExceptio発生する為、以下URLはダミー
        return "bnmp/";
    }
}
