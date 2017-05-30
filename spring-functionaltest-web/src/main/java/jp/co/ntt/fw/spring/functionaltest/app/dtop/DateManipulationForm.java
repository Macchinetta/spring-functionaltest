/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dtop;

import java.io.Serializable;

import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class DateManipulationForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate targetIncDecDate;

    private Integer targetIncreaseNum;

    private Integer targetDecreaseNum;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate targetStartEndDate;

    public LocalDate getTargetIncDecDate() {
        return targetIncDecDate;
    }

    public void setTargetIncDecDate(LocalDate targetIncDecDate) {
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

    public LocalDate getTargetStartEndDate() {
        return targetStartEndDate;
    }

    public void setTargetStartEndDate(LocalDate targetStartEndDate) {
        this.targetStartEndDate = targetStartEndDate;
    }
}
