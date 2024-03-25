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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.message.ResultMessages;

import jakarta.inject.Inject;

/**
 * 本アプリでは、原則、大項目単位でcontrollerを作成するが、 ファイルアップロード機能では、中項目単位でweb.xmlのパラメータを変更する試験が存在するため、 統一して中項目ごとにController作成をしている。
 */
@RequestMapping("flup/temporaryFiles")
@Controller
public class FLUPTemporaryFilesController {

    @Inject
    TemporaryFilesHelper temporaryFilesHelper;

    @GetMapping(params = "list")
    public String list(@Validated ScreenFlowUploadForm form,
            BindingResult result, Model model) {
        temporaryFilesHelper.bindTemporaryFileToModel(model);
        return "jsp/flup/temporaryFileUploadList";
    }

    @PostMapping(params = "delete")
    public String delete(
            RedirectAttributes redirectAttributes) throws IOException {
        temporaryFilesHelper.clearTemporarydirectory();
        redirectAttributes.addFlashAttribute(ResultMessages.success().add(
                "i.sf.flup.0003"));
        return "redirect:/flup/temporaryFiles?complete";
    }

    @GetMapping(params = "complete")
    public String delete(Model model) {
        return "jsp/flup/temporaryFileUploadList";
    }

}
