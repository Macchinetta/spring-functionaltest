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

public class DateToStringMappingDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String birthDate;

    private String birthDateSqlDate;

    private String birthDateSqlTime;

    private String birthDateCalendar;

    private String birthDateGregorianCalendar;

    private String birthDateTimestamp;

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

}
