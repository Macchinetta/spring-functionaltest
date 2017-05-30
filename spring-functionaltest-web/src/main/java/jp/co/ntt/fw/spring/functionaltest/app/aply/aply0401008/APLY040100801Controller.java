/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.aply.aply0401008;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception.IntentionalException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.terasoluna.gfw.common.message.ResultMessages;

@RequestMapping("aply")
@Controller
public class APLY040100801Controller {

    @RequestMapping(value = "0401/008")
    public String handle040100801() {
        throw new IntentionalException(ResultMessages.error().add(
                "e.sf.cmmn.8003"));
    }

}
