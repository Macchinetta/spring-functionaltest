/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.jmss;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsAmqReceivingService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("jmss")
@Controller
public class JMSController {

    @Inject
    JmsAmqReceivingService jmsAmqReceivingService;

    @RequestMapping
    public String handle() {
        return "jmss/index";
    }

}
