/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.flup;

import java.io.Serializable;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.validation.UploadFileAllowedExtention;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.validation.UploadFileMaxSize;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.validation.UploadFileNotEmpty;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.validation.UploadFileRequired;

import org.springframework.web.multipart.MultipartFile;

public class SingleUploadForm implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface UploadFileMaxSize250byte {
    }

    public interface UploadFileAllowJsp {
    }

    @UploadFileRequired
    @UploadFileNotEmpty
    @UploadFileMaxSize.List({
            @UploadFileMaxSize,
            @UploadFileMaxSize(value = 250, groups = UploadFileMaxSize250byte.class) })
    @UploadFileAllowedExtention.List({
            @UploadFileAllowedExtention("txt"),
            @UploadFileAllowedExtention(value = "jsp", groups = UploadFileAllowJsp.class) })
    private transient MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

}
