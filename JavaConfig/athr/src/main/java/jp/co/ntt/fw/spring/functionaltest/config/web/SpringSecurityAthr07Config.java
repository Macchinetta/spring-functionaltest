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

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import jp.co.ntt.fw.spring.functionaltest.app.athr.handler.JsonDelegatingAccessDeniedHandler;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecurityAthr07Config {

    /**
     * Configure {@link JsonDelegatingAccessDeniedHandler} bean.
     * @return Bean of configured {@link JsonDelegatingAccessDeniedHandler}
     */
    @Bean("jspAccessDeniedHandlerForAjax")
    public JsonDelegatingAccessDeniedHandler jspAccessDeniedHandlerForAjax() {
        return new JsonDelegatingAccessDeniedHandler(jspAccessAntPathRequestMatcher(),
                jspAccessDeniedHandler());
    }

    /**
     * Configure {@link AntPathRequestMatcher} bean.
     * @return Bean of configured {@link AntPathRequestMatcher}
     */
    @Bean("jspAccessAntPathRequestMatcher")
    public AntPathRequestMatcher jspAccessAntPathRequestMatcher() {
        return antMatcher("/jsp/0701/001/denyjson/**");
    }

    /**
     * Configure {@link AccessDeniedHandler} bean.
     * @return Bean of configured {@link AccessDeniedHandler}
     */
    @Bean("jspAccessDeniedHandler")
    public AccessDeniedHandler jspAccessDeniedHandler() {
        AccessDeniedHandlerImpl bean = new AccessDeniedHandlerImpl();
        bean.setErrorPage("/jsp/accessDeniedPage");
        return bean;
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginForStaffManager Bean defined by SpringSecurityAthrConfig#loginForStaffManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(70)
    public SecurityFilterChain filterChainJsp0701(HttpSecurity http,
            @Qualifier("loginForStaffManager") AuthenticationManager loginForStaffManager)
            throws Exception {

        http.securityMatcher(antMatcher("/jsp/0701/**"));
        http.authenticationManager(loginForStaffManager);
        http.formLogin(login -> login.loginPage("/jsp/0701/001")
                .loginProcessingUrl("/jsp/0701/001/authenticate")
                .defaultSuccessUrl("/jsp/0701/001/afterLogin"));
        http.logout(logout -> logout.logoutUrl("/jsp/0701/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID"));
        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .accessDeniedHandler(jspAccessDeniedHandlerForAjax()));
        http.authorizeHttpRequests(
                authz -> authz.requestMatchers(antMatcher("/jsp/0701/001/afterLogin"))
                        .authenticated().requestMatchers(antMatcher("/jsp/0701/001/denyjson/**"))
                        .denyAll().requestMatchers(antMatcher("/jsp/0701/001/deny/**")).denyAll()
                        .requestMatchers(antMatcher("jsp/0701/001/**")).permitAll()
                        .requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link JsonDelegatingAccessDeniedHandler} bean.
     * @return Bean of configured {@link JsonDelegatingAccessDeniedHandler}
     */
    @Bean("thymeleafAccessDeniedHandlerForAjax")
    public JsonDelegatingAccessDeniedHandler thymeleafAccessDeniedHandlerForAjax() {
        return new JsonDelegatingAccessDeniedHandler(thymeleafAccessAntPathRequestMatcher(),
                thymeleafAccessDeniedHandler());
    }

    /**
     * Configure {@link AntPathRequestMatcher} bean.
     * @return Bean of configured {@link AntPathRequestMatcher}
     */
    @Bean("thymeleafAccessAntPathRequestMatcher")
    public AntPathRequestMatcher thymeleafAccessAntPathRequestMatcher() {
        return antMatcher("/thymeleaf/0701/001/denyjson/**");
    }

    /**
     * Configure {@link AccessDeniedHandler} bean.
     * @return Bean of configured {@link AccessDeniedHandler}
     */
    @Bean("thymeleafAccessDeniedHandler")
    public AccessDeniedHandler thymeleafAccessDeniedHandler() {
        AccessDeniedHandlerImpl bean = new AccessDeniedHandlerImpl();
        bean.setErrorPage("/thymeleaf/accessDeniedPage");
        return bean;
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginForStaffManager Bean defined by SpringSecurityAthrConfig#loginForStaffManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(71)
    public SecurityFilterChain filterChainThymeleaf0701(HttpSecurity http,
            @Qualifier("loginForStaffManager") AuthenticationManager loginForStaffManager)
            throws Exception {

        http.securityMatcher(antMatcher("/thymeleaf/0701/**"));
        http.authenticationManager(loginForStaffManager);
        http.formLogin(login -> login.loginPage("/thymeleaf/0701/001")
                .loginProcessingUrl("/thymeleaf/0701/001/authenticate")
                .defaultSuccessUrl("/thymeleaf/0701/001/afterLogin"));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0701/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID"));
        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .accessDeniedHandler(thymeleafAccessDeniedHandlerForAjax()));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(antMatcher("/thymeleaf/0701/001/afterLogin")).authenticated()
                .requestMatchers(antMatcher("/thymeleaf/0701/001/denyjson/**")).denyAll()
                .requestMatchers(antMatcher("/thymeleaf/0701/001/deny/**")).denyAll()
                .requestMatchers(antMatcher("/thymeleaf/0701/001/**")).permitAll()
                .requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }
}
