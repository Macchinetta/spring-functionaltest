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
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.format.annotation.DateTimeFormat;

public class DateToStringMappingForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss.SSS")
    private Date birthDate;

    private java.sql.Date birthDateSqlDate;

    private Time birthDateSqlTime;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss.SSS")
    private Calendar birthDateCalendar;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss.SSS")
    private GregorianCalendar birthDateGregorianCalendar;

    private Timestamp birthDateTimestamp;

    @DateTimeFormat(pattern = "uuuu/MM/dd")
    private LocalDate birthDateLocalDate;

    @DateTimeFormat(pattern = "HH:mm:ss.SSS")
    private LocalTime birthDateLocalTime;

    @DateTimeFormat(pattern = "uuuu/MM/dd HH:mm:ss.SSS")
    private LocalDateTime birthDateLocalDateTime;

    @DateTimeFormat(pattern = "HH:mm:ss.SSSZZZZZ")
    private OffsetTime birthDateOffsetTime;

    @DateTimeFormat(pattern = "uuuu/MM/dd HH:mm:ss.SSSZZZZZ")
    private OffsetDateTime birthDateOffsetDateTime;

    @DateTimeFormat(pattern = "uuuu/MM/dd HH:mm:ss.SSSZZZZZ")
    private ZonedDateTime birthDateZonedDateTime;

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public java.sql.Date getBirthDateSqlDate() {
        return birthDateSqlDate;
    }

    public void setBirthDateSqlDate(java.sql.Date birthDateSqlDate) {
        this.birthDateSqlDate = birthDateSqlDate;
    }

    public Time getBirthDateSqlTime() {
        return birthDateSqlTime;
    }

    public void setBirthDateSqlTime(Time birthDateSqlTime) {
        this.birthDateSqlTime = birthDateSqlTime;
    }

    public Calendar getBirthDateCalendar() {
        return birthDateCalendar;
    }

    public void setBirthDateCalendar(Calendar birthDateCalendar) {
        this.birthDateCalendar = birthDateCalendar;
    }

    public GregorianCalendar getBirthDateGregorianCalendar() {
        return birthDateGregorianCalendar;
    }

    public void setBirthDateGregorianCalendar(
            GregorianCalendar birthDateGregorianCalendar) {
        this.birthDateGregorianCalendar = birthDateGregorianCalendar;
    }

    public Timestamp getBirthDateTimestamp() {
        return birthDateTimestamp;
    }

    public void setBirthDateTimestamp(Timestamp birthDateTimestamp) {
        this.birthDateTimestamp = birthDateTimestamp;
    }

    public LocalDate getBirthDateLocalDate() {
        return birthDateLocalDate;
    }

    public void setBirthDateLocalDate(LocalDate birthDateLocalDate) {
        this.birthDateLocalDate = birthDateLocalDate;
    }

    public LocalTime getBirthDateLocalTime() {
        return birthDateLocalTime;
    }

    public void setBirthDateLocalTime(LocalTime birthDateLocalTime) {
        this.birthDateLocalTime = birthDateLocalTime;
    }

    public LocalDateTime getBirthDateLocalDateTime() {
        return birthDateLocalDateTime;
    }

    public void setBirthDateLocalDateTime(
            LocalDateTime birthDateLocalDateTime) {
        this.birthDateLocalDateTime = birthDateLocalDateTime;
    }

    public OffsetTime getBirthDateOffsetTime() {
        return birthDateOffsetTime;
    }

    public void setBirthDateOffsetTime(OffsetTime birthDateOffsetTime) {
        this.birthDateOffsetTime = birthDateOffsetTime;
    }

    public OffsetDateTime getBirthDateOffsetDateTime() {
        return birthDateOffsetDateTime;
    }

    public void setBirthDateOffsetDateTime(
            OffsetDateTime birthDateOffsetDateTime) {
        this.birthDateOffsetDateTime = birthDateOffsetDateTime;
    }

    public ZonedDateTime getBirthDateZonedDateTime() {
        return birthDateZonedDateTime;
    }

    public void setBirthDateZonedDateTime(
            ZonedDateTime birthDateZonedDateTime) {
        this.birthDateZonedDateTime = birthDateZonedDateTime;
    }
}
