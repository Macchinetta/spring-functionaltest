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
package jp.co.ntt.fw.spring.functionaltest.domain.service.cdls;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.terasoluna.gfw.common.codelist.AbstractCodeList;
import org.terasoluna.gfw.common.date.jodatime.JodaTimeDateFactory;

public class DepYearCodeList extends AbstractCodeList {

    private JodaTimeDateFactory dateFactory;

    public void setDateFactory(JodaTimeDateFactory dateFactory) {
        this.dateFactory = dateFactory;
    }

    @Override
    public Map<String, String> asMap() {
        DateTime dateTime = dateFactory.newDateTime();
        DateTime nextYearDateTime = dateTime.plusYears(1);

        Map<String, String> depYearMap = new LinkedHashMap<>();

        String thisYear = dateTime.toString("Y");
        String nextYear = nextYearDateTime.toString("Y");
        depYearMap.put(thisYear, thisYear);
        depYearMap.put(nextYear, nextYear);

        return Collections.unmodifiableMap(depYearMap);
    }
}
