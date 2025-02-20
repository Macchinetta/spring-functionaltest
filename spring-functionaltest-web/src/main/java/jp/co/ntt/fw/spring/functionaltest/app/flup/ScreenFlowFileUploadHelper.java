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

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.message.ResultMessages;

import jp.co.ntt.fw.spring.functionaltest.domain.model.UploadFile;
import jp.co.ntt.fw.spring.functionaltest.domain.service.flup.FileUploadService;

@Component
public class ScreenFlowFileUploadHelper {

    @Inject
    TemporaryFilesHelper temporaryFilesHelper;

    @Inject
    FileUploadService fileUploadService;

    public String confirm(ScreenFlowUploadForm form) throws IOException {

        MultipartFile multipartFile = form.getMultipartFile();

        File temporaryFile = temporaryFilesHelper.saveTemporaryFile(multipartFile);

        form.setFileName(multipartFile.getOriginalFilename());
        form.setContentType(multipartFile.getContentType());

        return temporaryFile.getName();
    }

    public void uploadToDisc(ScreenFlowUploadForm form, String temporaryFileId,
            RedirectAttributes redirectAttributes) throws IOException {

        UploadFile newUploadFile = toUploadFile(form);

        UploadFile savedUploadFile =
                fileUploadService.saveFileToDisc(temporaryFileId, newUploadFile);

        UploadedContent uploadedFile = toUploadedContent(savedUploadFile);
        redirectAttributes.addFlashAttribute(uploadedFile);

        redirectAttributes.addFlashAttribute(ResultMessages.success().add("i.sf.fu.0002"));
    }

    public void uploadToDb(ScreenFlowUploadForm form, String temporaryFileId,
            RedirectAttributes redirectAttributes) throws IOException {

        UploadFile newUploadFile = toUploadFile(form);

        UploadFile savedUploadFile = fileUploadService.saveFileToDb(temporaryFileId, newUploadFile);

        UploadedContent uploadedFile = toUploadedContent(savedUploadFile);
        redirectAttributes.addFlashAttribute(uploadedFile);

        redirectAttributes.addFlashAttribute(ResultMessages.success().add("i.sf.fu.0002"));

    }

    private UploadFile toUploadFile(ScreenFlowUploadForm form) {
        UploadFile newUploadFile = new UploadFile();
        newUploadFile.setFileName(form.getFileName());
        newUploadFile.setContentType(form.getContentType());
        newUploadFile.setDescription(form.getDescription());
        return newUploadFile;
    }

    private UploadedContent toUploadedContent(UploadFile savedUploadFile) {
        UploadedContent uploadedContent = new UploadedContent();
        uploadedContent.setFileId(savedUploadFile.getFileId());
        uploadedContent.setFileName(savedUploadFile.getFileName());
        uploadedContent.setFileContent(savedUploadFile.getContent());
        uploadedContent.setDescription(savedUploadFile.getDescription());
        return uploadedContent;
    }

}
