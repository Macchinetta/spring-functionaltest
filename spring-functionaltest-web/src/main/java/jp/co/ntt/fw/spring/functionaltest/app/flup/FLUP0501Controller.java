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

import javax.inject.Inject;
import javax.validation.groups.Default;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception.InvalidRequestException;
import jp.co.ntt.fw.spring.functionaltest.app.flup.ScreenFlowUploadForm.Confirm;
import jp.co.ntt.fw.spring.functionaltest.app.flup.ScreenFlowUploadForm.Upload;

/**
 * 本アプリでは、原則、大項目単位でcontrollerを作成するが、 ファイルアップロード機能では、中項目単位でweb.xmlのパラメータを変更する試験が存在するため、
 * 統一して中項目ごとにController作成をしている。
 */
@RequestMapping("flup/0501")
@Controller
public class FLUP0501Controller {

    @Inject
    ScreenFlowFileUploadHelper screenFlowFileUploadHelper;

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

    @RequestMapping(value = "001", method = RequestMethod.GET)
    public String handle001Form() {
        return form();
    }

    @RequestMapping(value = "001", method = RequestMethod.POST, params = "confirm")
    public String handle001Confirm(
            @Validated({Confirm.class, Default.class}) ScreenFlowUploadForm form,
            ScreenFlowUploadSessionInfo screenFlowUploadSessionInfo, BindingResult result)
            throws IOException {
        return confirm(form, screenFlowUploadSessionInfo, result);
    }

    @RequestMapping(value = "001", method = RequestMethod.POST, params = "redo")
    public String handle001Redo(ScreenFlowUploadForm form) {
        return form();
    }

    @RequestMapping(value = "001", method = RequestMethod.POST)
    public String handle001Upload(
            @Validated({Upload.class, Default.class}) ScreenFlowUploadForm form,
            ScreenFlowUploadSessionInfo screenFlowUploadSessionInfo, BindingResult result,
            RedirectAttributes redirectAttributes) throws IOException {
        String temporaryFileId = screenFlowUploadSessionInfo.getTemporaryFileId();
        if (result.hasErrors() || !StringUtils.hasText(temporaryFileId)) {
            throw new InvalidRequestException(result.toString());
        }

        screenFlowFileUploadHelper.uploadToDisc(form, temporaryFileId, redirectAttributes);

        return "redirect:/flup/0501?complete";
    }

    @RequestMapping(value = "002", method = RequestMethod.GET)
    public String handle002Form() {
        return form();
    }

    @RequestMapping(value = "002", method = RequestMethod.POST, params = "confirm")
    public String handle002Confirm(
            @Validated({Confirm.class, Default.class}) ScreenFlowUploadForm form,
            ScreenFlowUploadSessionInfo screenFlowUploadSessionInfo, BindingResult result)
            throws IOException {
        return confirm(form, screenFlowUploadSessionInfo, result);
    }

    @RequestMapping(value = "002", method = RequestMethod.POST, params = "redo")
    public String handle002Redo(ScreenFlowUploadForm form) {
        return form();
    }

    @RequestMapping(value = "002", method = RequestMethod.POST)
    public String handle002Upload(
            @Validated({Upload.class, Default.class}) ScreenFlowUploadForm form,
            ScreenFlowUploadSessionInfo screenFlowUploadSessionInfo, BindingResult result,
            RedirectAttributes redirectAttributes) throws IOException {
        String temporaryFileId = screenFlowUploadSessionInfo.getTemporaryFileId();
        if (result.hasErrors() || !StringUtils.hasText(temporaryFileId)) {
            throw new InvalidRequestException(result.toString());
        }

        screenFlowFileUploadHelper.uploadToDb(form, temporaryFileId, redirectAttributes);

        return "redirect:/flup/0501?complete";
    }

    @RequestMapping(method = RequestMethod.GET, params = "complete")
    public String handleComplete(SessionStatus sessionStatus,
            ScreenFlowUploadSessionInfo screenFlowUploadSessionInfo) {
        sessionStatus.setComplete();
        screenFlowUploadSessionInfo.setTemporaryFileId("");
        return "flup/screenFlowFileUploadComplete";
    }

    private String form() {
        return "flup/screenFlowFileUploadForm";
    }

    private String confirm(ScreenFlowUploadForm form,
            ScreenFlowUploadSessionInfo screenFlowUploadSessionInfo, BindingResult result)
            throws IOException {
        if (result.hasErrors()) {
            return form();
        }

        String temporaryFileId = screenFlowFileUploadHelper.confirm(form);
        screenFlowUploadSessionInfo.setTemporaryFileId(temporaryFileId);
        return "flup/screenFlowFileUploadConfirm";
    }

}
