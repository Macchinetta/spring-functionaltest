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
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.io.Serializable;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import org.joda.time.LocalDate;

public class VariousSimpleValidationForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer notnull;

    @Null
    private String nullvalue;

    @Pattern(regexp = "\\d{6}")
    private String pattern;

    @Min(100)
    private Integer min;

    @Max(100)
    private Integer max;

    @DecimalMin("0.0")
    private Float decimalmin;

    @DecimalMax("99999.99")
    private Float decimalmax;

    @Size(min = 2, max = 3)
    private String size;

    @Digits(integer = 6, fraction = 2)
    private Float digits;

    @AssertTrue
    private boolean truevalue;

    @AssertFalse
    private boolean falsevalue;

    @Future
    private LocalDate future;

    @Past
    private LocalDate past;

    @CreditCardNumber
    private String creditcardnumber;

    @Email
    private String email;

    @URL
    private String url;

    @NotBlank
    private String notblank;

    @NotEmpty
    private String notempty;

    public Integer getNotnull() {
        return notnull;
    }

    public void setNotnull(Integer notnull) {
        this.notnull = notnull;
    }

    public String getNullvalue() {
        return nullvalue;
    }

    public void setNullvalue(String nullvalue) {
        this.nullvalue = nullvalue;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Float getDecimalmin() {
        return decimalmin;
    }

    public void setDecimalmin(Float decimalmin) {
        this.decimalmin = decimalmin;
    }

    public Float getDecimalmax() {
        return decimalmax;
    }

    public void setDecimalmax(Float decimalmax) {
        this.decimalmax = decimalmax;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Float getDigits() {
        return digits;
    }

    public void setDigits(Float digits) {
        this.digits = digits;
    }

    public boolean isTruevalue() {
        return truevalue;
    }

    public void setTruevalue(boolean truevalue) {
        this.truevalue = truevalue;
    }

    public boolean isFalsevalue() {
        return falsevalue;
    }

    public void setFalsevalue(boolean falsevalue) {
        this.falsevalue = falsevalue;
    }

    public LocalDate getFuture() {
        return future;
    }

    public void setFuture(LocalDate future) {
        this.future = future;
    }

    public LocalDate getPast() {
        return past;
    }

    public void setPast(LocalDate past) {
        this.past = past;
    }

    public String getCreditcardnumber() {
        return creditcardnumber;
    }

    public void setCreditcardnumber(String creditcardnumber) {
        this.creditcardnumber = creditcardnumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNotblank() {
        return notblank;
    }

    public void setNotblank(String notblank) {
        this.notblank = notblank;
    }

    public String getNotempty() {
        return notempty;
    }

    public void setNotempty(String notempty) {
        this.notempty = notempty;
    }
}
