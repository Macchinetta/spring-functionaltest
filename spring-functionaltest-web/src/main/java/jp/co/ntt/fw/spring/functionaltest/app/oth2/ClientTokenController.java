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
import jp.co.ntt.fw.spring.functionaltest.domain.service.oth2.OauthRevokeTokenClientService;

@RequestMapping("oth2/client/token")
@Controller
public class ClientTokenController {

    @Inject
    OauthRevokeTokenClientService revokeTokenClientService;

    /**
     * <ul>
     * <li>Calls Service to send revokeTokenRequest</li>
     * </ul>
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/revoke/authcode_db", method = RequestMethod.GET)
    public String revokeTokenAndApprovalsFromDb(Model model) {
        String result = revokeTokenClientService
                .revokeTokenAndApprovalsFromDb();
        model.addAttribute("result", result);
        return "oth2/revokeResult";
    }

    /**
     * <ul>
     * <li>Calls Service to send revokeTokenRequest</li>
     * </ul>
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/revoke/credential_db", method = RequestMethod.GET)
    public String revokeTokenFromDb(Model model) {
        String result = revokeTokenClientService.revokeTokenFromDb();
        model.addAttribute("result", result);
        return "oth2/revokeResult";
    }

    /**
     * <ul>
     * <li>Calls Service to send revokeTokenRequest</li>
     * </ul>
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/revoke/authcode_memory", method = RequestMethod.GET)
    public String revokeTokenAndApprovalsFromMemory(Model model) {
        String result = revokeTokenClientService
                .revokeTokenAndApprovalsFromMemory();
        model.addAttribute("result", result);
        return "oth2/revokeResult";
    }

    /**
     * <ul>
     * <li>Returns implicitRevokeToken.jsp and call processing to delete the token</li>
     * </ul>
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/revoke/tokenLocalstorage", method = RequestMethod.GET)
    public String revokeTokenFromLocalstorage(Model model) {
        return "oth2/implicitRevokeToken";
    }

}
