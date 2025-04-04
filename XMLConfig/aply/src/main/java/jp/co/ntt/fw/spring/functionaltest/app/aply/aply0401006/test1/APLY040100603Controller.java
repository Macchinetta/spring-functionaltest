/*
 * Copyright(c) 2024 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.app.aply.aply0401006.test1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.terasoluna.gfw.common.message.ResultMessages;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.exception.IntentionalException;

@RequestMapping(value = {"jsp", "thymeleaf"})
@Controller
public class APLY040100603Controller {

    @GetMapping(value = "0401/006/03")
    public String handle040100603() {
        throw new IntentionalException(ResultMessages.error().add("e.sf.fw.8003"));
    }

}
