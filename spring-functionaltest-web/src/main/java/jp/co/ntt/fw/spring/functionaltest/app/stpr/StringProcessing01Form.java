/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.stpr;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class StringProcessing01Form implements Serializable {

    private static final long serialVersionUID = 1L;

    private String targetText;

    @Size(min = 1, max = 1)
    private String trimText;

    public String getTargetText() {
        return targetText;
    }

    public void setTargetText(String targetText) {
        this.targetText = targetText;
    }

    public String getTrimText() {
        return trimText;
    }

    public void setTrimText(String trimText) {
        this.trimText = trimText;
    }

}
