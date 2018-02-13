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
package jp.co.ntt.fw.spring.functionaltest.infra.datasource.dtac;

import org.joda.time.DateTime;
import org.terasoluna.gfw.common.date.jodatime.AbstractJodaTimeDateFactory;

public class FixedJodaTimeDateFactory extends AbstractJodaTimeDateFactory {

    private static final ThreadLocal<Integer> hourOfDayThreadLocal = new ThreadLocal<>();

    @Override
    public DateTime newDateTime() {

        int hourOfDay = 10;

        if (hourOfDayThreadLocal.get() != null) {
            hourOfDay = hourOfDayThreadLocal.get();
        }

        // RoutingDataSourceでの時間切り替えを実現するため、時間のみ設定変更可能とする。
        // 他はダミー。
        return new DateTime(2015, 6, 24, hourOfDay, 0);
    }

    public void setHourOfDay(int hourOfDay) {
        hourOfDayThreadLocal.set(hourOfDay);
    }

    public void removeHourOfDayThreadLocal() {
        hourOfDayThreadLocal.remove();
    }

}
