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

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.ntt.fw.spring.functionaltest.domain.service.oth2.OauthAuthCodeService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.oth2.OauthResource;

@RequestMapping("oth2/client/01/01")
@Controller
public class OTH20101Controller {

    private static final Logger logger = LoggerFactory.getLogger(
            OTH20101Controller.class);

    @Inject
    OauthAuthCodeService oauthAuthCodeService;

    /**
     * <ul>
     * <li>Calls Service to send request with the authorization code grant(GET)</li>
     * </ul>
     * @param id
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/001/get", method = RequestMethod.GET)
    public String handle01001(@RequestParam("id") String id, Model model) {

        OauthResource response = oauthAuthCodeService.getAuthCodeGrantResource(
                id);

        String tokenValue = oauthAuthCodeService.getAuthCodeTokenValue();
        logger.debug("Result response={}, token={}", response, tokenValue);

        model.addAttribute("response", response.getResult());
        model.addAttribute("title", "GETメソッドの結果");
        model.addAttribute("token", tokenValue);
        model.addAttribute("name", response.getPrincipalString());
        return "oth2/viewPrincipalName";
    }

    /**
     * <ul>
     * <li>Calls Service to send request with the authorization code grant(POST)</li>
     * </ul>
     * @param id
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/002/post", method = RequestMethod.GET)
    public String handle01002(@RequestParam("id") String id, Model model) {

        OauthResource response = oauthAuthCodeService.postAuthCodeGrantResource(
                id);

        String tokenValue = oauthAuthCodeService.getAuthCodeTokenValue();
        logger.debug("Result response={}, token={}", response, tokenValue);

        model.addAttribute("response", response.getResult());
        model.addAttribute("title", "POSTメソッドの結果");
        model.addAttribute("token", tokenValue);
        model.addAttribute("name", response.getPrincipalString());
        return "oth2/viewPrincipalName";
    }

    /**
     * <ul>
     * <li>Calls Service to send request with the authorization code grant(PUT)</li>
     * </ul>
     * @param id
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/003/put/{id}", method = RequestMethod.GET)
    public String handle01003(@PathVariable("id") String id, Model model) {

        oauthAuthCodeService.putAuthCodeGrantResource(id);

        String tokenValue = oauthAuthCodeService.getAuthCodeTokenValue();
        logger.debug("Result response={}, token={}", "-", tokenValue);

        model.addAttribute("response", "Success");
        model.addAttribute("title", "PUTメソッドの結果");
        model.addAttribute("token", tokenValue);
        return "oth2/view";
    }

    /**
     * <ul>
     * <li>Calls Service to send request with the authorization code grant(DELETE)</li>
     * </ul>
     * @param id
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/004/delete/{id}", method = RequestMethod.GET)
    public String handle01004(@PathVariable("id") String id, Model model) {

        oauthAuthCodeService.deleteAuthCodeGrantResource(id);

        String tokenValue = oauthAuthCodeService.getAuthCodeTokenValue();
        logger.debug("Result response={}, token={}", "-", tokenValue);

        model.addAttribute("response", "Success");
        model.addAttribute("title", "DELETEメソッドの結果");
        model.addAttribute("token", tokenValue);
        return "oth2/view";
    }

    /**
     * <ul>
     * <li>Calls Service to send request with the authorization code grant(DELETE:not registerd in client)</li>
     * </ul>
     * @param model
     * @return path of the view
     */
    @RequestMapping(value = "/010/delete/{id}", method = RequestMethod.GET)
    public String handle01010(@PathVariable("id") String id, Model model) {

        oauthAuthCodeService.deleteNotRegistAuthCodeGrantResource(id);

        model.addAttribute("title", "DELETEメソッドの結果");
        model.addAttribute("token", "-");
        return "oth2/view";
    }

}
