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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.service.vldt.UserIdUseService;

@RequestMapping("thymeleaf")
@Controller
public class VLDT_Thymeleaf_0601Controller {

    @Inject
    UserIdUseService userIdUseService;

    @ModelAttribute
    public UserIdForm setUpUserIdForm() {
        return new UserIdForm();
    }

    @GetMapping(value = {"0601/001", "0601/002", "0601/003", "0601/004",})
    public String handle0101Init() {
        return "thymeleaf/vldt/userIdUseRequestForm";
    }

    @PostMapping(value = "0601/regist")
    public String handle0101001Regist(UserIdForm form, Model model) {

        String userId = "beforeNull".equals(form.getUserId()) ? null : form.getUserId();
        // テストの内容的にnullをメソッドに渡さないと意味がないため、SonarQube指摘に未対応としています。
        String convertUserId = userIdUseService.convertUserId(userId);

        model.addAttribute("userId", convertUserId);

        return "thymeleaf/vldt/userIdUseComplete";
    }

}
