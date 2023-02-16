/*
 * Copyright(c) 2014 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.app.stpr;

import java.io.Serializable;

import org.terasoluna.gfw.common.validator.constraints.Compare;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
