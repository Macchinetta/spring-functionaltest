/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dtop;

import java.io.Serializable;

public class GetDateAndTimeForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String targetTimeZone;

    public String getTargetTimeZone() {
        return targetTimeZone;
    }

    public void setTargetTimeZone(String targetTimeZone) {
        this.targetTimeZone = targetTimeZone;
    }
}
