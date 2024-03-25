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
import java.util.List;

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
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.csrf.CsrfAuthenticationStrategy;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jp.co.ntt.fw.spring.functionaltest.app.athn.CompanyIdUsernamePasswordAuthenticationFilter;
import jp.co.ntt.fw.spring.functionaltest.domain.service.athn.CompanyIdUsernamePasswordAuthenticationProvider;
import jp.co.ntt.fw.spring.functionaltest.domain.service.athn.ReservationUserDetailsService;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecurityAthn17Config {

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param customerPasswordAuthenticationProvider Bean defined by #customerPasswordAuthenticationProvider
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("customerAuthenticationManager")
    public AuthenticationManager customerAuthenticationManager(
            @Qualifier("customerPasswordAuthenticationProvider") AuthenticationProvider customerPasswordAuthenticationProvider) {
        return new ProviderManager(customerPasswordAuthenticationProvider);
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param passwordEncoder Bean defined by ApplicationContextConfig#passwordEncoder
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("customerPasswordAuthenticationProvider")
    public AuthenticationProvider customerPasswordAuthenticationProvider(
            @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setHideUserNotFoundExceptions(true);
        authProvider.setPasswordEncoder(passwordEncoder);
        authProvider.setUserDetailsService(customerService());
        return authProvider;
    }

    /**
     * Configure {@link ReservationUserDetailsService} bean.
     * @return Bean of configured {@link ReservationUserDetailsService}
     */
    @Bean("customerService")
    public ReservationUserDetailsService customerService() {
        return new ReservationUserDetailsService();
    }

    /**
     * Configure {@link LoginUrlAuthenticationEntryPoint} bean.
     * @return Bean of configured {@link LoginUrlAuthenticationEntryPoint}
     */
    @Bean("jspCustomerLoginUrlAuthenticationEntryPoint")
    public LoginUrlAuthenticationEntryPoint jspCustomerLoginUrlAuthenticationEntryPoint() {
        return new LoginUrlAuthenticationEntryPoint("/jsp/1701/001");
    }

    /**
     * Configure {@link LoginUrlAuthenticationEntryPoint} bean.
     * @return Bean of configured {@link LoginUrlAuthenticationEntryPoint}
     */
    @Bean("thymeleafCustomerLoginUrlAuthenticationEntryPoint")
    public LoginUrlAuthenticationEntryPoint thymeleafCustomerLoginUrlAuthenticationEntryPoint() {
        return new LoginUrlAuthenticationEntryPoint("/thymeleaf/1701/001");
    }

    /**
     * Configure {@link LoginUrlAuthenticationEntryPoint} bean.
     * @return Bean of configured {@link LoginUrlAuthenticationEntryPoint}
     */
    @Bean("jspLoginUrlAuthenticationEntryPoint")
    public LoginUrlAuthenticationEntryPoint jspLoginUrlAuthenticationEntryPoint() {
        return new LoginUrlAuthenticationEntryPoint("/jsp/1702/001");
    }

    /**
     * Configure {@link LoginUrlAuthenticationEntryPoint} bean.
     * @return Bean of configured {@link LoginUrlAuthenticationEntryPoint}
     */
    @Bean("thymeleafLoginUrlAuthenticationEntryPoint")
    public LoginUrlAuthenticationEntryPoint thymeleafLoginUrlAuthenticationEntryPoint() {
        return new LoginUrlAuthenticationEntryPoint("/thymeleaf/1702/001");
    }

    /**
     * Configure {@link CompanyIdUsernamePasswordAuthenticationFilter} bean.
     * @param authenticationManager Bean defined by #authenticationManager
     * @return Bean of configured {@link CompanyIdUsernamePasswordAuthenticationFilter}
     */
    @Bean("jspCompanyIdUsernamePasswordAuthenticationFilter")
    public CompanyIdUsernamePasswordAuthenticationFilter jspCompanyIdUsernamePasswordAuthenticationFilter(
            @Qualifier("authenticationManager") AuthenticationManager authenticationManager) {

        CompanyIdUsernamePasswordAuthenticationFilter bean = new CompanyIdUsernamePasswordAuthenticationFilter();
        bean.setRequiresAuthenticationRequestMatcher(
                jspAntPathRequestMatcher());
        bean.setAuthenticationManager(authenticationManager);
        bean.setSessionAuthenticationStrategy(sessionAuthenticationStrategy());
        bean.setSecurityContextRepository(securityContextRepository());
        bean.setAuthenticationFailureHandler(
                jspSimpleUrlAuthenticationFailureHandler());
        bean.setAuthenticationSuccessHandler(
                jspSimpleUrlAuthenticationSuccessHandler());
        return bean;
    }

    /**
     * Configure {@link AntPathRequestMatcher} bean.
     * @return Bean of configured {@link AntPathRequestMatcher}
     */
    @Bean("jspAntPathRequestMatcher")
    public AntPathRequestMatcher jspAntPathRequestMatcher() {
        return new AntPathRequestMatcher("/jsp/1702/customerAuthenticate", "POST");
    }

    /**
     * Configure {@link SimpleUrlAuthenticationFailureHandler} bean.
     * @return Bean of configured {@link SimpleUrlAuthenticationFailureHandler}
     */
    @Bean("jspSimpleUrlAuthenticationFailureHandler")
    public SimpleUrlAuthenticationFailureHandler jspSimpleUrlAuthenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/jsp/1702/001?error");
    }

    /**
     * Configure {@link SimpleUrlAuthenticationSuccessHandler} bean.
     * @return Bean of configured {@link SimpleUrlAuthenticationSuccessHandler}
     */
    @Bean("jspSimpleUrlAuthenticationSuccessHandler")
    public SimpleUrlAuthenticationSuccessHandler jspSimpleUrlAuthenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler("/jsp/1702/001?loginSuccess");
    }

    /**
     * Configure {@link CompanyIdUsernamePasswordAuthenticationFilter} bean.
     * @param authenticationManager Bean defined by #authenticationManager
     * @return Bean of configured {@link CompanyIdUsernamePasswordAuthenticationFilter}
     */
    @Bean("thymeleafCompanyIdUsernamePasswordAuthenticationFilter")
    public CompanyIdUsernamePasswordAuthenticationFilter thymeleafCompanyIdUsernamePasswordAuthenticationFilter(
            @Qualifier("authenticationManager") AuthenticationManager authenticationManager) {

        CompanyIdUsernamePasswordAuthenticationFilter bean = new CompanyIdUsernamePasswordAuthenticationFilter();
        bean.setRequiresAuthenticationRequestMatcher(
                thymeleafAntPathRequestMatcher());
        bean.setAuthenticationManager(authenticationManager);
        bean.setSessionAuthenticationStrategy(sessionAuthenticationStrategy());
        bean.setSecurityContextRepository(securityContextRepository());
        bean.setAuthenticationFailureHandler(
                thymeleafSimpleUrlAuthenticationFailureHandler());
        bean.setAuthenticationSuccessHandler(
                thymeleafSimpleUrlAuthenticationSuccessHandler());
        return bean;
    }

    /**
     * Configure {@link AntPathRequestMatcher} bean.
     * @return Bean of configured {@link AntPathRequestMatcher}
     */
    @Bean("thymeleafAntPathRequestMatcher")
    public AntPathRequestMatcher thymeleafAntPathRequestMatcher() {
        return new AntPathRequestMatcher("/thymeleaf/1702/customerAuthenticate", "POST");
    }

    /**
     * Configure {@link SimpleUrlAuthenticationFailureHandler} bean.
     * @return Bean of configured {@link SimpleUrlAuthenticationFailureHandler}
     */
    @Bean("thymeleafSimpleUrlAuthenticationFailureHandler")
    public SimpleUrlAuthenticationFailureHandler thymeleafSimpleUrlAuthenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/thymeleaf/1702/001?error");
    }

    /**
     * Configure {@link SimpleUrlAuthenticationSuccessHandler} bean.
     * @return Bean of configured {@link SimpleUrlAuthenticationSuccessHandler}
     */
    @Bean("thymeleafSimpleUrlAuthenticationSuccessHandler")
    public SimpleUrlAuthenticationSuccessHandler thymeleafSimpleUrlAuthenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler("/thymeleaf/1702/001?loginSuccess");
    }

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param companyIdUsernamePasswordAuthenticationProvider Bean defined by #companyIdUsernamePasswordAuthenticationProvider
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("authenticationManager")
    public AuthenticationManager authenticationManager(
            @Qualifier("companyIdUsernamePasswordAuthenticationProvider") AuthenticationProvider companyIdUsernamePasswordAuthenticationProvider) {
        return new ProviderManager(companyIdUsernamePasswordAuthenticationProvider);
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param passwordEncoder Bean defined by ApplicationContextConfig#passwordEncoder
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("companyIdUsernamePasswordAuthenticationProvider")
    public AuthenticationProvider companyIdUsernamePasswordAuthenticationProvider(
            @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder) {
        CompanyIdUsernamePasswordAuthenticationProvider authProvider = new CompanyIdUsernamePasswordAuthenticationProvider();
        authProvider.setUserDetailsService(customerService());
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    /**
     * Configure {@link HttpSessionSecurityContextRepository} bean.
     * @return Bean of configured {@link HttpSessionSecurityContextRepository}
     */
    @Bean("securityContextRepository")
    public HttpSessionSecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }

    /**
     * Configure {@link CompositeSessionAuthenticationStrategy} bean.
     * @return Bean of configured {@link CompositeSessionAuthenticationStrategy}
     */
    @Bean("sessionAuthenticationStrategy")
    public CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy() {
        List<SessionAuthenticationStrategy> list = new ArrayList<SessionAuthenticationStrategy>();
        list.add(csrfAuthenticationStrategy());
        list.add(sessionFixationProtectionStrategy());
        CompositeSessionAuthenticationStrategy bean = new CompositeSessionAuthenticationStrategy(list);
        return bean;
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
     * Configure {@link SessionFixationProtectionStrategy} bean.
     * @return Bean of configured {@link SessionFixationProtectionStrategy}
     */
    @Bean
    public SessionFixationProtectionStrategy sessionFixationProtectionStrategy() {
        return new SessionFixationProtectionStrategy();
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
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param customerAuthenticationManager Bean defined by #customerAuthenticationManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(170)
    public SecurityFilterChain filterChainJsp1701(HttpSecurity http,
            @Qualifier("customerAuthenticationManager") AuthenticationManager customerAuthenticationManager) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/1701/**"));
        http.exceptionHandling(exception -> exception
                .authenticationEntryPoint(
                        jspCustomerLoginUrlAuthenticationEntryPoint()));
        http.authenticationManager(customerAuthenticationManager);
        http.formLogin(login -> login.loginProcessingUrl("/jsp/1701/customerAuthenticate")
                .defaultSuccessUrl("/jsp/1701/001?loginSuccess", true));
        http.sessionManagement(sessionManagement -> sessionManagement
                .sessionAuthenticationStrategy(
                        sessionAuthenticationStrategy()));
        http.logout(logout -> logout.logoutUrl("/jsp/1701/logout")
                .logoutSuccessUrl("/athn")
                .deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param jspCompanyIdUsernamePasswordAuthenticationFilter Bean defined by #jspCompanyIdUsernamePasswordAuthenticationFilter
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(171)
    public SecurityFilterChain filterChainJsp1702(HttpSecurity http,
            @Qualifier("jspCompanyIdUsernamePasswordAuthenticationFilter") CompanyIdUsernamePasswordAuthenticationFilter jspCompanyIdUsernamePasswordAuthenticationFilter) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/1702/**"));
        http.exceptionHandling(exception -> exception
                .authenticationEntryPoint(
                        jspLoginUrlAuthenticationEntryPoint()));
        http.addFilterAt(jspCompanyIdUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.csrf(csrf -> csrf.csrfTokenRepository(csrfTokenRepository()));
        http.logout(logout -> logout.logoutUrl("/jsp/1702/logout")
                .logoutSuccessUrl("/athn")
                .deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManagerForAthn Bean defined by SpringSecurityAthnConfig#userLoginManagerForAthn
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(172)
    public SecurityFilterChain filterChainJsp1703(HttpSecurity http,
            @Qualifier("userLoginManagerForAthn") AuthenticationManager userLoginManagerForAthn) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/1703/**"));
        http.authenticationManager(userLoginManagerForAthn);
        http.formLogin(login -> login.loginPage("/jsp/1703/001")
                .loginProcessingUrl("/jsp/1703/001/authenticate")
                .defaultSuccessUrl("/jsp/1703/001?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/jsp/1703/001/logout")
                .logoutSuccessUrl("/jsp/1703/001"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/jsp/1703/001/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/1703/001/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/1703/001/logout/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/1703/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param customerAuthenticationManager Bean defined by #customerAuthenticationManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(173)
    public SecurityFilterChain filterChainThymeleaf1701(HttpSecurity http,
            @Qualifier("customerAuthenticationManager") AuthenticationManager customerAuthenticationManager) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/1701/**"));
        http.exceptionHandling(exception -> exception
                .authenticationEntryPoint(
                        thymeleafCustomerLoginUrlAuthenticationEntryPoint()));
        http.authenticationManager(customerAuthenticationManager);
        http.formLogin(login -> login.loginProcessingUrl("/thymeleaf/1701/customerAuthenticate")
                .defaultSuccessUrl("/thymeleaf/1701/001?loginSuccess", true));
        http.sessionManagement(sessionManagement -> sessionManagement
                .sessionAuthenticationStrategy(
                        sessionAuthenticationStrategy()));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/1701/logout")
                .logoutSuccessUrl("/athn")
                .deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param thymeleafCompanyIdUsernamePasswordAuthenticationFilter Bean defined by
     *            #thymeleafCompanyIdUsernamePasswordAuthenticationFilter
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(174)
    public SecurityFilterChain filterChainThymeleaf1702(HttpSecurity http,
            @Qualifier("thymeleafCompanyIdUsernamePasswordAuthenticationFilter") CompanyIdUsernamePasswordAuthenticationFilter thymeleafCompanyIdUsernamePasswordAuthenticationFilter) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/1702/**"));
        http.exceptionHandling(exception -> exception
                .authenticationEntryPoint(
                        thymeleafLoginUrlAuthenticationEntryPoint()));
        http.addFilterAt(thymeleafCompanyIdUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.csrf(csrf -> csrf.csrfTokenRepository(csrfTokenRepository()));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/1702/logout")
                .logoutSuccessUrl("/athn")
                .deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManagerForAthn Bean defined by SpringSecurityAthnConfig#userLoginManagerForAthn
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(175)
    public SecurityFilterChain filterChainThymeleaf1703(HttpSecurity http,
            @Qualifier("userLoginManagerForAthn") AuthenticationManager userLoginManagerForAthn) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/1703/**"));
        http.authenticationManager(userLoginManagerForAthn);
        http.formLogin(login -> login.loginPage("/thymeleaf/1703/001")
                .loginProcessingUrl("/thymeleaf/1703/001/authenticate")
                .defaultSuccessUrl("/thymeleaf/1703/001?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/1703/001/logout")
                .logoutSuccessUrl("/thymeleaf/1703/001"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/1703/001/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/1703/001/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/1703/001/logout/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/1703/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }
}
