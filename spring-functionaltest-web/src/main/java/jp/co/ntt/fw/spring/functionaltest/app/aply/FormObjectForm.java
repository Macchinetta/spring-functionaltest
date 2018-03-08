/*
 * Copyright 2014-2018 NTT Corporation.
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
 */
package jp.co.ntt.fw.spring.functionaltest.app.aply;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class FormObjectForm {
    private String initConfirmValue1;

    private String initConfirmValue2;

    @NumberFormat(pattern = "###,###")
    @NotNull
    private Integer value1;

    @NumberFormat(style = Style.NUMBER)
    private Integer value2;

    @NumberFormat(style = Style.CURRENCY)
    private Integer value3;

    @NumberFormat(style = Style.PERCENT)
    @NotNull
    private BigDecimal value4;

    @DateTimeFormat(pattern = "yyyyMMdd")
    private Date value5;

    private Long value6;

    private Long value7;

    public Integer getValue1() {
        return value1;
    }

    public void setValue1(Integer value1) {
        this.value1 = value1;
    }

    public Integer getValue2() {
        return value2;
    }

    public void setValue2(Integer value2) {
        this.value2 = value2;
    }

    public Integer getValue3() {
        return value3;
    }

    public void setValue3(Integer value3) {
        this.value3 = value3;
    }

    public BigDecimal getValue4() {
        return value4;
    }

    public void setValue4(BigDecimal value4) {
        this.value4 = value4;
    }

    public Date getValue5() {
        return value5;
    }

    public void setValue5(Date value5) {
        this.value5 = value5;
    }

    public Long getValue6() {
        return value6;
    }

    public void setValue6(Long value6) {
        this.value6 = value6;
    }

    public Long getValue7() {
        return value7;
    }

    public void setValue7(Long value7) {
        this.value7 = value7;
    }

    public String getInitConfirmValue1() {
        return initConfirmValue1;
    }

    public void setInitConfirmValue1(String initConfirmValue1) {
        this.initConfirmValue1 = initConfirmValue1;
    }

    public String getInitConfirmValue2() {
        return initConfirmValue2;
    }

    public void setInitConfirmValue2(String initConfirmValue2) {
        this.initConfirmValue2 = initConfirmValue2;
    }
}
