/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.dnta;

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
public class DNTA02Controller {

    @Inject
    // JDK7向けのテスト実行時に、インスタンス生成の際のNoClassDefFoundErrorを回避するために@Lazyをつける
    @Lazy
    DateAndTimeApiService dateAndTimeApiService;

    @ModelAttribute
    public CompareDateForm setUpCompareDateForm() {
        return new CompareDateForm();
    }

    @ModelAttribute
    public CompareTimeForm setUpCompareTimeForm() {
        return new CompareTimeForm();
    }

    @ModelAttribute
    public CompareZonedDateTimeForm setUpCompareZonedDateTimeForm() {
        return new CompareZonedDateTimeForm();
    }

    @RequestMapping(value = "0201/001", method = RequestMethod.GET)
    public String handle01001() {
        return "dnta/duration";
    }

    @RequestMapping(value = "020/002", method = RequestMethod.GET)
    public String handle01002() {
        return "dnta/duration";
    }

    @RequestMapping(value = "0202/001", method = RequestMethod.GET)
    public String handle02001() {
        return "dnta/duration";
    }

    @RequestMapping(value = "0202/002", method = RequestMethod.GET)
    public String handle02002() {
        return "dnta/duration";
    }

    @RequestMapping(value = "duration", method = RequestMethod.GET, params = "compareDate")
    public String handleCompareDate(CompareDateForm form, Model model) {

        int[] result = dateAndTimeApiService.compareDate(form.getYear1(), form
                .getMonth1(), form.getDay1(), form.getYear2(),
                form.getMonth2(), form.getDay2());

        model.addAttribute("start", dateAndTimeApiService
                .getSpecifiedLocalDate(form.getYear1(), form.getMonth1(), form
                        .getDay1()));
        model.addAttribute("end", dateAndTimeApiService.getSpecifiedLocalDate(
                form.getYear2(), form.getMonth2(), form.getDay2()));
        model.addAttribute("yearResult", result[0]);
        model.addAttribute("monthResult", result[1]);
        model.addAttribute("dayResult", result[2]);
        return "dnta/showPeriod";
    }

    @RequestMapping(value = "duration", method = RequestMethod.GET, params = "compareTime")
    public String handleCompareTime(CompareTimeForm form, Model model) {

        long[] result = dateAndTimeApiService.compareTime(form.getHour1(), form
                .getMinute1(), form.getSecond1(), form.getHour2(), form
                .getMinute2(), form.getSecond2());

        model.addAttribute("start", dateAndTimeApiService
                .getSpecifiedLocalTime(form.getHour1(), form.getMinute1(), form
                        .getSecond1()));
        model.addAttribute("end", dateAndTimeApiService.getSpecifiedLocalTime(
                form.getHour2(), form.getMinute2(), form.getSecond2()));
        model.addAttribute("hourResult", result[0]);
        model.addAttribute("minuteResult", result[1]);
        model.addAttribute("secondResult", result[2]);
        return "dnta/showDuration";
    }

    @RequestMapping(value = "duration", method = RequestMethod.GET, params = "compareTimeWithSummerTime")
    public String handleCompareTimeWithSummerTime(
            CompareZonedDateTimeForm form, Model model) {
        long[] result = dateAndTimeApiService
                .compareTime(form.getZonedDateTimeyYear1(), form
                        .getZonedDateTimeMonth1(), form.getZonedDateTimeDay1(),
                        form.getZonedDateTimeHour1(), form
                                .getZonedDateTimeMinute1(), form
                                .getZonedDateTimeSecond1(), 0, form
                                .getZonedDateTimeZone1(), form
                                .getZonedDateTimeYear2(), form
                                .getZonedDateTimeMonth2(), form
                                .getZonedDateTimeDay2(), form
                                .getZonedDateTimeHour2(), form
                                .getZonedDateTimeMinute2(), form
                                .getZonedDateTimeSecond2(), 0, form
                                .getZonedDateTimeZone2());

        model.addAttribute("start", dateAndTimeApiService
                .getSpecifiedZonedDateTime(form.getZonedDateTimeyYear1(), form
                        .getZonedDateTimeMonth1(), form.getZonedDateTimeDay1(),
                        form.getZonedDateTimeHour1(), form
                                .getZonedDateTimeMinute1(), form
                                .getZonedDateTimeSecond1(), 0, form
                                .getZonedDateTimeZone1()));
        model.addAttribute("end", dateAndTimeApiService
                .getSpecifiedZonedDateTime(form.getZonedDateTimeYear2(), form
                        .getZonedDateTimeMonth2(), form.getZonedDateTimeDay2(),
                        form.getZonedDateTimeHour2(), form
                                .getZonedDateTimeMinute2(), form
                                .getZonedDateTimeSecond2(), 0, form
                                .getZonedDateTimeZone2()));
        model.addAttribute("hourResult", result[0]);
        model.addAttribute("minuteResult", result[1]);
        model.addAttribute("secondResult", result[2]);
        return "dnta/showDuration";
    }
}
