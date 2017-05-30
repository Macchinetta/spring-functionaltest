/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.lggn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.terasoluna.gfw.common.exception.SystemException;

@RequestMapping("lggn")
@Controller
public class LGGN0402001Controller {

    @RequestMapping(value = "0402/001", method = RequestMethod.GET)
    public String handle001() {
        throw new SystemException("e.ab.cd.5001", "SystemException occurred!");
    }
}
