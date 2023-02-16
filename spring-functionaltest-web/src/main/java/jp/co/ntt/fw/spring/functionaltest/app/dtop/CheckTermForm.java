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
package jp.co.ntt.fw.spring.functionaltest.app.dtop;

import java.io.Serializable;

public class CheckTermForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String targetTermFrom;

    private String targetTermTo;

    private String targetCheckTermFrom;

    private String targetCheckTermTo;

    private String targetCheckDate;

    private String targetIncDecDate;

    private Integer targetIncreaseMonthNum;

    private Integer targetDecreaseMonthNum;

    private Integer targetIncreaseDayNum;

    private Integer targetDecreaseDayNum;

    private String targetStartEndDate;

    public String getTargetTermFrom() {
        return targetTermFrom;
    }

    public void setTargetTermFrom(String targetTermFrom) {
        this.targetTermFrom = targetTermFrom;
    }

    public String getTargetTermTo() {
        return targetTermTo;
    }

    public void setTargetTermTo(String targetTermTo) {
        this.targetTermTo = targetTermTo;
    }

    public String getTargetCheckTermFrom() {
        return targetCheckTermFrom;
    }

    public void setTargetCheckTermFrom(String targetCheckTermFrom) {
        this.targetCheckTermFrom = targetCheckTermFrom;
    }

    public String getTargetCheckTermTo() {
        return targetCheckTermTo;
    }

    public void setTargetCheckTermTo(String targetCheckTermTo) {
        this.targetCheckTermTo = targetCheckTermTo;
    }

    public String getTargetCheckDate() {
        return targetCheckDate;
    }

    public void setTargetCheckDate(String targetCheckDate) {
        this.targetCheckDate = targetCheckDate;
    }

    public String getTargetIncDecDate() {
        return targetIncDecDate;
    }

    public void setTargetIncDecDate(String targetIncDecDate) {
        this.targetIncDecDate = targetIncDecDate;
    }

    public Integer getTargetIncreaseMonthNum() {
        return targetIncreaseMonthNum;
    }

    public void setTargetIncreaseMonthNum(Integer targetIncreaseMonthNum) {
        this.targetIncreaseMonthNum = targetIncreaseMonthNum;
    }

    public Integer getTargetDecreaseMonthNum() {
        return targetDecreaseMonthNum;
    }

    public void setTargetDecreaseMonthNum(Integer targetDecreaseMonthNum) {
        this.targetDecreaseMonthNum = targetDecreaseMonthNum;
    }

    public Integer getTargetIncreaseDayNum() {
        return targetIncreaseDayNum;
    }

    public void setTargetIncreaseDayNum(Integer targetIncreaseDayNum) {
        this.targetIncreaseDayNum = targetIncreaseDayNum;
    }

    public Integer getTargetDecreaseDayNum() {
        return targetDecreaseDayNum;
    }

    public void setTargetDecreaseDayNum(Integer targetDecreaseDayNum) {
        this.targetDecreaseDayNum = targetDecreaseDayNum;
    }

    public String getTargetStartEndDate() {
        return targetStartEndDate;
    }

    public void setTargetStartEndDate(String targetStartEndDate) {
        this.targetStartEndDate = targetStartEndDate;
    }
}
