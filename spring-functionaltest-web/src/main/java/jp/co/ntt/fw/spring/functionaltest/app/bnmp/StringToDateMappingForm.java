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
package jp.co.ntt.fw.spring.functionaltest.app.bnmp;

import java.io.Serializable;

public class StringToDateMappingForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String birthDate;

    private String birthDateSqlDate;

    private String birthDateSqlTime;

    private String birthDateCalendar;

    private String birthDateGregorianCalendar;

    private String birthDateTimestamp;

    private String birthDateLocalDate;

    private String birthDateLocalTime;

    private String birthDateLocalDateTime;

    private String birthDateOffsetTime;

    private String birthDateOffsetDateTime;

    private String birthDateZonedDateTime;

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthDateSqlDate() {
        return birthDateSqlDate;
    }

    public void setBirthDateSqlDate(String birthDateSqlDate) {
        this.birthDateSqlDate = birthDateSqlDate;
    }

    public String getBirthDateSqlTime() {
        return birthDateSqlTime;
    }

    public void setBirthDateSqlTime(String birthDateSqlTime) {
        this.birthDateSqlTime = birthDateSqlTime;
    }

    public String getBirthDateCalendar() {
        return birthDateCalendar;
    }

    public void setBirthDateCalendar(String birthDateCalendar) {
        this.birthDateCalendar = birthDateCalendar;
    }

    public String getBirthDateGregorianCalendar() {
        return birthDateGregorianCalendar;
    }

    public void setBirthDateGregorianCalendar(
            String birthDateGregorianCalendar) {
        this.birthDateGregorianCalendar = birthDateGregorianCalendar;
    }

    public String getBirthDateTimestamp() {
        return birthDateTimestamp;
    }

    public void setBirthDateTimestamp(String birthDateTimestamp) {
        this.birthDateTimestamp = birthDateTimestamp;
    }

    public String getBirthDateLocalDate() {
        return birthDateLocalDate;
    }

    public void setBirthDateLocalDate(String birthDateLocalDate) {
        this.birthDateLocalDate = birthDateLocalDate;
    }

    public String getBirthDateLocalTime() {
        return birthDateLocalTime;
    }

    public void setBirthDateLocalTime(String birthDateLocalTime) {
        this.birthDateLocalTime = birthDateLocalTime;
    }

    public String getBirthDateLocalDateTime() {
        return birthDateLocalDateTime;
    }

    public void setBirthDateLocalDateTime(String birthDateLocalDateTime) {
        this.birthDateLocalDateTime = birthDateLocalDateTime;
    }

    public String getBirthDateOffsetTime() {
        return birthDateOffsetTime;
    }

    public void setBirthDateOffsetTime(String birthDateOffsetTime) {
        this.birthDateOffsetTime = birthDateOffsetTime;
    }

    public String getBirthDateOffsetDateTime() {
        return birthDateOffsetDateTime;
    }

    public void setBirthDateOffsetDateTime(String birthDateOffsetDateTime) {
        this.birthDateOffsetDateTime = birthDateOffsetDateTime;
    }

    public String getBirthDateZonedDateTime() {
        return birthDateZonedDateTime;
    }

    public void setBirthDateZonedDateTime(String birthDateZonedDateTime) {
        this.birthDateZonedDateTime = birthDateZonedDateTime;
    }
}
