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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.CacheControlHeadersWriter;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive;
import org.springframework.security.web.header.writers.DelegatingRequestMatcherHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecuritySpscConfig {

    /**
     * Configure {@link DelegatingRequestMatcherHeaderWriter} bean.
     * @return Bean of configured {@link DelegatingRequestMatcherHeaderWriter}
     */
    @Bean("jspSecureCacheControlHeadersWriter")
    public DelegatingRequestMatcherHeaderWriter jspSecureCacheControlHeadersWriter() {

        AntPathRequestMatcher antPathRequestMatcher = antMatcher("/jsp/0401/001/secure/**");
        CacheControlHeadersWriter cacheControlHeadersWriter = new CacheControlHeadersWriter();

        return new DelegatingRequestMatcherHeaderWriter(antPathRequestMatcher,
                cacheControlHeadersWriter);
    }

    /**
     * Configure {@link DelegatingRequestMatcherHeaderWriter} bean.
     * @return Bean of configured {@link DelegatingRequestMatcherHeaderWriter}
     */
    @Bean("thymeleafSecureCacheControlHeadersWriter")
    public DelegatingRequestMatcherHeaderWriter thymeleafSecureCacheControlHeadersWriter() {

        AntPathRequestMatcher antPathRequestMatcher = antMatcher("/thymeleaf/0401/001/secure/**");
        CacheControlHeadersWriter cacheControlHeadersWriter = new CacheControlHeadersWriter();

        return new DelegatingRequestMatcherHeaderWriter(antPathRequestMatcher,
                cacheControlHeadersWriter);
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(40)
    public SecurityFilterChain filterChainJsp0701001(HttpSecurity http) throws Exception {
        http.securityMatcher(antMatcher("/jsp/0701/001"));
        http.headers(headers -> headers.httpStrictTransportSecurity(
                hsts -> hsts.maxAgeInSeconds(20).includeSubDomains(true).preload(true)));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/**")).permitAll());
        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(41)
    public SecurityFilterChain filterChainJsp0701002(HttpSecurity http) throws Exception {
        http.securityMatcher(antMatcher("/jsp/0701/002"));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/**")).permitAll());
        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(42)
    public SecurityFilterChain filterChainJsp0702001(HttpSecurity http) throws Exception {
        http.securityMatcher(antMatcher("/jsp/0702/001"));
        http.headers(headers -> headers.httpStrictTransportSecurity(
                hsts -> hsts.maxAgeInSeconds(20).includeSubDomains(false).preload(false)));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/**")).permitAll());
        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(43)
    public SecurityFilterChain filterChainThymeleaf0701001(HttpSecurity http) throws Exception {
        http.securityMatcher(antMatcher("/thymeleaf/0701/001"));
        http.headers(headers -> headers.httpStrictTransportSecurity(
                hsts -> hsts.maxAgeInSeconds(20).includeSubDomains(true).preload(true)));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/**")).permitAll());
        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(44)
    public SecurityFilterChain filterChainThymeleaf0701002(HttpSecurity http) throws Exception {
        http.securityMatcher(antMatcher("/thymeleaf/0701/002"));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/**")).permitAll());
        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(45)
    public SecurityFilterChain filterChainThymeleaf0702001(HttpSecurity http) throws Exception {
        http.securityMatcher(antMatcher("/thymeleaf/0702/001"));
        http.headers(headers -> headers.httpStrictTransportSecurity(
                hsts -> hsts.maxAgeInSeconds(20).includeSubDomains(false).preload(false)));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/**")).permitAll());
        return http.build();
    }

    @Bean
    public ClearSiteDataHeaderWriter clearSiteDataHeaderWriter() {
        return new ClearSiteDataHeaderWriter(Directive.CACHE);
    }
}
