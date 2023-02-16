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
package jp.co.ntt.fw.spring.functionaltest.app.thly;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("thly")
public class THLY02Controller {

    @ModelAttribute
    public StaffInfoForm setUpForm() {
        return new StaffInfoForm();
    }

    @GetMapping(value = "0201/001")
    public String handle0201001() {
        return "thly/createForm";
    }

    @GetMapping(value = "0201/002")
    public String handle0201002() {
        return "thly/createFormNoop";
    }

    @GetMapping(value = "0201/003")
    public String handle0201003() {
        return "thly/createFormOptParam";
    }

    @GetMapping(value = "0201/004")
    public String handle0201004() {
        return "thly/createFormOptParam";
    }

}
