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
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.service.vldt.UserInfoUseBeanInput;
import jp.co.ntt.fw.spring.functionaltest.domain.service.vldt.UserInfoUseBeanOutput;
import jp.co.ntt.fw.spring.functionaltest.domain.service.vldt.UserInfoUseBeanService;

@RequestMapping("thymeleaf")
@Controller
public class VLDT_Thymeleaf_0602Controller {

    @Inject
    UserInfoUseBeanService userInfoUseBeanService;

    @Inject
    VLDT06Helper vLDT02Helper;

    @ModelAttribute
    public UserInfoForm setUserInfoForm() {
        return new UserInfoForm();
    }

    @GetMapping(value = {"0602/001", "0602/002", "0602/003", "0602/004", "0602/005", "0602/006",
            "0602/007", "0602/008", "0602/009"})
    public String handle0201001Init() {
        return "thymeleaf/vldt/userInfoUseBeanRequestForm";
    }

    @PostMapping(value = "0602/regist")
    public String handle0201001Regist(@Validated UserInfoForm form, BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "thymeleaf/vldt/userInfoUseBeanRequestForm";
        }

        UserInfoUseBeanInput userInfoUseBeanInput =
                vLDT02Helper.changeUserInfoFormToUserInfoUseBeanInput(form);
        UserInfoUseBeanOutput userInfoUseBeanOutput =
                userInfoUseBeanService.convertUserInfo(userInfoUseBeanInput);

        model.addAttribute("userInfo", userInfoUseBeanOutput.getUserInfo());
        return "thymeleaf/vldt/userInfoUseBeanComplete";
    }
}
