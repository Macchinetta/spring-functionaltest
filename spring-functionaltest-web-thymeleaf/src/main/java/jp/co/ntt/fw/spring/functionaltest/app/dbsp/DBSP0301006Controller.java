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
package jp.co.ntt.fw.spring.functionaltest.app.dbsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenCheck;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenType;

@Controller
@RequestMapping("dbsp/0301/006")
public class DBSP0301006Controller {

    @GetMapping
    public String first() {
        return "dbsp/firstView0301006";
    }

    @PostMapping(params = "second")
    @TransactionTokenCheck(value = "create", type = TransactionTokenType.BEGIN)
    public String second() {
        return "dbsp/secondView0301006";
    }

    @PostMapping(params = "third")
    @TransactionTokenCheck(value = "create")
    public String third() {
        return "dbsp/thirdView0301006";
    }

    @PostMapping(params = "fourth")
    @TransactionTokenCheck(value = "create")
    public String fourth() {
        return "dbsp/fourthView0301006";
    }

    @PostMapping(params = "fifth")
    @TransactionTokenCheck(value = "create")
    public String fifth() {
        return "redirect:/dbsp/0301/006?complete";
    }

    @GetMapping(params = "complete")
    public String complete() {
        return "dbsp/fifthView0301006";
    }

}
