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
package jp.co.ntt.fw.spring.functionaltest.app.aply;

import java.util.Calendar;

import javax.inject.Inject;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
        binder.registerCustomEditor(String.class,
                new StringTrimmerEditor(true));
    }

    @RequestMapping(value = "1001/001")
    public String handle01001(Model model) {
        // モデルに格納される値
        model.addAttribute("valueUsingThText",
                "<font color='red'>Sample</font>");
        return "aply/showValueUsingThText";
    }

    @RequestMapping(value = "1001/002")
    public String handle01002(Model model) {
        // モデルに格納される値
        model.addAttribute("valueUsingNumberFormatInteger", 7777);
        return "aply/showValueUsingNumberFormatInteger";
    }

    @RequestMapping(value = "1001/003")
    public String handle01003(Model model) {
        // モデルに格納される値
        model.addAttribute("valueUsingNumberFormatDecimal", 3333.55);
        return "aply/showValueUsingNumberFormatDecimal";
    }

    @RequestMapping(value = "1001/004")
    public String handle01004(Model model) {
        // モデルに格納される値
        model.addAttribute("valueUsingNumberFormatPercent", 0.345);
        return "aply/showValueUsingNumberFormatPercent";
    }

    @RequestMapping(value = "1001/005")
    public String handle01005(Model model) {
        // モデルに格納される値
        model.addAttribute("valueUsingDateFormat", dateFactory.newDate());
        return "aply/showValueUsingDateFormat";
    }

    @RequestMapping(value = "1001/006")
    public String handle01006(Model model) {
        // モデルに格納される値
        Calendar calendar = Calendar.getInstance();
        calendar.set(2013, 11, 9);
        model.addAttribute("valueUsingCalendarFormat", calendar);
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

    @RequestMapping(value = "1004/001")
    public String handle04001(Model model, ThymeleafForm thymeleafForm) {
        return "aply/inputConditionalExpressions";
    }

    @RequestMapping(value = "1004/displaySwitching", method = RequestMethod.POST, params = "comparator")
    public String handleInputDisplayConditionalExpressionsUsingComparator(
            Model model, ThymeleafForm thymeleafForm) {
        return "aply/displayConditionalExpressionsUsingComparator";
    }

    @RequestMapping(value = "1004/002")
    public String handle04002(Model model, ThymeleafForm thymeleafForm) {
        return "aply/inputConditionalExpressions";
    }

    @RequestMapping(value = "1004/displaySwitching", method = RequestMethod.POST, params = "arithmeticOperation")
    public String handleInputDisplayConditionalExpressionsUsingArithmeticOperation(
            Model model, ThymeleafForm thymeleafForm) {
        return "aply/displayConditionalExpressionsUsingArithmeticOperation";
    }

    @RequestMapping(value = "1004/003")
    public String handle04003(Model model, ThymeleafForm thymeleafForm) {
        return "aply/inputConditionalExpressions";
    }

    @RequestMapping(value = "1004/displaySwitching", method = RequestMethod.POST, params = "defaultExpressions")
    public String handleInputDisplayDefaultExpressions(Model model,
            ThymeleafForm thymeleafForm) {
        return "aply/displayDefaultExpressions";
    }

    @RequestMapping(value = "1005/001")
    public String handle05001(Model model, ThymeleafForm thymeleafForm) {
        return "aply/inputDisplaySwitchingInModel";
    }

    @RequestMapping(value = "1005/displaySwitching", method = RequestMethod.POST, params = "thIfFormat")
    public String handleInputDisplaySwitchingUsingThIf(Model model,
            ThymeleafForm thymeleafForm) {
        return "aply/showDisplaySwitchingUsingThIf";
    }

    @RequestMapping(value = "1005/002")
    public String handle05002(Model model, ThymeleafForm thymeleafForm) {
        return "aply/inputDisplaySwitchingInModel";
    }

    @RequestMapping(value = "1005/displaySwitching", method = RequestMethod.POST, params = "thSwitchFormat")
    public String handleInputDisplaySwitchingUsingCChoose(Model model,
            ThymeleafForm thymeleafForm) {
        return "aply/showDisplaySwitchingUsingThSwitch";
    }

    @RequestMapping(value = "1006/001")
    public String handle06001(Model model, ThymeleafFormListForm form) {
        return "aply/inputCollectionInModel";
    }

    @RequestMapping(value = "1006/collectionInModel", method = RequestMethod.POST, params = "collectionInModel")
    public String handleInputCollectionInModel(Model model,
            ThymeleafFormListForm thymeleafFormListform) {
        return "aply/showCollectionInModel";
    }

    @RequestMapping(value = "1006/002")
    public String handle06002(Model model, ThymeleafFormListForm form) {
        return "aply/inputCollectionInModel";
    }

    @RequestMapping(value = "1006/collectionInModel", method = RequestMethod.POST, params = "collectionInModelUsingStatus")
    public String handleInputCollectionUsingThObject(Model model,
            ThymeleafFormListForm thymeleafFormListForm) {
        return "aply/showCollectionInModelUsingStatus";
    }

    @RequestMapping(value = "1007/001")
    public String handle07001(Model model, ThymeleafForm thymeleafForm) {
        return "aply/inputValue";
    }

    @RequestMapping(value = "1007/showValue", method = RequestMethod.POST, params = "thObject")
    public String handleInputValueUsingThObject(Model model,
            ThymeleafForm thymeleafForm) {
        return "aply/showValueUsingThObject";
    }

    @RequestMapping(value = "1007/002")
    public String handle07002(Model model, ThymeleafForm thymeleafForm) {
        return "aply/inputValue";
    }

    @RequestMapping(value = "1007/showValue", method = RequestMethod.POST, params = "thWith")
    public String handleInputValueUsingThWith(Model model,
            ThymeleafForm thymeleafForm) {
        return "aply/showValueUsingThWith";
    }

    @RequestMapping(value = "1008/001")
    public String handle08001(Model model, ThymeleafForm thymeleafForm) {
        return "aply/inputFormObjectBindHtmlForm";
    }

    @RequestMapping(value = "1008/bindFormObject", method = RequestMethod.POST, params = "bindFormObject")
    public String handleBindFormObject(Model model,
            ThymeleafForm thymeleafForm) {
        return "aply/showFormObjectBindHtmlForm";
    }
}
