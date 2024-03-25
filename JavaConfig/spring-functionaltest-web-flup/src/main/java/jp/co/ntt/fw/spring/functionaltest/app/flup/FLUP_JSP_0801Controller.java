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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.message.ResultMessages;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.app.flup.SingleUploadForm.UploadFileAllowJsp;
import jp.co.ntt.fw.spring.functionaltest.domain.model.UploadFile;
import jp.co.ntt.fw.spring.functionaltest.domain.service.flup.FileUploadService;

/**
 * 本アプリでは、原則、大項目単位でcontrollerを作成するが、 ファイルアップロード機能では、中項目単位でweb.xmlのパラメータを変更する試験が存在するため、 統一して中項目ごとにController作成をしている。
 */
@RequestMapping("jsp/0801")
@Controller
public class FLUP_JSP_0801Controller {

    @Inject
    FileUploadService fileUploadService;

    @ModelAttribute
    public SingleUploadForm setUpForm() {
        return new SingleUploadForm();
    }

    @GetMapping(value = "001")
    public String handle001Form() {
        return "jsp/flup/directFileUploadForm";
    }

    @PostMapping(value = "001")
    public String handle001Upload(@Validated({
            UploadFileAllowJsp.class }) SingleUploadForm form,
            BindingResult result,
            RedirectAttributes redirectAttributes) throws IOException {

        if (result.hasErrors()) {
            return handle001Form();
        }

        MultipartFile multipartFile = form.getMultipartFile();
        UploadFile newUploadFile = new UploadFile();
        newUploadFile.setFileName(multipartFile.getOriginalFilename());
        newUploadFile.setContentType(multipartFile.getContentType());

        UploadFile savedUploadFile = fileUploadService.saveFileToDisc(
                multipartFile.getInputStream(), newUploadFile);

        UploadedContent uploadedContent = new UploadedContent();
        uploadedContent.setFileId(savedUploadFile.getFileId());
        uploadedContent.setFileName(savedUploadFile.getFileName());
        redirectAttributes.addFlashAttribute(uploadedContent);

        redirectAttributes.addFlashAttribute(ResultMessages.success().add(
                "i.sf.flup.0002"));

        return "redirect:/jsp/0801/001?complete";
    }

    @GetMapping(value = "001", params = "complete")
    public String handleComplete() {
        return "jsp/flup/directFileUploadComplete";
    }

}
