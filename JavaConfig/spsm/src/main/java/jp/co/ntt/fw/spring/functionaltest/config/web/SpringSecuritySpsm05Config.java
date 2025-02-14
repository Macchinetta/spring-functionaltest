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
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecuritySpsm05Config {

    /**
     * Enabled Value
     */
    @Value("${enabledValue}")
    private boolean enabledValue;

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManagerForAthn Bean defined by #loginForSessionUserInfoManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    public SecurityFilterChain filterChainJsp0501001(HttpSecurity http,
            @Qualifier("loginForSessionUserInfoManager") AuthenticationManager userLoginManagerForAthn)
            throws Exception {

        http.securityMatcher(antMatcher("/jsp/0501/001/**"));
        http.authenticationManager(userLoginManagerForAthn);

        http.formLogin(login -> login.loginPage("/jsp/0501/001")
                .loginProcessingUrl("/jsp/0501/001/authenticate")
                .defaultSuccessUrl("/jsp/0501/001?loginSuccess"));
        http.sessionManagement(sessionManagement -> sessionManagement
                .sessionConcurrency(sessionConcurrency -> sessionConcurrency
                        .maxSessionsPreventsLogin(false).maximumSessions(2).expiredUrl("/")));
        http.sessionManagement(Customizer.withDefaults());
        http.logout(logout -> logout.logoutUrl("/jsp/0501/001/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(
                authz -> authz.requestMatchers(antMatcher("/jsp/0501/001/login/**")).permitAll()
                        .requestMatchers(antMatcher("/jsp/0501/**")).authenticated()
                        .requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManagerForAthn Bean defined by #loginForSessionUserInfoManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    public SecurityFilterChain filterChainJsp0501002(HttpSecurity http,
            @Qualifier("loginForSessionUserInfoManager") AuthenticationManager userLoginManagerForAthn)
            throws Exception {

        http.securityMatcher(antMatcher("/jsp/0501/002/**"));
        http.authenticationManager(userLoginManagerForAthn);

        http.formLogin(login -> login.loginPage("/jsp/0501/002")
                .loginProcessingUrl("/jsp/0501/002/authenticate")
                .defaultSuccessUrl("/jsp/0501/002?loginSuccess"));
        http.sessionManagement(sessionManagement -> sessionManagement
                .sessionConcurrency(sessionConcurrency -> sessionConcurrency
                        .maxSessionsPreventsLogin(true).maximumSessions(2)));
        http.sessionManagement(Customizer.withDefaults());
        http.logout(logout -> logout.logoutUrl("/jsp/0501/002/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManagerForAthn Bean defined by #loginForSessionUserInfoManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    public SecurityFilterChain filterChainThymeleaf0501001(HttpSecurity http,
            @Qualifier("loginForSessionUserInfoManager") AuthenticationManager userLoginManagerForAthn)
            throws Exception {

        http.securityMatcher(antMatcher("/thymeleaf/0501/001/**"));
        http.authenticationManager(userLoginManagerForAthn);

        http.formLogin(login -> login.loginPage("/thymeleaf/0501/001")
                .loginProcessingUrl("/thymeleaf/0501/001/authenticate")
                .defaultSuccessUrl("/thymeleaf/0501/001?loginSuccess"));
        http.sessionManagement(sessionManagement -> sessionManagement
                .sessionConcurrency(sessionConcurrency -> sessionConcurrency
                        .maxSessionsPreventsLogin(false).maximumSessions(2).expiredUrl("/")));
        http.sessionManagement(Customizer.withDefaults());
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0501/001/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(
                authz -> authz.requestMatchers(antMatcher("/thymeleaf/0501/001/login/**"))
                        .permitAll().requestMatchers(antMatcher("/thymeleaf/0501/**"))
                        .authenticated().requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param userLoginManagerForAthn Bean defined by #loginForSessionUserInfoManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    public SecurityFilterChain filterChainThymeleaf0501002(HttpSecurity http,
            @Qualifier("loginForSessionUserInfoManager") AuthenticationManager userLoginManagerForAthn)
            throws Exception {

        http.securityMatcher(antMatcher("/thymeleaf/0501/002/**"));
        http.authenticationManager(userLoginManagerForAthn);

        http.formLogin(login -> login.loginPage("/thymeleaf/0501/002")
                .loginProcessingUrl("/thymeleaf/0501/002/authenticate")
                .defaultSuccessUrl("/thymeleaf/0501/002?loginSuccess"));
        http.sessionManagement(sessionManagement -> sessionManagement
                .sessionConcurrency(sessionConcurrency -> sessionConcurrency
                        .maxSessionsPreventsLogin(true).maximumSessions(2)));
        http.sessionManagement(Customizer.withDefaults());
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0501/002/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param loginForSessionUserInfoProvider Bean defined by #userLoginAuthenticationProvider
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("loginForSessionUserInfoManager")
    public AuthenticationManager loginForSessionUserInfoManager(
            @Qualifier("loginForSessionUserInfoProvider") AuthenticationProvider loginForSessionUserInfoProvider) {
        return new ProviderManager(loginForSessionUserInfoProvider);
    }

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param loginForSessionUserInfoDetailsService Bean defined by
     *        #loginForSessionUserInfoDetailsService
     * @param passwordEncoder Bean defined by ApplicationContextConfig#passwordEncoder
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("loginForSessionUserInfoProvider")
    public AuthenticationProvider userLoginAuthenticationProvider(
            @Qualifier("loginForSessionUserInfoDetailsService") UserDetailsService loginForSessionUserInfoDetailsService,
            @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(loginForSessionUserInfoDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    /**
     * Configure {@link UserDetailsService} bean.
     * @param dataSource Bean defined by SpringFunctionaltestEnvConfig#dataSource
     * @return Bean of configured {@link UserDetailsService}
     */
    @Bean("loginForSessionUserInfoDetailsService")
    public UserDetailsService loginForSessionUserInfoDetailsService(
            @Qualifier("dataSource") DataSource dataSource) {
        JdbcDaoImpl bean = new JdbcDaoImpl();
        bean.setDataSource(dataSource);
        bean.setUsersByUsernameQuery(
                "SELECT username, password, enabled FROM t_account WHERE username = ? and enabled = "
                        + enabledValue + "");
        bean.setAuthoritiesByUsernameQuery(
                "SELECT username, 'ROLE_USER' authority FROM t_account WHERE username = ? and enabled = "
                        + enabledValue + "");

        return bean;
    }
}
