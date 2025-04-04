/*
 * Copyright(c) 2024 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.domain.cmmn.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationFailureCredentialsExpiredEvent;
import org.springframework.security.authentication.event.AuthenticationFailureDisabledEvent;
import org.springframework.security.authentication.event.AuthenticationFailureExpiredEvent;
import org.springframework.security.authentication.event.AuthenticationFailureLockedEvent;
import org.springframework.security.authentication.event.AuthenticationFailureServiceExceptionEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.security.web.authentication.session.SessionFixationProtectionEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEventListeners {

    private static final Logger logger =
            LoggerFactory.getLogger(AuthenticationEventListeners.class);

    @EventListener(AuthenticationFailureBadCredentialsEvent.class)
    public void handleBadCredentials(AuthenticationFailureBadCredentialsEvent event) {
        logger.info("Bad credentials is detected. username : {}",
                event.getAuthentication().getName());
    }

    @EventListener(AuthenticationFailureDisabledEvent.class)
    public void handleAuthenticationFailureDisabled(AuthenticationFailureDisabledEvent event) {
        logger.info("User deisabled is detected. username : {}",
                event.getAuthentication().getName());
    }

    @EventListener(AuthenticationFailureLockedEvent.class)
    public void handleAuthenticationFailureLocked(AuthenticationFailureLockedEvent event) {
        logger.info("User locked is detected. username : {}", event.getAuthentication().getName());
    }

    @EventListener(AuthenticationFailureExpiredEvent.class)
    public void handleAuthenticationFailureExpired(AuthenticationFailureExpiredEvent event) {
        logger.info("Authentication expired is detected. username : {}",
                event.getAuthentication().getName());
    }

    @EventListener(AuthenticationFailureCredentialsExpiredEvent.class)
    public void handleAuthenticationFailureCredentialsExpired(
            AuthenticationFailureCredentialsExpiredEvent event) {
        logger.info("Credentials expired is detected. username : {}",
                event.getAuthentication().getName());
    }

    @EventListener(AuthenticationFailureServiceExceptionEvent.class)
    public void handleAuthenticationFailureServiceExceptionEvent(
            AuthenticationFailureServiceExceptionEvent event) {
        logger.info("ServiceException is detected. username : {}",
                event.getAuthentication().getName());
    }

    @EventListener(AuthenticationSuccessEvent.class)
    public void handleAuthenticationSuccess(AuthenticationSuccessEvent event) {
        logger.info("Autnenticated. username : {}", event.getAuthentication().getName());
    }

    @EventListener(SessionFixationProtectionEvent.class)
    public void handleSessionFixationProtection(SessionFixationProtectionEvent event) {
        logger.info("Session changed. sessionId : {}",
                event.getOldSessionId() + " to " + event.getNewSessionId());
    }

    @EventListener(InteractiveAuthenticationSuccessEvent.class)
    public void handleInteractiveAuthenticationSuccess(
            InteractiveAuthenticationSuccessEvent event) {
        logger.info("Autnenticate completed. username : {}", event.getAuthentication().getName());
    }

    @EventListener(LogoutSuccessEvent.class)
    public void handleLogoutSuccess(LogoutSuccessEvent event) {
        logger.info("Logout completed. username : {}", event.getAuthentication().getName());
    }
}
