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
package jp.co.ntt.fw.spring.functionaltest.app.spsm;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("thymeleaf")
@Controller
public class SPSM_Thymeleaf_04Controller {

    @GetMapping("0403/001/001/login")
    public String handle0401002001Login(Model model) {
        model.addAttribute("testNo", "0403/001/001");
        return "thymeleaf/spsm/loginForInvalidSession";
    }

    @GetMapping(value = "0403/001/001", params = "loginSuccess")
    public String handle0401002001LoginSuccess(@AuthenticationPrincipal User userDetails,
            Model model) {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("testNo", "0403/001/001");
        return "thymeleaf/spsm/validSession";
    }

    @GetMapping("0403/001/002/login")
    public String handle0401002002Login(Model model) {
        model.addAttribute("testNo", "0403/001/002");
        return "thymeleaf/spsm/loginForInvalidSession";
    }

    @GetMapping(value = "0403/001/002", params = "loginSuccess")
    public String handle0401002002LoginSuccess(@AuthenticationPrincipal User userDetails,
            Model model) {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("testNo", "0403/001/002");
        return "thymeleaf/spsm/validSession";
    }

}
