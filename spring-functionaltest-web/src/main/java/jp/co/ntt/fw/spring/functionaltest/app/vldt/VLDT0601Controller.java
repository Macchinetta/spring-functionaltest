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
package jp.co.ntt.fw.spring.functionaltest.app.vldt;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.ntt.fw.spring.functionaltest.domain.service.vldt.UserIdUseService;

@RequestMapping("vldt")
@Controller
public class VLDT0601Controller {

    @Inject
    UserIdUseService userIdUseService;

    @ModelAttribute
    public UserIdForm setUpUserIdForm() {
        return new UserIdForm();
    }

    @RequestMapping(value = {"0601/001", "0601/002", "0601/003", "0601/004",},
            method = RequestMethod.GET)
    public String handle0101Init() {
        return "vldt/userIdUseRequestForm";
    }

    @RequestMapping(value = "0601/regist", method = RequestMethod.POST)
    public String handle0101001Regist(UserIdForm form, Model model) {

        String userId = "beforeNull".equals(form.getUserId()) ? null : form.getUserId();
        // テストの内容的にnullをメソッドに渡さないと意味がないため、SonarQube指摘に未対応としています。
        String convertUserId = userIdUseService.convertUserId(userId);

        model.addAttribute("userId", convertUserId);

        return "vldt/userIdUseComplete";
    }

}
