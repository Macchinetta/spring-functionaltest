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
package jp.co.ntt.fw.spring.functionaltest.infra.datasource.dtac;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.terasoluna.gfw.common.time.ClockFactory;

public class ChangeTimeClockFactory implements ClockFactory {

    private static final ThreadLocal<Integer> hourOfDayThreadLocal = new ThreadLocal<>();

    public void setHourOfDay(int hourOfDay) {
        hourOfDayThreadLocal.set(hourOfDay);
    }

    public void removeHourOfDayThreadLocal() {
        hourOfDayThreadLocal.remove();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Clock fixed(ZoneId zone) {

        int hourOfDay = 10;

        if (hourOfDayThreadLocal.get() != null) {
            hourOfDay = hourOfDayThreadLocal.get();
        }

        // ThreadLocalに格納されている時間で返却する（RoutingDataSourceの時間切り替えの為のみのため、時間は重要ではない）
        Instant instant = ZonedDateTime.of(2015, 6, 24, hourOfDay, 0, 0, 0,
                zone).toInstant();
        return Clock.fixed(instant, zone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Clock tick(ZoneId zone) {
        throw new UnsupportedOperationException();
    }
}
