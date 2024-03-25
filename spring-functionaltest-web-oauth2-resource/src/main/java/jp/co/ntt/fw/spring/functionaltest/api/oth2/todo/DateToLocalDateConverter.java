package jp.co.ntt.fw.spring.functionaltest.api.oth2.todo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.github.dozermapper.core.DozerConverter;

public class DateToLocalDateConverter extends DozerConverter<Date, LocalDate> {

    public DateToLocalDateConverter() {
        super(Date.class, LocalDate.class);
    }

    @Override
    public LocalDate convertTo(Date source, LocalDate destination) {
        return source == null ? null : source.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @Override
    public Date convertFrom(LocalDate source, Date destination) {
        return source == null ? null : Date.from(source.atStartOfDay(ZoneId.systemDefault())
                .toInstant());
    }

}
