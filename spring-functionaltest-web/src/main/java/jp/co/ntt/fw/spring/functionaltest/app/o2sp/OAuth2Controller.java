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
package jp.co.ntt.fw.spring.functionaltest.app.o2sp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("o2sp")
@Controller
public class OAuth2Controller {

    @GetMapping("login")
    public String login(Model model) {
        return "o2sp/login";
    }

    @GetMapping("login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return login(model);
    }

    @GetMapping("/")
    public String index(Model model) {
        return "o2sp/index";
    }
}
