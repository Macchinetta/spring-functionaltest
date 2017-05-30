/*
 * Copyright(c) 2014-2017 NTT Corporation.
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

    public void bindToModel(MultipartFile multipartFile,
            RedirectAttributes redirectAttributes) throws IOException {
        UploadedContent uploadedContent = new UploadedContent();
        uploadedContent.setFileContent(multipartFile.getInputStream());
        uploadedContent.setFileName(multipartFile.getOriginalFilename());
        redirectAttributes.addFlashAttribute(uploadedContent);
        redirectAttributes.addFlashAttribute(ResultMessages.success().add(
                "i.sf.flup.0002"));
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
        redirectAttributes.addFlashAttribute("uploadedContents",
                uploadedContents);
        redirectAttributes.addFlashAttribute(ResultMessages.success().add(
                "i.sf.flup.0002"));
    }

}
