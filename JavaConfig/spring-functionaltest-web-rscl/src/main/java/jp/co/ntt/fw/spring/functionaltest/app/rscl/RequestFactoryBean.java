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
package jp.co.ntt.fw.spring.functionaltest.app.rscl;

import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.cookie.StandardCookieSpec;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.http.ssl.TLS;
import org.apache.hc.core5.pool.PoolConcurrencyPolicy;
import org.apache.hc.core5.pool.PoolReusePolicy;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

/**
 * <ul>
 * HTTPS用RestTemplateのClientHttpRequestFactory作成クラス。
 * </ul>
 */
public class RequestFactoryBean implements
                                FactoryBean<ClientHttpRequestFactory>,
                                DisposableBean {

    private String keyStoreFileName;

    private char[] keyStorePassword;

    private int maxConnTotal = 2;

    private int maxConnPerRoute = 1;

    private long soTimeoutMilliseconds = 180_000L;

    private long connectionTimeToLiveSeconds = 60L;

    private long connectTimeoutSeconds = 5L;

    private long responseTimeoutSeconds = 10L;

    private HttpComponentsClientHttpRequestFactory factory;

    @Override
    public ClientHttpRequestFactory getObject() throws Exception {

        // SSL利用の設定
        SSLContext sslContext = SSLContext.getInstance("TLS");

        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(this.getClass().getClassLoader().getResourceAsStream(
                this.keyStoreFileName), this.keyStorePassword);

        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory
                .getDefaultAlgorithm());
        kmf.init(ks, this.keyStorePassword);

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ks);

        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        // @formatter:off
        PoolingHttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(
                        SSLConnectionSocketFactoryBuilder.create()
                        .setSslContext(sslContext)
                        .setTlsVersions(TLS.V_1_3, TLS.V_1_2)
                        .build())
                .setDefaultSocketConfig(
                        SocketConfig.custom()
                        .setSoTimeout(Timeout.ofMilliseconds(soTimeoutMilliseconds))
                        .build())
                .setMaxConnTotal(this.maxConnTotal)
                .setMaxConnPerRoute(this.maxConnPerRoute)
                .setPoolConcurrencyPolicy(PoolConcurrencyPolicy.STRICT)
                .setConnPoolPolicy(PoolReusePolicy.LIFO)
                .setDefaultConnectionConfig(
                        ConnectionConfig.custom()
                        .setTimeToLive(TimeValue.ofSeconds(this.connectionTimeToLiveSeconds))
                        .setConnectTimeout(Timeout.ofSeconds(this.connectTimeoutSeconds))
                        .build())
                .build();
        // @formatter:on

        // @formatter:off
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(
                        RequestConfig.custom()
                        .setResponseTimeout(Timeout.ofSeconds(this.responseTimeoutSeconds))
                        .setCookieSpec(StandardCookieSpec.STRICT).build())
                .build();
        // @formatter:on

        // RestTemplateへ渡すRequestFactoryの作成
        this.factory = new HttpComponentsClientHttpRequestFactory(httpClient);

        return this.factory;
    }

    @Override
    public Class<?> getObjectType() {
        return ClientHttpRequestFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void destroy() throws Exception {
        if (this.factory != null) {
            this.factory.destroy();
        }
    }

    public void setKeyStoreFileName(String keyStoreFileName) {
        this.keyStoreFileName = keyStoreFileName;
    }

    public void setKeyStorePassword(char[] keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
    }

    public void setMaxConnTotal(int maxConnTotal) {
        this.maxConnTotal = maxConnTotal;
    }

    public void setMaxConnPerRoutes(int maxConnPerRoute) {
        this.maxConnPerRoute = maxConnPerRoute;
    }

    public void setSoTimeoutMilliseconds(long soTimeoutMilliseconds) {
        this.soTimeoutMilliseconds = soTimeoutMilliseconds;
    }

    public void setConnectionTimeToLiveSeconds(
            long connectionTimeToLiveSeconds) {
        this.connectionTimeToLiveSeconds = connectionTimeToLiveSeconds;
    }

    public void setConnectTimeoutSeconds(long connectTimeoutSeconds) {
        this.connectTimeoutSeconds = connectTimeoutSeconds;
    }

    public void setResponseTimeoutSeconds(long responseTimeoutSeconds) {
        this.responseTimeoutSeconds = responseTimeoutSeconds;
    }

}
