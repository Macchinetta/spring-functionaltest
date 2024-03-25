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

import jp.co.ntt.fw.spring.functionaltest.app.athn.handler.MyLogoutSuccessHandler;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecurityAthn15Config {

    /**
     * Configure {@link MyLogoutSuccessHandler} bean.
     * @return Bean of configured {@link MyLogoutSuccessHandler}
     */
    @Bean("jspLogoutSuccessHandler")
    public MyLogoutSuccessHandler jspLogoutSuccessHandler() {
        MyLogoutSuccessHandler bean = new MyLogoutSuccessHandler();
        bean.setDefaultTargetUrl("/jsp/1501/logoutComplete");
        return bean;
    }

    /**
     * Configure {@link MyLogoutSuccessHandler} bean.
     * @return Bean of configured {@link MyLogoutSuccessHandler}
     */
    @Bean("thymeleafLogoutSuccessHandler")
    public MyLogoutSuccessHandler thymeleafLogoutSuccessHandler() {
        MyLogoutSuccessHandler bean = new MyLogoutSuccessHandler();
        bean.setDefaultTargetUrl("/thymeleaf/1501/logoutComplete");
        return bean;
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManagerForAthn Bean defined by SpringSecurityAthnConfig#userLoginManagerForAthn
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(150)
    public SecurityFilterChain filterChainJsp1501(HttpSecurity http,
            @Qualifier("userLoginManagerForAthn") AuthenticationManager userLoginManagerForAthn) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/1501/**"));
        http.authenticationManager(userLoginManagerForAthn);
        http.formLogin(login -> login.loginPage("/jsp/1501/001/login")
                .defaultSuccessUrl("/jsp/1501/001?loginSuccess", true)
                .loginProcessingUrl("/jsp/1501/001/authenticate"));
        http.logout(logout -> logout.logoutUrl("/jsp/1501/001/logout")
                .logoutSuccessHandler(jspLogoutSuccessHandler()));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/jsp/1501/001/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/1501/001/logout/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/1501/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManagerForAthn Bean defined by SpringSecurityAthnConfig#userLoginManagerForAthn
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(151)
    public SecurityFilterChain filterChainThymeleaf1501(HttpSecurity http,
            @Qualifier("userLoginManagerForAthn") AuthenticationManager userLoginManagerForAthn) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/1501/**"));
        http.authenticationManager(userLoginManagerForAthn);
        http.formLogin(login -> login.loginPage("/thymeleaf/1501/001/login")
                .defaultSuccessUrl("/thymeleaf/1501/001?loginSuccess", true)
                .loginProcessingUrl("/thymeleaf/1501/001/authenticate"));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/1501/001/logout")
                .logoutSuccessHandler(thymeleafLogoutSuccessHandler()));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/1501/001/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/1501/001/logout/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/1501/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }
}
