/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.aply;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception.IntentionalException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.terasoluna.gfw.common.message.ResultMessages;

@Controller
@RequestMapping("aply")
public class APLY04Controller {

    @RequestMapping(value = "0401/001")
    public String handle01001(Model model) {
        return "aply/controllerCommonComplete";
    }

    @RequestMapping(value = "0401/002")
    public String handle01002(Model model) {
        return "aply/controllerCommonComplete";
    }

    // APLY0401003は APLY0401003Controller参照

    @RequestMapping(value = "0402/001")
    public String handle02001(Model model) {
        return "aply/controllerCommonComplete";
    }

    @RequestMapping(value = "0402/002")
    public String handle02002() {
        throw new IntentionalException(ResultMessages.error().add(
                "e.sf.cmmn.8003"));
    }
}
