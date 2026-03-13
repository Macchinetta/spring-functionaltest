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
package jp.co.ntt.fw.spring.functionaltest.config.app.resttemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.impl.DefaultHttpRequestRetryStrategy;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.NoOpResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.process.BasicCredentialsProviderFactoryBean;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.process.CustomAuthHeaderInitializer;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.process.CustomErrorHandler;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.process.CustomHeaderInitializer;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.process.CustomHttpMessageConverter;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.process.CustomOrder1Interceptor;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.process.CustomOrder2Interceptor;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.process.CustomUriBuilderFactory;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.process.LoggingInterceptor;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.process.LoggingMappingJackson2HttpMessageConverter;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.process.RequestFactoryBean;

/**
 * Application context.
 */
@Configuration
public class RestTemplateConfig {

    /**
     * baseurl property.
     */
    @Value("${rscl.applicationContextUrl}/api/v1")
    private String baseUrl;

    /**
     * basicAuth.username property.
     */
    @Value("${rscl.basicAuth.username}")
    private String basicAuthUsername;

    /**
     * basicAuth.password property.
     */
    @Value("${rscl.basicAuth.password}")
    private String basicAuthPassword;

    /**
     * basicAuth.invalidPassword property.
     */
    @Value("${rscl.basicAuth.invalidPassword}")
    private String basicAuthInvalidPassword;

    // HttpComponentsClientHttpRequestFactoryのTimeout

    /**
     * timeoutRestTemplate.connectTimeout property.
     */
    @Value("${rscl.timeoutRestTemplate.connectTimeout:2000}")
    private int connectTimeout;

    /**
     * timeoutRestTemplate.readTimeout property.
     */
    @Value("${rscl.timeoutRestTemplate.readTimeout:2000}")
    private int readTimeout;

    // SSL

    /**
     * keystore.filename property.
     */
    @Value("${rscl.keystore.filename}")
    private String keystoreFilename;

    /**
     * keystore.password property.
     */
    @Value("${rscl.keystore.password}")
    private String keystorePassword;

    // Proxy

    /**
     * http.proxyHost property.
     */
    @Value("${rscl.http.proxyHost}")
    private String httpProxyHost;

    /**
     * http.proxyPort property.
     */
    @Value("${rscl.http.proxyPort}")
    private int httpProxyPort;

    @Value("${rscl.http.proxyUserName}")
    String proxyUserName;

    @Value("${rscl.http.proxyPassword}")
    String proxyPassword;

    @Value("${rscl.http.proxyInvalidPassword}")
    String proxyInvalidPassword;

    /**
     * Configure {@link RestTemplate} bean.
     * 
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }

    /**
     * Configure {@link RestTemplate} bean.
     * 
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("bufferingRestTemplate")
    public RestTemplate bufferingRestTemplate() {
        return new RestTemplate(
                new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
    }

    /**
     * Configure {@link SourceHttpMessageConverter} bean.
     * 
     * @return Bean of configured {@link SourceHttpMessageConverter}
     */
    @Bean("sourceHttpMessageConverter")
    public SourceHttpMessageConverter<?> sourceHttpMessageConverter() {
        return new SourceHttpMessageConverter<>();
    }

    /**
     * Configure {@link RestTemplate} bean.
     * 
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("sourceRestTemplate")
    public RestTemplate sourceRestTemplate() {
        var bean = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(sourceHttpMessageConverter());
        bean.setMessageConverters(messageConverters);
        return bean;
    }

    /**
     * Configure {@link RestTemplate} bean.
     * 
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("baseRestTemplate")
    public RestTemplate baseRestTemplate() {
        var restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(baseUrl));
        return restTemplate;
    }

    /**
     * Configure {@link CustomErrorHandler} bean.
     * 
     * @return Bean of configured {@link CustomErrorHandler}
     */
    @Bean("customErrorHandler")
    public CustomErrorHandler customErrorHandler() {
        return new CustomErrorHandler();
    }

    /**
     * Configure {@link RestTemplate} bean.
     * 
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("statusRestTemplate")
    public RestTemplate statusRestTemplate() {
        var restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setErrorHandler(customErrorHandler());
        return restTemplate;
    }

    /**
     * Configure {@link NoOpResponseErrorHandler} bean.
     * 
     * @return Bean of configured {@link NoOpResponseErrorHandler}
     */
    @Bean("noOpResponseErrorHandler")
    public NoOpResponseErrorHandler noOpResponseErrorHandler() {
        return new NoOpResponseErrorHandler();
    }

    /**
     * Configure {@link RestTemplate} bean.
     * 
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("statusNoOpRestTemplate")
    public RestTemplate statusNoOpRestTemplate() {
        var restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setErrorHandler(noOpResponseErrorHandler());
        return restTemplate;
    }

    /**
     * Configure {@link CloseableHttpClient} bean that does not perform retry.
     * 
     * @return Bean of configured {@link CloseableHttpClient}
     */
    @Bean("noRetryHttpClient")
    public CloseableHttpClient noRetryHttpClient() {
        return HttpClientBuilder.create()
                .setRetryStrategy(
                        new DefaultHttpRequestRetryStrategy(0, TimeValue.ZERO_MILLISECONDS))
                .build();
    }

    /**
     * Configure {@link RestTemplate} bean.
     * 
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("exceptionRetryRestTemplate")
    public RestTemplate exceptionRetryRestTemplate() {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(noRetryHttpClient()));
    }

    /**
     * Configure {@link RestTemplate} bean.
     * 
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("statusRetryRestTemplate")
    public RestTemplate statusRetryRestTemplate() {
        var restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(noRetryHttpClient()));
        restTemplate.setErrorHandler(noOpResponseErrorHandler());
        return restTemplate;
    }

    /**
     * Configure {@link CustomHeaderInitializer} bean.
     * 
     * @return Bean of configured {@link CustomHeaderInitializer}
     */
    @Bean("customHeaderInitializer")
    public CustomHeaderInitializer customHeaderInitializer() {
        return new CustomHeaderInitializer();
    }

    /**
     * Configure {@link RestTemplate} bean.
     * 
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("headerRestTemplate")
    public RestTemplate headerRestTemplate() {
        var restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setClientHttpRequestInitializers(List.of(customHeaderInitializer()));
        return restTemplate;
    }

    /**
     * Configure {@link CustomAuthHeaderInitializer} bean.
     * 
     * @return Bean of configured {@link CustomAuthHeaderInitializer}
     */
    @Bean("customAuthHeaderInitializer")
    public CustomAuthHeaderInitializer customAuthHeaderInitializer() {
        return new CustomAuthHeaderInitializer(basicAuthUsername, basicAuthPassword);
    }

    /**
     * Configure {@link RestTemplate} bean.
     * 
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("authRestTemplate")
    public RestTemplate authRestTemplate() {
        var restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setClientHttpRequestInitializers(List.of(customAuthHeaderInitializer()));
        return restTemplate;
    }

    /**
     * Configure {@link CustomAuthHeaderInitializer} bean.
     * 
     * @return Bean of configured {@link CustomAuthHeaderInitializer}
     */
    @Bean("customAuthInvalidHeaderInitializer")
    public CustomAuthHeaderInitializer customAuthInvalidHeaderInitializer() {
        return new CustomAuthHeaderInitializer(basicAuthUsername, basicAuthInvalidPassword);
    }

    /**
     * Configure {@link RestTemplate} bean.
     * 
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("authInvalidRestTemplate")
    public RestTemplate authInvalidRestTemplate() {
        var restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        restTemplate
                .setClientHttpRequestInitializers(List.of(customAuthInvalidHeaderInitializer()));
        return restTemplate;
    }

    /**
     * Configure {@link HttpComponentsClientHttpRequestFactory} bean.
     * 
     * @return Bean of configured {@link HttpComponentsClientHttpRequestFactory}
     */
    @Bean("timeoutHttpRequestFactory")
    public HttpComponentsClientHttpRequestFactory timeoutHttpRequestFactory() {
        // 7.0からsetConnectTimeoutが非推奨となったため、ConnectionConfigで設定する。
        var connectionConfig = ConnectionConfig.custom()
                .setConnectTimeout(Timeout.ofMilliseconds(connectTimeout)).build();

        var poolManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setDefaultConnectionConfig(connectionConfig).build();

        var httpClient = HttpClientBuilder.create().setConnectionManager(poolManager).build();

        var bean = new HttpComponentsClientHttpRequestFactory(httpClient);
        bean.setReadTimeout(readTimeout);
        return bean;
    }

    /**
     * Configure {@link RestTemplate} bean.
     * 
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("timeoutRestTemplate")
    public RestTemplate timeoutRestTemplate() {
        return new RestTemplate(timeoutHttpRequestFactory());
    }

    /**
     * Configure {@link CustomUriBuilderFactory} bean.
     * 
     * @return Bean of configured {@link CustomUriBuilderFactory}
     */
    @Bean("customUriBuilderFactory")
    public CustomUriBuilderFactory customUriBuilderFactory() {
        return new CustomUriBuilderFactory();
    }

    /**
     * Configure {@link RestTemplate} bean.
     * 
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("uriRestTemplate")
    public RestTemplate uriRestTemplate() {
        var restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setUriTemplateHandler(customUriBuilderFactory());
        return restTemplate;
    }

    /**
     * Configure {@link RequestFactoryBean} bean.
     * 
     * @return Bean of configured {@link RequestFactoryBean}
     */
    @Bean("httpsRequestFactoryBean")
    public RequestFactoryBean httpsRequestFactoryBean() {
        var factory = new RequestFactoryBean();
        factory.setKeyStoreFileName(keystoreFilename);
        factory.setKeyStorePassword(keystorePassword.toCharArray());
        return factory;
    }

    /**
     * Configure {@link RestTemplate} bean.
     * 
     * @param httpsRequestFactoryBean bean of {@link RequestFactoryBean}
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("httpsRestTemplate")
    public RestTemplate httpsRestTemplate() throws Exception {
        return new RestTemplate(httpsRequestFactoryBean().getObject());
    }

    /**
     * Configure {@link RequestFactoryBean} bean.
     * 
     * @return Bean of configured {@link RequestFactoryBean}
     */
    @Bean("abnormalHttpsRequestFactoryBean")
    public RequestFactoryBean abnormalHttpsRequestFactoryBean() {
        RequestFactoryBean factory = new RequestFactoryBean();
        factory.setKeyStoreFileName(keystoreFilename);
        factory.setKeyStorePassword(keystorePassword.toCharArray());
        factory.setHandshakeTimeoutMilliseconds(1);
        factory.setConnectionTimeToLiveSeconds(10);
        return factory;
    }

    /**
     * Configure {@link RestTemplate} bean.
     * 
     * @return Bean of configured {@link RestTemplate}
     * @throws Exception
     */
    @Bean("abnormalHttpsRestTemplate")
    public RestTemplate abnormalHttpsRestTemplate() throws Exception {
        return new RestTemplate(abnormalHttpsRequestFactoryBean().getObject());
    }

    /**
     * Configure {@link CustomHttpMessageConverter} bean.
     * 
     * @return Bean of configured {@link CustomHttpMessageConverter}
     */
    @Bean("customHttpMessageConverter")
    public CustomHttpMessageConverter customHttpMessageConverter() {
        return new CustomHttpMessageConverter();
    }

    /**
     * Configure {@link LoggingMappingJackson2MessageConverter} bean.
     * 
     * @return Bean of configured {@link LoggingMappingJackson2MessageConverter}
     */
    @Bean("mappingJackson2HttpMessageConverter")
    public LoggingMappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        return new LoggingMappingJackson2HttpMessageConverter();
    }

    /**
     * Configure {@link RestTemplate} bean.
     * 
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("converterRestTemplate")
    public RestTemplate converterRestTemplate() {
        var bean = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        // omitted: Default HttpMessageConverters registration
        converters.add(customHttpMessageConverter());
        converters.add(mappingJackson2HttpMessageConverter());
        bean.setMessageConverters(converters);
        return bean;
    }

    /**
     * Configure {@link LoggingInterceptor} bean.
     * 
     * @return Bean of configured {@link LoggingInterceptor}
     */
    @Bean("loggingInterceptor")
    public LoggingInterceptor loggingInterceptor() {
        return new LoggingInterceptor();
    }

    /**
     * Configure {@link RestTemplate} bean.
     * 
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("interceptorRestTemplate")
    public RestTemplate interceptorRestTemplate() {
        var bean = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        bean.setInterceptors(List.of(loggingInterceptor()));
        return bean;
    }

    /**
     * Configure {@link CustomOrder1Interceptor} bean.
     * 
     * @return Bean of configured {@link CustomOrder1Interceptor}
     */
    @Bean("customOrder1Interceptor")
    public CustomOrder1Interceptor customOrder1Interceptor() {
        return new CustomOrder1Interceptor();
    }

    /**
     * Configure {@link CustomOrder2Interceptor} bean.
     * 
     * @return Bean of configured {@link CustomOrder2Interceptor}
     */
    @Bean("customOrder2Interceptor")
    public CustomOrder2Interceptor customOrder2Interceptor() {
        return new CustomOrder2Interceptor();
    }

    /**
     * Configure {@link RestTemplate} bean.
     * 
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("multiInterceptorRestTemplate")
    public RestTemplate multiInterceptorRestTemplate() {
        var bean = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        bean.setInterceptors(List.of(customOrder1Interceptor(), customOrder2Interceptor()));
        return bean;
    }

    // Proxyの標準設定

    /**
     * Configure {@link InetSocketAddress} bean.
     * 
     * @return Bean of configured {@link InetSocketAddress}
     */
    @Bean("inetSocketAddress")
    public InetSocketAddress inetSocketAddress() {
        return new InetSocketAddress(httpProxyHost, httpProxyPort);
    }

    /**
     * Configure {@link Proxy} bean.
     * 
     * @return Bean of configured {@link Proxy}
     */
    @Bean
    public Proxy proxy() {
        return new Proxy(Proxy.Type.HTTP, inetSocketAddress());
    }

    /**
     * Configure {@link HttpHost} bean.
     * 
     * @return Bean of configured {@link HttpHost}
     */
    @Bean
    public HttpHost httpHost() {
        return new HttpHost(httpProxyHost, httpProxyPort);
    }

    /**
     * Configure {@link HttpClientBuilder} bean.
     * 
     * @return Bean of configured {@link HttpClientBuilder}
     */
    @Bean("proxyHttpClientBuilder")
    public HttpClientBuilder proxyHttpClientBuilder() {
        HttpClientBuilder bean = HttpClientBuilder.create();
        bean.setProxy(httpHost());
        return bean;
    }

    /**
     * Configure {@link HttpComponentsClientHttpRequestFactory} bean.
     * 
     * @return Bean of configured {@link HttpComponentsClientHttpRequestFactory}
     */
    @Bean("httpComponentsClientHttpRequestFactory")
    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(proxyHttpClientBuilder().build());
    }

    /**
     * Configure {@link RestTemplate} bean.
     * 
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("httpComponentsProxyRestTemplate")
    public RestTemplate httpComponentsProxyRestTemplate() {
        return new RestTemplate(httpComponentsClientHttpRequestFactory());
    }

    // 認証成功

    /**
     * Configure {@link BasicCredentialsProviderFactoryBean} bean.
     * 
     * @return Bean of configured {@link BasicCredentialsProviderFactoryBean}
     */
    @Bean
    public BasicCredentialsProviderFactoryBean basicCredentialsSuccessProviderFactoryBean() {
        return new BasicCredentialsProviderFactoryBean(proxyUserName, proxyPassword);
    }

    /**
     * Configure {@link HttpClientBuilder} bean.
     * 
     * @return Bean of configured {@link HttpClientBuilder}
     * @throws Exception
     */
    @Bean("proxyAuthHttpClientSuccessBuilder")
    public HttpClientBuilder proxyAuthHttpClientSuccessBuilder() throws Exception {
        HttpClientBuilder bean = HttpClientBuilder.create();
        bean.setDefaultCredentialsProvider(
                basicCredentialsSuccessProviderFactoryBean().getObject());
        bean.setProxy(httpHost());
        return bean;
    }

    /**
     * Configure {@link HttpComponentsClientHttpRequestFactory} bean.
     * 
     * @return Bean of configured {@link HttpComponentsClientHttpRequestFactory}
     * @throws Exception
     */
    @Bean("httpComponentsClientAuthSuccessHttpRequestFactory")
    public HttpComponentsClientHttpRequestFactory httpComponentsClientAuthSuccessHttpRequestFactory()
            throws Exception {
        return new HttpComponentsClientHttpRequestFactory(
                proxyAuthHttpClientSuccessBuilder().build());
    }

    /**
     * Configure {@link RestTemplate} bean.
     * 
     * @return Bean of configured {@link RestTemplate}
     * @throws Exception
     */
    @Bean("credentialsSuccessProxyRestTemplate")
    public RestTemplate credentialsSuccessProxyRestTemplate() throws Exception {
        return new RestTemplate(httpComponentsClientAuthSuccessHttpRequestFactory());
    }

    // 認証失敗

    /**
     * Configure {@link BasicCredentialsProviderFactoryBean} bean.
     * 
     * @return Bean of configured {@link BasicCredentialsProviderFactoryBean}
     */
    @Bean
    public BasicCredentialsProviderFactoryBean basicCredentialsFailureProviderFactoryBean() {
        return new BasicCredentialsProviderFactoryBean(proxyUserName, proxyInvalidPassword);
    }

    /**
     * Configure {@link HttpClientBuilder} bean.
     * 
     * @return Bean of configured {@link HttpClientBuilder}
     * @throws Exception
     */
    @Bean("proxyAuthHttpClientFailureBuilder")
    public HttpClientBuilder proxyAuthHttpClientFailureBuilder() throws Exception {
        HttpClientBuilder bean = HttpClientBuilder.create();
        bean.setDefaultCredentialsProvider(
                basicCredentialsFailureProviderFactoryBean().getObject());
        bean.setProxy(httpHost());
        return bean;
    }

    /**
     * Configure {@link HttpComponentsClientHttpRequestFactory} bean.
     * 
     * @return Bean of configured {@link HttpComponentsClientHttpRequestFactory}
     * @throws Exception
     */
    @Bean("httpComponentsClientAuthFailureHttpRequestFactory")
    public HttpComponentsClientHttpRequestFactory httpComponentsClientAuthFailureHttpRequestFactory()
            throws Exception {
        return new HttpComponentsClientHttpRequestFactory(
                proxyAuthHttpClientFailureBuilder().build());
    }

    /**
     * Configure {@link RestTemplate} bean.
     * 
     * @return Bean of configured {@link RestTemplate}
     * @throws Exception
     */
    @Bean("credentialsFailureProxyRestTemplate")
    public RestTemplate credentialsFailureProxyRestTemplate() throws Exception {
        return new RestTemplate(httpComponentsClientAuthFailureHttpRequestFactory());
    }
}
