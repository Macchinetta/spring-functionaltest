/*
 * Copyright 2014-2017 NTT Corporation.
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
 *
 */
package jp.co.ntt.fw.spring.functionaltest.app.dnta;

import java.io.Serializable;

public class CompareDateForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer year1;

    private Integer month1;

    private Integer day1;

    private Integer year2;

    private Integer month2;

    private Integer day2;

    public Integer getYear1() {
        return year1;
    }

    public void setYear1(Integer year1) {
        this.year1 = year1;
    }

    public Integer getMonth1() {
        return month1;
    }

    public void setMonth1(Integer month1) {
        this.month1 = month1;
    }

    public Integer getDay1() {
        return day1;
    }

    public void setDay1(Integer day1) {
        this.day1 = day1;
    }

    public Integer getYear2() {
        return year2;
    }

    public void setYear2(Integer year2) {
        this.year2 = year2;
    }

    public Integer getMonth2() {
        return month2;
    }

    public void setMonth2(Integer month2) {
        this.month2 = month2;
    }

    public Integer getDay2() {
        return day2;
    }

    public void setDay2(Integer day2) {
        this.day2 = day2;
    }
}
