/*
 * Copyright(c) 2014 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.app.aply;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.terasoluna.gfw.common.date.jodatime.JodaTimeDateFactory;

@RequestMapping("/aply")
@Controller
public class APLY10Controller {

    @Inject
    JodaTimeDateFactory dateFactory;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // bind empty strings as null
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @RequestMapping(value = "1001/001")
    public String handle01001(Model model) {
        // モデルに格納される値
        model.addAttribute("valueUsingThText", "<font color='red'>Sample</font>");
        return "aply/showValueUsingThText";
    }

    @RequestMapping(value = "1001/002")
    public String handle01002(Model model) {
        // モデルに格納される値
        model.addAttribute("valueUsingNumberFormatInteger", 7777);
        model.addAttribute("testcaseId", "APLY1001002");
        return "aply/showValueUsingNumberFormatInteger";
    }

    @RequestMapping(value = "1001/003")
    public String handle01003(Model model) {
        // モデルに格納される値
        model.addAttribute("valueUsingNumberFormatDecimal", 3333.55);
        model.addAttribute("testcaseId", "APLY1001003");
        return "aply/showValueUsingNumberFormatDecimal";
    }

    @RequestMapping(value = "1001/004")
    public String handle01004(Model model) {
        // モデルに格納される値
        model.addAttribute("valueUsingNumberFormatPercent", 0.345);
        model.addAttribute("testcaseId", "APLY1001004");
        return "aply/showValueUsingNumberFormatPercent";
    }

    @RequestMapping(value = "1001/005")
    public String handle01005(Model model) {
        // モデルに格納される値
        model.addAttribute("testcaseId", "APLY1001005");
        model.addAttribute("valueUsingDateFormat", dateFactory.newDate());
        return "aply/showValueUsingDateFormat";
    }

    @RequestMapping(value = "1001/006")
    public String handle01006(Model model) {
        // モデルに格納される値
        Calendar calendar = Calendar.getInstance();
        calendar.set(2013, 11, 9);
        model.addAttribute("testcaseId", "APLY1001006");
        model.addAttribute("valueUsingCalendarFormat", calendar);
        return "aply/showValueUsingCalendarFormat";
    }

    @RequestMapping(value = "1001/007")
    public String handle01007(Model model) {
        // モデルに格納される値
        Integer[] array = {1111, 2222, 3333};
        model.addAttribute("valueUsingNumberFormatInteger", array);
        model.addAttribute("testcaseId", "APLY1001007");
        return "aply/showValueUsingNumberFormatInteger";
    }

    @RequestMapping(value = "1001/008")
    public String handle01008(Model model) {
        // モデルに格納される値
        List<Integer> list = new ArrayList<>();
        list.add(4444);
        list.add(5555);
        list.add(6666);
        model.addAttribute("valueUsingNumberFormatInteger", list);
        model.addAttribute("testcaseId", "APLY1001008");
        return "aply/showValueUsingNumberFormatInteger";
    }

    @RequestMapping(value = "1001/009")
    public String handle01009(Model model) {
        // モデルに格納される値
        Set<Integer> set = new HashSet<>();
        set.add(7777);
        set.add(8888);
        set.add(9999);
        model.addAttribute("valueUsingNumberFormatInteger", set);
        model.addAttribute("testcaseId", "APLY1001009");
        return "aply/showValueUsingNumberFormatInteger";
    }

    @RequestMapping(value = "1001/010")
    public String handle01010(Model model) {
        // モデルに格納される値
        BigDecimal[] array = {BigDecimal.valueOf(1111.1), BigDecimal.valueOf(2222.2),
                BigDecimal.valueOf(3333.3)};
        model.addAttribute("valueUsingNumberFormatDecimal", array);
        model.addAttribute("testcaseId", "APLY1001010");
        return "aply/showValueUsingNumberFormatDecimal";
    }

    @RequestMapping(value = "1001/011")
    public String handle01011(Model model) {
        // モデルに格納される値
        List<BigDecimal> list = new ArrayList<>();
        list.add(BigDecimal.valueOf(4444.4));
        list.add(BigDecimal.valueOf(5555.5));
        list.add(BigDecimal.valueOf(6666.6));
        model.addAttribute("valueUsingNumberFormatDecimal", list);
        model.addAttribute("testcaseId", "APLY1001011");
        return "aply/showValueUsingNumberFormatDecimal";
    }

    @RequestMapping(value = "1001/012")
    public String handle01012(Model model) {
        // モデルに格納される値
        Set<BigDecimal> set = new LinkedHashSet<>();
        set.add(BigDecimal.valueOf(7777.7));
        set.add(BigDecimal.valueOf(8888.8));
        set.add(BigDecimal.valueOf(9999.9));
        model.addAttribute("valueUsingNumberFormatDecimal", set);
        model.addAttribute("testcaseId", "APLY1001012");
        return "aply/showValueUsingNumberFormatDecimal";
    }

    @RequestMapping(value = "1001/013")
    public String handle01013(Model model) {
        // モデルに格納される値
        Double[] array = {0.111, 0.222, 0.333};
        model.addAttribute("valueUsingNumberFormatPercent", array);
        model.addAttribute("testcaseId", "APLY1001013");
        return "aply/showValueUsingNumberFormatPercent";
    }

    @RequestMapping(value = "1001/014")
    public String handle01014(Model model) {
        // モデルに格納される値
        List<Double> list = new ArrayList<>();
        list.add(0.444);
        list.add(0.555);
        list.add(0.666);
        model.addAttribute("valueUsingNumberFormatPercent", list);
        model.addAttribute("testcaseId", "APLY1001014");
        return "aply/showValueUsingNumberFormatPercent";
    }

    @RequestMapping(value = "1001/015")
    public String handle01015(Model model) {
        // モデルに格納される値
        Set<Double> set = new LinkedHashSet<>();
        set.add(0.777);
        set.add(0.888);
        set.add(0.999);
        model.addAttribute("valueUsingNumberFormatPercent", set);
        model.addAttribute("testcaseId", "APLY1001015");
        return "aply/showValueUsingNumberFormatPercent";
    }

    @RequestMapping(value = "1001/{caseId:01[6|8|9]|020}")
    public String handle01016_18_19_20(Model model, @PathVariable String caseId) {
        // モデルに格納される値
        model.addAttribute("valueUsingNumberFormatInteger", 7777);
        model.addAttribute("testcaseId", "APLY1001" + caseId);
        return "aply/showValueUsingNumberFormatInteger";
    }

    @RequestMapping(value = "1001/{caseId:02[2-5]}")
    public String handle01022_25(Model model, @PathVariable String caseId) {
        // モデルに格納される値
        model.addAttribute("valueUsingNumberFormatDecimal", 3333.55);
        model.addAttribute("testcaseId", "APLY1001" + caseId);
        return "aply/showValueUsingNumberFormatDecimal";
    }

    @RequestMapping(value = "1001/026")
    public String handle01026(Model model) {
        // モデルに格納される値
        model.addAttribute("valueUsingNumberFormatInteger", 99);
        model.addAttribute("testcaseId", "APLY1001026");
        return "aply/showValueUsingNumberFormatInteger";
    }

    @RequestMapping(value = "1001/027")
    public String handle01027(Model model) {
        // モデルに格納される値
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Date[] array = new Date[3];
        try {
            array[0] = df.parse("2013/12/09");
            array[1] = df.parse("2013/12/10");
            array[2] = df.parse("2013/12/11");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("testcaseId", "APLY1001027");
        model.addAttribute("valueUsingDateFormat", array);
        return "aply/showValueUsingDateFormat";
    }

    @RequestMapping(value = "1001/028")
    public String handle01028(Model model) {
        // モデルに格納される値
        List<Date> list = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        try {
            list.add(df.parse("2013/12/09"));
            list.add(df.parse("2013/12/10"));
            list.add(df.parse("2013/12/11"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("testcaseId", "APLY1001028");
        model.addAttribute("valueUsingDateFormat", list);
        return "aply/showValueUsingDateFormat";
    }

    @RequestMapping(value = "1001/029")
    public String handle01029(Model model) {
        // モデルに格納される値
        Set<Date> set = new LinkedHashSet<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        try {
            set.add(df.parse("2013/12/09"));
            set.add(df.parse("2013/12/10"));
            set.add(df.parse("2013/12/11"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("testcaseId", "APLY1001029");
        model.addAttribute("valueUsingDateFormat", set);
        return "aply/showValueUsingDateFormat";
    }

    @RequestMapping(value = "1001/030")
    public String handle01030(Model model) {
        // モデルに格納される値
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2013, 11, 9);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2013, 11, 10);
        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2013, 11, 11);
        Calendar[] array = {calendar1, calendar2, calendar3};
        model.addAttribute("testcaseId", "APLY1001030");
        model.addAttribute("valueUsingCalendarFormat", array);
        return "aply/showValueUsingCalendarFormat";
    }

    @RequestMapping(value = "1001/031")
    public String handle01031(Model model) {
        // モデルに格納される値
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2013, 11, 9);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2013, 11, 10);
        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2013, 11, 11);
        List<Calendar> list = new ArrayList<>();
        list.add(calendar1);
        list.add(calendar2);
        list.add(calendar3);
        model.addAttribute("testcaseId", "APLY1001031");
        model.addAttribute("valueUsingCalendarFormat", list);
        return "aply/showValueUsingCalendarFormat";
    }

    @RequestMapping(value = "1001/032")
    public String handle01032(Model model) {
        // モデルに格納される値
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2013, 11, 9);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2013, 11, 10);
        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2013, 11, 11);
        Set<Calendar> set = new LinkedHashSet<>();
        set.add(calendar1);
        set.add(calendar2);
        set.add(calendar3);
        model.addAttribute("testcaseId", "APLY1001032");
        model.addAttribute("valueUsingCalendarFormat", set);
        return "aply/showValueUsingCalendarFormat";
    }

    @RequestMapping(value = "1002/001")
    public String handle02001(Model model) {
        return "aply/linkUrlContextRelativePath";
    }

    @RequestMapping(value = "1002/002")
    public String handle02002(Model model) {
        return "aply/linkUrlPageRelativePath";
    }

    @RequestMapping(value = "1002/003")
    public String handle02003(Model model) {
        model.addAttribute("userId", 3);
        return "aply/linkUrlUsingParameter";
    }

    @RequestMapping(value = "1002/004")
    public String handle02004(Model model) {
        model.addAttribute("userId", 3);
        return "aply/linkUrlUsingVariable";
    }

    @RequestMapping(value = "1002/005")
    public String handle02005(Model model) {
        return "aply/requestUrlUsingMvcUrl";
    }

    @RequestMapping(value = "1002/006")
    public String handle02006(Model model) {
        model.addAttribute("userId", null);
        return "aply/linkUrlUsingParameterCaseNull";
    }

    @RequestMapping(value = "1002/address")
    public String handleMvcUrl(Model model) {
        return "aply/requestUrlUsingMvcUrl";
    }

    @RequestMapping(value = "1003/001")
    public String handle03001(Model model) {
        return "aply/showMessage";
    }

    @RequestMapping(value = "1003/002")
    public String handle03002(Model model) {
        model.addAttribute("textCombiningUsingPlus", "Hello World!");
        return "aply/showTextCombiningUsingPlus";
    }

    @RequestMapping(value = "1003/003")
    public String handle03003(Model model) {
        model.addAttribute("textCombiningUsingPipe", "Hello World!");
        return "aply/showTextCombiningUsingPipe";
    }

    @RequestMapping(value = "1003/004")
    public String handle03004(Model model) {
        model.addAttribute("textCombiningCaseNull", null);
        return "aply/showTextCombiningCaseNull";
    }

    @RequestMapping(value = "1004/001")
    public String handle04001(Model model, ThymeleafForm thymeleafForm) {
        return "aply/inputConditionalExpressions";
    }

    @RequestMapping(value = "1004/displaySwitching", method = RequestMethod.POST,
            params = "comparator")
    public String handleInputDisplayConditionalExpressionsUsingComparator(Model model,
            ThymeleafForm thymeleafForm) {
        return "aply/displayConditionalExpressionsUsingComparator";
    }

    @RequestMapping(value = "1004/002")
    public String handle04002(Model model, ThymeleafForm thymeleafForm) {
        return "aply/inputConditionalExpressions";
    }

    @RequestMapping(value = "1004/displaySwitching", method = RequestMethod.POST,
            params = "arithmeticOperation")
    public String handleInputDisplayConditionalExpressionsUsingArithmeticOperation(Model model,
            ThymeleafForm thymeleafForm) {
        return "aply/displayConditionalExpressionsUsingArithmeticOperation";
    }

    @RequestMapping(value = "1004/003")
    public String handle04003(Model model, ThymeleafForm thymeleafForm) {
        return "aply/inputConditionalExpressions";
    }

    @RequestMapping(value = "1004/displaySwitching", method = RequestMethod.POST,
            params = "defaultExpressions")
    public String handleInputDisplayDefaultExpressions(Model model, ThymeleafForm thymeleafForm) {
        return "aply/displayDefaultExpressions";
    }

    @RequestMapping(value = "1005/001")
    public String handle05001(Model model, ThymeleafForm thymeleafForm) {
        return "aply/inputDisplaySwitchingInModel";
    }

    @RequestMapping(value = "1005/displaySwitching", method = RequestMethod.POST,
            params = "thIfFormat")
    public String handleInputDisplaySwitchingUsingThIf(Model model, ThymeleafForm thymeleafForm) {
        return "aply/showDisplaySwitchingUsingThIf";
    }

    @RequestMapping(value = "1005/002")
    public String handle05002(Model model, ThymeleafForm thymeleafForm) {
        return "aply/inputDisplaySwitchingInModel";
    }

    @RequestMapping(value = "1005/displaySwitching", method = RequestMethod.POST,
            params = "thSwitchFormat")
    public String handleInputDisplaySwitchingUsingCChoose(Model model,
            ThymeleafForm thymeleafForm) {
        return "aply/showDisplaySwitchingUsingThSwitch";
    }

    @RequestMapping(value = "1006/001")
    public String handle06001(Model model, ThymeleafFormListForm form) {
        return "aply/inputCollectionInModel";
    }

    @RequestMapping(value = "1006/collectionInModel", method = RequestMethod.POST,
            params = "collectionInModel")
    public String handleInputCollectionInModel(Model model,
            ThymeleafFormListForm thymeleafFormListform) {
        return "aply/showCollectionInModel";
    }

    @RequestMapping(value = "1006/002")
    public String handle06002(Model model, ThymeleafFormListForm form) {
        return "aply/inputCollectionInModel";
    }

    @RequestMapping(value = "1006/collectionInModel", method = RequestMethod.POST,
            params = "collectionInModelUsingStatus")
    public String handleInputCollectionUsingThObject(Model model,
            ThymeleafFormListForm thymeleafFormListForm) {
        return "aply/showCollectionInModelUsingStatus";
    }

    @RequestMapping(value = "1007/001")
    public String handle07001(Model model, ThymeleafForm thymeleafForm) {
        return "aply/inputValue";
    }

    @RequestMapping(value = "1007/showValue", method = RequestMethod.POST, params = "thObject")
    public String handleInputValueUsingThObject(Model model, ThymeleafForm thymeleafForm) {
        return "aply/showValueUsingThObject";
    }

    @RequestMapping(value = "1007/002")
    public String handle07002(Model model, ThymeleafForm thymeleafForm) {
        return "aply/inputValue";
    }

    @RequestMapping(value = "1007/showValue", method = RequestMethod.POST, params = "thWith")
    public String handleInputValueUsingThWith(Model model, ThymeleafForm thymeleafForm) {
        return "aply/showValueUsingThWith";
    }

    @RequestMapping(value = "1008/001")
    public String handle08001(Model model, ThymeleafForm thymeleafForm) {
        return "aply/inputFormObjectBindHtmlForm";
    }

    @RequestMapping(value = "1008/bindFormObject", method = RequestMethod.POST,
            params = "bindFormObject")
    public String handleBindFormObject(Model model, ThymeleafForm thymeleafForm) {
        return "aply/showFormObjectBindHtmlForm";
    }

    @RequestMapping(value = "1009/001")
    public String handle09001(Model model) {
        // モデルに格納される値
        List<Integer> list = new ArrayList<>();
        list.add(123);
        list.add(456);
        list.add(789);
        model.addAttribute("aggregatesValue", list);
        model.addAttribute("testcaseId", "APLY1009001");
        return "aply/showValueUsingAggregates";
    }

    @RequestMapping(value = "1009/002")
    public String handle09002(Model model) {
        // モデルに格納される値
        List<Integer> list = new ArrayList<>();
        list.add(123);
        list.add(456);
        list.add(789);
        model.addAttribute("aggregatesValue", list);
        model.addAttribute("testcaseId", "APLY1009002");
        return "aply/showValueUsingAggregates";
    }
}
