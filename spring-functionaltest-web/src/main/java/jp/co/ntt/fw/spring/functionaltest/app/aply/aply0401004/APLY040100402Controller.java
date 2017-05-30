/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.aply.aply0401004;

import jp.co.ntt.fw.spring.functionaltest.app.aply.aply0401004.LoginFormModelAttributeSetter.LoginFormModelAttribute;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception.IntentionalException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.terasoluna.gfw.common.message.ResultMessages;

@RequestMapping("aply")
@LoginFormModelAttribute
@Controller
public class APLY040100402Controller {

    @RequestMapping(value = "0401/004/02")
    public String handle040100402() {
        throw new IntentionalException(ResultMessages.error().add(
                "e.sf.cmmn.8003"));
    }

}
