/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.infra.spring.data;

import java.util.Calendar;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.stereotype.Component;
import org.terasoluna.gfw.common.date.jodatime.JodaTimeDateFactory;

@Component
public class AuditDateTimeProvider implements DateTimeProvider {

    @Inject
    JodaTimeDateFactory dateFactory;

    @Override
    public Calendar getNow() {
        DateTime currentDateTime = dateFactory.newDateTime();
        return currentDateTime.toGregorianCalendar();
    }

}
