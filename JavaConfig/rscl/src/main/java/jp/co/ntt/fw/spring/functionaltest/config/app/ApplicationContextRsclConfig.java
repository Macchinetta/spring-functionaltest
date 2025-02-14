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
package jp.co.ntt.fw.spring.functionaltest.config.app;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpHost;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2CollectionHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.proxy.InternalProxyServer;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.proxy.InternalProxyServlet;
import jp.co.ntt.fw.spring.functionaltest.app.rscl.AccessCtrlInterceptor;
import jp.co.ntt.fw.spring.functionaltest.app.rscl.CustomErrorHandler;
import jp.co.ntt.fw.spring.functionaltest.app.rscl.LoggingInterceptor;
import jp.co.ntt.fw.spring.functionaltest.app.rscl.RequestFactoryBean;
import jp.co.ntt.fw.spring.functionaltest.app.server.RSCLHttpsServer;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.BasicCredentialsProviderFactoryBean;

/**
 * Application context.
 */
@Configuration
public class ApplicationContextRsclConfig {

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

    /**
     * httpsserver1.port property.
     */
    @Value("${rscl.httpsserver1.port}")
    private int httpsserver1Port;

    /**
     * httpsserver2.port property.
     */
    @Value("${rscl.httpsserver2.port}")
    private int httpsserver2Port;

    /**
     * httpsserver3.port property.
     */
    @Value("${rscl.httpsserver3.port}")
    private int httpsserver3Port;

    /**
     * httpsserver1.port property.
     */
    @Value("${rscl.httpsserver4.port}")
    private int httpsserver4Port;

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

    /**
     * http.proxyUseAuthPath property.
     */
    @Value("${cmmn.http.proxyUseAuthPath}")
    private String httpProxyUseAuthPath;

    /**
     * http.proxyPort property.
     */
    @Value("${cmmn.http.proxyUserName}")
    private String httpProxyUserName;

    /**
     * http.proxyPort property.
     */
    @Value("${cmmn.http.proxyPassword}")
    private String httpProxyPassword;

    /**
     * http.proxyPort property.
     */
    @Value("${cmmn.http.proxyPort}")

    private int cmmnHttpProxyPort;

    /**
     * Configure {@link RSCLHttpsServer} bean.
     * @return Bean of configured {@link RSCLHttpsServer}
     */
    @Bean(name = "rsclHttpsServer1", initMethod = "startServer", destroyMethod = "stopServer")
    public RSCLHttpsServer rsclHttpsServer1() {
        RSCLHttpsServer bean = new RSCLHttpsServer();
        bean.setKeyStoreFileName(keystoreFilename);
        bean.setKeyStorePassword(keystorePassword.toCharArray());
        bean.setPort(httpsserver1Port);
        return bean;
    }

    /**
     * Configure {@link RSCLHttpsServer} bean.
     * @return Bean of configured {@link RSCLHttpsServer}
     */
    @Bean(name = "rsclHttpsServer2", initMethod = "startServer", destroyMethod = "stopServer")
    public RSCLHttpsServer rsclHttpsServer2() {
        RSCLHttpsServer bean = new RSCLHttpsServer();
        bean.setKeyStoreFileName(keystoreFilename);
        bean.setKeyStorePassword(keystorePassword.toCharArray());
        bean.setPort(httpsserver2Port);
        return bean;
    }

    /**
     * Configure {@link RSCLHttpsServer} bean.
     * @return Bean of configured {@link RSCLHttpsServer}
     */
    @Bean(name = "rsclHttpsServer3", initMethod = "startServer", destroyMethod = "stopServer")
    public RSCLHttpsServer rsclHttpsServer3() {
        RSCLHttpsServer bean = new RSCLHttpsServer();
        bean.setKeyStoreFileName(keystoreFilename);
        bean.setKeyStorePassword(keystorePassword.toCharArray());
        bean.setPort(httpsserver3Port);
        return bean;
    }

    /**
     * Configure {@link RSCLHttpsServer} bean.
     * @return Bean of configured {@link RSCLHttpsServer}
     */
    @Bean(name = "rsclHttpsServer4", initMethod = "startServer", destroyMethod = "stopServer")
    public RSCLHttpsServer rsclHttpsServer4() {
        RSCLHttpsServer bean = new RSCLHttpsServer();
        bean.setKeyStoreFileName(keystoreFilename);
        bean.setKeyStorePassword(keystorePassword.toCharArray());
        bean.setPort(httpsserver4Port);
        return bean;
    }

    /**
     * Configure {@link RestTemplate} bean.
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Configure {@link RestTemplate} bean.
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("bufferingRestTemplate")
    public RestTemplate bufferingRestTemplate() {
        return new RestTemplate(
                new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
    }

    /**
     * Configure {@link Jaxb2CollectionHttpMessageConverter} bean.
     * @return Bean of configured {@link Jaxb2CollectionHttpMessageConverter}
     */
    @Bean("jaxb2CollectionHttpMessageConverter")
    public Jaxb2CollectionHttpMessageConverter<?> jaxb2CollectionHttpMessageConverter() {
        return new Jaxb2CollectionHttpMessageConverter<>();
    }

    /**
     * Configure {@link RestTemplate} bean.
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("collectionRestTemplate")
    public RestTemplate collectionRestTemplate() {
        RestTemplate bean = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(jaxb2CollectionHttpMessageConverter());
        bean.setMessageConverters(messageConverters);
        return bean;
    }

    /**
     * Configure {@link SourceHttpMessageConverter} bean.
     * @return Bean of configured {@link SourceHttpMessageConverter}
     */
    @Bean("sourceHttpMessageConverter")
    public SourceHttpMessageConverter<?> sourceHttpMessageConverter() {
        return new SourceHttpMessageConverter<>();
    }

    /**
     * Configure {@link RestTemplate} bean.
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("sourceRestTemplate")
    public RestTemplate sourceRestTemplate() {
        RestTemplate bean = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(sourceHttpMessageConverter());
        bean.setMessageConverters(messageConverters);
        return bean;
    }

    /**
     * Configure {@link CustomErrorHandler} bean.
     * @return Bean of configured {@link CustomErrorHandler}
     */
    @Bean("customErrorHandler")
    public CustomErrorHandler customErrorHandler() {
        return new CustomErrorHandler();
    }

    /**
     * Configure {@link RestTemplate} bean.
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("customErrorRestTemplate")
    public RestTemplate customErrorRestTemplate() {
        RestTemplate bean = new RestTemplate();
        bean.setErrorHandler(customErrorHandler());
        return bean;
    }

    /**
     * Configure {@link SimpleClientHttpRequestFactory} bean.
     * @return Bean of configured {@link SimpleClientHttpRequestFactory}
     */
    @Bean("clientHttpRequestFactory")
    public SimpleClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory bean = new SimpleClientHttpRequestFactory();
        bean.setConnectTimeout(connectTimeout);
        bean.setReadTimeout(readTimeout);
        return bean;
    }

    /**
     * Configure {@link RestTemplate} bean.
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("timeoutRestTemplate")
    public RestTemplate timeoutRestTemplate() {
        return new RestTemplate(clientHttpRequestFactory());
    }

    /**
     * Configure {@link RestTemplate} bean.
     * @return Bean of configured {@link RestTemplate}
     * @throws Exception
     */
    @Bean("httpsRestTemplate")
    public RestTemplate httpsRestTemplate() throws Exception {
        return new RestTemplate(httpsRequestFactoryBean().getObject());
    }

    /**
     * Configure {@link RequestFactoryBean} bean.
     * @return Bean of configured {@link RequestFactoryBean}
     */
    @Bean("httpsRequestFactoryBean")
    public RequestFactoryBean httpsRequestFactoryBean() {
        RequestFactoryBean factory = new RequestFactoryBean();
        factory.setKeyStoreFileName(keystoreFilename);
        factory.setKeyStorePassword(keystorePassword.toCharArray());
        return factory;
    }

    /**
     * Configure {@link RestTemplate} bean.
     * @return Bean of configured {@link RestTemplate}
     * @throws Exception
     */
    @Bean("abnormalHttpsRestTemplate")
    public RestTemplate abnormalHttpsRestTemplate() throws Exception {
        return new RestTemplate(abnormalHttpsRequestFactoryBean().getObject());
    }

    /**
     * Configure {@link RequestFactoryBean} bean.
     * @return Bean of configured {@link RequestFactoryBean}
     */
    @Bean("abnormalHttpsRequestFactoryBean")
    public RequestFactoryBean abnormalHttpsRequestFactoryBean() {
        RequestFactoryBean factory = new RequestFactoryBean();
        factory.setKeyStoreFileName(keystoreFilename);
        factory.setKeyStorePassword(keystorePassword.toCharArray());
        factory.setSoTimeoutMilliseconds(1);
        factory.setConnectionTimeToLiveSeconds(10);
        return factory;
    }

    /**
     * Configure {@link BasicAuthenticationInterceptor} bean.
     * @return Bean of configured {@link BasicAuthenticationInterceptor}
     */
    @Bean("basicAuthInterceptor")
    public BasicAuthenticationInterceptor basicAuthInterceptor() {
        return new BasicAuthenticationInterceptor(basicAuthUsername, basicAuthPassword);
    }

    /**
     * Configure {@link BasicAuthenticationInterceptor} bean.
     * @return Bean of configured {@link BasicAuthenticationInterceptor}
     */
    @Bean("invalidCredentialBasicAuthInterceptor")
    public BasicAuthenticationInterceptor invalidCredentialBasicAuthInterceptor() {
        return new BasicAuthenticationInterceptor(basicAuthUsername, basicAuthInvalidPassword);
    }

    /**
     * Configure {@link LoggingInterceptor} bean.
     * @return Bean of configured {@link LoggingInterceptor}
     */
    @Bean("loggingInterceptor")
    public LoggingInterceptor loggingInterceptor() {
        return new LoggingInterceptor();
    }

    /**
     * Configure {@link AccessCtrlInterceptor} bean.
     * @return Bean of configured {@link AccessCtrlInterceptor}
     */
    @Bean("accessCtrlInterceptor")
    public AccessCtrlInterceptor accessCtrlInterceptor() {
        return new AccessCtrlInterceptor();
    }

    /**
     * Configure {@link RestTemplate} bean.
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("interceptorsRestTemplate")
    public RestTemplate interceptorsRestTemplate() {
        RestTemplate bean = new RestTemplate();
        List<ClientHttpRequestInterceptor> list = new ArrayList<ClientHttpRequestInterceptor>();
        list.add(basicAuthInterceptor());
        list.add(loggingInterceptor());
        list.add(accessCtrlInterceptor());
        bean.setInterceptors(list);
        return bean;
    }

    /**
     * Configure {@link RestTemplate} bean.
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("invalidCredentialInterceptorsRestTemplate")
    public RestTemplate invalidCredentialInterceptorsRestTemplate() {
        RestTemplate bean = new RestTemplate();
        List<ClientHttpRequestInterceptor> list = new ArrayList<ClientHttpRequestInterceptor>();
        list.add(invalidCredentialBasicAuthInterceptor());
        list.add(loggingInterceptor());
        list.add(accessCtrlInterceptor());
        bean.setInterceptors(list);
        return bean;
    }

    /**
     * Configure {@link HttpClientBuilder} bean.
     * @return Bean of configured {@link HttpClientBuilder}
     * @throws Exception
     */
    @Bean("proxyAuthHttpClientBuilder")
    public HttpClientBuilder proxyAuthHttpClientBuilder() throws Exception {
        HttpClientBuilder bean = HttpClientBuilder.create();
        bean.setDefaultCredentialsProvider(basicCredentialsProviderFactoryBean().getObject());
        bean.setProxy(httpHost());
        return bean;
    }

    /**
     * Configure {@link BasicCredentialsProviderFactoryBean} bean.
     * @return Bean of configured {@link BasicCredentialsProviderFactoryBean}
     */
    @Bean
    public BasicCredentialsProviderFactoryBean basicCredentialsProviderFactoryBean() {
        return new BasicCredentialsProviderFactoryBean();
    }

    /**
     * Configure {@link HttpHost} bean.
     * @return Bean of configured {@link HttpHost}
     */
    @Bean
    public HttpHost httpHost() {
        return new HttpHost(httpProxyHost, httpProxyPort);
    }

    /**
     * Configure {@link RestTemplate} bean.
     * @return Bean of configured {@link RestTemplate}
     * @throws Exception
     */
    @Bean("proxyAuthRestTemplate")
    public RestTemplate proxyAuthRestTemplate() throws Exception {
        return new RestTemplate(httpComponentsClientAuthHttpRequestFactory());
    }

    /**
     * Configure {@link HttpComponentsClientHttpRequestFactory} bean.
     * @return Bean of configured {@link HttpComponentsClientHttpRequestFactory}
     * @throws Exception
     */
    @Bean("httpComponentsClientAuthHttpRequestFactory")
    public HttpComponentsClientHttpRequestFactory httpComponentsClientAuthHttpRequestFactory()
            throws Exception {
        return new HttpComponentsClientHttpRequestFactory(proxyAuthHttpClientBuilder().build());
    }

    /**
     * Configure {@link HttpClientBuilder} bean.
     * @return Bean of configured {@link HttpClientBuilder}
     */
    @Bean("proxyHttpClientBuilder")
    public HttpClientBuilder proxyHttpClientBuilder() {
        HttpClientBuilder bean = HttpClientBuilder.create();
        bean.setProxy(httpHost());
        return bean;
    }

    /**
     * Configure {@link RestTemplate} bean.
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("proxyRestTemplate")
    public RestTemplate proxyRestTemplate() {
        return new RestTemplate(httpComponentsClientHttpRequestFactory());
    }

    /**
     * Configure {@link HttpComponentsClientHttpRequestFactory} bean.
     * @return Bean of configured {@link HttpComponentsClientHttpRequestFactory}
     */
    @Bean("httpComponentsClientHttpRequestFactory")
    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(proxyHttpClientBuilder().build());
    }

    /**
     * Configure {@link InetSocketAddress} bean.
     * @return Bean of configured {@link InetSocketAddress}
     */
    @Bean("inetSocketAddress")
    public InetSocketAddress inetSocketAddress() {
        return new InetSocketAddress(httpProxyHost, httpProxyPort);
    }

    /**
     * Configure {@link RestTemplate} bean.
     * @return Bean of configured {@link RestTemplate}
     */
    @Bean("simpleClientRestTemplate")
    public RestTemplate simpleClientRestTemplate() {
        return new RestTemplate(simpleClientHttpRequestFactory());
    }

    /**
     * Configure {@link SimpleClientHttpRequestFactory} bean.
     * @return Bean of configured {@link SimpleClientHttpRequestFactory}
     */
    @Bean
    public SimpleClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory bean = new SimpleClientHttpRequestFactory();
        bean.setProxy(proxy());
        return bean;
    }

    /**
     * Configure {@link Proxy} bean.
     * @return Bean of configured {@link Proxy}
     */
    @Bean
    public Proxy proxy() {
        return new Proxy(Proxy.Type.HTTP, inetSocketAddress());
    }

    /**
     * Configure {@link InternalProxyServlet} bean.
     * @return Bean of configured {@link InternalProxyServlet}
     */
    @Bean("proxyServlet")
    public InternalProxyServlet proxyServlet() {
        return new InternalProxyServlet(httpProxyUseAuthPath, httpProxyUserName, httpProxyPassword);
    }

    /**
     * Configure {@link InternalProxyServer} bean.
     * @param proxyServlet Bean defined by #proxyServlet
     * @return Bean of configured {@link InternalProxyServer}
     */
    @Bean(initMethod = "startServer", destroyMethod = "stopServer")
    public InternalProxyServer internalProxyServer() {
        InternalProxyServer server = new InternalProxyServer();
        server.setInternalProxyServlet(proxyServlet());
        server.setPort(cmmnHttpProxyPort);
        return server;
    }
}
