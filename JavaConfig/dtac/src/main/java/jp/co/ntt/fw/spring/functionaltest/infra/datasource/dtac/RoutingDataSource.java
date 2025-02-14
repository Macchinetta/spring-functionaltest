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
package jp.co.ntt.fw.spring.functionaltest.infra.datasource.dtac;

import java.time.LocalTime;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.terasoluna.gfw.common.time.ClockFactory;

public class RoutingDataSource extends AbstractRoutingDataSource { // (1)

    ClockFactory clockFactory;

    @Override
    protected Object determineCurrentLookupKey() {

        LocalTime localTime = LocalTime.now(clockFactory.fixed());
        int hour = localTime.getHour();

        return (7 <= hour && hour <= 23) ? "OPEN" : "CLOSE";
    }

    public void setClockFactory(ClockFactory clockFactory) {
        this.clockFactory = clockFactory;
    }
}
