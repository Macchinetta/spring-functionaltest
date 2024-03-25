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
import java.time.ZoneOffset;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.terasoluna.gfw.common.time.ClockFactory;

import jakarta.inject.Inject;
import jakarta.inject.Named;

@RequestMapping("jsp")
@Controller
public class SYDT01Controller {

    @Named("clockFactory")
    @Inject
    ClockFactory clockFactory; // (2)

    @GetMapping(value = "0101/001")
    public String handle0101001(Model model) {

        Clock fixedClock = clockFactory.fixed(); // (3)
        Clock tickClock = clockFactory.tick(); // (4)
        Clock fixedClockWithZone = clockFactory.fixed(ZoneOffset.UTC); // (5)
        Clock tickClockWithZone = clockFactory.tick(ZoneOffset.UTC); // (6)

        // (7)
        for (int i = 0; i < 3; i++) {
            model.addAttribute("fixedClock" + i, fixedClock);
            model.addAttribute("tickClock" + i, tickClock);
            model.addAttribute("fixedClockWithZone" + i, fixedClockWithZone);
            model.addAttribute("tickClockWithZone" + i, tickClockWithZone);
        }

        return "jsp/sydt/clockDisplay";
    }

}
