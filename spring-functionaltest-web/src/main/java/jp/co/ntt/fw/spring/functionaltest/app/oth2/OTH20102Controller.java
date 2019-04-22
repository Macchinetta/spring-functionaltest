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
package jp.co.ntt.fw.spring.functionaltest.app.oth2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("oth2/client/01/02")
@Controller
public class OTH20102Controller {

    @Value("${oth2.applicationContextUrl}")
    String applicationContextUrl;

    @Value("${oth2.applicationContextUrl}/oth2/client")
    String clientUrl;

    @Value("${oth2.databaseApplicationContextUrl}/oth2")
    String authServerUrl;

    @Value("${oth2.databaseApplicationContextUrl}${oth2.restServletPath}/oth2/resources")
    String resourceServerUrl;

    String clientId = "testClient";

    String readOnlyClientId = "testClientReadOnly";

    /**
     * <ul>
     * <li>Returns implicitGet.jsp to send request with the implicit grant(GET)</li>
     * </ul>
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/001/get", method = RequestMethod.GET)
    public String handle02001(Model model) {
        model.addAttribute("oauth2ClientUrl", clientUrl);
        model.addAttribute("oauth2ResourceServerUrl", resourceServerUrl);
        model.addAttribute("oauth2AuthServerUrl", authServerUrl);
        model.addAttribute("oauth2ApplicationContextUrl",
                applicationContextUrl);
        model.addAttribute("clientId", clientId);
        return "oth2/implicitGet";
    }

    /**
     * <ul>
     * <liReturns implicitPost.jsp to send request with the implicit grant(POST)</li>
     * </ul>
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/002/post", method = RequestMethod.GET)
    public String handle02002(Model model) {
        model.addAttribute("oauth2ClientUrl", clientUrl);
        model.addAttribute("oauth2ResourceServerUrl", resourceServerUrl);
        model.addAttribute("oauth2AuthServerUrl", authServerUrl);
        model.addAttribute("oauth2ApplicationContextUrl",
                applicationContextUrl);
        model.addAttribute("clientId", clientId);
        return "oth2/implicitPost";
    }

    /**
     * <ul>
     * <li>Returns implicitPut.jsp to send request with the implicit grant(PUT)</li>
     * </ul>
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/003/put", method = RequestMethod.GET)
    public String handle02003(Model model) {
        model.addAttribute("oauth2ClientUrl", clientUrl);
        model.addAttribute("oauth2ResourceServerUrl", resourceServerUrl);
        model.addAttribute("oauth2AuthServerUrl", authServerUrl);
        model.addAttribute("oauth2ApplicationContextUrl",
                applicationContextUrl);
        model.addAttribute("clientId", clientId);
        return "oth2/implicitPut";
    }

    /**
     * <ul>
     * <li>Returns implicitDelete.jsp to send request with the implicit grant(DELETE)</li>
     * </ul>
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/004/delete", method = RequestMethod.GET)
    public String handle02004(Model model) {
        model.addAttribute("oauth2ClientUrl", clientUrl);
        model.addAttribute("oauth2ResourceServerUrl", resourceServerUrl);
        model.addAttribute("oauth2AuthServerUrl", authServerUrl);
        model.addAttribute("oauth2ApplicationContextUrl",
                applicationContextUrl);
        model.addAttribute("clientId", clientId);
        return "oth2/implicitDelete";
    }

    /**
     * <ul>
     * <liReturns implicitPost.jsp to send request with the implicit grant(DELETE:not registered in client)</li>
     * </ul>
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/010/delete", method = RequestMethod.GET)
    public String handle02010(Model model) {
        model.addAttribute("oauth2ClientUrl", clientUrl);
        model.addAttribute("oauth2ResourceServerUrl", resourceServerUrl);
        model.addAttribute("oauth2AuthServerUrl", authServerUrl);
        model.addAttribute("oauth2ApplicationContextUrl",
                applicationContextUrl);
        model.addAttribute("clientId", readOnlyClientId);
        return "oth2/implicitDelete";
    }
}
