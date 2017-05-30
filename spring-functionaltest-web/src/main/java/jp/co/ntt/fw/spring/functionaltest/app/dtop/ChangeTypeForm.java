/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dtop;

import java.io.Serializable;

public class ChangeTypeForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String targetDate;

    public String getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }
}
