/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.rscl;

import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

/**
 * <ul>
 * HTTPS用RestTemplateのClientHttpRequestFactory作成クラス。
 * </ul>
 */
public class RequestFactoryBean implements
                               FactoryBean<ClientHttpRequestFactory> {

    private String keyStoreFileName;

    private char[] keyStorePassword;

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

        TrustManagerFactory tmf = TrustManagerFactory
                .getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ks);

        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        // SSLコンテキストを設定したHTTPClientの作成
        HttpClient httpClient = HttpClientBuilder.create().setSSLContext(
                sslContext).build();

        // RestTemplateへ渡すRequestFactoryの作成
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

        return factory;
    }

    @Override
    public Class<?> getObjectType() {
        return ClientHttpRequestFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setKeyStoreFileName(String keyStoreFileName) {
        this.keyStoreFileName = keyStoreFileName;
    }

    public void setKeyStorePassword(char[] keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
    }

}
