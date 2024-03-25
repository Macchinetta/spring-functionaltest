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
package jp.co.ntt.fw.spring.functionaltest.domain.service.rscl;

import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;

public class BasicCredentialsProviderFactoryBean implements
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

        char[] passwordCharArray = this.password == null ? null
                : this.password.toCharArray();
        UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials(this.userName, passwordCharArray);

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
