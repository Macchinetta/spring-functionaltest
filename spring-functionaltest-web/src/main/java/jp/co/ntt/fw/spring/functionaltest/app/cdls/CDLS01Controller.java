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

import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.ntt.fw.spring.functionaltest.domain.model.CodeList;
import jp.co.ntt.fw.spring.functionaltest.domain.service.cdls.CodeListService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.cdls.ReloadCodeListService;

@RequestMapping("cdls")
@Controller
public class CDLS01Controller {

    @Inject
    Mapper beanMapper;

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

    @RequestMapping(value = "0101/001", method = RequestMethod.GET)
    public String handle01001() {
        return "cdls/simpleMapCodeList";
    }

    @RequestMapping(value = "0101/002", method = RequestMethod.GET)
    public String handle01002(Model model) {
        return "cdls/simpleMapCodeList";
    }

    @RequestMapping(value = "0101/002", method = RequestMethod.POST, params = "post")
    public String handle01002Post(RedirectAttributes redirectAttrs,
            @Validated ClOrderStatusForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "cdls/simpleMapCodeList";
        }
        CodeList codeList = beanMapper.map(form, CodeList.class);

        redirectAttrs.addFlashAttribute("orderStatusCodeListValue",
                codeListService.getOrderStatusCodeListValue(codeList.getId()));
        return "redirect:002";
    }

    @RequestMapping(value = "0102/001", method = RequestMethod.GET)
    public String handle02001(Model model) {
        return "cdls/numberRangeCodeList";
    }

    @RequestMapping(value = "0102/002", method = RequestMethod.GET)
    public String handle02002(Model model) {
        return "cdls/numberRangeCodeList";
    }

    @RequestMapping(value = "0102/002", method = RequestMethod.POST, params = "post")
    public String handle02002Post(RedirectAttributes redirectAttrs,
            @Validated ClMonthAscForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "cdls/numberRangeCodeList";
        }
        CodeList codeList = beanMapper.map(form, CodeList.class);

        redirectAttrs.addFlashAttribute("monthAscCodeListValue", codeListService
                .getMonthAscCodeListValue(codeList.getId()));
        return "redirect:002";
    }

    @RequestMapping(value = "0103/001", method = RequestMethod.GET)
    public String handle03001(Model model) {
        return "cdls/jdbcCodeList";
    }

    @RequestMapping(value = "0103/002", method = RequestMethod.GET)
    public String handle03002(Model model) {
        return "cdls/jdbcCodeList";
    }

    @RequestMapping(value = "0103/002", method = RequestMethod.POST, params = "post")
    public String handle03002Post(RedirectAttributes redirectAttrs,
            @Validated ClAuthoritiesForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "cdls/jdbcCodeList";
        }
        CodeList codeList = beanMapper.map(form, CodeList.class);

        redirectAttrs.addFlashAttribute("authoritiesCodeListValue",
                codeListService.getAuthoritiesCodeListValue(codeList.getId()));
        return "redirect:002";
    }

    @RequestMapping(value = "0104/001", method = RequestMethod.GET)
    public String handle04001(Model model) {
        return "cdls/enumCodeList";
    }

    @RequestMapping(value = "0104/002", method = RequestMethod.GET)
    public String handle04002(Model model) {
        return "cdls/enumCodeList";
    }

    @RequestMapping(value = "0104/002", method = RequestMethod.POST, params = "post")
    public String handle04002Post(RedirectAttributes redirectAttrs,
            @Validated ClEnumOrderStatusForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "cdls/enumCodeList";
        }
        CodeList codeList = beanMapper.map(form, CodeList.class);
        redirectAttrs.addFlashAttribute("enumOrderStatusCodeListValue",
                codeListService.getEnumOrderStatusCodeListValue(codeList
                        .getId()));
        return "redirect:002";
    }

    @RequestMapping(value = "0106/001", method = RequestMethod.GET)
    public String handle06001(Model model) {
        ClOrderStatusForm orderStatusForm = new ClOrderStatusForm();
        orderStatusForm.setId("2");
        model.addAttribute("orderStatusForm", orderStatusForm);
        return "cdls/codeListValue";
    }

    @RequestMapping(value = "0107/001", method = RequestMethod.GET)
    public String handle07001(Model model) {
        return "cdls/validationCodeList";
    }

    @RequestMapping(value = "0107/001", method = RequestMethod.POST, params = "post")
    public String handle07001Post(Model model,
            @Validated ClOrderStatusForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "cdls/validationCodeList";
        }
        return "redirect:001?result";
    }

    @RequestMapping(value = "0107/001", method = RequestMethod.GET, params = "result")
    public String handle07001Result(Model model) {
        return "cdls/codeListSubmitSuccess";
    }

    @RequestMapping(value = "0107/002", method = RequestMethod.GET)
    public String handle07002(Model model) {
        return "cdls/validationCodeList";
    }

    @RequestMapping(value = "0107/002", method = RequestMethod.POST, params = "post")
    public String handle07002Post(Model model,
            @Validated ClOrderStatusForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "cdls/validationCodeList";
        }
        return "redirect:002?result";
    }

    @RequestMapping(value = "0107/002", method = RequestMethod.GET, params = "result")
    public String handle07002Result(Model model) {
        return "cdls/codeListSubmitSuccess";
    }

}
