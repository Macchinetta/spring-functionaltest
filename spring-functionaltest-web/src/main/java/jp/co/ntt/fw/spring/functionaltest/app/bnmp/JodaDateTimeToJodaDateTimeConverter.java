/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.bnmp;

import org.dozer.DozerConverter;
import org.joda.time.DateTime;

/**
 * JodaDateTimeToJodaDateTimeConverter<br>
 * <p>
 * dozerでJodaTimeのDateTimeクラスをデフォルト設定でマッピングすることができない為、このクラスを用意。
 * </p>
 */
public class JodaDateTimeToJodaDateTimeConverter
                                                extends
                                                DozerConverter<DateTime, DateTime> {

    public JodaDateTimeToJodaDateTimeConverter() {
        super(DateTime.class, DateTime.class);
    }

    @Override
    public DateTime convertTo(DateTime source, DateTime destination) {
        return new DateTime(source);
    }

    @Override
    public DateTime convertFrom(DateTime source, DateTime destination) {
        return new DateTime(source);
    }

}
