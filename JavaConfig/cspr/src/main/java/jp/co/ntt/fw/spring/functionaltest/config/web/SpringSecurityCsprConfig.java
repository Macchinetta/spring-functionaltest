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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.terasoluna.gfw.security.web.logging.UserIdMDCPutFilter;
import jp.co.ntt.fw.spring.functionaltest.domain.service.cspr.CommitterUserDetailsService;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecurityCsprConfig {

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param committerAuthenticationManager Bean defined by #committerAuthenticationManager
     * @param userIdMDCPutFilter Bean defined by SpringSecurityConfig#userIdMDCPutFilter
     * @param sessionAuthenticationStrategy Bean defined by
     *        SpringSecurityConfig#sessionAuthenticationStrategy
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(1)
    public SecurityFilterChain filterChainJspNotUseDeniedHandler(HttpSecurity http,
            AuthenticationManager committerAuthenticationManager,
            UserIdMDCPutFilter userIdMDCPutFilter,
            CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy) throws Exception {

        http.securityMatcher(antMatcher("/jsp/notUseDeniedHandler/**"));
        http.authenticationManager(committerAuthenticationManager);
        http.addFilterAfter(userIdMDCPutFilter, AnonymousAuthenticationFilter.class);
        http.formLogin(login -> login.loginProcessingUrl("/jsp/authenticate").defaultSuccessUrl("/")
                .loginPage("/jsp/cspr/login"));
        http.logout(logout -> logout.logoutUrl("/jsp/logout").deleteCookies("JSESSIONID"));
        http.sessionManagement(
                session -> session.sessionAuthenticationStrategy(sessionAuthenticationStrategy));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/jsp/0301/001"))
                .hasRole("USER").requestMatchers(antMatcher("/jsp/0301/003")).hasRole("USER")
                .requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param committerAuthenticationManager Bean defined by #committerAuthenticationManager
     * @param userIdMDCPutFilter Bean defined by SpringSecurityConfig#userIdMDCPutFilter
     * @param sessionAuthenticationStrategy Bean defined by
     *        SpringSecurityConfig#sessionAuthenticationStrategy
     * @param accessDeniedHandler Bean defined by SpringSecurityConfig#accessDeniedHandler
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(2)
    public SecurityFilterChain filterChainJsp(HttpSecurity http,
            AuthenticationManager committerAuthenticationManager,
            UserIdMDCPutFilter userIdMDCPutFilter,
            CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy,
            @Qualifier("accessDeniedHandler") AccessDeniedHandler accessDeniedHandler)
            throws Exception {

        http.securityMatcher(antMatcher("/jsp/**"));
        http.authenticationManager(committerAuthenticationManager);
        http.csrf(csrf -> csrf.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler()));
        http.exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler));
        http.addFilterAfter(userIdMDCPutFilter, AnonymousAuthenticationFilter.class);
        http.formLogin(login -> login.loginProcessingUrl("/jsp/authenticate").defaultSuccessUrl("/")
                .loginPage("/jsp/cspr/login"));
        http.logout(logout -> logout.logoutUrl("/jsp/logout").deleteCookies("JSESSIONID"));
        http.sessionManagement(
                session -> session.sessionAuthenticationStrategy(sessionAuthenticationStrategy));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/jsp/0301/001"))
                .hasRole("USER").requestMatchers(antMatcher("/jsp/0301/003")).hasRole("USER")
                .requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param committerAuthenticationManager Bean defined by #committerAuthenticationManager
     * @param userIdMDCPutFilter Bean defined by SpringSecurityConfig#userIdMDCPutFilter
     * @param sessionAuthenticationStrategy Bean defined by
     *        SpringSecurityConfig#sessionAuthenticationStrategy
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(3)
    public SecurityFilterChain filterChainThymeleafNotUseDeniedHandler(HttpSecurity http,
            AuthenticationManager committerAuthenticationManager,
            UserIdMDCPutFilter userIdMDCPutFilter,
            CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy) throws Exception {

        http.securityMatcher(antMatcher("/thymeleaf/notUseDeniedHandler/**"));
        http.authenticationManager(committerAuthenticationManager);
        http.addFilterAfter(userIdMDCPutFilter, AnonymousAuthenticationFilter.class);
        http.formLogin(login -> login.loginProcessingUrl("/thymeleaf/authenticate")
                .defaultSuccessUrl("/").loginPage("/thymeleaf/cspr/login"));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/logout").deleteCookies("JSESSIONID"));
        http.sessionManagement(
                session -> session.sessionAuthenticationStrategy(sessionAuthenticationStrategy));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/thymeleaf/0301/001"))
                .hasRole("USER").requestMatchers(antMatcher("/thymeleaf/0301/003")).hasRole("USER")
                .requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param committerAuthenticationManager Bean defined by #committerAuthenticationManager
     * @param userIdMDCPutFilter Bean defined by SpringSecurityConfig#userIdMDCPutFilter
     * @param sessionAuthenticationStrategy Bean defined by
     *        SpringSecurityConfig#sessionAuthenticationStrategy
     * @param accessDeniedHandler Bean defined by SpringSecurityConfig#accessDeniedHandler
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(4)
    public SecurityFilterChain filterChainThymeleaf(HttpSecurity http,
            AuthenticationManager committerAuthenticationManager,
            UserIdMDCPutFilter userIdMDCPutFilter,
            CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy,
            @Qualifier("accessDeniedHandler") AccessDeniedHandler accessDeniedHandler)
            throws Exception {

        http.securityMatcher(antMatcher("/thymeleaf/**"));
        http.authenticationManager(committerAuthenticationManager);
        http.csrf(csrf -> csrf.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler()));
        http.exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler));
        http.addFilterAfter(userIdMDCPutFilter, AnonymousAuthenticationFilter.class);
        http.formLogin(login -> login.loginProcessingUrl("/thymeleaf/authenticate")
                .defaultSuccessUrl("/").loginPage("/thymeleaf/cspr/login"));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/logout").deleteCookies("JSESSIONID"));
        http.sessionManagement(
                session -> session.sessionAuthenticationStrategy(sessionAuthenticationStrategy));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/thymeleaf/0301/001"))
                .hasRole("USER").requestMatchers(antMatcher("/thymeleaf/0301/003")).hasRole("USER")
                .requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link CsrfTokenRequestAttributeHandler} bean.
     * @return Bean of configured {@link CsrfTokenRequestAttributeHandler}
     */
    @Bean("csrfTokenRequestAttributeHandler")
    public CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler() {
        CsrfTokenRequestAttributeHandler bean = new CsrfTokenRequestAttributeHandler();
        bean.setCsrfRequestAttributeName("_csrf");
        return bean;
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param committerAuthenticationManager Bean defined by #committerAuthenticationManager
     * @param userIdMDCPutFilter Bean defined by SpringSecurityConfig#userIdMDCPutFilter
     * @param sessionAuthenticationStrategy Bean defined by
     *        SpringSecurityConfig#sessionAuthenticationStrategy
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(5)
    public SecurityFilterChain filterChainJspDisabledcspr(HttpSecurity http,
            AuthenticationManager committerAuthenticationManager,
            UserIdMDCPutFilter userIdMDCPutFilter,
            CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy) throws Exception {

        http.securityMatcher(antMatcher("/jspDisabledcspr/**"));
        http.authenticationManager(committerAuthenticationManager);
        http.csrf(csrf -> csrf.disable());
        http.addFilterAfter(userIdMDCPutFilter, AnonymousAuthenticationFilter.class);
        http.formLogin(login -> login.loginProcessingUrl("/jspDisabledcspr/authenticate")
                .defaultSuccessUrl("/").loginPage("/jspDisabledcspr/login"));
        http.logout(
                logout -> logout.logoutUrl("/jspDisabledcspr/logout").deleteCookies("JSESSIONID"));
        http.sessionManagement(
                session -> session.sessionAuthenticationStrategy(sessionAuthenticationStrategy));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param committerAuthenticationManager Bean defined by #committerAuthenticationManager
     * @param userIdMDCPutFilter Bean defined by SpringSecurityConfig#userIdMDCPutFilter
     * @param sessionAuthenticationStrategy Bean defined by
     *        SpringSecurityConfig#sessionAuthenticationStrategy
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(6)
    public SecurityFilterChain filterChainThymeleafDisabledcspr(HttpSecurity http,
            AuthenticationManager committerAuthenticationManager,
            UserIdMDCPutFilter userIdMDCPutFilter,
            CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy) throws Exception {

        http.securityMatcher(antMatcher("/thymeleafDisabledcspr/**"));
        http.authenticationManager(committerAuthenticationManager);
        http.csrf(csrf -> csrf.disable());
        http.addFilterAfter(userIdMDCPutFilter, AnonymousAuthenticationFilter.class);
        http.formLogin(login -> login.loginProcessingUrl("/thymeleafDisabledcspr/authenticate")
                .defaultSuccessUrl("/").loginPage("/thymeleafDisabledcspr/login"));
        http.logout(logout -> logout.logoutUrl("/thymeleafDisabledcspr/logout")
                .deleteCookies("JSESSIONID"));
        http.sessionManagement(
                session -> session.sessionAuthenticationStrategy(sessionAuthenticationStrategy));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param committerPasswordAuthenticationProvider Bean defined by
     *        #committerPasswordAuthenticationProvider
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("committerAuthenticationManager")
    public AuthenticationManager committerAuthenticationManager(
            AuthenticationProvider committerPasswordAuthenticationProvider) {
        return new ProviderManager(committerPasswordAuthenticationProvider);
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param passwordEncoder Bean defined by ApplicationContextConfig#passwordEncoder
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("committerPasswordAuthenticationProvider")
    public AuthenticationProvider committerPasswordAuthenticationProvider(
            @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setHideUserNotFoundExceptions(true);
        authProvider.setPasswordEncoder(passwordEncoder);
        authProvider.setUserDetailsService(committerService());
        return authProvider;
    }

    /**
     * Configure {@link CommitterUserDetailsService} bean.
     * @return Bean of configured {@link CommitterUserDetailsService}
     */
    @Bean("committerService")
    public CommitterUserDetailsService committerService() {
        return new CommitterUserDetailsService();
    }
}
