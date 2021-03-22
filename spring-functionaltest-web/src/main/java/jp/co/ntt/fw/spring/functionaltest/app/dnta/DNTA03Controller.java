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
package jp.co.ntt.fw.spring.functionaltest.app.dnta;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.service.dnta.DateAndTimeApiService;

import org.springframework.context.annotation.Lazy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("dnta")
@Controller
public class DNTA03Controller {

    @Inject
    // JDK7向けのテスト実行時に、インスタンス生成の際のNoClassDefFoundErrorを回避するために@Lazyをつける
    @Lazy
    private DateAndTimeApiService dateAndTimeApiService;

    private static final int YEAR = 2012;

    private static final int MONTH = 12;

    private static final int DAY = 15;

    private static final int HOUR = 12;

    private static final int MINUTE = 10;

    private static final int SECOND = 30;

    private static final int NANO_SECOND = 567;

    private static final int OFFSET = 9;

    private static final String AREA = "Asia/Tokyo";

    @ModelAttribute
    public DateForm setUpDateForm() {
        return new DateForm();
    }

    @ModelAttribute
    public ChangeTypeForm setUpChangeTypeForm() {
        return new ChangeTypeForm();
    }

    @RequestMapping(value = "0301/001", method = RequestMethod.GET)
    public String handle01001() {
        return "dnta/changeType";
    }

    @RequestMapping(value = "0301/002", method = RequestMethod.GET)
    public String handle01002() {
        return "dnta/changeType";
    }

    @RequestMapping(value = "0301/003", method = RequestMethod.GET)
    public String handle01003() {
        return "dnta/changeType";
    }

    @RequestMapping(value = "0301/004", method = RequestMethod.GET)
    public String handle01004() {
        return "dnta/changeType";
    }

    @RequestMapping(value = "0301/005", method = RequestMethod.GET)
    public String handle01005() {
        return "dnta/changeType";
    }

    @RequestMapping(value = "0301/006", method = RequestMethod.GET)
    public String handle01006() {
        return "dnta/changeType";
    }

    @RequestMapping(value = "0301/007", method = RequestMethod.GET)
    public String handle01007() {
        return "dnta/changeType";
    }

    @RequestMapping(value = "0301/008", method = RequestMethod.GET)
    public String handle01008() {
        return "dnta/changeType";
    }

    @RequestMapping(value = "0301/009", method = RequestMethod.GET)
    public String handle01009() {
        return "dnta/changeType";
    }

    @RequestMapping(value = "0302/001", method = RequestMethod.GET)
    public String handle02001() {
        return "dnta/changeType";
    }

    @RequestMapping(value = "0302/002", method = RequestMethod.GET)
    public String handle02002() {
        return "dnta/changeType";
    }

    @RequestMapping(value = "0303/001", method = RequestMethod.GET)
    public String handle03001() {
        return "dnta/changeType";
    }

    @RequestMapping(value = "0303/002", method = RequestMethod.GET)
    public String handle03002() {
        return "dnta/changeType";
    }

    @RequestMapping(value = "0303/003", method = RequestMethod.GET)
    public String handle03003() {
        return "dnta/changeType";
    }

    @RequestMapping(value = "0303/004", method = RequestMethod.GET)
    public String handle03004() {
        return "dnta/changeType";
    }

    @RequestMapping(value = "0303/005", method = RequestMethod.GET)
    public String handle03005() {
        return "dnta/changeType";
    }

    @RequestMapping(value = "0303/006", method = RequestMethod.GET)
    public String handle03006() {
        return "dnta/changeType";
    }

    @RequestMapping(value = "0304/001", method = RequestMethod.GET)
    public String handle04001() {
        return "dnta/changeType";
    }

    @RequestMapping(value = "0305/001", method = RequestMethod.GET)
    public String handle05001() {
        return "dnta/changeType";
    }

    @RequestMapping(value = "0305/002", method = RequestMethod.GET)
    public String handle05002() {
        return "dnta/changeType";
    }

    @RequestMapping(value = "0305/003", method = RequestMethod.GET)
    public String handle05003() {
        return "dnta/changeType";
    }

    @RequestMapping(value = "0306/001", method = RequestMethod.GET)
    public String handle06001() {
        return "dnta/changeType";
    }

    @RequestMapping(value = "0306/002", method = RequestMethod.GET)
    public String handle06002() {
        return "dnta/changeType";
    }

    @RequestMapping(value = "changeType", method = RequestMethod.GET, params = "getLocalTimeToLocalDateTime")
    public String handleGetLocalTimeToLocalDateTime(DateForm form,
            Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService
                .toLocalDateTimeFromLocalTime(HOUR, MINUTE, SECOND, form
                        .getYear(), form.getMonth(), form.getDay()));
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "changeType", method = RequestMethod.GET, params = "getLocalDateToLocalDateTime")
    public String handleGetLocalDateToLocalDateTime(DateForm form,
            Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService
                .toLocalDateTimeFromLocalDate(YEAR, MONTH, DAY, form.getHour(),
                        form.getMinute(), form.getSecond()));
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "changeType", method = RequestMethod.GET, params = "getLocalDateTimeToLocalTime")
    public String handleGetLocalDateTimeToLocalTime(Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.toLocalTime(YEAR,
                MONTH, DAY, HOUR, MINUTE, SECOND));
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "changeType", method = RequestMethod.GET, params = "getLocalDateTimeToLocalDate")
    public String handleGetLocalDateTimeToLocalDate(Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.toLocalDate(YEAR,
                MONTH, DAY, HOUR, MINUTE, SECOND));
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "changeType", method = RequestMethod.GET, params = "getOffseTimeToOffsetDateTime")
    public String handleGetOffseTimeToOffsetDateTime(DateForm form,
            Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.toOffsetDateTime(
                HOUR, MINUTE, SECOND, NANO_SECOND, OFFSET, form.getYear(), form
                        .getMonth(), form.getDay()));
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "changeType", method = RequestMethod.GET, params = "getOffsetDateTimeToZonedDateTime")
    public String handleGetOffsetDateTimeToZonedDateTime(DateForm form,
            Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.toZonedDateTime(
                YEAR, MONTH, DAY, HOUR, MINUTE, SECOND, NANO_SECOND, OFFSET,
                AREA));
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "changeType", method = RequestMethod.GET, params = "getZonedDateTimeToOffsetDateTime")
    public String handleGetZonedDateTimeToOffsetDateTime(DateForm form,
            Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.toOffsetDateTime(
                YEAR, MONTH, DAY, HOUR, MINUTE, SECOND, NANO_SECOND, AREA));
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "changeType", method = RequestMethod.GET, params = "getZonedDateTimeToOffsetTime")
    public String handleGetZonedDateTimeToOffsetTime(DateForm form,
            Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.toOffsetTime(
                YEAR, MONTH, DAY, HOUR, MINUTE, SECOND, NANO_SECOND, AREA));
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "changeType", method = RequestMethod.GET, params = "getLocalTimeToOffsetTime")
    public String handleGetLocalTimeToOffsetTime(DateForm form, Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.toOffsetTime(
                HOUR, MINUTE, SECOND, OFFSET));
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "changeType", method = RequestMethod.GET, params = "getLocalDateTimeToUtilDate")
    public String handleGetLocalDateTimeToUtilDate(Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.toUtilDate(YEAR,
                MONTH, DAY, HOUR, MINUTE, SECOND, OFFSET));
        return "dnta/showUtilAndSqlDateTime";
    }

    @RequestMapping(value = "changeType", method = RequestMethod.GET, params = "getUtilDateToLocalDateTime")
    public String handleGetUtilDateToLocalDateTime(
            Model model) throws ParseException {
        java.util.Date utilDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                .parse("2012/12/15 12:10:30");
        model.addAttribute("resultDate", dateAndTimeApiService.toLocalDateTime(
                utilDate));
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "changeType", method = RequestMethod.GET, params = "getLocalDateToSqlDate")
    public String handleGetLocalDateToSqlDate(Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.toSqlDate(YEAR,
                MONTH, DAY));
        return "dnta/showUtilAndSqlDate";
    }

    @RequestMapping(value = "changeType", method = RequestMethod.GET, params = "getSqlDateToLocalDate")
    public String handleGetSqlDateToLocalDate(
            Model model) throws ParseException {
        java.util.Date utilDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                .parse("2012/12/15 12:10:30");
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        model.addAttribute("resultDate", dateAndTimeApiService.toLocalDate(
                sqlDate));
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "changeType", method = RequestMethod.GET, params = "getLocalDateTimeToSqlTimestamp")
    public String handleGetLocalDateTimeToSqlTimestamp(Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.toSqlTimestamp(
                YEAR, MONTH, DAY, HOUR, MINUTE, SECOND));
        return "dnta/showUtilAndSqlDateTime";
    }

    @RequestMapping(value = "changeType", method = RequestMethod.GET, params = "getSqlTimestampToLocalDateTime")
    public String handleGetSqlTimestampToLocalDateTime(
            Model model) throws ParseException {
        java.util.Date utilDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                .parse("2012/12/15 12:10:30");
        java.sql.Timestamp timestamp = new java.sql.Timestamp(utilDate
                .getTime());
        model.addAttribute("resultDate", dateAndTimeApiService.toLocalDateTime(
                timestamp));
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "changeType", method = RequestMethod.GET, params = "getLocalTimeToSqlTime")
    public String handleGetLocalTimeToSqlTime(Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.toSqlTime(HOUR,
                MINUTE, SECOND));
        return "dnta/showUtilAndSqlTime";
    }

    @RequestMapping(value = "changeType", method = RequestMethod.GET, params = "getSqlTimeToLocalTime")
    public String handleGetSqlTimeToLocalTime(
            Model model) throws ParseException {
        java.util.Date utilDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                .parse("2012/12/15 12:10:30");
        java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());
        model.addAttribute("resultDate", dateAndTimeApiService.toLocalTime(
                sqlTime));
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "changeType", method = RequestMethod.GET, params = "getDateFromDateFactory")
    public String handleGetDateFromDateFactory(Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService
                .createLocalDateFromDateFactory());
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "changeType", method = RequestMethod.GET, params = "getNormalFormat")
    public String handleGetNormalFormat(Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.createDateString(
                YEAR, MONTH, DAY));
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "changeType", method = RequestMethod.GET, params = "getPredefinedFormat")
    public String handleGetPredefinedFormat(Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService
                .createDateStringWithPredefinedFormat(YEAR, MONTH, DAY));
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "changeType", method = RequestMethod.GET, params = "getAnyFormat")
    public String handleGetAnyFormat(Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService
                .createDateStringWithAnyFormat(YEAR, MONTH, DAY));
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "changeType", method = RequestMethod.GET, params = "getParseDate")
    public String handleGetParseDate(ChangeTypeForm form, Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.parseToDate(form
                .getTargetDate()));
        return "dnta/showDateTime";
    }

    @RequestMapping(value = "changeType", method = RequestMethod.GET, params = "getParseTime")
    public String handleGetParseTime(ChangeTypeForm form, Model model) {
        model.addAttribute("resultDate", dateAndTimeApiService.parseToTime(form
                .getTargetDate()));
        return "dnta/showDateTime";
    }
}
