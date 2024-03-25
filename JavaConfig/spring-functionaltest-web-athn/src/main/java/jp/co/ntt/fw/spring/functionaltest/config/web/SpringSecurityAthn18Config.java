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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecurityAthn18Config {

    /**
     * password.pbkdf2.iteration property.
     */
    @Value("${password.pbkdf2.iteration}")
    private int pbkdf2Iteration;

    /**
     * password.pbkdf2.secretKeyFactoryAlgorithm property.
     */
    @Value("${password.pbkdf2.secretKeyFactoryAlgorithm}")
    private SecretKeyFactoryAlgorithm secretKeyFactoryAlgorithm;

    /**
     * enabledValue property.
     */
    @Value("${enabledValue}")
    private boolean enabledValue;

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param loginWithCustomPbkdf2PasswordAuthenticationProvider Bean defined by
     *            #loginWithCustomPbkdf2PasswordAuthenticationProvider
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("loginWithCustomPbkdf2Password")
    public AuthenticationManager loginWithCustomPbkdf2Password(
            @Qualifier("loginWithCustomPbkdf2PasswordAuthenticationProvider") AuthenticationProvider loginWithCustomPbkdf2PasswordAuthenticationProvider) {
        return new ProviderManager(loginWithCustomPbkdf2PasswordAuthenticationProvider);
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param loginWithEncodingPasswordService Bean defined by #loginWithEncodingPasswordService
     * @param athnPasswordEncoderCustomPbkdf2 Bean defined by #athnPasswordEncoderCustomPbkdf2
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("loginWithCustomPbkdf2PasswordAuthenticationProvider")
    public AuthenticationProvider loginWithCustomPbkdf2PasswordAuthenticationProvider(
            @Qualifier("loginWithEncodingPasswordService") UserDetailsService loginWithEncodingPasswordService,
            @Qualifier("athnPasswordEncoderCustomPbkdf2") PasswordEncoder athnPasswordEncoderCustomPbkdf2) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(loginWithEncodingPasswordService);
        authProvider.setPasswordEncoder(athnPasswordEncoderCustomPbkdf2);
        return authProvider;
    }

    /**
     * Configure {@link PasswordEncoder} bean.
     * @param athnPasswordEncoderBCrypt PasswordEncoder defined by SpringSecurityAthnConfig#athnPasswordEncoderBCrypt()
     * @return Bean of configured {@link DelegatingPasswordEncoder}
     */
    @Bean("athnPasswordEncoderCustomPbkdf2")
    public PasswordEncoder athnPasswordEncoderCustomPbkdf2(
            @Qualifier("athnPasswordEncoderBCrypt") BCryptPasswordEncoder athnPasswordEncoderBCrypt) {
        Map<String, PasswordEncoder> idToPasswordEncoder = new HashMap<>();
        idToPasswordEncoder.put("pbkdf2", pbkdf2PasswordCustomEncoder());
        DelegatingPasswordEncoder bean = new DelegatingPasswordEncoder("pbkdf2", idToPasswordEncoder);
        bean.setDefaultPasswordEncoderForMatches(athnPasswordEncoderBCrypt);
        return bean;
    }

    /**
     * Configure {@link Pbkdf2PasswordEncoder} bean.
     * @return Bean of configured {@link Pbkdf2PasswordEncoder}
     */
    @Bean
    public Pbkdf2PasswordEncoder pbkdf2PasswordCustomEncoder() {
        return new Pbkdf2PasswordEncoder("", 16, pbkdf2Iteration, secretKeyFactoryAlgorithm);
    }

    /**
     * Configure {@link UserDetailsService} bean.
     * @param dataSource DataSource defined by SpringFunctionaltestEnvConfig#dataSource()
     * @return Bean of configured {@link UserDetailsService}
     */
    @Bean("loginWithEncodingPasswordService")
    public UserDetailsService loginWithEncodingPasswordService(
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
     * @param loginWithCustomPbkdf2Password Bean defined by #loginWithCustomPbkdf2Password
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(180)
    public SecurityFilterChain filterChainJsp1801002(HttpSecurity http,
            @Qualifier("loginWithCustomPbkdf2Password") AuthenticationManager loginWithCustomPbkdf2Password) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/1801/002/**"));
        http.authenticationManager(loginWithCustomPbkdf2Password);
        http.formLogin(login -> login.loginPage("/jsp/1801/002/login")
                .loginProcessingUrl("/jsp/1801/002/authenticate")
                .defaultSuccessUrl("/jsp/1801/002/afterLogin", true));
        http.logout(logout -> logout.logoutUrl("/jsp/1801/002/logout")
                .logoutSuccessUrl("/"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/jsp/1801/002/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/1801/002/logout/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/1801/002/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginWithCustomPbkdf2Password Bean defined by #loginWithCustomPbkdf2Password
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(181)
    public SecurityFilterChain filterChainThymeleaf1801002(HttpSecurity http,
            @Qualifier("loginWithCustomPbkdf2Password") AuthenticationManager loginWithCustomPbkdf2Password) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/1801/002/**"));
        http.authenticationManager(loginWithCustomPbkdf2Password);
        http.formLogin(login -> login.loginPage("/thymeleaf/1801/002/login")
                .loginProcessingUrl("/thymeleaf/1801/002/authenticate")
                .defaultSuccessUrl("/thymeleaf/1801/002/afterLogin", true));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/1801/002/logout")
                .logoutSuccessUrl("/"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/1801/002/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/1801/002/logout/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/1801/002/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }
}
