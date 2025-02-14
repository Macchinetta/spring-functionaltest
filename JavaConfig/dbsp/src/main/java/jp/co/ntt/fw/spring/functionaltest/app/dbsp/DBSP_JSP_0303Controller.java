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
package jp.co.ntt.fw.spring.functionaltest.app.dbsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenCheck;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenType;

@Controller
@RequestMapping("")
public class DBSP_JSP_0303Controller {
    @GetMapping(value = "003")
    public String first0303003() {
        return "jsp/dbsp/firstView0303003";
    }

    @PostMapping(value = "003", params = "second")
    @TransactionTokenCheck(type = TransactionTokenType.BEGIN)
    public String second0303003() {
        return "jsp/dbsp/secondView0303003";
    }

    @PostMapping(value = "003", params = "third")
    @TransactionTokenCheck
    public String third0303003() {
        return "jsp/dbsp/thirdView0303003";
    }

    @PostMapping(value = "003", params = "fourth")
    @TransactionTokenCheck
    public String fourth0303003() {
        return "jsp/dbsp/fourthView0303003";
    }

    @PostMapping(value = "003", params = "fifth")
    @TransactionTokenCheck
    public String fifth0303003() {
        return "redirect:/jsp/0303/003?complete";
    }

    @GetMapping(value = "003", params = "complete")
    public String complete0303003() {
        return "jsp/dbsp/fifthView";
    }

    @GetMapping(value = "004")
    public String first0303004() {
        return "jsp/dbsp/firstView0303004";
    }

    @PostMapping(value = "004", params = "second")
    @TransactionTokenCheck(type = TransactionTokenType.BEGIN, value = "create")
    public String second0303004() {
        return "jsp/dbsp/secondView0303004";
    }

    @PostMapping(value = "004", params = "third")
    @TransactionTokenCheck(value = "create")
    public String third0303004() {
        return "jsp/dbsp/thirdView0303004";
    }

    @PostMapping(value = "004", params = "fourth")
    @TransactionTokenCheck(value = "create")
    public String fourth0303004() {
        return "jsp/dbsp/fourthView0303004";
    }

    @PostMapping(value = "004", params = "fifth")
    @TransactionTokenCheck(value = "create")
    public String fifth0303004() {
        return "redirect:/jsp/0303/004?complete";
    }

    @GetMapping(value = "004", params = "complete")
    public String complete0303004() {
        return "jsp/dbsp/fifthView";
    }
}
