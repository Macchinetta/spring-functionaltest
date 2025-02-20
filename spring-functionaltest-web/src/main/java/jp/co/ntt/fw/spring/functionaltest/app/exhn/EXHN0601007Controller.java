/*
 * Copyright(c) 2025 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.app.exhn;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.ntt.fw.spring.functionaltest.domain.cmmn.exception.SubClassException;
import jp.co.ntt.fw.spring.functionaltest.domain.service.exhn.ThrowExceptionService;

@Controller
@RequestMapping("exhn")
public class EXHN0601007Controller {

    @Inject
    ThrowExceptionService throwExceptionService;

    @GetMapping(value = "0601/007")
    public String employeeUpdate(Model model) throws SubClassException {

        throwExceptionService.throwSubClassException();

        return "exhn/index";
    }
}
