/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.soap;

import java.io.Serializable;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.validation.UploadFileNotEmpty;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.validation.UploadFileRequired;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @UploadFileRequired
    @UploadFileNotEmpty
    private transient MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

}
