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
@RequestMapping("/flup/0101")
@Controller
public class FLUP0101Controller {

    @Inject
    FileUploadHelper fileUploadHelper;

    @ModelAttribute
    public SingleUploadForm setUpForm() {
        return new SingleUploadForm();
    }

    @GetMapping(value = "001")
    public String handle001Form() {
        return "flup/singleFileUploadForm";
    }

    @PostMapping(value = "001")
    public String handle001Upload(@Validated SingleUploadForm form,
            BindingResult result,
            RedirectAttributes redirectAttributes) throws IOException {
        if (result.hasErrors()) {
            return handle001Form();
        }

        fileUploadHelper.bindToModel(form.getMultipartFile(),
                redirectAttributes);

        return "redirect:/flup/0101?complete";
    }

    @GetMapping(params = "complete")
    public String handl001Complate() {
        return "flup/singleFileUploadComplete";
    }

}
