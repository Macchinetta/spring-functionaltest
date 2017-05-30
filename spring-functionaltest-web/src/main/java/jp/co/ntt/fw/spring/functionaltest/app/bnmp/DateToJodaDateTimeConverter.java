/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.bnmp;

import java.util.Date;

import org.dozer.DozerConverter;
import org.joda.time.DateTime;

public class DateToJodaDateTimeConverter extends DozerConverter<Date, DateTime> {

    public DateToJodaDateTimeConverter() {
        super(Date.class, DateTime.class);
    }

    @Override
    public DateTime convertTo(Date source, DateTime destination) {
        return new DateTime(source);
    }

    @Override
    public Date convertFrom(DateTime source, Date destination) {
        return source.toDate();
    }
}
