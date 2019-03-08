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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = OAuth2ResourceOwnerPasswordCredentialsGrant.class)
@Order(0)
public class OAuth2ResourceOwnerPasswordCredentialsGrantExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(
            OAuth2ResourceOwnerPasswordCredentialsGrantExceptionHandler.class);

    @ExceptionHandler(OAuth2AccessDeniedException.class)
    public String handleOAuth2AccessDeniedException(
            OAuth2AccessDeniedException e, Model model) {

        logger.debug("error={}, error_description={}", e.getOAuth2ErrorCode(), e
                .getMessage());
        model.addAttribute("exception", e);
        Throwable cause = e.getCause();
        if (cause instanceof InvalidGrantException) {
            return handleInvalidGrantException(((InvalidGrantException) cause),
                    model);
        }

        model.addAttribute("exceptionCode", OAuth2Constants.SYSTEM_ERROR_CODE);
        model.addAttribute("cause", cause);

        return OAuth2Constants.VIEW_NAME_SYSTEM_ERROR_PAGE;
    }

    private String handleInvalidGrantException(InvalidGrantException cause,
            Model model) {
        model.addAttribute("exceptionCode",
                OAuth2Constants.ACCESS_DENIED_ERROR_CODE);
        model.addAttribute("cause", cause);

        return OAuth2Constants.VIEW_NAME_ACCESS_DENIED_ERROR_PAGE;
    }

}
