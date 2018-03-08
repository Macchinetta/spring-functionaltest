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
package jp.co.ntt.fw.spring.functionaltest.domain.service.dtop;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.springframework.stereotype.Service;
import org.terasoluna.gfw.common.date.jodatime.JodaTimeDateFactory;

@Service
public class DateOperationServiceImpl implements DateOperationService {
    @Inject
    JodaTimeDateFactory dateFactory;

    @Override
    public DateTime getNowDateTime() {
        return dateFactory.newDateTime();
    }

    @Override
    public Date getNowDate() {
        return dateFactory.newDate();
    }

    @Override
    public LocalDate calcIncreaseNumOfDay(LocalDate dateTime, int increaseNum) {
        return dateTime.plusDays(increaseNum);
    }

    @Override
    public LocalDate calcDecreaseNumOfDay(LocalDate dateTime, int decreaseNum) {
        return dateTime.minusDays(decreaseNum);
    }

    @Override
    public LocalDate calcIncreaseNumOfMonth(LocalDate dateTime,
            int increaseNum) {
        return dateTime.plusMonths(increaseNum);
    }

    @Override
    public LocalDate calcDecreaseNumOfMonth(LocalDate dateTime,
            int decreaseNum) {
        return dateTime.minusMonths(decreaseNum);
    }

    @Override
    public LocalDate calcIncreaseNumOfYear(LocalDate dateTime,
            int increaseNum) {
        return dateTime.plusYears(increaseNum);
    }

    @Override
    public LocalDate calcDecreaseNumOfYear(LocalDate dateTime,
            int decreaseNum) {
        return dateTime.minusYears(decreaseNum);
    }

    @Override
    public boolean checkContainTermToDate(DateTime targetTermFrom,
            DateTime targetTermTo, DateTime targetCheckDate) {
        Interval interval1 = new Interval(targetTermFrom, targetTermTo);
        return interval1.contains(targetCheckDate);
    }

    @Override
    public boolean checkContainTermToTerm(DateTime targetTermFrom,
            DateTime targetTermTo, DateTime targetCheckTermFrom,
            DateTime targetCheckTermTo) {
        Interval interval1 = new Interval(targetTermFrom, targetTermTo);
        Interval interval2 = new Interval(targetCheckTermFrom, targetCheckTermTo);
        return interval1.contains(interval2);
    }

    @Override
    public boolean checkAbutsTerm(DateTime targetTermFrom,
            DateTime targetTermTo, DateTime targetCheckTermFrom,
            DateTime targetCheckTermTo) {
        Interval interval1 = new Interval(targetTermFrom, targetTermTo);
        Interval interval2 = new Interval(targetCheckTermFrom, targetCheckTermTo);
        return interval1.abuts(interval2);
    }

    @Override
    public Interval getGapTerm(DateTime targetTermFrom, DateTime targetTermTo,
            DateTime targetCheckTermFrom, DateTime targetCheckTermTo) {
        Interval interval1 = new Interval(targetTermFrom, targetTermTo);
        Interval interval2 = new Interval(targetCheckTermFrom, targetCheckTermTo);
        return interval1.gap(interval2);
    }

    @Override
    public Interval getOverlapTerm(DateTime targetTermFrom,
            DateTime targetTermTo, DateTime targetCheckTermFrom,
            DateTime targetCheckTermTo) {
        Interval interval1 = new Interval(targetTermFrom, targetTermTo);
        Interval interval2 = new Interval(targetCheckTermFrom, targetCheckTermTo);
        return interval1.overlap(interval2);
    }

    @Override
    public DateTime calcIncreaseNumOfDateUsingPeriod(DateTime dateTime,
            Period increasePeriod) {
        return dateTime.plus(increasePeriod);
    }

    @Override
    public DateTime calcDecreaseNumOfDateUsingPeriod(DateTime dateTime,
            Period decreasePeriod) {
        return dateTime.minus(decreasePeriod);
    }

    @Override
    public String getJapaneseDateStr(String format) {
        Locale locale = new Locale("ja", "JP", "JP");
        Calendar cal = Calendar.getInstance(locale);
        DateFormat df = new SimpleDateFormat(format, locale);
        cal.setTime(getNowDate());

        return df.format(cal.getTime());
    }

    @Override
    public String parseJapaneseDate(String parseStr, String format) {
        Locale locale = new Locale("ja", "JP", "JP");
        Calendar cal = Calendar.getInstance(locale);
        DateFormat df = new SimpleDateFormat(format, locale);

        try {
            cal.setTime(df.parse(parseStr));
        } catch (ParseException e) {
        }
        return df.format(cal.getTime());
    }
}
