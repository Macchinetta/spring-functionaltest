/*
 * Copyright(c) 2025 NTT Corporation.
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

import java.time.Duration;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.ThreadUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.terasoluna.gfw.common.date.jodatime.JodaTimeDateFactory;

@RequestMapping("sydt")
@Controller
public class SYDT01Controller {

    @Named("defaultDateFactory")
    @Inject
    private JodaTimeDateFactory defaultJodaTimeDateFactory;

    @Named("dateFactory")
    @Inject
    private JodaTimeDateFactory jdbcFixedJodaTimeDateFactory;

    @Named("jdbcAdjustedJodaTimeDateFactory")
    @Inject
    private JodaTimeDateFactory jdbcAdjustedJodaTimeDateFactory;

    @Named("cacheFactory")
    @Inject
    private JodaTimeDateFactory cacheFactory;

    @GetMapping(value = "0101/001")
    public String handle0101001(Model model) {

        // システム・デフォルト
        model.addAttribute("factory_name", "defaultJodaTimeDateFactory");
        timeCount(model, this.defaultJodaTimeDateFactory);

        return "sydt/dateTimeDisplay";
    }

    @GetMapping(value = "0102/001")
    public String handle0102001(Model model) {

        // DB固定値 : 2013/12/09 13:50:12.100
        model.addAttribute("factory_name", "jdbcFixedJodaTimeDateFactory");
        timeCount(model, this.jdbcFixedJodaTimeDateFactory);

        return "sydt/dateTimeDisplay";
    }

    @GetMapping(value = "0103/001")
    public String handle0103001(Model model) {

        // 初期値 : -1440m = -24h キャッシュなし
        model.addAttribute("factory_name", "jdbcAdjustedJodaTimeDateFactory");
        timeCount(model, this.jdbcAdjustedJodaTimeDateFactory);

        return "sydt/dateTimeDisplay";
    }

    @GetMapping(value = "0103/002")
    public String handle0103002(Model model) {

        // 初期値 : -1440m = -24h キャッシュあり
        model.addAttribute("factory_name", "jdbcAdjustedJodaTimeDateFactory");
        timeCount(model, this.cacheFactory);

        return "sydt/dateTimeDisplay";
    }

    private void timeCount(Model model, JodaTimeDateFactory factory) {
        for (int i = 0; i < 3; i++) {
            model.addAttribute("jodaFactory" + (i + 1), factory.newDateTime());
            model.addAttribute("DateTime" + (i + 1), new DateTime());
            try {
                ThreadUtils.sleep(Duration.ofMillis(100L));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
