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
package jp.co.ntt.fw.spring.functionaltest.domain.service.cdls;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import org.terasoluna.gfw.common.codelist.AbstractCodeList;
import org.terasoluna.gfw.common.time.ClockFactory;

public class DepYearCodeList extends AbstractCodeList {

    private ClockFactory clockFactory;

    public void setClockFactory(ClockFactory clockFactory) {
        this.clockFactory = clockFactory;
    }

    @Override
    public Map<String, String> asMap() {
        Clock clock = clockFactory.fixed();
        LocalDateTime localDateTime = LocalDateTime.now(clock);
        LocalDateTime nextYearDateTime = localDateTime.plusYears(1);

        Map<String, String> depYearMap = new LinkedHashMap<>();

        String thisYear = String.valueOf(localDateTime.getYear());
        String nextYear = String.valueOf(nextYearDateTime.getYear());

        depYearMap.put(thisYear, thisYear);
        depYearMap.put(nextYear, nextYear);

        return Collections.unmodifiableMap(depYearMap);
    }
}
