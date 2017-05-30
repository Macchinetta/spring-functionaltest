/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.exhn;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.validation.UploadFileRequired;

import org.springframework.web.multipart.MultipartFile;

public class ArticleBatchRegisterForm implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface Confirm {
    }

    public interface Register {
    }

    @Size(min = 1, max = 50)
    private String title;

    @UploadFileRequired(groups = Confirm.class)
    private MultipartFile multipartFile;

    @NotNull(groups = Register.class)
    private String fileName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

}
