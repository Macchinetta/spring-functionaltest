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
package jp.co.ntt.fw.spring.functionaltest.app.intr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class INTR0202Controller {

    @RequestMapping(value = "001", method = RequestMethod.GET)
    public String handle001() {
        return "intr/userDetails";
    }

    @RequestMapping(value = "004", method = RequestMethod.GET)
    public String handle004() {
        return "intr/userDetails";
    }

    @RequestMapping(value = "005", method = RequestMethod.GET)
    public String handle005() {
        return "intr/userDetails";
    }

    @RequestMapping(value = "006", method = RequestMethod.GET)
    public String handle006() {
        return "intr/changeLocaleCookie";
    }

    @RequestMapping(value = "006_check", method = RequestMethod.GET)
    public String handle006_check() {
        return "intr/checkLocale";
    }

}
