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

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecurityAthn09Config {

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param dbUserLoginManager Bean defined by SpringSecurityAthnConfig#dbUserLoginManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(90)
    public SecurityFilterChain filterChainJsp0901(HttpSecurity http,
            @Qualifier("dbUserLoginManager") AuthenticationManager dbUserLoginManager)
            throws Exception {

        http.securityMatcher(antMatcher("/jsp/0901/**"));
        http.authenticationManager(dbUserLoginManager);
        http.formLogin(login -> login.loginPage("/jsp/0901/001/login")
                .loginProcessingUrl("/jsp/0901/001/authenticate")
                .defaultSuccessUrl("/jsp/0901/001?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/jsp/0901/001/logout").logoutSuccessUrl("/"));
        http.authorizeHttpRequests(
                authz -> authz.requestMatchers(antMatcher("/jsp/0901/001/login/**")).permitAll()
                        .requestMatchers(antMatcher("/jsp/0901/001/logout/**")).permitAll()
                        .requestMatchers(antMatcher("/jsp/0901/**")).authenticated()
                        .requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param dbUserLoginManager Bean defined by SpringSecurityAthnConfig#dbUserLoginManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(91)
    public SecurityFilterChain filterChainThymeleaf0901(HttpSecurity http,
            @Qualifier("dbUserLoginManager") AuthenticationManager dbUserLoginManager)
            throws Exception {

        http.securityMatcher(antMatcher("/thymeleaf/0901/**"));
        http.authenticationManager(dbUserLoginManager);
        http.formLogin(login -> login.loginPage("/thymeleaf/0901/001/login")
                .loginProcessingUrl("/thymeleaf/0901/001/authenticate")
                .defaultSuccessUrl("/thymeleaf/0901/001?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0901/001/logout").logoutSuccessUrl("/"));
        http.authorizeHttpRequests(
                authz -> authz.requestMatchers(antMatcher("/thymeleaf/0901/001/login/**"))
                        .permitAll().requestMatchers(antMatcher("/thymeleaf/0901/001/logout/**"))
                        .permitAll().requestMatchers(antMatcher("/thymeleaf/0901/**"))
                        .authenticated().requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }
}
