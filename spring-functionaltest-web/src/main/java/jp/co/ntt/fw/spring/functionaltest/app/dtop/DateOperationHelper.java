/*
 * Copyright(c) 2014-2017 NTT Corporation.
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
