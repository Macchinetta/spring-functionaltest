/*
 * Copyright 2014-2017 NTT Corporation.
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
 *
 */
package jp.co.ntt.fw.spring.functionaltest.app.athn.listner;

import org.aspectj.lang.annotation.Aspect;
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
import org.springframework.security.web.authentication.session.SessionFixationProtectionEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEventListeners {

    private static final Logger log = LoggerFactory.getLogger(
            AuthenticationEventListeners.class);

    @EventListener
    public void handleBadCredentials(
            AuthenticationFailureBadCredentialsEvent event) {
        log.info("Bad credentials is detected. username : {}", event
                .getAuthentication().getName());
    }

    @EventListener
    public void handleAuthenticationFailureDisabled(
            AuthenticationFailureDisabledEvent event) {
        log.info("User deisabled is detected. username : {}", event
                .getAuthentication().getName());
    }

    @EventListener
    public void handleAuthenticationFailureLocked(
            AuthenticationFailureLockedEvent event) {
        log.info("User locked is detected. username : {}", event
                .getAuthentication().getName());
    }

    @EventListener
    public void handleAuthenticationFailureExpired(
            AuthenticationFailureExpiredEvent event) {
        log.info("Authentication expired is detected. username : {}", event
                .getAuthentication().getName());
    }

    @EventListener
    public void handleAuthenticationFailureCredentialsExpired(
            AuthenticationFailureCredentialsExpiredEvent event) {
        log.info("Credentials expired is detected. username : {}", event
                .getAuthentication().getName());
    }

    @EventListener
    public void handleAuthenticationFailureServiceExceptionEvent(
            AuthenticationFailureServiceExceptionEvent event) {
        log.info("ServiceException is detected. username : {}", event
                .getAuthentication().getName());
    }

    @EventListener
    public void handleAuthenticationSuccess(AuthenticationSuccessEvent event) {
        log.info("Autnenticated. username : {}", event.getAuthentication()
                .getName());
    }

    @EventListener
    public void handleSessionFixationProtection(
            SessionFixationProtectionEvent event) {
        log.info("Session changed. sessionId : {}", event.getOldSessionId()
                + " to " + event.getNewSessionId());
    }

    @EventListener
    public void handleInteractiveAuthenticationSuccess(
            InteractiveAuthenticationSuccessEvent event) {
        log.info("Autnenticate completed. username : {}", event
                .getAuthentication().getName());
    }

}
