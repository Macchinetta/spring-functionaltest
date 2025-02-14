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
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
@EnableMethodSecurity
public class SpringSecurityAthr09Config {

    /**
     * enabledValue property.
     */
    @Value("${enabledValue}")
    private boolean enabledValue;

    /**
     * Configure {@link RoleHierarchy} bean.
     * @return Bean of configured {@link RoleHierarchy}
     */
    @Bean("authorizationRoleHierarchy")
    public RoleHierarchy authorizationRoleHierarchy() {
        RoleHierarchy bean = RoleHierarchyImpl.fromHierarchy("""
                ROLE_ADMIN > ROLE_STAFF
                ROLE_STAFF > ROLE_USER
                """);
        return bean;
    }

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param loginForRoleHierarchyAuthenticationProvider Bean defined by
     *        #loginForRoleHierarchyAuthenticationProvider
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("loginForRoleHierarchyManager")
    public AuthenticationManager loginForRoleHierarchyManager(
            @Qualifier("loginForRoleHierarchyAuthenticationProvider") AuthenticationProvider loginForRoleHierarchyAuthenticationProvider) {
        return new ProviderManager(loginForRoleHierarchyAuthenticationProvider);
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param loginForRoleHierarchyDetailsService Bean defined by
     *        #loginForRoleHierarchyDetailsService
     * @param passwordEncoder Bean defined by ApplicationContextConfig#passwordEncoder
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("loginForRoleHierarchyAuthenticationProvider")
    public AuthenticationProvider loginForRoleHierarchyAuthenticationProvider(
            @Qualifier("loginForRoleHierarchyDetailsService") UserDetailsService loginForRoleHierarchyDetailsService,
            @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(loginForRoleHierarchyDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    /**
     * Configure {@link UserDetailsService} bean.
     * @param dataSource DataSource defined by SpringFunctionaltestEnvConfig#dataSource()
     * @return Bean of configured {@link UserDetailsService}
     */
    @Bean("loginForRoleHierarchyDetailsService")
    public UserDetailsService loginForRoleHierarchyDetailsService(
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

    /**
     * Configure {@link DefaultMethodSecurityExpressionHandler} bean.
     * @return Bean of configured {@link DefaultMethodSecurityExpressionHandler}
     */
    @Bean("authorizationJspRoleHierarchyHandler")
    public SecurityExpressionHandler<FilterInvocation> authorizationJspRoleHierarchyHandler() {
        DefaultWebSecurityExpressionHandler bean = new DefaultWebSecurityExpressionHandler();
        bean.setRoleHierarchy(authorizationRoleHierarchy());
        return bean;
    }

    /**
     * Configure {@link DefaultMethodSecurityExpressionHandler} bean.
     * @param authorizationRoleHierarchy Bean defined by #authorizationRoleHierarchy
     * @return Bean of configured {@link DefaultMethodSecurityExpressionHandler}
     */
    @Bean("authorizationRoleHierarchyMethodExpressionHandler")
    public static MethodSecurityExpressionHandler authorizationRoleHierarchyMethodExpressionHandler(
            RoleHierarchy authorizationRoleHierarchy) {
        DefaultMethodSecurityExpressionHandler bean = new DefaultMethodSecurityExpressionHandler();
        bean.setRoleHierarchy(authorizationRoleHierarchy);
        return bean;
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginForRoleHierarchyManager Bean defined by #loginForRoleHierarchyManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(90)
    public SecurityFilterChain filterChainJsp0901(HttpSecurity http,
            @Qualifier("loginForRoleHierarchyManager") AuthenticationManager loginForRoleHierarchyManager)
            throws Exception {

        AuthorityAuthorizationManager<RequestAuthorizationContext> authManager =
                AuthorityAuthorizationManager.hasRole("STAFF");
        authManager.setRoleHierarchy(authorizationRoleHierarchy());

        http.securityMatcher(antMatcher("/jsp/0901/001/**"));
        http.authenticationManager(loginForRoleHierarchyManager);
        http.formLogin(login -> login.loginPage("/jsp/0901/001")
                .loginProcessingUrl("/jsp/0901/001/authenticate")
                .defaultSuccessUrl("/jsp/0901/001/afterLogin"));
        http.logout(logout -> logout.logoutUrl("/jsp/0901/001/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID"));
        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .accessDeniedPage("/jsp/hierarchyAccessDeniedPage"));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/jsp/0901/001/*"))
                .access(authManager).requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginForRoleHierarchyManager Bean defined by #loginForRoleHierarchyManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(91)
    public SecurityFilterChain filterChainJsp0902(HttpSecurity http,
            @Qualifier("loginForRoleHierarchyManager") AuthenticationManager loginForRoleHierarchyManager)
            throws Exception {

        http.securityMatcher(antMatcher("/jsp/0901/002/**"));
        http.authenticationManager(loginForRoleHierarchyManager);
        http.formLogin(login -> login.loginPage("/jsp/0901/002")
                .loginProcessingUrl("/jsp/0901/002/authenticate")
                .defaultSuccessUrl("/jsp/0901/002/afterLogin"));
        http.logout(logout -> logout.logoutUrl("/jsp/0901/002/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID"));
        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .accessDeniedPage("/jsp/hierarchyAccessDeniedPage"));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginForRoleHierarchyManager Bean defined by #loginForRoleHierarchyManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(92)
    public SecurityFilterChain filterChainJsp0903(HttpSecurity http,
            @Qualifier("loginForRoleHierarchyManager") AuthenticationManager loginForRoleHierarchyManager)
            throws Exception {

        http.securityMatcher(antMatcher("/jsp/0901/003/**"));
        http.authenticationManager(loginForRoleHierarchyManager);
        http.formLogin(login -> login.loginPage("/jsp/0901/003")
                .loginProcessingUrl("/jsp/0901/003/authenticate")
                .defaultSuccessUrl("/jsp/0901/003/afterLogin"));
        http.logout(logout -> logout.logoutUrl("/jsp/0901/003/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID"));
        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .accessDeniedPage("/jsp/methodHierarchyAccessDeniedPage"));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/jsp/0901/003/*"))
                .permitAll().requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginForRoleHierarchyManager Bean defined by #loginForRoleHierarchyManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(93)
    public SecurityFilterChain filterChainThymeleaf0901(HttpSecurity http,
            @Qualifier("loginForRoleHierarchyManager") AuthenticationManager loginForRoleHierarchyManager)
            throws Exception {

        AuthorityAuthorizationManager<RequestAuthorizationContext> authManager =
                AuthorityAuthorizationManager.hasRole("STAFF");
        authManager.setRoleHierarchy(authorizationRoleHierarchy());

        http.securityMatcher(antMatcher("/thymeleaf/0901/001/**"));
        http.authenticationManager(loginForRoleHierarchyManager);
        http.formLogin(login -> login.loginPage("/thymeleaf/0901/001")
                .loginProcessingUrl("/thymeleaf/0901/001/authenticate")
                .defaultSuccessUrl("/thymeleaf/0901/001/afterLogin"));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0901/001/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID"));
        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .accessDeniedPage("/thymeleaf/hierarchyAccessDeniedPage"));
        http.authorizeHttpRequests(
                authz -> authz.requestMatchers(antMatcher("/thymeleaf/0901/001/*"))
                        .access(authManager).requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginForRoleHierarchyManager Bean defined by #loginForRoleHierarchyManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(94)
    public SecurityFilterChain filterChainThymeleaf0902(HttpSecurity http,
            @Qualifier("loginForRoleHierarchyManager") AuthenticationManager loginForRoleHierarchyManager)
            throws Exception {

        http.securityMatcher(antMatcher("/thymeleaf/0901/002/**"));
        http.authenticationManager(loginForRoleHierarchyManager);
        http.formLogin(login -> login.loginPage("/thymeleaf/0901/002")
                .loginProcessingUrl("/thymeleaf/0901/002/authenticate")
                .defaultSuccessUrl("/thymeleaf/0901/002/afterLogin"));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0901/002/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID"));
        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .accessDeniedPage("/thymeleaf/hierarchyAccessDeniedPage"));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginForRoleHierarchyManager Bean defined by #loginForRoleHierarchyManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(95)
    public SecurityFilterChain filterChainThymeleaf0903(HttpSecurity http,
            @Qualifier("loginForRoleHierarchyManager") AuthenticationManager loginForRoleHierarchyManager)
            throws Exception {

        http.securityMatcher(antMatcher("/thymeleaf/0901/003/**"));
        http.authenticationManager(loginForRoleHierarchyManager);
        http.formLogin(login -> login.loginPage("/thymeleaf/0901/003")
                .loginProcessingUrl("/thymeleaf/0901/003/authenticate")
                .defaultSuccessUrl("/thymeleaf/0901/003/afterLogin"));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0901/003/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID"));
        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .accessDeniedPage("/thymeleaf/methodHierarchyAccessDeniedPage"));
        http.authorizeHttpRequests(
                authz -> authz.requestMatchers(antMatcher("/thymeleaf/0901/003/*")).permitAll()
                        .requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }
}
