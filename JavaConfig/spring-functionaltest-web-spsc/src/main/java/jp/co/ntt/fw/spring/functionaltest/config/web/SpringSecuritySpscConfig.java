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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.CacheControlHeadersWriter;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive;
import org.springframework.security.web.header.writers.DelegatingRequestMatcherHeaderWriter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
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

        AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher("/jsp/0401/001/secure/**");
        CacheControlHeadersWriter cacheControlHeadersWriter = new CacheControlHeadersWriter();

        return new DelegatingRequestMatcherHeaderWriter(antPathRequestMatcher, cacheControlHeadersWriter);
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(1)
    public SecurityFilterChain filterChainJsp0101001(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/jsp/0101/001"));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/login")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**"))
                .authenticated());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(2)
    public SecurityFilterChain filterChainJsp0201001(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/jsp/0201/001"));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(3)
    public SecurityFilterChain filterChainJsp0201002(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/jsp/0201/002"));
        http.headers(headers -> headers.disable());
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(4)
    public SecurityFilterChain filterChainJsp0201003(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/jsp/0201/003"));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        // java.lang.IllegalStateException: Headers security is enabled, but no headers will be added. Either add headers or
        // disable headers security
        // http.headers(headers -> headers.defaultsDisabled());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(5)
    public SecurityFilterChain filterChainJsp0201004(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/jsp/0201/004"));
        // @formatter:off
        http.headers(headers -> headers.defaultsDisabled()
                .cacheControl(Customizer.withDefaults())
                .frameOptions(Customizer.withDefaults())
                .contentTypeOptions(Customizer.withDefaults())
                .httpStrictTransportSecurity(Customizer.withDefaults())
                .contentSecurityPolicy(csp -> csp.policyDirectives("default-src 'self'"))
                .referrerPolicy(Customizer.withDefaults())
                .permissionsPolicy(permissions -> permissions.policy("geolocation=(self)"))
        );
        // @formatter:on
        http.formLogin(Customizer.withDefaults());
        http.logout(logout -> logout.addLogoutHandler(
                new HeaderWriterLogoutHandler(clearSiteDataHeaderWriter())));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(6)
    public SecurityFilterChain filterChainJsp0201005(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/jsp/0201/005"));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(7)
    public SecurityFilterChain filterChainJsp0201006(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/jsp/0201/006"));
        http.headers(headers -> headers.frameOptions(
                frameOptions -> frameOptions.sameOrigin()));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(8)
    public SecurityFilterChain filterChainJsp0201007(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/jsp/0201/007"));
        http.headers(headers -> headers.cacheControl(
                cacheControl -> cacheControl.disable()));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(9)
    public SecurityFilterChain filterChainJsp0301001(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/jsp/0301/001"));
        http.headers(headers -> headers.addHeaderWriter(
                new StaticHeadersWriter("X-WebKit-CSP", "default-src 'self'")));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(10)
    public SecurityFilterChain filterChainJsp0401001(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/jsp/0401/001"));
        http.headers(headers -> headers.defaultsDisabled().addHeaderWriter(
                jspSecureCacheControlHeadersWriter()));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(11)
    public SecurityFilterChain filterChainJsp0501001(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/jsp/0501/001"));
        http.headers(headers -> headers.contentSecurityPolicy(csp -> csp
                .policyDirectives("default-src 'self'")));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(12)
    public SecurityFilterChain filterChainJsp0501002(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/jsp/0501/002"));
        http.headers(headers -> headers.contentSecurityPolicy(csp -> csp
                .policyDirectives("default-src 'self'; report-uri /csp_report;")
                .reportOnly()));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(13)
    public SecurityFilterChain filterChainJsp0501003(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/jsp/0501/003"));
        http.headers(headers -> headers.contentSecurityPolicy(csp -> csp
                .policyDirectives(
                        "default-src 'self'; report-uri /csp_report;")));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(14)
    public SecurityFilterChain filterChainJsp0501004(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/jsp/0501/004"));
        http.headers(headers -> headers.contentSecurityPolicy(csp -> csp
                .policyDirectives(
                        "upgrade-insecure-requests; default-src 'self';")));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(15)
    public SecurityFilterChain filterChainJsp0501005(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/jsp/0501/005"));
        http.headers(headers -> headers.contentSecurityPolicy(csp -> csp
                .policyDirectives("upgrade-insecure-requests;")));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link DelegatingRequestMatcherHeaderWriter} bean.
     * @return Bean of configured {@link DelegatingRequestMatcherHeaderWriter}
     */
    @Bean("thymeleafSecureCacheControlHeadersWriter")
    public DelegatingRequestMatcherHeaderWriter thymeleafSecureCacheControlHeadersWriter() {

        AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher("/thymeleaf/0401/001/secure/**");
        CacheControlHeadersWriter cacheControlHeadersWriter = new CacheControlHeadersWriter();

        return new DelegatingRequestMatcherHeaderWriter(antPathRequestMatcher, cacheControlHeadersWriter);
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(18)
    public SecurityFilterChain filterChainThymeleaf0101001(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0101/001"));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/login")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**"))
                .authenticated());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(19)
    public SecurityFilterChain filterChainThymeleaf0201001(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0201/001"));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(20)
    public SecurityFilterChain filterChainThymeleaf0201002(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0201/002"));
        http.headers(headers -> headers.disable());
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(21)
    public SecurityFilterChain filterChainThymeleaf0201003(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0201/003"));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        // java.lang.IllegalStateException: Headers security is enabled, but no headers will be added. Either add headers or
        // disable headers security
        // http.headers(headers -> headers.defaultsDisabled());

        return http.build();
    }


    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(22)
    public SecurityFilterChain filterChainThymeleaf0201004(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0201/004"));
        // @formatter:off
        http.headers(headers -> headers.defaultsDisabled()
                .cacheControl(Customizer.withDefaults())
                .frameOptions(Customizer.withDefaults())
                .contentTypeOptions(Customizer.withDefaults())
                .httpStrictTransportSecurity(Customizer.withDefaults())
                .contentSecurityPolicy(csp -> csp.policyDirectives("default-src 'self'"))
                .referrerPolicy(Customizer.withDefaults())
                .permissionsPolicy(permissions -> permissions.policy("geolocation=(self)"))
        );
        // @formatter:on
        http.formLogin(Customizer.withDefaults());
        http.logout(logout -> logout.addLogoutHandler(
                new HeaderWriterLogoutHandler(clearSiteDataHeaderWriter())));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(23)
    public SecurityFilterChain filterChainThymeleaf0201005(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0201/005"));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(24)
    public SecurityFilterChain filterChainThymeleaf0201006(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0201/006"));
        http.headers(headers -> headers.frameOptions(
                frameOptions -> frameOptions.sameOrigin()));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(25)
    public SecurityFilterChain filterChainThymeleaf0201007(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0201/007"));
        http.headers(headers -> headers.cacheControl(
                cacheControl -> cacheControl.disable()));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(26)
    public SecurityFilterChain filterChainThymeleaf0301001(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0301/001"));
        http.headers(headers -> headers.addHeaderWriter(
                new StaticHeadersWriter("X-WebKit-CSP", "default-src 'self'")));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(27)
    public SecurityFilterChain filterChainThymeleaf0401001(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0401/001"));
        http.headers(headers -> headers.defaultsDisabled().addHeaderWriter(
                thymeleafSecureCacheControlHeadersWriter()));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(28)
    public SecurityFilterChain filterChainThymeleaf0501001(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0501/001"));
        http.headers(headers -> headers.contentSecurityPolicy(csp -> csp
                .policyDirectives("default-src 'self'")));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(29)
    public SecurityFilterChain filterChainThymeleaf0501002(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0501/002"));
        http.headers(headers -> headers.contentSecurityPolicy(csp -> csp
                .policyDirectives("default-src 'self'; report-uri /csp_report;")
                .reportOnly()));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(30)
    public SecurityFilterChain filterChainThymeleaf0501003(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0501/003"));
        http.headers(headers -> headers.contentSecurityPolicy(csp -> csp
                .policyDirectives(
                        "default-src 'self'; report-uri /csp_report;")));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(31)
    public SecurityFilterChain filterChainThymeleaf0501004(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0501/004"));
        http.headers(headers -> headers.contentSecurityPolicy(csp -> csp
                .policyDirectives(
                        "upgrade-insecure-requests; default-src 'self';")));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(32)
    public SecurityFilterChain filterChainThymeleaf0501005(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0501/005"));
        http.headers(headers -> headers.contentSecurityPolicy(csp -> csp
                .policyDirectives("upgrade-insecure-requests;")));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    @Bean
    public ClearSiteDataHeaderWriter clearSiteDataHeaderWriter() {
        return new ClearSiteDataHeaderWriter(Directive.CACHE);
    }
}
