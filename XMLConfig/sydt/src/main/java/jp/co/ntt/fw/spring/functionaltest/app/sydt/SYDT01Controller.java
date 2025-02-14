/*
 * Copyright(c) 2024 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.app.sydt;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import org.apache.commons.lang3.ThreadUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.terasoluna.gfw.common.time.ClockFactory;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jp.co.ntt.fw.spring.functionaltest.domain.service.sydt.ThrowableService;

@RequestMapping("jsp")
@Controller
public class SYDT01Controller {

    @Named("defaultClockFactory")
    @Inject
    private ClockFactory defaultClockFactory;

    @Named("defaultConfigurableClockFactory")
    @Inject
    private ClockFactory defaultConfigurableClockFactory;

    @Named("patternConfigurableClockFactory")
    @Inject
    private ClockFactory patternConfigurableClockFactory;

    @Named("dateAndTimeConfigurableClockFactory")
    @Inject
    private ClockFactory dateAndTimeConfigurableClockFactory;

    @Named("configurableAdjustClockFactory")
    @Inject
    private ClockFactory configurableAdjustClockFactory;

    @Named("defaultJdbcClockFactory")
    @Inject
    private ClockFactory defaultJdbcClockFactory;

    @Named("adjustJdbcClockFactory")
    @Inject
    private ClockFactory adjustJdbcClockFactory;

    @Inject
    private ThrowableService throwableService;

    @GetMapping(value = "0101/001")
    public String handle0101001(Model model) {
        Clock systemDefault = this.defaultClockFactory.fixed();
        Clock dbDateTime = this.defaultJdbcClockFactory.fixed();

        // system default
        Clock fixedClock1 = this.defaultClockFactory.fixed();
        Clock fixedClock2 = this.defaultClockFactory.fixed(ZoneId.of("Asia/Tokyo"));
        Clock tickClock1 = this.defaultClockFactory.tick();
        Clock tickClock2 = this.defaultClockFactory.tick(ZoneOffset.UTC);

        setAttribute(model, systemDefault, dbDateTime, fixedClock1, fixedClock2, tickClock1,
                tickClock2);

        return "jsp/sydt/clockDisplay";
    }

    @GetMapping(value = "0102/001")
    public String handle0102001(Model model) {
        Clock systemDefault = this.defaultClockFactory.fixed();
        Clock dbDateTime = this.defaultJdbcClockFactory.fixed();

        // 2012-09-11T02:25:15
        Clock fixedClock1 = this.defaultConfigurableClockFactory.fixed();
        Clock fixedClock2 = this.defaultConfigurableClockFactory.fixed(ZoneId.of("Asia/Tokyo"));
        Clock tickClock1 = this.defaultConfigurableClockFactory.tick();
        Clock tickClock2 = this.defaultConfigurableClockFactory.tick(ZoneOffset.UTC);

        setAttribute(model, systemDefault, dbDateTime, fixedClock1, fixedClock2, tickClock1,
                tickClock2);

        return "jsp/sydt/clockDisplay";
    }

    @GetMapping(value = "0102/002")
    public String handle0102002(Model model) {
        Clock systemDefault = this.defaultClockFactory.fixed();
        Clock dbDateTime = this.defaultJdbcClockFactory.fixed();

        // 2012-09-11T02:25:15
        Clock fixedClock1 = this.patternConfigurableClockFactory.fixed();
        Clock fixedClock2 = this.patternConfigurableClockFactory.fixed(ZoneId.of("Asia/Tokyo"));
        Clock tickClock1 = this.patternConfigurableClockFactory.tick();
        Clock tickClock2 = this.patternConfigurableClockFactory.tick(ZoneOffset.UTC);

        setAttribute(model, systemDefault, dbDateTime, fixedClock1, fixedClock2, tickClock1,
                tickClock2);

        return "jsp/sydt/clockDisplay";
    }

    @GetMapping(value = "0102/003")
    public String handle0102003(Model model) {
        Clock systemDefault = this.defaultClockFactory.fixed();
        Clock dbDateTime = this.defaultJdbcClockFactory.fixed();

        // 2012-09-11T02:25:15
        Clock fixedClock1 = this.dateAndTimeConfigurableClockFactory.fixed();
        Clock fixedClock2 = this.dateAndTimeConfigurableClockFactory.fixed(ZoneId.of("Asia/Tokyo"));
        Clock tickClock1 = this.dateAndTimeConfigurableClockFactory.tick();
        Clock tickClock2 = this.dateAndTimeConfigurableClockFactory.tick(ZoneOffset.UTC);

        setAttribute(model, systemDefault, dbDateTime, fixedClock1, fixedClock2, tickClock1,
                tickClock2);

        return "jsp/sydt/clockDisplay";
    }

    // system default + diff(1d)
    @GetMapping(value = "0103/001")
    public String handle0103001(Model model) {
        Clock systemDefault = this.defaultClockFactory.fixed();
        Clock dbDateTime = this.defaultJdbcClockFactory.fixed();

        // system default + 1d
        Clock fixedClock1 = this.configurableAdjustClockFactory.fixed();
        Clock fixedClock2 = this.configurableAdjustClockFactory.fixed(ZoneId.of("Asia/Tokyo"));
        Clock tickClock1 = this.configurableAdjustClockFactory.tick();
        Clock tickClock2 = this.configurableAdjustClockFactory.tick(ZoneOffset.UTC);

        setAttribute(model, systemDefault, dbDateTime, fixedClock1, fixedClock2, tickClock1,
                tickClock2);

        return "jsp/sydt/clockDisplay";
    }

    @GetMapping(value = "0103/002")
    public String handle0103002(Model model) {
        this.throwableService.errorConfigurableAdjustClockFactory();
        return null;
    }

    // database
    @GetMapping(value = "0104/001")
    public String handle0104001(Model model) {
        Clock systemDefault = this.defaultClockFactory.fixed();
        Clock dbDateTime = this.defaultJdbcClockFactory.fixed();

        // 2013/12/09 13:50:12.100
        Clock fixedClock1 = this.defaultJdbcClockFactory.fixed();
        Clock fixedClock2 = this.defaultJdbcClockFactory.fixed(ZoneId.of("Asia/Tokyo"));
        Clock tickClock1 = this.defaultJdbcClockFactory.tick();
        Clock tickClock2 = this.defaultJdbcClockFactory.tick(ZoneOffset.UTC);

        setAttribute(model, systemDefault, dbDateTime, fixedClock1, fixedClock2, tickClock1,
                tickClock2);

        return "jsp/sydt/clockDisplay";
    }

    // system default + diff
    @GetMapping(value = "0105/001")
    public String handle0105001(Model model) {
        Clock systemDefault = this.defaultClockFactory.fixed();
        Clock dbDateTime = this.defaultJdbcClockFactory.fixed();

        // system default - 3600s
        Clock fixedClock1 = this.adjustJdbcClockFactory.fixed();
        Clock fixedClock2 = this.adjustJdbcClockFactory.fixed(ZoneId.of("Asia/Tokyo"));
        Clock tickClock1 = this.adjustJdbcClockFactory.tick();
        Clock tickClock2 = this.adjustJdbcClockFactory.tick(ZoneOffset.UTC);

        setAttribute(model, systemDefault, dbDateTime, fixedClock1, fixedClock2, tickClock1,
                tickClock2);

        return "jsp/sydt/clockDisplay";
    }

    @GetMapping(value = "0105/002")
    public String handle0105002(Model model) {
        this.throwableService.errorJdbcAdjustClockFactory();
        return null;
    }

    private void setAttribute(Model model, Clock systemDefault, Clock target, Clock fixedClock1,
            Clock fixedClock2, Clock tickClock1, Clock tickClock2) {
        model.addAttribute("baseDateTime", LocalDateTime.now(systemDefault));
        model.addAttribute("targetDateTime", LocalDateTime.now(target));

        for (int i = 0; i < 3; i++) {
            model.addAttribute("fixedLocalDateTime1_" + (i + 1), LocalDateTime.now(fixedClock1));
            model.addAttribute("fixedLocalDateTime2_" + (i + 1), LocalDateTime.now(fixedClock2));
            model.addAttribute("tickLocalDateTime1_" + (i + 1), LocalDateTime.now(tickClock1));
            model.addAttribute("tickLocalDateTime2_" + (i + 1), LocalDateTime.now(tickClock2));

            try {
                ThreadUtils.sleep(Duration.ofSeconds(1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
