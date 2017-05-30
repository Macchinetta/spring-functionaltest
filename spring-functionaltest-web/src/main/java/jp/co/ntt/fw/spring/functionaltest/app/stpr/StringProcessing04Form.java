/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.stpr;

import java.io.Serializable;

public class StringProcessing04Form implements Serializable {

    private static final long serialVersionUID = 1L;

    private String targetValue;

    private String normalizationForm;

    public String getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(String targetValue) {
        this.targetValue = targetValue;
    }

    public String getNormalizationForm() {
        return normalizationForm;
    }

    public void setNormalizationForm(String normalizationForm) {
        this.normalizationForm = normalizationForm;
    }

}
