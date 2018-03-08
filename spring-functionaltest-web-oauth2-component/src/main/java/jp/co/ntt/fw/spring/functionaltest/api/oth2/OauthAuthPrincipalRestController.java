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
package jp.co.ntt.fw.spring.functionaltest.api.oth2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jp.co.ntt.fw.spring.functionaltest.domain.service.oth2.OauthResource;

@RestController
@RequestMapping("oth2/resources/principal")
public class OauthAuthPrincipalRestController {

    private static final Logger logger = LoggerFactory.getLogger(
            OauthAuthPrincipalRestController.class);

    /**
     * <ul>
     * <li>Get the resource.</li>
     * </ul>
     * @param id Sample argument
     * @param authentication User/Client's authentication
     * @return Resource
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public OauthResource getResource(@PathVariable("id") String id,
            OAuth2Authentication authentication) {

        if (logger.isDebugEnabled()) {
            logger.debug(
                    "called OAuth Resource/GET. id={}, username={}, clientid={}",
                    id, authentication.getUserAuthentication().getName(),
                    authentication.getOAuth2Request().getClientId());
        }

        OauthResource resource = new OauthResource(id, "GET");
        resource.setPrincipalString(authentication.getOAuth2Request()
                .getClientId());
        resource.setResultSuccess();

        return resource;
    }

    /**
     * <ul>
     * <li>Post the resource.</li>
     * </ul>
     * @param resource Sample argument
     * @param authentication User/Client's authentication
     * @return Resource
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public OauthResource postResource(
            @RequestBody @Validated OauthResource resource,
            @AuthenticationPrincipal String name) {

        if (logger.isDebugEnabled()) {
            logger.debug("called OAuth Resource/POST. name={}", name);
        }

        resource.setPrincipalString(name);
        resource.setResultSuccess();

        return resource;
    }

    /**
     * <ul>
     * <li>Post the resource.</li>
     * </ul>
     * @param id Sample argument
     * @param authentication User/Client's authentication
     * @return Resource
     */
    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public OauthResource postResource(@PathVariable("id") String id,
            @AuthenticationPrincipal UserDetails user) {

        String userName = user.getUsername();
        if (logger.isDebugEnabled()) {
            logger.debug("called OAuth Resource/POST. username={}", userName);
        }

        OauthResource resource = new OauthResource(id, "POST");
        resource.setPrincipalString(userName);
        resource.setResultSuccess();

        return resource;
    }

    /**
     * <ul>
     * <li>Put the resource.</li>
     * </ul>
     * @param id Sample argument
     * @param authentication User/Client's authentication
     * @return Resource
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public OauthResource putResource(@PathVariable("id") String id,
            OAuth2Authentication authentication) {

        if (logger.isDebugEnabled()) {
            logger.debug(
                    "called OAuth Resource/PUT. id={}, username={}, clientid={}",
                    id, authentication.getUserAuthentication().getName(),
                    authentication.getOAuth2Request().getClientId());
        }

        OauthResource resource = new OauthResource(id, "PUT");
        resource.setResultSuccess();

        return resource;
    }

    /**
     * <ul>
     * <li>Delete the resource.<br/>
     * This method doesn't return resource as processing result.</li>
     * </ul>
     * @param id Sample argument
     * @param authentication User/Client's authentication
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteResource(@PathVariable("id") String id,
            OAuth2Authentication authentication) {

        if (logger.isDebugEnabled()) {
            logger.debug(
                    "called OAuth Resource/DELETE. id={}, username={}, clientid={}",
                    id, authentication.getUserAuthentication().getName(),
                    authentication.getOAuth2Request().getClientId());
        }

        OauthResource resource = new OauthResource(id, "DELETE");
        resource.setResultSuccess();
        // nothing to do.
        return;
    }

}
