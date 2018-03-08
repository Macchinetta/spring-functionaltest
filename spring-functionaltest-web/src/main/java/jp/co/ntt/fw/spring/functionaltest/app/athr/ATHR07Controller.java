/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.app.athr;

import java.security.Principal;

/*
 * Copyright(c) 2014-2015 NTT Corporation.
 */
import jp.co.ntt.fw.spring.functionaltest.domain.repository.cspr.CommitterCriteria;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ATHR07Controller {
    @RequestMapping("0701/001")
    public String handle0701001() {
        return "athr/loginForCustomizedAuthorizeErrorForAuthenticated";
    }

    @RequestMapping(value = "0701/001/afterLogin")
    public String handle0701001afterLogin(Principal principal, Model model) {
        Authentication authentication = (Authentication) principal;
        UserDetails userDetails = null;
        if (authentication != null) {
            userDetails = (UserDetails) authentication.getPrincipal();
        }
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
            model.addAttribute("userEmail", userDetails.getUsername()
                    + "@example.com");
        }
        return "athr/showCustomizedAuthorizeErrorForAuthenticated";
    }

    @RequestMapping(value = "0701/001/api/select", method = RequestMethod.GET)
    public void search(CommitterCriteria criteria) {

    }

}
