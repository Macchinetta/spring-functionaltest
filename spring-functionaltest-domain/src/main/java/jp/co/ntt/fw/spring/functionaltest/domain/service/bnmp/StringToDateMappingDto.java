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
package jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class StringToDateMappingDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date birthDate;

    private java.sql.Date birthDateSqlDate;

    private Time birthDateSqlTime;

    private Calendar birthDateCalendar;

    private GregorianCalendar birthDateGregorianCalendar;

    private Timestamp birthDateTimestamp;

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
}
