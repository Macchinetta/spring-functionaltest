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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.ntt.fw.spring.functionaltest.domain.service.oth2.OauthAuthCodeService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.oth2.OauthResource;

@RequestMapping("oth2/client/05")
@Controller
public class OTH205Controller {

    private static final Logger logger = LoggerFactory.getLogger(
            OTH205Controller.class);

    @Inject
    OauthAuthCodeService oauthAuthCodeService;

    @Inject
    ResourceOwnerPasswordResourceDetails passGrantResource;

    /**
     * <ul>
     * <li>Calls Service to send request with the authorization code grant(GET).</li>
     * </ul>
     * @param id
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/02/001/get/{id}", method = RequestMethod.GET)
    public String handle01001(@PathVariable("id") String id, Model model) {

        OauthResource response = oauthAuthCodeService
                .getAuthCodeGrantResourceByScope(id);

        String tokenValue = oauthAuthCodeService.getAuthCodeByScopeTokenValue();
        logger.debug("Result response={}, token={}", response, tokenValue);

        model.addAttribute("response", response.getResult());
        model.addAttribute("title", "GETメソッドの結果");
        model.addAttribute("token", tokenValue);
        return "oth2/view";
    }
}
