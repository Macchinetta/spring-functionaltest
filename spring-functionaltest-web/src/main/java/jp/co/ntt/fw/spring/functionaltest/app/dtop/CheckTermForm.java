/*
 * Copyright 2014-2017 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package jp.co.ntt.fw.spring.functionaltest.app.dtop;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class CheckTermForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private DateTime targetTermFrom;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private DateTime targetTermTo;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private DateTime targetCheckTermFrom;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private DateTime targetCheckTermTo;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private DateTime targetCheckDate;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private DateTime targetIncDecDate;

    private Integer targetIncreaseMonthNum;

    private Integer targetDecreaseMonthNum;

    private Integer targetIncreaseDayNum;

    private Integer targetDecreaseDayNum;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private DateTime targetStartEndDate;

    public DateTime getTargetTermFrom() {
        return targetTermFrom;
    }

    public void setTargetTermFrom(DateTime targetTermFrom) {
        this.targetTermFrom = targetTermFrom;
    }

    public DateTime getTargetTermTo() {
        return targetTermTo;
    }

    public void setTargetTermTo(DateTime targetTermTo) {
        this.targetTermTo = targetTermTo;
    }

    public DateTime getTargetCheckTermFrom() {
        return targetCheckTermFrom;
    }

    public void setTargetCheckTermFrom(DateTime targetCheckTermFrom) {
        this.targetCheckTermFrom = targetCheckTermFrom;
    }

    public DateTime getTargetCheckTermTo() {
        return targetCheckTermTo;
    }

    public void setTargetCheckTermTo(DateTime targetCheckTermTo) {
        this.targetCheckTermTo = targetCheckTermTo;
    }

    public DateTime getTargetCheckDate() {
        return targetCheckDate;
    }

    public void setTargetCheckDate(DateTime targetCheckDate) {
        this.targetCheckDate = targetCheckDate;
    }

    public DateTime getTargetIncDecDate() {
        return targetIncDecDate;
    }

    public void setTargetIncDecDate(DateTime targetIncDecDate) {
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

    public DateTime getTargetStartEndDate() {
        return targetStartEndDate;
    }

    public void setTargetStartEndDate(DateTime targetStartEndDate) {
        this.targetStartEndDate = targetStartEndDate;
    }
}
