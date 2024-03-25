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

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jp.co.ntt.fw.spring.functionaltest.domain.service.athn.AccountUserDetailsService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.athn.authEventHandleAccountUserDetailsService;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecurityAthnConfig {

    /**
     * enabledValue property.
     */
    @Value("${enabledValue}")
    private boolean enabledValue;

    /**
     * password.bcrypt.iteration property.
     */
    @Value("${password.bcrypt.iteration}")
    private int bcryptIteration;

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
     * @param authenticationProvider Bean defined by #userLoginAuthenticationProvider
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("userLoginManagerForAthn")
    // If multiple AuthenticationManagers are defined, the following error will occur, so set Primary.
    // Found 19 beans for type interface org.springframework.security.authentication.AuthenticationManager, but none marked as primary
    @Primary
    public AuthenticationManager userLoginManagerForAthn(
            @Qualifier("userLoginAuthenticationProvider") AuthenticationProvider userLoginAuthenticationProvider) {
        return new ProviderManager(userLoginAuthenticationProvider);
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param userDetailsServiceForAthn Bean defined by #userDetailsServiceForAthn
     * @param passwordEncoder Bean defined by ApplicationContextConfig#passwordEncoder
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("userLoginAuthenticationProvider")
    public AuthenticationProvider userLoginAuthenticationProvider(
            @Qualifier("userDetailsServiceForAthn") UserDetailsService userDetailsServiceForAthn,
            @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsServiceForAthn);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param authenticationProvider Bean defined by #dbUserLoginAuthenticationProvider
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("dbUserLoginManager")
    public AuthenticationManager dbUserLoginManager(
            @Qualifier("dbUserLoginAuthenticationProvider") AuthenticationProvider dbUserLoginAuthenticationProvider) {
        return new ProviderManager(dbUserLoginAuthenticationProvider);
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param passwordEncoder Bean defined by ApplicationContextConfig#passwordEncoder
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("dbUserLoginAuthenticationProvider")
    public AuthenticationProvider dbUserLoginAuthenticationProvider(
            @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(dbUserDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    /**
     * Configure {@link UserDetailsService} bean.
     * @param dataSource DataSource defined by SpringFunctionaltestEnvConfig#dataSource()
     * @return Bean of configured {@link UserDetailsService}
     */
    @Bean("userDetailsServiceForAthn")
    public UserDetailsService userDetailsServiceForAthn(
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
     * Configure {@link UserDetailsService} bean.
     * @return Bean of configured {@link UserDetailsService}
     */
    @Bean("dbUserDetailsService")
    public UserDetailsService dbUserDetailsService() {
        return new AccountUserDetailsService();
    }

    /**
     * Configure {@link UserDetailsService} bean.
     * @return Bean of configured {@link UserDetailsService}
     */
    @Bean("authEventHandleUserDetailsService")
    public UserDetailsService authEventHandleUserDetailsService() {
        return new authEventHandleAccountUserDetailsService();
    }

    /**
     * Configure {@link UserDetailsService} bean.
     * @param dataSource DataSource defined by SpringFunctionaltestEnvConfig#dataSource()
     * @return Bean of configured {@link UserDetailsService}
     */
    @Bean("loginWithEncodingPasswordService")
    public UserDetailsService loginWithEncodingPasswordService(
            @Qualifier("dataSource") DataSource dataSource) {
        JdbcDaoImpl bean = new JdbcDaoImpl();
        bean.setDataSource(dataSource);
        bean.setUsersByUsernameQuery(
                "SELECT username, password, enabled FROM t_administrator WHERE username = ? and enabled = "
                        + enabledValue + "");
        bean.setAuthoritiesByUsernameQuery(
                "SELECT username, authority FROM t_administrator WHERE username = ? and enabled = "
                        + enabledValue + "");
        return bean;
    }
}
