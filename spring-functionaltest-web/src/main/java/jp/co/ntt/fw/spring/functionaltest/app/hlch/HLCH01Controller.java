/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.hlch;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.service.hlch.HealthCheckService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("hlch")
@Controller
public class HLCH01Controller {

    @Inject
    HealthCheckService healthcheckService;

    @RequestMapping(value = "0101/001")
    public String handle0101001() {
        healthcheckService.healthcheck();
        return "common/hlch/ok";
    }

    @RequestMapping(value = "0101/002")
    public String handle0101002() {
        healthcheckService.healthcheck();
        return "common/hlch/okTrimDirectiveWhitespaces";
    }

}
