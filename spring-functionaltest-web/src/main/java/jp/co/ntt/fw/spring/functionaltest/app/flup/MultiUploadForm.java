/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.flup;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

public class MultiUploadForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Valid
    private List<SingleUploadForm> uploadForms;

    public List<SingleUploadForm> getUploadForms() {
        return uploadForms;
    }

    public void setUploadForms(List<SingleUploadForm> uploadForms) {
        this.uploadForms = uploadForms;
    }

}
