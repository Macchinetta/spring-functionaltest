/*
 * Copyright(c) 2014 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.app.lggn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.terasoluna.gfw.common.exception.BusinessException;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.logger.FunctionalTestLogger;

@RequestMapping("lggn")
@Controller
public class LGGN0401Controller {

    private static final FunctionalTestLogger logger = FunctionalTestLogger
            .getLogger(LGGN0401Controller.class);

    @GetMapping(value = "0401/001")
    public String handle001() {
        logger.debug("debug log");
        logger.debug("debug log args:{}");
        logger.debug("debug log args:{}", "param1", "param2", "param3");
        return "lggn/index";
    }

    @GetMapping(value = "0401/002")
    public String handle002() {
        logger.info("i.ab.cd.1001");
        logger.info("i.ab.cd.1001", "replace_value_1");
        logger.info("i.ab.cd.1001", "replace_value_1_1", "replace_value_1_2");
        return "lggn/index";
    }

    @GetMapping(value = "0401/003")
    public String handle003() {
        logger.warn("w.ab.cd.2001");
        logger.warn("w.ab.cd.2001", "replace_value_2");
        logger.warn("w.ab.cd.2001", "replace_value_2_1", "replace_value_2_2");
        return "lggn/index";
    }

    @GetMapping(value = "0401/004")
    public String handle004() {
        logger.error("e.ab.cd.3001");
        logger.error("e.ab.cd.3001", "replace_value_3");
        logger.error("e.ab.cd.3001", "replace_value_3_1", "replace_value_3_2");
        return "lggn/index";
    }

    @GetMapping(value = "0401/005")
    public String handle005() {
        logger.trace("t.ab.cd.4001");
        logger.trace("t.ab.cd.4001", "replace_value_4");
        logger.trace("t.ab.cd.4001", "replace_value_4_1", "replace_value_4_2");
        return "lggn/index";
    }

    @GetMapping(value = "0401/006")
    public String handle006() {
        logger.info("i.ab.cd.1002");
        logger.info("i.ab.cd.1002", "replace_value_5");
        logger.info("i.ab.cd.1002", "replace_value_5_1", "replace_value_5_2");
        return "lggn/index";
    }

    @GetMapping(value = "0401/007")
    public String handle007() {
        logger.trace_noid("t.ab.cd.4001", "replace_value_7");
        return "lggn/index";
    }

    @GetMapping(value = "0401/008")
    public String handle008() {
        logger.warn("w.ab.cd.2001", new BusinessException("TestWarnException"),
                "replace_value_2");
        return "lggn/index";
    }

    @GetMapping(value = "0401/009")
    public String handle009() {
        logger.error("e.ab.cd.3001",
                new BusinessException("TestErrorException"), "replace_value_3");
        return "lggn/index";
    }
}
