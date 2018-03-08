/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dtop;

import org.joda.time.Interval;
import org.joda.time.Period;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class DateOperationHelper {

    public void bindTermResultToModel(Model model, Interval interval) {

        // 期間が存在する場合、それぞれ表示用の期間（開始日、終了日）を設定
        if (interval != null) {
            model.addAttribute("resultStartDate", interval.getStart());
            model.addAttribute("resultEndDate", interval.getEnd());
        }
    }

    public Period getPeriodByMonth(int monthNum) {
        return new Period(0, monthNum, 0, 0, 0, 0, 0, 0);
    }

    public Period getPeriodByMonthAndDay(int monthNum, int dayNum) {
        return new Period(0, monthNum, 0, dayNum, 0, 0, 0, 0);
    }
}
