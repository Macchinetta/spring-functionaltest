/*
 * Copyright(c) 2024 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.app.dtop;

import java.io.Serializable;

public class DateManipulationForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String targetIncDecDate;

    private Integer targetIncreaseNum;

    private Integer targetDecreaseNum;

    private String targetStartEndDate;

    public String getTargetIncDecDate() {
        return targetIncDecDate;
    }

    public void setTargetIncDecDate(String targetIncDecDate) {
        this.targetIncDecDate = targetIncDecDate;
    }

    public Integer getTargetIncreaseNum() {
        return targetIncreaseNum;
    }

    public void setTargetIncreaseNum(Integer targetIncreaseNum) {
        this.targetIncreaseNum = targetIncreaseNum;
    }

    public Integer getTargetDecreaseNum() {
        return targetDecreaseNum;
    }

    public void setTargetDecreaseNum(Integer targetDecreaseNum) {
        this.targetDecreaseNum = targetDecreaseNum;
    }

    public String getTargetStartEndDate() {
        return targetStartEndDate;
    }

    public void setTargetStartEndDate(String targetStartEndDate) {
        this.targetStartEndDate = targetStartEndDate;
    }
}
