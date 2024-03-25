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

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import jp.co.ntt.fw.spring.functionaltest.domain.service.athr.AuthorizationService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.athr.AuthorizationServiceImpl;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecurityAthrConfig {

    /**
     * Configure {@link AuthorizationService} bean.
     * @return Bean of configured {@link AuthorizationService}
     */
    @Bean("authorizationService")
    public AuthorizationService authorizationService() {
        return new AuthorizationServiceImpl();
    }

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param loginForStaffAuthenticationProvider Bean defined by #loginForStaffAuthenticationProvider
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("loginForStaffManager")
    // If multiple AuthenticationManagers are defined, the following error will occur, so set Primary.
    // Found 19 beans for type interface org.springframework.security.authentication.AuthenticationManager, but none marked as primary
    @Primary
    public AuthenticationManager loginForStaffManager(
            @Qualifier("loginForStaffAuthenticationProvider") AuthenticationProvider loginForStaffAuthenticationProvider) {
        return new ProviderManager(loginForStaffAuthenticationProvider);
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param loginForStaffDetailsService Bean defined by SpringSecurityAthr02Config#loginForStaffDetailsService
     * @param passwordEncoder Bean defined by ApplicationContextConfig#passwordEncoder
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("loginForStaffAuthenticationProvider")
    public AuthenticationProvider loginForStaffAuthenticationProvider(
            @Qualifier("loginForStaffDetailsService") UserDetailsService loginForStaffDetailsService,
            @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(loginForStaffDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param loginForMethodAuthenticationProvider Bean defined by #loginForMethodAuthenticationProvider
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("loginForMethodManager")
    public AuthenticationManager loginForMethodManager(
            @Qualifier("loginForMethodAuthenticationProvider") AuthenticationProvider loginForMethodAuthenticationProvider) {
        return new ProviderManager(loginForMethodAuthenticationProvider);
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param loginForMethodDetailsService Bean defined by SpringSecurityAthr03Config#loginForMethodDetailsService
     * @param passwordEncoder Bean defined by ApplicationContextConfig#passwordEncoder
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("loginForMethodAuthenticationProvider")
    public AuthenticationProvider loginForMethodAuthenticationProvider(
            @Qualifier("loginForMethodDetailsService") UserDetailsService loginForMethodDetailsService,
            @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(loginForMethodDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    /**
     * Configure {@link AuthenticationManager} bean.
     * @param loginForRoleUserAuthenticationProvider Bean defined by #loginForRoleUserAuthenticationProvider
     * @return Bean of configured {@link AuthenticationManager}
     */
    @Bean("loginForRoleUserManager")
    public AuthenticationManager loginForRoleUserManager(
            @Qualifier("loginForRoleUserAuthenticationProvider") AuthenticationProvider loginForRoleUserAuthenticationProvider) {
        return new ProviderManager(loginForRoleUserAuthenticationProvider);
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param loginForRoleUserDetailsService Bean defined by SpringSecurityAthr04Config#loginForRoleUserDetailsService
     * @param passwordEncoder Bean defined by ApplicationContextConfig#passwordEncoder
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean("loginForRoleUserAuthenticationProvider")
    public AuthenticationProvider loginForRoleUserAuthenticationProvider(
            @Qualifier("loginForRoleUserDetailsService") UserDetailsService loginForRoleUserDetailsService,
            @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(loginForRoleUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
}
