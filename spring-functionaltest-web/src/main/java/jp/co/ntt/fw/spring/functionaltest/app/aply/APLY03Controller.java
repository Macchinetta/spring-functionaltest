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
package jp.co.ntt.fw.spring.functionaltest.app.aply;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.terasoluna.gfw.common.time.ClockFactory;

import jakarta.inject.Inject;

@RequestMapping("aply")
@Controller
public class APLY03Controller {

    @Inject
    ClockFactory clockFactory;

    @GetMapping(value = "0301/001")
    public String handle01001(Model model) {
        return "aply/useCommonJsp";
    }

    @GetMapping(value = "0302/001")
    public String handle02001(Model model) {
        // モデルに格納される値
        model.addAttribute("valueUsingEL", "<font color='red'>Sample</font>");
        return "aply/showValueUsingELHtmlEscapeInModel";
    }

    @GetMapping(value = "0302/002")
    public String handle02002(Model model) {
        // モデルに格納される値
        model.addAttribute("valueUsingJSTL", "<font color='red'>Sample</font>");
        return "aply/showValueUsingJSTLHtmlEscapeInModel";
    }

    @GetMapping(value = "0302/003")
    public String handle02003(Model model) {
        // モデルに格納される値
        model.addAttribute("valueUsingNumberFormat", 3333.55);
        return "aply/showValueUsingNumberFormatInModel";
    }

    @GetMapping(value = "0302/004")
    public String handle02004(Model model) {
        // モデルに格納される値
        model.addAttribute("valueUsingDateFormat", Date.from(clockFactory
                .fixed().instant()));
        return "aply/showValueUsingDateFormatInModel";
    }

    @GetMapping(value = "0303/001")
    public String handle03001(Model model, JspForm form) {
        return "aply/inputDisplaySwitchingInModel";
    }

    @PostMapping(value = "0303/displaySwitching", params = "cIfFormat")
    public String handleInputDisplaySwitchingUsingCIf(Model model,
            JspForm form) {
        return "aply/showDisplaySwitchingUsingCIf";
    }

    @GetMapping(value = "0303/002")
    public String handle03002(Model model, JspForm form) {
        return "aply/inputDisplaySwitchingInModel";
    }

    @PostMapping(value = "0303/displaySwitching", params = "cChooseFormat")
    public String handleInputDisplaySwitchingUsingCChoose(Model model,
            JspForm form) {
        return "aply/showDisplaySwitchingUsingCChoose";
    }

    @GetMapping(value = "0304/001")
    public String handle04001(Model model, JspFormListForm form) {
        return "aply/inputCollectionInModel";
    }

    @PostMapping(value = "0304/collectionInModel", params = "collectionInModel")
    public String handleInputCollectionInModel(Model model,
            JspFormListForm form) {
        return "aply/showCollectionInModel";
    }

    @GetMapping(value = "0305/001")
    public String handle05001(Model model, JspForm form) {
        return "aply/inputFormObjectBindHtmlForm";
    }

    @PostMapping(value = "0305/bindFormObject", params = "bindFormObject")
    public String handleBindFormObject(Model model, JspForm form) {
        return "aply/showFormObjectBindHtmlForm";
    }
}
