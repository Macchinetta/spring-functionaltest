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
package jp.co.ntt.fw.spring.functionaltest.app.cdls;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.CodeList;
import jp.co.ntt.fw.spring.functionaltest.domain.service.cdls.CodeListService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.cdls.ReloadCodeListService;

@RequestMapping("thymeleaf")
@Controller
public class CDLS_Thymeleaf_01Controller {

    @Inject
    CDLSBeanMapper beanMapper;

    @Inject
    CodeListService codeListService;

    @Inject
    ReloadCodeListService reloadCodeListService;

    @ModelAttribute
    public CodeListForm setUpCodeListForm() {
        return new CodeListForm();
    }

    @ModelAttribute
    public ClOrderStatusForm setUpCLOrderStausForm() {
        return new ClOrderStatusForm();
    }

    @ModelAttribute
    public ClMonthAscForm setUpCLMonthAscForm() {
        return new ClMonthAscForm();
    }

    @ModelAttribute
    public ClAuthoritiesForm setUpCLAuthoritiesForm() {
        return new ClAuthoritiesForm();
    }

    @ModelAttribute
    public ClEnumOrderStatusForm setUpCLEnumOrderStatusForm() {
        return new ClEnumOrderStatusForm();
    }

    @ModelAttribute
    public ClI18nDBPriceForm setUpCLI18nDBPriceForm() {
        return new ClI18nDBPriceForm();
    }

    @GetMapping(value = "0101/001")
    public String handle01001() {
        return "thymeleaf/cdls/simpleMapCodeList";
    }

    @GetMapping(value = "0101/002")
    public String handle01002(Model model) {
        return "thymeleaf/cdls/simpleMapCodeList";
    }

    @PostMapping(value = "0101/002", params = "post")
    public String handle01002Post(RedirectAttributes redirectAttrs,
            @Validated ClOrderStatusForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "thymeleaf/cdls/simpleMapCodeList";
        }
        CodeList codeList = beanMapper.map(form);

        redirectAttrs.addFlashAttribute("orderStatusCodeListValue",
                codeListService.getOrderStatusCodeListValue(codeList.getId()));
        return "redirect:002";
    }

    @GetMapping(value = "0102/001")
    public String handle02001(Model model) {
        return "thymeleaf/cdls/numberRangeCodeList";
    }

    @GetMapping(value = "0102/002")
    public String handle02002(Model model) {
        return "thymeleaf/cdls/numberRangeCodeList";
    }

    @PostMapping(value = "0102/002", params = "post")
    public String handle02002Post(RedirectAttributes redirectAttrs,
            @Validated ClMonthAscForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "thymeleaf/cdls/numberRangeCodeList";
        }
        CodeList codeList = beanMapper.map(form);

        redirectAttrs.addFlashAttribute("monthAscCodeListValue", codeListService
                .getMonthAscCodeListValue(codeList.getId()));
        return "redirect:002";
    }

    @GetMapping(value = "0103/001")
    public String handle03001(Model model) {
        return "thymeleaf/cdls/jdbcCodeList";
    }

    @GetMapping(value = "0103/002")
    public String handle03002(Model model) {
        return "thymeleaf/cdls/jdbcCodeList";
    }

    @PostMapping(value = "0103/002", params = "post")
    public String handle03002Post(RedirectAttributes redirectAttrs,
            @Validated ClAuthoritiesForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "thymeleaf/cdls/jdbcCodeList";
        }
        CodeList codeList = beanMapper.map(form);

        redirectAttrs.addFlashAttribute("authoritiesCodeListValue",
                codeListService.getAuthoritiesCodeListValue(codeList.getId()));
        return "redirect:002";
    }

    @GetMapping(value = "0104/001")
    public String handle04001(Model model) {
        return "thymeleaf/cdls/enumCodeList";
    }

    @GetMapping(value = "0104/002")
    public String handle04002(Model model) {
        return "thymeleaf/cdls/enumCodeList";
    }

    @PostMapping(value = "0104/002", params = "post")
    public String handle04002Post(RedirectAttributes redirectAttrs,
            @Validated ClEnumOrderStatusForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "thymeleaf/cdls/enumCodeList";
        }
        CodeList codeList = beanMapper.map(form);
        redirectAttrs.addFlashAttribute("enumOrderStatusCodeListValue",
                codeListService.getEnumOrderStatusCodeListValue(codeList
                        .getId()));
        return "redirect:002";
    }

    @GetMapping(value = "0106/001")
    public String handle06001(Model model) {
        ClOrderStatusForm orderStatusForm = new ClOrderStatusForm();
        orderStatusForm.setId("2");
        model.addAttribute("orderStatusForm", orderStatusForm);
        return "thymeleaf/cdls/codeListValue";
    }

    @GetMapping(value = "0107/001")
    public String handle07001(Model model) {
        return "thymeleaf/cdls/validationCodeList";
    }

    @PostMapping(value = "0107/001", params = "post")
    public String handle07001Post(Model model,
            @Validated ClOrderStatusForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "thymeleaf/cdls/validationCodeList";
        }
        return "redirect:001?result";
    }

    @GetMapping(value = "0107/001", params = "result")
    public String handle07001Result(Model model) {
        return "thymeleaf/cdls/codeListSubmitSuccess";
    }

    @GetMapping(value = "0107/002")
    public String handle07002(Model model) {
        return "thymeleaf/cdls/validationCodeList";
    }

    @PostMapping(value = "0107/002", params = "post")
    public String handle07002Post(Model model,
            @Validated ClOrderStatusForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "thymeleaf/cdls/validationCodeList";
        }
        return "redirect:002?result";
    }

    @GetMapping(value = "0107/002", params = "result")
    public String handle07002Result(Model model) {
        return "thymeleaf/cdls/codeListSubmitSuccess";
    }

}
