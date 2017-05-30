/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.stpr;

import java.io.Serializable;

public class StringProcessing0201002Form implements Serializable {

    private static final long serialVersionUID = 1L;

    private String targetValueStr;

    private String format;

    public String getTargetValueStr() {
        return targetValueStr;
    }

    public void setTargetValueStr(String targetValueStr) {
        this.targetValueStr = targetValueStr;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}
