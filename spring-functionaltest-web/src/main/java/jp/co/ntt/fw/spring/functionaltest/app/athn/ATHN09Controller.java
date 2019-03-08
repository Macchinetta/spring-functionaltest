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
package jp.co.ntt.fw.spring.functionaltest.app.athn;

import jp.co.ntt.fw.spring.functionaltest.domain.service.athn.AccountUserDetails;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ATHN09Controller {

    @RequestMapping(value = "0901/001", params = "loginSuccess")
    public String handle0901LoginSuccess(Model model) {

        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication(); // (1)
        String userUuid = null;

        if (authentication.getPrincipal() instanceof AccountUserDetails) {
            AccountUserDetails userDetails = AccountUserDetails.class.cast(
                    authentication.getPrincipal()); // (2)
            userUuid = userDetails.getAccount().getUserUuid(); // (3)

            model.addAttribute("userUuid", userUuid);
        }

        model.addAttribute("testNo", "0901/001");
        return "athn/topForDispAuthentication";
    }

}
