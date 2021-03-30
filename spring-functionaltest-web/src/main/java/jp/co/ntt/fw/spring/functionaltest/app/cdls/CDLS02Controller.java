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
package jp.co.ntt.fw.spring.functionaltest.app.cdls;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.dozermapper.core.Mapper;

import jp.co.ntt.fw.spring.functionaltest.domain.model.CodeList;
import jp.co.ntt.fw.spring.functionaltest.domain.service.cdls.ReloadCodeListService;

@RequestMapping("cdls")
@Controller
public class CDLS02Controller {

    @Inject
    Mapper beanMapper;

    @Inject
    ReloadCodeListService orderStatusService;

    @ModelAttribute
    public UpdateCodeListForm setUpUpdateCodeListForm() {
        return new UpdateCodeListForm();
    }

    @RequestMapping(value = "0201/001", method = RequestMethod.GET)
    public String handle01001(Model model, UpdateCodeListForm form) {
        return "cdls/codeListReload";
    }

    @RequestMapping(value = "0201/001", method = RequestMethod.POST, params = "update")
    public String handle01001Update(
            @Validated UpdateCodeListForm updateCodeListForm,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cdls/codeListReload";
        }
        CodeList updateCodeList = beanMapper.map(updateCodeListForm,
                CodeList.class);
        orderStatusService.updateAuthorityTableValue(updateCodeList);

        return "redirect:001";
    }

    @RequestMapping(value = "0201/002", method = RequestMethod.GET)
    public String handle01002(Model model, UpdateCodeListForm form) {
        return "cdls/codeListReload";
    }

    @RequestMapping(value = "0201/002", method = RequestMethod.POST, params = "update")
    public String handle01002Update(
            @Validated UpdateCodeListForm updateCodeListForm,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cdls/codeListReload";
        }
        CodeList updateCodeList = beanMapper.map(updateCodeListForm,
                CodeList.class);
        orderStatusService.updateAuthorityTableValue(updateCodeList);

        return "redirect:002";
    }

    @RequestMapping(value = "0201/002", method = RequestMethod.GET, params = "refresh")
    public String handle01002Refresh(Model model, UpdateCodeListForm form) {
        orderStatusService.refresh();
        return "cdls/codeListReload";
    }
}
