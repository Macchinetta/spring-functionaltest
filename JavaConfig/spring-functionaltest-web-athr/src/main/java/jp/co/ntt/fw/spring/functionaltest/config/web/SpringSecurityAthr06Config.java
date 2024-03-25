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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecurityAthr06Config {

    /**
     * RemoteIpAddress property.
     */
    @Value("${RemoteIpAddress:localhost}")
    private String remoteIpAddress;

    /**
     * ahthorization.ipaddress.deny property.
     */
    @Value("${ahthorization.ipaddress.deny}")
    private String ipaddressDeny;

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginForStaffManager Bean defined by SpringSecurityAthrConfig#loginForStaffManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(50)
    public SecurityFilterChain filterChainJsp0601001(HttpSecurity http,
            @Qualifier("loginForStaffManager") AuthenticationManager loginForStaffManager) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/0601/001/**"));
        http.authenticationManager(loginForStaffManager);
        http.formLogin(login -> login.loginPage("/jsp/0601/001")
                .loginProcessingUrl("/jsp/0601/001/authenticate")
                .defaultSuccessUrl("/jsp/0601/001/afterLogin"));
        http.logout(logout -> logout.logoutUrl("/jsp/0601/001/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/001/accounts/**")).hasAnyRole("ACCOUNT_MANAGER", "ACCOUNT_USER")
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/001/manager/**")).hasRole("ACCOUNT_MANAGER")
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/001/afterLogin")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/001/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

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
    @Order(51)
    public SecurityFilterChain filterChainJsp0601002(HttpSecurity http,
            @Qualifier("loginForStaffManager") AuthenticationManager loginForStaffManager) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/0601/002/**"));
        http.authenticationManager(loginForStaffManager);
        http.formLogin(login -> login.loginPage("/jsp/0601/002")
                .loginProcessingUrl("/jsp/0601/002/authenticate")
                .defaultSuccessUrl("/jsp/0601/002/afterLogin"));
        http.logout(logout -> logout.logoutUrl("/jsp/0601/002/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/002/manager/**")).hasRole("ACCOUNT_MANAGER")
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/002/configurations/**"))
                .access(new WebExpressionAuthorizationManager(
                        "hasIpAddress('" + remoteIpAddress + "') and hasRole('CONFIGURATION_MANAGER')"))
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/002/admin/**")).hasRole("ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/002/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

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
    @Order(52)
    public SecurityFilterChain filterChainJsp0601003(HttpSecurity http,
            @Qualifier("loginForStaffManager") AuthenticationManager loginForStaffManager) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/0601/003/**"));
        http.authenticationManager(loginForStaffManager);
        http.formLogin(login -> login.loginPage("/jsp/0601/003")
                .loginProcessingUrl("/jsp/0601/003/authenticate")
                .defaultSuccessUrl("/jsp/0601/003/afterLogin"));
        http.logout(logout -> logout.logoutUrl("/jsp/0601/003/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/003/manager/**")).hasRole("ACCOUNT_MANAGER")
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/003/configurations/**"))
                .access(new WebExpressionAuthorizationManager(
                        "hasIpAddress('" + ipaddressDeny + "') and hasRole('CONFIGURATION_MANAGER')"))
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/003/admin/**")).hasRole("ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/003/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

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
    @Order(53)
    public SecurityFilterChain filterChainJsp0601004(HttpSecurity http,
            @Qualifier("loginForStaffManager") AuthenticationManager loginForStaffManager) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/0601/004/**"));
        http.authenticationManager(loginForStaffManager);
        http.formLogin(login -> login.loginPage("/jsp/0601/004")
                .loginProcessingUrl("/jsp/0601/004/authenticate")
                .defaultSuccessUrl("/jsp/0601/004/afterLogin"));
        http.logout(logout -> logout.logoutUrl("/jsp/0601/004/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/004/user/**")).hasAnyRole("USER","ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/004/admin/**")).hasRole("ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/004/deny/**")).denyAll()
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/004/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

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
    @Order(54)
    public SecurityFilterChain filterChainJsp0601019(HttpSecurity http,
            @Qualifier("loginForStaffManager") AuthenticationManager loginForStaffManager) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/0601/019/**"));
        http.authenticationManager(loginForStaffManager);
        http.formLogin(login -> login.loginPage("/jsp/0601/019")
                .loginProcessingUrl("/jsp/0601/019/authenticate")
                .defaultSuccessUrl("/"));
        http.logout(logout -> logout.logoutUrl("/jsp/0601/019/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/019/account/{userName}/**"))
                .access(new WebExpressionAuthorizationManager("isAuthenticated() and #userName == principal.username"))
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

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
    @Order(55)
    public SecurityFilterChain filterChainJsp0601020(HttpSecurity http,
            @Qualifier("loginForStaffManager") AuthenticationManager loginForStaffManager) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/0601/020/**"));
        http.authenticationManager(loginForStaffManager);
        http.formLogin(Customizer.withDefaults());
        http.logout(logout -> logout.logoutUrl("/jsp/0601/020/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/020/account/{userName}/"))
                .access(new WebExpressionAuthorizationManager("isAuthenticated() and #userName == principal.username"))
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/020/account/{userName}"))
                .access(new WebExpressionAuthorizationManager("isAuthenticated() and #userName == principal.username"))
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

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
    @Order(56)
    public SecurityFilterChain filterChainJsp0601021(HttpSecurity http,
            @Qualifier("loginForStaffManager") AuthenticationManager loginForStaffManager) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/0601/021/**"));
        http.authenticationManager(loginForStaffManager);
        http.formLogin(Customizer.withDefaults());
        http.logout(logout -> logout.logoutUrl("/jsp/0601/021/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/021/account/{userName}.*"))
                .access(new WebExpressionAuthorizationManager("isAuthenticated() and #userName == principal.username"))
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/021/account/{userName}/**"))
                .access(new WebExpressionAuthorizationManager("isAuthenticated() and #userName == principal.username"))
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

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
    @Order(57)
    public SecurityFilterChain filterChainJsp0601022(HttpSecurity http,
            @Qualifier("loginForStaffManager") AuthenticationManager loginForStaffManager) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/jsp/0601/022/**"));
        http.authenticationManager(loginForStaffManager);
        http.formLogin(Customizer.withDefaults());
        http.logout(logout -> logout.logoutUrl("/jsp/0601/022/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/022/account/{userName}.*"))
                .access(new WebExpressionAuthorizationManager("isAuthenticated() and #userName == principal.username"))
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/022/account/{userName}/"))
                .access(new WebExpressionAuthorizationManager("isAuthenticated() and #userName == principal.username"))
                .requestMatchers(new AntPathRequestMatcher("/jsp/0601/022/account/{userName}"))
                .access(new WebExpressionAuthorizationManager("isAuthenticated() and #userName == principal.username"))
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

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
    @Order(58)
    public SecurityFilterChain filterChainThymeleaf0601001(HttpSecurity http,
            @Qualifier("loginForStaffManager") AuthenticationManager loginForStaffManager) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0601/001/**"));
        http.authenticationManager(loginForStaffManager);
        http.formLogin(login -> login.loginPage("/thymeleaf/0601/001")
                .loginProcessingUrl("/thymeleaf/0601/001/authenticate")
                .defaultSuccessUrl("/thymeleaf/0601/001/afterLogin"));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0601/001/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/001/accounts/**")).hasAnyRole("ACCOUNT_MANAGER", "ACCOUNT_USER")
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/001/manager/**")).hasRole("ACCOUNT_MANAGER")
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/001/afterLogin")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/001/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

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
    @Order(59)
    public SecurityFilterChain filterChainThymeleaf0601002(HttpSecurity http,
            @Qualifier("loginForStaffManager") AuthenticationManager loginForStaffManager) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0601/002/**"));
        http.authenticationManager(loginForStaffManager);
        http.formLogin(login -> login.loginPage("/thymeleaf/0601/002")
                .loginProcessingUrl("/thymeleaf/0601/002/authenticate")
                .defaultSuccessUrl("/thymeleaf/0601/002/afterLogin"));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0601/002/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/002/manager/**")).hasRole("ACCOUNT_MANAGER")
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/002/configurations/**"))
                .access(new WebExpressionAuthorizationManager(
                        "hasIpAddress('" + remoteIpAddress  + "') and hasRole('CONFIGURATION_MANAGER')"))
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/002/admin/**")).hasRole("ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/002/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

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
    @Order(60)
    public SecurityFilterChain filterChainThymeleaf0601003(HttpSecurity http,
            @Qualifier("loginForStaffManager") AuthenticationManager loginForStaffManager) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0601/003/**"));
        http.authenticationManager(loginForStaffManager);
        http.formLogin(login -> login.loginPage("/thymeleaf/0601/003")
                .loginProcessingUrl("/thymeleaf/0601/003/authenticate")
                .defaultSuccessUrl("/thymeleaf/0601/003/afterLogin"));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0601/003/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/003/manager/**")).hasRole("ACCOUNT_MANAGER")
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/003/configurations/**"))
                .access(new WebExpressionAuthorizationManager(
                        "hasIpAddress('" + ipaddressDeny + "') and hasRole('CONFIGURATION_MANAGER')"))
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/003/admin/**")).hasRole("ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/003/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

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
    @Order(61)
    public SecurityFilterChain filterChainThymeleaf0601004(HttpSecurity http,
            @Qualifier("loginForStaffManager") AuthenticationManager loginForStaffManager) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0601/004/**"));
        http.authenticationManager(loginForStaffManager);
        http.formLogin(login -> login.loginPage("/thymeleaf/0601/004")
                .loginProcessingUrl("/thymeleaf/0601/004/authenticate")
                .defaultSuccessUrl("/thymeleaf/0601/004/afterLogin"));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0601/004/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/004/user/**")).hasAnyRole("USER","ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/004/admin/**")).hasRole("ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/004/deny/**")).denyAll()
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/004/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

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
    @Order(62)
    public SecurityFilterChain filterChainThymeleaf0601019(HttpSecurity http,
            @Qualifier("loginForStaffManager") AuthenticationManager loginForStaffManager) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0601/019/**"));
        http.authenticationManager(loginForStaffManager);
        http.formLogin(login -> login.loginPage("/thymeleaf/0601/019")
                .loginProcessingUrl("/thymeleaf/0601/019/authenticate")
                .defaultSuccessUrl("/"));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0601/019/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/019/account/{userName}/**"))
                .access(new WebExpressionAuthorizationManager("isAuthenticated() and #userName == principal.username"))
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

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
    @Order(63)
    public SecurityFilterChain filterChainThymeleaf0601020(HttpSecurity http,
            @Qualifier("loginForStaffManager") AuthenticationManager loginForStaffManager) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0601/020/**"));
        http.authenticationManager(loginForStaffManager);
        http.formLogin(Customizer.withDefaults());
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0601/020/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/020/account/{userName}/"))
                .access(new WebExpressionAuthorizationManager("isAuthenticated() and #userName == principal.username"))
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/020/account/{userName}"))
                .access(new WebExpressionAuthorizationManager("isAuthenticated() and #userName == principal.username"))
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

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
    @Order(64)
    public SecurityFilterChain filterChainThymeleaf0601021(HttpSecurity http,
            @Qualifier("loginForStaffManager") AuthenticationManager loginForStaffManager) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0601/021/**"));
        http.authenticationManager(loginForStaffManager);
        http.formLogin(Customizer.withDefaults());
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0601/021/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/021/account/{userName}.*"))
                .access(new WebExpressionAuthorizationManager("isAuthenticated() and #userName == principal.username"))
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/021/account/{userName}/**"))
                .access(new WebExpressionAuthorizationManager("isAuthenticated() and #userName == principal.username"))
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

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
    @Order(65)
    public SecurityFilterChain filterChainThymeleaf0601022(HttpSecurity http,
            @Qualifier("loginForStaffManager") AuthenticationManager loginForStaffManager) throws Exception {

        http.securityMatcher(new AntPathRequestMatcher("/thymeleaf/0601/022/**"));
        http.authenticationManager(loginForStaffManager);
        http.formLogin(Customizer.withDefaults());
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0601/022/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/022/account/{userName}.*"))
                .access(new WebExpressionAuthorizationManager("isAuthenticated() and #userName == principal.username"))
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/022/account/{userName}/"))
                .access(new WebExpressionAuthorizationManager("isAuthenticated() and #userName == principal.username"))
                .requestMatchers(new AntPathRequestMatcher("/thymeleaf/0601/022/account/{userName}"))
                .access(new WebExpressionAuthorizationManager("isAuthenticated() and #userName == principal.username"))
                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }
}
