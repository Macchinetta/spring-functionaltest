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
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecurityAthn04Config {

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param dbUserLoginSpecifiedPassAuthenticationProvider Bean defined by
     *        #dbUserLoginSpecifiedPassAuthenticationProvider
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("dbUserLoginManagerForSpecifiedPass")
    public AuthenticationManager dbUserLoginManagerForSpecifiedPass(
            @Qualifier("dbUserLoginSpecifiedPassAuthenticationProvider") AuthenticationProvider dbUserLoginSpecifiedPassAuthenticationProvider) {
        return new ProviderManager(dbUserLoginSpecifiedPassAuthenticationProvider);
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param dbUserDetailsService Bean defined by SpringSecurityAthnConfig#dbUserDetailsService
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("dbUserLoginSpecifiedPassAuthenticationProvider")
    public AuthenticationProvider dbUserLoginSpecifiedPassAuthenticationProvider(
            @Qualifier("dbUserDetailsService") UserDetailsService dbUserDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(dbUserDetailsService);
        authProvider.setPasswordEncoder(athnPasswordEncoderSpecifiedPass());
        return authProvider;
    }

    /**
     * Configure {@link BCryptPasswordEncoder} bean.
     * @return Bean of configured {@link BCryptPasswordEncoder}
     */
    @Bean("athnPasswordEncoderSpecifiedPass")
    public BCryptPasswordEncoder athnPasswordEncoderSpecifiedPass() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param dbUserLoginSpecifiedHashAuthenticationProvider Bean defined by
     *        #dbUserLoginSpecifiedHashAuthenticationProvider
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("dbUserLoginManagerForSpecifiedHash")
    public AuthenticationManager dbUserLoginManagerForSpecifiedHash(
            @Qualifier("dbUserLoginSpecifiedHashAuthenticationProvider") AuthenticationProvider dbUserLoginSpecifiedHashAuthenticationProvider) {
        return new ProviderManager(dbUserLoginSpecifiedHashAuthenticationProvider);
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param dbUserDetailsService Bean defined by SpringSecurityAthnConfig#dbUserDetailsService
     * @param bCryptPasswordEncoder Bean defined by ApplicationContextConfig#bCryptPasswordEncoder
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("dbUserLoginSpecifiedHashAuthenticationProvider")
    public AuthenticationProvider dbUserLoginSpecifiedHashAuthenticationProvider(
            @Qualifier("dbUserDetailsService") UserDetailsService dbUserDetailsService,
            @Qualifier("bCryptPasswordEncoder") PasswordEncoder bCryptPasswordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(dbUserDetailsService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authProvider;
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param dbUserLoginManager Bean defined by SpringSecurityAthnConfig#dbUserLoginManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(40)
    public SecurityFilterChain filterChainJsp0401(HttpSecurity http,
            @Qualifier("dbUserLoginManager") AuthenticationManager dbUserLoginManager)
            throws Exception {

        http.securityMatcher(antMatcher("/jsp/0401/**"));
        http.authenticationManager(dbUserLoginManager);
        http.formLogin(login -> login.loginPage("/jsp/0401/001/login")
                .loginProcessingUrl("/jsp/0401/001/authenticate")
                .defaultSuccessUrl("/jsp/0401/001?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/jsp/0401/001/logout").logoutSuccessUrl("/"));
        http.authorizeHttpRequests(
                authz -> authz.requestMatchers(antMatcher("/jsp/0401/001/login/**")).permitAll()
                        .requestMatchers(antMatcher("/jsp/0401/001/logout/**")).permitAll()
                        .requestMatchers(antMatcher("/jsp/0401/**")).authenticated()
                        .requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param dbUserLoginManagerForSpecifiedPass Bean defined by #dbUserLoginManagerForSpecifiedPass
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(41)
    public SecurityFilterChain filterChainJsp0402001(HttpSecurity http,
            @Qualifier("dbUserLoginManagerForSpecifiedPass") AuthenticationManager dbUserLoginManagerForSpecifiedPass)
            throws Exception {

        http.securityMatcher(antMatcher("/jsp/0402/001/**"));
        http.authenticationManager(dbUserLoginManagerForSpecifiedPass);
        http.formLogin(login -> login.loginPage("/jsp/0402/001/login")
                .loginProcessingUrl("/jsp/0402/001/authenticate")
                .defaultSuccessUrl("/jsp/0402/001?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/jsp/0402/001/logout").logoutSuccessUrl("/"));
        http.authorizeHttpRequests(
                authz -> authz.requestMatchers(antMatcher("/jsp/0402/001/login/**")).permitAll()
                        .requestMatchers(antMatcher("/jsp/0402/001/logout/**")).permitAll()
                        .requestMatchers(antMatcher("/jsp/0402/001/**")).authenticated());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param dbUserLoginManagerForSpecifiedHash Bean defined by #dbUserLoginManagerForSpecifiedHash
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(42)
    public SecurityFilterChain filterChainJsp0402004(HttpSecurity http,
            @Qualifier("dbUserLoginManagerForSpecifiedHash") AuthenticationManager dbUserLoginManagerForSpecifiedHash)
            throws Exception {

        http.securityMatcher(antMatcher("/jsp/0402/004/**"));
        http.authenticationManager(dbUserLoginManagerForSpecifiedHash);
        http.formLogin(login -> login.loginPage("/jsp/0402/004/login")
                .loginProcessingUrl("/jsp/0402/004/authenticate")
                .defaultSuccessUrl("/jsp/0402/004?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/jsp/0402/004/logout").logoutSuccessUrl("/"));
        http.authorizeHttpRequests(
                authz -> authz.requestMatchers(antMatcher("/jsp/0402/004/login/**")).permitAll()
                        .requestMatchers(antMatcher("/jsp/0402/004/logout/**")).permitAll()
                        .requestMatchers(antMatcher("/jsp/0402/004/**")).authenticated());

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
    @Order(43)
    public SecurityFilterChain filterChainThymeleaf0401(HttpSecurity http,
            @Qualifier("dbUserLoginManager") AuthenticationManager dbUserLoginManager)
            throws Exception {

        http.securityMatcher(antMatcher("/thymeleaf/0401/**"));
        http.authenticationManager(dbUserLoginManager);
        http.formLogin(login -> login.loginPage("/thymeleaf/0401/001/login")
                .loginProcessingUrl("/thymeleaf/0401/001/authenticate")
                .defaultSuccessUrl("/thymeleaf/0401/001?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0401/001/logout").logoutSuccessUrl("/"));
        http.authorizeHttpRequests(
                authz -> authz.requestMatchers(antMatcher("/thymeleaf/0401/001/login/**"))
                        .permitAll().requestMatchers(antMatcher("/thymeleaf/0401/001/logout/**"))
                        .permitAll().requestMatchers(antMatcher("/thymeleaf/0401/**"))
                        .authenticated().requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param dbUserLoginManagerForSpecifiedPass Bean defined by #dbUserLoginManagerForSpecifiedPass
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(44)
    public SecurityFilterChain filterChainThymeleaf0402001(HttpSecurity http,
            @Qualifier("dbUserLoginManagerForSpecifiedPass") AuthenticationManager dbUserLoginManagerForSpecifiedPass)
            throws Exception {

        http.securityMatcher(antMatcher("/thymeleaf/0402/001/**"));
        http.authenticationManager(dbUserLoginManagerForSpecifiedPass);
        http.formLogin(login -> login.loginPage("/thymeleaf/0402/001/login")
                .loginProcessingUrl("/thymeleaf/0402/001/authenticate")
                .defaultSuccessUrl("/thymeleaf/0402/001?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0402/001/logout").logoutSuccessUrl("/"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(antMatcher("/thymeleaf/0402/001/login/**")).permitAll()
                .requestMatchers(antMatcher("/thymeleaf/0402/001/logout/**")).permitAll()
                .requestMatchers(antMatcher("/thymeleaf/0402/001/**")).authenticated());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param dbUserLoginManagerForSpecifiedHash Bean defined by #dbUserLoginManagerForSpecifiedHash
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(45)
    public SecurityFilterChain filterChainThymeleaf0402004(HttpSecurity http,
            @Qualifier("dbUserLoginManagerForSpecifiedHash") AuthenticationManager dbUserLoginManagerForSpecifiedHash)
            throws Exception {

        http.securityMatcher(antMatcher("/thymeleaf/0402/004/**"));
        http.authenticationManager(dbUserLoginManagerForSpecifiedHash);
        http.formLogin(login -> login.loginPage("/thymeleaf/0402/004/login")
                .loginProcessingUrl("/thymeleaf/0402/004/authenticate")
                .defaultSuccessUrl("/thymeleaf/0402/004?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0402/004/logout").logoutSuccessUrl("/"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(antMatcher("/thymeleaf/0402/004/login/**")).permitAll()
                .requestMatchers(antMatcher("/thymeleaf/0402/004/logout/**")).permitAll()
                .requestMatchers(antMatcher("/thymeleaf/0402/004/**")).authenticated());

        return http.build();
    }
}
