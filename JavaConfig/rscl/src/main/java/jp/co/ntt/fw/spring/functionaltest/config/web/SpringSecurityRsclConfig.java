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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Bean definition to configure SpringSecurity.
 */
@SuppressWarnings("deprecation")
@Configuration
public class SpringSecurityRsclConfig {

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    public SecurityFilterChain filterChainRscl(HttpSecurity http) throws Exception {

        http.securityMatcher(antMatcher("/api/v1/rscl/**"));
        http.sessionManagement(sessionManagement -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authenticationManager(userLoginManager());
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/api/v1/rscl/basic"))
                .hasAuthority("ROLE_USER").requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link AuthenticationManager} bean.
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("rsclAuthenticationManager")
    public AuthenticationManager userLoginManager() {
        return new ProviderManager(authenticationProvider());
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("authenticationProvider")
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(noOpPasswordEncoder());
        return authProvider;
    }

    /**
     * Configure {@link UserDetailsService} bean.
     * @return Bean of configured {@link UserDetailsService}
     */
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.builder().username("rscluser").password("password1234")
                .authorities("ROLE_USER").build());
        return manager;
    }

    /**
     * Configure {@link PasswordEncoder} bean.
     * @return Bean of configured {@link PasswordEncoder}
     */
    // NoOpPasswordEncoderは非推奨ではあるが削除予定はない
    @Bean("noOpPasswordEncoder")
    public PasswordEncoder noOpPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
