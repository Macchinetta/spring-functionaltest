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
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecurityAthr03Config {

    /**
     * enabledValue property.
     */
    @Value("${enabledValue}")
    private boolean enabledValue;

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginForMethodManager Bean defined by SpringSecurityAthrConfig#loginForMethodManager
     * @param sessionAuthenticationStrategy Bean defined by SpringSecurity#sessionAuthenticationStrategy
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(30)
    public SecurityFilterChain filterChainJsp0301(HttpSecurity http,
            @Qualifier("loginForMethodManager") AuthenticationManager loginForMethodManager,
            CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/0301/001/**"));
        http.authenticationManager(loginForMethodManager);
        http.formLogin(login -> login.loginPage("/jsp/0301/001")
                .loginProcessingUrl("/jsp/0301/001/authenticate")
                .defaultSuccessUrl("/jsp/0301/001/afterLogin"));
        http.sessionManagement(sessionManagement -> sessionManagement
                .sessionAuthenticationStrategy(sessionAuthenticationStrategy));
        http.logout(logout -> logout.logoutUrl("/jsp/0301/001/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"));
        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .accessDeniedPage("/jsp/methodAccessDeniedPage"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginForMethodManager Bean defined by SpringSecurityAthrConfig#loginForMethodManager
     * @param sessionAuthenticationStrategy Bean defined by SpringSecurity#sessionAuthenticationStrategy
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(31)
    public SecurityFilterChain filterChaintThymeleaf0301(HttpSecurity http,
            @Qualifier("loginForMethodManager") AuthenticationManager loginForMethodManager,
            CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0301/001/**"));
        http.authenticationManager(loginForMethodManager);
        http.formLogin(login -> login.loginPage("/thymeleaf/0301/001")
                .loginProcessingUrl("/thymeleaf/0301/001/authenticate")
                .defaultSuccessUrl("/thymeleaf/0301/001/afterLogin"));
        http.sessionManagement(sessionManagement -> sessionManagement
                .sessionAuthenticationStrategy(sessionAuthenticationStrategy));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0301/001/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"));
        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .accessDeniedPage("/thymeleaf/methodAccessDeniedPage"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link UserDetailsService} bean.
     * @param dataSource DataSource defined by SpringFunctionaltestEnvConfig#dataSource()
     * @return Bean of configured {@link UserDetailsService}
     */
    @Bean("loginForMethodDetailsService")
    public UserDetailsService loginForMethodDetailsService(
            @Qualifier("dataSource") DataSource dataSource) {
        JdbcDaoImpl bean = new JdbcDaoImpl();
        bean.setDataSource(dataSource);
        bean.setUsersByUsernameQuery(
                "SELECT username, password, enabled FROM t_staff WHERE username = ? and enabled = "
                        + enabledValue + "");
        bean.setAuthoritiesByUsernameQuery(
                "SELECT username, authority FROM t_staff WHERE username = ? and enabled = "
                        + enabledValue + "");
        return bean;
    }
}
