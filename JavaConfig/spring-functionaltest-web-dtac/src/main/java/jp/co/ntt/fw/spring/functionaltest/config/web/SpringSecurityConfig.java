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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.DelegatingAccessDeniedHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.csrf.CsrfAuthenticationStrategy;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.terasoluna.gfw.security.web.logging.UserIdMDCPutFilter;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    /**
     * enabledValue property.
     */
    @Value("${enabledValue}")
    private boolean enabledValue;

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManagerDTAC Bean defined by #userLoginManagerDTAC
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(1)
    public SecurityFilterChain filterChainJsp(HttpSecurity http,
            AuthenticationManager userLoginManagerDTAC) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/**"));
        http.authenticationManager(userLoginManagerDTAC);
        http.formLogin(login -> login.loginPage("/jsp/login")
                                     .defaultSuccessUrl("/jsp/login?loginSuccess")
                                     .loginProcessingUrl("/jsp/authenticate"));
        http.sessionManagement(sessionManagement -> sessionManagement.sessionAuthenticationStrategy(
                sessionAuthenticationStrategy()));
        http.logout(logout -> logout.logoutUrl("/jsp/logout")
                                    .logoutSuccessUrl("/jsp/login")
                                    .invalidateHttpSession(true)
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
    @Bean("userLoginManagerDTAC")
    public AuthenticationManager userLoginManagerDTAC(
            AuthenticationProvider authenticationProvider) {
        return new ProviderManager(authenticationProvider);
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param userDetailsServiceDTAC Bean defined by #userDetailsServiceDTAC
     * @param passwordEncoder Bean defined by ApplicationContextConfig#passwordEncoder
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("authenticationProvider")
    public AuthenticationProvider authenticationProvider(
            UserDetailsService userDetailsServiceDTAC,
            @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsServiceDTAC);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    /**
     * Configure {@link HttpSessionCsrfTokenRepository} bean.
     * @return Bean of configured {@link HttpSessionCsrfTokenRepository}
     */
    @Bean("csrfTokenRepository")
    public HttpSessionCsrfTokenRepository csrfTokenRepository() {
        return new HttpSessionCsrfTokenRepository();
    }

    /**
     * Configure {@link UserDetailsService} bean.
     * @param dataSource DataSource defined by SpringFunctionaltestEnvConfig#dataSource()
     * @return Bean of configured {@link UserDetailsService}
     */
    @Bean("userDetailsServiceDTAC")
    public UserDetailsService userDetailsServiceDTAC(
            @Qualifier("dataSource") DataSource dataSource) {
        JdbcDaoImpl bean = new JdbcDaoImpl();
        bean.setRolePrefix("ROLE_");
        bean.setDataSource(dataSource);
        bean.setUsersByUsernameQuery(
                "SELECT username, password, enabled FROM t_user WHERE username = ? and enabled = "
                        + enabledValue + "");
        bean.setAuthoritiesByUsernameQuery(
                "SELECT username, authority FROM t_user WHERE username = ? and enabled = "
                        + enabledValue + "");
        return bean;
    }

    /**
     * Configure {@link CompositeSessionAuthenticationStrategy} bean.
     * @return Bean of configured {@link CompositeSessionAuthenticationStrategy}
     */
    @Bean("sessionAuthenticationStrategy")
    public CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy() {
        List<SessionAuthenticationStrategy> list = new ArrayList<SessionAuthenticationStrategy>();
        list.add(sessionFixationProtectionStrategy());
        list.add(csrfAuthenticationStrategy());
        CompositeSessionAuthenticationStrategy bean = new CompositeSessionAuthenticationStrategy(list);
        return bean;
    }

    /**
     * Configure {@link SessionFixationProtectionStrategy} bean.
     * @return Bean of configured {@link SessionFixationProtectionStrategy}
     */
    @Bean
    public SessionFixationProtectionStrategy sessionFixationProtectionStrategy() {
        return new SessionFixationProtectionStrategy();
    }

    /**
     * Configure {@link CsrfAuthenticationStrategy} bean.
     * @return Bean of configured {@link CsrfAuthenticationStrategy}
     */
    @Bean
    public CsrfAuthenticationStrategy csrfAuthenticationStrategy() {
        return new CsrfAuthenticationStrategy(csrfTokenRepository());
    }

    /**
     * Configure ignore security pattern.
     * @return Bean of configured {@link WebSecurityCustomizer}
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(
                new AntPathRequestMatcher("/resources/**"));
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
	@Order(2)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.exceptionHandling(ex -> ex.accessDeniedHandler(
                accessDeniedHandler()));
        http.addFilterAfter(userIdMDCPutFilter(),
                AnonymousAuthenticationFilter.class);
        http.sessionManagement(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());
        return http.build();
    }

    /**
     * Configure {@link AccessDeniedHandler} bean.
     * @return Bean of configured {@link AccessDeniedHandler}
     */
    @Bean("accessDeniedHandler")
    public AccessDeniedHandler accessDeniedHandler() {
        LinkedHashMap<Class<? extends AccessDeniedException>, AccessDeniedHandler> errorHandlers = new LinkedHashMap<>();

        // Invalid CSRF authenticator error handler
        AccessDeniedHandlerImpl invalidCsrfTokenErrorHandler = new AccessDeniedHandlerImpl();
        invalidCsrfTokenErrorHandler.setErrorPage(
                "/WEB-INF/views/common/error/invalidCsrfTokenError.jsp");
        errorHandlers.put(InvalidCsrfTokenException.class,
                invalidCsrfTokenErrorHandler);

        // Missing CSRF authenticator error handler
        AccessDeniedHandlerImpl missingCsrfTokenErrorHandler = new AccessDeniedHandlerImpl();
        missingCsrfTokenErrorHandler.setErrorPage(
                "/WEB-INF/views/common/error/missingCsrfTokenError.jsp");
        errorHandlers.put(MissingCsrfTokenException.class,
                missingCsrfTokenErrorHandler);

        // Default error handler
        AccessDeniedHandlerImpl defaultErrorHandler = new AccessDeniedHandlerImpl();
        defaultErrorHandler.setErrorPage(
                "/WEB-INF/views/common/error/accessDeniedError.jsp");

        return new DelegatingAccessDeniedHandler(errorHandlers, defaultErrorHandler);
    }

    /**
     * Configure {@link DefaultWebSecurityExpressionHandler} bean.
     * @return Bean of configured {@link DefaultWebSecurityExpressionHandler}
     */
    @Bean("webSecurityExpressionHandler")
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
        return new DefaultWebSecurityExpressionHandler();
    }

    /**
     * Configure {@link UserIdMDCPutFilter} bean.
     * @return Bean of configured {@link UserIdMDCPutFilter}
     */
    @Bean("userIdMDCPutFilter")
    public UserIdMDCPutFilter userIdMDCPutFilter() {
        return new UserIdMDCPutFilter();
    }
}
