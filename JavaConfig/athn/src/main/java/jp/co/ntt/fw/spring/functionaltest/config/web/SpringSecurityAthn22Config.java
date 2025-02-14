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
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecurityAthn22Config {

    /**
     * enabledValue property.
     */
    @Value("${enabledValue}")
    private boolean enabledValue;

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param loginForRememberMeUserInfoAuthenticationProvider Bean defined by
     *        #loginForRememberMeUserInfoAuthenticationProvider
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("loginForRememberMeUserInfoManager")
    public AuthenticationManager loginForRememberMeUserInfoManager(
            @Qualifier("loginForRememberMeUserInfoAuthenticationProvider") AuthenticationProvider loginForRememberMeUserInfoAuthenticationProvider) {
        return new ProviderManager(loginForRememberMeUserInfoAuthenticationProvider,
                rememberMeAuthenticationProvider());
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param loginForRememberMeUserInfoDetailsService Bean defined by
     *        #loginForRememberMeUserInfoDetailsService
     * @param passwordEncoder Bean defined by ApplicationContextConfig#passwordEncoder
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("loginForRememberMeUserInfoAuthenticationProvider")
    public AuthenticationProvider loginForRememberMeUserInfoAuthenticationProvider(
            @Qualifier("loginForRememberMeUserInfoDetailsService") UserDetailsService loginForRememberMeUserInfoDetailsService,
            @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(loginForRememberMeUserInfoDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    /**
     * Configure {@link UserDetailsService} bean.
     * @param dataSource DataSource defined by SpringFunctionaltestEnvConfig#dataSource()
     * @return Bean of configured {@link UserDetailsService}
     */
    @Bean("loginForRememberMeUserInfoDetailsService")
    public UserDetailsService loginForRememberMeUserInfoDetailsService(
            @Qualifier("dataSource") DataSource dataSource) {
        JdbcDaoImpl bean = new JdbcDaoImpl();
        bean.setRolePrefix("ROLE_");
        bean.setDataSource(dataSource);
        bean.setUsersByUsernameQuery(
                "SELECT username, password, enabled FROM t_account WHERE username = ? and enabled = "
                        + enabledValue + "");
        bean.setAuthoritiesByUsernameQuery(
                "SELECT username, 'ROLE_USER' authority FROM t_account WHERE username = ? and enabled = "
                        + enabledValue + "");
        return bean;
    }

    /**
     * Configure {@link rememberMeAuthenticationProvider} bean.
     * @return Bean of configured {@link rememberMeAuthenticationProvider}
     */
    @Bean("rememberMeAuthenticationProvider")
    public RememberMeAuthenticationProvider rememberMeAuthenticationProvider() {
        return new RememberMeAuthenticationProvider("spring-test-km/ylnHv");
    }

    /**
     * Configure {@link TokenBasedRememberMeServices} bean.
     * @param loginForRememberMeUserInfoDetailsService Bean defined by
     *        #loginForRememberMeUserInfoDetailsService()
     * @return Bean of configured {@link TokenBasedRememberMeServices}
     */
    @Bean("rememberMeServices")
    public TokenBasedRememberMeServices rememberMeServices(
            @Qualifier("loginForRememberMeUserInfoDetailsService") UserDetailsService loginForRememberMeUserInfoDetailsService) {
        return new TokenBasedRememberMeServices("spring-test-km/ylnHv",
                loginForRememberMeUserInfoDetailsService);
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginForRememberMeUserInfoManager Bean defined by #loginForRememberMeUserInfoManager
     * @param rememberMeServices Bean defined by #rememberMeServices
     * @param sessionAuthenticationStrategy Bean defined by
     *        SpringSecurityAthn17Config#sessionAuthenticationStrategy
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(220)
    public SecurityFilterChain filterChainJsp2201(HttpSecurity http,
            @Qualifier("loginForRememberMeUserInfoManager") AuthenticationManager loginForRememberMeUserInfoManager,
            RememberMeServices rememberMeServices,
            CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy) throws Exception {

        http.securityMatcher(antMatcher("/jsp/2201/**"));
        http.authenticationManager(loginForRememberMeUserInfoManager);
        http.formLogin(login -> login.loginPage("/jsp/2201/loginForRememberMe")
                .loginProcessingUrl("/jsp/2201/authenticate")
                .defaultSuccessUrl("/jsp/2201?loginSuccess"));
        http.rememberMe(rememberMe -> rememberMe.key("spring-test-km/ylnHv")
                .rememberMeServices(rememberMeServices));
        http.sessionManagement(sessionManagement -> sessionManagement
                .sessionAuthenticationStrategy(sessionAuthenticationStrategy));
        http.logout(logout -> logout.logoutUrl("/jsp/2201/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginForRememberMeUserInfoManager Bean defined by #loginForRememberMeUserInfoManager
     * @param rememberMeServices Bean defined by #rememberMeServices
     * @param sessionAuthenticationStrategy Bean defined by
     *        SpringSecurityAthn17Config#sessionAuthenticationStrategy
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(221)
    public SecurityFilterChain filterChainThymeleaf2201(HttpSecurity http,
            @Qualifier("loginForRememberMeUserInfoManager") AuthenticationManager loginForRememberMeUserInfoManager,
            RememberMeServices rememberMeServices,
            CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy) throws Exception {

        http.securityMatcher(antMatcher("/thymeleaf/2201/**"));
        http.authenticationManager(loginForRememberMeUserInfoManager);
        http.formLogin(login -> login.loginPage("/thymeleaf/2201/loginForRememberMe")
                .loginProcessingUrl("/thymeleaf/2201/authenticate")
                .defaultSuccessUrl("/thymeleaf/2201?loginSuccess"));
        http.rememberMe(rememberMe -> rememberMe.key("spring-test-km/ylnHv")
                .rememberMeServices(rememberMeServices));
        http.sessionManagement(sessionManagement -> sessionManagement
                .sessionAuthenticationStrategy(sessionAuthenticationStrategy));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/2201/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }
}
