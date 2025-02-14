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
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecurityAthn07Config {

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManagerForAthn Bean defined by
     *        SpringSecurityAthnConfig#userLoginManagerForAthn
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(70)
    public SecurityFilterChain filterChainJsp0701001(HttpSecurity http,
            @Qualifier("userLoginManagerForAthn") AuthenticationManager userLoginManagerForAthn)
            throws Exception {

        http.securityMatcher(antMatcher("/jsp/0701/001/**"));
        http.authenticationManager(userLoginManagerForAthn);
        http.formLogin(login -> login.loginPage("/jsp/0701/001/login")
                .defaultSuccessUrl("/jsp/0701/001?loginSuccess", true)
                .loginProcessingUrl("/jsp/0701/001/authenticate"));
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(
                authz -> authz.requestMatchers(antMatcher("/jsp/0701/001/login/**")).permitAll()
                        .requestMatchers(antMatcher("/jsp/0701/**")).authenticated()
                        .requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManagerForAthn Bean defined by
     *        SpringSecurityAthnConfig#userLoginManagerForAthn
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(71)
    public SecurityFilterChain filterChainJsp0702001(HttpSecurity http,
            @Qualifier("userLoginManagerForAthn") AuthenticationManager userLoginManagerForAthn)
            throws Exception {

        http.securityMatcher(antMatcher("/jsp/0702/001/**"));
        http.authenticationManager(userLoginManagerForAthn);
        http.formLogin(login -> login.loginPage("/jsp/0702/001/login")
                .defaultSuccessUrl("/jsp/0702/001?loginSuccess", true)
                .loginProcessingUrl("/jsp/0702/001/authenticate"));
        http.logout(logout -> logout.deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(
                authz -> authz.requestMatchers(antMatcher("/jsp/0702/001/login/**")).permitAll()
                        .requestMatchers(antMatcher("/jsp/0702/**")).authenticated()
                        .requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManagerForAthn Bean defined by
     *        SpringSecurityAthnConfig#userLoginManagerForAthn
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(72)
    public SecurityFilterChain filterChainThymeleaf0701001(HttpSecurity http,
            @Qualifier("userLoginManagerForAthn") AuthenticationManager userLoginManagerForAthn)
            throws Exception {

        http.securityMatcher(antMatcher("/thymeleaf/0701/001/**"));
        http.authenticationManager(userLoginManagerForAthn);
        http.formLogin(login -> login.loginPage("/thymeleaf/0701/001/login")
                .defaultSuccessUrl("/thymeleaf/0701/001?loginSuccess", true)
                .loginProcessingUrl("/thymeleaf/0701/001/authenticate"));
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(
                authz -> authz.requestMatchers(antMatcher("/thymeleaf/0701/001/login/**"))
                        .permitAll().requestMatchers(antMatcher("/thymeleaf/0701/**"))
                        .authenticated().requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManagerForAthn Bean defined by
     *        SpringSecurityAthnConfig#userLoginManagerForAthn
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(73)
    public SecurityFilterChain filterChainThymeleaf0702001(HttpSecurity http,
            @Qualifier("userLoginManagerForAthn") AuthenticationManager userLoginManagerForAthn)
            throws Exception {

        http.securityMatcher(antMatcher("/thymeleaf/0702/001/**"));
        http.authenticationManager(userLoginManagerForAthn);
        http.formLogin(login -> login.loginPage("/thymeleaf/0702/001/login")
                .defaultSuccessUrl("/thymeleaf/0702/001?loginSuccess", true)
                .loginProcessingUrl("/thymeleaf/0702/001/authenticate"));
        http.logout(logout -> logout.deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(
                authz -> authz.requestMatchers(antMatcher("/thymeleaf/0702/001/login/**"))
                        .permitAll().requestMatchers(antMatcher("/thymeleaf/0702/**"))
                        .authenticated().requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }
}
