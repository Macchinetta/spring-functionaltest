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
package jp.co.ntt.fw.spring.functionaltest.app.dbsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenCheck;
import org.terasoluna.gfw.web.token.transaction.TransactionTokenType;

@Controller
@RequestMapping("")
public class DBSP0303Controller {
    @RequestMapping(value = "003", method = RequestMethod.GET)
    public String first0303003() {
        return "dbsp/firstView0303003";
    }

    @RequestMapping(value = "003", params = "second", method = RequestMethod.POST)
    @TransactionTokenCheck(type = TransactionTokenType.BEGIN)
    public String second0303003() {
        return "dbsp/secondView0303003";
    }

    @RequestMapping(value = "003", params = "third", method = RequestMethod.POST)
    @TransactionTokenCheck
    public String third0303003() {
        return "dbsp/thirdView0303003";
    }

    @RequestMapping(value = "003", params = "fourth", method = RequestMethod.POST)
    @TransactionTokenCheck
    public String fourth0303003() {
        return "dbsp/fourthView0303003";
    }

    @RequestMapping(value = "003", params = "fifth", method = RequestMethod.POST)
    @TransactionTokenCheck
    public String fifth0303003() {
        return "redirect:/dbsp/0303/003?complete";
    }

    @RequestMapping(value = "003", params = "complete", method = RequestMethod.GET)
    public String complete0303003() {
        return "dbsp/fifthView";
    }

    @RequestMapping(value = "004", method = RequestMethod.GET)
    public String first0303004() {
        return "dbsp/firstView0303004";
    }

    @RequestMapping(value = "004", params = "second", method = RequestMethod.POST)
    @TransactionTokenCheck(type = TransactionTokenType.BEGIN, value = "create")
    public String second0303004() {
        return "dbsp/secondView0303004";
    }

    @RequestMapping(value = "004", params = "third", method = RequestMethod.POST)
    @TransactionTokenCheck(value = "create")
    public String third0303004() {
        return "dbsp/thirdView0303004";
    }

    @RequestMapping(value = "004", params = "fourth", method = RequestMethod.POST)
    @TransactionTokenCheck(value = "create")
    public String fourth0303004() {
        return "dbsp/fourthView0303004";
    }

    @RequestMapping(value = "004", params = "fifth", method = RequestMethod.POST)
    @TransactionTokenCheck(value = "create")
    public String fifth0303004() {
        return "redirect:/dbsp/0303/004?complete";
    }

    @RequestMapping(value = "004", params = "complete", method = RequestMethod.GET)
    public String complete0303004() {
        return "dbsp/fifthView";
    }
}
