/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.rscl;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;

public class BasicCredentialsProviderFactoryBean
                                                implements
                                                FactoryBean<BasicCredentialsProvider> {

    @Value("${rscl.http.proxyHost}")
    String host;

    @Value("${rscl.http.proxyPort}")
    int port;

    @Value("${rscl.http.proxyUserName}")
    String userName;

    @Value("${rscl.http.proxyPassword}")
    String password;

    @Override
    public BasicCredentialsProvider getObject() throws Exception {

        AuthScope authScope = new AuthScope(this.host, this.port);

        UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials(this.userName, this.password);

        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(authScope,
                usernamePasswordCredentials);
        return credentialsProvider;
    }

    @Override
    public Class<?> getObjectType() {
        return BasicCredentialsProvider.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
