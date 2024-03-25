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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;

import jp.co.ntt.fw.spring.functionaltest.app.handler.RestTemplateResponseErrorHandler;
import jp.co.ntt.fw.spring.functionaltest.domain.service.interceptor.RestTemplateAccessTokenInterceptor;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
public class SpringSecurityOth2Config {

    /**
     * connection.timeout property.
     */
    @Value("${oth2.resource.connection.timeout:10000}")
    private Integer connectTimeout;

    /**
     * read.timeout property.
     */
    @Value("${oth2.resource.read.timeout:10000}")
    private Integer readTimeout;

    /**
     * server.url property.
     */
    @Value("${oth2.authorization.server.url}")
    private String authorizationUrl;

    /**
     * server.wrongurl property.
     */
    @Value("${oth2.authorization.server.wrongurl}")
    private String authorizationWrongUrl;

    /**
     * token.url property.
     */
    @Value("${oth2.authorization.token.url}")
    private String tokenUrl;

    /**
     * token.wrongurl property.
     */
    @Value("${oth2.authorization.token.wrongurl}")
    private String tokenWrongUrl;

    /**
     * redirect.url property.
     */
    @Value("${oth2.redirect.url}")
    private String redirectUri;

    /**
     * client.secret property.
     */
    @Value("${oth2.client.secret}")
    private String clientSecret;

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    @Order(1)
    public SecurityFilterChain filterChainOAtuh2(
            HttpSecurity http) throws Exception {
        http.securityMatcher(new AntPathRequestMatcher("/auth/**"));
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.oauth2Client(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(
                new AntPathRequestMatcher("/**")).permitAll());

        return http.build();
    }

    /**
     * Saving authentication requests.
     * Configure {@link AuthorizationRequestRepository} bean.
     * @return Bean of configured {@link AuthorizationRequestRepository}
     */
    @Bean
    public AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {
        return new HttpSessionOAuth2AuthorizationRequestRepository();
    }

    /**
     * Configure {@link ClientRegistrationRepository} bean.
     * @return Bean of configured {@link ClientRegistrationRepository}
     */
    // ClientRegistration
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        // @formatter:off
        return new InMemoryClientRegistrationRepository(
                registrationRead(),
                registrationWrong(),
                registrationAll(),
                registrationPartial(),
                registrationTokenWrong(),
                registrationAuthWrong(),
                registrationWait());
        // @formatter:on
    }

    /**
     * READ Only
     * @return Bean of configured {@link ClientRegistration}
     */
    private ClientRegistration registrationRead() {
        // @formatter:off
        return ClientRegistration
                .withRegistrationId("registration_read")
                .clientId("readClient")
                .clientSecret(clientSecret)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri(redirectUri)
                .scope("READ")
                .authorizationUri(authorizationUrl)
                .tokenUri(tokenUrl)
                .build();
        // @formatter:on
    }

    /**
     * UPDATE not allowed
     * @return Bean of configured {@link ClientRegistration}
     */
    private ClientRegistration registrationWrong() {
        // @formatter:off
        return ClientRegistration
                .withRegistrationId("registration_wrong")
                .clientId("readClient")
                .clientSecret(clientSecret)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri(redirectUri)
                .scope("READ", "UPDATE")
                .authorizationUri(authorizationUrl)
                .tokenUri(tokenUrl)
                .build();
        // @formatter:on
    }

    /**
     * ALL OK
     * @return Bean of configured {@link ClientRegistration}
     */
    private ClientRegistration registrationAll() {
        // @formatter:off
        return ClientRegistration
                .withRegistrationId("registration_all")
                .clientId("testClient")
                .clientSecret(clientSecret)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri(redirectUri)
                .scope("READ", "UPDATE", "CREATE", "DELETE")
                .authorizationUri(authorizationUrl)
                .tokenUri(tokenUrl)
                .build();
        // @formatter:on
    }

    /**
     * READ or DELETE
     * @return Bean of configured {@link ClientRegistration}
     */
    private ClientRegistration registrationPartial() {
        // @formatter:off
        return ClientRegistration
                .withRegistrationId("registration_partial")
                .clientId("testClient")
                .clientSecret(clientSecret)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri(redirectUri)
                .scope("READ", "DELETE")
                .authorizationUri(authorizationUrl)
                .tokenUri(tokenUrl)
                .build();
        // @formatter:on
    }

    /**
     * AccessToken endpoint is wrong
     * @return Bean of configured {@link ClientRegistration}
     */
    private ClientRegistration registrationTokenWrong() {
        // @formatter:off
        return ClientRegistration
                .withRegistrationId("registration_token_wrong")
                .clientId("readClient")
                .clientSecret(clientSecret)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri(redirectUri)
                .scope("READ")
                .authorizationUri(authorizationUrl)
                .tokenUri(tokenWrongUrl)
                .build();
        // @formatter:on
    }

    /**
     * AccessToken endpoint is wrong
     * @return Bean of configured {@link ClientRegistration}
     */
    private ClientRegistration registrationAuthWrong() {
        // @formatter:off
        return ClientRegistration
                .withRegistrationId("registration_auth_wrong")
                .clientId("readClient")
                .clientSecret(clientSecret)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri(redirectUri)
                .scope("READ")
                .authorizationUri(authorizationWrongUrl)
                .tokenUri(tokenUrl)
                .build();
        // @formatter:on
    }

    /**
     * Send Wait
     * @return Bean of configured {@link ClientRegistration}
     */
    private ClientRegistration registrationWait() {
        // @formatter:off
        return ClientRegistration
                .withRegistrationId("registration_wait")
                .clientId("testClient")
                .clientSecret(clientSecret)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri(redirectUri)
                .scope("READ,UPDATE,CREATE,DELETE")
                .authorizationUri(authorizationUrl)
                .tokenUri(tokenUrl)
                .build();
        // @formatter:on
    }

    /**
     * Store and manage authorized clients in memory.
     * Configure {@link OAuth2AuthorizedClientService} bean.
     * @return Bean of configured {@link OAuth2AuthorizedClientService}
     */
    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
    }

    /**
     * Persist OAuth2AuthorizedClient between web requests.
     * Configure {@link OAuth2AuthorizedClientService} bean.
     * @return Bean of configured {@link OAuth2AuthorizedClientService}
     */
    @Bean
    public OAuth2AuthorizedClientRepository authorizedClientRepository() {
        return new AuthenticatedPrincipalOAuth2AuthorizedClientRepository(authorizedClientService());
    }

    /**
     * Configure {@link RestTemplate} bean.
     * @return Map of configured {@link RestTemplate}
     */
    // RestTemplate
    @Bean("oauthRestTemplateMap")
    public Map<String, RestTemplate> oauthRestTemplateMap() {
        Map<String, RestTemplate> map = new LinkedHashMap<String, RestTemplate>();
        map.put("registration_read", readOnlyRestTemplate());
        map.put("registration_wrong", wrongSettingRestTemplate());
        map.put("registration_all", allPermittedRestTemplate());
        map.put("registration_partial", partiallyPermittedRestTemplate());
        map.put("registration_token_wrong", wrongTokenRestTemplate());
        map.put("registration_auth_wrong", wrongAuthRestTemplate());
        map.put("registration_wait", waitRestTemplate());
        return map;
    }

    /**
     * Configure {@link RestTemplateResponseErrorHandler} bean.
     * @return Bean of configured {@link RestTemplateResponseErrorHandler}
     */
    @Bean("restTmplateErrorHandler")
    public RestTemplateResponseErrorHandler restTmplateErrorHandler() {
        return new RestTemplateResponseErrorHandler();
    }

    // RestTemplate configuration definition.

    /**
     * Configure {@link SimpleClientHttpRequestFactory} bean.
     * @return Bean of configured {@link SimpleClientHttpRequestFactory}
     */
    @Bean
    public SimpleClientHttpRequestFactory requestFactory() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);
        return requestFactory;
    }

    /**
     * Configure {@link ClientHttpRequestInterceptor} bean.
     * @return Bean of configured {@link ClientHttpRequestInterceptor}
     */
    @Bean
    public ClientHttpRequestInterceptor tokenInterceptorRead() {
        RestTemplateAccessTokenInterceptor bean = new RestTemplateAccessTokenInterceptor();
        bean.setRegistrationId("registration_read");
        return bean;
    }

    /**
     * Configure {@link ClientHttpRequestInterceptor} bean.
     * @return List of configured {@link ClientHttpRequestInterceptor}
     */
    @Bean
    public List<ClientHttpRequestInterceptor> tokenInterceptorReads() {
        return new ArrayList<ClientHttpRequestInterceptor>(Arrays.asList(
                tokenInterceptorRead()));
    }

    /**
     * Configure {@link ClientHttpRequestInterceptor} bean.
     * @return Bean of configured {@link ClientHttpRequestInterceptor}
     */
    @Bean
    public ClientHttpRequestInterceptor tokenInterceptorWrong() {
        RestTemplateAccessTokenInterceptor bean = new RestTemplateAccessTokenInterceptor();
        bean.setRegistrationId("registration_wrong");
        return bean;
    }

    /**
     * Configure {@link ClientHttpRequestInterceptor} bean.
     * @return List of configured {@link ClientHttpRequestInterceptor}
     */
    @Bean
    public List<ClientHttpRequestInterceptor> tokenInterceptorWrongs() {
        return new ArrayList<ClientHttpRequestInterceptor>(Arrays.asList(
                tokenInterceptorWrong()));
    }

    /**
     * Configure {@link ClientHttpRequestInterceptor} bean.
     * @return Bean of configured {@link ClientHttpRequestInterceptor}
     */
    @Bean
    public ClientHttpRequestInterceptor tokenInterceptorAll() {
        RestTemplateAccessTokenInterceptor bean = new RestTemplateAccessTokenInterceptor();
        bean.setRegistrationId("registration_all");
        return bean;
    }

    /**
     * Configure {@link ClientHttpRequestInterceptor} bean.
     * @return List of configured {@link ClientHttpRequestInterceptor}
     */
    @Bean
    public List<ClientHttpRequestInterceptor> tokenInterceptorAlls() {
        return new ArrayList<ClientHttpRequestInterceptor>(Arrays.asList(
                tokenInterceptorAll()));
    }

    /**
     * Configure {@link ClientHttpRequestInterceptor} bean.
     * @return Bean of configured {@link ClientHttpRequestInterceptor}
     */
    @Bean
    public ClientHttpRequestInterceptor tokenInterceptorPartial() {
        RestTemplateAccessTokenInterceptor bean = new RestTemplateAccessTokenInterceptor();
        bean.setRegistrationId("registration_partial");
        return bean;
    }

    /**
     * Configure {@link ClientHttpRequestInterceptor} bean.
     * @return List of configured {@link ClientHttpRequestInterceptor}
     */
    @Bean
    public List<ClientHttpRequestInterceptor> tokenInterceptorPartials() {
        return new ArrayList<ClientHttpRequestInterceptor>(Arrays.asList(
                tokenInterceptorPartial()));
    }

    /**
     * Configure {@link ClientHttpRequestInterceptor} bean.
     * @return Bean of configured {@link ClientHttpRequestInterceptor}
     */
    @Bean
    public ClientHttpRequestInterceptor tokenInterceptorTokenWrong() {
        RestTemplateAccessTokenInterceptor bean = new RestTemplateAccessTokenInterceptor();
        bean.setRegistrationId("registration_token_wrong");
        return bean;
    }

    /**
     * Configure {@link ClientHttpRequestInterceptor} bean.
     * @return List of configured {@link ClientHttpRequestInterceptor}
     */
    @Bean
    public List<ClientHttpRequestInterceptor> tokenInterceptorTokenWrongs() {
        return new ArrayList<ClientHttpRequestInterceptor>(Arrays.asList(
                tokenInterceptorTokenWrong()));
    }

    /**
     * Configure {@link ClientHttpRequestInterceptor} bean.
     * @return Bean of configured {@link ClientHttpRequestInterceptor}
     */
    @Bean
    public ClientHttpRequestInterceptor tokenInterceptorAuthWrong() {
        RestTemplateAccessTokenInterceptor bean = new RestTemplateAccessTokenInterceptor();
        bean.setRegistrationId("registration_auth_wrong");
        return bean;
    }

    /**
     * Configure {@link ClientHttpRequestInterceptor} bean.
     * @return List of configured {@link ClientHttpRequestInterceptor}
     */
    @Bean
    public List<ClientHttpRequestInterceptor> tokenInterceptorAuthWrongs() {
        return new ArrayList<ClientHttpRequestInterceptor>(Arrays.asList(
                tokenInterceptorAuthWrong()));
    }

    /**
     * Configure {@link ClientHttpRequestInterceptor} bean.
     * @return Bean of configured {@link ClientHttpRequestInterceptor}
     */
    @Bean
    public ClientHttpRequestInterceptor tokenInterceptorWait() {
        RestTemplateAccessTokenInterceptor bean = new RestTemplateAccessTokenInterceptor();
        bean.setRegistrationId("registration_wait");
        return bean;
    }

    /**
     * Configure {@link ClientHttpRequestInterceptor} bean.
     * @return List of configured {@link ClientHttpRequestInterceptor}
     */
    @Bean
    public List<ClientHttpRequestInterceptor> tokenInterceptorWaits() {
        return new ArrayList<ClientHttpRequestInterceptor>(Arrays.asList(
                tokenInterceptorWait()));
    }

    // Bean definition for RestTemplate.

    /**
     * Configure {@link RestTemplate} bean.
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("noAuthRestTemplate")
    public RestTemplate noAuthRestTemplate() {
        RestTemplate bean = new RestTemplate();
        bean.setRequestFactory(requestFactory());
        bean.setErrorHandler(restTmplateErrorHandler());
        return bean;
    }

    /**
     * Configure {@link RestTemplate} bean.
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("readOnlyRestTemplate")
    public RestTemplate readOnlyRestTemplate() {
        RestTemplate bean = new RestTemplate();
        bean.setRequestFactory(requestFactory());
        bean.setInterceptors(tokenInterceptorReads());
        bean.setErrorHandler(restTmplateErrorHandler());
        return bean;
    }

    /**
     * Configure {@link RestTemplate} bean.
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("wrongSettingRestTemplate")
    public RestTemplate wrongSettingRestTemplate() {
        RestTemplate bean = new RestTemplate();
        bean.setRequestFactory(requestFactory());
        bean.setInterceptors(tokenInterceptorWrongs());
        bean.setErrorHandler(restTmplateErrorHandler());
        return bean;
    }

    /**
     * Configure {@link RestTemplate} bean.
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("allPermittedRestTemplate")
    public RestTemplate allPermittedRestTemplate() {
        RestTemplate bean = new RestTemplate();
        bean.setRequestFactory(requestFactory());
        bean.setInterceptors(tokenInterceptorAlls());
        bean.setErrorHandler(restTmplateErrorHandler());
        return bean;
    }

    /**
     * Configure {@link RestTemplate} bean.
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("partiallyPermittedRestTemplate")
    public RestTemplate partiallyPermittedRestTemplate() {
        RestTemplate bean = new RestTemplate();
        bean.setRequestFactory(requestFactory());
        bean.setInterceptors(tokenInterceptorPartials());
        bean.setErrorHandler(restTmplateErrorHandler());
        return bean;
    }

    /**
     * Configure {@link RestTemplate} bean.
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("wrongTokenRestTemplate")
    public RestTemplate wrongTokenRestTemplate() {
        RestTemplate bean = new RestTemplate();
        bean.setRequestFactory(requestFactory());
        bean.setInterceptors(tokenInterceptorTokenWrongs());
        bean.setErrorHandler(restTmplateErrorHandler());
        return bean;
    }

    /**
     * Configure {@link RestTemplate} bean.
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("wrongAuthRestTemplate")
    public RestTemplate wrongAuthRestTemplate() {
        RestTemplate bean = new RestTemplate();
        bean.setRequestFactory(requestFactory());
        bean.setInterceptors(tokenInterceptorAuthWrongs());
        bean.setErrorHandler(restTmplateErrorHandler());
        return bean;
    }

    /**
     * Configure {@link RestTemplate} bean.
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("waitRestTemplate")
    public RestTemplate waitRestTemplate() {
        RestTemplate bean = new RestTemplate();
        bean.setRequestFactory(requestFactory());
        bean.setInterceptors(tokenInterceptorWaits());
        bean.setErrorHandler(restTmplateErrorHandler());
        return bean;
    }
}
