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

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecurityVldtConfig {

    /**
     * enabledValue property.
     */
    @Value("${enabledValue}")
    private boolean enabledValue;

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManager Bean defined by #userLoginManager
     * @param sessionAuthenticationStrategy Bean defined by SpringSecurityConfig#sessionAuthenticationStrategy
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(1)
    public SecurityFilterChain filterChainJsp(HttpSecurity http,
            AuthenticationManager userLoginManager,
            CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/0103/003/**"));
        http.authenticationManager(userLoginManager);
        http.formLogin(login -> login.loginPage("/jsp/vldt/authorizedValidationView")
                                     .defaultSuccessUrl("/jsp/0103/003?LoginSuccess")
                                     .loginProcessingUrl("/jsp/0103/003/authentication"));
        http.sessionManagement(sessionManagement -> sessionManagement.sessionAuthenticationStrategy(
                sessionAuthenticationStrategy));
        http.logout(logout -> logout.logoutUrl("/jsp/0103/003/logout")
                                    .logoutSuccessUrl("/jsp/0103/003")
                                    .deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManager Bean defined by #userLoginManager
     * @param sessionAuthenticationStrategy Bean defined by SpringSecurityConfig#sessionAuthenticationStrategy
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(2)
    public SecurityFilterChain filterChainThymeleaf(HttpSecurity http,
            AuthenticationManager userLoginManager,
            CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0103/003/**"));
        http.authenticationManager(userLoginManager);
        http.formLogin(login -> login.loginPage("/thymeleaf/vldt/authorizedValidationView")
                                     .defaultSuccessUrl("/thymeleaf/0103/003?LoginSuccess")
                                     .loginProcessingUrl("/thymeleaf/0103/003/authentication"));
        http.sessionManagement(sessionManagement -> sessionManagement
                .sessionAuthenticationStrategy(sessionAuthenticationStrategy));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0103/003/logout")
                                    .logoutSuccessUrl("/thymeleafp/0103/003")
                                    .deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param authenticationProvider Bean defined by #authenticationProvider
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("userLoginManager")
    public AuthenticationManager userLoginManager(
            AuthenticationProvider authenticationProvider) throws Exception {
        return new ProviderManager(authenticationProvider);
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param vldtUserDetailsService Bean defined by #vldtUserDetailsService
     * @param passwordEncoder Bean defined by ApplicationContextConfig#passwordEncoder
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("authenticationProvider")
    public AuthenticationProvider authenticationProvider(
            UserDetailsService vldtUserDetailsService,
            @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(vldtUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    /**
     * Configure {@link UserDetailsService} bean.
     * @param dataSource DataSource defined by SpringFunctionaltestEnvConfig#dataSource()
     * @return Bean of configured {@link UserDetailsService}
     */
    @Bean("vldtUserDetailsService")
    public UserDetailsService vldtUserDetailsService(
            @Qualifier("dataSource") DataSource dataSource) {
        JdbcDaoImpl bean = new JdbcDaoImpl();
        bean.setRolePrefix("ROLE_");
        bean.setDataSource(dataSource);
        bean.setUsersByUsernameQuery(
                "SELECT username, password, enabled FROM t_vldt_account WHERE username = ? and enabled = "
                        + enabledValue + "");
        bean.setAuthoritiesByUsernameQuery(
                "SELECT username, authority FROM t_vldt_account WHERE username = ? and enabled = "
                        + enabledValue + "");
        return bean;
    }

}
