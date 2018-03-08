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

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.ntt.fw.spring.functionaltest.domain.service.oth2.OauthRevokeTokenService;

@RestController
@RequestMapping("oth2")
public class OauthTokenRevocationRestController {

    @Inject
    OauthRevokeTokenService revokeTokenService;

    private static final Logger logger = LoggerFactory.getLogger(
            OauthTokenRevocationRestController.class);

    /**
     * <ul>
     * <li>Call service to revoke token and approval.</li>
     * </ul>
     * @param tokenValue
     * @param user
     * @return result of revoke token
     */
    @RequestMapping(value = "tokens/revoke_and_approvals", method = RequestMethod.POST)
    public ResponseEntity<Object> revokeAndApprovals(
            @RequestParam("token") String tokenValue,
            @AuthenticationPrincipal UserDetails user) {

        String clientId = user.getUsername();
        revokeTokenService.revokeTokenAndApprovals(tokenValue, clientId);

        logger.debug("[AUTH-SERVER]result of revoke : {} ", HttpStatus.OK);
        return ResponseEntity.ok().body(null);
    }

    /**
     * <ul>
     * <li>Call service to revoke token.</li>
     * </ul>
     * @param tokenValue
     * @param user
     * @return result of revoke token
     */
    @RequestMapping(value = "tokens/revoke", method = RequestMethod.POST)
    public ResponseEntity<Object> revokeToken(
            @RequestParam("token") String tokenValue,
            @AuthenticationPrincipal UserDetails user) {

        String clientId = user.getUsername();
        revokeTokenService.revokeToken(tokenValue, clientId);
        logger.debug("[AUTH-SERVER]result of revoke : {} ", HttpStatus.OK);
        return ResponseEntity.ok().body(null);

    }

}
