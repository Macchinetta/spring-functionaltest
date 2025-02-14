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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecuritySpsm04Config {

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManagerForAthn Bean defined by SpringSecurityConfig#userLoginManagerForAthn
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    public SecurityFilterChain filterChainJsp0403001001(HttpSecurity http,
            @Qualifier("userLoginManagerForAthn") AuthenticationManager userLoginManagerForAthn)
            throws Exception {

        http.securityMatcher(antMatcher("/jsp/0403/001/001/**"));
        http.authenticationManager(userLoginManagerForAthn);

        http.formLogin(login -> login.loginPage("/jsp/0403/001/001/login")
                .loginProcessingUrl("/jsp/0403/001/001/authenticate")
                .defaultSuccessUrl("/jsp/0403/001/001?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/jsp/0403/001/001/logout")
                .logoutSuccessUrl("/jsp/0403/001/001/login"));
        http.sessionManagement(sessionManagement -> sessionManagement
                .invalidSessionUrl("/jsp/invalidSessionError"));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManagerForAthn Bean defined by SpringSecurityConfig#userLoginManagerForAthn
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChainJsp0403001002(HttpSecurity http,
            @Qualifier("userLoginManagerForAthn") AuthenticationManager userLoginManagerForAthn)
            throws Exception {

        http.securityMatcher(antMatcher("/jsp/0403/001/002/**"));
        http.authenticationManager(userLoginManagerForAthn);

        http.formLogin(login -> login.loginPage("/jsp/0403/001/002/login")
                .loginProcessingUrl("/jsp/0403/001/002/authenticate")
                .defaultSuccessUrl("/jsp/0403/001/002?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/jsp/0403/001/002/logout")
                .logoutSuccessUrl("/jsp/0403/001/002/login"));
        http.sessionManagement(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManagerForAthn Bean defined by SpringSecurityConfig#userLoginManagerForAthn
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChainThymeleaf0403001001(HttpSecurity http,
            @Qualifier("userLoginManagerForAthn") AuthenticationManager userLoginManagerForAthn)
            throws Exception {

        http.securityMatcher(antMatcher("/thymeleaf/0403/001/001/**"));
        http.authenticationManager(userLoginManagerForAthn);

        http.formLogin(login -> login.loginPage("/thymeleaf/0403/001/001/login")
                .loginProcessingUrl("/thymeleaf/0403/001/001/authenticate")
                .defaultSuccessUrl("/thymeleaf/0403/001/001?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0403/001/001/logout")
                .logoutSuccessUrl("/thymeleaf/0403/001/001/login"));
        http.sessionManagement(sessionManagement -> sessionManagement
                .invalidSessionUrl("/thymeleaf/invalidSessionError"));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManagerForAthn Bean defined by SpringSecurityConfig#userLoginManagerForAthn
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChainThymeleaf0403001002(HttpSecurity http,
            @Qualifier("userLoginManagerForAthn") AuthenticationManager userLoginManagerForAthn)
            throws Exception {

        http.securityMatcher(antMatcher("/thymeleaf/0403/001/002/**"));
        http.authenticationManager(userLoginManagerForAthn);

        http.formLogin(login -> login.loginPage("/thymeleaf/0403/001/002/login")
                .loginProcessingUrl("/thymeleaf/0403/001/002/authenticate")
                .defaultSuccessUrl("/thymeleaf/0403/001/002?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0403/001/002/logout")
                .logoutSuccessUrl("/thymeleaf/0403/001/002/login"));
        http.sessionManagement(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

}
