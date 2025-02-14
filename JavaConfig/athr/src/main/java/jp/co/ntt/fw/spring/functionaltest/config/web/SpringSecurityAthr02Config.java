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
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecurityAthr02Config {

    /**
     * enabledValue property.
     */
    @Value("${enabledValue}")
    private boolean enabledValue;

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginForStaffManager Bean defined by SpringSecurityAthrConfig#loginForStaffManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(20)
    public SecurityFilterChain filterChainJsp0201(HttpSecurity http,
            @Qualifier("loginForStaffManager") AuthenticationManager loginForStaffManager)
            throws Exception {

        http.securityMatcher(antMatcher("/jsp/0201/**"));
        http.authenticationManager(loginForStaffManager);
        http.formLogin(login -> login.loginPage("/jsp/0201/001")
                .loginProcessingUrl("/jsp/0201/001/authenticate")
                .defaultSuccessUrl("/jsp/0201/001/afterLogin"));
        http.logout(logout -> logout.logoutUrl("/jsp/0201/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(
                authz -> authz.requestMatchers(antMatcher("/jsp/0201/001/*")).authenticated()
                        .requestMatchers(antMatcher("/jsp/0201/002/restrict.*")).authenticated()
                        .requestMatchers(antMatcher("/jsp/0201/002/restrict/")).authenticated()
                        .requestMatchers(antMatcher("/jsp/0201/002/restrict")).authenticated()
                        .requestMatchers(antMatcher("/jsp/0201/004/showForAccessSuccessfully"))
                        .authenticated().requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginForStaffManager Bean defined by SpringSecurityAthrConfig#loginForStaffManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(21)
    public SecurityFilterChain filterChainThymeleaf0201(HttpSecurity http,
            @Qualifier("loginForStaffManager") AuthenticationManager loginForStaffManager)
            throws Exception {

        http.securityMatcher(antMatcher("/thymeleaf/0201/**"));
        http.authenticationManager(loginForStaffManager);
        http.formLogin(login -> login.loginPage("/thymeleaf/0201/001")
                .loginProcessingUrl("/thymeleaf/0201/001/authenticate")
                .defaultSuccessUrl("/thymeleaf/0201/001/afterLogin"));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0201/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(antMatcher("/thymeleaf/0201/001/*")).authenticated()
                .requestMatchers(antMatcher("/thymeleaf/0201/002/restrict.*")).authenticated()
                .requestMatchers(antMatcher("/thymeleaf/0201/002/restrict/")).authenticated()
                .requestMatchers(antMatcher("/thymeleaf/0201/002/restrict")).authenticated()
                .requestMatchers(antMatcher("/thymeleaf/0201/004/showForAccessSuccessfully"))
                .authenticated().requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link UserDetailsService} bean.
     * @param dataSource DataSource defined by SpringFunctionaltestEnvConfig#dataSource()
     * @return Bean of configured {@link UserDetailsService}
     */
    @Bean("loginForStaffDetailsService")
    public UserDetailsService loginForStaffDetailsService(
            @Qualifier("dataSource") DataSource dataSource) {
        JdbcDaoImpl bean = new JdbcDaoImpl();
        bean.setDataSource(dataSource);
        bean.setUsersByUsernameQuery(
                "SELECT username, password, enabled FROM t_staff WHERE username = ? and enabled = "
                        + enabledValue + "");
        bean.setAuthoritiesByUsernameQuery(
                "SELECT username, authority FROM t_staff WHERE username = ? and enabled = "
                        + enabledValue + "");
        return bean;
    }
}
