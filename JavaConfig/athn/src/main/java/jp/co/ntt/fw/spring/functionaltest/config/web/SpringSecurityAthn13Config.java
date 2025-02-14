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
public class SpringSecurityAthn13Config {

    /**
     * Configure {@link ExceptionMappingAuthenticationFailureHandler} bean.
     * @return Bean of configured {@link ExceptionMappingAuthenticationFailureHandler}
     */
    @Bean("jspAuthenticationFailureHandler")
    public ExceptionMappingAuthenticationFailureHandler jspAuthenticationFailureHandler() {
        ExceptionMappingAuthenticationFailureHandler bean =
                new ExceptionMappingAuthenticationFailureHandler();
        bean.setDefaultFailureUrl("/jsp/systemError");
        Properties mappings = new Properties();
        mappings.put("org.springframework.security.authentication.BadCredentialsException",
                "/jsp/badCredentials");
        mappings.put("org.springframework.security.core.userdetails.UsernameNotFoundException",
                "/jsp/usernameNotFound");
        mappings.put("org.springframework.security.authentication.DisabledException",
                "/jsp/disabled");
        bean.setExceptionMappings(mappings);
        return bean;
    }

    /**
     * Configure {@link ExceptionMappingAuthenticationFailureHandler} bean.
     * @return Bean of configured {@link ExceptionMappingAuthenticationFailureHandler}
     */
    @Bean("thymeleafAuthenticationFailureHandler")
    public ExceptionMappingAuthenticationFailureHandler thymeleafAuthenticationFailureHandler() {
        ExceptionMappingAuthenticationFailureHandler bean =
                new ExceptionMappingAuthenticationFailureHandler();
        bean.setDefaultFailureUrl("/thymeleaf/systemError");
        Properties mappings = new Properties();
        mappings.put("org.springframework.security.authentication.BadCredentialsException",
                "/thymeleaf/badCredentials");
        mappings.put("org.springframework.security.core.userdetails.UsernameNotFoundException",
                "/thymeleaf/usernameNotFound");
        mappings.put("org.springframework.security.authentication.DisabledException",
                "/thymeleaf/disabled");
        bean.setExceptionMappings(mappings);
        return bean;
    }

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param authFailureEventHandleAuthenticationProvider Bean defined by
     *        #authFailureEventHandleAuthenticationProvider
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("authFailureEventHandleUserLoginManager")
    public AuthenticationManager authFailureEventHandleUserLoginManager(
            @Qualifier("authFailureEventHandleAuthenticationProvider") AuthenticationProvider authFailureEventHandleAuthenticationProvider) {
        return new ProviderManager(authFailureEventHandleAuthenticationProvider);
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param authEventHandleUserDetailsService Bean defined by
     *        SpringSecurityAthnConfig#authEventHandleUserDetailsService
     * @param passwordEncoder Bean defined by ApplicationContextConfig#passwordEncoder
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("authFailureEventHandleAuthenticationProvider")
    public AuthenticationProvider authFailureEventHandleAuthenticationProvider(
            @Qualifier("authEventHandleUserDetailsService") UserDetailsService authEventHandleUserDetailsService,
            @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setHideUserNotFoundExceptions(false);
        authProvider.setPasswordEncoder(passwordEncoder);
        authProvider.setUserDetailsService(authEventHandleUserDetailsService);
        return authProvider;
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param authFailureEventHandleUserLoginManager Bean defined by
     *        #authFailureEventHandleUserLoginManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(130)
    public SecurityFilterChain filterChainJsp1302(HttpSecurity http,
            @Qualifier("authFailureEventHandleUserLoginManager") AuthenticationManager authFailureEventHandleUserLoginManager)
            throws Exception {

        http.securityMatcher(antMatcher("/jsp/1302/**"));
        http.authenticationManager(authFailureEventHandleUserLoginManager);
        http.formLogin(login -> login.loginPage("/jsp/1302/001/login")
                .loginProcessingUrl("/jsp/1302/001/authenticate")
                .defaultSuccessUrl("/jsp/1302/001?loginSuccess", true)
                .failureHandler(jspAuthenticationFailureHandler()));
        http.logout(logout -> logout.logoutUrl("/jsp/1302/001/logout").logoutSuccessUrl("/athn"));
        http.authorizeHttpRequests(
                authz -> authz.requestMatchers(antMatcher("/jsp/1302/001/login/**")).permitAll()
                        .requestMatchers(antMatcher("/jsp/1302/001/logout/**")).permitAll()
                        .requestMatchers(antMatcher("/jsp/1302/**")).authenticated()
                        .requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param authFailureEventHandleUserLoginManager Bean defined by
     *        #authFailureEventHandleUserLoginManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(131)
    public SecurityFilterChain filterChainThymeleaf1302(HttpSecurity http,
            @Qualifier("authFailureEventHandleUserLoginManager") AuthenticationManager authFailureEventHandleUserLoginManager)
            throws Exception {

        http.securityMatcher(antMatcher("/thymeleaf/1302/**"));
        http.authenticationManager(authFailureEventHandleUserLoginManager);
        http.formLogin(login -> login.loginPage("/thymeleaf/1302/001/login")
                .loginProcessingUrl("/thymeleaf/1302/001/authenticate")
                .defaultSuccessUrl("/thymeleaf/1302/001?loginSuccess", true)
                .failureHandler(thymeleafAuthenticationFailureHandler()));
        http.logout(
                logout -> logout.logoutUrl("/thymeleaf/1302/001/logout").logoutSuccessUrl("/athn"));
        http.authorizeHttpRequests(
                authz -> authz.requestMatchers(antMatcher("/thymeleaf/1302/001/login/**"))
                        .permitAll().requestMatchers(antMatcher("/thymeleaf/1302/001/logout/**"))
                        .permitAll().requestMatchers(antMatcher("/thymeleaf/1302/**"))
                        .authenticated().requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }
}
