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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.ntt.fw.spring.functionaltest.domain.service.oth2.OauthIllegalParamAuthCodeService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.oth2.OauthResource;

@RequestMapping("oth2/client/11")
@Controller
public class OTH211Controller {

    private static final Logger logger = LoggerFactory.getLogger(
            OTH211Controller.class);

    @Inject
    OauthIllegalParamAuthCodeService oauthIllegalParamAuthCodeService;

    @Inject
    ResourceOwnerPasswordResourceDetails passGrantResource;

    @Value("${oth2.applicationContextUrl}")
    String applicationContextUrl;

    @Value("${oth2.applicationContextUrl}/oth2/client")
    String clientUrl;

    @Value("${oth2.databaseApplicationContextUrl}/oth2")
    String authServerUrl;

    @Value("${oth2.databaseApplicationContextUrl}${oth2.restServletPath}/oth2/resources")
    String resourceServerUrl;

    String clientId = "testClient";

    /**
     * <ul>
     * <li>Calls Service to send request with the authorization code grant(GET).</li>
     * </ul>
     * @param id
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/01/002/get/{id}", method = RequestMethod.GET)
    public String handle03001(@PathVariable("id") String id, Model model) {

        OauthResource response = oauthIllegalParamAuthCodeService
                .getResourceByIllegalSecret(id);

        logger.debug("Result response={}", response);

        model.addAttribute("response", response.getResult());
        model.addAttribute("title", "GETメソッドの結果");
        return "oth2/view";
    }

    /**
     * <ul>
     * <li>Calls Service to send request with the authorization code grant(GET).</li>
     * </ul>
     * @param id
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/01/004/get/{id}", method = RequestMethod.GET)
    public String handle01004(@PathVariable("id") String id, Model model) {

        OauthResource response = oauthIllegalParamAuthCodeService
                .getResourceByIllegalResourceId(id);

        logger.debug("Result response={}", response);

        model.addAttribute("response", response.getResult());
        model.addAttribute("title", "GETメソッドの結果");
        return "oth2/view";
    }

    /**
     * <ul>
     * <li>Calls Service to send request with the implicit grant(GET).</li>
     * </ul>
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/01/006/get", method = RequestMethod.GET)
    public String handle01006(Model model) {
        model.addAttribute("oauth2ClientUrl", clientUrl);
        model.addAttribute("oauth2ResourceServerUrl", resourceServerUrl);
        model.addAttribute("oauth2AuthServerUrl", authServerUrl);
        model.addAttribute("oauth2ApplicationContextUrl",
                applicationContextUrl);
        model.addAttribute("clientId", clientId);
        return "oth2/implicitDelayedGet";
    }
}
