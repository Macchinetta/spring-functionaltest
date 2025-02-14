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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jp.co.ntt.fw.spring.functionaltest.domain.service.athn.AccountUserDetails;

@Controller
public class ATHN_JSP_06Controller {

    private static final Logger logger = LoggerFactory.getLogger(ATHN_JSP_06Controller.class);

    @GetMapping(value = "0601/001", params = "loginSuccess")
    public String handle0601LoginSuccess(@AuthenticationPrincipal AccountUserDetails userDetails,
            Model model) {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("testNo", "0601/001");
        logger.debug("AccountUserDetails password:[" + userDetails.getPassword() + "]");
        return "jsp/athn/topForDefault";
    }

}
