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
package jp.co.ntt.fw.spring.functionaltest.api.oth2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jp.co.ntt.fw.spring.functionaltest.domain.model.OauthUser;
import jp.co.ntt.fw.spring.functionaltest.domain.service.oth2.OauthResource;

@RestController
@RequestMapping("oth2/resources/extend")
public class OauthExtendRestController {

    private static final Logger logger = LoggerFactory.getLogger(
            OauthExtendRestController.class);

    //
    // Extend @AuthenticationPrincipal argument.
    //

    /**
     * <ul>
     * <li>Get the resource.<br/>
     * This method can be called only when extending `@AuthenticationPrincipal` argument.</li>
     * </ul>
     * @param id Sample argument
     * @param oauthUser User and client Details
     * @return Resource
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public OauthResource getResource(@PathVariable("id") String id,
            @AuthenticationPrincipal OauthUser oauthUser) {

        if (logger.isDebugEnabled()) {
            logger.debug(
                    "called OAuth Resource/GET. id={}, clientid={}, company_id={}, business_id={}",
                    id, oauthUser.getClientId(), oauthUser.getCompanyId(),
                    oauthUser.getBusinessId());
        }

        OauthResource resource = new OauthResource(id, "GET");
        resource.setResultSuccess();
        resource.setBusinessId(oauthUser.getBusinessId());
        resource.setCompanyId(oauthUser.getCompanyId());

        return resource;
    }

    /**
     * <ul>
     * <li>Post the resource.<br/>
     * This method can be called only when extending `@AuthenticationPrincipal` argument.</li>
     * </ul>
     * @param id Sample argument
     * @param oauthUser User and client Details
     * @return Resource
     */
    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public OauthResource postResource(@PathVariable("id") String id,
            @AuthenticationPrincipal OauthUser oauthUser) {

        if (logger.isDebugEnabled()) {
            logger.debug(
                    "called OAuth Resource/POST. id={}, clientid={}, company_id={}, business_id={}",
                    id, oauthUser.getClientId(), oauthUser.getCompanyId(),
                    oauthUser.getBusinessId());
        }

        OauthResource resource = new OauthResource(id, "POST");
        resource.setResultSuccess();
        resource.setBusinessId(oauthUser.getBusinessId());
        resource.setCompanyId(oauthUser.getCompanyId());

        return resource;
    }

    /**
     * <ul>
     * <li>Put the resource.<br/>
     * This method can be called only when extending `@AuthenticationPrincipal` argument.</li>
     * </ul>
     * @param id Sample argument
     * @param oauthUser User and client Details
     * @return Resource
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public OauthResource putResource(@PathVariable("id") String id,
            @AuthenticationPrincipal OauthUser oauthUser) {

        if (logger.isDebugEnabled()) {
            logger.debug(
                    "called OAuth Resource/PUT. id={}, clientid={}, company_id={}, business_id={}",
                    id, oauthUser.getClientId(), oauthUser.getCompanyId(),
                    oauthUser.getBusinessId());
        }

        OauthResource resource = new OauthResource(id, "PUT");
        resource.setResultSuccess();
        resource.setBusinessId(oauthUser.getBusinessId());
        resource.setCompanyId(oauthUser.getCompanyId());

        return resource;
    }

    /**
     * <ul>
     * <li>Delete the resource.<br/>
     * This method doesn't return resource as processing result.<br/>
     * This method can be called only when extending `@AuthenticationPrincipal` argument.</li>
     * </ul>
     * @param id Sample argument
     * @param oauthUser User and client Details
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteResource(@PathVariable("id") String id,
            @AuthenticationPrincipal OauthUser oauthUser) {

        if (logger.isDebugEnabled()) {
            logger.debug(
                    "called OAuth Resource/DELETE. id={}, clientid={}, company_id={}, business_id={}",
                    id, oauthUser.getClientId(), oauthUser.getCompanyId(),
                    oauthUser.getBusinessId());
        }

        OauthResource resource = new OauthResource(id, "DELETE");
        resource.setResultSuccess();
        resource.setBusinessId(oauthUser.getBusinessId());
        resource.setCompanyId(oauthUser.getCompanyId());
        // nothing to do.
        return;
    }

}
