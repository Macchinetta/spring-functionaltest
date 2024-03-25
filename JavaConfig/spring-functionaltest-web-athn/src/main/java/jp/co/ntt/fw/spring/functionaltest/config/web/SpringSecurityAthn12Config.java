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
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jp.co.ntt.fw.spring.functionaltest.app.athn.handler.MyAuthenticationSuccessHandler;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecurityAthn12Config {

    /**
     * Configure {@link MyAuthenticationSuccessHandler} bean.
     * @return Bean of configured {@link MyAuthenticationSuccessHandler}
     */
    @Bean("jspAuthenticationSuccessHandler")
    public MyAuthenticationSuccessHandler jspAuthenticationSuccessHandler() {
        MyAuthenticationSuccessHandler bean = new MyAuthenticationSuccessHandler();
        bean.setDefaultTargetUrl("/jsp/1202/001?loginSuccess");
        return bean;
    }

    /**
     * Configure {@link MyAuthenticationSuccessHandler} bean.
     * @return Bean of configured {@link MyAuthenticationSuccessHandler}
     */
    @Bean("thymeleafAuthenticationSuccessHandler")
    public MyAuthenticationSuccessHandler thymeleafAuthenticationSuccessHandler() {
        MyAuthenticationSuccessHandler bean = new MyAuthenticationSuccessHandler();
        bean.setDefaultTargetUrl("/thymeleaf/1202/001?loginSuccess");
        return bean;
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param dbUserLoginManager Bean defined by SpringSecurityAthnConfig#dbUserLoginManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(120)
    public SecurityFilterChain filterChainJsp1201001(HttpSecurity http,
            @Qualifier("dbUserLoginManager") AuthenticationManager dbUserLoginManager) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/1201/001/**"));
        http.authenticationManager(dbUserLoginManager);
        http.formLogin(login -> login.loginPage("/jsp/1201/001/login")
                .loginProcessingUrl("/jsp/1201/001/authenticate")
                .defaultSuccessUrl("/jsp/1201/001?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/jsp/1201/001/logout")
                .logoutSuccessUrl("/"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/jsp/1201/001/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/1201/001/logout/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/1201/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

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
    @Order(121)
    public SecurityFilterChain filterChainJsp1202001(HttpSecurity http,
            @Qualifier("dbUserLoginManager") AuthenticationManager dbUserLoginManager) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/1202/001/**"));
        http.authenticationManager(dbUserLoginManager);
        http.formLogin(login -> login.loginPage("/jsp/1202/001/login")
                .loginProcessingUrl("/jsp/1202/001/authenticate")
                .successHandler(jspAuthenticationSuccessHandler()));
        http.logout(logout -> logout.logoutUrl("/jsp/1202/001/logout")
                .logoutSuccessUrl("/"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/jsp/1202/001/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/1202/001/logout/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/1202/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

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
    @Order(122)
    public SecurityFilterChain filterChainThymeleaf1201001(HttpSecurity http,
            @Qualifier("dbUserLoginManager") AuthenticationManager dbUserLoginManager) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/1201/001/**"));
        http.authenticationManager(dbUserLoginManager);
        http.formLogin(login -> login.loginPage("/thymeleaf/1201/001/login")
                .loginProcessingUrl("/thymeleaf/1201/001/authenticate")
                .defaultSuccessUrl("/thymeleaf/1201/001?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/1201/001/logout")
                .logoutSuccessUrl("/"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/1201/001/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/1201/001/logout/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/1201/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

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
    @Order(123)
    public SecurityFilterChain filterChainThymeleaf1202001(HttpSecurity http,
            @Qualifier("dbUserLoginManager") AuthenticationManager dbUserLoginManager) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/1202/001/**"));
        http.authenticationManager(dbUserLoginManager);
        http.formLogin(login -> login.loginPage("/thymeleaf/1202/001/login")
                .loginProcessingUrl("/thymeleaf/1202/001/authenticate")
                .successHandler(thymeleafAuthenticationSuccessHandler()));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/1202/001/logout")
                .logoutSuccessUrl("/"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/1202/001/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/1202/001/logout/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/1202/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }
}
