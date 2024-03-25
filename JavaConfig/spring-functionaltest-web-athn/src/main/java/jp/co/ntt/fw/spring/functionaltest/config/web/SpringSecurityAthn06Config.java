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

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jp.co.ntt.fw.spring.functionaltest.domain.service.athn.ThrowAuthenticationServiceExceptionAuthenticationProvider;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecurityAthn06Config {

    /**
     * Configure {@link AuthenticationEventPublisher} bean.
     * @param applicationEventPublisher defined by Spring framework
     * @return Bean of configured {@link AuthenticationEventPublisher}
     */
    @Bean
    public AuthenticationEventPublisher authenticationEventPublisher(
            ApplicationEventPublisher applicationEventPublisher) {
        return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
    }

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param authEventHandleUserLoginAuthenticationProvider Bean defined by #authEventHandleUserLoginAuthenticationProvider
     * @param authenticationEventPublisher Bean defined by #authenticationEventPublisher
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("authEventHandleUserLoginManager")
    public AuthenticationManager authEventHandleUserLoginManager(
            @Qualifier("authEventHandleUserLoginAuthenticationProvider") AuthenticationProvider authEventHandleUserLoginAuthenticationProvider,
            AuthenticationEventPublisher authenticationEventPublisher) {
        ProviderManager bean = new ProviderManager(authEventHandleUserLoginAuthenticationProvider);
        bean.setAuthenticationEventPublisher(authenticationEventPublisher);
        return bean;
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param loginWithEncodingPasswordService Bean defined by SpringSecurityAthnConfig#authEventHandleUserDetailsService
     * @param passwordEncoder Bean defined by ApplicationContextConfig#passwordEncoder
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("authEventHandleUserLoginAuthenticationProvider")
    public AuthenticationProvider authEventHandleUserLoginAuthenticationProvider(
            @Qualifier("authEventHandleUserDetailsService") UserDetailsService authEventHandleUserDetailsService,
            @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(authEventHandleUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param authenticationEventPublisher Bean defined by #authenticationEventPublisher
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("authEventHandleUserLoginManagerForServiceException")
    public AuthenticationManager authEventHandleUserLoginManagerForServiceException(
            AuthenticationEventPublisher authenticationEventPublisher) {
        ProviderManager bean = new ProviderManager(throwAuthenticationServiceExceptionAuthenticationProvider());
        bean.setAuthenticationEventPublisher(authenticationEventPublisher);
        return bean;
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("throwAuthenticationServiceExceptionAuthenticationProvider")
    public AuthenticationProvider throwAuthenticationServiceExceptionAuthenticationProvider() {
        return new ThrowAuthenticationServiceExceptionAuthenticationProvider();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param authEventHandleUserLoginManager Bean defined by #authEventHandleUserLoginManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(60)
    public SecurityFilterChain filterChainJsp0601001(HttpSecurity http,
            @Qualifier("authEventHandleUserLoginManager") AuthenticationManager authEventHandleUserLoginManager) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/0601/001/**"));
        http.authenticationManager(authEventHandleUserLoginManager);
        http.formLogin(login -> login.loginPage("/jsp/0601/001/login")
                .loginProcessingUrl("/jsp/0601/001/authenticate")
                .defaultSuccessUrl("/jsp/0601/001?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/jsp/0601/001/logout")
                .logoutSuccessUrl("/"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/001/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/001/logout/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param authEventHandleUserLoginManagerForServiceException Bean defined by
     *            #authEventHandleUserLoginManagerForServiceException
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(61)
    public SecurityFilterChain filterChainJsp0602002(HttpSecurity http,
            @Qualifier("authEventHandleUserLoginManagerForServiceException") AuthenticationManager authEventHandleUserLoginManagerForServiceException) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/0602/002/**"));
        http.authenticationManager(authEventHandleUserLoginManagerForServiceException);
        http.formLogin(login -> login.loginPage("/jsp/0602/002/login")
                .loginProcessingUrl("/jsp/0602/002/authenticate")
                .defaultSuccessUrl("/jsp/0602/002?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/jsp/0602/002/logout")
                .logoutSuccessUrl("/"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/jsp/0602/002/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/0602/002/logout/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/0602/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param authEventHandleUserLoginManager Bean defined by #authEventHandleUserLoginManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(62)
    public SecurityFilterChain filterChainThymeleaf0601001(HttpSecurity http,
            @Qualifier("authEventHandleUserLoginManager") AuthenticationManager authEventHandleUserLoginManager) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0601/001/**"));
        http.authenticationManager(authEventHandleUserLoginManager);
        http.formLogin(login -> login.loginPage("/thymeleaf/0601/001/login")
                .loginProcessingUrl("/thymeleaf/0601/001/authenticate")
                .defaultSuccessUrl("/thymeleaf/0601/001?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0601/001/logout")
                .logoutSuccessUrl("/"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/001/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/001/logout/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param authEventHandleUserLoginManagerForServiceException Bean defined by
     *            #authEventHandleUserLoginManagerForServiceException
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(63)
    public SecurityFilterChain filterChainThymeleaf0602002(HttpSecurity http,
            @Qualifier("authEventHandleUserLoginManagerForServiceException") AuthenticationManager authEventHandleUserLoginManagerForServiceException) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0602/002/**"));
        http.authenticationManager(authEventHandleUserLoginManagerForServiceException);
        http.formLogin(login -> login.loginPage("/thymeleaf/0602/002/login")
                .loginProcessingUrl("/thymeleaf/0602/002/authenticate")
                .defaultSuccessUrl("/thymeleaf/0602/002?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0602/002/logout")
                .logoutSuccessUrl("/"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0602/002/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0602/002/logout/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0602/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }
}
