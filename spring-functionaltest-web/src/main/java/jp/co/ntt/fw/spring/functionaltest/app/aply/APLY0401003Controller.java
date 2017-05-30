/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.aply;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception.IntentionalException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.terasoluna.gfw.common.message.ResultMessages;

@RequestMapping("aply")
@Controller
public class APLY0401003Controller {

    @RequestMapping(value = "0401/003")
    public String handle01003() {
        throw new IntentionalException(ResultMessages.error().add(
                "e.sf.cmmn.8003"));
    }

}
