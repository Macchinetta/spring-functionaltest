/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.hlch;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.service.hlch.HealthCheckExceptionService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("hlch")
@Controller
public class HLCH02Controller {

    @Inject
    HealthCheckExceptionService healthcheckexceptionService;

    @RequestMapping(value = "0201/001")
    public String handle0201001() {
        healthcheckexceptionService.healthcheckexception();
        return "common/hlch/ok";
    }

}
