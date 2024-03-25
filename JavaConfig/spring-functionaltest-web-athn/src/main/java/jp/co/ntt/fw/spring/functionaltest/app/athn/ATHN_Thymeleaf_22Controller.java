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
package jp.co.ntt.fw.spring.functionaltest.app.athn;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ATHN_Thymeleaf_22Controller {

    @GetMapping("2201/001")
    public String handle2201001() {
        return "thymeleaf/athn/loginForRememberMe";
    }

    @GetMapping(value = "2201", params = "loginSuccess")
    public String handle2201LoginSuccess(
            @AuthenticationPrincipal User userDetails, Model model) {
        // RememberMe機能を使用しない場合、認証済み情報は取得できないため、
        // 認証済み情報が存在するかチェックする。
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
            model.addAttribute("userEmail", userDetails.getUsername()
                    + "@example.com");
        }
        return "thymeleaf/athn/rememberMeUserInfoDsp";
    }
}
