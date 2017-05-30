/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.api.oth2;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jp.co.ntt.fw.spring.functionaltest.domain.service.oth2.OauthRevokeTokenService;

@RestController
@RequestMapping("oth2")
public class OauthTokenRevocationRestController {

    @Inject
    OauthRevokeTokenService revokeTokenService;

    private static final Logger logger = LoggerFactory
            .getLogger(OauthTokenRevocationRestController.class);

    /**
     * <ul>
     * <li>Call service to revoke token.</li>
     * </ul>
     * @param token
     * @param user
     * @return result of revoke token
     */
    @RequestMapping(value = "tokens/revoke", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String revoke(@RequestParam("token") String token,
            @AuthenticationPrincipal UserDetails user) {

        String clientId = user.getUsername();
        String result = revokeTokenService.revokeToken(token, clientId);
        logger.debug("[AUTH-SERVER]result of revoke : {} ", result);
        return result;

    }

}
