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
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import java.io.Serializable;
import java.time.LocalDate;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

/**
 * Bean Validationのアノテーションを検証する。
 */
public class VariousSimpleValidationBVForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer notnull;

    @NotEmpty
    private String notempty;

    @NotBlank
    private String notblank;

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

    @Positive
    private int positive;

    @PositiveOrZero
    private int positiveorzero;

    @Negative
    private int negative;

    @NegativeOrZero
    private int negativeorzero;

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

    @FutureOrPresent
    private LocalDate futureorpresent;

    @Past
    private LocalDate past;

    @PastOrPresent
    private LocalDate pastorpresent;

    @Email
    private String email;

    public Integer getNotnull() {
        return notnull;
    }

    public void setNotnull(Integer notnull) {
        this.notnull = notnull;
    }

    public String getNotempty() {
        return notempty;
    }

    public void setNotempty(String notempty) {
        this.notempty = notempty;
    }

    public String getNotblank() {
        return notblank;
    }

    public void setNotblank(String notblank) {
        this.notblank = notblank;
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

    public int getPositive() {
        return positive;
    }

    public void setPositive(int positive) {
        this.positive = positive;
    }

    public int getPositiveorzero() {
        return positiveorzero;
    }

    public void setPositiveorzero(int positiveorzero) {
        this.positiveorzero = positiveorzero;
    }

    public int getNegative() {
        return negative;
    }

    public void setNegative(int negative) {
        this.negative = negative;
    }

    public int getNegativeorzero() {
        return negativeorzero;
    }

    public void setNegativeorzero(int negativeorzero) {
        this.negativeorzero = negativeorzero;
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

    public LocalDate getFutureorpresent() {
        return futureorpresent;
    }

    public void setFutureorpresent(LocalDate futureorpresent) {
        this.futureorpresent = futureorpresent;
    }

    public LocalDate getPast() {
        return past;
    }

    public void setPast(LocalDate past) {
        this.past = past;
    }

    public LocalDate getPastorpresent() {
        return pastorpresent;
    }

    public void setPastorpresent(LocalDate pastorpresent) {
        this.pastorpresent = pastorpresent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
