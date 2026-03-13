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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.proxy.InternalProxyServer;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.proxy.InternalProxyServlet;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.server.RSCLHttpsServer;

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

    // TLS Server Settings

    /**
     * Configure {@link RSCLHttpsServer} bean.
     * 
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
     * 
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
     * 
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
     * 
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

    // Proxy Server Settings

    /**
     * Configure {@link InternalProxyServlet} bean.
     * 
     * @return Bean of configured {@link InternalProxyServlet}
     */
    @Bean("proxyServlet")
    public InternalProxyServlet proxyServlet() {
        return new InternalProxyServlet(httpProxyUseAuthPath, httpProxyUserName, httpProxyPassword);
    }

    /**
     * Configure {@link InternalProxyServer} bean.
     * 
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
