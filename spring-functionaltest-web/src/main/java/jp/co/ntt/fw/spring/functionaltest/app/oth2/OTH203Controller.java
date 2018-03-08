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
package jp.co.ntt.fw.spring.functionaltest.app.oth2;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.ntt.fw.spring.functionaltest.domain.service.oth2.OauthRemoteCoopService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.oth2.OauthResource;

@RequestMapping("oth2/client/03")
@Controller
public class OTH203Controller {

    private static final Logger logger = LoggerFactory.getLogger(
            OTH203Controller.class);

    @Inject
    OauthRemoteCoopService oauthRemoteCoopService;

    /**
     * <ul>
     * <li>Calls Service to send request with the authorization code grant(GET).</li>
     * </ul>
     * @param id
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/01/001/get/{id}", method = RequestMethod.GET)
    public String handle01001(@PathVariable("id") String id, Model model) {

        OauthResource response = oauthRemoteCoopService.getResource(id);

        String tokenValue = oauthRemoteCoopService.getTokenValue();
        logger.debug("Result response={}, token={}", response, tokenValue);

        model.addAttribute("response", response.getResult());
        model.addAttribute("businessId", response.getBusinessId());
        model.addAttribute("companyId", response.getCompanyId());
        model.addAttribute("title", "GETメソッドの結果");
        model.addAttribute("token", tokenValue);
        return "oth2/viewAdditionalValue";
    }

    /**
     * <ul>
     * <li>Calls Service to send request with the authorization code grant(POST).</li>
     * </ul>
     * @param id
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/01/002/post/{id}", method = RequestMethod.GET)
    public String handle01002(@PathVariable("id") String id, Model model) {

        OauthResource response = oauthRemoteCoopService.postResource(id);

        String tokenValue = oauthRemoteCoopService.getTokenValue();
        logger.debug("Result response={}, token={}", response, tokenValue);

        model.addAttribute("response", response.getResult());
        model.addAttribute("businessId", response.getBusinessId());
        model.addAttribute("companyId", response.getCompanyId());
        model.addAttribute("title", "POSTメソッドの結果");
        model.addAttribute("token", tokenValue);
        return "oth2/viewAdditionalValue";
    }

    /**
     * <ul>
     * <li>Calls Service to send request with the authorization code grant(PUT).</li>
     * </ul>
     * @param id
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/01/003/put/{id}", method = RequestMethod.GET)
    public String handle01003(@PathVariable("id") String id, Model model) {

        oauthRemoteCoopService.putResource(id);

        String tokenValue = oauthRemoteCoopService.getTokenValue();
        logger.debug("Result response={}, token={}", "-", tokenValue);

        model.addAttribute("response", "Success");
        model.addAttribute("businessId", "-");
        model.addAttribute("companyId", "-");
        model.addAttribute("title", "PUTメソッドの結果");
        model.addAttribute("token", tokenValue);
        return "oth2/viewAdditionalValue";
    }

    /**
     * <ul>
     * <li>Calls Service to send request with the authorization code grant(DELETE).</li>
     * </ul>
     * @param id
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/01/004/delete/{id}", method = RequestMethod.GET)
    public String handle01004(@PathVariable("id") String id, Model model) {

        oauthRemoteCoopService.deleteResource(id);

        String tokenValue = oauthRemoteCoopService.getTokenValue();
        logger.debug("Result response={}, token={}", "-", tokenValue);

        model.addAttribute("response", "Success");
        model.addAttribute("businessId", "-");
        model.addAttribute("companyId", "-");
        model.addAttribute("title", "DELETEメソッドの結果");
        model.addAttribute("token", tokenValue);
        return "oth2/viewAdditionalValue";
    }

    @RequestMapping(value = "/01/005/get/{id}", method = RequestMethod.GET)
    public String handle01005(@PathVariable("id") String id, Model model) {

        OauthResource response = oauthRemoteCoopService.getResourceDefault(id);

        String tokenValue = oauthRemoteCoopService.getTokenValue();
        logger.debug("Result response={}, token={}", response, tokenValue);

        model.addAttribute("response", response.getResult());
        model.addAttribute("name", response.getPrincipalString());
        model.addAttribute("title", "GETメソッドの結果");
        model.addAttribute("token", tokenValue);
        return "oth2/viewPrincipalName";
    }

    @RequestMapping(value = "/01/006/get/{id}", method = RequestMethod.GET)
    public String handle01006(@PathVariable("id") String id, Model model) {

        OauthResource response = oauthRemoteCoopService.getResourceUnauthorized(
                id);

        String tokenValue = oauthRemoteCoopService.getTokenValue();
        logger.debug("Result response={}, token={}", response, tokenValue);

        model.addAttribute("response", response.getResult());
        model.addAttribute("name", response.getPrincipalString());
        model.addAttribute("title", "GETメソッドの結果");
        model.addAttribute("token", tokenValue);
        return "oth2/viewPrincipalName";
    }
}
