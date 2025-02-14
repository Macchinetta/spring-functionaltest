package jp.co.ntt.fw.spring.functionaltest.domain.service.sydt;

import java.time.temporal.ChronoUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.terasoluna.gfw.common.time.ClockFactory;
import org.terasoluna.gfw.common.time.ConfigurableAdjustClockFactory;
import org.terasoluna.gfw.common.time.JdbcAdjustClockFactory;
import jakarta.inject.Inject;

@Service
public class ThrowableServiceImple implements ThrowableService {

    private static final Logger logger = LoggerFactory.getLogger(ThrowableServiceImple.class);

    @Inject
    private DataSource dataSource;

    @Override
    public void errorConfigurableAdjustClockFactory() {
        ClockFactory clockFactory = new ConfigurableAdjustClockFactory(1, ChronoUnit.MONTHS);
        try {
            clockFactory.fixed();
        } catch (UnsupportedTemporalTypeException e) {
            logger.error(e.getClass().getSimpleName() + ":" + e.getMessage());
            throw e;
        }
    }

    @Override
    public void errorJdbcAdjustClockFactory() {
        ClockFactory clockFactory = new JdbcAdjustClockFactory(dataSource,
                "SELECT diff FROM operation_date where operation_date_id='2'", ChronoUnit.YEARS);
        try {
            clockFactory.fixed();
        } catch (UnsupportedTemporalTypeException e) {
            logger.error(e.getClass().getSimpleName() + ":" + e.getMessage());
            throw e;
        }
    }

}
