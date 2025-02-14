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

import java.util.Properties;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecurityAthn16Config {

    /**
     * Configure {@link ExceptionMappingAuthenticationFailureHandler} bean.
     * @return Bean of configured {@link ExceptionMappingAuthenticationFailureHandler}
     */
    @Bean("jspAuthenticationSystemFailureHandler")
    public ExceptionMappingAuthenticationFailureHandler jspAuthenticationSystemFailureHandler() {
        ExceptionMappingAuthenticationFailureHandler bean =
                new ExceptionMappingAuthenticationFailureHandler();
        bean.setDefaultFailureUrl("/jsp/1601/001/login?error");
        Properties mappings = new Properties();
        mappings.put(
                "org.springframework.security.authentication.InternalAuthenticationServiceException",
                "/jsp/1601/001/login?systemError");
        bean.setExceptionMappings(mappings);
        return bean;
    }

    /**
     * Configure {@link ExceptionMappingAuthenticationFailureHandler} bean.
     * @return Bean of configured {@link ExceptionMappingAuthenticationFailureHandler}
     */
    @Bean("thymeleafAuthenticationSystemFailureHandler")
    public ExceptionMappingAuthenticationFailureHandler thymeleafAuthenticationSystemFailureHandler() {
        ExceptionMappingAuthenticationFailureHandler bean =
                new ExceptionMappingAuthenticationFailureHandler();
        bean.setDefaultFailureUrl("/thymeleaf/1601/001/login?error");
        Properties mappings = new Properties();
        mappings.put(
                "org.springframework.security.authentication.InternalAuthenticationServiceException",
                "/thymeleaf/1601/001/login?systemError");
        bean.setExceptionMappings(mappings);
        return bean;
    }

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param authEventHandleUserLoginAuthenticationProvider Bean defined by
     *        #authEventSystemFailureHandleUserLoginAuthenticationProvider
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("authEventSystemFailureHandleUserLoginManager")
    public AuthenticationManager authEventHandleUserLoginManager(
            @Qualifier("authEventSystemFailureHandleUserLoginAuthenticationProvider") AuthenticationProvider authEventSystemFailureHandleUserLoginAuthenticationProvider) {
        return new ProviderManager(authEventSystemFailureHandleUserLoginAuthenticationProvider);
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param loginWithEncodingPasswordService Bean defined by
     *        SpringSecurityAthnConfig#authEventHandleUserDetailsService
     * @param passwordEncoder Bean defined by ApplicationContextConfig#passwordEncoder
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("authEventSystemFailureHandleUserLoginAuthenticationProvider")
    public AuthenticationProvider authEventSystemFailureHandleUserLoginAuthenticationProvider(
            @Qualifier("authEventHandleUserDetailsService") UserDetailsService authEventHandleUserDetailsService,
            @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(authEventHandleUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param authEventSystemFailureHandleUserLoginManager Bean defined by
     *        #authEventSystemFailureHandleUserLoginManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(160)
    public SecurityFilterChain filterChainJsp1601(HttpSecurity http,
            @Qualifier("authEventSystemFailureHandleUserLoginManager") AuthenticationManager authEventSystemFailureHandleUserLoginManager)
            throws Exception {

        http.securityMatcher(antMatcher("/jsp/1601/**"));
        http.authenticationManager(authEventSystemFailureHandleUserLoginManager);
        http.formLogin(login -> login.loginPage("/jsp/1601/001/login")
                .loginProcessingUrl("/jsp/1601/authenticate")
                .defaultSuccessUrl("/jsp/1601/001?loginSuccess", true)
                .failureHandler(jspAuthenticationSystemFailureHandler()));
        http.logout(logout -> logout.logoutUrl("/jsp/1601/001/logout").logoutSuccessUrl("/athn"));
        http.authorizeHttpRequests(
                authz -> authz.requestMatchers(antMatcher("/jsp/1601/001/login/**")).permitAll()
                        .requestMatchers(antMatcher("/jsp/1601/001/logout/**")).permitAll()
                        .requestMatchers(antMatcher("/jsp/1601/**")).authenticated()
                        .requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param authEventSystemFailureHandleUserLoginManager Bean defined by
     *        #authEventSystemFailureHandleUserLoginManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(161)
    public SecurityFilterChain filterChainJsp1602(HttpSecurity http,
            @Qualifier("authEventSystemFailureHandleUserLoginManager") AuthenticationManager authEventSystemFailureHandleUserLoginManager)
            throws Exception {

        http.securityMatcher(antMatcher("/jsp/1602/**"));
        http.authenticationManager(authEventSystemFailureHandleUserLoginManager);
        http.formLogin(login -> login.loginPage("/jsp/1602/001/login")
                .loginProcessingUrl("/jsp/1602/authenticate")
                .defaultSuccessUrl("/jsp/1602/001?loginSuccess", true)
                .failureUrl("/jsp/1602/001/login?systemError"));
        http.logout(logout -> logout.logoutUrl("/jsp/1602/001/logout").logoutSuccessUrl("/athn"));
        http.authorizeHttpRequests(
                authz -> authz.requestMatchers(antMatcher("/jsp/1602/001/login/**")).permitAll()
                        .requestMatchers(antMatcher("/jsp/1602/001/logout/**")).permitAll()
                        .requestMatchers(antMatcher("/jsp/1602/**")).authenticated()
                        .requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param authEventSystemFailureHandleUserLoginManager Bean defined by
     *        #authEventSystemFailureHandleUserLoginManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(162)
    public SecurityFilterChain filterChainThymeleaf1601(HttpSecurity http,
            @Qualifier("authEventSystemFailureHandleUserLoginManager") AuthenticationManager authEventSystemFailureHandleUserLoginManager)
            throws Exception {

        http.securityMatcher(antMatcher("/thymeleaf/1601/**"));
        http.authenticationManager(authEventSystemFailureHandleUserLoginManager);
        http.formLogin(login -> login.loginPage("/thymeleaf/1601/001/login")
                .loginProcessingUrl("/thymeleaf/1601/authenticate")
                .defaultSuccessUrl("/thymeleaf/1601/001?loginSuccess", true)
                .failureHandler(thymeleafAuthenticationSystemFailureHandler()));
        http.logout(
                logout -> logout.logoutUrl("/thymeleaf/1601/001/logout").logoutSuccessUrl("/athn"));
        http.authorizeHttpRequests(
                authz -> authz.requestMatchers(antMatcher("/thymeleaf/1601/001/login/**"))
                        .permitAll().requestMatchers(antMatcher("/thymeleaf/1601/001/logout/**"))
                        .permitAll().requestMatchers(antMatcher("/thymeleaf/1601/**"))
                        .authenticated().requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param authEventSystemFailureHandleUserLoginManager Bean defined by
     *        #authEventSystemFailureHandleUserLoginManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(163)
    public SecurityFilterChain filterChainThymeleaf1602(HttpSecurity http,
            @Qualifier("authEventSystemFailureHandleUserLoginManager") AuthenticationManager authEventSystemFailureHandleUserLoginManager)
            throws Exception {

        http.securityMatcher(antMatcher("/thymeleaf/1602/**"));
        http.authenticationManager(authEventSystemFailureHandleUserLoginManager);
        http.formLogin(login -> login.loginPage("/thymeleaf/1602/001/login")
                .loginProcessingUrl("/thymeleaf/1602/authenticate")
                .defaultSuccessUrl("/thymeleaf/1602/001?loginSuccess", true)
                .failureUrl("/thymeleaf/1602/001/login?systemError"));
        http.logout(
                logout -> logout.logoutUrl("/thymeleaf/1602/001/logout").logoutSuccessUrl("/athn"));
        http.authorizeHttpRequests(
                authz -> authz.requestMatchers(antMatcher("/thymeleaf/1602/001/login/**"))
                        .permitAll().requestMatchers(antMatcher("/thymeleaf/1602/001/logout/**"))
                        .permitAll().requestMatchers(antMatcher("/thymeleaf/1602/**"))
                        .authenticated().requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }
}
