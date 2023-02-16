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
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.inject.Inject;
import jakarta.validation.groups.Default;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception.InvalidRequestException;
import jp.co.ntt.fw.spring.functionaltest.app.flup.ScreenFlowUploadForm.Confirm;
import jp.co.ntt.fw.spring.functionaltest.app.flup.ScreenFlowUploadForm.Upload;

/**
 * 本アプリでは、原則、大項目単位でcontrollerを作成するが、 ファイルアップロード機能では、中項目単位でweb.xmlのパラメータを変更する試験が存在するため、 統一して中項目ごとにController作成をしている。
 */
@RequestMapping("flup/0601")
@Controller
public class FLUP0601Controller {

    @Inject
    ScreenFlowFileUploadHelper screenFlowFileUploadHelper;

    @Inject
    TemporaryFilesCleaner temporaryFilesCleaner;

    @Inject
    ScreenFlowUploadSessionInfo screenFlowUploadSessionInfo;

    @ModelAttribute
    public ScreenFlowUploadForm setUpForm() {
        return new ScreenFlowUploadForm();
    }

    @ModelAttribute(value = "screenFlowUploadSessionInfo")
    public ScreenFlowUploadSessionInfo setUpScreenFlowUploadSessionInfo() {
        return screenFlowUploadSessionInfo;
    }

    @GetMapping(value = "001")
    public String handle001Form() {
        return form();
    }

    @PostMapping(value = "001", params = "confirm")
    public String handle001Confirm(@Validated({ Confirm.class,
            Default.class }) ScreenFlowUploadForm form, BindingResult result,
            ScreenFlowUploadSessionInfo screenFlowUploadSessionInfo,
            Model model) throws IOException {

        String confirmView = confirm(form, result, screenFlowUploadSessionInfo,
                model);

        return confirmView;
    }

    @PostMapping(value = "001", params = "redo")
    public String handle001Redo(ScreenFlowUploadForm form) {
        return form();
    }

    @PostMapping(value = "001")
    public String handle001Upload(@Validated({ Upload.class,
            Default.class }) ScreenFlowUploadForm form, BindingResult result,
            ScreenFlowUploadSessionInfo screenFlowUploadSessionInfo,
            RedirectAttributes redirectAttributes) throws IOException {
        return upload(form, result, screenFlowUploadSessionInfo,
                redirectAttributes);
    }

    @GetMapping(value = "002")
    public String handle002Form() {
        return form();
    }

    @PostMapping(value = "002", params = "confirm")
    public String handle002Confirm(@Validated({ Confirm.class,
            Default.class }) ScreenFlowUploadForm form, BindingResult result,
            ScreenFlowUploadSessionInfo screenFlowUploadSessionInfo,
            Model model) throws IOException {
        return confirm(form, result, screenFlowUploadSessionInfo, model);
    }

    @PostMapping(value = "002", params = "redo")
    public String handle002Redo(ScreenFlowUploadForm form) {
        return form();
    }

    @PostMapping(value = "002")
    public String handle002Upload(@Validated({ Upload.class,
            Default.class }) ScreenFlowUploadForm form, BindingResult result,
            ScreenFlowUploadSessionInfo screenFlowUploadSessionInfo,
            RedirectAttributes redirectAttributes) throws IOException {
        return upload(form, result, screenFlowUploadSessionInfo,
                redirectAttributes);
    }

    @GetMapping(params = "complete")
    public String handleComplete(SessionStatus sessionStatus,
            ScreenFlowUploadSessionInfo screenFlowUploadSessionInfo) {
        sessionStatus.setComplete();
        screenFlowUploadSessionInfo.setTemporaryFileId("");
        return "flup/screenFlowFileUploadComplete";
    }

    @GetMapping(value = "001", params = "enableScheduler")
    public String enableScheduler() throws IOException {

        // スケジューラを有効に。
        temporaryFilesCleaner.turnOnDeletion();

        return "flup/index";
    }

    private String form() {
        return "flup/screenFlowFileUploadForm";
    }

    private String confirm(ScreenFlowUploadForm form, BindingResult result,
            ScreenFlowUploadSessionInfo screenFlowUploadSessionInfo,
            Model model) throws IOException {
        if (result.hasErrors()) {
            return form();
        }

        String temporaryFileId = screenFlowFileUploadHelper.confirm(form);
        screenFlowUploadSessionInfo.setTemporaryFileId(temporaryFileId);
        model.addAttribute("displayConfirmTemporaryDirectoryButton", true);

        return "flup/screenFlowFileUploadConfirm";
    }

    private String upload(ScreenFlowUploadForm form, BindingResult result,
            ScreenFlowUploadSessionInfo screenFlowUploadSessionInfo,
            RedirectAttributes redirectAttributes) throws IOException {
        String temporaryFileId = screenFlowUploadSessionInfo
                .getTemporaryFileId();
        if (result.hasErrors() || !StringUtils.hasText(temporaryFileId)) {
            throw new InvalidRequestException(result.toString());
        }

        screenFlowFileUploadHelper.uploadToDisc(form, temporaryFileId,
                redirectAttributes);

        return "redirect:/flup/0601?complete";
    }

}
