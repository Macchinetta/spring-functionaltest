/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dnta;

import java.io.Serializable;

public class CalcDateForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String inputDateTime;

    private Integer plus;

    private Integer minus;

    public String getInputDateTime() {
        return inputDateTime;
    }

    public void setInputDateTime(String inputDateTime) {
        this.inputDateTime = inputDateTime;
    }

    public Integer getPlus() {
        return plus;
    }

    public void setPlus(Integer plus) {
        this.plus = plus;
    }

    public Integer getMinus() {
        return minus;
    }

    public void setMinus(Integer minus) {
        this.minus = minus;
    }
}
