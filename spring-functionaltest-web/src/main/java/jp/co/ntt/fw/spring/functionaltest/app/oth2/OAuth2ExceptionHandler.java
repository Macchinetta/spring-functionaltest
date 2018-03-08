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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice(basePackages = "jp.co.ntt.fw.spring.functionaltest.app.oth2")
public class OAuth2ExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(
            OAuth2ExceptionHandler.class);

    @ExceptionHandler(UserDeniedAuthorizationException.class)
    public String handleUserDeniedAuthorizationException(
            UserDeniedAuthorizationException e, RedirectAttributes attributes) {
        logger.debug("error={}, error_description={}", e.getOAuth2ErrorCode(), e
                .getMessage());
        attributes.addFlashAttribute("exceptionCode",
                OAuth2Constants.ACCESS_DENIED_ERROR_CODE);
        attributes.addFlashAttribute("exception", e);
        return "redirect:" + OAuth2Constants.URL_ACCESS_DENIED_ERROR_PAGE;
    }

    @ExceptionHandler(OAuth2Exception.class)
    public String handleOAuth2Exception(OAuth2Exception e,
            RedirectAttributes attributes) {
        logger.debug("error={}, error_description={}", e.getOAuth2ErrorCode(), e
                .getMessage());
        attributes.addFlashAttribute("exceptionCode",
                OAuth2Constants.SYSTEM_ERROR_CODE);
        attributes.addFlashAttribute("exception", e);
        return "redirect:" + OAuth2Constants.URL_SYSTEM_ERROR_PAGE;
    }

    @ExceptionHandler(HttpStatusCodeException.class)
    public String handleHttpStatusCodeException(HttpStatusCodeException e,
            RedirectAttributes attributes) {
        logger.debug("status code={}, message={}", e.getStatusCode(), e
                .getMessage());
        attributes.addFlashAttribute("exceptionCode",
                OAuth2Constants.SYSTEM_ERROR_CODE);
        attributes.addFlashAttribute("exception", e);
        return "redirect:" + OAuth2Constants.URL_SYSTEM_ERROR_PAGE;
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public String handleInsufficientAuthenticationException(
            InsufficientAuthenticationException e,
            RedirectAttributes attributes) {
        logger.debug("message={}", e.getMessage());
        attributes.addFlashAttribute("exceptionCode",
                OAuth2Constants.SYSTEM_ERROR_CODE);
        attributes.addFlashAttribute("exception", e);
        return "redirect:" + OAuth2Constants.URL_SYSTEM_ERROR_PAGE;
    }
}
