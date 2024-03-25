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
package jp.co.ntt.fw.spring.functionaltest.config.web;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.DelegatingAccessDeniedHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.csrf.CsrfAuthenticationStrategy;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.terasoluna.gfw.security.web.logging.UserIdMDCPutFilter;

import jp.co.ntt.fw.spring.functionaltest.app.cspr.AjaxSupportAccessDeniedHandlerImpl;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
@EnableWebSecurity
@Import({ SpringSecurityCsprConfig.class })
public class SpringSecurityConfig {

    /**
     * Configure {@link HttpSessionCsrfTokenRepository} bean.
     * @return Bean of configured {@link HttpSessionCsrfTokenRepository}
     */
    @Bean("csrfTokenRepository")
    public HttpSessionCsrfTokenRepository csrfTokenRepository() {
        return new HttpSessionCsrfTokenRepository();
    }

    /**
     * Configure {@link CompositeSessionAuthenticationStrategy} bean.
     * @return Bean of configured {@link CompositeSessionAuthenticationStrategy}
     */
    @Bean("sessionAuthenticationStrategy")
    public CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy() {
        List<SessionAuthenticationStrategy> list = new ArrayList<SessionAuthenticationStrategy>();
        list.add(sessionFixationProtectionStrategy());
        list.add(csrfAuthenticationStrategy());
        CompositeSessionAuthenticationStrategy bean = new CompositeSessionAuthenticationStrategy(list);
        return bean;
    }

    /**
     * Configure {@link SessionFixationProtectionStrategy} bean.
     * @return Bean of configured {@link SessionFixationProtectionStrategy}
     */
    @Bean
    public SessionFixationProtectionStrategy sessionFixationProtectionStrategy() {
        return new SessionFixationProtectionStrategy();
    }

    /**
     * Configure {@link CsrfAuthenticationStrategy} bean.
     * @return Bean of configured {@link CsrfAuthenticationStrategy}
     */
    @Bean
    public CsrfAuthenticationStrategy csrfAuthenticationStrategy() {
        return new CsrfAuthenticationStrategy(csrfTokenRepository());
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param csrfTokenRequestAttributeHandler Bean defined by SpringSecurityCsprConfig#csrfTokenRequestAttributeHandler
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(10)
    public SecurityFilterChain filterChain(HttpSecurity http,
            CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler) throws Exception {
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.exceptionHandling(ex -> ex.accessDeniedHandler(
                accessDeniedHandler()));
        http.csrf(csrf -> csrf.csrfTokenRequestHandler(
                csrfTokenRequestAttributeHandler));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link AccessDeniedHandler} bean.
     * @return Bean of configured {@link AccessDeniedHandler}
     */
    @Bean("accessDeniedHandler")
    public AccessDeniedHandler accessDeniedHandler() {
        LinkedHashMap<Class<? extends AccessDeniedException>, AccessDeniedHandler> errorHandlers = new LinkedHashMap<>();

        // Invalid CSRF authenticator error handler
        errorHandlers.put(InvalidCsrfTokenException.class,
                invalidCsrfTokenErrorHandler());

        // Missing CSRF authenticator error handler
        AccessDeniedHandlerImpl missingCsrfTokenErrorHandler = new AccessDeniedHandlerImpl();
        missingCsrfTokenErrorHandler.setErrorPage(
                "/WEB-INF/views/jsp/common/error/missingCsrfTokenError.jsp");
        errorHandlers.put(MissingCsrfTokenException.class,
                missingCsrfTokenErrorHandler);

        // Default error handler
        AccessDeniedHandlerImpl defaultErrorHandler = new AccessDeniedHandlerImpl();
        defaultErrorHandler.setErrorPage(
                "/WEB-INF/views/jsp/common/error/accessDeniedError.jsp");

        return new DelegatingAccessDeniedHandler(errorHandlers, defaultErrorHandler);
    }

    /**
     * Configure {@link AjaxSupportAccessDeniedHandlerImpl} bean.
     * Since we are injecting a MessageSource, define it as a bean. 
     * @return Bean of configured {@link AjaxSupportAccessDeniedHandlerImpl}
     */
    @Bean
    public AjaxSupportAccessDeniedHandlerImpl invalidCsrfTokenErrorHandler() {
        // Since we are injecting a MessageSource, define it as a bean.
        AjaxSupportAccessDeniedHandlerImpl invalidCsrfTokenErrorHandler = new AjaxSupportAccessDeniedHandlerImpl();
        invalidCsrfTokenErrorHandler.setErrorPage(
                "/WEB-INF/views/jsp/common/error/invalidCsrfTokenError.jsp");
        return invalidCsrfTokenErrorHandler;
    }

    /**
     * Configure {@link DefaultWebSecurityExpressionHandler} bean.
     * @return Bean of configured {@link DefaultWebSecurityExpressionHandler}
     */
    @Bean("webSecurityExpressionHandler")
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
        return new DefaultWebSecurityExpressionHandler();
    }

    /**
     * Configure {@link UserIdMDCPutFilter} bean.
     * @return Bean of configured {@link UserIdMDCPutFilter}
     */
    @Bean("userIdMDCPutFilter")
    public UserIdMDCPutFilter userIdMDCPutFilter() {
        return new UserIdMDCPutFilter();
    }
}
