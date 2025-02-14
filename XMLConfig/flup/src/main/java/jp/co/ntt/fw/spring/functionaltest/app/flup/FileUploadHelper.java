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
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.message.ResultMessages;

@Component
public class FileUploadHelper {

    public void bindToModel(MultipartFile multipartFile, RedirectAttributes redirectAttributes)
            throws IOException {
        UploadedContent uploadedContent = new UploadedContent();
        uploadedContent.setFileContent(multipartFile.getInputStream());
        uploadedContent.setFileName(multipartFile.getOriginalFilename());
        redirectAttributes.addFlashAttribute(uploadedContent);
        redirectAttributes.addFlashAttribute(ResultMessages.success().add("i.sf.fu.0002"));
    }

    public void bindToModel(List<MultipartFile> multipartFiles,
            RedirectAttributes redirectAttributes) throws IOException {
        List<UploadedContent> uploadedContents = new ArrayList<UploadedContent>();
        for (MultipartFile multipartFile : multipartFiles) {
            UploadedContent uploadedContent = new UploadedContent();
            uploadedContent.setFileContent(multipartFile.getInputStream());
            uploadedContent.setFileName(multipartFile.getOriginalFilename());
            uploadedContents.add(uploadedContent);
        }
        redirectAttributes.addFlashAttribute("uploadedContents", uploadedContents);
        redirectAttributes.addFlashAttribute(ResultMessages.success().add("i.sf.fu.0002"));
    }

}
