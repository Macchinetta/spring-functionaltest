/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.stpr;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class StringProcessing0201001Form implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer targetValueInt;

    private String format;

    public Integer getTargetValueInt() {
        return targetValueInt;
    }

    public void setTargetValueInt(Integer targetValueInt) {
        this.targetValueInt = targetValueInt;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}
