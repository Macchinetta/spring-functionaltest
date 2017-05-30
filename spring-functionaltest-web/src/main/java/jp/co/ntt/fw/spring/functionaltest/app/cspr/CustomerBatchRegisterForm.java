/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.cspr;

import java.io.Serializable;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.validation.UploadFileRequired;

import org.springframework.web.multipart.MultipartFile;

public class CustomerBatchRegisterForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @UploadFileRequired
    private MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

}
