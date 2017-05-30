/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.flup;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.validation.UploadFileRequired;

public class ScreenFlowUploadForm implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface Confirm {
    }

    public interface Upload {
    }

    @UploadFileRequired(groups = Confirm.class)
    private transient MultipartFile multipartFile;

    @Size(max = 1024, groups = Upload.class)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotEmpty(groups = Upload.class)
    @Size(min = 1, max = 256, groups = Upload.class)
    private String fileName;

    @NotEmpty(groups = Upload.class)
    @Size(min = 1, max = 64, groups = Upload.class)
    private String contentType;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
