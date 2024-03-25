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
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecurityAthn05Config {

    /**
     * password.bcrypt.iteration property.
     */
    @Value("${password.bcrypt.iteration}")
    private int bcryptIteration;

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param loginWithBCryptPasswordAuthenticationProvider Bean defined by #loginWithBCryptPasswordAuthenticationProvider
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("loginWithBCryptPassword")
    public AuthenticationManager loginWithBCryptPassword(
            @Qualifier("loginWithBCryptPasswordAuthenticationProvider") AuthenticationProvider loginWithBCryptPasswordAuthenticationProvider) {
        return new ProviderManager(loginWithBCryptPasswordAuthenticationProvider);
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param loginWithEncodingPasswordService Bean defined by SpringSecurityAthnConfig#loginWithEncodingPasswordService
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("loginWithBCryptPasswordAuthenticationProvider")
    public AuthenticationProvider loginWithBCryptPasswordAuthenticationProvider(
            @Qualifier("loginWithEncodingPasswordService") UserDetailsService loginWithEncodingPasswordService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(loginWithEncodingPasswordService);
        authProvider.setPasswordEncoder(athnPasswordEncoderBCrypt());
        return authProvider;
    }

    /**
     * Configure {@link BCryptPasswordEncoder} bean.
     * @return Bean of configured {@link BCryptPasswordEncoder}
     */
    @Bean("athnPasswordEncoderBCrypt")
    public BCryptPasswordEncoder athnPasswordEncoderBCrypt() {
        return new BCryptPasswordEncoder(bcryptIteration);
    }

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param loginWithPbkdf2PasswordAuthenticationProvider Bean defined by #loginWithPbkdf2PasswordAuthenticationProvider
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("loginWithPbkdf2Password")
    public AuthenticationManager loginWithPbkdf2Password(
            @Qualifier("loginWithPbkdf2PasswordAuthenticationProvider") AuthenticationProvider loginWithPbkdf2PasswordAuthenticationProvider) {
        return new ProviderManager(loginWithPbkdf2PasswordAuthenticationProvider);
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param loginWithEncodingPasswordService Bean defined by SpringSecurityAthnConfig#loginWithEncodingPasswordService
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("loginWithPbkdf2PasswordAuthenticationProvider")
    public AuthenticationProvider loginWithPbkdf2PasswordAuthenticationProvider(
            @Qualifier("loginWithEncodingPasswordService") UserDetailsService loginWithEncodingPasswordService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(loginWithEncodingPasswordService);
        authProvider.setPasswordEncoder(athnPasswordEncoderPbkdf2());
        return authProvider;
    }

    /**
     * Configure {@link Pbkdf2PasswordEncoder} bean.
     * @return Bean of configured {@link Pbkdf2PasswordEncoder}
     */
    @Bean("athnPasswordEncoderPbkdf2")
    public Pbkdf2PasswordEncoder athnPasswordEncoderPbkdf2() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param loginWithSCryptPasswordAuthenticationProvider Bean defined by #loginWithSCryptPasswordAuthenticationProvider
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("loginWithSCryptPassword")
    public AuthenticationManager loginWithSCryptPassword(
            @Qualifier("loginWithSCryptPasswordAuthenticationProvider") AuthenticationProvider loginWithSCryptPasswordAuthenticationProvider) {
        return new ProviderManager(loginWithSCryptPasswordAuthenticationProvider);
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param loginWithEncodingPasswordService Bean defined by SpringSecurityAthnConfig#loginWithEncodingPasswordService
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("loginWithSCryptPasswordAuthenticationProvider")
    public AuthenticationProvider loginWithSCryptPasswordAuthenticationProvider(
            @Qualifier("loginWithEncodingPasswordService") UserDetailsService loginWithEncodingPasswordService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(loginWithEncodingPasswordService);
        authProvider.setPasswordEncoder(athnPasswordEncoderSCrypt());
        return authProvider;
    }

    /**
     * Configure {@link SCryptPasswordEncoder} bean.
     * @return Bean of configured {@link SCryptPasswordEncoder}
     */
    @Bean("athnPasswordEncoderSCrypt")
    public SCryptPasswordEncoder athnPasswordEncoderSCrypt() {
        return SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param loginWithArgon2PasswordAuthenticationProvider Bean defined by #loginWithArgon2PasswordAuthenticationProvider
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("loginWithArgon2Password")
    public AuthenticationManager loginWithArgon2Password(
            @Qualifier("loginWithArgon2PasswordAuthenticationProvider") AuthenticationProvider loginWithArgon2PasswordAuthenticationProvider) {
        return new ProviderManager(loginWithArgon2PasswordAuthenticationProvider);
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param loginWithEncodingPasswordService Bean defined by SpringSecurityAthnConfig#loginWithEncodingPasswordService
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("loginWithArgon2PasswordAuthenticationProvider")
    public AuthenticationProvider loginWithArgon2PasswordAuthenticationProvider(
            @Qualifier("loginWithEncodingPasswordService") UserDetailsService loginWithEncodingPasswordService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(loginWithEncodingPasswordService);
        authProvider.setPasswordEncoder(athnPasswordEncoderArgon2());
        return authProvider;
    }

    /**
     * Configure {@link Argon2PasswordEncoder} bean.
     * @return Bean of configured {@link Argon2PasswordEncoder}
     */
    @Bean("athnPasswordEncoderArgon2")
    public Argon2PasswordEncoder athnPasswordEncoderArgon2() {
        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param loginWithDelegatingPasswordAuthenticationProvider Bean defined by
     *            #loginWithDelegatingPasswordAuthenticationProvider
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("loginWithDelegatingPassword")
    public AuthenticationManager loginWithDelegatingPassword(
            @Qualifier("loginWithDelegatingPasswordAuthenticationProvider") AuthenticationProvider loginWithDelegatingPasswordAuthenticationProvider) {
        return new ProviderManager(loginWithDelegatingPasswordAuthenticationProvider);
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param loginWithEncodingPasswordService Bean defined by SpringSecurityAthnConfig#loginWithEncodingPasswordService
     * @param passwordEncoder Bean defined by ApplicationContextConfig#passwordEncoder
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("loginWithDelegatingPasswordAuthenticationProvider")
    public AuthenticationProvider loginWithDelegatingPasswordAuthenticationProvider(
            @Qualifier("loginWithEncodingPasswordService") UserDetailsService loginWithEncodingPasswordService,
            @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(loginWithEncodingPasswordService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginWithBCryptPassword Bean defined by #loginWithBCryptPassword
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(50)
    public SecurityFilterChain filterChainJsp0501002(HttpSecurity http,
            @Qualifier("loginWithBCryptPassword") AuthenticationManager loginWithBCryptPassword) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/0501/002/**"));
        http.authenticationManager(loginWithBCryptPassword);
        http.formLogin(login -> login.loginPage("/jsp/0501/002/login")
                .loginProcessingUrl("/jsp/0501/002/authenticate")
                .defaultSuccessUrl("/jsp/0501/002/afterLogin", true));
        http.logout(logout -> logout.logoutUrl("/jsp/0501/002/logout")
                .logoutSuccessUrl("/"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/jsp/0501/002/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/0501/002/logout/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/0501/002/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginWithBCryptPassword Bean defined by #loginWithBCryptPassword
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(51)
    public SecurityFilterChain filterChainJsp0501004(HttpSecurity http,
            @Qualifier("loginWithPbkdf2Password") AuthenticationManager loginWithPbkdf2Password) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/0501/004/**"));
        http.authenticationManager(loginWithPbkdf2Password);
        http.formLogin(login -> login.loginPage("/jsp/0501/004/login")
                .loginProcessingUrl("/jsp/0501/004/authenticate")
                .defaultSuccessUrl("/jsp/0501/004/afterLogin", true));
        http.logout(logout -> logout.logoutUrl("/jsp/0501/004/logout")
                .logoutSuccessUrl("/"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/jsp/0501/004/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/0501/004/logout/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/0501/004/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginWithSCryptPassword Bean defined by #loginWithSCryptPassword
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(52)
    public SecurityFilterChain filterChainJsp0501006(HttpSecurity http,
            @Qualifier("loginWithSCryptPassword") AuthenticationManager loginWithSCryptPassword) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/0501/006/**"));
        http.authenticationManager(loginWithSCryptPassword);
        http.formLogin(login -> login.loginPage("/jsp/0501/006/login")
                .loginProcessingUrl("/jsp/0501/006/authenticate")
                .defaultSuccessUrl("/jsp/0501/006/afterLogin", true));
        http.logout(logout -> logout.logoutUrl("/jsp/0501/006/logout")
                .logoutSuccessUrl("/"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/jsp/0501/006/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/0501/006/logout/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/0501/006/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginWithArgon2Password Bean defined by #loginWithArgon2Password
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(53)
    public SecurityFilterChain filterChainJsp0501008(HttpSecurity http,
            @Qualifier("loginWithArgon2Password") AuthenticationManager loginWithArgon2Password) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/0501/008/**"));
        http.authenticationManager(loginWithArgon2Password);
        http.formLogin(login -> login.loginPage("/jsp/0501/008/login")
                .loginProcessingUrl("/jsp/0501/008/authenticate")
                .defaultSuccessUrl("/jsp/0501/008/afterLogin", true));
        http.logout(logout -> logout.logoutUrl("/jsp/0501/008/logout")
                .logoutSuccessUrl("/"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/jsp/0501/008/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/0501/008/logout/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/0501/008/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginWithDelegatingPassword Bean defined by #loginWithDelegatingPassword
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(54)
    public SecurityFilterChain filterChainJsp0502(HttpSecurity http,
            @Qualifier("loginWithDelegatingPassword") AuthenticationManager loginWithDelegatingPassword) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/0502/002/**"));
        http.authenticationManager(loginWithDelegatingPassword);
        http.formLogin(login -> login.loginPage("/jsp/0502/002/login")
                .loginProcessingUrl("/jsp/0502/002/authenticate")
                .defaultSuccessUrl("/jsp/0502/002/afterLogin", true));
        http.logout(logout -> logout.logoutUrl("/jsp/0502/002/logout")
                .logoutSuccessUrl("/"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/jsp/0502/002/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/0502/002/logout/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/0502/002/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginWithBCryptPassword Bean defined by #loginWithBCryptPassword
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(55)
    public SecurityFilterChain filterChainThymeleaf0501002(HttpSecurity http,
            @Qualifier("loginWithBCryptPassword") AuthenticationManager loginWithBCryptPassword) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0501/002/**"));
        http.authenticationManager(loginWithBCryptPassword);
        http.formLogin(login -> login.loginPage("/thymeleaf/0501/002/login")
                .loginProcessingUrl("/thymeleaf/0501/002/authenticate")
                .defaultSuccessUrl("/thymeleaf/0501/002/afterLogin", true));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0501/002/logout")
                .logoutSuccessUrl("/"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0501/002/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0501/002/logout/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0501/002/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginWithPbkdf2Password Bean defined by #loginWithPbkdf2Password
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(56)
    public SecurityFilterChain filterChainThymeleaf0501004(HttpSecurity http,
            @Qualifier("loginWithPbkdf2Password") AuthenticationManager loginWithPbkdf2Password) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0501/004/**"));
        http.authenticationManager(loginWithPbkdf2Password);
        http.formLogin(login -> login.loginPage("/thymeleaf/0501/004/login")
                .loginProcessingUrl("/thymeleaf/0501/004/authenticate")
                .defaultSuccessUrl("/thymeleaf/0501/004/afterLogin", true));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0501/004/logout")
                .logoutSuccessUrl("/"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0501/004/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0501/004/logout/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0501/004/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginWithSCryptPassword Bean defined by #loginWithSCryptPassword
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(57)
    public SecurityFilterChain filterChainThymeleaf0501006(HttpSecurity http,
            @Qualifier("loginWithSCryptPassword") AuthenticationManager loginWithSCryptPassword) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0501/006/**"));
        http.authenticationManager(loginWithSCryptPassword);
        http.formLogin(login -> login.loginPage("/thymeleaf/0501/006/login")
                .loginProcessingUrl("/thymeleaf/0501/006/authenticate")
                .defaultSuccessUrl("/thymeleaf/0501/006/afterLogin", true));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0501/006/logout")
                .logoutSuccessUrl("/"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0501/006/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0501/006/logout/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0501/006/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginWithArgon2Password Bean defined by #loginWithArgon2Password
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(58)
    public SecurityFilterChain filterChainThymeleaf0501008(HttpSecurity http,
            @Qualifier("loginWithArgon2Password") AuthenticationManager loginWithArgon2Password) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0501/008/**"));
        http.authenticationManager(loginWithArgon2Password);
        http.formLogin(login -> login.loginPage("/thymeleaf/0501/008/login")
                .loginProcessingUrl("/thymeleaf/0501/008/authenticate")
                .defaultSuccessUrl("/thymeleaf/0501/008/afterLogin", true));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0501/008/logout")
                .logoutSuccessUrl("/"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0501/008/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0501/008/logout/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0501/008/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginWithDelegatingPassword Bean defined by #loginWithDelegatingPassword
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(59)
    public SecurityFilterChain filterChainThymeleaf0502002(HttpSecurity http,
            @Qualifier("loginWithDelegatingPassword") AuthenticationManager loginWithDelegatingPassword) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0502/002/**"));
        http.authenticationManager(loginWithDelegatingPassword);
        http.formLogin(login -> login.loginPage("/thymeleaf/0502/002/login")
                .loginProcessingUrl("/thymeleaf/0502/002/authenticate")
                .defaultSuccessUrl("/thymeleaf/0502/002/afterLogin", true));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0502/002/logout")
                .logoutSuccessUrl("/"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0502/002/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0502/002/logout/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0502/002/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }
}
