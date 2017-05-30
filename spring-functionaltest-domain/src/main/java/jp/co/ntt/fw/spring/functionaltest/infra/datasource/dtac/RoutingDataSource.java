/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.infra.datasource.dtac;

import org.joda.time.DateTime;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.terasoluna.gfw.common.date.jodatime.JodaTimeDateFactory;

public class RoutingDataSource extends AbstractRoutingDataSource { // (1)

    JodaTimeDateFactory dateFactory;

    @Override
    protected Object determineCurrentLookupKey() {

        DateTime dateTime = dateFactory.newDateTime();
        int hour = dateTime.getHourOfDay();

        if (7 <= hour && hour <= 23) {
            return "OPEN";
        } else {
            return "CLOSE";
        }
    }

    public void setDateFactory(JodaTimeDateFactory dateFactory) {
        this.dateFactory = dateFactory;
    }
}
