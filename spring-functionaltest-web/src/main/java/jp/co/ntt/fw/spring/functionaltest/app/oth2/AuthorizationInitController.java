/*
 * Copyright 2014-2017 NTT Corporation.
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
 *
 */
package jp.co.ntt.fw.spring.functionaltest.app.oth2;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.ntt.fw.spring.functionaltest.domain.service.oth2.OauthAuthorizationInitService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.oth2.OauthInitContextTokenService;

/**
 * <ul>
 * <li>Calls Service to delete for approval information and access token.</li>
 * <li>This method delete approvals and tokens from DB.</li>
 * </ul>
 */
@RequestMapping("oth2/client/common")
@Controller
public class AuthorizationInitController {

    @Inject
    OauthAuthorizationInitService authorizationService;

    @Inject
    OauthInitContextTokenService oauthInitContextTokenService;

    /**
     * <ul>
     * <li>Calls Service to delete approvals and token.</li>
     * <li>This method delete approvals and tokens from DB, and delete context token from RestOperations.
     * </ul>
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public String handleClear(Model model) {

        authorizationService.deleteApproveAndToken();

        oauthInitContextTokenService.initContextTokenAll();

        model.addAttribute("response", "Success");
        model.addAttribute("title", "初期化の結果");
        model.addAttribute("token", "-");
        return "oth2/view";
    }
}
