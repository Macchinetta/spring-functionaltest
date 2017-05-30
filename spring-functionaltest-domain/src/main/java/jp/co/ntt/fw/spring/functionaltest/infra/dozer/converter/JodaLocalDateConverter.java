/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.infra.dozer.converter;

import org.dozer.DozerConverter;
import org.joda.time.LocalDate;

public class JodaLocalDateConverter extends
                                   DozerConverter<LocalDate, LocalDate> {

    public JodaLocalDateConverter() {
        super(LocalDate.class, LocalDate.class);
    }

    @Override
    public LocalDate convertTo(LocalDate source, LocalDate destination) {
        // This method not called, because type of from/to is same.
        return convertFrom(source, destination);
    }

    @Override
    public LocalDate convertFrom(LocalDate source, LocalDate destination) {
        return source;
    }
}
