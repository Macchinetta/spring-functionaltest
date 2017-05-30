/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dnta;

import java.io.Serializable;

public class CompareTimeForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer hour1;

    private Integer minute1;

    private Integer second1;

    private Integer hour2;

    private Integer minute2;

    private Integer second2;

    public Integer getHour1() {
        return hour1;
    }

    public void setHour1(Integer hour1) {
        this.hour1 = hour1;
    }

    public Integer getMinute1() {
        return minute1;
    }

    public void setMinute1(Integer minute1) {
        this.minute1 = minute1;
    }

    public Integer getSecond1() {
        return second1;
    }

    public void setSecond1(Integer second1) {
        this.second1 = second1;
    }

    public Integer getHour2() {
        return hour2;
    }

    public void setHour2(Integer hour2) {
        this.hour2 = hour2;
    }

    public Integer getMinute2() {
        return minute2;
    }

    public void setMinute2(Integer minute2) {
        this.minute2 = minute2;
    }

    public Integer getSecond2() {
        return second2;
    }

    public void setSecond2(Integer second2) {
        this.second2 = second2;
    }
}
