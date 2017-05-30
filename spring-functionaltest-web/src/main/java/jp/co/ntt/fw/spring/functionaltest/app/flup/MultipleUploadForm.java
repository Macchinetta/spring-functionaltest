/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.flup;

import java.io.Serializable;
import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.validation.UploadFileNotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class MultipleUploadForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @UploadFileNotEmpty
    private transient List<MultipartFile> multipartFiles;

    public List<MultipartFile> getMultipartFiles() {
        return multipartFiles;
    }

    public void setMultipartFiles(List<MultipartFile> multipartFiles) {
        this.multipartFiles = multipartFiles;
    }

}
