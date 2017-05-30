/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.stpr;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.terasoluna.gfw.common.validator.constraints.Compare;

@Compare(left = "startIndex", right = "endIndex", operator = Compare.Operator.LESS_THAN_OR_EQUAL)
public class StringProcessing03Form implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(min = 3)
    private String targetValue;

    private String splitRegex;

    @NotNull
    @Min(0)
    private Integer startIndex;

    @NotNull
    @Min(0)
    private Integer endIndex;

    public String getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(String targetValue) {
        this.targetValue = targetValue;
    }

    public String getSplitRegex() {
        return splitRegex;
    }

    public void setSplitRegex(String splitRegex) {
        this.splitRegex = splitRegex;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

}
