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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.CodeList;
import jp.co.ntt.fw.spring.functionaltest.domain.service.cdls.ReloadCodeListService;

@RequestMapping("cdls")
@Controller
public class CDLS02Controller {

    @Inject
    CDLSBeanMapper beanMapper;

    @Inject
    ReloadCodeListService orderStatusService;

    @ModelAttribute
    public UpdateCodeListForm setUpUpdateCodeListForm() {
        return new UpdateCodeListForm();
    }

    @GetMapping(value = "0201/001")
    public String handle01001(Model model, UpdateCodeListForm form) {
        return "cdls/codeListReload";
    }

    @PostMapping(value = "0201/001", params = "update")
    public String handle01001Update(
            @Validated UpdateCodeListForm updateCodeListForm,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cdls/codeListReload";
        }
        CodeList updateCodeList = beanMapper.map(updateCodeListForm);
        orderStatusService.updateAuthorityTableValue(updateCodeList);

        return "redirect:001";
    }

    @GetMapping(value = "0201/002")
    public String handle01002(Model model, UpdateCodeListForm form) {
        return "cdls/codeListReload";
    }

    @PostMapping(value = "0201/002", params = "update")
    public String handle01002Update(
            @Validated UpdateCodeListForm updateCodeListForm,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cdls/codeListReload";
        }
        CodeList updateCodeList = beanMapper.map(updateCodeListForm);
        orderStatusService.updateAuthorityTableValue(updateCodeList);

        return "redirect:002";
    }

    @GetMapping(value = "0201/002", params = "refresh")
    public String handle01002Refresh(Model model, UpdateCodeListForm form) {
        orderStatusService.refresh();
        return "cdls/codeListReload";
    }
}
