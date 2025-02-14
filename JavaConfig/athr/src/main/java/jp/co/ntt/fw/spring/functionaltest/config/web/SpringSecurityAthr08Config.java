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
import java.util.LinkedHashMap;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.DelegatingAuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import jp.co.ntt.fw.spring.functionaltest.app.athr.entrypoint.AjaxAuthenticationEntryPoint;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecurityAthr08Config {

    /**
     * Configure {@link DelegatingAuthenticationEntryPoint} bean.
     * @return Bean of configured {@link DelegatingAuthenticationEntryPoint}
     */
    @Bean("jspAjaxAuthenticationEntryPoint")
    public DelegatingAuthenticationEntryPoint jspAjaxAuthenticationEntryPoint() {

        LinkedHashMap<RequestMatcher, AuthenticationEntryPoint> map =
                new LinkedHashMap<RequestMatcher, AuthenticationEntryPoint>();
        map.put(jspEntryPointAntPathRequestMatcher(), jspEntryPoint());
        DelegatingAuthenticationEntryPoint bean = new DelegatingAuthenticationEntryPoint(map);
        bean.setDefaultEntryPoint(jspLoginUrlAuthenticationEntryPoint());
        return bean;
    }

    /**
     * Configure {@link AntPathRequestMatcher} bean.
     * @return Bean of configured {@link AntPathRequestMatcher}
     */
    @Bean("jspEntryPointAntPathRequestMatcher")
    public AntPathRequestMatcher jspEntryPointAntPathRequestMatcher() {
        return antMatcher("/jsp/0802/001/api/**");
    }

    /**
     * Configure {@link AjaxAuthenticationEntryPoint} bean.
     * @return Bean of configured {@link AjaxAuthenticationEntryPoint}
     */
    @Bean("jspEntryPoint")
    public AjaxAuthenticationEntryPoint jspEntryPoint() {
        return new AjaxAuthenticationEntryPoint();
    }

    /**
     * Configure {@link LoginUrlAuthenticationEntryPoint} bean.
     * @return Bean of configured {@link LoginUrlAuthenticationEntryPoint}
     */
    @Bean("jspLoginUrlAuthenticationEntryPoint")
    public LoginUrlAuthenticationEntryPoint jspLoginUrlAuthenticationEntryPoint() {
        return new LoginUrlAuthenticationEntryPoint("/jsp/0802/001");
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginForStaffManager Bean defined by SpringSecurityAthrConfig#loginForStaffManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(81)
    public SecurityFilterChain filterChainJsp0802(HttpSecurity http,
            @Qualifier("loginForStaffManager") AuthenticationManager loginForStaffManager)
            throws Exception {

        http.securityMatcher(antMatcher("/jsp/0802/**"));
        http.authenticationManager(loginForStaffManager);
        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(jspAjaxAuthenticationEntryPoint()));
        http.formLogin(login -> login.loginProcessingUrl("/jsp/0802/customerAuthenticate")
                .defaultSuccessUrl("/jsp/0802/001?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/jsp/0802/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/jsp/0802/001/api"))
                .authenticated().requestMatchers(antMatcher("/jsp/0802/001/afterLogin"))
                .authenticated().requestMatchers(antMatcher("/jsp/0802/**")).permitAll()
                .requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Configure {@link DelegatingAuthenticationEntryPoint} bean.
     * @return Bean of configured {@link DelegatingAuthenticationEntryPoint}
     */
    @Bean("thymeleafAjaxAuthenticationEntryPoint")
    public DelegatingAuthenticationEntryPoint thymeleafAjaxAuthenticationEntryPoint() {

        LinkedHashMap<RequestMatcher, AuthenticationEntryPoint> map =
                new LinkedHashMap<RequestMatcher, AuthenticationEntryPoint>();
        map.put(thymeleafEntryPointAntPathRequestMatcher(), thymeleafEntryPoint());
        DelegatingAuthenticationEntryPoint bean = new DelegatingAuthenticationEntryPoint(map);
        bean.setDefaultEntryPoint(thymeleafLoginUrlAuthenticationEntryPoint());
        return bean;
    }

    /**
     * Configure {@link AntPathRequestMatcher} bean.
     * @return Bean of configured {@link AntPathRequestMatcher}
     */
    @Bean("thymeleafEntryPointAntPathRequestMatcher")
    public AntPathRequestMatcher thymeleafEntryPointAntPathRequestMatcher() {
        return antMatcher("/thymeleaf/0802/001/api/**");
    }

    /**
     * Configure {@link AjaxAuthenticationEntryPoint} bean.
     * @return Bean of configured {@link AjaxAuthenticationEntryPoint}
     */
    @Bean("thymeleafEntryPoint")
    public AjaxAuthenticationEntryPoint thymeleafEntryPoint() {
        return new AjaxAuthenticationEntryPoint();
    }

    /**
     * Configure {@link LoginUrlAuthenticationEntryPoint} bean.
     * @return Bean of configured {@link LoginUrlAuthenticationEntryPoint}
     */
    @Bean("thymeleafLoginUrlAuthenticationEntryPoint")
    public LoginUrlAuthenticationEntryPoint thymeleafLoginUrlAuthenticationEntryPoint() {
        return new LoginUrlAuthenticationEntryPoint("/thymeleaf/0802/001");
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @param loginForStaffManager Bean defined by SpringSecurityAthrConfig#loginForStaffManager
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(81)
    public SecurityFilterChain filterChainThymeleaf0802(HttpSecurity http,
            @Qualifier("loginForStaffManager") AuthenticationManager loginForStaffManager)
            throws Exception {

        http.securityMatcher(antMatcher("/thymeleaf/0802/**"));
        http.authenticationManager(loginForStaffManager);
        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(thymeleafAjaxAuthenticationEntryPoint()));
        http.formLogin(login -> login.loginProcessingUrl("/thymeleaf/0802/customerAuthenticate")
                .defaultSuccessUrl("/thymeleaf/0802/001?loginSuccess", true));
        http.logout(logout -> logout.logoutUrl("/thymeleaf/0802/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers(antMatcher("/thymeleaf/0802/001/api")).authenticated()
                .requestMatchers(antMatcher("/thymeleaf/0802/001/afterLogin")).authenticated()
                .requestMatchers(antMatcher("/thymeleaf/0802/**")).permitAll()
                .requestMatchers(antMatcher("/**")).permitAll());

        return http.build();
    }

}
