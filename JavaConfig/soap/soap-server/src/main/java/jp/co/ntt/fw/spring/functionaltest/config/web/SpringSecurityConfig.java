/*
 * Copyright(c) 2025 NTT Corporation.
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {

    /**
     * Configure {@link PasswordEncoder} bean.
     * @return Bean of configured {@link PasswordEncoder}
     */
    @SuppressWarnings("deprecation")
    @Bean("noOpPasswordEncoder")
    public PasswordEncoder noOpPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.securityMatcher(antMatcher("/ws/**"));
        http.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authenticationManager(soapAuthenticationManager());
        http.csrf(carf -> carf.disable());
        http.httpBasic(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/**")).permitAll());
        return http.build();
    }

    /**
     * Configure {@link AuthenticationManager} bean.
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("soapAuthenticationManager")
    public AuthenticationManager soapAuthenticationManager() {
        return new ProviderManager(authenticationProvider());
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(soapUserDetailsService());
        authProvider.setPasswordEncoder(noOpPasswordEncoder());
        return authProvider;
    }

    /**
     * Configure {@link UserDetailsService} bean.
     * @return Bean of configured {@link UserDetailsService}
     */
    @Bean("soapUserDetailsService")
    public UserDetailsService soapUserDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        //@formatter:off
        manager.createUser(User.builder().username("testuser")
                                        .password("password")
                                        .authorities("ROLE_USER").build());
        manager.createUser(User.builder().username("testadmin")
                                        .password("password")
                                        .authorities("ROLE_ADMIN").build());
        //@formatter:on
        return manager;
    }

    /**
     * Configure {@link DefaultWebSecurityExpressionHandler} bean.
     * @return Bean of configured {@link DefaultWebSecurityExpressionHandler}
     */
    @Bean("webexpressionHandler")
    public DefaultWebSecurityExpressionHandler webexpressionHandler() {
        return new DefaultWebSecurityExpressionHandler();
    }
}
