/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.aply;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.terasoluna.gfw.common.date.jodatime.JodaTimeDateFactory;

@RequestMapping("aply")
@Controller
public class APLY03Controller {

    @Inject
    JodaTimeDateFactory dateFactory;

    @RequestMapping(value = "0301/001")
    public String handle01001(Model model) {
        return "aply/useCommonJsp";
    }

    @RequestMapping(value = "0302/001")
    public String handle02001(Model model) {
        // モデルに格納される値
        model.addAttribute("valueUsingEL", "<font color='red'>Sample</font>");
        return "aply/showValueUsingELHtmlEscapeInModel";
    }

    @RequestMapping(value = "0302/002")
    public String handle02002(Model model) {
        // モデルに格納される値
        model.addAttribute("valueUsingJSTL", "<font color='red'>Sample</font>");
        return "aply/showValueUsingJSTLHtmlEscapeInModel";
    }

    @RequestMapping(value = "0302/003")
    public String handle02003(Model model) {
        // モデルに格納される値
        model.addAttribute("valueUsingNumberFormat", 3333.55);
        return "aply/showValueUsingNumberFormatInModel";
    }

    @RequestMapping(value = "0302/004")
    public String handle02004(Model model) {
        // モデルに格納される値
        model.addAttribute("valueUsingDateFormat", dateFactory.newDate());
        return "aply/showValueUsingDateFormatInModel";
    }

    @RequestMapping(value = "0303/001")
    public String handle03001(Model model, JspForm form) {
        return "aply/inputDisplaySwitchingInModel";
    }

    @RequestMapping(value = "0303/displaySwitching", method = RequestMethod.POST, params = "cIfFormat")
    public String handleInputDisplaySwitchingUsingCIf(Model model, JspForm form) {
        return "aply/showDisplaySwitchingUsingCIf";
    }

    @RequestMapping(value = "0303/002")
    public String handle03002(Model model, JspForm form) {
        return "aply/inputDisplaySwitchingInModel";
    }

    @RequestMapping(value = "0303/displaySwitching", method = RequestMethod.POST, params = "cChooseFormat")
    public String handleInputDisplaySwitchingUsingCChoose(Model model,
            JspForm form) {
        return "aply/showDisplaySwitchingUsingCChoose";
    }

    @RequestMapping(value = "0304/001")
    public String handle04001(Model model, JspFormListForm form) {
        return "aply/inputCollectionInModel";
    }

    @RequestMapping(value = "0304/collectionInModel", method = RequestMethod.POST, params = "collectionInModel")
    public String handleInputCollectionInModel(Model model, JspFormListForm form) {
        return "aply/showCollectionInModel";
    }

    @RequestMapping(value = "0305/001")
    public String handle05001(Model model, JspForm form) {
        return "aply/inputFormObjectBindHtmlForm";
    }

    @RequestMapping(value = "0305/bindFormObject", method = RequestMethod.POST, params = "bindFormObject")
    public String handleBindFormObject(Model model, JspForm form) {
        return "aply/showFormObjectBindHtmlForm";
    }
}
