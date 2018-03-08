/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
