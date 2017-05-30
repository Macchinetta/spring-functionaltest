/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.dtop;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.Period;

public interface DateOperationService {
    DateTime getNowDateTime();

    Date getNowDate();

    LocalDate calcIncreaseNumOfDay(LocalDate dateTime, int increaseNum);

    LocalDate calcDecreaseNumOfDay(LocalDate dateTime, int decreaseNum);

    LocalDate calcIncreaseNumOfMonth(LocalDate dateTime, int increaseNum);

    LocalDate calcDecreaseNumOfMonth(LocalDate dateTime, int decreaseNum);

    LocalDate calcIncreaseNumOfYear(LocalDate dateTime, int increaseNum);

    LocalDate calcDecreaseNumOfYear(LocalDate dateTime, int decreaseNum);

    boolean checkContainTermToDate(DateTime targetTermFrom,
            DateTime targetTermTo, DateTime targetCheckDate);

    boolean checkContainTermToTerm(DateTime targetTermFrom,
            DateTime targetTermTo, DateTime targetCheckTermFrom,
            DateTime targetCheckTermTo);

    boolean checkAbutsTerm(DateTime targetTermFrom, DateTime targetTermTo,
            DateTime targetCheckTermFrom, DateTime targetCheckTermTo);

    Interval getGapTerm(DateTime targetTermFrom, DateTime targetTermTo,
            DateTime targetCheckTermFrom, DateTime targetCheckTermTo);

    Interval getOverlapTerm(DateTime targetTermFrom, DateTime targetTermTo,
            DateTime targetCheckTermFrom, DateTime targetCheckTermTo);

    DateTime calcIncreaseNumOfDateUsingPeriod(DateTime dateTime,
            Period increasePeriod);

    DateTime calcDecreaseNumOfDateUsingPeriod(DateTime dateTime,
            Period decreasePeriod);

    String getJapaneseDateStr(String format);

    String parseJapaneseDate(String parseStr, String format);

}
