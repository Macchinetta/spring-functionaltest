/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.bnmp;

import org.dozer.DozerConverter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class StringToJodaDateTimeConverter extends
                                          DozerConverter<String, DateTime> {

    public StringToJodaDateTimeConverter() {
        super(String.class, DateTime.class);
    }

    @Override
    public DateTime convertTo(String source, DateTime destination) {
        DateTimeFormatter fomatter = DateTimeFormat.forPattern("yyyyMMdd");
        return fomatter.parseDateTime(source);
    }

    @Override
    public String convertFrom(DateTime source, String destination) {
        return source.toString("yyyyMMdd");
    }
}
