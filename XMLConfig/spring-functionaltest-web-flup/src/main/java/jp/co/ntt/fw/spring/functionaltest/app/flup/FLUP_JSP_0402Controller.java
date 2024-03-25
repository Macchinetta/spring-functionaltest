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
package jp.co.ntt.fw.spring.functionaltest.app.flup;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.inject.Inject;

/**
 * 本アプリでは、原則、大項目単位でcontrollerを作成するが、 ファイルアップロード機能では、中項目単位でweb.xmlのパラメータを変更する試験が存在するため、 統一して中項目ごとにController作成をしている。
 */
@RequestMapping("jsp/0402")
@Controller
public class FLUP_JSP_0402Controller {

    @Inject
    FileUploadHelper fileUploadHelper;

    @ModelAttribute
    public MultipleUploadForm setUpForm() {
        return new MultipleUploadForm();
    }

    @GetMapping(value = "001")
    public String handle001Form() {
        return form();
    }

    @PostMapping(value = "001")
    public String handle001Upload(@Validated MultipleUploadForm form,
            BindingResult result,
            RedirectAttributes redirectAttributes) throws IOException {
        return upload(form, result, redirectAttributes);
    }

    @GetMapping(value = "002")
    public String handle002Form() {
        return form();
    }

    @PostMapping(value = "002")
    public String handle002Upload(@Validated MultipleUploadForm form,
            BindingResult result,
            RedirectAttributes redirectAttributes) throws IOException {
        return upload(form, result, redirectAttributes);
    }

    @GetMapping(params = "complete")
    public String handleComplate() {
        return "jsp/flup/multiFileUploadComplete";
    }

    private String form() {
        return "jsp/flup/multipleFileUploadForm";
    }

    private String upload(@Validated MultipleUploadForm form,
            BindingResult result,
            RedirectAttributes redirectAttributes) throws IOException {
        if (result.hasErrors()) {
            return "jsp/flup/multipleFileUploadForm";
        }

        fileUploadHelper.bindToModel(form.getMultipartFiles(),
                redirectAttributes);

        return "redirect:/jsp/0402?complete";
    }

}
