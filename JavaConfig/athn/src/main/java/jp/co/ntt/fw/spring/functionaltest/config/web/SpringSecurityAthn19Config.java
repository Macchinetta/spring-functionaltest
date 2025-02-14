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

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;

/**
 * Bean definition to configure SpringSecurity.
 */
// MessageDigestPasswordEncoderが非推奨であることはガイドラインで記載済み
@SuppressWarnings("deprecation")
@Configuration
public class SpringSecurityAthn19Config {

    /**
     * password.messagedigest.algorithm property.
     */
    @Value("${password.messagedigest.algorithm}")
    private String messagedigestAlgorithm;

    /**
     * password.messagedigest.hashedstretching property.
     */
    @Value("${password.messagedigest.hashedstretching}")
    private int messagedigestHashedstretching;

    /**
     * enabledValue property.
     */
    @Value("${enabledValue}")
    private boolean enabledValue;

    /**
     * Configure {@link MessageDigestPasswordEncoder} bean.
     * @return Bean of configured {@link MessageDigestPasswordEncoder}
     */
    @Bean("athnPasswordEncoderMessageDigest")
    public MessageDigestPasswordEncoder athnPasswordEncoderMessageDigest() {
        MessageDigestPasswordEncoder bean =
                new MessageDigestPasswordEncoder(messagedigestAlgorithm);
        bean.setIterations(messagedigestHashedstretching);
        return bean;
    }

    /**
     * Configure {@link PasswordEncoder} bean.
     * @return Bean of configured {@link DelegatingPasswordEncoder}
     */
    @Bean("athnPasswordEncoderMessageDigestDelegating")
    public PasswordEncoder athnPasswordEncoderMessageDigestDelegating() {
        Map<String, PasswordEncoder> idToPasswordEncoder = new HashMap<>();
        idToPasswordEncoder.put("MD", messageDigestPasswordEncoder());
        return new DelegatingPasswordEncoder("MD", idToPasswordEncoder);
    }

    /**
     * Configure {@link MessageDigestPasswordEncoder} bean.
     * @return Bean of configured {@link MessageDigestPasswordEncoder}
     */
    @Bean
    public MessageDigestPasswordEncoder messageDigestPasswordEncoder() {
        MessageDigestPasswordEncoder bean =
                new MessageDigestPasswordEncoder(messagedigestAlgorithm);
        bean.setIterations(messagedigestHashedstretching);
        return bean;
    }

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param loginWithMessageDigestPasswordAuthenticationProvider Bean defined by
     *        #loginWithMessageDigestPasswordAuthenticationProvider
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("loginWithMessageDigestPassword")
    public AuthenticationManager loginWithMessageDigestPassword(
            @Qualifier("loginWithMessageDigestPasswordAuthenticationProvider") AuthenticationProvider loginWithMessageDigestPasswordAuthenticationProvider) {
        return new ProviderManager(loginWithMessageDigestPasswordAuthenticationProvider);
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param loginWithMessageDigestPasswordService Bean defined by
     *        #loginWithMessageDigestPasswordService
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("loginWithMessageDigestPasswordAuthenticationProvider")
    public AuthenticationProvider loginWithMessageDigestPasswordAuthenticationProvider(
            @Qualifier("loginWithMessageDigestPasswordService") UserDetailsService loginWithMessageDigestPasswordService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(loginWithMessageDigestPasswordService);
        authProvider.setPasswordEncoder(athnPasswordEncoderMessageDigest());
        return authProvider;
    }

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param loginWithMessageDigestDelegatingPasswordAuthenticationProvider Bean defined by
     *        #loginWithMessageDigestDelegatingPasswordAuthenticationProvider
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("loginWithMessageDigestDelegatingPassword")
    public AuthenticationManager loginWithMessageDigestDelegatingPassword(
            @Qualifier("loginWithMessageDigestDelegatingPasswordAuthenticationProvider") AuthenticationProvider loginWithMessageDigestDelegatingPasswordAuthenticationProvider) {
        return new ProviderManager(loginWithMessageDigestDelegatingPasswordAuthenticationProvider);
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param loginWithMessageDigestPasswordService Bean defined by
     *        #loginWithMessageDigestPasswordService
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("loginWithMessageDigestDelegatingPasswordAuthenticationProvider")
    public AuthenticationProvider loginWithMessageDigestDelegatingPasswordAuthenticationProvider(
            @Qualifier("loginWithMessageDigestPasswordService") UserDetailsService loginWithMessageDigestPasswordService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(loginWithMessageDigestPasswordService);
        authProvider.setPasswordEncoder(athnPasswordEncoderMessageDigestDelegating());
        return authProvider;
    }

    /**
     * Configure {@link UserDetailsService} bean.
     * @param dataSource DataSource defined by SpringFunctionaltestEnvConfig#dataSource()
     * @return Bean of configured {@link UserDetailsService}
     */
    @Bean("loginWithMessageDigestPasswordService")
    public UserDetailsService loginWithMessageDigestPasswordService(
            @Qualifier("dataSource") DataSource dataSource) {
        JdbcDaoImpl bean = new JdbcDaoImpl();
        bean.setDataSource(dataSource);
        bean.setUsersByUsernameQuery(
                "SELECT username, password, enabled FROM t_administrator WHERE username = ? and enabled = "
                        + enabledValue + "");
        bean.setAuthoritiesByUsernameQuery(
                "SELECT username, authority FROM t_administrator WHERE username = ? and enabled = "
                        + enabledValue + "");
        return bean;
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginWithMessageDigestPassword Bean defined by #loginWithMessageDigestPassword
     * @param sessionAuthenticationStrategy Bean defined by
     *        SpringSecurityAthn17Config#sessionAuthenticationStrategy
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(190)
    public SecurityFilterChain filterChainJsp1901002(HttpSecurity http,
            @Qualifier("loginWithMessageDigestPassword") AuthenticationManager loginWithMessageDigestPassword,
            CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy) throws Exception {

        http.securityMatcher(antMatcher("/jsp/1901/002/**"));
        http.authenticationManager(loginWithMessageDigestPassword);
        http.formLogin(login -> login.loginPage("/jsp/1901/002")
                .loginProcessingUrl("/jsp/1901/002/authenticate")
                .defaultSuccessUrl("/jsp/1901/002/afterLogin"));
        http.sessionManagement(sessionManagement -> sessionManagement
                .sessionAuthenticationStrategy(sessionAuthenticationStrategy));
        http.logout(logout -> logout.logoutUrl("/jsp/1901/002/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginWithMessageDigestDelegatingPassword Bean defined by
     *        #loginWithMessageDigestDelegatingPassword
     * @param sessionAuthenticationStrategy Bean defined by
     *        SpringSecurityAthn17Config#sessionAuthenticationStrategy
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(191)
    public SecurityFilterChain filterChainJsp1901004(HttpSecurity http,
            @Qualifier("loginWithMessageDigestDelegatingPassword") AuthenticationManager loginWithMessageDigestDelegatingPassword,
            CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy) throws Exception {

        http.securityMatcher(antMatcher("/jsp/1901/004/**"));
        http.authenticationManager(loginWithMessageDigestDelegatingPassword);
        http.formLogin(login -> login.loginPage("/jsp/1901/004")
                .loginProcessingUrl("/jsp/1901/004/authenticate")
                .defaultSuccessUrl("/jsp/1901/004/afterLogin"));
        http.sessionManagement(sessionManagement -> sessionManagement
                .sessionAuthenticationStrategy(sessionAuthenticationStrategy));
        http.logout(logout -> logout.logoutUrl("/jsp/1901/004/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginWithMessageDigestPassword Bean defined by #loginWithMessageDigestPassword
     * @param sessionAuthenticationStrategy Bean defined by
     *        SpringSecurityAthn17Config#sessionAuthenticationStrategy
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(192)
    public SecurityFilterChain filterChainThymeleaf1901002(HttpSecurity http,
            @Qualifier("loginWithMessageDigestPassword") AuthenticationManager loginWithMessageDigestPassword,
            CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy) throws Exception {

        http.securityMatcher(antMatcher("/thymeleaf/1901/002/**"));
        http.authenticationManager(loginWithMessageDigestPassword);
        http.formLogin(login -> login.loginPage("/thymeleaf/1901/002")
                .loginProcessingUrl("/thymeleaf/1901/002/authenticate")
                .defaultSuccessUrl("/thymeleaf/1901/002/afterLogin"));
        http.sessionManagement(sessionManagement -> sessionManagement
                .sessionAuthenticationStrategy(sessionAuthenticationStrategy));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/1901/002/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginWithMessageDigestDelegatingPassword Bean defined by
     *        #loginWithMessageDigestDelegatingPassword
     * @param sessionAuthenticationStrategy Bean defined by
     *        SpringSecurityAthn17Config#sessionAuthenticationStrategy
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(193)
    public SecurityFilterChain filterChainThymeleaf1901004(HttpSecurity http,
            @Qualifier("loginWithMessageDigestDelegatingPassword") AuthenticationManager loginWithMessageDigestDelegatingPassword,
            CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy) throws Exception {

        http.securityMatcher(antMatcher("/thymeleaf/1901/004/**"));
        http.authenticationManager(loginWithMessageDigestDelegatingPassword);
        http.formLogin(login -> login.loginPage("/thymeleaf/1901/004")
                .loginProcessingUrl("/thymeleaf/1901/004/authenticate")
                .defaultSuccessUrl("/thymeleaf/1901/004/afterLogin"));
        http.sessionManagement(sessionManagement -> sessionManagement
                .sessionAuthenticationStrategy(sessionAuthenticationStrategy));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/1901/004/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }
}
