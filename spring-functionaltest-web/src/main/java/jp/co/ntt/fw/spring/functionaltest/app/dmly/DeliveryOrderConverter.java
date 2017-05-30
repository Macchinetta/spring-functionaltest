/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dmly;

import java.util.Date;

import org.dozer.DozerConverter;
import org.joda.time.LocalDateTime;

public class DeliveryOrderConverter extends DozerConverter<LocalDateTime, Date> {
    public DeliveryOrderConverter() {
        super(LocalDateTime.class, Date.class);
    }

    @Override
    public Date convertTo(LocalDateTime source, Date destination) {
        Date dest = null;
        if (source != null) {
            dest = source.toDate();
        }
        return dest;
    }

    @Override
    public LocalDateTime convertFrom(Date source, LocalDateTime destination) {
        LocalDateTime dest = null;
        if (source != null) {
            dest = LocalDateTime.fromDateFields(source);
        }
        return dest;
    }
}
