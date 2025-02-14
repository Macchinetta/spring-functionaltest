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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
@EnableMethodSecurity
public class SpringSecurityOth2Config {

    /**
     * jwk.set.uri property.
     */
    @Value("${oth2.jwk.set.uri}")
    private String jwkSetUri;

    /**
     * jwk.set.wronguri property.
     */
    @Value("${oth2.jwk.set.wronguri}")
    private String jwkSetWrongUri;

    /**
     * Configure {@link HandlerMappingIntrospector} bean.
     * @return Bean of configured {@link HandlerMappingIntrospector}
     */
    @Bean
    public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
        return new HandlerMappingIntrospector();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Order(1)
    @Bean
    public SecurityFilterChain authFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher(antMatcher("/auth/**"));
        // @formatter:off
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(antMatcher(HttpMethod.GET, "/auth/intercepturl/**")).hasAuthority("SCOPE_READ")
                .requestMatchers(antMatcher(HttpMethod.POST, "/auth/intercepturl/**")).hasAuthority("SCOPE_CREATE")
                .requestMatchers(antMatcher(HttpMethod.PUT, "/auth/intercepturl/**")).hasAuthority("SCOPE_UPDATE")
                .requestMatchers(antMatcher(HttpMethod.DELETE, "/auth/intercepturl/**")).hasAuthority("SCOPE_DELETE")
                .requestMatchers(antMatcher("/auth/**")).authenticated());
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwkSetUri(this.jwkSetUri)));
        // @formatter:on
        return http.build();
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Order(2)
    @Bean
    public SecurityFilterChain wrongFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher(antMatcher("/wrong/**"));
        // @formatter:off
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(antMatcher(HttpMethod.GET, "/wrong/**")).hasAuthority("SCOPE_READ")
                .requestMatchers(antMatcher(HttpMethod.POST, "/wrong/**")).hasAuthority("SCOPE_CREATE")
                .requestMatchers(antMatcher(HttpMethod.PUT, "/wrong/**")).hasAuthority("SCOPE_UPDATE")
                .requestMatchers(antMatcher(HttpMethod.DELETE, "/wrong/**")).hasAuthority("SCOPE_DELETE")
                .requestMatchers(antMatcher("/wrong/**")).authenticated());
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwkSetUri(this.jwkSetWrongUri)));
        // @formatter:on
        return http.build();
    }
}
