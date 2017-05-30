/*
 * Copyright(c) 2014-2017 NTT Corporation.
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
