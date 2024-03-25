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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecuritySpsm02Config {

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManagerForAthn Bean defined by SpringSecurityConfig#userLoginManagerForAthn
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    public SecurityFilterChain filterChainJsp0201001(HttpSecurity http,
            @Qualifier("userLoginManagerForAthn") AuthenticationManager userLoginManagerForAthn) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/0201/001/**"));
        http.authenticationManager(userLoginManagerForAthn);
        http.sessionManagement(sessionManagement -> sessionManagement
                .enableSessionUrlRewriting(true));

        http.formLogin(login -> login.loginPage("/jsp/0201/001/login")
                .loginProcessingUrl("/jsp/0201/001/authenticate")
                .defaultSuccessUrl("/jsp/0201/001?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/jsp/0201/001/logout")
                .logoutSuccessUrl("/jsp/0201/001"));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/jsp/0201/001/login/**")).permitAll()
                .requestMatchers(
                        new AntPathRequestMatcher("/jsp/0201/001/logout/**"))
                .permitAll().requestMatchers(
                        new AntPathRequestMatcher("/jsp/0201/**"))
                .authenticated().requestMatchers(
                        new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManagerForAthn Bean defined by SpringSecurityConfig#userLoginManagerForAthn
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChainJsp0202001(HttpSecurity http,
            @Qualifier("userLoginManagerForAthn") AuthenticationManager userLoginManagerForAthn) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/0202/001/**"));
        http.authenticationManager(userLoginManagerForAthn);
        http.sessionManagement(sessionManagement -> sessionManagement
                .enableSessionUrlRewriting(true).sessionFixation(
                        fixation -> fixation.migrateSession()));

        http.formLogin(login -> login.loginPage("/jsp/0202/001/login")
                .loginProcessingUrl("/jsp/0202/001/authenticate")
                .defaultSuccessUrl("/jsp/0202/001?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/jsp/0202/001/logout")
                .logoutSuccessUrl("/jsp/0202/001"));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/jsp/0202/001/login/**")).permitAll()
                .requestMatchers(
                        new AntPathRequestMatcher("/jsp/0202/001/logout/**"))
                .permitAll().requestMatchers(
                        new AntPathRequestMatcher("/jsp/0202/**"))
                .authenticated().requestMatchers(
                        new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManagerForAthn Bean defined by SpringSecurityConfig#userLoginManagerForAthn
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChainJsp0202002(HttpSecurity http,
            @Qualifier("userLoginManagerForAthn") AuthenticationManager userLoginManagerForAthn) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/0202/002/**"));
        http.authenticationManager(userLoginManagerForAthn);
        http.sessionManagement(sessionManagement -> sessionManagement
                .enableSessionUrlRewriting(true).sessionFixation(
                        fixation -> fixation.newSession()));

        http.formLogin(login -> login.loginPage("/jsp/0202/002/login")
                .loginProcessingUrl("/jsp/0202/002/authenticate")
                .defaultSuccessUrl("/jsp/0202/002?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/jsp/0202/002/logout")
                .logoutSuccessUrl("/jsp/0202/002"));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/jsp/0202/002/login/**")).permitAll()
                .requestMatchers(
                        new AntPathRequestMatcher("/jsp/0202/002/logout/**"))
                .permitAll().requestMatchers(
                        new AntPathRequestMatcher("/jsp/0202/**"))
                .authenticated().requestMatchers(
                        new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManagerForAthn Bean defined by SpringSecurityConfig#userLoginManagerForAthn
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChainThymeleaf0201(HttpSecurity http,
            @Qualifier("userLoginManagerForAthn") AuthenticationManager userLoginManagerForAthn) throws Exception {

        http.securityMatcher(
                new AntPathRequestMatcher("/thymeleaf/0201/001/**"));
        http.authenticationManager(userLoginManagerForAthn);
        http.sessionManagement(sessionManagement -> sessionManagement
                .enableSessionUrlRewriting(true));

        http.formLogin(login -> login.loginPage("/thymeleaf/0201/001/login")
                .loginProcessingUrl("/thymeleaf/0201/001/authenticate")
                .defaultSuccessUrl("/thymeleaf/0201/001?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0201/001/logout")
                .logoutSuccessUrl("/thymeleaf/0201/001"));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/thymeleaf/0201/001/login/**"))
                .permitAll().requestMatchers(
                        new AntPathRequestMatcher("/thymeleaf/0201/001/logout/**"))
                .permitAll().requestMatchers(
                        new AntPathRequestMatcher("/thymeleaf/0201/**"))
                .authenticated().requestMatchers(
                        new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManagerForAthn Bean defined by SpringSecurityConfig#userLoginManagerForAthn
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChainThymeleaf0202(HttpSecurity http,
            @Qualifier("userLoginManagerForAthn") AuthenticationManager userLoginManagerForAthn) throws Exception {

        http.securityMatcher(
                new AntPathRequestMatcher("/thymeleaf/0202/001/**"));
        http.authenticationManager(userLoginManagerForAthn);
        http.sessionManagement(sessionManagement -> sessionManagement
                .enableSessionUrlRewriting(true));

        http.formLogin(login -> login.loginPage("/thymeleaf/0202/001/login")
                .loginProcessingUrl("/thymeleaf/0202/001/authenticate")
                .defaultSuccessUrl("/thymeleaf/0202/001?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0202/001/logout")
                .logoutSuccessUrl("/thymeleaf/0202/001"));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/thymeleaf/0202/001/login/**"))
                .permitAll().requestMatchers(
                        new AntPathRequestMatcher("/thymeleaf/0202/001/logout/**"))
                .permitAll().requestMatchers(
                        new AntPathRequestMatcher("/thymeleaf/0202/**"))
                .authenticated().requestMatchers(
                        new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManagerForAthn Bean defined by SpringSecurityConfig#userLoginManagerForAthn
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChainThymeleaf0202002(HttpSecurity http,
            @Qualifier("userLoginManagerForAthn") AuthenticationManager userLoginManagerForAthn) throws Exception {

        http.securityMatcher(
                new AntPathRequestMatcher("/thymeleaf/0202/002/**"));
        http.authenticationManager(userLoginManagerForAthn);
        http.sessionManagement(sessionManagement -> sessionManagement
                .enableSessionUrlRewriting(true).sessionFixation(
                        fixation -> fixation.newSession()));

        http.formLogin(login -> login.loginPage("/thymeleaf/0202/002/login")
                .loginProcessingUrl("/thymeleaf/0202/002/authenticate")
                .defaultSuccessUrl("/thymeleaf/0202/002?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0202/002/logout")
                .logoutSuccessUrl("/thymeleaf/0202/002"));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/thymeleaf/0202/002/login/**"))
                .permitAll().requestMatchers(
                        new AntPathRequestMatcher("/thymeleaf/0202/002/logout/**"))
                .permitAll().requestMatchers(
                        new AntPathRequestMatcher("/thymeleaf/0202/**"))
                .authenticated().requestMatchers(
                        new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

}
