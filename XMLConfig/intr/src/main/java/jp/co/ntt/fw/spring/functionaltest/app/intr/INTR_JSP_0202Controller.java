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
package jp.co.ntt.fw.spring.functionaltest.app.intr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class INTR_JSP_0202Controller {

    @GetMapping(value = "001")
    public String handle001() {
        return "jsp/intr/userDetails";
    }

    @GetMapping(value = "004")
    public String handle004() {
        return "jsp/intr/userDetails";
    }

    @GetMapping(value = "005")
    public String handle005() {
        return "jsp/intr/userDetails";
    }

    @GetMapping(value = "006")
    public String handle006() {
        return "jsp/intr/changeLocaleCookie";
    }

    @GetMapping(value = "006_check")
    public String handle006_check() {
        return "jsp/intr/checkLocale";
    }

}
